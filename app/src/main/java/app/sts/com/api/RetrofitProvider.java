package app.sts.com.api;

import android.content.Context;
import android.widget.Toast;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import java.util.concurrent.TimeUnit;
import app.sts.com.BuildConfig;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {

    public static Retrofit createRetrofit(Context context) {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    NetworkMonitor monitor = new NetworkMonitor(context);
                    if (!monitor.isConnected()) {
                        throw new NoNetworkException();
                    }
                    return chain.proceed(chain.request());
                })
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
        Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getDeclaredClass().equals(ModelAdapter.class);
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create();
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(ApiCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static <T> Observable<T> configureObservable(Context context, Observable<T> observable) {
        Observable<T> sharedObservable = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).share();
        sharedObservable.subscribe(t -> {
        }, error -> {
            if (error instanceof NoNetworkException) {
                Toast.makeText(context, "Your internet is slow or not connected."
                        , Toast.LENGTH_SHORT).show();
            }
        });
        return sharedObservable;
    }
}
