package com.example.agencycomposeapp.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LoginViewState(
    val loginValue: String = "",
    val passwordValue: String = "",
    val isSending: Boolean = false
)

sealed class LoginEvent {

    data class LoginValueChanged(val newValue: String) : LoginEvent()
    data class PasswordValueChanged(val newValue: String) : LoginEvent()
    object SendButtonClicked: LoginEvent()
}

class LoginViewModel: ViewModel() {
    private val _viewState = MutableStateFlow(LoginViewState())
    val viewState: StateFlow<LoginViewState> = _viewState

    fun obtainEvent (event: LoginEvent) {
        when(event) {
            is LoginEvent.LoginValueChanged -> obtainLoginInput(event.newValue)
            is LoginEvent.PasswordValueChanged -> obtainPasswordInput(event.newValue)
            LoginEvent.SendButtonClicked -> obtainSendClick()
        }

    }

    private fun obtainLoginInput(newValue: String) {
        _viewState.value = _viewState.value.copy(loginValue = newValue)
    }

    private fun obtainPasswordInput(newValue: String) {
        _viewState.value = _viewState.value.copy(passwordValue = newValue)
    }

    private fun obtainSendClick() {
        viewModelScope.launch {
            _viewState.value = _viewState.value.copy(isSending = true)
            delay(3000)
            _viewState.value = _viewState.value.copy(isSending = false)
        }
    }
}