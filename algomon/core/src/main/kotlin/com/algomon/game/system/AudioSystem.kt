package com.algomon.game.system

import com.algomon.game.event.EntityOpenEvent
import com.algomon.game.event.MapChangeEvent
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.scenes.scene2d.Event
import com.badlogic.gdx.scenes.scene2d.EventListener
import com.github.quillraven.fleks.IntervalSystem
import ktx.assets.disposeSafely
import ktx.log.logger
import ktx.tiled.propertyOrNull

class AudioSystem : EventListener, IntervalSystem() {
    private val soundCache = mutableMapOf<String, Sound>()
    private val musicCache = mutableMapOf<String, Music>()
    private val soundRequests = mutableMapOf<String, Sound>()
    private var music: Music? = null

    override fun onTick() {
        if (soundRequests.isEmpty()) {
            return
        }

        soundRequests.values.forEach { it.play(1f) }
        soundRequests.clear()
    }

    override fun handle(event: Event): Boolean {
        when (event) {
            is MapChangeEvent -> {
                event.map.propertyOrNull<String>("bgm")?.let { path ->
                    log.debug { "Changing music to '$path'" }
                    val newMusic = musicCache.getOrPut(path) {
                        Gdx.audio.newMusic(Gdx.files.internal(path)).apply {
                            isLooping = true
                        }
                    }
                    if (music != null && newMusic != music) {
                        music?.stop()
                    }
                    music = newMusic
                    newMusic.play()
                }
                return true
            }

            is EntityOpenEvent -> queueSound("sound/${event.model.atlasKey}_open.mp3")
            /*is GamePauseEvent -> {
                music?.pause()
                soundCache.values.forEach { it.pause() }
            }

            is GameResumeEvent -> {
                music?.play()
                soundCache.values.forEach { it.resume() }
            }*/
        }
        return false
    }

    private fun queueSound(soundPath: String) {
        log.debug { "Queueing new sound '$soundPath'" }
        if (soundPath in soundRequests) {
            return
        }

        val sound = soundCache.getOrPut(soundPath) {
            Gdx.audio.newSound(Gdx.files.internal(soundPath))
        }
        soundRequests[soundPath] = sound
    }

    override fun onDispose() {
        soundCache.values.forEach { it.disposeSafely() }
        musicCache.values.forEach { it.disposeSafely() }
    }

    companion object {
        private val log = logger<AudioSystem>()
    }
}
