package app.nytimes.promobi.com.nytimesapp.application;

import android.app.Application;

import javax.inject.Inject;

import app.nytimes.promobi.com.nytimesapp.interfaces.ApiComponent;
import app.nytimes.promobi.com.nytimesapp.interfaces.DaggerApiComponent;
import app.nytimes.promobi.com.nytimesapp.modules.ApiModule;
import app.nytimes.promobi.com.nytimesapp.modules.AppModule;
import app.nytimes.promobi.com.nytimesapp.utilities.Utils;

@SuppressWarnings("deprecation")
public class NyTimesApplication extends Application {

    @Inject
    ApiModule apiModule;
    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApiComponent = DaggerApiComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule(Utils.BASE_URL+Utils.TOP_STORIES_V2_URL))
                .build();
    }

    public ApiComponent getNetComponent() {
        return mApiComponent;
    }
}
