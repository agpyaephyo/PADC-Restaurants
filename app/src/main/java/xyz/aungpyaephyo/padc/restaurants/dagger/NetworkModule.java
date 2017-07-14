package xyz.aungpyaephyo.padc.restaurants.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import xyz.aungpyaephyo.padc.restaurants.network.RestaurantsDataAgent;
import xyz.aungpyaephyo.padc.restaurants.network.RestaurantsDataAgentImpl;

/**
 * Created by aung on 7/15/17.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    RestaurantsDataAgent provideRestaurantsDataAgent() {
        return new RestaurantsDataAgentImpl();
    }
}
