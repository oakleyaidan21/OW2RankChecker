package service.owapi

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import service.owapi.model.PlayerData


class OWAPIClient {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }
    val apiUrl = "http://owapi.io/profile/pc/us"

    suspend fun getPlayerData(battleTag: String): PlayerData {
        val convertedTag = battleTag.replace("#", "-")
        return client.get("$apiUrl/$convertedTag").body()
    }
}