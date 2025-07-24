package com.watsidev.producto2.complication.data.model

data class ApiResponse(
    val message: String?,
    val error: String? = null,
    val task_id: Int? = null
)
