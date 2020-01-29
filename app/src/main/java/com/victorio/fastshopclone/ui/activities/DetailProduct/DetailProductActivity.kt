package com.victorio.fastshopclone.ui.activities.DetailProduct

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.animation.doOnStart
import com.bumptech.glide.Glide
import com.victorio.fastshopclone.R
import com.victorio.fastshopclone.model.Category
import com.victorio.fastshopclone.model.Product
import kotlinx.android.synthetic.main.activity_detail_product.*
import kotlinx.android.synthetic.main.activity_product_in_category.*
import kotlinx.android.synthetic.main.cart_menu_item_layout.*

class DetailProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)

        setSupportActionBar(product_detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        var product: Product = intent.extras?.get("product") as Product

        product.let {
            tv_product_name.text = product.name
            tv_product_price.text = "R$${product.price}"
            Glide.with(this).load(product.thumbnail).into(iv_product_thumbnail)
        }

        btn_add_to_cart.setOnClickListener {
            cart_badge.text = "1"

            var objectAnimator = ObjectAnimator.ofFloat(cart_badge, "alpha", 0f, 1f)
            objectAnimator.apply {
                duration = 350
                interpolator = AccelerateDecelerateInterpolator()
                doOnStart {
                    cart_badge.visibility = View.VISIBLE
                }
            }.start()

        }

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true
    }


}
