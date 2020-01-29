package com.victorio.fastshopclone.ui.activities.ProductsInCategory


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.victorio.fastshopclone.R
import com.victorio.fastshopclone.model.Category
import com.victorio.fastshopclone.model.Product
import com.victorio.fastshopclone.ui.activities.OnProductClickListener
import kotlinx.android.synthetic.main.categories_categories_item.view.*
import kotlinx.android.synthetic.main.products_in_category_itens.view.*


class ProductsInCategoryAdapter(var products: List<Product>, var context: Context) : RecyclerView.Adapter<ProductsInCategoryAdapter.ProductsInCategoryViewHolder>() {

    var onProductClickListener: OnProductClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsInCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.products_in_category_itens, parent, false)


        return ProductsInCategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductsInCategoryViewHolder, position: Int) {
        holder.bind(position)
    }



    inner class ProductsInCategoryViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        fun bind(position: Int){

            var product = products[position]
            Glide.with(context).load(product.thumbnail).into(view.img_item_product)
            view.tv_item_price.text = "R$${product.price}"
            view.tv_item_description.text = product.name

            view.setOnClickListener {
                onProductClickListener?.onClick(product)
            }
        }
    }
}