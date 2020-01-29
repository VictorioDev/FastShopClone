package com.victorio.fastshopclone.domain.api

import com.victorio.fastshopclone.model.CategoryResponse
import com.victorio.fastshopclone.model.ProductResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface EcomerceServiceApi {

    companion object{
        private const val URL = "http://192.168.0.108:3000/"
        private var instance : EcomerceServiceApi? = null
        private var retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        fun newInstance() : EcomerceServiceApi? {
            if(instance == null) {
                return retrofit.create(EcomerceServiceApi::class.java).also { instance = it }
            }else {
                return instance
            }
        }
    }

    @GET("categories/{categoryId}")
    fun getCategories(@Path("categoryId") categoryId: String?): Observable<CategoryResponse>


    @GET("products")
    fun getProducts(@Query("category") categoryId: String?): Observable<ProductResponse>

}