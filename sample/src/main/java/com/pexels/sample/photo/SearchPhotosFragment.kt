package com.pexels.sample.photo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.pexels.sample.ListResourceAdapter
import com.pexels.sample.databinding.FragmentSearchItemsBinding

class SearchPhotosFragment : Fragment() {

    private var _binding: FragmentSearchItemsBinding? = null
    private val binding: FragmentSearchItemsBinding
        get() = _binding!!

    private val viewModel: PhotosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchItemsBinding.inflate(inflater, container, false)
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
        binding.searchEditText.setOnEditorActionListener { _, actionId, _ ->
            Log.d("SearchPhotosFragment", "setOnEditorListener: $actionId")
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    viewModel.searchFor(binding.searchEditText.text?.toString() ?: "")
                    requireActivity().currentFocus?.also {
                        val iMM =
                            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        iMM.hideSoftInputFromWindow(it.windowToken, 0)
                    }
                    true
                }
                else -> false
            }
        }
        binding.listView.adapter = adapter
        viewModel.searchPhotos.observe(viewLifecycleOwner) {
            adapter.items = it
        }
        viewModel.searchEvent.observe(viewLifecycleOwner) {
            if (it.eventHandled) return@observe
            Snackbar.make(requireContext(), view, it.content!!, Snackbar.LENGTH_LONG)
                .setAction("Retry") {
                    viewModel.searchFor(binding.searchEditText.text?.toString() ?: "")
                }.show()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}