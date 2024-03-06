package dev.rafi.vitelist.database

import dev.rafi.vitelist.ViteList
import dev.rafi.vitelist.database.model.Server
import dev.rafi.vitelist.storage.Config
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File
import java.sql.Connection

class DataSource {

    init {
        initDataBase()

    }

    private fun initDataBase() {
        val dbType = Config.DB_TYPE
        val sqLite = "SQLite"
        val mySql = "MySQL"

        if (dbType.equals(sqLite, true)) {
            sqLite()
        } else if (dbType.equals(mySql, true)) {
            mySql()
        } else {
            sqLite()
        }

        transaction {
            SchemaUtils.create(Server)
        }
    }

    private fun sqLite() {
        val sqLiteDriver = "org.sqlite.JDBC"
        val dbFile = File(ViteList.getDataDir().toFile(), "data.db")

        if (!(dbFile.exists())) dbFile.createNewFile()

        Database.connect("jdbc:sqlite:${dbFile.toPath()}", sqLiteDriver)
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE
    }

    private fun mySql() {
        val mySqlDriver = "com.mysql.cj.jdbc.Driver"

        Database.connect("jdbc:mysql://${Config.DB_HOST}:${Config.DB_PORT}/${Config.DB_NAME}", driver = mySqlDriver, user = Config.DB_USERNAME, password = Config.DB_PASSWORD)
    }
}