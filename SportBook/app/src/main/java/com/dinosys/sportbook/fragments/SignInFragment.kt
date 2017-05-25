package com.dinosys.sportbook.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dinosys.sportbook.R
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : Fragment() {

    internal var mCallbackManager: CallbackManager? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFacebookLoginConfig()
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
