package xyz.aungpyaephyo.padc.restaurants.dagger;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import xyz.aungpyaephyo.padc.restaurants.data.vos.RestaurantVO;
import xyz.aungpyaephyo.padc.restaurants.mvp.presenters.RestaurantListPresenter;
import xyz.aungpyaephyo.padc.restaurants.mvp.views.RestaurantListView;

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
