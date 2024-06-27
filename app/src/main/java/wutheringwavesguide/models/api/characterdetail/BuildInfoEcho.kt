package wutheringwavesguide.models.api.characterdetail

data class BuildInfoEcho(
    val echo_1_stat: List<Echo1Stat>,
    val echo_1a_stat: List<Echo1Stat>,
    val echo_3_stat: List<Echo1Stat>,
    val echo_3a_stat: List<Echo1Stat>,
    val echo_4_stat: List<Echo1Stat>,
    val echo_sets: List<EchoSet>
)