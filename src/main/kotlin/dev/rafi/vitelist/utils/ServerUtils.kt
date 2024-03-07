package dev.rafi.vitelist.utils

import dev.rafi.vitelist.database.model.Server
import org.jetbrains.exposed.sql.transactions.transaction

fun serverExists(name: String): Boolean {
    var exists = false

    transaction {
        exists = Server.select(Server.name).where {Server.name eq name}.withDistinct().empty()
    }

    return !exists
}