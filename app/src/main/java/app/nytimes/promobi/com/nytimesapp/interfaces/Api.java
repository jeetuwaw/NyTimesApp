package app.nytimes.promobi.com.nytimesapp.interfaces;

import app.nytimes.promobi.com.nytimesapp.models.Home;
import app.nytimes.promobi.com.nytimesapp.utilities.Utils;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface Api {


    @Headers("api-key:" + Utils.API_KEY)
    @GET(Utils.HOME_JSON)
    Observable<Home> getDataForHome();

    @Headers("api-key:" + Utils.API_KEY)
    @GET("world.json")
    Observable<Home> getDataForWorld();

    @Headers("api-key:" + Utils.API_KEY)
    @GET("national.json")
    Observable<Home> getDataForNational();

    @Headers("api-key:" + Utils.API_KEY)
    @GET("opinion.json")
    Observable<Home> getDataForOpinion();

    @GET("/")
    Observable<String> getHtmlData();
}
