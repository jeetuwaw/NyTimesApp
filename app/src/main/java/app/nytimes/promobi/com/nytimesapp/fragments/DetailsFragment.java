package app.nytimes.promobi.com.nytimesapp.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.apache.http.HttpMessage;
import org.apache.http.io.HttpMessageParserFactory;
import org.greenrobot.eventbus.Subscribe;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.inject.Inject;

import app.nytimes.promobi.com.nytimesapp.R;
import app.nytimes.promobi.com.nytimesapp.activities.MainActivity;
import app.nytimes.promobi.com.nytimesapp.application.NyTimesApplication;
import app.nytimes.promobi.com.nytimesapp.eventsbus.Events;
import app.nytimes.promobi.com.nytimesapp.eventsbus.GlobalBus;
import app.nytimes.promobi.com.nytimesapp.eventsbus.OnBackPressedEvent;
import app.nytimes.promobi.com.nytimesapp.interfaces.Api;
import app.nytimes.promobi.com.nytimesapp.utilities.Convverter;
import app.nytimes.promobi.com.nytimesapp.utilities.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class DetailsFragment extends Fragment {


    @Inject
    Gson gson;
    @Inject
    okhttp3.OkHttpClient okHttpClient;

    private Events.FragmentActivityMessage event;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.progressbar)
    protected ProgressBar progressbar;
    private MainActivity mainActivity;
    private CompositeDisposable mCompositeDisposable;
    private Retrofit retrofit;


    public DetailsFragment() {

    }

    @SuppressLint("ValidFragment")
    public DetailsFragment(Events.FragmentActivityMessage event) {
        this.event = event;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
        if (mainActivity != null) {
            ((NyTimesApplication) mainActivity.getApplication()).getNetComponent().inject(this);
        } else {
            ((NyTimesApplication) getActivity().getApplication()).getNetComponent().inject(this);
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        GlobalBus.getBus().register(this);

        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Subscribe
    public void getMessage(Events.ActivityFragmentMessage activityFragmentMessage) {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (Utils.checkInternetConenction(getActivity())) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(HttpUrl.parse(event.getUrl().concat("/")))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
            Api api = retrofit.create(Api.class);
            mCompositeDisposable.add(api
                    .getHtmlData()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResult, this::handleError));

        } else {
            Utils.displayDialog(getActivity());
        }
    }

    @Subscribe
    public void getbackPressedEvent(OnBackPressedEvent event) {
        ((MainActivity) getActivity()).replaceFragment(getFragmentManager(), new MainActivityFragment(), false);
    }

    private void handleError(Throwable throwable) {
    }

    private void handleResult(String data) {
        webView.canGoBackOrForward(3);
        webView.canGoBack();
        if (Utils.checkInternetConenction(getActivity())) {
            webView.setWebViewClient(new WebViewClient(){

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                   progressbar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onPageFinished(WebView view, String url) {

                }
            });
            webView.setWebChromeClient(new WebChromeClient(){
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    progressbar.setProgress(newProgress);
                    if(newProgress == 100){
                        progressbar.setVisibility(View.GONE);
                    }
                }
            });
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadData(data, "text/html", "UTF-8");
        } else {
            Utils.displayDialog(getActivity());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        GlobalBus.getBus().unregister(this);
        webView.clearFormData();
        webView.clearCache(true);
        webView.clearMatches();
        webView.clearHistory();
        webView.destroy();
        webView = null;
        mCompositeDisposable.clear();
        mCompositeDisposable = null;
    }

}
