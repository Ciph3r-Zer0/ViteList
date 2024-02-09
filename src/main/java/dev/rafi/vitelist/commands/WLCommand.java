package dev.rafi.vitelist.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import dev.rafi.vitelist.ViteList;
import dev.rafi.vitelist.commands.sub.Add;
import dev.rafi.vitelist.commands.sub.Off;
import dev.rafi.vitelist.commands.sub.On;
import dev.rafi.vitelist.commands.sub.Remove;
import dev.rafi.vitelist.storage.Config;
import dev.rafi.vitelist.utils.ChatUtils;
import dev.rafi.vitelist.utils.ProxyUtils;
import dev.rafi.vitelist.utils.QueryUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class WLCommand implements SimpleCommand {

    @Override
    public void execute(Invocation invocation) {
        CommandSource executor = invocation.source();
        String[] args = invocation.arguments();

        //Whitelist on [serverName]                         [DONE]
        //Whitelist off [serverName]                        [DONE]
        //Whitelist add <playerName> [serverName]           [DONE]
        //Whitelist remove <playerName> [serverName]        [DONE]
        //Whitelist status [serverName]
        //Whitelist help

        if (args.length == 0) {
            ChatUtils.sendMessage(executor, "&7[&5ViteList&7] &k&lii &7Version &f&l1.0.0 &7by &f&lRafi &7(&f&lA.K.A Cipher&7)");
        }

        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("on")) {
                On.whitelistOn(executor, args);
            } else if (args[0].equalsIgnoreCase("off")) {
                Off.whitelistOff(executor, args);
            } else if (args[0].equalsIgnoreCase("add")) {
                Add.whitelistAdd(executor, args);
            } else if (args[0].equalsIgnoreCase("remove")) {
                Remove.whitelistAdd(executor, args);
            } else if (args[0].equalsIgnoreCase("status")) {
                ChatUtils.sendMessage(executor, Config.PREFIX + " &7This section is still &fWIP");
            } else if (args[0].equalsIgnoreCase("help")) {
                ChatUtils.sendMessage(executor, Config.PREFIX + " &7This section is still &fWIP");
            } else {
                ChatUtils.sendMessage(executor, Config.WRONG_USAGE);
            }
        }
    }

    @Override
    public CompletableFuture<List<String>> suggestAsync(Invocation invocation) {
        String[] args = invocation.arguments();

        if (args.length == 0) {
            return CompletableFuture.completedFuture(List.of("on", "off", "add", "remove", "status"));
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("status")) {
                return CompletableFuture.completedFuture(QueryUtils.getServerNames());
            }
        }
        if (args.length == 3) {
            return CompletableFuture.completedFuture(QueryUtils.getServerNames());
        }

        return CompletableFuture.completedFuture(ProxyUtils.getAllPlayerNames());
    }
}
