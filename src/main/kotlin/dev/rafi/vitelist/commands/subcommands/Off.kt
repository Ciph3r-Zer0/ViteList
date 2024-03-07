package dev.rafi.vitelist.commands.subcommands

import com.velocitypowered.api.command.CommandSource
import dev.rafi.vitelist.database.model.Server
import dev.rafi.vitelist.storage.Config
import dev.rafi.vitelist.utils.sendMessage
import dev.rafi.vitelist.utils.serverExists
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

fun off(source: CommandSource, args: Array<String>) {
    if (!(source.hasPermission("vitelist.commands.off"))) {
        sendMessage(source, Config.NO_PERMISSION)
        return
    }
    if (args.size == 1) {
        transaction {
            Server.update({ Server.status eq true }) {
                it[status] = false
            }
        }
        sendMessage(source, Config.DISABLE_GLOBAL)
    } else {
        val serverName = args[1]

        if (!serverExists(serverName)) {
            sendMessage(source, Config.SERVER_NOT_FOUND.replace("%server%", serverName))
            return
        }

        transaction {
            Server.update({ Server.name eq serverName }) {
                it[status] = false
            }
        }
        sendMessage(source, Config.DISABLE_SERVER.replace("%server%", serverName))
    }
}