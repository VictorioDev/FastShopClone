package com.victorio.fastshopclone.domain.implementation

import com.victorio.fastshopclone.domain.NetworkDatasource
import com.victorio.fastshopclone.domain.api.EcomerceServiceApi
import com.victorio.fastshopclone.model.CategoryResponse
import com.victorio.fastshopclone.model.ProductResponse
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class NetworkDataSourceImplementation(var ecomerceServiceApi: EcomerceServiceApi?) : NetworkDatasource {
    override fun getCategories(categoryId: String): Observable<CategoryResponse> = ecomerceServiceApi!!.getCategories(categoryId)
    override fun getProducts(categoryId: String?): Observable<ProductResponse> = ecomerceServiceApi!!.getProducts(categoryId)

}