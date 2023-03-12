import dev.kord.core.Kord
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

    kord.on<MessageCreateEvent> { // runs every time a message is created that our bot can read
        println("Message received! ${message.content}")
        // ignore other bots, even ourselves. We only serve humans here!
        if (message.author?.isBot != false) return@on

        // check if our command is being invoked
        if (message.content != "!ping") return@on

        // all clear, give them the pong!
        message.channel.createMessage("pong!")
    }

    kord.login {
        // we need to specify this to receive the content of messages
        @OptIn(PrivilegedIntent::class)
        intents += Intent.MessageContent
    }
}