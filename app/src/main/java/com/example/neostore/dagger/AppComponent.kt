package com.example.neostore.dagger

import com.example.neostore.activity.login.LoginActivity
import dagger.Component


@Component(modules = [AppModule::class])
interface AppComponent {

    //lateinit var wikiComponent: AppComponent
    fun inject(target: LoginActivity)

}