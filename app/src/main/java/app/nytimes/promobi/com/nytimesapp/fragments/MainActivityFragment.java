package app.nytimes.promobi.com.nytimesapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import app.nytimes.promobi.com.nytimesapp.R;
import app.nytimes.promobi.com.nytimesapp.interfaces.Api;
import app.nytimes.promobi.com.nytimesapp.models.Home;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void performCall(Retrofit retrofit) {

        //creating the api interface
        Api api = retrofit.create(Api.class);

        //now making the call object
        //Here we are using the api method that we created inside the api interface
        Call<Home> call = api.getHomeData();

        //then finallly we are making the call using enqueue()
        //it takes callback interface as an argument
        //and callback is having two methods onRespnose() and onFailure
        //if the request is successfull we will get the correct response and onResponse will be executed
        //if there is some error we will get inside the onFailure() method
        call.enqueue(new Callback<Home>() {
            @Override
            public void onResponse(Call<Home> call, Response<Home> response) {

                //In this point we got our hero list
                //thats damn easy right ;)
                Home home = response.body();

                //now we can do whatever we want with this list

            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
