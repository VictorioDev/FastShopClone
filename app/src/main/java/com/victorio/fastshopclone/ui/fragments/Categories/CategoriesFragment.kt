package com.victorio.fastshopclone.ui.fragments.Categories

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.victorio.fastshopclone.R
import com.victorio.fastshopclone.domain.api.EcomerceServiceApi
import com.victorio.fastshopclone.domain.implementation.NetworkDataSourceImplementation
import com.victorio.fastshopclone.domain.repository.CategoryRepository
import com.victorio.fastshopclone.domain.repository.ProductRepository
import com.victorio.fastshopclone.model.Category
import com.victorio.fastshopclone.ui.activities.MainActivity
import com.victorio.fastshopclone.ui.fragments.Home.HomeViewModel
import kotlinx.android.synthetic.main.categories_fragment.*


class CategoriesFragment : Fragment() {

    val FRAGMENT_ID = 1
    var onFragmentCreated: OnFragmentCreated? = null

    companion object {
        fun newInstance() = CategoriesFragment()
    }

    private lateinit var viewModel: HomeViewModel
    var categories = arrayListOf<Category>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.categories_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel()

        var categoriesAdapter = CategoriesAdapter(categories, activity!!.baseContext)
        categoriesAdapter.onCategoryClickListener = activity as MainActivity
        rv_categories_itens.apply {
            adapter = categoriesAdapter
            layoutManager = LinearLayoutManager(context)
        }


        viewModel.observeCategories().observe(this, Observer {
            Log.d("VAZP", "ObserveCategories: ${it.size}")
            categories.addAll(it)
            categoriesAdapter.notifyDataSetChanged()
            rv_categories_itens.visibility = View.VISIBLE
            categories_progress_bar.visibility = View.INVISIBLE
        })

        onFragmentCreated?.changeToobar(FRAGMENT_ID)


    }

    private fun getViewModel(): HomeViewModel {

        return ViewModelProviders.of(requireActivity(), object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(
                    CategoryRepository(NetworkDataSourceImplementation(EcomerceServiceApi.newInstance())),
                    ProductRepository(NetworkDataSourceImplementation(EcomerceServiceApi.newInstance()))
                ) as T
            }
        })[HomeViewModel::class.java]
    }



}
