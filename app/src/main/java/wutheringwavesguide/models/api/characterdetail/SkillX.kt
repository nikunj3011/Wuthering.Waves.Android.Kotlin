package wutheringwavesguide.models.api.characterdetail

data class SkillX(
    val category: String,
    val description: Description,
    val multipliers: List<Multiplier>,
    val name: String,
    val type: String
)