package com.example.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokemon.databinding.ActivityMainBinding
import androidx.databinding.DataBindingUtil.setContentView
import dagger.hilt.android.AndroidEntryPoint


/**
 * Creates a [MainActivity]
 * Annotation because they need to be injected themselves
 * since we need the viewmodel in the fragment and the fragment inside the activity
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
}