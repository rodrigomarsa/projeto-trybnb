package com.betrybe.trybnb.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    private val _loginError = MutableLiveData<String>()
    val loginError: LiveData<String> get() = _loginError

    private val _passwordError = MutableLiveData<String>()
    val passwordError: LiveData<String> get() = _passwordError

    fun validateFields(login: String, password: String): Boolean {
        var hasError = false

        if (login.isEmpty()) {
            _loginError.value = "O campo Login é obrigatório"
            hasError = true
        } else {
            _loginError.value = null
        }

        if (password.isEmpty()) {
            _passwordError.value = "O campo Password é obrigatório"
            hasError = true
        } else {
            _passwordError.value = null
        }

        return hasError
    }
}