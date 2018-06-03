package app.nytimes.promobi.com.nytimesapp.interfaces;

import java.util.List;

import app.nytimes.promobi.com.nytimesapp.models.Home;
import app.nytimes.promobi.com.nytimesapp.models.National;
import app.nytimes.promobi.com.nytimesapp.models.Opinion;
import app.nytimes.promobi.com.nytimesapp.models.World;
import app.nytimes.promobi.com.nytimesapp.utilities.Utils;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Api {
    @Headers("api-key:" + Utils.API_KEY)
    @GET("home.json")
    Call<Home> getHomeData();

    @Headers("api-key:" + Utils.API_KEY)
    @GET("national.json")
    Call<National> getNationalData();

    @Headers("api-key:" + Utils.API_KEY)
    @GET("opinion.json")
    Call<Opinion> getOpinionData();

    @Headers("api-key:" + Utils.API_KEY)
    @GET("world.json")
    Call<World> getWorldData();


}
