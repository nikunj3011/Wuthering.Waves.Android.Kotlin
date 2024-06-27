package wutheringwavesguide.models.api.characterdetail

data class AscensionMaterials(
    val ascension: Ascension,
    val breakthrough: Breakthrough,
    val common: List<Common>,
    val skill: Skill,
    val skill_other: List<SkillOther>
)