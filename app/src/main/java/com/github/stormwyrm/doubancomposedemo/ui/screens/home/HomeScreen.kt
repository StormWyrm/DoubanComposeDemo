package com.github.stormwyrm.doubancomposedemo.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.stormwyrm.doubancomposedemo.RankDetailActivity
import com.github.stormwyrm.doubancomposedemo.data.HomeTopRank
import com.github.stormwyrm.doubancomposedemo.data.HomeTypeRankItem
import com.github.stormwyrm.doubancomposedemo.data.homeTopRankList
import com.github.stormwyrm.doubancomposedemo.data.homeTypeRankList
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HomeScreen() {
    rememberSystemUiController().setStatusBarColor(Color.Transparent, darkIcons = true)
    LazyColumn(Modifier) {

        item {
            Spacer(
                modifier = Modifier
                    .statusBarsHeight()
                    .fillMaxWidth()
            )
        }

        item {
            TopRank()
        }

        item {
            YearRank()
        }

        item {
            TypeRankTitle()
        }

        items(homeTypeRankList.chunked(2)) {
            TypeRankRow(row = it)
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }

    }

}

@Composable
fun TopRank() {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .height(48.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = "电影榜单",
            color = Color.Black,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.align(Alignment.CenterStart)
        )
    }

    LazyRow {
        items(homeTopRankList) { item ->
            TopRankItem(item)
        }
    }
}

@Composable
fun TopRankItem(item: HomeTopRank) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .size(180.dp, 220.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                RankDetailActivity.navigate(context = context)
            }
    ) {
        Image(
            painter = rememberCoilPainter(request = item.imgUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(item.startColor), Color(item.endColor)),
                        start = Offset(0f, Float.POSITIVE_INFINITY),
                        end = Offset(Float.POSITIVE_INFINITY, 0f)
                    )
                )
                .padding(8.dp)
        ) {
            Text(
                text = item.title,
                color = Color.White,
                style = MaterialTheme.typography.subtitle1
            )

            Text(text = "豆瓣电影", color = Color.White, style = MaterialTheme.typography.overline)

            Spacer(modifier = Modifier.height(60.dp))

            item.list.forEach {
                Text(
                    text = it.title,
                    color = Color.White,
                    style = MaterialTheme.typography.overline,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = it.score,
                    color = Color(0xffffac2d),
                    style = MaterialTheme.typography.overline,
                    modifier = Modifier.padding(12.dp, 0.dp, 0.dp, 2.dp)
                )
            }
        }
    }
}

@Composable
fun YearRank() {
    Text(
        text = "年度电影榜单",
        color = Color.Black,
        style = MaterialTheme.typography.h5,
        modifier = Modifier.padding(16.dp, 8.dp)
    )

    val list = listOf("2020", "2019", "2018", "2017", "2016", "2015", "2014")
    var selectIndex by remember { mutableStateOf(0) }

    YearRow(list = list, selectIndex = selectIndex, onSelectListener = {
        selectIndex = it
    })
    YearRankItems(year = list[selectIndex])
}

@Composable
fun YearRow(list: List<String>, selectIndex: Int, onSelectListener: (Int) -> Unit) {
    LazyRow(contentPadding = PaddingValues(4.dp, 8.dp, 8.dp, 0.dp)) {
        itemsIndexed(list) { index, item ->
            val backgroundColor = if (index == selectIndex) 0xFFE0F7E8 else 0xFFF6F6F6
            val contentColor = if (index == selectIndex) 0xFF008E00 else 0xFF000000
            Text(
                text = item,
                color = Color(contentColor),
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .padding(8.dp, 0.dp, 0.dp, 0.dp)

                    .clip(RoundedCornerShape(5.dp))
                    .clickable {
                        onSelectListener.invoke(index)
                    }
                    .background(Color(backgroundColor))
                    .padding(7.dp, 4.dp)

            )
        }
    }
}

@Composable
fun YearRankItems(year: String) {
    val titleList = listOf("华语电影", "外语电影", "冷门佳片")
    val colorList = listOf(0xff442526, 0xff424243, 0xff442C2F)
    val context = LocalContext.current

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
    ) {
        titleList.forEachIndexed { index, s ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .height(120.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(colorList[index])),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${year}高分榜",
                        color = Color.White,
                        style = MaterialTheme.typography.body1
                    )
                    Text(
                        text = titleList[index],
                        color = Color.White,
                        style = MaterialTheme.typography.h5
                    )
                }
            }
        }
    }
}


@Composable
fun TypeRankTitle() {
    Text(
        text = "电影类型榜单",
        color = Color.Black,
        style = MaterialTheme.typography.h5,
        modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 0.dp)
    )
}

@Composable
fun TypeRankRow(row: List<HomeTypeRankItem>) {
    val first = row.first()
    val second = row.last()
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 16.dp, 0.dp, 0.dp)
    ) {
        TypeRankItem(first)
        TypeRankItem(second)
    }
}

@Composable
fun TypeRankItem(item: HomeTypeRankItem) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .size(180.dp, 180.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                RankDetailActivity.navigate(context = context)
            }
    ) {
        Image(
            painter = rememberCoilPainter(request = item.imgUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(item.startColor), Color(item.endColor)),
                        start = Offset(0f, Float.POSITIVE_INFINITY),
                        end = Offset(Float.POSITIVE_INFINITY, 0f)
                    )
                )
                .padding(8.dp)

        ) {
            Text(
                text = item.subTitle,
                color = Color.White,
                style = MaterialTheme.typography.subtitle1
            )
            Text(text = item.title, color = Color.White, style = MaterialTheme.typography.h6)

            Text(
                text = item.topOne,
                color = Color.White,
                style = MaterialTheme.typography.overline,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(0.dp, 70.dp, 0.dp, 0.dp)
            )
            Text(
                text = item.topTwo,
                color = Color.White,
                style = MaterialTheme.typography.overline,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = item.topThree,
                color = Color.White,
                style = MaterialTheme.typography.overline,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}