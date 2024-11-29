package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}


@Composable
fun LemonadeButtonAndImage(){
    var eventPhase by remember { mutableStateOf(0) }
    var lemonadeTapNums = (2..4).random()
    var lemonadeTapCount by remember { mutableStateOf(0) }
    val imageArrayNumber = 1
    val detailsArrayNumber = 0
    val resourceNames = arrayOf(
        arrayOf(R.string.lemon_tree_select,R.drawable.lemon_tree),
        arrayOf(R.string.lemon_squeeze,R.drawable.lemon_squeeze),
        arrayOf(R.string.lemonade_drink,R.drawable.lemon_drink),
        arrayOf(R.string.glass_start,R.drawable.lemon_restart),
    )
    val maxPhaseNums=3
    var texts = resourceNames[eventPhase][detailsArrayNumber]
    var image = resourceNames[eventPhase][imageArrayNumber]
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ){
            Image(
                painter= painterResource(image),
                contentDescription = "aaa",
                modifier = Modifier.wrapContentSize().clickable {
                    /*
                    * レモネード画像をTapすると画像が変わる
                    * レモンの場合は 複数回Tapした後に画像が変わる
                    * 空のグラスの場合は 最初から始める
                    * */

                    eventPhase = when(eventPhase){
                        1 -> 1 //レモンを絞るフェーズの時は何もしない
                        maxPhaseNums -> 0 //からのグラスの時は最初から
                        else -> eventPhase+1
                    }

                    /*
                    * レモンを絞る処理
                    * */
                    if(eventPhase == 1){
                        if(lemonadeTapCount >= lemonadeTapNums){
                            eventPhase +=1
                            lemonadeTapCount = 0
                        }else{
                            lemonadeTapCount +=1
                        }
                    }

                }
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(text=stringResource(texts))
        }

    }
}


@Preview
@Composable
fun LemonadeApp(){
    LemonadeButtonAndImage()
}
