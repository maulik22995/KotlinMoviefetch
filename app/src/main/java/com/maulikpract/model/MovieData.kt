package com.maulikpract.model

data class MovieData(
    val page: Int,
    val results: ArrayList<Result>,
    val total_pages: Int,
    val total_results: Int
)