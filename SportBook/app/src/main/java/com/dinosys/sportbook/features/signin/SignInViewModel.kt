package com.dinosys.sportbook.features.signin

import com.dinosys.sportbook.networks.authentication.AuthenticationAPI
import com.dinosys.sportbook.networks.models.SignInModel
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SignInViewModel @Inject constructor(val authApi : AuthenticationAPI) {

    open fun signIn(userName:String?, password:String?) :Observable<Response<SignInModel>> {
        if (userName.isNullOrEmpty()) {
            return Observable.error(Throwable("Username is null or empty"))
        }
        if (password.isNullOrEmpty()) {
            return Observable.error(Throwable("Password is null or empty"))
        }
        return authApi.signIn(userName!!, password!!)
    }
}
