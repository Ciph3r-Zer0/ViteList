package dev.rafi.vitelist.commands.sub;

import com.velocitypowered.api.command.CommandSource;
import dev.rafi.vitelist.storage.Config;
import dev.rafi.vitelist.utils.ChatUtils;
import dev.rafi.vitelist.utils.QueryUtils;

public class Add {
    public static void whitelistAdd(CommandSource executor, String[] args) {
        if (!executor.hasPermission("vitelist.commands.add")) {
            ChatUtils.sendMessage(executor, Config.NO_PERMISSION);
            return;
        }

        if (args.length == 1) {
            ChatUtils.sendMessage(executor, Config.WRONG_USAGE);
        } else if (args.length == 2) {
            QueryUtils.addToWhiteList(args[1], "all");
            ChatUtils.sendMessage(executor, Config.ADD_GLOBAL.replace("%user%", args[1]));
        } else if (args.length == 3) {
            if (!QueryUtils.serverExists(args[2])) {
                ChatUtils.sendMessage(executor, Config.WRONG_SERVER.replace("%server%", args[2]));
                return;
            }
            QueryUtils.addToWhiteList(args[1], args[2]);
            ChatUtils.sendMessage(executor, Config.ADD_SERVER.replace("%user%", args[1]).replace("%server%", args[2]));
        }
    }
}
