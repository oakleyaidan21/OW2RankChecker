package bot.commands

import dev.kord.core.behavior.interaction.response.PublicMessageInteractionResponseBehavior
import dev.kord.core.behavior.interaction.response.edit
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent

open class CommandHandler(private val commandEvent: GuildChatInputCommandInteractionCreateEvent) {

    suspend fun handle() {
        val message = handleCommand()
        onHandled(message, commandEvent.interaction.deferPublicResponseUnsafe())
    }

    open suspend fun handleCommand() : String {
        println("Handling command!")
        return "Handled command"
    }

    private suspend fun onHandled(message: String, response: PublicMessageInteractionResponseBehavior) {
        response.edit { content = message }
    }
}