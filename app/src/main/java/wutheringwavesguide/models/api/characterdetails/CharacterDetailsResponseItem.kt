package wutheringwavesguide.models.api.characterdetails

data class CharacterDetailsResponseItem(
    val activeSkills: ActiveSkills,
    val bStyle: String,
    val bestEchoSet: List<String>,
    val bestEchoStats: String,
    val bestEchoSubStats: String,
    val bestPrimaryEcho: String,
    val bestTeam: List<String>,
    val bestWeapons: List<String>,
    val concertoSkills: ConcertoSkills,
    val element: String,
    val id: String,
    val img: String,
    val intro: String,
    val name: String,
    val passiveSkills: PassiveSkills,
    val resonanceSkills: ResonanceSkills,
    val tag: String,
    val upgradeMaterials: List<String>,
    val weapon: String
)