package com.lulu.newsapp.ui.view.components

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lulu.newsapp.model.Article
import com.lulu.newsapp.utils.getCleanedTitle
import com.lulu.newsapp.utils.getFormattedDate


@Composable
fun NewsList(articleList: List<Article>) {

    Log.d("teste", "$articleList")
    LazyColumn(content = {
        items(articleList.size, itemContent = {
            NewsItem(item = articleList[it])
        })

    })

}

@Composable
fun NewsItem(item: Article) {
    val title = getCleanedTitle(item.title)
    val authorAndDate = if (item.publishedAt.isEmpty()) item.author else item.author.plus(
        " | ${
            getFormattedDate(item.publishedAt)
        }"
    )

    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .clickable {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.url)))
            },

        ) {

        Row(modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically){
        Text(
            modifier = Modifier.padding(end = 20.dp).fillMaxWidth(0.75f),
            text = title,
            style = MaterialTheme.typography.titleSmall
        )

        if(item.urlToImage != null) {
            AsyncImage(
                model = item.urlToImage,
                contentDescription = "news image",
                alignment = Alignment.Center,
                modifier = Modifier
                    .padding(end = 20.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
    }


        Text(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
            text = authorAndDate,
            style = MaterialTheme.typography.bodySmall
        )



        Divider(
            color = Color.Black,
            modifier = Modifier

                .height(1.dp)
                .fillMaxWidth()
        )
    }
}