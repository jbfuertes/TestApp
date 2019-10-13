package com.appetiser.testapp.presenter

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.appetiser.testapp.R
import com.appetiser.testapp.presenter.common.base.BaseFragment
import com.appetiser.testapp.presenter.common.event.EventObserver
import com.appetiser.testapp.presenter.common.extension.ensureNavigate
import com.appetiser.testapp.presenter.common.state.LiveState
import com.appetiser.testapp.presenter.controller.ListController
import com.appetiser.testapp.presenter.model.Track
import kotlinx.android.synthetic.main.fragment_list.*
import timber.log.Timber
import javax.inject.Inject

class ListFragment : BaseFragment(R.layout.fragment_list), ListController.Callback {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ListViewModel

    private val controller = ListController(this)

    override fun setupViewModel() {
        super.setupViewModel()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)
        viewModel.tracks.observe(this, Observer { handleTracks(it) })
        viewModel.submit.observe(this, EventObserver { handleSubmitAction(it) })
        viewModel.date.observe(this, Observer { controller.setDate(it) })
    }

    override fun setupView() {
        super.setupView()
        recycler_view.apply {
            val linearLayoutManager = LinearLayoutManager(requireContext())
            layoutManager = linearLayoutManager
            setController(controller)
        }

        swipe_refresh.setOnRefreshListener {
            swipe_refresh.isRefreshing = false
            viewModel.loadData(true)
        }
    }

    override fun onTrackClicked(track: Track) {
        viewModel.saveTrack(track)
    }

    override fun onReloadList() {
        viewModel.loadData(true)
    }

    override fun handleOnBackPressed() {
        requireActivity().finish()
    }

    /**
     * data handler for track list
     * @param state LiveState wrapped list of track
     * @see LiveState
     */
    private fun handleTracks(state: LiveState<List<Track>>){
        controller.setLoading(state is LiveState.Loading)
        controller.setError(if (state is LiveState.Error) "${state.error.localizedMessage}\nTap to retry" else "")
        controller.setTracks(if (state is LiveState.Data) state.data else emptyList())
    }

    /**
     * data handler for item click action
     * @param state LiveState wrapped Unit
     * @see LiveState
     */
    private fun handleSubmitAction(state : LiveState<Unit>){
        if (state is LiveState.Data){
            findNavController().ensureNavigate(
                destinationId = R.id.action_to_fragment_track_detail,
                currentScreenId = R.id.fragment_list)
        }
    }

}