package dev.rafi.vitelist.storage.model

import dev.dejvokep.boostedyaml.YamlDocument
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings
import dev.rafi.vitelist.ViteList
import java.io.File
import java.util.Objects


abstract class ConfigModel(fileName: String) {

    var config: YamlDocument = YamlDocument.create(
        File(ViteList.getDataDir().toFile(), fileName),
        Objects.requireNonNull(javaClass.getResourceAsStream("/$fileName")),
        GeneralSettings.DEFAULT,
        LoaderSettings.builder().setAutoUpdate(true).build(),
        DumperSettings.DEFAULT,
        UpdaterSettings.builder().setVersioning(BasicVersioning("config-version")).build())

    abstract fun initialize()
}