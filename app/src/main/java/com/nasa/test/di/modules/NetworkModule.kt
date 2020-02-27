package com.nasa.test.di.modules

import android.content.Context
import com.nasa.test.data.local.Constants
import com.nasa.test.data.network.CommonService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    private val TIMEOUT = 20

    @Provides
    @Singleton
    @Named("common-okHttpClient")
    fun provideIGolfHttpClient(cache: Cache): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor { chain ->
            val original = chain.request()

            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", Constants.Keys.NASA_API_KEY)
                .build()
            val requestBuilder = original.newBuilder()
                .url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }
        return builder
            .cache(cache)
            .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            )
            .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .build()
    }


    @Provides
    @Singleton
    @Named("common-retrofit")
    fun providesRetrofit(
        gConverterFactory: Converter.Factory, @Named("common-okHttpClient") client: OkHttpClient,
        rxAdapter: RxJava2CallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(Constants.URL.BASE_SERVER_URL)
            .addConverterFactory(gConverterFactory)
            .addCallAdapterFactory(rxAdapter)
            .build()
    }


    @Provides
    @Singleton
    fun provideIGolfApi(@Named("common-retrofit") retrofit: Retrofit): CommonService {
        return retrofit.create(CommonService::class.java)
    }

    @Provides
    @Singleton
    fun providesRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    @Named(NETWORK_CACHE)
    fun provideNetworkCacheDirectory(context: Context): File {
        return context.getDir(NETWORK_CACHE, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideNetworkCache(@Named(NETWORK_CACHE) cacheDir: File): Cache {
        val cacheSize = 20 * 1024 * 1024 // 20 MiB
        return Cache(cacheDir, cacheSize.toLong())
    }

    companion object {
        private const val NETWORK_CACHE = "network_cache"
    }
}