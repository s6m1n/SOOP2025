package com.example.soop2025.presentation.repos.component

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soop2025.R
import com.example.soop2025.domain.model.user.User
import com.example.soop2025.presentation.ui.component.CoilImage

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun UserModalBottomSheet(
    user: User,
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
                    imageUrl = user.profileImageUrl,
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
            UserText(
                titleText = stringResource(id = R.string.followers),
                value = user.followers.toString()
            )
            UserText(
                titleText = stringResource(id = R.string.followings),
                value = user.following.toString()
            )
            UserText(
                titleText = stringResource(id = R.string.languages),
                value = user.languages.joinToString(", ")
            )
            UserText(
                titleText = stringResource(id = R.string.repositories),
                value = user.repositoryCount.toString()
            )
            user.bio?.let {
                UserText(
                    titleText = "Bio",
                    value = it
                )
            }
            Spacer(modifier = Modifier.height(70.dp))
        }
    }
}
