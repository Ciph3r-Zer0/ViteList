package dev.rafi.vitelist

import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.plugin.annotation.DataDirectory
import com.velocitypowered.api.proxy.ProxyServer
import dev.rafi.vitelist.storage.Config
import org.slf4j.Logger
import java.nio.file.Path


@Plugin(
    id = "vitelist",
    name = "ViteList",
    version = "1.0.0"
)
class ViteList @Inject constructor(
    public val proxyServer: ProxyServer,
    public val logger: Logger,
    @DataDirectory public val dataDir: Path
) {

    @Subscribe
    private fun onProxyInit(event: ProxyInitializeEvent) {
        initialize(this)
        Config("config.yml")
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