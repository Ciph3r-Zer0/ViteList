package dev.rafi.vitelist.commands.sub;

import com.velocitypowered.api.command.CommandSource;
import dev.rafi.vitelist.storage.Config;
import dev.rafi.vitelist.utils.ChatUtils;
import dev.rafi.vitelist.utils.QueryUtils;

public class On {
    public static void whitelistOn(CommandSource executor, String[] args) {
        if (!executor.hasPermission("vitelist.commands.on")) {
            ChatUtils.sendMessage(executor, Config.NO_PERMISSION);
            return;
        }

        if (args.length == 1) {
            QueryUtils.turnOnWhiteList("all");
            ChatUtils.sendMessage(executor, Config.ENABLE);
        } else if (args.length == 2) {
            if (!QueryUtils.serverExists(args[1])) {
                ChatUtils.sendMessage(executor, Config.WRONG_SERVER.replace("%server%", args[1]));
                return;
            }
            QueryUtils.turnOnWhiteList(args[1]);
            ChatUtils.sendMessage(executor, Config.ENABLE_SERVER.replace("%server%", args[1]));
        }
    }
}
