package com.betrybe.trybnb.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.betrybe.trybnb.common.ApiIdlingResource
import com.betrybe.trybnb.data.models.LoginRequest
import com.betrybe.trybnb.data.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val mLoginRepository = LoginRepository()

    private var _isLoginError = MutableStateFlow(false)
    val isLoginError: StateFlow<Boolean>
        get() = _isLoginError

    private var _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun getToken(request: LoginRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            ApiIdlingResource.increment()
            val response = mLoginRepository.login(request)
            if (response.success) {
                val token = response.data?.token
                Log.d("ProfileViewModel", "Token: $token")
                _isLoginError.value = false
            } else {
                _errorMessage.postValue(response.message)
                _isLoginError.value = true
            }
            ApiIdlingResource.decrement()
        }
    }
}