package com.appetiser.testapp.presenter.common.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment(contentLayoutId: Int) : Fragment(contentLayoutId){


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        val callback = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                this@BaseFragment.handleOnBackPressed()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this,callback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        setupViewModel()
        setupView()
        setupPermission()
        setupInitialData()
    }

    /**
     * Handles dependencies setup eg. Third party libraries.
     */
    open fun setupDependencies() {}

    /**
     * Handles viewModels initialisation and observing.
     */
    open fun setupViewModel() {}

    /**
     * Handles setting up views eg. recyclerView
     */
    open fun setupView() {}

    /**
     * Handles permission requests
     */
    open fun setupPermission() {}

    /**
     * Handles initial data and data calls
     */
    open fun setupInitialData() {}

    /**
     * Override backpress event
     */
    open fun handleOnBackPressed() {
        findNavController().navigateUp()
    }

}