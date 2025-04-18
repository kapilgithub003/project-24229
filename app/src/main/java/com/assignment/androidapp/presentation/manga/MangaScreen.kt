package com.assignment.androidapp.presentation.manga

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.foundation.shape.RoundedCornerShape
import coil.compose.rememberAsyncImagePainter
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.assignment.androidapp.presentation.home.manga.MangaViewModel

@Composable
fun MangaScreen(viewModel: MangaViewModel, navController: NavController) {
    val mangaItems = viewModel.mangaFlow.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(mangaItems.itemCount) { index ->
                val manga = mangaItems[index]
                manga?.let {
                    Column(
                        modifier = Modifier
                            .clickable {
                                navController.navigate("mangaDetail/${manga.id}")
                            }
                            .fillMaxWidth()
                            .padding(start = 5.dp, top = 10.dp, bottom = 10.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(it.thumb),
                            contentDescription = it.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .clip(RoundedCornerShape(15.dp))
                        )
                    }
                }
            }
            if (mangaItems.loadState.append is LoadState.Loading) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(modifier = Modifier.size(30.dp))
                    }
                }
            }
            if (mangaItems.loadState.append is LoadState.Error) {
                val error = (mangaItems.loadState.append as LoadState.Error).error
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Text(
                        text = "Error loading more: ${error.localizedMessage ?: "Unknown error"}",
                        modifier = Modifier.padding(16.dp),
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
        if (mangaItems.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(40.dp)
            )
        }
        if (mangaItems.loadState.refresh is LoadState.Error) {
            val error = (mangaItems.loadState.refresh as LoadState.Error).error
            Text(
                text = "Error: ${error.localizedMessage ?: "Unknown error"}",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        if (mangaItems.itemCount == 0 &&
            mangaItems.loadState.refresh !is LoadState.Loading &&
            mangaItems.loadState.refresh !is LoadState.Error
        ) {
            Text(
                text = "No data available",
                color = Color.White,
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

