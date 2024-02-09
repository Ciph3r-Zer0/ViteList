package dev.rafi.vitelist.listeners;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerChooseInitialServerEvent;
import com.velocitypowered.api.event.player.ServerPreConnectEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import dev.rafi.vitelist.storage.Config;
import dev.rafi.vitelist.utils.ChatUtils;
import dev.rafi.vitelist.utils.QueryUtils;
import net.kyori.adventure.text.Component;

import java.util.Optional;

public class Listener {

    @Subscribe
    private void serverPreConnectEvent(ServerPreConnectEvent event) {
        Player player = event.getPlayer();
        RegisteredServer server = event.getOriginalServer();

        boolean whiteListed = QueryUtils.isWhiteListed(server.getServerInfo().getName());
        boolean canJoin = QueryUtils.playerExists(player.getUsername(), server.getServerInfo().getName());

        if (!whiteListed) return;
        if (canJoin) return;

        event.setResult(ServerPreConnectEvent.ServerResult.denied());
        ChatUtils.sendMessage(player, Config.SERVER_SWITCH_MESSAGE);
    }

    @Subscribe
    private void initialServerEvent(PlayerChooseInitialServerEvent event) {
        Player player = event.getPlayer();
        Optional<RegisteredServer> server = event.getInitialServer();

        if (!server.isPresent()) return;

        boolean whiteListed = QueryUtils.isWhiteListed(server.get().getServerInfo().getName());
        boolean canJoin = QueryUtils.playerExists(player.getUsername(), server.get().getServerInfo().getName());

        if (!whiteListed) return;
        if (canJoin) return;

        player.disconnect(ChatUtils.colorize(Config.WHITELIST_KICK));
    }
}
