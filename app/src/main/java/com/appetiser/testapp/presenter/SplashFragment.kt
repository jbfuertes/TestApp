package com.appetiser.testapp.presenter

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.appetiser.testapp.R
import com.appetiser.testapp.presenter.common.base.BaseFragment
import com.appetiser.testapp.presenter.common.event.EventObserver
import com.appetiser.testapp.presenter.common.extension.ensureNavigate
import javax.inject.Inject

class SplashFragment : BaseFragment(R.layout.fragment_splash) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SplashViewModel

    override fun setupViewModel() {
        super.setupViewModel()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel::class.java)
        viewModel.showInitialScreen.observe(this, EventObserver {
            findNavController().ensureNavigate(
                destinationId = if (it) R.id.action_to_fragment_list
                else R.id.action_to_fragment_track_detail,
                currentScreenId = R.id.fragment_splash
            )
        })
    }
}