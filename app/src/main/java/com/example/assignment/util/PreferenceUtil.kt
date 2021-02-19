package com.example.assignment.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import java.util.*
import java.util.concurrent.ConcurrentHashMap

object PreferenceUtil {
    private var sharedPreferences: SharedPreferences? = null
    private var preferenceUtil: PreferenceUtil? = null


    fun init(appContext: Context?) {
        sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(appContext)
        preferenceUtil = this
    }


    fun <T> getPreference(key: String?, defaultValue: T): T? {
        try {
            if (defaultValue is String) {
                return sharedPreferences!!.getString(
                    key,
                    defaultValue as String
                ) as T?
            } else if (defaultValue is Int) {
                return sharedPreferences!!.getInt(
                    key,
                    (defaultValue as Int)
                ) as T
            } else if (defaultValue is Boolean) {
                return sharedPreferences!!.getBoolean(
                    key,
                    (defaultValue as Boolean)
                ) as T
            } else if (defaultValue is Float) {
                return sharedPreferences!!.getFloat(
                    key,
                    (defaultValue as Float)
                ) as T
            } else if (defaultValue is Long) {
                return sharedPreferences!!.getLong(
                    key,
                    (defaultValue as Long)
                ) as T
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }


    fun <T> setPreference(key: String?, value: T?) {
        try {
            val editor = sharedPreferences!!.edit()
            if (value is String) {
                editor.putString(key, value as String)
            } else if (value is Int) {
                editor.putInt(key, (value as Int))
            } else if (value is Boolean) {
                editor.putBoolean(key, (value as Boolean))
            } else if (value is Float) {
                editor.putFloat(key, (value as Float))
            } else if (value is Long) {
                editor.putLong(key, (value as Long))
            }
            editor.apply()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun clearAllPreferences() {
        if (sharedPreferences != null) sharedPreferences!!.edit()
            .clear().apply()
    }

}