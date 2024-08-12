package com.chill.ui.viewmodel

import com.chill.mallang.data.model.response.ApiResponse
import com.chill.mallang.data.repository.remote.UserRepository
import com.chill.mallang.ui.feature.nickname.NickNameUiState
import com.chill.mallang.ui.feature.nickname.NicknameViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class NickNameViewModelTest {
    private lateinit var viewModel: NicknameViewModel
    private val userRepository: UserRepository = mockk() // 가짜 객체 추가

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = NicknameViewModel(userRepository)
    }

    @Test
    fun `닉네임이_중복되었는지_조회한다`() = runTest {
        val nickName = "짜이한한한"
        coEvery { userRepository.checkNickName(nickName) } returns flowOf(ApiResponse.Success(Unit)) // 반환 flow는 ApiResponse.Success로 고정
        viewModel.nicknameState.updateNickname(nickName)
        viewModel.checkNickName()
        advanceUntilIdle() //비동기 작업 끝날떄 까지 대기!
        assertEquals(NickNameUiState.Success(nickName), viewModel.uiState.value)
    }
}