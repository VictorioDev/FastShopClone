package com.victorio.fastshopclone.ui.fragments.Categories

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.victorio.fastshopclone.R
import com.victorio.fastshopclone.model.Category
import com.victorio.fastshopclone.ui.activities.OnCategoryClickListener
import kotlinx.android.synthetic.main.categories_categories_item.view.*


class CategoriesAdapter(var categories: List<Category>, var context: Context) : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    var onCategoryClickListener : OnCategoryClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.categories_categories_item, parent, false)


        return CategoriesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(position)
    }



    inner class CategoriesViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        fun bind(position: Int){

            var category = categories[position]
            view.tv_item_category_name.text = category.name

            view.setOnClickListener {
                onCategoryClickListener?.onCLick(category.id.toString())
            }
        }
    }
}