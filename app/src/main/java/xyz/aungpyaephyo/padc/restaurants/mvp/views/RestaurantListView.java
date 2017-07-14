package xyz.aungpyaephyo.padc.restaurants.mvp.views;

import java.util.List;

import xyz.aungpyaephyo.padc.restaurants.data.vos.RestaurantVO;

/**
 * Created by aung on 7/14/17.
 */

public interface RestaurantListView {

    void displayRestaurantList(List<RestaurantVO> restaurantList);
}
