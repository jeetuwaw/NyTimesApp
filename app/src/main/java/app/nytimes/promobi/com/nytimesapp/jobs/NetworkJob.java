package app.nytimes.promobi.com.nytimesapp.jobs;

import com.android.volley.NetworkError;


import app.nytimes.promobi.com.nytimesapp.interfaces.ApiNetwork;
import app.nytimes.promobi.com.nytimesapp.models.Home;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;



public class NetworkJob {
    private final ApiNetwork apiNetwork;

    public NetworkJob(ApiNetwork apiNetwork) {
        this.apiNetwork = apiNetwork;
    }

    public Disposable getHomeList(final GetHomeDataCallback callback) {

        return apiNetwork.getHomeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Function<Throwable, Observable<? extends Home>>() {
                    @Override
                    public Observable<? extends Home> apply(Throwable throwable) throws Exception {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Consumer<Home>() {

                    @Override
                    public void accept(Home home) throws Exception {

                    }
                });
    }


    public interface GetHomeDataCallback{
        void onSuccess(Home home);

        void onError(NetworkError networkError);
    }
}