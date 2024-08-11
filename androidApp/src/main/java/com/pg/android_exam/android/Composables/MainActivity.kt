package com.pg.android_exam.android.Composables

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pg.android_exam.android.Theme.MyApplicationTheme
import com.pg.android_exam.android.View.UserData
import com.pg.android_exam.android.ViewModels.VMUserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    val mViewModel: VMUserData by viewModels<VMUserData> { VMUserData.VMFactoryModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }

    @Composable
    fun MainScreen() {

        var userList by remember {
            mutableStateOf(ArrayList<UserData>())
        }

        LaunchedEffect(null) {

            withContext(Dispatchers.Main){
                launch {
                    mViewModel.FetchUserList(onResult = {
                        userList = it
                    })
                }
            }
        }

        if (userList.size > 0){

            var itemDetail: UserData by remember {
                mutableStateOf(userList[0])
            }

            UserListScreen(userList = userList, CreateNav(navDetails = itemDetail))

        }

    }

    @Composable
    fun CreateNav(navDetails: UserData): NavHostController{

        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "default"){

            composable(route = "default"){
                MainScreen()
            }

            composable(route = "details"){
                UserDetailScreen(userData = navDetails)
            }

        }

        return navController

    }

    @Composable
    fun UserListScreen(userList: ArrayList<UserData>, navHostController: NavHostController){

        LazyColumn(Modifier.fillMaxWidth(), contentPadding = PaddingValues(16.dp)) {

            items(userList){
                item ->

                ConstraintLayout(
                    Modifier
                        .fillMaxSize()
                        .onFocusEvent {
                            if (!hasWindowFocus()) {
                                navHostController.navigate("details")
                            }
                        }) {

                    val(name, birth, email, phone) = createRefs()

                    Text(text = "${item.name.fname} ${item.name.lname}",
                        Modifier
                            .constrainAs(name) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                            }
                            .width(50.dp)
                            .wrapContentHeight())

                    Text(text = item.birth.birthdate.toString(),
                        Modifier
                            .constrainAs(birth) {
                                start.linkTo(name.end)
                                end.linkTo(parent.end)
                            }
                            .width(50.dp)
                            .wrapContentHeight())

                    Text(text = item.email.toString(),
                        Modifier
                            .constrainAs(email) {
                                top.linkTo(name.bottom)
                                start.linkTo(parent.start)
                            }
                            .width(50.dp)
                            .wrapContentHeight())

                    Text(text = "${item.cellnum} / ${item.phone}",
                        Modifier
                            .constrainAs(phone) {
                                top.linkTo(birth.bottom)
                                start.linkTo(email.end)
                                end.linkTo(parent.end)
                            }
                            .width(50.dp)
                            .wrapContentHeight())

                }

            }
        }

    }

    @Composable
    fun UserDetailScreen(userData: UserData){
        Text(text = userData.toString(), Modifier.fillMaxSize())
    }

    @Preview(showSystemUi = true, showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_NO)
    @Composable
    fun PreviewMainScreen() {
        MyApplicationTheme {
            Surface(modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background) {
                MainScreen()
            }
        }
    }
}
