package com.victorio.fastshopclone.ui.fragments.Home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.victorio.fastshopclone.R
import com.victorio.fastshopclone.model.Product
import com.victorio.fastshopclone.ui.activities.OnProductClickListener
import kotlinx.android.synthetic.main.products_home_itens.view.*


class ProductsHomeAdapter(var products: List<Product>, var context: Context) : RecyclerView.Adapter<ProductsHomeAdapter.ProductsViewHolder>() {

    var onProductClickListener : OnProductClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.products_home_itens, parent, false)


        return ProductsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(position)
    }



    inner class ProductsViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        fun bind(position: Int){
            var product = products[position]
            Glide.with(context).load(product.thumbnail).into(view.img_item_product)
            view.tv_item_description.text = product.name
            view.tv_item_price.text = "R$${product.price}"

            view.setOnClickListener {
                onProductClickListener?.onClick(product)
            }

        }
    }
}