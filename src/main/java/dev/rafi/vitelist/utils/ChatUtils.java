package dev.rafi.vitelist.utils;

import com.velocitypowered.api.command.CommandSource;
import net.kyori.adventure.text.Component;

public class ChatUtils {

    public static Component colorize(String msg) {
        return Component.text(msg.replace('&', '§'));
    }

    public static void sendMessage(CommandSource source, String msg) {
        source.sendMessage(colorize(msg));
    }
}
