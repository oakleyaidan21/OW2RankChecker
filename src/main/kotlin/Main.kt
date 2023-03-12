import bot.commands.CommandInterpreter
import dev.kord.core.Kord
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent

suspend fun main() {
    val token = System.getenv("BOT_TOKEN")

    if(token == null) {
        println("Bot token not set")
        return
    }

    val kord = Kord(token)

    CommandInterpreter.setupCommands(kord)

    kord.on<MessageCreateEvent> { // runs every time a message is created that our bot can read
        CommandInterpreter.onMessageCreated(this)
    }

    kord.on<GuildChatInputCommandInteractionCreateEvent> {
        CommandInterpreter.onCommandInvoked(this)
    }

    kord.login {
        // we need to specify this to receive the content of messages
        @OptIn(PrivilegedIntent::class)
        intents += Intent.MessageContent
    }
}