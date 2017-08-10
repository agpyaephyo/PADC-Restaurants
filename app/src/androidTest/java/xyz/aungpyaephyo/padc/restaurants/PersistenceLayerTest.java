package xyz.aungpyaephyo.padc.restaurants;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import xyz.aungpyaephyo.padc.restaurants.data.vos.RestaurantVO;
import xyz.aungpyaephyo.padc.restaurants.network.responses.RestaurantListResponse;
import xyz.aungpyaephyo.padc.restaurants.persistence.RestaurantsContract;
import xyz.aungpyaephyo.padc.restaurants.utils.TestUtils;

import static org.hamcrest.core.Is.is;

/**
 * Created by aung on 7/29/17.
 */
@RunWith(AndroidJUnit4.class)
public class PersistenceLayerTest {

    @Before
    public void beforePersistenceLayerTest() {
        InstrumentationRegistry.getTargetContext()
                .getContentResolver().delete(RestaurantsContract.RestaurantEntry.CONTENT_URI, null, null);
        InstrumentationRegistry.getTargetContext()
                .getContentResolver().delete(RestaurantsContract.RestaurantTagEntry.CONTENT_URI, null, null);
    }

    @Test
    public void testPersistenceLayer() {
        try {
            //TODO Prepare test data
            String restaurants = TestUtils.loadDummyData(TestUtils.OFFLINE_RESTAURANT_LIST);
            Gson gson = new GsonBuilder().create();
            RestaurantListResponse restaurantListResponse = gson.fromJson(restaurants, RestaurantListResponse.class);
            ContentValues[] restaurantCVs = new ContentValues[restaurantListResponse.getRestaurantList().size()];
            int index = 0;
            for(RestaurantVO restaurant : restaurantListResponse.getRestaurantList()) {
                restaurantCVs[index++] = restaurant.parseToContentValues();
            }

            //TODO Bulk Insert Test Data
            InstrumentationRegistry.getTargetContext()
                    .getContentResolver().bulkInsert(RestaurantsContract.RestaurantEntry.CONTENT_URI, restaurantCVs);

            //TODO Query
            Cursor restaurantsCursor = InstrumentationRegistry.getTargetContext()
                    .getContentResolver().query(RestaurantsContract.RestaurantEntry.CONTENT_URI, null, null, null, null);
            if(restaurantsCursor != null && restaurantsCursor.moveToFirst()) {
                List<RestaurantVO> restaurantList = new ArrayList<>();
                do {
                    restaurantList.add(RestaurantVO.parseFromCursor(restaurantsCursor));
                } while (restaurantsCursor.moveToNext());

                //TODO Verify the test data
                Assert.assertThat("Checking test data with retrieved data",
                        restaurantListResponse.getRestaurantList().size(), is(restaurantList.size()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @After
    public void afterPersistenceLayerTest() {

    }
}
