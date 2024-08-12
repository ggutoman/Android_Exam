package com.pg.android_exam.android.Model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pg.android_exam.android.View.UserData

class ViewPager(val userList: ArrayList<UserData>): PagingSource<Int, UserData>() {

    override fun getRefreshKey(state: PagingState<Int, UserData>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserData> {

        val currentKey = params.key ?: 1
        val prevKey = if (currentKey == 1) null else currentKey - 1
        val nextKey = if (currentKey < userList.size){ currentKey + 1 } else { null }

        return LoadResult.Page(userList, prevKey, nextKey)
    }
}