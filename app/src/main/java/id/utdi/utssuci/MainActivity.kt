package id.utdi.utssuci

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.utdi.utssuci.ui.theme.UTSSuciTheme
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UTSSuciTheme {
                // Surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewsCard()
                }
            }
        }
    }
}

data class News(val imageResource: Int, val title: String, val content: String)

@Composable
fun NewsCard() {
    var currentNewsIndex by remember { mutableStateOf(0) }
    var isDescriptionVisible by remember { mutableStateOf(false) }

    // List of news to display
    val newsList = listOf(
        News(
            R.drawable.jalanbima,
            "Berita 1",
            "Salah satu menuju Kota Bima Tepain air yang dipinggirnya ada banyak bibir pantai dan teluk juga semenanjung."
        ),
        News(
            R.drawable.lambangbima,
            "Berita 2",
            "Ini merupakan lambang dari Kota Bima."
        ),
        News(
            R.drawable.rumahadatbima,
            "Berita 3",
            "Ini adalah brumah adat bima untuk menyimpan hasil tanam."
        ),
        News(
            R.drawable.tamborabima,
            "Berita 4",
            "Gunung Tambora merupakan gunung tertingggi dulunya 4880mdpl, namun meletus dan mengikis setengah dari gunung tambora."
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            // News Image
            val imagePainter: Painter = painterResource(id = newsList[currentNewsIndex].imageResource)
            Image(
                painter = imagePainter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clickable { isDescriptionVisible = !isDescriptionVisible }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // News Title
            Text(
                text = newsList[currentNewsIndex].title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // News Content
            Text(
                text = newsList[currentNewsIndex].content,
                fontSize = 16.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Action Buttons (Previous, Next)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        // Action for the Previous button
                        currentNewsIndex = (currentNewsIndex - 1).coerceAtLeast(0)
                        isDescriptionVisible = false
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Previous")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        // Action for the Next button
                        currentNewsIndex = (currentNewsIndex + 1) % newsList.size
                        isDescriptionVisible = false
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Next")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewsCardPreview() {
    UTSSuciTheme {
        NewsCard()
    }
}
