package com.appetiser.testapp.presenter

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.appetiser.testapp.R
import com.appetiser.testapp.presenter.common.base.BaseFragment
import com.appetiser.testapp.presenter.common.event.EventObserver
import com.appetiser.testapp.presenter.common.extension.ensureNavigate
import com.appetiser.testapp.presenter.common.extension.loadImage
import com.appetiser.testapp.presenter.common.state.LiveState
import com.appetiser.testapp.presenter.model.Track
import kotlinx.android.synthetic.main.fragment_track_detail.*
import javax.inject.Inject

class TrackDetailFragment : BaseFragment(R.layout.fragment_track_detail) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: TrackDetailViewModel

    override fun setupViewModel() {
        super.setupViewModel()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TrackDetailViewModel::class.java)
        viewModel.track.observe(this, Observer { handleTrackDetails(it) })
        viewModel.submit.observe(this,EventObserver { handleSubmit(it) })
    }

    override fun setupView() {
        super.setupView()
        toolbar.setNavigationOnClickListener { handleOnBackPressed() }
    }

    override fun handleOnBackPressed() {
        viewModel.removeTrack()
    }

    /**
     * Data Handler for track details
     * @param state LiveState wrapped track object
     * @see LiveState
     */
    private fun handleTrackDetails(state: LiveState<Track>){
        if (state is LiveState.Data){
            image_artwork.loadImage(imageUrl = state.data.imageUrlLarge)
            text_name.text = state.data.name
            text_description.text = state.data.longDescription
            text_genre.text = state.data.genre
            text_price.text = "${state.data.price} ${state.data.currency}"
        }
    }

    /**
     * Data Handler for track details
     * @param state LiveState wrapped Unit
     * @see LiveState
     */
    private fun handleSubmit(state: LiveState<Unit>){
        if (state is LiveState.Data){
            findNavController().ensureNavigate(
                destinationId = R.id.action_to_fragment_list,
                currentScreenId = R.id.fragment_track_detail)
        }
    }

}