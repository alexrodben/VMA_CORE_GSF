package com.garb.api.models

data class PluginModel(
    val idPlugin: Long = 0,
    val name: String? = null,
    val description: String? = null,
    val isOn: Boolean = false,
    val isRunning: Boolean = false,
    val hasSerial: Boolean = false,
    val isSerialUp: Boolean = false,
)