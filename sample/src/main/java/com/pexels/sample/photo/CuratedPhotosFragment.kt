package com.pexels.sample.photo

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

class CuratedPhotosFragment : Fragment() {

    private var _binding: FragmentHandpickedItemsBinding? = null
    private val binding: FragmentHandpickedItemsBinding
        get() = _binding!!

    private val viewModel: PhotosViewModel by viewModels()


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
        viewModel.curatedPhotos.observe(viewLifecycleOwner) {
            adapter.items = it
        }
        viewModel.curatedEvent.observe(viewLifecycleOwner) {
            if (it.eventHandled) return@observe
            Snackbar.make(requireContext(), view, it.content!!, Snackbar.LENGTH_LONG)
                .setAction("Retry") {
                    viewModel.fetchCurated()
                }.show()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}