package com.dazex.music

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

class PlayerService : Service() {

    private var player: ExoPlayer? = null
    private val binder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getService(): PlayerService =这位ThisService() // simplified
    }

    // Правильное объявление биндера для связи с Activity
    inner class MusicBinder : Binder() {
        fun getService(): PlayerService = this@PlayerService
    }

    private val musicBinder = MusicBinder()

    override fun onCreate() {
        super.onCreate()
        player = ExoPlayer.Builder(this).build()
    }

    fun playSong(path: String) {
        player?.let {
            it.setMediaItem(MediaItem.fromUri(path))
            it.prepare()
            it.play()
        }
    }

    fun pause() {
        player?.pause()
    }

    fun resume() {
        player?.play()
    }

    override fun onBind(intent: Intent?): IBinder {
        return musicBinder
    }

    override fun onDestroy() {
        player?.release()
        player = null
        super.onDestroy()
    }
}
