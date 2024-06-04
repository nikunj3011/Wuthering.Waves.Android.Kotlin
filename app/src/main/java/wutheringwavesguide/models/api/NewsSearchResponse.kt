package wutheringwavesguide.models.api

import com.google.gson.annotations.SerializedName
import wutheringwavesguide.models.db.News

data class NewsSearchResponse(
    @SerializedName("articles")
    val article: Article,
) {

    data class Article(
        @SerializedName("totalResults")
        val total: Int = 0,
        @SerializedName("results")
        val news: List<News>
    )

}