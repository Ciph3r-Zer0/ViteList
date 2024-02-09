package dev.rafi.vitelist.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import dev.rafi.vitelist.ViteList;
import dev.rafi.vitelist.database.model.ServerModel;
import dev.rafi.vitelist.storage.Config;
import dev.rafi.vitelist.utils.QueryUtils;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class DataSource {
    @Getter private static Dao<ServerModel, String> serverDao;
    @Getter private ConnectionSource connectionSource;

    public DataSource() throws IOException, SQLException {
        if (Config.TYPE.equalsIgnoreCase("MySQL")) {
            MySQL();
        } else {
            SQLite();
        }
    }


    private void SQLite() throws IOException, SQLException {
        File file = new File(ViteList.getDataDirectory().toUri().getPath().replace("vitelist", "ViteList") + "/" + "data.db");
        if (!(file.exists())) file.createNewFile();

        String databaseUrl = "jdbc:sqlite:" + ViteList.getDataDirectory().toUri().getPath() + "/data.db";

        connectionSource = new JdbcConnectionSource(databaseUrl);

        setupTables();
    }

    private void MySQL() throws IOException, SQLException {
        String databaseUrl = "jdbc:mysql://" + Config.HOST + ":" + Config.PORT + "/" + Config.NAME + "?useSSL=false&autoReconnect=true";

        connectionSource = new JdbcConnectionSource(databaseUrl, Config.USER, Config.PASSWORD);

        setupTables();
    }

    private void setupTables() throws SQLException {
        serverDao = DaoManager.createDao(connectionSource, ServerModel.class);
        TableUtils.createTableIfNotExists(connectionSource, ServerModel.class);
    }
}
