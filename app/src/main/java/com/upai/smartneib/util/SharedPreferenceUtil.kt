package com.upai.smartneib.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceUtil {

    companion object {

        fun saveUserAndPass(context: Context, user: String, pass: String) {
            val sp: SharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putString("username", user)
            editor.putString("password", pass)
            editor.apply()
        }

        fun getUser(context: Context): String? {
            val sp: SharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
            return sp.getString("username", null)
        }

        fun getPass(context: Context): String? {
            val sp: SharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
            return sp.getString("password", null)
        }
    }

}