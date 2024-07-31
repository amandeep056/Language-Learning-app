// VocabularyRepository.kt
package com.example.languagelearningapp

object VocabularyRepository {
    private val vocabularyList = mutableListOf<Vocabulary>()

    fun addVocabulary(vocabulary: Vocabulary) {
        vocabularyList.add(vocabulary)
    }

    fun getVocabularyList(): List<Vocabulary> {
        return vocabularyList
    }
}
