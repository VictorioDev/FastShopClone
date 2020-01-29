package com.victorio.fastshopclone.ui.fragments.Home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.synnapps.carouselview.ImageListener
import com.victorio.fastshopclone.R
import com.victorio.fastshopclone.domain.api.EcomerceServiceApi
import com.victorio.fastshopclone.domain.implementation.NetworkDataSourceImplementation
import com.victorio.fastshopclone.domain.repository.CategoryRepository
import com.victorio.fastshopclone.domain.repository.ProductRepository
import com.victorio.fastshopclone.model.Category
import com.victorio.fastshopclone.model.Product
import com.victorio.fastshopclone.ui.activities.MainActivity
import com.victorio.fastshopclone.ui.fragments.Categories.OnFragmentCreated
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : Fragment() {

    val FRAGMENT_ID = 0
    var onFragmentCreated: OnFragmentCreated? = null
    var categories = arrayListOf<Category>()
    var products = arrayListOf<Product>()

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel


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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.home_fragment, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = getViewModel()

        var categoriesAdapter =
            CategoriesHomeAdapter(
                categories,
                activity!!.baseContext
            )

        var productsHomeAdapter =
            ProductsHomeAdapter(
                products,
                activity!!.baseContext
            )
        productsHomeAdapter.onProductClickListener = activity as MainActivity

        rv_categories.apply {
            adapter = categoriesAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }

        rv_products_visited.apply {
            adapter = productsHomeAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }

        var carouselThumbNails = arrayListOf<Int>(R.drawable.cr_1, R.drawable.cr_2, R.drawable.cr_3)

        val imageListener =
            ImageListener { position, imageView ->
                imageView.setImageResource(
                    carouselThumbNails[position]
                )
            }

        carouselView.setImageListener(imageListener)

        carouselView.pageCount = carouselThumbNails.size


        viewModel.observeCategories().observe(this, Observer {
            Log.d("VAZP", "Chegou aqui ${it.size}")
            categories.addAll(it)
            categoriesAdapter.notifyDataSetChanged()
        })



        viewModel.observeProducts().observe(this, Observer {
            Log.d("VAZP", "Chegou aqui ${it.size}")
            products.addAll(it)
            productsHomeAdapter.notifyDataSetChanged()
        })
        viewModel.fetchCategories()

        viewModel.fetchProducts()


        // TODO: Use the ViewModel
    }



    override fun onResume() {
        super.onResume()
        Log.d("VAZP", "Fragment Home resumed")
        onFragmentCreated?.changeToobar(FRAGMENT_ID)
    }

}
