package com.dinosys.sportbook.networks.authentication

import com.dinosys.sportbook.networks.models.SignInModel
import io.reactivex.Observable
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.*

interface AuthenticationAPI {

    @DELETE
    fun singOut(@Header("Access-Token") accessToken: String?,
                @Header("Client") client: String?,
                @Header("Expiry") expiry: String?,
                @Header("Token-Type") tokenType: String?,
                @Header("Uid") Uid: String?): Observable<Response<JSONObject>>

    @FormUrlEncoded
    @POST("auth/sign_in")
    fun signIn(@Field("email")email:String, @Field("password") password:String): Observable<Response<SignInModel>>

    fun signUp(email:String, password:String): Observable<*>
}