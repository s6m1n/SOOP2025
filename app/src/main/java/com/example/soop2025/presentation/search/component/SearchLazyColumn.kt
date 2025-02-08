package com.example.soop2025.presentation.search.component

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.soop2025.domain.model.repossearch.ReposSearch

@SuppressLint("ComposableNaming")
@Composable
private fun LazyListState.rememberIsBottomReached(): State<Boolean> {
    return remember(this) {
        derivedStateOf {
            if (layoutInfo.totalItemsCount == 0) {
                return@derivedStateOf false
            } else {
                val lastItem =
                    layoutInfo.visibleItemsInfo.lastOrNull() ?: return@derivedStateOf false
                return@derivedStateOf (lastItem.index == layoutInfo.totalItemsCount - 1)
            }
        }
    }
}

@Composable
fun SearchLazyColumn(
    items: List<ReposSearch>,
    onItemClicked: (String, String) -> Unit,
    onScrollNewPage: () -> Unit
) {
    val listState: LazyListState = rememberLazyListState()
    val isBottomReached by listState.rememberIsBottomReached()

    LaunchedEffect(isBottomReached) { if (isBottomReached) onScrollNewPage() }

    LazyColumn(
        state = listState
    ) {
        itemsIndexed(
            items = items,
            key = { _, repository ->
                repository.id
            }
        ) { idx, repository ->
            SearchItem(
                reposSearch = repository,
                onItemClicked = { onItemClicked(repository.owner?.name ?: "", repository.name) }
            )
            if (idx < items.lastIndex) HorizontalDivider(color = Color.Black, thickness = 1.dp)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchLazyColumnPreview() {
    val repositories = listOf(getDummyRepo(1), getDummyRepo(2), getDummyRepo(3))
    SearchLazyColumn(
        items = repositories,
        onItemClicked = { _, _ -> },
        onScrollNewPage = {}
    )
}
