package com.example.zaynannisa.kotlinretrofitrxjava.retrofit

import com.example.zaynannisa.kotlinretrofitrxjava.data.Post
import io.reactivex.Observable
import retrofit2.http.GET


interface IMyApi {
    @get:GET("posts")
    val posts: Observable<List<Post>>
}