package wutheringwavesguide.models.api.weapon

data class WeaponResponseItem(
    val bStyle: String,
    val baseAtk: Int,
    val bonusStat: String,
    val description: String,
    val img: String,
    val name: String,
    val rarity: String,
    val rarityDes: String,
    val type: String
)