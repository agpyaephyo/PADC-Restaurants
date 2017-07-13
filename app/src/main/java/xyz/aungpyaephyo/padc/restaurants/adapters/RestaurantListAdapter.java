package xyz.aungpyaephyo.padc.restaurants.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import xyz.aungpyaephyo.padc.restaurants.R;
import xyz.aungpyaephyo.padc.restaurants.data.vo.RestaurantVO;
import xyz.aungpyaephyo.padc.restaurants.views.holders.RestaurantViewHolder;

/**
 * Created by aung on 7/13/17.
 */

public class RestaurantListAdapter extends BaseRecyclerAdapter<RestaurantViewHolder, RestaurantVO> {

    public RestaurantListAdapter(Context context) {
        super(context);
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.view_item_restaurants, parent, false);
        return new RestaurantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
