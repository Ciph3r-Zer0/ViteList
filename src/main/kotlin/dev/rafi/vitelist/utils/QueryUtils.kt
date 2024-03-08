package dev.rafi.vitelist.utils

import dev.rafi.vitelist.database.model.Server
import org.jetbrains.exposed.sql.transactions.transaction

fun serverExists(serverName: String): Boolean {
    var exists = false

    transaction {
        exists = Server.select(Server.name).where {Server.name eq serverName}.withDistinct().empty()
    }
    return !exists
}

fun isWhitelisted(serverName: String): Boolean {
    var exists = false

    transaction {
        Server
            .select(Server.status)
            .where {Server.name eq serverName}
            .withDistinct()
            .map {
            exists = it[Server.status]
        }
    }
    return exists
}

fun isPlayerWhitelisted(playerName: String, serverName: String): Boolean {
    var exists = false

    transaction {
        Server
            .select(Server.players)
            .where {Server.name eq serverName}
            .withDistinct()
            .map {
            exists = it[Server.players].contains(playerName)
        }
    }
    return exists
}