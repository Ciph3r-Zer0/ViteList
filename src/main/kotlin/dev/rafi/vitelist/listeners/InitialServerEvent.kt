package dev.rafi.vitelist.listeners

import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.player.PlayerChooseInitialServerEvent
import dev.rafi.vitelist.ViteList
import dev.rafi.vitelist.storage.Config
import dev.rafi.vitelist.utils.colorize
import dev.rafi.vitelist.utils.isPlayerWhitelisted
import dev.rafi.vitelist.utils.isWhitelisted

class InitialServerEvent {
    init {
        ViteList.getProxyServer().eventManager.register(ViteList.getInst(), this)
    }

    @Subscribe
    fun chooseInitialServerEvent(event: PlayerChooseInitialServerEvent) {
        val player = event.player
        val server = event.initialServer

        if (!server.isPresent) return

        val isWhitelisted = isWhitelisted(server.get().serverInfo.name)

        if (!isWhitelisted) return

        val isPlayerWhitelisted = isPlayerWhitelisted(player.username, server.get().serverInfo.name)

        if (!isPlayerWhitelisted) return

        player.disconnect(colorize(Config.KICK))
    }
}