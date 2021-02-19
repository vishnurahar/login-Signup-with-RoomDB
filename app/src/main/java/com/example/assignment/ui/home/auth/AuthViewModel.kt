package com.example.assignment.ui.home.auth

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment.db.user.User
import com.example.assignment.db.user.UserDatabase
import com.example.assignment.db.user.UserRepository

class AuthViewModel(context: Context) : ViewModel() {
    var signUpPage : MutableLiveData<Boolean> = MutableLiveData(true)
    var ctaClicked : MutableLiveData<Boolean> = MutableLiveData()

    private val userRepository: UserRepository

    init {
        userRepository = UserRepository.getInstance(UserDatabase.getAppDatabase(context).userDao())
    }

    internal fun createUser(email: String, password: String,name:String) {
        userRepository.insertUser(email, password,name)
    }

    internal fun checkValidLogin(email: String, password: String): Boolean {
        return userRepository.isValidAccount(email, password)
    }

    internal fun getLoggedInUser(email: String): User {
        return userRepository.getLoggedInUser(email)
    }



    fun onSubActionClicked(){
        signUpPage.value = !signUpPage.value!!
    }

    fun onActionClicked(){
        ctaClicked.value = true
    }

    class Factory internal constructor(context: Context) : ViewModelProvider.Factory {
        private val context: Context

        init {
            this.context = context.applicationContext
        }

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AuthViewModel(context) as T
        }
    }
}