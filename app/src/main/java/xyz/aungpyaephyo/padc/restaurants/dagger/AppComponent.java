package xyz.aungpyaephyo.padc.restaurants.dagger;

import javax.inject.Singleton;

import dagger.Component;
import xyz.aungpyaephyo.padc.restaurants.RestaurantsApp;
import xyz.aungpyaephyo.padc.restaurants.activities.RestaurantListActivity;
import xyz.aungpyaephyo.padc.restaurants.data.models.base.BaseModel;

/**
 * Created by aung on 7/14/17.
 */

@Singleton
@Component(modules = {AppModule.class, PresenterModule.class, NetworkModule.class, ModelModule.class})
public interface AppComponent {

    void inject(RestaurantListActivity restaurantListActivity);

    void inject(BaseModel baseModel);

    void inject(RestaurantsApp restaurantsApp);
}
