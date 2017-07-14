package xyz.aungpyaephyo.padc.restaurants;

import android.app.Application;

import xyz.aungpyaephyo.padc.restaurants.data.models.RestaurantsModel;
import xyz.aungpyaephyo.padc.restaurants.utils.NetworkUtils;

/**
 * Created by aung on 7/13/17.
 */

public class RestaurantsApp extends Application {

    public static final String TAG = "RestaurantsApp";

    @Override
    public void onCreate() {
        super.onCreate();
        if (NetworkUtils.isOnline(getApplicationContext())) {
            RestaurantsModel.getInstance().loadRestaurants(getApplicationContext());
        }
    }
}
