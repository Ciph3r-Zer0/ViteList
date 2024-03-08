package dev.rafi.vitelist.listeners

import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.player.ServerPreConnectEvent
import com.velocitypowered.api.event.player.ServerPreConnectEvent.ServerResult
import dev.rafi.vitelist.ViteList
import dev.rafi.vitelist.storage.Config
import dev.rafi.vitelist.utils.isPlayerWhitelisted
import dev.rafi.vitelist.utils.isWhitelisted
import dev.rafi.vitelist.utils.sendMessage

class PreConnectEvent {
    init {
        ViteList.getProxyServer().eventManager.register(ViteList.getInst(), this)
    }

    @Subscribe
    fun serverPreConnectEvent(event: ServerPreConnectEvent) {
        val player = event.player
        val server = event.originalServer
        val isWhitelisted = isWhitelisted(server.serverInfo.name)

        if (!isWhitelisted) return

        val isPlayerWhitelisted = isPlayerWhitelisted(player.username, server.serverInfo.name)

        if (isPlayerWhitelisted) return

        event.result = ServerResult.denied()
        sendMessage(player, Config.SERVER_SWITCH)
    }
}