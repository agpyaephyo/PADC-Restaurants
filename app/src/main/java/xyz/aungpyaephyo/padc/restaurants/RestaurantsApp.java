package xyz.aungpyaephyo.padc.restaurants;

import android.app.Application;

import com.facebook.stetho.Stetho;

import javax.inject.Inject;

import xyz.aungpyaephyo.padc.restaurants.dagger.AppComponent;
import xyz.aungpyaephyo.padc.restaurants.dagger.AppModule;
import xyz.aungpyaephyo.padc.restaurants.dagger.DaggerAppComponent;
import xyz.aungpyaephyo.padc.restaurants.data.models.RestaurantsModel;
import xyz.aungpyaephyo.padc.restaurants.utils.NetworkUtils;

/**
 * Created by aung on 7/13/17.
 */

public class RestaurantsApp extends Application {

    public static final String TAG = "RestaurantsApp";

    private AppComponent mAppComponent;

    @Inject
    RestaurantsModel mRestaurantModel;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

        mAppComponent = initDagger(this);

        getAppComponent().inject(this);
        if (NetworkUtils.isOnline(getApplicationContext())) {
            //mRestaurantModel.loadRestaurants(getApplicationContext());
        }
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    protected AppComponent initDagger(RestaurantsApp restaurantsApp) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(restaurantsApp))
                .build();
    }

}
