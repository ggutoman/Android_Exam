package com.pg.android_exam.android.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pg.android_exam.android.Model.HttpRepository
import com.pg.android_exam.android.View.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class VMUserData: ViewModel() {

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