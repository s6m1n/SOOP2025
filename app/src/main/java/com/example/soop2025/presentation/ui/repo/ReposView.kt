package com.example.soop2025.presentation.ui.repo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soop2025.R
import com.example.soop2025.domain.model.repos.Repos
import com.example.soop2025.presentation.ui.component.CoilImage
import com.example.soop2025.presentation.ui.component.RepoInformationText
import com.example.soop2025.presentation.ui.formatMetricSuffix

@Composable
fun ReposView(
    repos: Repos,
    onButtonClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = repos.repoName,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        repos.language?.let {
            Text(text = it)
        }
        Divider(
            color = Color.LightGray,
            modifier = Modifier.padding(vertical = 20.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            RepoInformationText(
                "Star",
                formatMetricSuffix(repos.starCount)
            )
            RepoInformationText(
                "Watchers",
                formatMetricSuffix(repos.watchersCount)
            )
            RepoInformationText(
                "Forks",
                formatMetricSuffix(repos.forksCount)
            )
        }
        Divider(
            color = Color.LightGray,
            modifier = Modifier.padding(vertical = 20.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CoilImage(
                imageUrl = repos.userProfileImageUrl,
                contentDescription = stringResource(id = R.string.user_profile_image),
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
            )
            Text(
                text = repos.userName,
                modifier = Modifier.weight(1F, true),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Button(
                onClick = {
                    onButtonClicked()
                }
            ) {
                Text(text = "more")
            }
        }
        repos.description?.let {
            Divider(
                color = Color.LightGray,
                modifier = Modifier.padding(vertical = 20.dp)
            )
            Text(
                text = "description",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            Text(text = repos.description)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ReposViewPreview() {
    ReposView(repos = dummyRepos) {}
}

private val dummyRepos = Repos(
    id = 3111,
    repoName = "Sallie Wong",
    description = null,
    starCount = 2237,
    watchersCount = 34265,
    forksCount = 2646,
    language = null,
    userName = "Malinda Merritt",
    userProfileImageUrl = "http://www.bing.com/search?q=elit"
)
