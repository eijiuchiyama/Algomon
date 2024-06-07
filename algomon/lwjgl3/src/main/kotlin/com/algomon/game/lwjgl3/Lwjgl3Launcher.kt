@file:JvmName("Lwjgl3Launcher")

package com.algomon.game.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.algomon.game.Main

/** Launches the desktop (LWJGL3) application. */
fun main() {
    // This handles macOS support and helps on Windows.
    if (StartupHelper.startNewJvmIfRequired())
      return
    Lwjgl3Application(Main(), Lwjgl3ApplicationConfiguration().apply {
        setTitle("Algomon")
        setWindowedMode(640, 480)
        setWindowIcon(*(arrayOf(128, 64, 32, 16).map { "icon$it.png" }.toTypedArray()))
    })
}
