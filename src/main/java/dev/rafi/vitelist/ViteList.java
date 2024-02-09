package dev.rafi.vitelist;

import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.rafi.vitelist.commands.WLCommand;
import dev.rafi.vitelist.database.DataSource;
import dev.rafi.vitelist.database.model.ServerModel;
import dev.rafi.vitelist.listeners.Listener;
import dev.rafi.vitelist.storage.Config;
import dev.rafi.vitelist.utils.QueryUtils;
import lombok.Getter;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;

@Plugin(
        id = "vitelist",
        name = "ViteList",
        version = "1.0.0"
)
public class ViteList {

    @Getter private static ProxyServer proxyServer;
    private final Logger logger;
    @Getter private static Path dataDirectory;

    @Inject
    public ViteList(ProxyServer proxyServer, Logger logger, @DataDirectory Path dataDirectory) {
        ViteList.proxyServer = proxyServer;
        this.logger = logger;
        ViteList.dataDirectory = dataDirectory;

    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        CommandManager commandManager = proxyServer.getCommandManager();

        try {
            new Config(dataDirectory, "config.yml");
            new DataSource();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

        QueryUtils.addServers();

        proxyServer.getEventManager().register(this, new Listener());

        CommandMeta wlCommandMeta = commandManager.metaBuilder("whitelist").aliases("wl").plugin(this).build();
        commandManager.register(wlCommandMeta, new WLCommand());
    }
}
