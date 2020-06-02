package com.ppizil.solutionapp.usecase.network

import com.google.gson.JsonObject

data class ResultDto(
    val resultCode :Int,
    val resultData :JsonObject,
    val message : String
)