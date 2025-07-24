package com.watsidev.producto2.complication.data.model

data class TaskRequest(
    val title: String,
    val description: String?,
    val priority_id: Int,
    val date_limit: String
)
