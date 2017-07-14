package xyz.aungpyaephyo.padc.restaurants.mvp.presenters.base;

import de.greenrobot.event.EventBus;
import xyz.aungpyaephyo.padc.restaurants.mvp.views.base.BaseView;

/**
 * Created by aung on 7/14/17.
 */

public abstract class BasePresenter<T extends BaseView> {

    protected T mView;

    public void onCreate() {
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    public void setView(T view) {
        mView = view;
    }

    public abstract void onStart();

    public abstract void onStop();

    public void onDestroy() {
        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }
}
