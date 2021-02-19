package com.example.assignment.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel:ViewModel() {

    var logoutClicked:MutableLiveData<Boolean> = MutableLiveData()

    fun onLogoutClicked(){
        logoutClicked.value = true
    }
}