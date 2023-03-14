package amir.flickr.data.di

import amir.flickr.data.api.RestAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Named("BASE_URL")
    fun providesBaseUrl(): String {
        return RestAPI.BASE_API_URL
    }

    @Provides
    @Singleton
    fun provideLogInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }

    @Provides
    @Singleton
    fun provideAuthInterceptorOkHttpClient(
        logInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            //if (BuildConfig.DEBUG) {
            addInterceptor(logInterceptor).retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).protocols(Collections.singletonList(Protocol.HTTP_1_1))
            //}
        }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient, @Named("BASE_URL") apiBaseURL: String
    ): Retrofit {
        return Retrofit.Builder().client(okHttpClient).addConverterFactory(GsonConverterFactory.create())
            .baseUrl(apiBaseURL).build()
    }

    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit
    ): RestAPI {
        return retrofit.create(RestAPI::class.java)
    }
}