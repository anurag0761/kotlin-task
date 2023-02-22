package com.example.freecart

import android.app.Application
import com.example.freecart.ApiRepo.ApiRepo
import com.example.freecart.handler.ApiViewModelFactory
import com.logidtic.blueaid.utility.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class Freecart: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        // Common task handler classes
        bind() from singleton { CoroutineHandler(this@Freecart) }
        bind() from singleton { PreferenceHandler(this@Freecart) }

        bind() from singleton { ApiRepo(instance(), instance()) }

        bind() from singleton { InterceptorManager(this@Freecart) }
        bind() from singleton { ApiViewModelFactory(instance(),instance(),instance()) }

    }
}