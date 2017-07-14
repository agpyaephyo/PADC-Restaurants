package xyz.aungpyaephyo.padc.restaurants.data.models.base;

import android.content.Context;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import xyz.aungpyaephyo.padc.restaurants.RestaurantsApp;
import xyz.aungpyaephyo.padc.restaurants.network.RestaurantsDataAgent;

/**
 * Created by aung on 7/13/17.
 */

public class BaseModel {

    @Inject
    protected RestaurantsDataAgent mDataAgent;

    protected BaseModel(Context context) {
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }

        ((RestaurantsApp) context).getAppComponent().inject(this);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onEventMainThread(Object obj) {

    }
}
