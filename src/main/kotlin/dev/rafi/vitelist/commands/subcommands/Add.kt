package dev.rafi.vitelist.commands.subcommands

import com.velocitypowered.api.command.CommandSource
import dev.rafi.vitelist.database.model.Server
import dev.rafi.vitelist.storage.Config
import dev.rafi.vitelist.utils.sendMessage
import dev.rafi.vitelist.utils.serverExists
import org.jetbrains.exposed.sql.SqlExpressionBuilder.plus
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

fun add(source: CommandSource, args: Array<String>) {
    if (!(source.hasPermission("vitelist.commands.add"))) {
        sendMessage(source, Config.NO_PERMISSION)
        return
    }
    if (args.size == 1) {
        sendMessage(source, Config.WRONG_USAGE)
    } else if (args.size == 2) {
        val playerName = args[1]

        transaction {
            Server.selectAll().forEach { server ->
                if (server[Server.players].contains(playerName)) return@forEach

                Server.update({ Server.id eq server[Server.id] }) {updateServer ->
                    updateServer[players] = players.plus("$playerName,")
                }
            }
        }
        sendMessage(source, Config.ADD_GLOBAL.replace("%user%", playerName))
    } else {
        val playerName = args[1]
        val serverName = args[2]

        if (!serverExists(serverName)) {
            sendMessage(source, Config.SERVER_NOT_FOUND.replace("%server%", serverName))
            return
        }

        transaction {
            Server.select(Server.name, Server.players).where {Server.name eq serverName}.forEach { server ->
                if (server[Server.players].contains(playerName)) return@forEach

                Server.update({ Server.name eq server[Server.name] }) { updateServer ->
                    updateServer[players] = players.plus("$playerName,")
                }
            }
        }
        sendMessage(source, Config.ADD_SERVER.replace("%user%", playerName).replace("%server%", serverName))
    }
}