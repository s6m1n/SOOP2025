package com.example.soop2025.presentation.ui.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.soop2025.R
import com.example.soop2025.domain.model.user.UserDetail
import com.example.soop2025.domain.model.user.UserRepositories
import com.example.soop2025.presentation.ReposDetailViewModel
import com.example.soop2025.presentation.ui.UiState
import com.example.soop2025.presentation.ui.component.CoilImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserBottomSheet(
    reposDetailViewModel: ReposDetailViewModel,
    userName: String,
    sheetState: SheetState,
    closeBottomSheet: () -> Unit
) {
    val userDetailState: UiState<UserDetail> =
        reposDetailViewModel.userDetailState.collectAsStateWithLifecycle().value
    val userRepoState: UiState<UserRepositories> =
        reposDetailViewModel.userRepoState.collectAsStateWithLifecycle().value

    LaunchedEffect(key1 = Unit) { reposDetailViewModel.fetchUser(userName) }
    LaunchedEffect(key1 = Unit) { reposDetailViewModel.fetchUserRepos(userName) }

    if (userDetailState is UiState.Success && userRepoState is UiState.Success) {
        HandleSuccessUiState(
            userDetailState.data,
            userRepoState.data,
            closeBottomSheet,
            sheetState,
            userName
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun HandleSuccessUiState(
    userDetail: UserDetail,
    userRepo: UserRepositories,
    closeBottomSheet: () -> Unit,
    sheetState: SheetState,
    userName: String
) {
    ModalBottomSheet(
        onDismissRequest = {
            closeBottomSheet()
        },
        dragHandle = {},
        sheetState = sheetState
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.padding(all = 20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CoilImage(
                    imageUrl = userDetail.profileImageUrl,
                    contentDescription = stringResource(id = R.string.user_profile_image),
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(40.dp)
                )
                Text(
                    text = userName,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
            }
            UserDetailText(
                titleText = "Followers",
                value = userDetail.followers.toString()
            )
            UserDetailText(
                titleText = "Following",
                value = userDetail.following.toString()
            )
            UserDetailText(
                titleText = "Languages",
                value = userRepo.languages.joinToString(", ")
            )
            UserDetailText(
                titleText = "Repositories",
                value = userRepo.repositoryCount.toString()
            )
            userDetail.bio?.let {
                UserDetailText(
                    titleText = "Bio",
                    value = it
                )
            }
            Spacer(modifier = Modifier.height(70.dp))
        }
    }
}
