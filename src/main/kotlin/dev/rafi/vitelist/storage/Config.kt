package dev.rafi.vitelist.storage

import dev.dejvokep.boostedyaml.YamlDocument
import dev.rafi.vitelist.storage.model.ConfigModel

class Config constructor(fileName: String) : ConfigModel(fileName) {

    init {
        initialize()
    }

    override fun initialize() {
        init(config)
    }

    companion object {
        lateinit var cfg: YamlDocument

        lateinit var CONFIG_VERSION: Number

        lateinit var DB_TYPE: String
        lateinit var DB_HOST: String
        lateinit var DB_PORT: String
        lateinit var DB_NAME: String
        lateinit var DB_USERNAME: String
        lateinit var DB_PASSWORD: String

        lateinit var PREFIX: String
        lateinit var NO_PERMISSION: String
        lateinit var RELOAD_SUCCESS: String
        lateinit var RELOAD_FAIL: String


        private fun init(config: YamlDocument) {
            cfg = config
            initProperties()
        }

        private fun initProperties() {
            CONFIG_VERSION = cfg.getInt("config-version")

            DB_TYPE = cfg.getString("database.type")
            DB_HOST = cfg.getString("database.host")
            DB_PORT = cfg.getString("database.port")
            DB_NAME = cfg.getString("database.name")
            DB_USERNAME = cfg.getString("database.username")
            DB_PASSWORD = cfg.getString("database.password")

            PREFIX = cfg.getString("messages.prefix")
            NO_PERMISSION = setPlaceHolders(cfg.getString("messages.no-permission"))
            RELOAD_SUCCESS = setPlaceHolders(cfg.getString("messages.reload-success"))
            RELOAD_FAIL = setPlaceHolders(cfg.getString("messages.reload-fail"))
        }

        private fun setPlaceHolders(msg : String) : String {
            return msg.replace("%prefix%", PREFIX)
        }

        fun reload() : Boolean {
            if (cfg.reload()) {
                initProperties()
                return true
            } else {
                return false
            }
        }
    }
}