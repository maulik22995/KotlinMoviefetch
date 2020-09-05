package com.maulikpract.model

import android.net.Uri
import android.os.Environment
import androidx.core.net.toUri
import java.io.File

data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
){
    var isDownloading: Boolean = false
    var progress = 0

    /*val uriFile: Uri
        get() = file.toUri()*/
   /* val file: File
        get() = File(globalContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), title)*/

}