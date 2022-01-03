package io.soniasieiro.stationsapp.utils
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.soniasieiro.stationsapp.ui.main.MainViewModel
import java.lang.IllegalArgumentException

class CustomViewModelFactory(private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(MainViewModel::class.java) -> MainViewModel(application)
                else -> throw IllegalArgumentException("Unknown ViewModel")
            }
        } as T
    }
}