package com.victorio.fastshopclone.ui.fragments.Home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.victorio.fastshopclone.R
import com.victorio.fastshopclone.model.Category
import kotlinx.android.synthetic.main.category_home_item.view.*


class CategoriesHomeAdapter(var categories: List<Category>, var context: Context) : RecyclerView.Adapter<CategoriesHomeAdapter.CategoriesHomeViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.category_home_item, parent, false)


        return CategoriesHomeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoriesHomeViewHolder, position: Int) {
        holder.bind(position)
    }



    inner class CategoriesHomeViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        fun bind(postition: Int){

            var category = categories[postition]
            view.text_category_item.text = category.name
            Glide.with(context).load(category.thumbnail).into(view.img_category_item)

        }
    }
}