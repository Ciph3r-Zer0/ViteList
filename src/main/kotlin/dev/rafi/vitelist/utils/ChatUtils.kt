package dev.rafi.vitelist.utils

import com.velocitypowered.api.command.CommandSource
import net.kyori.adventure.text.Component

class ChatUtils {
    companion object {
        private fun colorize(msg: String) : Component {
            return Component.text(msg.replace('&', '§'))
        }

        public fun sendMessage(source: CommandSource, msg: String) {
            source.sendMessage(colorize(msg))
        }
    }
}