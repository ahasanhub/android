package com.learn2crack.retrofitkotlin.network


import com.learn2crack.retrofitkotlin.model.Post
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface RequestInterface {
    @GET("posts")
    fun getData() : Observable<List<Post>>

    @POST("posts")
    fun addData(@Body post:Post):Observable<Post>
}
