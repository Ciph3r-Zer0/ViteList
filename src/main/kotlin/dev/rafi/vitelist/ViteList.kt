package dev.rafi.vitelist

import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.plugin.annotation.DataDirectory
import com.velocitypowered.api.proxy.ProxyServer
import dev.rafi.vitelist.commands.WLCommand
import dev.rafi.vitelist.database.DataSource
import dev.rafi.vitelist.database.model.Server
import dev.rafi.vitelist.storage.Config
import org.jetbrains.exposed.sql.insertIgnore
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.Logger
import java.nio.file.Path


@Plugin(
    id = "vitelist",
    name = "ViteList",
    version = "1.0.0"
)
class ViteList @Inject constructor(
    val proxyServer: ProxyServer,
    val logger: Logger,
    @DataDirectory val dataDir: Path
) {

    @Subscribe
    private fun onProxyInit(event: ProxyInitializeEvent) {
        initialize(this)
        Config("config.yml")
        WLCommand()
        DataSource()

        transaction {
            getProxyServer().allServers.forEach() { server ->
                Server.insertIgnore {
                    it[name] = server.serverInfo.name
                    it[players] = ""
                }
            }
        }
    }

    companion object {
        private lateinit var inst: ViteList

        private fun initialize(viteList: ViteList) {
            inst = viteList
        }

        private fun getInst(): ViteList {
            return inst
        }

        fun getProxyServer(): ProxyServer {
            return getInst().proxyServer
        }

        fun getLogger(): Logger {
            return getInst().logger
        }

        fun getDataDir(): Path {
            return getInst().dataDir
        }
    }
}