package com.example.t4_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.t4_p1.ui.theme.T4_P1Theme
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            T4_P1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen()
                }
            }
        }
    }
}

data class News(
    val title: String,
    val date: String,
    val image: Int
)

val latestNews = listOf(
    News("El presidente de EE.UU. no muestra signos de arrepentimiento...", "febrero 08 - 2024", 1),
    News("Bañarse en la piscina del desierto de Cleopatra", "febrero 10 - 2024", 1),
    News("Gigantes tecnológicos apuestan por la IA", "febrero 12 - 2024", 1)
)

val worldNews = listOf(
    News("El presidente de EE.UU. no muestra signos de arrepentimiento...", "", R.drawable.trump),
    News("Bañarse en la piscina del desierto de Cleopatra", "", R.drawable.cleo),
    News("Gigantes tecnológicos apuestan por la IA", "", R.drawable.tech),
    News("El rover de Marte envía nuevas imágenes", "", R.drawable.marte)
)
@Composable
fun NewsCard(news: News){
    Box(
        modifier = Modifier
            .width(280.dp)
            .height(180.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0xFF6C63FF))
            .padding(16.dp)
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = news.title,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 25.sp
            )
            Text(
                text = news.date,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 15.sp
            )
        }
    }
}

@Composable
fun WorldNewsCard(news: News){
    Box(
        modifier = Modifier
            .height(200.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0xFF6C63FF))
    ){
        Image(
            painter = painterResource(id =news.image),
            contentDescription = news.title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .background(Color.LightGray)
                .align(Alignment.BottomStart)
                .fillMaxWidth()

        ){
            Text(text = news.title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(7.dp))
        }
    }
}

@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        //Buscador
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    text = "Buscar"
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Casita"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            shape = RoundedCornerShape(20.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Black
        )
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Noticias",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
                Box(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .height(5.dp)
                        .width(50.dp)
                        .background(Color(0xFF6C63FF))
                )
            }
            Text(
                text = "Eventos",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = Color.LightGray
            )
            Text(
                text = "Clima",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = Color.LightGray
            )
        }
        Text(
            text = "Últimas noticias",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            modifier = Modifier.padding(20.dp)
            
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            items(latestNews) {news -> NewsCard(news = news)}
        }
        Text(
            text = "Alrededor del mundo",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            modifier = Modifier.padding(20.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp),
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(worldNews){
                news -> WorldNewsCard(news = news)
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    T4_P1Theme {
        HomeScreen()
    }
}
