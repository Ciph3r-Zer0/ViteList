package dev.rafi.vitelist.commands.subcommands

import com.velocitypowered.api.command.CommandSource
import dev.rafi.vitelist.database.model.Server
import dev.rafi.vitelist.storage.Config
import dev.rafi.vitelist.utils.sendMessage
import dev.rafi.vitelist.utils.serverExists
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

fun remove(source: CommandSource, args: Array<String>) {
    if (!(source.hasPermission("vitelist.commands.remove"))) {
        sendMessage(source, Config.NO_PERMISSION)
        return
    }
    if (args.size == 1) {
        sendMessage(source, Config.WRONG_USAGE)
    } else if (args.size == 2) {
        val playerName = args[1]

        transaction {
            Server.selectAll().forEach { server ->

                Server.update({ Server.id eq server[Server.id] }) {
                    it[players] = server[players].replace("$playerName," , "")
                }
            }
        }
        sendMessage(source, Config.REMOVE_GLOBAL.replace("%user%", playerName))
    } else {
        val playerName = args[1]
        val serverName = args[2]

        if (!serverExists(serverName)) {
            sendMessage(source, Config.SERVER_NOT_FOUND.replace("%server%", serverName))
            return
        }

        transaction {
            Server.select(Server.name, Server.players).where { Server.name eq serverName}.forEach { server ->

                Server.update({ Server.name eq server[Server.name] }) {
                    it[players] = server[players].replace("$playerName,", "")
                }
            }
        }
        sendMessage(source, Config.REMOVE_SERVER.replace("%user%", playerName).replace("%server%", serverName))
    }
}