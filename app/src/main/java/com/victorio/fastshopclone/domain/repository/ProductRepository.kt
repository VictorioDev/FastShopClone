package com.victorio.fastshopclone.domain.repository

import com.victorio.fastshopclone.domain.NetworkDatasource

class ProductRepository (var networkDatasource: NetworkDatasource) {

    fun getProducts(categoryId: String?) = networkDatasource.getProducts(categoryId)
}