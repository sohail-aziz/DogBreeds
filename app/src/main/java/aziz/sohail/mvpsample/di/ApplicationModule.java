package aziz.sohail.mvpsample.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import aziz.sohail.mvpsample.Constants;
import aziz.sohail.mvpsample.data.local.AppDatabase;
import aziz.sohail.mvpsample.data.local.BreedDao;
import aziz.sohail.mvpsample.data.local.DogDao;
import aziz.sohail.mvpsample.data.remote.RestAPI;
import aziz.sohail.mvpsample.data.repository.BreedRepository;
import aziz.sohail.mvpsample.data.repository.BreedRepositoryImpl;
import aziz.sohail.mvpsample.data.repository.DogRepository;
import aziz.sohail.mvpsample.data.repository.DogRepositoryImpl;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Dagger Module providing all the dependencies
 */
@Module
public class ApplicationModule {

    private final Context context;

    private static final String NAME_BASE_URL = "name_base_url";


    public ApplicationModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @Named(NAME_BASE_URL)
    String provideBaseUrlString() {
        return Constants.BASE_URL;
    }

    @Provides
    @Named("ui_thread")
    Scheduler provideUiThread() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Named("executor_thread")
    Scheduler provideExecutorThread() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.context;
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

    @Provides
    @Singleton
    RestAPI provideDogAPI(Retrofit retrofit) {
        return retrofit.create(RestAPI.class);
    }

//    @Provides
//    @Singleton
//    public Repository provideRepository(RepositoryImpl repository) {
//        return repository;
//    }

    @Provides
    @Singleton
    public BreedRepository provideBreedRepository(BreedRepositoryImpl repository) {
        return repository;
    }

    @Provides
    @Singleton
    DogRepository provideDogRepository(DogRepositoryImpl repository) {
        return repository;
    }

    @Provides
    @Singleton
    AppDatabase provideDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database")
                .build();
    }

    @Singleton
    @Provides
    BreedDao provideBreedDao(AppDatabase database) {
        return database.breedDao();
    }

    @Singleton
    @Provides
    DogDao provideDogDao(AppDatabase database) {
        return database.dogDao();
    }


}
