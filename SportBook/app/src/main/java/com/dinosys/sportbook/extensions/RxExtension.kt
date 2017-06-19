package com.dinosys.sportbook.extensions

import com.dinosys.sportbook.features.BaseFragment
import io.reactivex.disposables.Disposable

fun Disposable.addToFragment(baseFragment: BaseFragment) {
    baseFragment.addDisposable(this)
}
