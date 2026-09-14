package com.example.groceryapp.presentation.registration

sealed class RegistrationEvent {
    object NavigateToHome : RegistrationEvent()
    object NavigateToRegistration1 : RegistrationEvent()
    object NavigateToRegistration2 : RegistrationEvent()
    object NavigateToRegistration3 : RegistrationEvent()
    data class ShowMessage(val message: String) : RegistrationEvent()
}