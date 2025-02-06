package com.example.soop2025.presentation.ui.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.soop2025.domain.model.repossearch.ReposSearch

@Composable
fun SearchLazyColumn(
    repositories: List<ReposSearch>,
    onItemClicked: (String, String) -> Unit
) {
    LazyColumn {
        itemsIndexed(
            items = repositories,
            key = { _, repository ->
                repository.id
            }
        ) { idx, repository ->
            SearchItem(
                reposSearch = repository,
                onItemClicked = { onItemClicked(repository.owner?.name ?: "", repository.name) }
            )
            if (idx < repositories.lastIndex) Divider(color = Color.Black, thickness = 1.dp)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchLazyColumnPreview() {
    val repositories = listOf(getDummyRepo(1), getDummyRepo(2), getDummyRepo(3))
    SearchLazyColumn(
        repositories
    ) { _, _ -> }
}
