package com.example.assignment.db.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User(@PrimaryKey val email: String, val password: String, val name: String)