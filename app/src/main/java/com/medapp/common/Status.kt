package com.medapp.common

sealed interface Status {

    data class LogIn(val username: String) : Status
    object LogOut : Status

}