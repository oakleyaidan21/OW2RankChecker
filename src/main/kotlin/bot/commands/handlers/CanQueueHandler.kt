package bot.commands.handlers

import bot.commands.CommandHandler
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent

class CanQueueHandler(commandEvent: GuildChatInputCommandInteractionCreateEvent) : CommandHandler(commandEvent) {

    override suspend fun handleCommand(): String {
        return "Handled queue command"
    }
}