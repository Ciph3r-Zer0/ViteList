package dev.rafi.vitelist.commands.subcommands

import com.velocitypowered.api.command.CommandSource
import dev.rafi.vitelist.database.model.Server
import dev.rafi.vitelist.storage.Config
import dev.rafi.vitelist.utils.sendMessage
import dev.rafi.vitelist.utils.serverExists
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

fun on(source: CommandSource, args: Array<String>) {
    if (!(source.hasPermission("vitelist.commands.on"))) {
        sendMessage(source, Config.NO_PERMISSION)
        return
    }
    if (args.size == 1) {
        transaction {
            Server.update({ Server.status eq false }) {
                it[status] = true
            }
        }
        sendMessage(source, Config.ENABLE_GLOBAL)
    } else {
        val serverName = args[1]

        if (!serverExists(serverName)) {
            sendMessage(source, Config.SERVER_NOT_FOUND.replace("%server%", serverName))
            return
        }

        transaction {
            Server.update({ Server.name eq serverName }) {
                it[status] = true
            }
        }
        sendMessage(source, Config.ENABLE_SERVER.replace("%server%", serverName))
    }
}