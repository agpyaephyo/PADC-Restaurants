package xyz.aungpyaephyo.padc.restaurants.data.models.base;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import xyz.aungpyaephyo.padc.restaurants.network.RestaurantsDataAgent;
import xyz.aungpyaephyo.padc.restaurants.network.RestaurantsDataAgentImpl;

/**
 * Created by aung on 7/13/17.
 */

public class BaseModel {

    protected RestaurantsDataAgent mDataAgent;

    protected BaseModel() {
        mDataAgent = RestaurantsDataAgentImpl.getInstance();

        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onEventMainThread(Object obj) {

    }
}
