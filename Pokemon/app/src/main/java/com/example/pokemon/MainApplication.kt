package com.example.pokemon

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Every app that uses Hilt needs one main application class
 * The annotation will trigger the codegeneration that this library needs
 */
@HiltAndroidApp
class MainApplication : Application(){

}