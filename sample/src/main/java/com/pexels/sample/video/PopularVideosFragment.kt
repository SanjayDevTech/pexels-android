package com.pexels.sample.video

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.pexels.sample.ListResourceAdapter
import com.pexels.sample.databinding.FragmentHandpickedItemsBinding

class PopularVideosFragment : Fragment() {

    private var _binding: FragmentHandpickedItemsBinding? = null
    private val binding: FragmentHandpickedItemsBinding
        get() = _binding!!

    private val viewModel: VideosViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHandpickedItemsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ListResourceAdapter { viewItem ->
            val openUrl = Intent(Intent.ACTION_VIEW).also {
                it.data = viewItem.previewUrl.toUri()
            }
            requireContext().startActivity(openUrl)
        }
        binding.listView.adapter = adapter
        viewModel.popularVideos.observe(viewLifecycleOwner) {
            adapter.items = it
        }
        viewModel.popularEvent.observe(viewLifecycleOwner) {
            if (it.eventHandled) return@observe
            Snackbar.make(requireContext(), view, it.content!!, Snackbar.LENGTH_LONG)
                .setAction("Retry") {
                    viewModel.fetchPopular()
                }.show()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}