package dev.rafi.vitelist.commands.sub;

import com.velocitypowered.api.command.CommandSource;
import dev.rafi.vitelist.storage.Config;
import dev.rafi.vitelist.utils.ChatUtils;
import dev.rafi.vitelist.utils.QueryUtils;

public class Off {
    public static void whitelistOff(CommandSource executor, String[] args) {
        if (!executor.hasPermission("vitelist.commands.off")) {
            ChatUtils.sendMessage(executor, Config.NO_PERMISSION);
            return;
        }
        if (args.length == 1) {
            QueryUtils.turnOffWhiteList("all");
            ChatUtils.sendMessage(executor, Config.DISABLE);
        } else if (args.length == 2) {
            if (!QueryUtils.serverExists(args[1])) {
                ChatUtils.sendMessage(executor, Config.WRONG_SERVER.replace("%server%", args[1]));
                return;
            }
            QueryUtils.turnOffWhiteList(args[1]);
            ChatUtils.sendMessage(executor, Config.DISABLE_SERVER.replace("%server%", args[1]));
        }
    }
}
