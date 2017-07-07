package com.dinosys.sportbook.extensions

import com.dinosys.sportbook.features.BaseFragment
import io.reactivex.disposables.Disposable

fun Disposable.addDisposableTo(baseFragment: BaseFragment) {
    baseFragment.addDisposable(this)
}
