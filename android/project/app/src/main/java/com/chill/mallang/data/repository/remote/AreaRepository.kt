package com.chill.mallang.data.repository.remote

import com.chill.mallang.data.model.entity.Area
import com.chill.mallang.data.model.entity.AreaDetail
import com.chill.mallang.data.model.entity.TeamList
import com.chill.mallang.data.model.entity.TeamRecords
import com.chill.mallang.data.model.entity.TryCount
import com.chill.mallang.data.model.response.ApiResponse
import kotlinx.coroutines.flow.Flow

interface AreaRepository {
    suspend fun getAreas(): Flow<ApiResponse<List<Area>>>

    suspend fun getOccupationStatus(): Flow<ApiResponse<TeamList>>

    suspend fun getTryCount(areaId: Int): Flow<ApiResponse<TryCount>>

    suspend fun getAreaDetail(
        areaId: Int,
        userTeam: Int,
    ): Flow<ApiResponse<AreaDetail>>

    suspend fun getAreaRecords(
        areaId: Int,
        userId: Int,
    ): Flow<ApiResponse<TeamRecords>>
}
