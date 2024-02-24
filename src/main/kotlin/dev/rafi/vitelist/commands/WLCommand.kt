package dev.rafi.vitelist.commands

import com.velocitypowered.api.command.SimpleCommand
import dev.rafi.vitelist.ViteList
import dev.rafi.vitelist.commands.subcommands.Reload
import dev.rafi.vitelist.utils.ChatUtils

class WLCommand : SimpleCommand {
    init {
        val cmdManager = ViteList.getProxyServer().commandManager
        val cmdMeta = cmdManager.metaBuilder("whitelist").aliases("wl").plugin(ViteList.getProxyServer()).build()
        cmdManager.register(cmdMeta, this)
    }

    override fun execute(invocation: SimpleCommand.Invocation) {
        val source = invocation.source();
        val args = invocation.arguments();

        if (args.isEmpty()) ChatUtils.sendMessage(source, "&7[&5ViteList&7] &k&lii &7Version &f&l1.0.0 &7by &f&lRafi &7(&f&lA.K.A Cipher&7)")

        if (args.isNotEmpty()) {
            if (args[0].equals("reload", true)) {
                Reload.reload(source, args)
            }
        }
    }
}