package xyz.aungpyaephyo.padc.restaurants.delegates;

import xyz.aungpyaephyo.padc.restaurants.data.vos.RestaurantVO;

/**
 * Created by aung on 7/27/17.
 */

public interface RestaurantViewHolderDelegate {

    void onTapRestaurantItem(RestaurantVO restaurant);
}
