package xyz.aungpyaephyo.padc.restaurants.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import xyz.aungpyaephyo.padc.restaurants.data.models.RestaurantsModel;

/**
 * Created by aung on 7/15/17.
 */

@Module
public class ModelModule {

    @Provides
    @Singleton
    RestaurantsModel provideRestaurantsModel(Context context) {
        return new RestaurantsModel(context);
    }
}
