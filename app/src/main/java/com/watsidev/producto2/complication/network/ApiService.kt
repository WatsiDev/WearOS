package com.watsidev.producto2.complication.network

import com.watsidev.producto2.complication.data.model.ApiResponse
import com.watsidev.producto2.complication.data.model.Priority
import com.watsidev.producto2.complication.data.model.StatusUpdate
import com.watsidev.producto2.complication.data.model.Task
import com.watsidev.producto2.complication.data.model.TaskRequest
import com.watsidev.producto2.complication.data.model.UpdateTask
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

//const val BASE_URL = "http://localhost:3000"
// Si usas emulador de Android Studio:
const val BASE_URL = "http://10.0.2.2:3000/api/"
interface TaskApi {

    @GET("tasks")
    suspend fun getAllTasks(): List<Task>

    @GET("tasks/priority/{id}")
    suspend fun getTasksByPriority(@Path("id") priorityId: Int): List<Task>

    @GET("tasks/{id}")
    suspend fun getTaskById(@Path("id") id: Int): UpdateTask

    @POST("tasks")
    suspend fun createTask(@Body task: TaskRequest): ApiResponse

    @PUT("tasks/{id}")
    suspend fun updateTask(@Path("id") id: Int, @Body task: TaskRequest): ApiResponse

    @PATCH("tasks/{id}/status")
    suspend fun updateTaskStatus(@Path("id") id: Int, @Body status: StatusUpdate): ApiResponse

    @DELETE("tasks/{id}")
    suspend fun deleteTask(@Path("id") id: Int): ApiResponse

    @GET("priorities")
    suspend fun getPriorities(): List<Priority>
}

object RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: TaskApi = retrofit.create(TaskApi::class.java)
}
