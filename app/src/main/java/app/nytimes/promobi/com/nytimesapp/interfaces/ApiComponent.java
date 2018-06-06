package app.nytimes.promobi.com.nytimesapp.interfaces;

import javax.inject.Singleton;

import app.nytimes.promobi.com.nytimesapp.activities.MainActivity;
import app.nytimes.promobi.com.nytimesapp.fragments.DetailsFragment;
import app.nytimes.promobi.com.nytimesapp.fragments.MainActivityFragment;
import app.nytimes.promobi.com.nytimesapp.modules.ApiModule;
import app.nytimes.promobi.com.nytimesapp.modules.AppModule;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface ApiComponent {

    void inject(MainActivityFragment mainActivityFragment);
    void inject(DetailsFragment detailsFragment);


}
