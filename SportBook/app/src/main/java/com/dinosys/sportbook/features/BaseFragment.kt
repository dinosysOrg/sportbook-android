package com.dinosys.sportbook.features

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseFragment : Fragment() {

    private val composite = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(inflateFromLayout(), container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        initData()
    }

    abstract fun inflateFromLayout() : Int

    override fun onDestroy() {
        super.onDestroy()
        composite.clear()
    }

    fun addDisposable(disposable: Disposable) {
        composite.add(disposable)
    }

    open fun initViews() {

    }

    open fun initListeners() {

    }

    open fun initData() {

    }

}