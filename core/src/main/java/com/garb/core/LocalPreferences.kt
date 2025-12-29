package com.garb.core

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

/**
 * Clase helper para manejo de SharedPreferences globales.
 * Usa el APPLICATION_ID como nombre de archivo para evitar conflictos.
 * Implementa SharedPreferences delegando la mayoría de métodos al prefs real.
 *
 * LocalPreferences.init(this)
 */
object LocalPreferences {
    private lateinit var prefs: SharedPreferences

    fun init(context: Context, name: String? = null) {
        prefs = context.applicationContext.getSharedPreferences(
            name ?: (context.applicationContext.packageName + "_preferences"),
            Context.MODE_PRIVATE
        )
    }

    // ---- Strings ----
    fun getString(key: PreferenceKey): String = prefs.getString(key.keyName, null) ?: "UNKNOWN"
    fun putString(key: PreferenceKey, value: String?) = prefs.edit { putString(key.keyName, value) }

    // ---- Booleans ----
    fun getBoolean(key: PreferenceKey): Boolean = prefs.getBoolean(key.keyName, false)
    fun putBoolean(key: PreferenceKey, value: Boolean) = prefs.edit { putBoolean(key.keyName, value) }

    fun getAll(): MutableMap<String, *> = prefs.all
    fun registerChangeListener(preferenceChangeListener: SharedPreferences.OnSharedPreferenceChangeListener) {
        prefs.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
    }

    fun unregisterChangeListener(preferenceChangeListener: SharedPreferences.OnSharedPreferenceChangeListener) {
        prefs.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener)
    }

    fun changeListener(key: PreferenceKey, accion: (String) -> Unit): SharedPreferences.OnSharedPreferenceChangeListener {
        return SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, k ->
            if (key.keyName == k) accion(k)
        }
    }
}