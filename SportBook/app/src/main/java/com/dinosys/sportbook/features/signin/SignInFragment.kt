package com.dinosys.sportbook.features.signin


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.dinosys.sportbook.R
import com.dinosys.sportbook.application.SportbookApp
import com.dinosys.sportbook.features.BaseFragment
import com.dinosys.sportbook.networks.models.SignInModel
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_sign_in.*
import retrofit2.Response
import javax.inject.Inject

class SignInFragment : BaseFragment() {

    override fun inflateFromLayout(): Int = R.layout.fragment_sign_in

    @Inject
    lateinit var signInApi: SignInViewModel

    private var mCallbackManager: CallbackManager? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SportbookApp.authComponent.inject(this)
        initFacebookLoginConfig()
        initListeners()
    }

    override fun initListeners() {
        val btnSignInDisposable = RxView.clicks(btnSignIn)
                .subscribeOn(AndroidSchedulers.mainThread())
                .switchMap {
                    val userName = etUsername.text.toString()
                    val password = etPassword.text.toString()
                    signInApi.signIn(userName, password)
                            .onErrorResumeNext {
                                t: Throwable? -> onSignInErrorRespone()
                            }
                            .subscribeOn(Schedulers.newThread())
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response -> onSignInDataResponse(response = response) })

        addDisposable(btnSignInDisposable)
    }

    fun onSignInErrorRespone() : ObservableSource<Response<SignInModel>>? {
        Log.e(TAG, "onSignInErrorRespone")
        return Observable.empty()
    }

    fun onSignInDataResponse(response: Response<SignInModel>) {
        val error = response.body()?.errors
        when (error) {
            null -> {
                val signIn = response.body()
                signIn?.header =  response.headers()
            }
            else -> onSignInErrorRespone()
        }
    }

    private fun initFacebookLoginConfig() {
        btnFacebookLogin!!.setReadPermissions("email")
        btnFacebookLogin!!.fragment = this
        mCallbackManager = CallbackManager.Factory.create()
        btnFacebookLogin!!.registerCallback(mCallbackManager, createFacebookcallback())
    }

    private fun createFacebookcallback(): FacebookCallback<LoginResult> {
        return object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {

            }

            override fun onCancel() {
                Log.v(TAG, "[FacebookCallback][onCancel]")
            }

            override fun onError(exception: FacebookException) {
                Log.e(TAG, "[FacebookCallback][onError]:" + exception.message)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (mCallbackManager != null) {
            mCallbackManager!!.onActivityResult(requestCode, resultCode, data)
        }
    }

    companion object {
        val TAG = "LoginFragment"
    }
}

private val  <E> List<E>.isNotNullAndEmpty: Boolean get() = if (this == null || this.isEmpty()) true else false
