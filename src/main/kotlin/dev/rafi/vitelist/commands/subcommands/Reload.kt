package dev.rafi.vitelist.commands.subcommands

import com.velocitypowered.api.command.CommandSource
import dev.rafi.vitelist.storage.Config
import dev.rafi.vitelist.utils.sendMessage

fun reload(source: CommandSource, args: Array<String>) {
    if (!(source.hasPermission("vitelist.commands.reload"))) {
        sendMessage(source, Config.NO_PERMISSION)
        return
    }
    if (Config.reload()) {
        sendMessage(source, Config.RELOAD_SUCCESS)
    } else {
        sendMessage(source, Config.RELOAD_FAIL)
    }
}