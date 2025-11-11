package com.garb.core

import android.os.Environment
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
import kotlin.io.use

object CustomLogger {
    private const val DIRECTORY = "/vending/driver/logs/"

    fun info(message: String) {
        val stackTrace = Thread.currentThread().getStackTrace()[3]
        val f = "${stackTrace.fileName}:${stackTrace.lineNumber}.${stackTrace.methodName}"
        log(stackTrace.className, Level.INFO, f, message, null, null)
    }

    fun debug(message: String) {
        val stackTrace = Thread.currentThread().getStackTrace()[3]
        val f = "${stackTrace.fileName}:${stackTrace.lineNumber}.${stackTrace.methodName}"
        log(stackTrace.className, Level.OFF, f, message, null, null)
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

    private fun log(tag: String, level: Level, function: String, message: String, throwable: Throwable?, fileCustomName: String?) {
        if (TextUtils.isEmpty(message)) return
        val formattedMessage: String = formatLogMessage(tag, level, function, message, throwable)
        val formattedCompleteMessage: String = formatCompleteLogMessage(tag, level, formattedMessage)
        if (fileCustomName != null) writeToFile(formattedCompleteMessage, fileCustomName)
        if (level != Level.OFF) writeToFile(formattedCompleteMessage, null)
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

    private fun createFolder(now: Date, fileCustomName: String?): String {
        // Crear la carpeta del dia si no existe
        val sdfDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateString = sdfDate.format(now)
        val baseDir: File = try {
            Environment.getExternalStorageDirectory()
        } catch (e: Exception) {
            System.err.println("Logger error: -createFolder- " + e.message)
            File(System.getProperty("java.io.tmpdir") ?: "/tmp")
        }
        var folderPath = File(baseDir, DIRECTORY + dateString).path
        if (fileCustomName != null) folderPath += "/$fileCustomName"
        val folder = File(folderPath)
        if (!folder.exists()) {
            if (folder.mkdirs()) {
                println("Logger info: -writeToFile- Directorio creado: $folderPath")
            }
        }
        return folderPath
    }

    private fun writeFile(filePath: String, message: String) {
        val file = File(filePath)
        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    println("Logger info: -writeToFile- Archivo creado: $filePath")
                }
            }
            OutputStreamWriter(FileOutputStream(file, true), StandardCharsets.UTF_8).use { writer -> writer.write(message) }
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

    private fun formatLogMessage(tag: String, level: Level, function: String, message: String, throwable: Throwable?): String {
        val sb = kotlin.text.StringBuilder().append(function).append(": ").append(message).append("\n")
        if (throwable != null) sb.append(Log.getStackTraceString(throwable))
        logToAndroid(level, tag, sb.toString())
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