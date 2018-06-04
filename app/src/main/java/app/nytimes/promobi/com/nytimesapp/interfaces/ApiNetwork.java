package app.nytimes.promobi.com.nytimesapp.interfaces;


import app.nytimes.promobi.com.nytimesapp.models.Home;
import app.nytimes.promobi.com.nytimesapp.utilities.Utils;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiNetwork {

    @Headers("api-key:" + Utils.API_KEY)
    @GET("home.json")
    Observable<Home> getHomeData();

}
