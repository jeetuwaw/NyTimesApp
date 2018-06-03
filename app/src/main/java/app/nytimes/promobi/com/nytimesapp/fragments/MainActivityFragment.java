package app.nytimes.promobi.com.nytimesapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import app.nytimes.promobi.com.nytimesapp.R;
import app.nytimes.promobi.com.nytimesapp.interfaces.Api;
import app.nytimes.promobi.com.nytimesapp.models.Home;
import app.nytimes.promobi.com.nytimesapp.utilities.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        performCall();
    }

    private void performCall() {
        String url = Utils.BASE_URL + Utils.TOP_STORIES_V2_URL;
        Log.d("Logger1==", url.toString());

        //Creating a retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        //creating the api interface
        Api api = retrofit.create(Api.class);

        //now making the call object
        //Here we are using the api method that we created inside the api interface
        Call<List<Home>> call = api.getHomeData(Utils.API_KEY);

        //then finallly we are making the call using enqueue()
        //it takes callback interface as an argument
        //and callback is having two methods onRespnose() and onFailure
        //if the request is successfull we will get the correct response and onResponse will be executed
        //if there is some error we will get inside the onFailure() method
        call.enqueue(new Callback<List<Home>>() {
            @Override
            public void onResponse(Call<List<Home>> call, Response<List<Home>> response) {

                //In this point we got our hero list
                //thats damn easy right ;)
                List<Home> heroList = response.body();

                for (Home home : heroList) {
                    Log.d("Logger2==", home.toString());

                }
                //now we can do whatever we want with this list

            }

            @Override
            public void onFailure(Call<List<Home>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
