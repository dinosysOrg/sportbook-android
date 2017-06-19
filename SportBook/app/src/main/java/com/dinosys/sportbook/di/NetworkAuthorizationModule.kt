package com.dinosys.sportbook.di

import android.content.Context
import com.dinosys.sportbook.configs.REST_BASE_URL
import com.dinosys.sportbook.managers.AuthenticationManager
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
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

        val authUser = AuthenticationManager.getUser(context)
        val header = authUser?.header!!
        httpClient.addInterceptor(object : Interceptor {

            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()

                val requestBuilder = original.newBuilder()
                        .addHeader("Access-Token", header.get("Access-Token"))
                        .addHeader("Client", header.get("Client"))
                        .addHeader("Expiry", header.get("Expiry"))
                        .addHeader("Token-Type", header.get("Token-Type"))
                        .addHeader("Uid",  header.get("Uid"))

                val request = requestBuilder.build()
                return chain.proceed(request)
            }
        })

        val client = httpClient.build()
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(REST_BASE_URL)
                .build()
    }

}