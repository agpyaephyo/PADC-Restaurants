package xyz.aungpyaephyo.padc.restaurants;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import xyz.aungpyaephyo.padc.restaurants.data.vos.RestaurantVO;
import xyz.aungpyaephyo.padc.restaurants.events.DataEvents;
import xyz.aungpyaephyo.padc.restaurants.network.RestaurantsDataAgentImpl;
import xyz.aungpyaephyo.padc.restaurants.network.responses.RestaurantListResponse;
import xyz.aungpyaephyo.padc.restaurants.utils.TestUtils;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by aung on 7/28/17.
 */
@RunWith(AndroidJUnit4.class)
public class NetworkLayerTest {

    private List<RestaurantVO> mOfflineRestaurants;
    private CountDownLatch mCountDown;

    @Before
    public void beforeNetworkLayerTest() {
        mCountDown = new CountDownLatch(1);
        EventBus.getDefault().register(this);
    }

    @Test
    public void networkLayerTest() {
        try {
            String restaurants = TestUtils.loadDummyData(TestUtils.OFFLINE_RESTAURANT_LIST);
            Gson gson = new GsonBuilder().create();
            RestaurantListResponse restaurantListResponse = gson.fromJson(restaurants, RestaurantListResponse.class);
            mOfflineRestaurants = restaurantListResponse.getRestaurantList();
            assertThat("Checking offline restaurants' size ", mOfflineRestaurants.size(), is(24));

            RestaurantsDataAgentImpl restaurantsDataAgent = new RestaurantsDataAgentImpl();
            restaurantsDataAgent.loadRestaurants(InstrumentationRegistry.getContext());
            mCountDown.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void afterNetworkLayerTest() {
        EventBus.getDefault().unregister(this);
    }



    @Subscribe(threadMode = ThreadMode.BackgroundThread)
    public void onRestaurantsLoaded(DataEvents.RestaurantListLoadedEvent event) {
        assertThat("Checking network response size", mOfflineRestaurants.size(), is(event.getRestaurantList().size()));
        mCountDown.countDown();
    }
}
