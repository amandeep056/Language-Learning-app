// ViewVocabularyListFragment.kt
package com.example.languagelearningapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class ViewVocabularyListFragment : Fragment() {

    private lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_vocabulary_list, container, false)
        listView = view.findViewById(R.id.list_view_vocabulary)

        val vocabularyList = VocabularyRepository.getVocabularyList().map {
            "${it.word} - ${it.meaning}"
        }

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, vocabularyList)
        listView.adapter = adapter

        return view
    }
}
