package com.pg.android_exam.android.Composables

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutBaseScope
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.pg.android_exam.android.Theme.MyApplicationTheme
import com.pg.android_exam.android.View.UserData
import com.pg.android_exam.android.ViewModels.VMUserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

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

        //todo: static variable to get refresh state
        var swipeRefresh by remember {
            mutableStateOf(true)
        }

        //todo: static variable to get user list
        var userList by remember {
            mutableStateOf(ArrayList<UserData>())
        }

        //todo: execute background operation on main thread, when swipe refreshed
        LaunchedEffect(swipeRefresh) {

            if (swipeRefresh){

                withContext(Dispatchers.Main) {
                    launch {
                        mViewModel.FetchUserList(
                            onSuccess = {
                                userList = it
                                swipeRefresh = false
                            },
                            onError = {
                                Log.d(this::class.simpleName, it)
                                swipeRefresh = false
                            })
                    }
                }

            }
        }

        //todo: swipe refresh code block execution, triggers when swipe event happens
        SwipeRefresh(
            state = SwipeRefreshState(swipeRefresh),
            onRefresh = { swipeRefresh = true }) {

            //todo: create nav host view when size is not empty
            if (userList.size > 0){

                //todo: create lazy column's list using Lazy Paging Items
                val lazyItems: LazyPagingItems<UserData> = mViewModel.GetPageUserList(userList).collectAsLazyPagingItems()

                //todo: init variable, observing and store changes on Nav Host Controller
                val navController = rememberNavController()

                //todo: create Nav Host Controller, with nav controller object and default destinations
                NavHost(navController = navController, startDestination = "default"){

                    //todo: default route for nav host
                    composable(route = "default"){
                        UserListScreen(lazyItems, navController)
                    }

                    //todo: detail route for nav host, showing details of selected list
                    composable(route = "details/{index}",
                        listOf(navArgument("index"){ //todo: arguments passed to this composable
                            type = NavType.IntType
                        }
                        )){

                        //todo: content of this nav composable, with the passing param for detail viewing
                        entry -> UserDetailScreen(userData = lazyItems[entry.arguments!!.getInt("index")] as UserData)
                    }

                }

            }

        }

    }

    @SuppressLint("NewApi", "SimpleDateFormat")
    @Composable
    fun UserListScreen(userList: LazyPagingItems<UserData>, navHostController: NavHostController){

        ConstraintLayout {

            val(cardviewheader, cardviewcontent) = createRefs()

            Card(
                Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .constrainAs(cardviewheader) {
                        top.linkTo(parent.top)
                    },
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.tertiary
                ),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 10.dp
                ),
                shape = MaterialTheme.shapes.medium
            ) {

                Text(
                    text = "USER LIST",
                    Modifier
                        .fillMaxSize()
                        .wrapContentHeight(align = Alignment.CenterVertically),
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = TextUnit(18f, TextUnitType.Sp),
                        color = MaterialTheme.colorScheme.background
                    ))
            }

            Card(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(10.dp, 100.dp, 10.dp, 0.dp)
                    .constrainAs(cardviewcontent) {
                        top.linkTo(cardviewheader.bottom)
                        bottom.linkTo(cardviewheader.bottom)
                    },
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 10.dp
                ),
                shape = MaterialTheme.shapes.large
            ) {

                LazyColumn(
                    Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(5.dp)) {

                    items(userList.itemCount){
                            item ->

                        ConstraintLayout(
                            Modifier
                                .fillMaxWidth()) {

                            val(row1, row2, btn_view) = createRefs()

                            val userData: UserData = userList[item]!!

                            Row(
                                modifier = Modifier
                                    .constrainAs(row1){
                                        start.linkTo(parent.start)
                                    }
                                    .width(200.dp)
                                    .height(IntrinsicSize.Min)
                            ) {

                                // use the material divider
                                Divider(
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(1.dp)
                                )

                                Column {

                                    Text(
                                        text = "${userData.name.fname} ${userData.name.lname}",
                                        Modifier
                                            .padding(5.dp, 0.dp, 2.dp, 2.dp)
                                            .fillMaxWidth()
                                            .wrapContentHeight())

                                    Text(
                                        text = userData.email.toString(),
                                        Modifier
                                            .padding(5.dp, 2.dp, 2.dp, 0.dp)
                                            .fillMaxWidth()
                                            .wrapContentHeight())

                                }
                            }

                            Row(
                                modifier = Modifier
                                    .constrainAs(row2){
                                        start.linkTo(row1.end)
                                    }
                                    .width(200.dp)
                                    .height(IntrinsicSize.Min)
                            ) {

                                Column {

                                    Text(
                                        text = DateTimeFormatter.ofPattern("MMMM dd, yyyy")
                                            .format(
                                                OffsetDateTime
                                                    .parse(userData.birth.birthdate.toString())
                                            ),
                                        Modifier
                                            .padding(0.dp, 0.dp, 0.dp, 2.dp)
                                            .fillMaxWidth()
                                            .wrapContentHeight())

                                    Text(text = "${userData.cellnum} / ${userData.phone}",
                                        Modifier
                                            .fillMaxWidth()
                                            .wrapContentHeight()
                                    )

                                }
                            }

                            TextButton(onClick = {
                                //todo: detail viewing button event, navigate to detail screen
                                navHostController.navigate("details/${item}")
                            },
                                Modifier
                                    .constrainAs(btn_view) {
                                        top.linkTo(row1.bottom)
                                        start.linkTo(row1.start)
                                        end.linkTo(row2.end)
                                    }
                                    .fillMaxWidth()
                                    .padding(0.dp, 2.dp)) {

                                Text(text = "View Details",
                                    style = TextStyle(
                                        textAlign = TextAlign.Center,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                )

                            }

                        }

                    }
                }

            }
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun UserDetailScreen(userData: UserData){
        ConstraintLayout {

            val(appbar, content) = createRefs()

            CenterAlignedTopAppBar(

                title = {
                    Text(text = "User Detail")
                },
                Modifier.constrainAs(appbar){
                    top.linkTo(parent.top)
                }
                ,
                colors = TopAppBarDefaults.topAppBarColors(
                    titleContentColor = Color.LightGray,
                    containerColor = MaterialTheme.colorScheme.tertiary
                )
            )

            Text(
                text = userData.toString(),
                Modifier
                    .fillMaxSize()
                    .constrainAs(content) {
                        top.linkTo(appbar.bottom)
                    }
                    .padding(0.dp, 5.dp))

        }
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
