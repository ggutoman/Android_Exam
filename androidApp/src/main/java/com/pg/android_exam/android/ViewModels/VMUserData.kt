package com.pg.android_exam.android.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pg.android_exam.android.Model.HttpRepository
import com.pg.android_exam.android.Model.ViewPager
import com.pg.android_exam.android.View.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class VMUserData: ViewModel() {

    fun GetPageUserList(listData: ArrayList<UserData>): Flow<PagingData<UserData>>{

        return Pager(
            config = PagingConfig(pageSize = 1),
            initialKey = 1,
            pagingSourceFactory = {ViewPager(listData)})
            .flow.cachedIn(viewModelScope)

    }

    suspend fun FetchUserList(onResult: (ArrayList<UserData>) -> Unit) {

        CoroutineScope(Dispatchers.Main).launch {

            onResult(
                async(Dispatchers.IO) {

                    HttpRepository.getRequestUserMain().userData

                }.await()
            )

        }

    }

    object VMFactoryModel: ViewModelProvider.NewInstanceFactory() {

        val vmModel: VMUserData = VMUserData()

        override fun <T : ViewModel> create(modelClass: Class<T>): T  = VMUserData() as T
    }

}