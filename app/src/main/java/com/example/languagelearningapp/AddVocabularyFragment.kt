// AddVocabularyFragment.kt
package com.example.languagelearningapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment

class AddVocabularyFragment : Fragment() {

    private lateinit var etWord: EditText
    private lateinit var etMeaning: EditText
    private lateinit var spinnerCategory: Spinner
    private lateinit var rgDifficulty: RadioGroup
    private lateinit var btnSave: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_vocabulary, container, false)
        etWord = view.findViewById(R.id.et_word)
        etMeaning = view.findViewById(R.id.et_meaning)
        spinnerCategory = view.findViewById(R.id.spinner_category)
        rgDifficulty = view.findViewById(R.id.rg_difficulty)
        btnSave = view.findViewById(R.id.btn_save)

        // Set up the category spinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.category_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCategory.adapter = adapter
        }

        btnSave.setOnClickListener {
            saveVocabulary()
        }

        return view
    }

    private fun saveVocabulary() {
        val word = etWord.text.toString()
        val meaning = etMeaning.text.toString()
        val category = spinnerCategory.selectedItem.toString()
        val difficulty = when (rgDifficulty.checkedRadioButtonId) {
            R.id.rb_easy -> "Easy"
            R.id.rb_medium -> "Medium"
            R.id.rb_hard -> "Hard"
            else -> "Medium"
        }

        if (word.isEmpty() || meaning.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val vocabulary = Vocabulary(word, meaning, category, difficulty)
        VocabularyRepository.addVocabulary(vocabulary)
        Toast.makeText(requireContext(), "Vocabulary added", Toast.LENGTH_SHORT).show()

        // Clear input fields
        etWord.text.clear()
        etMeaning.text.clear()
        rgDifficulty.clearCheck()
        spinnerCategory.setSelection(0)
    }
}
