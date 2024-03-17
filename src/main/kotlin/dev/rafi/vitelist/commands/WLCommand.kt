package dev.rafi.vitelist.commands

import com.velocitypowered.api.command.SimpleCommand
import dev.rafi.vitelist.ViteList
import dev.rafi.vitelist.commands.subcommands.*
import dev.rafi.vitelist.storage.Config
import dev.rafi.vitelist.utils.sendMessage

class WLCommand : SimpleCommand {
    init {
        val cmdManager = ViteList.getProxyServer().commandManager
        val cmdMeta = cmdManager.metaBuilder("whitelist").aliases("wl").plugin(ViteList.getProxyServer()).build()
        cmdManager.register(cmdMeta, this)
    }

    override fun execute(invocation: SimpleCommand.Invocation) {
        val source = invocation.source()
        val args = invocation.arguments()

        if (args.isEmpty()) {
            sendMessage(source, "&7[&5ViteList&7] &k&lii &7Version &f&l1.0.0 &7by &f&lRafi &7(&f&lA.K.A Cipher&7)")
            return
        }

        if (args[0].equals("reload", true)) {
            reload(source, args)
        } else if (args[0].equals("on", true)) {
            on(source, args)
        } else if (args[0].equals("off", true)) {
            off(source, args)
        } else if (args[0].equals("add", true)) {
            add(source, args)
        } else if (args[0].equals("remove", true)) {
            remove(source, args)
        } else {
            sendMessage(source, Config.WRONG_USAGE)
        }
    }
}