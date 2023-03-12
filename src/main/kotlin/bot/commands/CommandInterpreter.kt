package bot.commands

import bot.commands.Constants.Companion.HELP_TEXT
import bot.commands.handlers.CanQueueHandler
import dev.kord.core.Kord
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.rest.builder.interaction.string
import service.owapi.OWAPIClient

class CommandInterpreter(val owapiClient: OWAPIClient) {

        suspend fun setupCommands(kord: Kord) {

            kord.createGlobalChatInputCommand(
                "can_queue",
                "Checks the ranks of each btag and whether they can queue"
            ) {
                string("btag1", "The first player") {
                    required = true
                }
                string("btag2", "The second player") {
                    required = true
                }
                string("btag3", "The third player") {
                    required = false
                }
                string("btag4", "The fourth player") {
                    required = false
                }
                string("btag5", "The fifth player") {
                    required = false
                }

            }
        }

        suspend fun onCommandInvoked(commandEvent: GuildChatInputCommandInteractionCreateEvent) {
            val command = commandEvent.interaction.command
            println("Command invoked: ${command.rootName}")

            // do stuff based on command
            val commandHandler = when(command.rootName) {
                "can_queue" -> CanQueueHandler(commandEvent, owapiClient)
                else -> CommandHandler(commandEvent)
            }

            commandHandler.handle()
        }

        suspend fun onMessageCreated(messageEvent: MessageCreateEvent) {
            val message = messageEvent.message
            println("Message received: ${message.content}")

            // ignore other bots, even ourselves. We only serve humans here!
            if (message.author?.isBot != false) return

            when(message.content) {
                "!ping" -> message.channel.createMessage("pong!")
                "!help" -> message.channel.createMessage(HELP_TEXT)
                else -> println("message did not contain a command")
            }
        }

}