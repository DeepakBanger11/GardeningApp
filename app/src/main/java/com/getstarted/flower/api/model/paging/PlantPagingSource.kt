package com.getstarted.flower.api.model.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.getstarted.flower.api.PlantApiService
import com.getstarted.flower.api.model.Data


class PlantPagingSource(private val repository: PlantApiService) :
    PagingSource<Int, Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val nextPage = params.key ?: 1
            val response = repository.getPlantData(nextPage)
            LoadResult.Page(
                data = response.data,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}

