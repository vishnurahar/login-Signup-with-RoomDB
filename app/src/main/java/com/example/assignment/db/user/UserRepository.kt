package com.example.assignment.db.user

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class UserRepository private constructor(private val userDao: UserDao) {
    private val userLiveData: LiveData<User>? = null

    fun isValidAccount(email: String, password: String): Boolean {

        val user = userDao.getAccount(email)
        if(user!=null) return user.password == password

        return false
    }

    fun getLoggedInUser(email:String):User{
        return userDao.getAccount(email)!!
    }

    fun insertUser(email: String, password: String,name:String) {
        val account = User(email, password,name)
        userDao.insert(account)
    }

    companion object {
        private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao): UserRepository {
            if (instance == null) {
                instance = UserRepository(userDao)
            }
            return instance!!
        }
    }
}