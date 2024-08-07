package com.chill.mallang.ui.feature.word

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chill.mallang.data.model.response.ApiResponse
import com.chill.mallang.data.repository.local.DataStoreRepository
import com.chill.mallang.data.repository.remote.StudyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordNoteViewModel
    @Inject
    constructor(
        private val studyRepository: StudyRepository,
        private val dataStoreRepository: DataStoreRepository,
    ) : ViewModel() {
        var state by mutableStateOf(WordNoteState())
            private set

        init {
            loadWords()
        }

        // 처음 단어장 정보 불러오기 api
        fun loadWords() {
            viewModelScope.launch {
                dataStoreRepository.getUserId().collect { userId ->
                    if (userId != null) {
                        Log.d("nakyung", "userId: $userId")
                        studyRepository
                            .getWordList(userId)
                            .collectLatest { response ->
                                when (response) {
                                    is ApiResponse.Success -> {
                                        Log.d("nakyung", "response: ${response.body}")
                                        state =
                                            state.copy(
                                                wordList = response.body ?: emptyList(),
                                            )
                                    }

                                    is ApiResponse.Error -> {
                                        // api 통신 실패
                                        Log.d("nakyung", response.errorMessage)
                                    }

                                    ApiResponse.Init -> {}
                                }
                            }
                    }
                }
            }
        }

        fun loadIncorrectWords() {
            viewModelScope.launch {
                state =
                    state.copy(
                        wordList =
                            arrayListOf(
                                Word.IncorrectWord(
                                    studyId = 1,
                                    word = "우리나라의 경제는 그동안 세계에 유례가 없을 정도로 __할 만한 성장을 이루었다.",
                                ),
                            ),
                    )
            }
        }
    }
