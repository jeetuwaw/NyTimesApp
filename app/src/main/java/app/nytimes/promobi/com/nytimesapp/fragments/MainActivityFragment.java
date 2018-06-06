package app.nytimes.promobi.com.nytimesapp.fragments;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import app.nytimes.promobi.com.nytimesapp.Adapters.SectionDataListAdapter;
import app.nytimes.promobi.com.nytimesapp.R;
import app.nytimes.promobi.com.nytimesapp.activities.MainActivity;
import app.nytimes.promobi.com.nytimesapp.application.NyTimesApplication;
import app.nytimes.promobi.com.nytimesapp.eventsbus.Events;
import app.nytimes.promobi.com.nytimesapp.eventsbus.GlobalBus;
import app.nytimes.promobi.com.nytimesapp.eventsbus.OnBackPressedEvent;
import app.nytimes.promobi.com.nytimesapp.interfaces.Api;
import app.nytimes.promobi.com.nytimesapp.models.Home;
import app.nytimes.promobi.com.nytimesapp.utilities.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @Inject
    Retrofit retrofit;

    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.num_results)
    TextView numOfResults;
    @BindView(R.id.section)
    TextView section;
    @BindView(R.id.copyrights)
    TextView copyRights;
    @BindView(R.id.last_updated)
    TextView lastupdated;

    private MainActivity mainActivity;
    private CompositeDisposable mCompositeDisposable;
    private SectionDataListAdapter sectionDataListAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
        if (mainActivity != null) {
            ((NyTimesApplication) mainActivity.getApplication()).getNetComponent().inject(this);
        } else {
            ((NyTimesApplication) getActivity().getApplication()).getNetComponent().inject(this);
        }
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        GlobalBus.getBus().register(this);

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Subscribe
    public void getMessage(Events.FragmentActivityMessage event) {
        ((MainActivity) getActivity()).replaceFragment(getFragmentManager(), new DetailsFragment(event), false);
    }

    @Subscribe
    public void getbackPressedEvent(OnBackPressedEvent event) {
        getActivity().finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Utils.checkInternetConenction(getActivity())) {
            getData();
        } else{
            Utils.displayDialog(getActivity());
        }
    }

    public void getData() {

        //creating the api interface
        Api api = retrofit.create(Api.class);
        mCompositeDisposable.add(api
                .getDataForHome()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, this::handleError));
    }

    private void handleError(Throwable t) {
        Toast.makeText(mainActivity.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void handleResult(Home home) {
        System.out.println("handleResult===" + home.toString());
        numOfResults.setText("Total: " + home.getResults().length);
        numOfResults.setTextColor(Color.WHITE);
        lastupdated.setText("Last Updated: " + home.getLast_updated());
        lastupdated.setTextColor(Color.WHITE);
        copyRights.setText("Cpyrights: " + home.getCopyright());
        copyRights.setTextColor(Color.WHITE);
        section.setText("Section: " + home.getSection());
        section.setTextColor(Color.WHITE);
        sectionDataListAdapter = new SectionDataListAdapter(getActivity(), home.getResults());
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setAdapter(sectionDataListAdapter);
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        GlobalBus.getBus().unregister(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
        mCompositeDisposable = null;
    }
}
