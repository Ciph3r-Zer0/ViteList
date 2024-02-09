package dev.rafi.vitelist.storage;

import dev.rafi.vitelist.storage.model.ConfigModel;

import java.io.IOException;
import java.nio.file.Path;

public class Config extends ConfigModel {

    public Config(Path dir, String fileName) throws IOException {
        super(dir, fileName);
    }

    public static int CONFIG_VERSION;
    public static String TYPE;
    public static String HOST;
    public static int PORT;
    public static String NAME;
    public static String USER;
    public static String PASSWORD;

    public static String PREFIX;
    public static String ENABLE;
    public static String ENABLE_SERVER;
    public static String DISABLE;
    public static String DISABLE_SERVER;
    public static String ADD_GLOBAL;
    public static String ADD_SERVER;
    public static String REMOVE_GLOBAL;
    public static String REMOVE_SERVER;
    public static String NO_PERMISSION;
    public static String WRONG_USAGE;
    public static String WRONG_SERVER;
    public static String WHITELIST_KICK;
    public static String SERVER_SWITCH_MESSAGE;

    @Override
    public void addComments() {
        getYamlFile().setComment("config-version", "Do not touch");

        getYamlFile().setComment("database", "Database configuration section");
        getYamlFile().setComment("database.type", "Available types: [SQLite, MySQL]");

        getYamlFile().setComment("message", "Message configuration section");
        getYamlFile().setComment("messages.whitelist-kick", "When a player tries to join and gets kicked");
        getYamlFile().setComment("messages.server-switch-message", "When a player tries to join a whitelisted server");
    }

    @Override
    public void addDefaults() {
        getYamlFile().addDefault("config-version", 1);

        getYamlFile().addDefault("database.type", "SQLite");
        getYamlFile().addDefault("database.host", "127.0.0.1");
        getYamlFile().addDefault("database.port", 3306);
        getYamlFile().addDefault("database.name", "vite_list");
        getYamlFile().addDefault("database.user", "root");
        getYamlFile().addDefault("database.password", "admin@123");

        getYamlFile().addDefault("messages.prefix", "&7[&5ViteList&7] &k&lii");
        getYamlFile().addDefault("messages.enable", "%prefix% &7Whitelist has been &aEnabled &7globally");
        getYamlFile().addDefault("messages.enable-server", "%prefix% &7Whitelist has been &aEnabled &7in &f&l%server%");
        getYamlFile().addDefault("messages.disable", "%prefix% &7Whitelist has been &cDisabled &7globally");
        getYamlFile().addDefault("messages.disable-server", "%prefix% &7Whitelist has been &cDisabled &7in &f&l%server%");
        getYamlFile().addDefault("messages.add-global", "%prefix% &aAdded &f&l%user% &7to global whitelist");
        getYamlFile().addDefault("messages.add-server", "%prefix% &aAdded &f&l%user% &7to whitelist in &f&l%server%");
        getYamlFile().addDefault("messages.remove-global", "%prefix% &cRemoved &f&l%user% &7from global whitelist");
        getYamlFile().addDefault("messages.remove-server", "%prefix% &cRemoved &f&l%user% &7from whitelist in &f&l%server%");
        getYamlFile().addDefault("messages.no-permission", "%prefix% &cInsufficient &7permissions");
        getYamlFile().addDefault("messages.wrong-usage", "%prefix% &cWrong &7usage of command, use &f&l/whitelist help &7for more info");
        getYamlFile().addDefault("messages.wrong-server", "%prefix% &7Specified server &f&l%server% &7does &cnot &7exist");
        getYamlFile().addDefault("messages.whitelist-kick", "&c&lYou are not whitelisted in this server");
        getYamlFile().addDefault("messages.server-switch-message", "%prefix% &cServer is whitelisted");
    }

    @Override
    public void initVariables() {
        CONFIG_VERSION = getYamlFile().getInt("config-version");

        TYPE = getYamlFile().getString("database.type");
        HOST = getYamlFile().getString("database.host");
        PORT = getYamlFile().getInt("database.port");
        NAME = getYamlFile().getString("database.name");
        USER = getYamlFile().getString("database.user");
        PASSWORD = getYamlFile().getString("database.password");

        PREFIX = getYamlFile().getString("messages.prefix");
        ENABLE = getYamlFile().getString("messages.enable").replace("%prefix%", PREFIX);
        ENABLE_SERVER = getYamlFile().getString("messages.enable-server").replace("%prefix%", PREFIX);
        DISABLE = getYamlFile().getString("messages.disable").replace("%prefix%", PREFIX);
        DISABLE_SERVER = getYamlFile().getString("messages.disable-server").replace("%prefix%", PREFIX);
        ADD_GLOBAL = getYamlFile().getString("messages.add-global").replace("%prefix%", PREFIX);
        ADD_SERVER = getYamlFile().getString("messages.add-server").replace("%prefix%", PREFIX);
        REMOVE_GLOBAL = getYamlFile().getString("messages.remove-global").replace("%prefix%", PREFIX);
        REMOVE_SERVER = getYamlFile().getString("messages.remove-server").replace("%prefix%", PREFIX);
        NO_PERMISSION = getYamlFile().getString("messages.no-permission").replace("%prefix%", PREFIX);
        WRONG_USAGE = getYamlFile().getString("messages.wrong-usage").replace("%prefix%", PREFIX);
        WRONG_SERVER = getYamlFile().getString("messages.wrong-server").replace("%prefix%", PREFIX);
        WHITELIST_KICK = getYamlFile().getString("messages.whitelist-kick").replace("%prefix%", PREFIX);
        SERVER_SWITCH_MESSAGE = getYamlFile().getString("messages.server-switch-message").replace("%prefix%", PREFIX);
    }
}
