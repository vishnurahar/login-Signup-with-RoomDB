package com.example.assignment.util

object PreferenceManager {

    var isLoggedIn : Boolean
        get() = PreferenceUtil.getPreference(PreferenceConstant.PRE_LOGGED_IN,false)!!
        set(isLoggedIn) = PreferenceUtil.setPreference(PreferenceConstant.PRE_LOGGED_IN,isLoggedIn)

    var email : String
        get() = PreferenceUtil.getPreference(PreferenceConstant.PRE_EMAIL,"")!!
        set(newemail) = PreferenceUtil.setPreference(PreferenceConstant.PRE_EMAIL,newemail)

    var name : String
        get() = PreferenceUtil.getPreference(PreferenceConstant.PRE_NAME,"")!!
        set(newname) = PreferenceUtil.setPreference(PreferenceConstant.PRE_NAME,newname)

    fun logOut(){
        PreferenceUtil.clearAllPreferences()
    }

}
