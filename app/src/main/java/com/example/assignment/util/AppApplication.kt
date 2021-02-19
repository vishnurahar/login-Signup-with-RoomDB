package com.example.assignment.util

import android.content.Context
import androidx.multidex.MultiDexApplication

class AppApplication : MultiDexApplication() {


    companion object {
        lateinit var mContext: Context

        fun getContext(): Context {
            return mContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this


    }

    override fun attachBaseContext(base: Context?) {
        PreferenceUtil.init(base)
        super.attachBaseContext(base)
    }
}