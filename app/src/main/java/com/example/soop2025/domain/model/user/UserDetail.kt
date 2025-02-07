package com.example.soop2025.domain.model.user

data class UserDetail(
    val id: Long,
    val name: String,
    val followers: Int,
    val following: Int,
    val profileImageUrl: String,
    val bio: String?
) {

    companion object {
        fun ofEmpty() = UserDetail(0, "", 0, 0, "", "")
    }
}
