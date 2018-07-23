package com.azhar.university.magles.domain.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.azhar.university.magles.domain.models.User;
import com.azhar.university.magles.domain.utils.UserManager;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by interactive on 7/23/18.
 */
@Module
public class ApiModule {
    private final SharedPreferences preferences;
    private final File cacheFile;
    private final Application application;
    private final String url;

    public ApiModule(File cacheFile, Application application, String url) {
        this.cacheFile = cacheFile;
        this.application = application;
        preferences = PreferenceManager.getDefaultSharedPreferences(application);
        this.url = url;
    }

    @Provides
    @Singleton
    Retrofit provideCall() {
        final User user = UserManager.getCurrentUser(preferences);
        HttpLoggingInterceptor bodyLogging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i(getClass().getSimpleName(), message);
            }
        });
        bodyLogging.setLevel(HttpLoggingInterceptor.Level.BODY);
        HttpLoggingInterceptor headerLogging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i(getClass().getSimpleName(), message);
            }
        });
        headerLogging.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        HttpLoggingInterceptor bodyLogging1 = new HttpLoggingInterceptor();
        bodyLogging1.setLevel(HttpLoggingInterceptor.Level.BODY);
        HttpLoggingInterceptor headerLogging1 = new HttpLoggingInterceptor();
        headerLogging1.setLevel(HttpLoggingInterceptor.Level.HEADERS);// The Interceptor is then added to the client
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request request;
                        request = original.newBuilder()
                                .header("Content-Type", "application/json")
                                .header("Authorization", user.getAuthorization())
                                .header("Cache-Control", "no-cache")
                                .removeHeader("Pragma")
                                .build();
                        Response response = chain.proceed(request);
                        response.cacheResponse();
                        // Customize or return the response
                        return response;
                    }
                })
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .addInterceptor(bodyLogging)
                .addInterceptor(headerLogging)
                .addInterceptor(headerLogging1)
                .addInterceptor(bodyLogging1)
                .readTimeout(2, TimeUnit.MINUTES)
                .build();

        return new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}