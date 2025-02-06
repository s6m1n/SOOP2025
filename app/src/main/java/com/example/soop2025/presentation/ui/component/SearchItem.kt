package com.example.soop2025.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soop2025.R
import com.example.soop2025.domain.model.repossearch.ReposSearch
import com.example.soop2025.domain.model.repossearch.ReposSearchOwner
import com.example.soop2025.presentation.ui.formatMetricSuffix

@Composable
fun SearchItem(
    reposSearch: ReposSearch,
    onItemClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClicked()
            }
            .padding(all = 15.dp)
    ) {
        reposSearch.owner?.name?.let {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                CoilImage(
                    imageUrl = reposSearch.owner.profileImageUrl,
                    contentDescription = stringResource(id = R.string.user_profile_image),
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .clip(CircleShape)
                        .size(20.dp)
                )
                Text(text = it)
            }
        }
        Text(
            text = reposSearch.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(5.dp))
        reposSearch.description?.let {
            Text(
                text = it
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .wrapContentHeight(Alignment.CenterVertically)
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_star),
                contentDescription = stringResource(id = R.string.star),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 5.dp)
            )
            Text(
                text = formatMetricSuffix(reposSearch.stargazersCount),
                modifier = Modifier.padding(end = 10.dp)
            )
            reposSearch.language?.let {
                Text(text = reposSearch.language)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchItemPreview() {
    SearchItem(getDummyRepo(1)) {}
}

val dummyOwner = ReposSearchOwner(
    id = 9559,
    name = "Owner Name",
    profileImageUrl = "https://www.google.com/#q=eloquentiam"
)

fun getDummyRepo(id: Int) = ReposSearch(
    id = id,
    name = "Repository Name",
    owner = dummyOwner,
    description = "Repository Description 입니다",
    stargazersCount = 2718,
    language = "Kotlin"
)
