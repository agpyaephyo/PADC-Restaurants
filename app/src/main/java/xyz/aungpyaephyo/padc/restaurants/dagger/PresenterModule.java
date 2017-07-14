package xyz.aungpyaephyo.padc.restaurants.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import xyz.aungpyaephyo.padc.restaurants.mvp.presenters.RestaurantListPresenter;

/**
 * Created by aung on 7/15/17.
 */

@Module
public class PresenterModule {

    @Provides
    @Singleton
    RestaurantListPresenter provideRestaurantListPresenter() {
        return new RestaurantListPresenter();
    }

}
