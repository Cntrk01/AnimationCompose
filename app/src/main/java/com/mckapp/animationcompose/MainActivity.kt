package com.mckapp.animationcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mckapp.animationcompose.navigation.AppNavigation
import com.mckapp.animationcompose.ui.theme.AnimationComposeTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    //modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //AnimatedVisibility()
                    //AnimatedContent()
                    //AnimatedContentSize()
                    //AnimatedFloat()
                    //AnimateColorAsState()
                    //AnimateDpAsState()
                    //RepeatAnAnimation()
                    //IncreaseAnimation()
                    //AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AnimatedVisibility() {
    var isVisible by remember {
        mutableStateOf(false)
    }

    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { isVisible = !isVisible }) {
            if (!isVisible) Text(text = "SHOW") else Text(text = "GONE")
        }

        AnimatedVisibility(
            visible = isVisible,
            enter = scaleIn(tween(500)), //slideInVertically(initialOffsetY = { it })
            exit = scaleOut(tween(500))  //slideOutVertically(targetOffsetY = { it }), x y ekseninde aşağı yukarı istersek
        )
        {
            Box(
                modifier =
                Modifier
                    .size(300.dp)
                    .background(Color.Magenta)
            )
        }
    }
}

@Composable
fun AnimatedContent() {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { isExpanded = !isExpanded }) {
            Text(text = if (isExpanded) "Collapse" else "Expand")
        }

        Box(
            modifier = Modifier
                .animateContentSize(tween(500))
                .background(Color.DarkGray)
                .fillMaxWidth()
        ) {
            androidx.compose.animation.AnimatedContent(targetState = isExpanded, label = "") {
                if (it) {
                    Text(
                        text = "TEXT\nTEXT\nTEXTEXTEETETETETETETT\nTEXTEXTEETETETETETETTTEXTEXTEETETETETETETT",
                        color = Color.White,
                        modifier = Modifier.padding(10.dp)
                    )
                } else {
                    Text(
                        text = "Collapsed",
                        color = Color.White,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AnimatedContentSize() {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(if (isExpanded) 500.dp else 70.dp)
            .background(Color.Cyan)
            .clickable { isExpanded = !isExpanded }
            .animateContentSize(),
    ) {
        Column {
            Text(
                text = if (!isExpanded) "Click To Expand" else "Expanded Content",
                color = Color.White,
                modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )
        }

        AnimatedVisibility(visible = isExpanded) {
            Text(
                text = "Expanded Content Expanded Content Expanded Content Expanded Content Expanded Content \nExpanded Content Expanded Content Expanded Content Expanded Content Expanded Content",
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun AnimatedFloat() {
    var isRotated by remember { mutableStateOf(false) }
    var rotationAngle by remember { mutableStateOf(0f) }

    val rotationSpeed = 60f

    LaunchedEffect(isRotated) {
        while (isRotated) {
            rotationAngle += rotationSpeed / 60f
            delay(16)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Automatically Rotating Image")

        // Resim
        Button(
            onClick = { isRotated = !isRotated },
            modifier = Modifier
                .size(100.dp)
                .padding(vertical = 16.dp)
        ) {
            Text(text = if (isRotated) "Stop Rotation" else "Start Rotation")
        }

        // Dönen resim
        Box(
            modifier = Modifier.size(200.dp),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.foundation.Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "",
                modifier = Modifier.graphicsLayer(rotationZ = rotationAngle)
            )
        }
    }

    //   var isRotated by remember {
    //        mutableStateOf(false)
    //    }
    //
    //    val rotationAngle by animateFloatAsState(
    //        targetValue = if (isRotated) 360f else 0f,
    //        label = "",
    //        animationSpec = tween(1000)
    //    )
    //
    //    Column(
    //        modifier = Modifier
    //            .fillMaxSize()
    //            .padding(16.dp),
    //        horizontalAlignment = Alignment.CenterHorizontally
    //    ) {
    //        Image(
    //            modifier = Modifier
    //                .size(200.dp)
    //                .clickable { isRotated = !isRotated }
    //                .graphicsLayer(rotationZ = rotationAngle),
    //            painter = painterResource(id = R.drawable.ic_launcher_background),
    //            contentDescription = ""
    //        )
    //
    //        Button(onClick = { isRotated =!isRotated },modifier = Modifier
    //            .width(200.dp)
    //            .padding(top = 50.dp)) {
    //            Text(text = "Rotate ")
    //        }
    //    }
    //}
}

@Composable
fun AnimateColorAsState() {
    var isColorChanged by remember {
        mutableStateOf(false)
    }

    val targetColor by animateColorAsState(
        targetValue = if (isColorChanged) Color.Blue else Color.Magenta,
        label = "",
        animationSpec = tween(1000)
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .height(200.dp)
                .fillMaxSize(),
            border = BorderStroke(5.dp, Color.Red),
            colors = CardDefaults.cardColors(targetColor)
        ) {

        }

        Button(
            onClick = { isColorChanged = !isColorChanged },
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = "Switch Color")
        }

    }
}

@Composable
fun AnimateDpAsState() {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    val size by animateDpAsState(
        targetValue = if (isExpanded) 250.dp else 100.dp,
        label = "",
        animationSpec = tween(1000)
    )

    Column(
        modifier = Modifier.padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .border(5.dp, Color.Gray),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = ""
        )

        Button(
            modifier = Modifier
                .padding(50.dp)
                .width(300.dp),
            onClick = { isExpanded = !isExpanded }) {
            Text(text = "Animate To Dp As State")
        }
    }

}

//Modifierde paddding kısımına animatePadding vererek bunu da yapabiliriz.
@Composable
fun AnimationPadding() {
    val clicked by remember {
        mutableStateOf(false)
    }
    val animatedPadding by animateDpAsState(
        targetValue = if (clicked) 35.dp else 0.dp,
        label = ""
    )
}

@Composable
fun RepeatAnAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite")
    //Bunu aslında Z rotasyonunda dönüş işlemi için yaptık
    val float by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val color by infiniteTransition.animateColor(
        initialValue = Color.Gray,
        targetValue = Color.Blue,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "Color"
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Rounded.Star,
            contentDescription = "",
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer {
                    rotationZ = float
                },
            tint = Color.Blue.copy(alpha = 0.8f)
        )

        Spacer(modifier = Modifier.size(16.dp))

        Box(modifier = Modifier
            .size(200.dp)
            .drawBehind {
                drawRect(color)
            })

    }
}

@Composable
fun IncreaseAnimation() {
    var count by remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        androidx.compose.animation.AnimatedContent(
            targetState = count,
            label = "",
            transitionSpec = {
                slideInVertically( //- vererek animasyonun aşağı doğru hareket edeceğini belirledik
                    initialOffsetY = { -it },
                    animationSpec = tween(500)
              //togetherWith fonksiyonu ise slideInVertically ve slideOutVertically animasyonlarını bir araya getirir. Bu sayede, bileşen ekrana kaydırılarak görünürken aynı anda diğer bileşen ekrandan kaydırılarak kaybolabilir.
                ) togetherWith slideOutVertically( // Bu fonksiyon, bir bileşeni ekrandan kaydırarak kaybolmasını sağlar. targetOffsetY parametresi, bileşenin kaybolurken hangi yüksekliğe kadar (ekranın dışına) kaydırılacağını belirtir
                    targetOffsetY = { it },
                    animationSpec = tween(500) //1000 yapıp buradaki iti -it yaptığımda sayılar çarpışma efektine uğruyor
                )
            }) { targetCount ->
            Text(
                text = targetCount.toString(),
                fontFamily = FontFamily.Serif,
                fontSize = 200.sp
            )
            Spacer(modifier = Modifier.size(8.dp))

        }

        Button(onClick = {count++ }) {
            Text(text = "Add Number", fontSize = 22.sp)
        }
    }
}
