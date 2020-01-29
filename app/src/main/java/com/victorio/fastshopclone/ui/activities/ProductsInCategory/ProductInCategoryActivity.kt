package com.victorio.fastshopclone.ui.activities.ProductsInCategory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.victorio.fastshopclone.R
import com.victorio.fastshopclone.domain.api.EcomerceServiceApi
import com.victorio.fastshopclone.domain.implementation.NetworkDataSourceImplementation
import com.victorio.fastshopclone.domain.repository.CategoryRepository
import com.victorio.fastshopclone.domain.repository.ProductRepository
import com.victorio.fastshopclone.model.Product
import com.victorio.fastshopclone.ui.activities.DetailProduct.DetailProductActivity
import com.victorio.fastshopclone.ui.activities.OnCategoryClickListener
import com.victorio.fastshopclone.ui.activities.OnProductClickListener
import kotlinx.android.synthetic.main.activity_product_in_category.*

class ProductInCategoryActivity : AppCompatActivity(), OnProductClickListener{



    override fun onClick(product: Product) {
        var intent = Intent(this, DetailProductActivity::class.java)
        intent.putExtra("product", product)
        startActivity(intent)
    }

    var products = arrayListOf<Product>()

    lateinit var productsInCategoryViewModel: ProductsInCategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_in_category)

        var categoryId : String = intent.extras?.get("category_id") as String


        var productsInCategoryAdapter = ProductsInCategoryAdapter(products, this)
        productsInCategoryAdapter.onProductClickListener = this
        rv_products_category.apply {
            adapter = productsInCategoryAdapter
            layoutManager = LinearLayoutManager(baseContext)
        }

        productsInCategoryViewModel = getViewModel()

        productsInCategoryViewModel.getProductsInCategory().observe(this, Observer {
            Log.d("VAZP", "Chegaram ${it.size} produtos na classe ${javaClass.simpleName}")
            products.addAll(it)
            productsInCategoryAdapter.notifyDataSetChanged()
        })

        productsInCategoryViewModel.getCategoryName().observe(this, Observer {
            Log.d("VAZP", "Categoria escolhida na classe ${javaClass.simpleName} foi ${it}")
            products_in_category_toolbar.title = it

            setSupportActionBar(products_in_category_toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)


            products_progress_bar.visibility = View.INVISIBLE
            products_container.visibility = View.VISIBLE
        })

        categoryId.let {
            productsInCategoryViewModel.fetchProductsWithCategory(it)
            productsInCategoryViewModel.fetchCategoryName(it)
        }



    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getViewModel(): ProductsInCategoryViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return ProductsInCategoryViewModel(
                    CategoryRepository(NetworkDataSourceImplementation(EcomerceServiceApi.newInstance())),
                    ProductRepository(NetworkDataSourceImplementation(EcomerceServiceApi.newInstance()))
                ) as T
            }
        })[ProductsInCategoryViewModel::class.java]
    }


}
