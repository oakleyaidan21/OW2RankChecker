package bot.commands

class Constants {
    companion object {
        const val HELP_TEXT = """
            Commands: 
                - !queue [btag1] [btag2] ... [btag5]
                 - checks the SR of each btag and returns why or why not they can queue together
                - !help
                 - get a list of commands
        """
    }
}