package com.dinosys.sportbook.networks.authentication

import com.dinosys.sportbook.networks.models.SignInModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthenticationAPI {
    fun singOut(): Observable<*>


    @FormUrlEncoded
    @POST("auth/sign_in")
    fun signIn(@Field("email")email:String, @Field("password") password:String): Observable<Response<SignInModel>>

    fun signUp(email:String, password:String): Observable<*>
}