package dev.rafi.vitelist.database.model

import dev.rafi.vitelist.storage.Config
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Server : Table(Config.DB_NAME) {
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", length = 50).uniqueIndex()
    val status: Column<Boolean> = bool("status").default(false).index()
    val players: Column<String> = text("players")

    override val primaryKey = PrimaryKey(id)
}