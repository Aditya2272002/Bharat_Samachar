package com.example.bharatsamachar.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bharatsamachar.R
import com.example.bharatsamachar.models.TopNewsArticles
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun TopNews(navController: NavController, articles: List<TopNewsArticles>) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Top News", fontWeight = FontWeight.SemiBold)
        LazyColumn {
            items(articles.size) { index ->
                TopNewsItem(article = articles[index],
                    onNewsClick = { navController.navigate(("Detail/$index")) })
            }
        }
    }
}

@Composable
fun TopNewsItem(article: TopNewsArticles, onNewsClick: () -> Unit) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .padding(8.dp)
            .clickable { onNewsClick() }
    ) {
        CoilImage(
            imageModel = article.urlToImage,
            contentScale = ContentScale.Crop,
            error = ImageBitmap.imageResource(R.drawable.breaking_news),
            placeHolder = ImageBitmap.imageResource(R.drawable.breaking_news)
        )

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(top = 16.dp, start = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(article.publishedAt!!, color = Color.White, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(90.dp))
            Text(text = article.title!!, color = Color.White, fontWeight = FontWeight.SemiBold)
        }


    }
}


@Preview(showBackground = true)
@Composable
fun TopNewsPreview() {
    //TopNews(rememberNavController())

}