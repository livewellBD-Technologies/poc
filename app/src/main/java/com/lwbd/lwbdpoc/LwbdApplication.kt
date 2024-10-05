package com.lwbd.lwbdpoc

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import dagger.hilt.android.HiltAndroidApp

import javax.inject.Inject


/**
 * [Application] class for Lwbd
 */
@HiltAndroidApp
class LwbdApplication : Application(), ImageLoaderFactory {
    @Inject
    lateinit var imageLoader: dagger.Lazy<ImageLoader>

    override fun onCreate() {
        super.onCreate()
    }

    override fun newImageLoader(): ImageLoader = imageLoader.get()
}
