package dev.rafi.vitelist.utils;

import com.velocitypowered.api.proxy.Player;
import dev.rafi.vitelist.ViteList;

import java.util.ArrayList;
import java.util.List;

public class ProxyUtils {
    public static List<String> getAllPlayerNames() {
        List<String> players = new ArrayList<>();

        for (Player player : ViteList.getProxyServer().getAllPlayers()) {
            players.add(player.getUsername());
        }
        return players;
    }
}
