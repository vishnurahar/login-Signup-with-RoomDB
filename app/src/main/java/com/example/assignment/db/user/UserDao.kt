package com.example.assignment.db.user

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM user WHERE user.email LIKE :email")
    fun getAccount(email: String): User?
}