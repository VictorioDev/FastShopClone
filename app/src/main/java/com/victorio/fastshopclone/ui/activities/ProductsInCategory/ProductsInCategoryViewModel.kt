package com.victorio.fastshopclone.ui.activities.ProductsInCategory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.victorio.fastshopclone.domain.repository.CategoryRepository
import com.victorio.fastshopclone.domain.repository.ProductRepository
import com.victorio.fastshopclone.model.Category
import com.victorio.fastshopclone.model.Product
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ProductsInCategoryViewModel(var categoryRepository: CategoryRepository, var productRepository: ProductRepository): ViewModel() {

    private var compositeDisposable = CompositeDisposable()

    private var _products = MutableLiveData<List<Product>>()
    var products: LiveData<List<Product>> = _products

    private var _category = MutableLiveData<String>()
    var category: LiveData<String> = _category


    fun fetchProductsWithCategory(categoryId: String?){
         compositeDisposable.add(productRepository.getProducts(categoryId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
               it.let {
                   _products.value = it.products
               }
            },{
                Log.d("VAZP", "Deu ruim ${javaClass.simpleName}")
            }))
    }

    fun fetchCategoryName(categoryId: String){
        compositeDisposable.add(categoryRepository.getCategories(categoryId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.let {
                    if(it.categories.size > 0){
                        _category.value = it.categories[0].name
                        Log.d("VAZP", "${javaClass.simpleName} -> ${it.categories[0].name}")
                    }
                }
            },{
                Log.d("VAZP", "Deu ruim ${javaClass.simpleName}")
            }))
    }


   fun getProductsInCategory(): LiveData<List<Product>> = products
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun getCategoryName() : LiveData<String> = category
}