package com.watsidev.producto2.complication.data.model

import com.google.gson.annotations.SerializedName

data class Task(
    val id: Int,
    val title: String,
    val description: String?,
    val priority: String,
    @SerializedName("date_limit") val dateLimit: String,
    @SerializedName("is_completed") val isCompleted: Boolean,
    @SerializedName("create_date") val createDate: String
)

data class UpdateTask(
    val id: Int,
    val title: String,
    val description: String?,
    val priority: Int,
    @SerializedName("date_limit") val dateLimit: String,
    @SerializedName("is_completed") val isCompleted: Boolean,
    @SerializedName("create_date") val createDate: String
)