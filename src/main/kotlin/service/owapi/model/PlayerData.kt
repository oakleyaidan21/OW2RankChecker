package service.owapi.model

import kotlinx.serialization.Serializable

@Serializable
data class PlayerData(
    val username: String,
    val portrait: String,
    val endorsement: String,
    val private: Boolean,
    val games: Games,
    val playtime: Playtime,
    val competitive: Competitive,
)

@Serializable
data class Competitive(
    val tank: CompetitiveRole,
    val offense: CompetitiveRole,
    val support: CompetitiveRole
)

@Serializable
data class CompetitiveRole(
    val rank: String?,
    val icon: String?
)

@Serializable
data class Playtime(
    val quickplay: String,
    val competitive: String
)

@Serializable
data class Games(
    val quickplay: Game,
    val competitive: Game
)

@Serializable
data class Game(
    val won: Int,
    val played: Int,
    val lost: Int? = null,
    val draw: Int? = null,
    val win_rate: Float? = null
)