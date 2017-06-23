package com.dinosys.sportbook.features.signin

import android.content.Intent
import android.graphics.Paint
import com.dinosys.sportbook.MainActivity
import com.dinosys.sportbook.R
import com.dinosys.sportbook.application.SportbookApp
import com.dinosys.sportbook.configs.PLATFORM_ANDROID_VALUE
import com.dinosys.sportbook.extensions.addDisposableTo
import com.dinosys.sportbook.extensions.appContext
import com.dinosys.sportbook.extensions.openScreenByTag
import com.dinosys.sportbook.extensions.remove
import com.dinosys.sportbook.features.BaseFragment
import com.dinosys.sportbook.features.signup.SignUpFragment
import com.dinosys.sportbook.managers.AuthenticationManager
import com.dinosys.sportbook.networks.models.AuthModel
import com.dinosys.sportbook.utils.LogUtil
import com.dinosys.sportbook.utils.ToastUtil
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.firebase.iid.FirebaseInstanceId
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_sign_in.*
import retrofit2.Response
import javax.inject.Inject


class SignInFragment : BaseFragment() {

    override fun inflateFromLayout(): Int = R.layout.fragment_sign_in

    @Inject
    lateinit var signInApi: SignInViewModel

    private var mCallbackManager: CallbackManager? = null

    override fun initViews() {
        tvForgotPassword.setPaintFlags(tvForgotPassword.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
    }

    override fun initData() {
        SportbookApp.authComponent.inject(this)
        initFacebookLoginConfig()
    }

    override fun initListeners() {
        RxView.clicks(btnSignIn)
                .subscribeOn(AndroidSchedulers.mainThread())
                .switchMap {
                    val userName = etUsername.text.toString()
                    val password = etPassword.text.toString()
                    signInApi.signIn(appContext,userName, password)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .onErrorResumeNext {
                                t: Throwable? -> onSignInErrorResponse(t?.message)
                            }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response -> onSignInDataResponse(response = response) })
                .addDisposableTo(this)

        tvForgotPassword.setOnClickListener { fragmentManager.openScreenByTag(ForgotFragment.TAG) }
        btnCreateAnAccount.setOnClickListener { fragmentManager.openScreenByTag(SignUpFragment.TAG) }
    }

    fun onSignInErrorResponse(textError : String?) : ObservableSource<Response<AuthModel>>? {
        ToastUtil.show(appContext, textError)
        return Observable.empty()
    }

    fun onSignInDataResponse(response: Response<AuthModel>) {
        val statusCode = response.code()
        when (statusCode) {
            in 200..300 -> {
                val signIn = response.body()
                signIn?.header = response.headers()

                if (appContext != null && signIn != null) {
                    AuthenticationManager.saveAuthenticationInfo(appContext!!, signIn)

                    sendTokenToServerAfterSignIn(signIn.data?.id!!)
                    loadTournamentPage()
                }
            }
            else -> onSignInErrorResponse(getString(R.string.error_login_failure_text))
        }
    }

    private fun sendTokenToServerAfterSignIn(userId: Int) {
        signInApi.sendTokenToServer(userId,
                FirebaseInstanceId.getInstance().token,
                PLATFORM_ANDROID_VALUE)
                .subscribeOn(Schedulers.newThread())
                .subscribe({
                    LogUtil.d(TAG, "store stoken with successfully!")
                }, {
                    LogUtil.d(TAG, "store stoken with failure!")
                })
    }

    private fun loadTournamentPage() {
        (activity as MainActivity).loadTabContentDefaultSelected()
        fragmentManager.remove(this)
    }

    private fun initFacebookLoginConfig() {
        btnFacebookLogin!!.setReadPermissions("email")
        btnFacebookLogin!!.fragment = this
        mCallbackManager = CallbackManager.Factory.create()
        val disposable = Observable.create<LoginResult> { e ->
            btnFacebookLogin!!.registerCallback(mCallbackManager,
                    object : FacebookCallback<LoginResult> {
                        override fun onCancel() = e.onComplete()
                        override fun onSuccess(result: LoginResult?) = e.onNext(result)
                        override fun onError(error: FacebookException?) = e.onError(error)
                    })
        }.subscribeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    e ->
                    LogUtil.e(TAG, e.accessToken.token)
                    signInApi.signInWithFacebook(e.accessToken.token)
                            .subscribeOn(Schedulers.newThread())
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ e -> LogUtil.d(TAG, e.toString()) })
        addDisposable(disposable)
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
