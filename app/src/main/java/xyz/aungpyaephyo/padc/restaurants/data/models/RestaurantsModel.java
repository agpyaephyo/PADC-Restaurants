package xyz.aungpyaephyo.padc.restaurants.data.models;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import xyz.aungpyaephyo.padc.restaurants.RestaurantsApp;
import xyz.aungpyaephyo.padc.restaurants.data.models.base.BaseModel;
import xyz.aungpyaephyo.padc.restaurants.data.vos.RestaurantVO;
import xyz.aungpyaephyo.padc.restaurants.events.DataEvents;
import xyz.aungpyaephyo.padc.restaurants.persistence.RestaurantsContract;

/**
 * Created by aung on 7/13/17.
 */

public class RestaurantsModel extends BaseModel {

    public RestaurantsModel(Context context) {
        super(context);
    }

    public void loadRestaurants(Context context) {
        mDataAgent.loadRestaurants(context);
    }

    @Subscribe(threadMode = ThreadMode.BackgroundThread)
    public void onRestaurantsLoaded(DataEvents.RestaurantListLoadedEvent event) {
        List<RestaurantVO> restaurantList = event.getRestaurantList();
        ContentValues[] restaurantCVs = new ContentValues[restaurantList.size()];
        List<ContentValues> restaurantTagCVList = new ArrayList<>();
        int index = 0;
        for (RestaurantVO restaurant : restaurantList) {
            restaurantCVs[index++] = restaurant.parseToContentValues();
            restaurantTagCVList.addAll(restaurant.parseRestaurantTagToContentValues());
        }

        Context context = event.getContext();

        ContentValues[] restaurantTagsCVArray = restaurantTagCVList.toArray(new ContentValues[restaurantTagCVList.size()]);
        int restaurantTagsInsertedCount = context.getContentResolver().bulkInsert(RestaurantsContract.RestaurantTagEntry.CONTENT_URI, restaurantTagsCVArray);
        Log.d(RestaurantsApp.TAG, "restaurantTagsInsertedCount : " + restaurantTagsInsertedCount);

        int restaurantInsertedCount = context.getContentResolver().bulkInsert(RestaurantsContract.RestaurantEntry.CONTENT_URI, restaurantCVs);
        Log.d(RestaurantsApp.TAG, "restaurantInsertedCount : " + restaurantInsertedCount);
    }
}
