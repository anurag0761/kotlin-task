package com.logidtic.blueaid.utility

import android.content.Context
import android.preference.PreferenceManager

@Suppress("UNCHECKED_CAST")
class PreferenceHandler(context: Context) {
    private val sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)

    /**
     * Put preference based on the type of the data.
     *
     * @param key : Key of the preference.
     * @param data : data with "Any" type.
     */
    fun set(key: String, data: Any?) {
        if(data is Int) sharedPreference.edit().putInt(key, data).apply()
        if(data is Long) sharedPreference.edit().putLong(key, data).apply()
        if(data is Float) sharedPreference.edit().putFloat(key, data).apply()
        if(data is String) sharedPreference.edit().putString(key, data).apply()
        if(data is Boolean) sharedPreference.edit().putBoolean(key, data).apply()
    }

    /**
     * Delete preference based on the key.
     *
     * @param key : Key of the preference.
     */
    fun delete(key: String) {
        sharedPreference.edit().remove(key).apply()
    }

    /**
     * Get int type preference.
     *
     * @param key : Key of the preference.
     * @param default : Default value of the preference (Default : -1).
     */
    fun getInt(key: String, default: Int = -1) = sharedPreference.getInt(key, default)

    /**
     * Get long type preference.
     *
     * @param key : Key of the preference.
     * @param default : Default value of the preference (Default : -1).
     */
    fun getLong(key: String, default: Long = -1) = sharedPreference.getLong(key, default)

    /**
     * Get float type preference.
     *
     * @param key : Key of the preference.
     * @param default : Default value of the preference (Default : -1F).
     */
    fun getFloat(key: String, default: Float = -1F) = sharedPreference.getFloat(key, default)

    /**
     * Get string type preference.
     *
     * @param key : Key of the preference.
     * @param default : Default value of the preference (Default : null).
     */
    fun getString(key: String, default: String? = null) = sharedPreference.getString(key, default)

    /**
     * Get boolean type preference.
     *
     * @param key : Key of the preference.
     * @param default : Default value of the preference (Default : false).
     */
    fun getBoolean(key: String, default: Boolean = false) = sharedPreference.getBoolean(key, default)
}