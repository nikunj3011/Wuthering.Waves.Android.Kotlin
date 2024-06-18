package wutheringwavesguide.models.api.echo

import com.google.gson.annotations.SerializedName

data class EchoesResponseItem(
    val No: String,
    @SerializedName("Possible Sonata Effects")
    val PossibleSonataEffects: String,
    val classEcho: String,
    val cooldown: String,
    val cost: String,
    val description: String,
    val echoSets: List<EchoSet>,
    val img: String,
    val name: String
)