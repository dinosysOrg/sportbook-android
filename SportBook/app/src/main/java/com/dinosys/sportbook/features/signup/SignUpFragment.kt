package com.dinosys.sportbook.features.signup

import android.content.Intent
import android.util.Log
import com.dinosys.sportbook.R
import com.dinosys.sportbook.application.SportbookApp
import com.dinosys.sportbook.exceptions.SignUpWithFailureException
import com.dinosys.sportbook.extensions.addDisposableTo
import com.dinosys.sportbook.extensions.appContext
import com.dinosys.sportbook.extensions.openScreenByTag
import com.dinosys.sportbook.features.BaseFragment
import com.dinosys.sportbook.features.signin.SignInFragment
import com.dinosys.sportbook.networks.models.AuthModel
import com.dinosys.sportbook.utils.LogUtil
import com.dinosys.sportbook.utils.ToastUtil
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_sign_up.*
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class SignUpFragment : BaseFragment() {

    override fun inflateFromLayout() = R.layout.fragment_sign_up

    @Inject
    lateinit var signUpApi: SignUpViewModel

    private var mCallbackManager: CallbackManager? = null

    override fun initData() {
        SportbookApp.authComponent.inject(this)
        initFacebookLoginConfig()
    }


    override fun initListeners() {
        super.initListeners()
        val btnSignUpDisposable = RxView.clicks(btnSignUp)
                .subscribeOn(AndroidSchedulers.mainThread())
                .switchMap {
                    val name = etName.text.toString()
                    val email = etEmail.text.toString()
                    val password = etPassword.text.toString()
                    val confirmPassword = etConfirmPassword.text.toString()
                    signUpApi.signUp(context, name, email, password, confirmPassword)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .onErrorResumeNext {
                                t:Throwable? -> onSignUpErrorResponse(t)
                            }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({response -> onSignUpDataResponse(response = response)})
                .addDisposableTo(this)
    }

    private fun onSignUpErrorResponse(t : Throwable?) : ObservableSource<Response<AuthModel>>? {
        ToastUtil.show(appContext, t?.message)
        return Observable.empty()
    }

    private fun onSignUpDataResponse(response: Response<AuthModel>) {
        val statusCode = response.code()
        when (statusCode) {
            in 200..300 -> {
                ToastUtil.show(appContext!!, getString(R.string.check_confirm_email_text))
                fragmentManager.openScreenByTag(SignInFragment.TAG)
            }
            422 -> {
                val responseText = response.errorBody()?.string()
                var errorMessage:String? = JSONObject(responseText)
                        ?.getJSONObject("errors")
                        ?.getJSONArray("full_messages")?.getString(0)

                if (errorMessage.isNullOrEmpty()) {
                   errorMessage = getString(R.string.sign_up_with_the_failure)
                }
                onSignUpErrorResponse(SignUpWithFailureException(errorMessage))
            }
            else -> {
                val errorMessage = getString(R.string.sign_up_with_the_failure)
                onSignUpErrorResponse(SignUpWithFailureException(errorMessage))
            }
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
                LogUtil.d(TAG, "[FacebookCallback][onCancel]")
            }

            override fun onError(exception: FacebookException) {
                LogUtil.e(TAG, "[FacebookCallback][onError]:" + exception.message)
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
        val TAG = "SignUpFragment"
    }

}

