package com.pg.android_exam.android.Model

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

object CoroutineTask {

    fun ExecuteTask(params: Any, onLoad: () -> Unit, onBackground: (params: Any) -> Any, onFinished:(result: Any) -> Unit ){

        CoroutineScope(Dispatchers.Default).launch {

            onLoad()

            onFinished(

                async(Dispatchers.IO) {

                    onBackground(params)

                }.await()
            )

        }
    }
}