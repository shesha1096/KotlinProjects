package com.shesha.kotlinprojects.koindependencyapp.di.module

import com.shesha.kotlinprojects.koindependencyapp.main.viewmodel.MainViewModel
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel {
        MainViewModel(get(),get())
    }
}