package app.nytimes.promobi.com.nytimesapp.interfaces;

import java.util.List;

import app.nytimes.promobi.com.nytimesapp.models.Home;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("home")
    Call<List<Home>> getHomeData(@Query("api-key") String apiKey);

}
