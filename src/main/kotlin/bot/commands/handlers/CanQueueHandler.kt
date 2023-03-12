package bot.commands.handlers

import bot.commands.CommandHandler
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import service.owapi.OWAPIClient
import service.owapi.model.PlayerData

class CanQueueHandler(commandEvent: GuildChatInputCommandInteractionCreateEvent, val owapiClient: OWAPIClient) : CommandHandler(commandEvent) {

    override suspend fun handleCommand(): String {
        val command = commandEvent.interaction.command
        val battleTags = command.strings.entries.map {
            it.value
        }
        var data : PlayerData? = null
        try {
            data = owapiClient.getPlayerData(battleTags[0])
        } catch(e: Exception) {
            println("Error getting player info: $e")
        }
        return "Handled queue command. Data: $data"
    }
}