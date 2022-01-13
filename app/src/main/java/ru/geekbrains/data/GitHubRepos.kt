package ru.geekbrains.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubRepos(
    @SerializedName("id")
    val id: String? = "asdasda",
    @SerializedName("name")
    val name: String? = "namee",
) : Parcelable