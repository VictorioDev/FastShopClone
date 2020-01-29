package com.victorio.fastshopclone.ui.fragments.Home

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

class HomeViewModel(var categoryRepository: CategoryRepository, var productRepository: ProductRepository) : ViewModel() {
    private var _categories = MutableLiveData<List<Category>>()
    private var categories : LiveData<List<Category>> = MutableLiveData()
        get() = _categories

    private var _products = MutableLiveData<List<Product>>()
    private var products : LiveData<List<Product>> = MutableLiveData()
        get() = _products
    private var compositeDisposable = CompositeDisposable()


    fun observeCategories() : LiveData<List<Category>> = categories

    fun observeProducts() : LiveData<List<Product>> = products


    fun fetchCategories(){
       compositeDisposable.add(categoryRepository.getCategories("")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _categories.value = it.categories
                Log.d("VAZP", "Size: ${it.categories.size}")
            },{
                Log.d("VAZP", "Deu Ruim! -> ${it.message}")
            }))
    }

    fun fetchProducts(){
        compositeDisposable.add(productRepository.getProducts(null)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _products.value = it.products
                Log.d("VAZP", "Size Products: ${it.products.size}")
            },{
                Log.d("VAZP", "Deu Ruim! -> ${it.message}")
            }))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
