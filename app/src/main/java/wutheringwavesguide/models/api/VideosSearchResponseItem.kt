package wutheringwavesguide.models.api

import com.google.gson.annotations.SerializedName

data class VideosSearchResponseItem(
    val id: Long,
    val idLink: String,
    val name: String,
    val link: String,
    val description: String,
    @SerializedName("poster_url")
    val posterUrl: String,
    @SerializedName("cover_url")
    val coverUrl: String,
    val brand: String,
    @SerializedName("is_censored")
    val isCensored: Boolean,
    val tags: List<String>,
    val releasedDate: String
)