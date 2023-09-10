package com.example.test1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.test1.bmi.bmiView
import com.example.test1.bmi.BmiViewModel
import com.example.test1.ui.theme.Test1Theme

class MainActivity : ComponentActivity() {

    private val bmiViewModel by viewModels<BmiViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Test1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    // 体重
                    Text(
                        text = "体重（kg）",
                        color = Color.Red,
                        fontSize =  15.sp,
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    TextField(
                        value = bmiViewModel.weight,
                        placeholder = {Text(text="70")},
                        onValueChange = { bmiViewModel.weight = it }
                    )

                    //mainVisual()
//                    bmiView()

                    /*
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ScreenRoute.mainScreen.route,
                    ){

                        // メイン画面
                        composable(route = ScreenRoute.mainScreen.route){
                            Text(text = "メイン画面")
                            mainVisual(navController)
                        }

                        // bmi計算画面
                        composable(
                            route = ScreenRoute.calcBmiScreen.route + "/{param}",
                            arguments = listOf(
                                    navArgument("param") { type = NavType.StringType },
                            )
                        )
                        { backStackEntry ->
                            val param = backStackEntry.arguments?.getString("param") ?: ""

                            Text(text = "BMI")
                            bmiView(navController, param)

                        }

                    }

                     */

                }
            }
        }
    }
}

@Composable
fun mainVisual(navController: NavController) {

    //　全体
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
    ) {




        // プロフ
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.img_profile),
                contentDescription = "プロフ画像",
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(100.dp))
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "山田 太郎",
                color = Color.Gray,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "職業")

        }

        // 会社等
        Column(
            modifier = Modifier.padding(20.dp, 0.dp)
        ) {
            Text(
                text = "会社名",
                fontSize = 23.sp,
                fontWeight = FontWeight.ExtraBold,
            )

            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "部署 グループ名",
                color = Color.Gray,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(13.dp))
            // メール
            Row() {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "メールアイコン"
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Email")
            }

            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "example@email.com",
//                textDecoration = TextDecoration.Underline,
            )

            Divider(
                modifier = Modifier.clip(RoundedCornerShape(100.dp)),
                thickness = 2.dp,
            )

            Spacer(modifier = Modifier.height(10.dp))


            var isShowDetail by remember{ mutableStateOf(false) }
            // 詳細ボタン

            Button(
                modifier = Modifier
                    .fillMaxWidth(),
//                    .background(color = Color.Magenta) // ボタンに関してはmodifierじゃなくてcolorsで設定
//                    .clip(RoundedCornerShape(10.dp)),
                colors = ButtonDefaults.buttonColors(Color.Magenta),
                onClick = { isShowDetail = !isShowDetail }
            ) {
                Text(
                    text = "詳細を表示",
                    color = Color.White,

                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            if (isShowDetail) {
               // 趣味
                contentRow(Icons.Default.Favorite, "趣味アイコン", "趣味", "キックボクシング")
                Spacer(modifier = Modifier.height(5.dp))
                // 居住地
                contentRow(Icons.Default.LocationOn, "居住地アイコン", "居住地", "東京都○○区○○")
            }
//            Row(){
//
//                Icon(
//                    imageVector = Icons.Default.Favorite,
//                    contentDescription = "趣味アイコン"
//                )
//                Spacer(modifier = Modifier.width(5.dp))
//                Text(text = "趣味:")
//                Spacer(modifier = Modifier.width(10.dp))
//                Text(text = "キックボクシング")
//            }


            // 画面遷移
            var param :String = "受け渡し用テキスト"
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate(ScreenRoute.calcBmiScreen.route + "/$param")
                }
            )
            {
                Text(text = "画面遷移")
            }

        } // 会社等　END

    } // All END
}

@Composable
fun contentRow(
    icon: ImageVector,
    iconDesc: String,
    title: String,
    detail: String,
) {
    Row(
        modifier = Modifier
            .background(color = Color.LightGray.copy(alpha = 0.3f))
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconDesc,
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = "${title}:")
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = detail)
    }
}

