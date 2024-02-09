package dev.rafi.vitelist.storage.model;

import lombok.Getter;
import org.simpleyaml.configuration.file.YamlFile;

import java.io.IOException;
import java.nio.file.Path;

@Getter
public abstract class ConfigModel {
    private final String fileName;
    private final Path dir;
    private final YamlFile yamlFile;

    public ConfigModel(Path dir, String fileName) throws IOException {
        this.dir = dir;
        this.fileName = fileName;

        this.yamlFile = new YamlFile(dir.toUri().getPath().replace("vitelist", "ViteList") + "/" + fileName);

        if (!yamlFile.exists()) {
            this.yamlFile.createNewFile();
        }

        this.yamlFile.load();

        addComments();
        addDefaults();
        initVariables();

        this.yamlFile.save();
    }

    public abstract void addComments();
    public abstract void addDefaults();
    public abstract void initVariables();
}
