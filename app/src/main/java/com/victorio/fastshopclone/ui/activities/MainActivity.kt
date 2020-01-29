package com.victorio.fastshopclone.ui.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import com.victorio.fastshopclone.R
import com.victorio.fastshopclone.model.Product
import com.victorio.fastshopclone.ui.activities.DetailProduct.DetailProductActivity
import com.victorio.fastshopclone.ui.activities.ProductsInCategory.ProductInCategoryActivity
import com.victorio.fastshopclone.ui.fragments.Categories.CategoriesFragment
import com.victorio.fastshopclone.ui.fragments.Categories.OnFragmentCreated
import com.victorio.fastshopclone.ui.fragments.Home.HomeFragment
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity : AppCompatActivity(), OnFragmentCreated, OnCategoryClickListener, OnProductClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            var homeFragment = HomeFragment.newInstance()
            homeFragment.onFragmentCreated = this
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, homeFragment)
                .commitNow()
        }
        
        bottom_navigation.setOnNavigationItemSelectedListener {
            menuItem ->
            var transaction = supportFragmentManager.beginTransaction()
            when(menuItem.itemId) {
                R.id.home -> {
                    var homeFragment = HomeFragment.newInstance()
                    homeFragment.onFragmentCreated = this
                    transaction.replace(R.id.container,homeFragment).commit()
                }
                R.id.products -> {
                    var categoriesFragment = CategoriesFragment.newInstance()
                    categoriesFragment.onFragmentCreated = this
                    transaction.replace(R.id.container, categoriesFragment).commit()
                }
            }
            true
        }

        search_container.setOnClickListener {
            val activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, search_container, "search_container_transition")
            val intent: Intent = Intent(
                this,
                SearchActivity::class.java
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                startActivity(intent ,activityOptionsCompat.toBundle())
            }else {
                startActivity(intent)
            }

        }
    }

    override fun changeToobar(fragmentId : Int) {
        when(fragmentId){
            0 -> {
                main_toolbar.title = ""
                var drawable = ContextCompat.getDrawable(this,
                    R.drawable.fast_shop_logo
                )
                main_toolbar.logo = drawable
                setSupportActionBar(main_toolbar)
            }
            1 -> {
                main_toolbar.title = "Produtos"
                main_toolbar.logo = null
                main_toolbar.setTitleTextColor(ContextCompat.getColor(this,
                    R.color.searchStroke
                ))
                setSupportActionBar(main_toolbar)
            }
        }

    }

    override fun onCLick(categoryId: String?) {
        var intent = Intent(this, ProductInCategoryActivity::class.java)
        intent.putExtra("category_id", categoryId)
        startActivity(intent)
    }

    override fun onClick(product: Product) {
        var intent = Intent(this, DetailProductActivity::class.java)
        intent.putExtra("product", product)
        startActivity(intent)
    }


}
