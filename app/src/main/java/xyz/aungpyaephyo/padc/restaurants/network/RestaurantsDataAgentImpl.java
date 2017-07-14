package xyz.aungpyaephyo.padc.restaurants.network;

import android.content.Context;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import de.greenrobot.event.EventBus;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.aungpyaephyo.padc.restaurants.events.DataEvents;
import xyz.aungpyaephyo.padc.restaurants.network.responses.RestaurantListResponse;
import xyz.aungpyaephyo.padc.restaurants.utils.RestaurantsConstants;

/**
 * Created by aung on 7/13/17.
 */

public class RestaurantsDataAgentImpl implements RestaurantsDataAgent {

    private static RestaurantsDataAgent objInstance;

    private final RestaurantsApi theApi;

    private RestaurantsDataAgentImpl() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestaurantsConstants.RESTAURANTS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(okHttpClient)
                .build();

        theApi = retrofit.create(RestaurantsApi.class);
    }

    public static RestaurantsDataAgent getInstance() {
        if (objInstance == null) {
            objInstance = new RestaurantsDataAgentImpl();
        }
        return objInstance;
    }

    @Override
    public void loadRestaurants(final Context context) {
        Call<RestaurantListResponse> loadRestaurantsCall = theApi.getRestaurantList();
        loadRestaurantsCall.enqueue(new Callback<RestaurantListResponse>() {
            @Override
            public void onResponse(Call<RestaurantListResponse> call, Response<RestaurantListResponse> response) {
                RestaurantListResponse restaurantListResponse = response.body();
                if (restaurantListResponse == null) {
                    EventBus.getDefault().post(new DataEvents.EmptyResponseBodyEvent());
                } else {
                    if (restaurantListResponse.getCode() == RestaurantsConstants.RESPONSE_CODE_OK) {
                        EventBus.getDefault().post(new DataEvents.RestaurantListLoadedEvent(context, restaurantListResponse.getRestaurantList()));
                    } else {
                        EventBus.getDefault().post(new DataEvents.RequestErrorEvent(restaurantListResponse.getMessage()));
                    }
                }
            }

            @Override
            public void onFailure(Call<RestaurantListResponse> call, Throwable t) {
                EventBus.getDefault().post(new DataEvents.RequestErrorEvent(t.getMessage()));
            }
        });
    }
}
