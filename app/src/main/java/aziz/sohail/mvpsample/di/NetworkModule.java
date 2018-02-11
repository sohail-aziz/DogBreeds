package aziz.sohail.mvpsample.di;

import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import aziz.sohail.mvpsample.Constants;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Network module provides network dependencies
 */

@Module
public class NetworkModule {

    private static final String NAME_BASE_URL = "name_base_url";


    @Provides
    @Named(NAME_BASE_URL)
    String provideBaseUrlString() {
        return Constants.BASE_URL;
    }

    @Provides
    @Singleton
    OkHttpClient provideHttpClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();

//        return new OkHttpClient.Builder().build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient,
                             @Named(NAME_BASE_URL) String baseUrl) {

        Gson gson = new Gson();

        return new Retrofit.Builder().baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
