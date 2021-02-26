package com.shesha.kotlinprojects.koindependencyapp.di.module

import com.shesha.kotlinprojects.koindependencyapp.data.repository.MainRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepository(get())
    }
}