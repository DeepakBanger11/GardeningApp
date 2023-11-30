//package com.getstarted.flower.api
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.getstarted.flower.data.PlantJson
//import com.getstarted.flower.utils.Constants.DESCRIPTION
//
//class PlantPagingSource(
//    private val plantApiService: PlantApiService
//) : PagingSource<Int, PlantJson>() {
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlantJson> {
//        try {
//            val nextPageNumber = params.key ?: 1
//            val response = plantApiService.getPlantData(page = nextPageNumber)
//
//            val data = response.data.map { data ->
//                PlantJson(
//                    plantId = data.common_name.toString(),
//                    name = data.common_name.toString(),
//                    wateringInterval = data.watering.toString(),
//                    description = DESCRIPTION,
//                    growZoneNumber = data.cycle.toString(),
//                    imageUrl = data.default_image?.original_url ?: "no image"
//                )
//            }
//
//            val prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1
//            val nextKey = if (data.isEmpty()) null else nextPageNumber + 1
//
//            return LoadResult.Page(
//                data = data,
//                prevKey = prevKey,
//                nextKey = nextKey
//            )
//        } catch (e: Exception) {
//            return LoadResult.Error(e)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, PlantJson>): Int? {
//        // Here you can return the key that you want to start pagination from
//        // Return null if you don't have any specific starting key
//        return null
//    }
//}