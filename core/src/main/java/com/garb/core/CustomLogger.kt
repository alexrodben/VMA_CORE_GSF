package com.garb.core

import android.text.TextUtils
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.logging.Level

/**
 * Clase helper para manejo de Logger globales.
 *
 * CustomLogger.init(baseDir = getExternalFilesDir(null)!!, folder = "driver")
 */

object CustomLogger {
    private var warned = false
    private var baseDir: File? = null
    private var subFolder: String = "driver"

    fun init(baseDir: File, folder: String? = null) {
        this.subFolder = folder ?: "globlal"
        this.baseDir = baseDir
    }


    fun info(message: String) {
        val stackTrace = Thread.currentThread().getStackTrace()[3]
        val f = "${stackTrace.fileName}:${stackTrace.lineNumber}.${stackTrace.methodName}"
        log(stackTrace.className, Level.INFO, f, message, null, null)
    }

    fun debug(message: String) {
        val stackTrace = Thread.currentThread().getStackTrace()[3]
        val f = "${stackTrace.fileName}:${stackTrace.lineNumber}.${stackTrace.methodName}"
        log(stackTrace.className, Level.FINE, f, message, null, null)
    }

    fun warn(message: String) {
        val stackTrace = Thread.currentThread().getStackTrace()[3]
        val f = "${stackTrace.fileName}:${stackTrace.lineNumber}.${stackTrace.methodName}"
        log(stackTrace.className, Level.WARNING, f, message, null, null)
    }

    fun error(message: String, throwable: Throwable) {
        val stackTrace = Thread.currentThread().getStackTrace()[3]
        val f = "${stackTrace.fileName}:${stackTrace.lineNumber}.${stackTrace.methodName}"
        log(stackTrace.className, Level.SEVERE, f, message, throwable, "error")
    }

    private fun log(
        tag: String,
        level: Level,
        function: String,
        message: String,
        throwable: Throwable?,
        fileCustomName: String?
    ) {
        if (TextUtils.isEmpty(message)) return
        val formattedMessage = formatLogMessage(function, message, throwable)
        logToAndroid(level, tag, formattedMessage)
        if (baseDir == null) {
            Log.e("CustomLogger", "CustomLogger.init(...) NO fue llamado")
            return
        }
        val formattedCompleteMessage: String = formatCompleteLogMessage(tag, level, formattedMessage)
        if (level != Level.OFF) writeToFile(formattedCompleteMessage, null)
        if (fileCustomName != null) writeToFile(formattedCompleteMessage, fileCustomName)
    }

    private fun writeToFile(message: String, fileCustomName: String?) {
        // Obtener la fecha y hora actual
        val now = Date()
        val sdfTime = SimpleDateFormat("HH", Locale.getDefault())
        val timeString = sdfTime.format(now)
        // Crear el archivo del log de la hora si no existe
        val folderPath: String = createFolder(now, fileCustomName)
        val filePath = "$folderPath/${fileCustomName ?: "app"}-$timeString.log"
        writeFile(filePath, message)
    }

    private fun getBaseDirOrNull(): File? {
        if (baseDir == null && !warned) {
            warned = true
            Log.w("CustomLogger", "CustomLogger no inicializado. Llamar CustomLogger.init(...) en Application")
        }
        return baseDir
    }

    private fun createFolder(now: Date, fileCustomName: String?): String {
        val sdfDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateString = sdfDate.format(now)
        val base = getBaseDirOrNull() ?: return ""
        var folder = File(base, "vending/$subFolder/logs/$dateString")
        if (fileCustomName != null) folder = File(folder, fileCustomName)
        if (!folder.exists()) folder.mkdirs()
        return folder.path
    }

    private fun writeFile(filePath: String, message: String) {
        val file = File(filePath)
        try {
            if (!file.exists()) {
                if (file.createNewFile()) println("Logger info: -writeToFile- Archivo creado: $filePath")
            }
            OutputStreamWriter(FileOutputStream(file, true), StandardCharsets.UTF_8).use { writer ->
                writer.write(message)
            }
        } catch (e: IOException) {
            System.err.println("Logger error: -writeToFile- " + e.message)
        }
    }

    private fun formatCompleteLogMessage(tag: String, level: Level, message: String): String {
        val now = Date()
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
        val timestamp = sdf.format(now)
        return timestamp + " - [" + level.name + "]" + " *" + tag + "* " + message
    }

    private fun formatLogMessage(function: String, message: String, throwable: Throwable?): String {
        val sb = kotlin.text.StringBuilder().append(function).append(": ").append(message).append("\n")
        if (throwable != null) sb.append(Log.getStackTraceString(throwable))
        return sb.toString()
    }

    private fun logToAndroid(level: Level, tag: String, message: String) {
        when (level) {
            Level.WARNING -> Log.w(tag, message)
            Level.SEVERE -> Log.e(tag, message)
            Level.INFO -> Log.i(tag, message)
            Level.FINE -> Log.d(tag, message)
            else -> Log.d(tag, message)
        }
    }
}