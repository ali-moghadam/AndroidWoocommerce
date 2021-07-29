package com.alirnp.androidwoocommerceapp.di

import com.alirnp.androidwoocommerceapp.Config
import com.alirnp.androidwoocommerceapp.core.woocomere.AuthInterceptor
import com.alirnp.androidwoocommerceapp.repository.api.CategoryAPI
import com.alirnp.androidwoocommerceapp.repository.api.ProductAPI
import com.alirnp.androidwoocommerceapp.repository.api.UserApi
import com.alirnp.androidwoocommerceapp.repository.networkBoundResource.LiveDataCallAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
       return GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { logger ->
            Timber.i("retrofit -> $logger")
        }.also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(loggingInterceptor : HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(Config.CONSUMER_KEY, Config.CONSUMER_SECRET))
            .addInterceptor(loggingInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @WooCommerceRetrofit
    fun provideWoocommerceRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Config.woocommerceApiUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            // TODO: 5/29/2021 remove  RxJava2CallAdapterFactory
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @WordpressRetrofit
    fun provideWordpressRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Config.wordpressUserApiUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            // TODO: 5/29/2021 remove  RxJava2CallAdapterFactory
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideProductAPI(@WooCommerceRetrofit retrofit: Retrofit): ProductAPI {
        return retrofit.create(ProductAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoryAPI(@WooCommerceRetrofit retrofit: Retrofit): CategoryAPI {
        return retrofit.create(CategoryAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideUserApi(@WordpressRetrofit retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }
}