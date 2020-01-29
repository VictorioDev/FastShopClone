package com.victorio.fastshopclone.domain.repository

import com.victorio.fastshopclone.domain.NetworkDatasource

class CategoryRepository(var networkDatasource: NetworkDatasource) {

    fun getCategories(categoryId: String) = networkDatasource.getCategories(categoryId)

}