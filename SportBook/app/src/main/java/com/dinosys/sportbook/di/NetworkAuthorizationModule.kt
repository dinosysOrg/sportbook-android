package com.dinosys.sportbook.di

import android.content.Context
import com.dinosys.sportbook.BuildConfig
import com.dinosys.sportbook.configs.REST_BASE_URL
import com.dinosys.sportbook.managers.AuthenticationManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkAuthorizationModule {

    @Provides
    @Singleton
    fun provideRetrofit(context: Context) : Retrofit {
        val httpClient = OkHttpClient.Builder()

        val header = AuthenticationManager.getHeaderInfo(context)
        httpClient.addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                    .addHeader("Access-Token", header?.get("Access-Token"))
                    .addHeader("Client", header?.get("Client"))
                    .addHeader("Expiry", header?.get("Expiry"))
                    .addHeader("Token-Type", header?.get("Token-Type"))
                    .addHeader("Uid",  header?.get("Uid"))

            val request = requestBuilder.build()
            chain.proceed(request)
        }
        if (BuildConfig.DEBUG) {
            val headersLogging = HttpLoggingInterceptor()
            headersLogging.setLevel(HttpLoggingInterceptor.Level.HEADERS)

            val bodyLogging = HttpLoggingInterceptor()
            bodyLogging.setLevel(HttpLoggingInterceptor.Level.BODY)

            httpClient.addInterceptor(headersLogging)
            httpClient.addInterceptor(bodyLogging)
        }
        val client = httpClient.build()
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(REST_BASE_URL)
                .build()
    }

}