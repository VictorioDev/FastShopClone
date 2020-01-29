package com.victorio.fastshopclone.domain

import com.victorio.fastshopclone.model.CategoryResponse
import com.victorio.fastshopclone.model.ProductResponse
import io.reactivex.Observable

interface NetworkDatasource {

    fun getCategories(categoryId: String) : Observable<CategoryResponse>

    fun getProducts(categoryId: String?): Observable<ProductResponse>
}