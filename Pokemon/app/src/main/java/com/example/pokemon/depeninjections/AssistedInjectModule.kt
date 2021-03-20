package com.example.pokemon.depeninjections

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

// AssistedInject puts all assisted bindings in the same module.
// We need to make a decision about where to install it.
// In this case, as we only need it in fragments, we install it there.
// source: https://gist.github.com/manuelvicnt/437668cda3a891d347e134b1de29aee1
/**
 * This interface is necessary for Hilt to work
 * to avoid errors on @Provides in AssistedInject.Factory
 *
 * Needed until AssistedInject is incorporated into Dagger
 */
@InstallIn(FragmentComponent::class)
@AssistedModule
@Module(includes = [AssistedInject_AssistedInjectModule::class])
interface AssistedInjectModule