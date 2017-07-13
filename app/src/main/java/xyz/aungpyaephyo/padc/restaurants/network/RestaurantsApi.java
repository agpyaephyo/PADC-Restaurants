package xyz.aungpyaephyo.padc.restaurants.network;

import retrofit2.Call;
import retrofit2.http.GET;
import xyz.aungpyaephyo.padc.restaurants.network.responses.RestaurantListResponse;
import xyz.aungpyaephyo.padc.restaurants.utils.RestaurantsConstants;

/**
 * Created by aung on 7/13/17.
 */

public interface RestaurantsApi {

    @GET(RestaurantsConstants.API_GET_RESTAURANTS_V2)
    Call<RestaurantListResponse> getRestaurantList();

}
