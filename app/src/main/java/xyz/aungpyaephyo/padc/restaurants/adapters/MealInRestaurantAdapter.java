package xyz.aungpyaephyo.padc.restaurants.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import xyz.aungpyaephyo.padc.restaurants.R;
import xyz.aungpyaephyo.padc.restaurants.data.vos.MealInRestaurantVO;
import xyz.aungpyaephyo.padc.restaurants.views.holders.MealInRestaurantViewHolder;

/**
 * Created by aung on 7/27/17.
 */

public class MealInRestaurantAdapter extends BaseRecyclerAdapter<MealInRestaurantViewHolder, MealInRestaurantVO> {

    public MealInRestaurantAdapter(Context context) {
        super(context);
    }

    @Override
    public MealInRestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.view_item_meal_in_restaurant, parent, false);
        return new MealInRestaurantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MealInRestaurantViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 12;
    }
}
