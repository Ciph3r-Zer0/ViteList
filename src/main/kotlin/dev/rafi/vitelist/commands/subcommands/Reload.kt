package dev.rafi.vitelist.commands.subcommands

import com.velocitypowered.api.command.CommandSource
import dev.rafi.vitelist.storage.Config
import dev.rafi.vitelist.utils.ChatUtils

class Reload {
    companion object {
        fun reload(source: CommandSource, args: Array<String>) {
            if (!(source.hasPermission("vitelist.commands.reload"))) {
                ChatUtils.sendMessage(source, Config.NO_PERMISSION)
                return
            }

            if (Config.reload()) {
                ChatUtils.sendMessage(source, Config.RELOAD_SUCCESS)
            } else {
                ChatUtils.sendMessage(source, Config.RELOAD_FAIL)
            }
            println(Config.PREFIX)
        }
    }
}