package com.dinosys.sportbook.features.signin

import com.dinosys.sportbook.R
import com.dinosys.sportbook.application.SportbookApp
import com.dinosys.sportbook.extensions.appContext
import com.dinosys.sportbook.features.BaseFragment
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_forgot.*
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class ForgotFragment : BaseFragment() {

    @Inject
    lateinit var signInApi: SignInViewModel

    override fun inflateFromLayout() = R.layout.fragment_forgot

    override fun initData() {
        SportbookApp.authComponent.inject(this)
    }

    override fun initListeners() {
        val disposable = RxView.clicks(btnForgotPassword)
                .subscribeOn(AndroidSchedulers.mainThread())
                .switchMap {
                    e ->
                    val email = etEmail.text.toString()
                    signInApi.forgotPassword(appContext, email)
                            .onErrorResumeNext {
                        t: Throwable? -> onForgotErrorResponse(t?.message)
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{ response -> onForgotSuccessResponse(response) }
        addDisposable(disposable)
    }

    private fun onForgotErrorResponse(error: String?): ObservableSource<Response<JSONObject>> {
        return Observable.empty()
    }

    private fun onForgotSuccessResponse(response: Response<JSONObject>) {

    }

    companion object {
        val TAG = "ForgotFragment"
    }

}
