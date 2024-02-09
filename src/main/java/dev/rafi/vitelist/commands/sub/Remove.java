package dev.rafi.vitelist.commands.sub;

import com.velocitypowered.api.command.CommandSource;
import dev.rafi.vitelist.storage.Config;
import dev.rafi.vitelist.utils.ChatUtils;
import dev.rafi.vitelist.utils.QueryUtils;

public class Remove {
    public static void whitelistAdd(CommandSource executor, String[] args) {
        if (!executor.hasPermission("vitelist.commands.remove")) {
            ChatUtils.sendMessage(executor, Config.NO_PERMISSION);
            return;
        }

        if (args.length == 1) {
            ChatUtils.sendMessage(executor, Config.WRONG_USAGE);
        } else if (args.length == 2) {
            QueryUtils.removeFromWhiteList(args[1], "all");
            ChatUtils.sendMessage(executor, Config.REMOVE_GLOBAL.replace("%user%", args[1]));
        } else if (args.length == 3) {
            if (!QueryUtils.serverExists(args[2])) {
                ChatUtils.sendMessage(executor, Config.WRONG_SERVER.replace("%server%", args[2]));
                return;
            }
            QueryUtils.removeFromWhiteList(args[1], args[2]);
            ChatUtils.sendMessage(executor, Config.REMOVE_SERVER.replace("%user%", args[1]).replace("%server%", args[2]));
        }
    }
}
