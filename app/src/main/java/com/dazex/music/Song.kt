package com.dazex.music

data class Song(
    val id: Long,
    val title: String,
    val artist: String,
    val dataPath: String,
    val duration: Long
)
