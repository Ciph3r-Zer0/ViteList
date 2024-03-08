package dev.rafi.vitelist.utils

import com.velocitypowered.api.command.CommandSource
import net.kyori.adventure.text.Component


fun colorize(msg: String) : Component {
    return Component.text(msg.replace('&', '§'))
}
fun sendMessage(source: CommandSource, msg: String) {
    source.sendMessage(colorize(msg))
}