package com.pg.android_exam.android.Composables

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.pg.android_exam.android.R
import com.pg.android_exam.android.Repositories.HttpRepository
import com.pg.android_exam.android.Theme.MyApplicationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
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
}

@Composable
fun MainScreen() {

    LaunchedEffect(null) {
        CoroutineScope(Dispatchers.Main).launch{
            async {
                println(HttpRepository().getRequestUserMain().toString())
            }
        }
    }

    ConstraintLayout(Modifier.fillMaxSize()){

        val(imgLogo, loadInd, loadProgress) = createRefs()

        Image(painter = painterResource(id = R.drawable.pg), contentDescription = "logoSplash",
            Modifier.constrainAs(imgLogo){
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
        })

        Text(text = "0%",
            Modifier
                .constrainAs(loadInd) {
                    top.linkTo(imgLogo.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(0.dp, 5.dp),
            style = TextStyle(fontFamily = FontFamily.SansSerif, fontSize = 14.sp)
        )

        LinearProgressIndicator(progress = 0f,
            Modifier
                .constrainAs(loadProgress) {
                    top.linkTo(loadInd.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .background(MaterialTheme.colorScheme.primary))
    }

}

@Composable
fun UserListScreen(){

}

@Composable
fun UserDetailScreen(){

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
