package com.watsidev.producto2.complication.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

data class TaskRequest(
    val title: String,
    val description: String,
    val priority: String,
    val column_id: String,
    val created_by: String
)

data class TaskResponse(
    val id: Int,
    val title: String,
    val description: String,
    val priority: String,
    val column_id: Int,
    val created_by: Int,
    val created_at: String,
    val updated_at: String
)


interface TaskApi {
    @GET("api/tasks")
    suspend fun getTasksByColumn(
        @Query("column_id") columnId: Int = 1
    ): List<TaskResponse>

    @POST("api/tasks")
    suspend fun createTask(@Body task: TaskRequest): TaskResponse
}


object RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://kanban-api-production-3916.up.railway.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: TaskApi = retrofit.create(TaskApi::class.java)
}
