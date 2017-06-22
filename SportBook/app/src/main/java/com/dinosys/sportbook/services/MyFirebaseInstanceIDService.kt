package com.dinosys.sportbook.services

import com.dinosys.sportbook.configs.DEFAULT_USER_ID_UNKNOWN
import com.dinosys.sportbook.configs.PLATFORM_ANDROID_VALUE
import com.dinosys.sportbook.di.authentication.DaggerAuthenticationComponent
import com.dinosys.sportbook.managers.AuthenticationManager
import com.dinosys.sportbook.networks.authentication.AuthenticationAPI
import com.dinosys.sportbook.utils.LogUtil
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MyFirebaseInstanceIDService : FirebaseInstanceIdService() {

    @Inject
    lateinit var authenticationAPI: AuthenticationAPI

    var disposable: Disposable? = null

    override fun onTokenRefresh() {
        val refreshedToken = FirebaseInstanceId.getInstance().token
        val authUser = AuthenticationManager.getUser(this)
        var userId = authUser?.data?.id
        if (userId == null) {
            userId = DEFAULT_USER_ID_UNKNOWN
        }
        disposable = authenticationAPI.sendTokenToServer(userId = userId,
                token = refreshedToken,
                platform = PLATFORM_ANDROID_VALUE)
                .subscribeOn(Schedulers.newThread())
                .subscribe({
                    LogUtil.d("MyFirebaseInstanceIDService", "store stoken with successfully!")
                }, {
                    LogUtil.d("MyFirebaseInstanceIDService", "store stoken with failure!")
                })
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAuthenticationComponent.builder().build().inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

}
