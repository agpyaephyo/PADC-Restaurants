package xyz.aungpyaephyo.padc.restaurants.mvp.views;

import java.util.List;

import xyz.aungpyaephyo.padc.restaurants.data.vos.RestaurantVO;
import xyz.aungpyaephyo.padc.restaurants.mvp.views.base.BaseView;

/**
 * Created by aung on 7/14/17.
 */

public interface RestaurantListView extends BaseView {

    void displayRestaurantList(List<RestaurantVO> restaurantList);
}
