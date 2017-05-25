package com.dinosys.sportbook.networks.authentication

import com.dinosys.sportbook.networks.models.SignInModel
import io.reactivex.Observable

interface AuthenticationAPI {
    fun singOut(): Observable<*>
    fun signIn(email:String, password:String): Observable<SignInModel>
    fun signUp(email:String, password:String): Observable<*>
}