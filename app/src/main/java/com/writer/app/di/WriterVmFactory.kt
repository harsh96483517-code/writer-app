package com.writer.app.di

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras

/** Builds ViewModels with access to the [AppContainer] and nav arguments. */
class WriterVmFactory(
    private val container: AppContainer,
    private val builder: (AppContainer, SavedStateHandle) -> ViewModel
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        val handle = extras.createSavedStateHandle()
        return builder(container, handle) as T
    }
}
