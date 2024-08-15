package com.pg.android_exam.android.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pg.android_exam.android.Model.HttpRepository
import com.pg.android_exam.android.Model.ViewPager
import com.pg.android_exam.android.View.User
import com.pg.android_exam.android.View.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class VMUserData: ViewModel() {

    fun GetPageUserList(listData: ArrayList<UserData>): Flow<PagingData<UserData>>{

        return Pager(
            config = PagingConfig(pageSize = 1),
            initialKey = 1,
            pagingSourceFactory = {ViewPager(listData)})
            .flow.cachedIn(viewModelScope)

    }

    suspend fun FetchUserList(onSuccess: (ArrayList<UserData>) -> Unit, onError: (String) -> Unit) {

        CoroutineScope(Dispatchers.Main).launch {

            flow {
                emit(HttpRepository.getRequestUserMain())
            }.collect{
                when(it){
                    is HttpRepository.Response.Success -> {onSuccess(it.data.userData)}
                    is HttpRepository.Response.Error -> {onError(it.exception)}
                }
            }

        }

    }

    object VMFactoryModel: ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T  = VMUserData() as T
    }

}