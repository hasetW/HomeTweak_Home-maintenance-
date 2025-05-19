package com.example.myapplication.data.model



import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_TOKEN = "jwt_token"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_ROLE = "user_role"
    }

    fun saveAuthToken(token: String) {
        prefs.edit().putString(KEY_TOKEN, token).apply()
    }

    fun saveUserId(userId: Int) {
        prefs.edit().putInt(KEY_USER_ID, userId).apply()
    }

    fun saveUserRole(role: String) {
        prefs.edit().putString(KEY_ROLE, role).apply()
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(KEY_TOKEN, null)
    }

    fun fetchUserId(): Int {
        return prefs.getInt(KEY_USER_ID, -1)
    }

    fun fetchUserRole(): String? {
        return prefs.getString(KEY_ROLE, null)
    }

    fun clearSession() {
        prefs.edit().clear().apply()
    }
}
