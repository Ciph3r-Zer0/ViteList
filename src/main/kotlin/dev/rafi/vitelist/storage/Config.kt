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


        private fun init(config: YamlDocument) {
            cfg = config
            initProperties()
        }

        private fun initProperties() {
            CONFIG_VERSION = cfg.getInt("config-version")
        }
    }
}