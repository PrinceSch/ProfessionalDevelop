package com.princesch.profdevelop.view.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.princesch.profdevelop.R
import com.princesch.profdevelop.databinding.ActivityMainBinding
import com.princesch.profdevelop.model.data.AppState
import com.princesch.profdevelop.model.data.DataModel
import com.princesch.profdevelop.utils.isOnline
import com.princesch.profdevelop.view.base.BaseActivity
import com.princesch.profdevelop.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    private lateinit var binding: ActivityMainBinding
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
            }
        }

    private val fabClickListener: View.OnClickListener =
        View.OnClickListener {
            val searchFragment = SearchFragment.newInstance()
            searchFragment.setOnSearchClickListener(object :
                SearchFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    isNetworkAvailable = isOnline(applicationContext)
                    if (isNetworkAvailable) {
                        model.getData(searchWord, isNetworkAvailable)
                    } else {
                        Toast.makeText(
                            applicationContext,
                            resources.getString(R.string.dialog_title_device_is_offline),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            })
            searchFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }

    override lateinit var model: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initViews()
    }

    private fun initViewModel() {
        if (binding.mainActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: MainViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@MainActivity, { renderData(it) })
    }

    private fun initViews() {
        binding.searchFab.setOnClickListener(fabClickListener)
        binding.mainActivityRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
        binding.mainActivityRecyclerview.adapter = adapter
    }


    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                showViewWorking()
                val data = appState.data
                if (data.isNullOrEmpty()) {
                    Toast.makeText(applicationContext,
                        getString(R.string.empty_server_response_on_success),
                        Toast.LENGTH_LONG).show()
                } else {
                    adapter?.setData(data)
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = View.VISIBLE
                    binding.progressBarRound.visibility = View.GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = View.GONE
                    binding.progressBarRound.visibility = View.VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }


    private fun showViewWorking() {
        binding.loadingFrameLayout.visibility = View.GONE
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            Toast.makeText(
                applicationContext,
                resources.getString(R.string.dialog_title_device_is_offline),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = View.GONE
        binding.loadingFrameLayout.visibility = View.VISIBLE
        binding.errorLinearLayout.visibility = View.GONE
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = View.GONE
        binding.loadingFrameLayout.visibility = View.GONE
        binding.errorLinearLayout.visibility = View.VISIBLE
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}