package dev.rafi.vitelist.utils;

import com.j256.ormlite.stmt.UpdateBuilder;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import dev.rafi.vitelist.ViteList;
import dev.rafi.vitelist.database.DataSource;
import dev.rafi.vitelist.database.model.ServerModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QueryUtils {

    public static void addServers() {
        Collection<RegisteredServer> servers = ViteList.getProxyServer().getAllServers();

        servers.forEach(server -> {
            try {
                String name = server.getServerInfo().getName();
                ServerModel serverModel = DataSource.getServerDao().queryBuilder().where().eq("server_name", name).queryForFirst();

                if (serverModel == null) {
                    DataSource.getServerDao().createIfNotExists(new ServerModel(name));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void turnOnWhiteList(String serverName) {
        try {
            UpdateBuilder<ServerModel, String> updateBuilder = DataSource.getServerDao().updateBuilder();
            updateBuilder.updateColumnValue("whitelist_status", true);
            updateBuilder.where().eq("whitelist_status", false);

            if (!serverName.equalsIgnoreCase("all")) {
                updateBuilder.where().eq("server_name", serverName);
            }

            updateBuilder.update();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void turnOffWhiteList(String serverName) {
        try {
            UpdateBuilder<ServerModel, String> updateBuilder = DataSource.getServerDao().updateBuilder();
            updateBuilder.updateColumnValue("whitelist_status", false);
            updateBuilder.where().eq("whitelist_status", true);

            if (!serverName.equalsIgnoreCase("all")) {
                updateBuilder.where().eq("server_name", serverName);
            }

            updateBuilder.update();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addToWhiteList(String playerName, String serverName) {
        try {
            if (serverName.equalsIgnoreCase("all")) {
                List<ServerModel> servers = DataSource.getServerDao().queryForAll();

                for (ServerModel server : servers) {
                    if (playerExists(playerName, server.getServerName())) return;

                    if (server.getWhitelistedPlayers() == null) {
                        server.setWhitelistedPlayers(playerName + ",");
                        DataSource.getServerDao().update(server);
                    } else {
                        server.setWhitelistedPlayers(server.getWhitelistedPlayers() + playerName + ",");
                        DataSource.getServerDao().update(server);
                    }
                }
            } else {
                List<ServerModel> servers = DataSource.getServerDao().queryForEq("server_name", serverName);

                for (ServerModel server : servers) {
                    if (playerExists(playerName, serverName)) return;

                    if (server.getWhitelistedPlayers() == null) {
                        server.setWhitelistedPlayers(playerName + ",");
                        DataSource.getServerDao().update(server);
                    } else {
                        server.setWhitelistedPlayers(server.getWhitelistedPlayers() + playerName + ",");
                        DataSource.getServerDao().update(server);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeFromWhiteList(String playerName, String serverName) {
        try {
            if (serverName.equalsIgnoreCase("all")) {
                List<ServerModel> servers = DataSource.getServerDao().queryForAll();

                for (ServerModel server : servers) {
                    server.setWhitelistedPlayers(server.getWhitelistedPlayers().replace(playerName + ",", ""));
                    DataSource.getServerDao().update(server);
                }
            } else {
                List<ServerModel> servers = DataSource.getServerDao().queryForEq("server_name", serverName);

                for (ServerModel server : servers) {

                    server.setWhitelistedPlayers(server.getWhitelistedPlayers().replace(playerName + ",", ""));
                    DataSource.getServerDao().update(server);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean serverExists(String serverName) {
        try {
            List<ServerModel> servers = DataSource.getServerDao().query(
                    DataSource.getServerDao().queryBuilder()
                            .limit(1L)
                            .where()
                            .eq("server_name", serverName)
                            .prepare());

            if (!servers.isEmpty()) return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static boolean playerExists(String playerName, String serverName) {
        boolean exists = false;

        try {
            ServerModel server = DataSource.getServerDao().queryForEq("server_name", serverName).get(0);
            if (!(server.getWhitelistedPlayers() == null)) {
                exists = server.getWhitelistedPlayers().contains(playerName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exists;
    }

    public static boolean isWhiteListed(String serverName) {
        boolean whitelisted = false;
        try {
            ServerModel server = DataSource.getServerDao().queryForEq("server_name", serverName).get(0);
            whitelisted = server.isWhitelistedStatus();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return whitelisted;
    }

    public static List<String> getServerNames() {
        List<String> serverNames = new ArrayList<>();
        try {
            for (ServerModel serverModel : DataSource.getServerDao().queryForAll()) {
                serverNames.add(serverModel.getServerName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return serverNames;
    }
}
