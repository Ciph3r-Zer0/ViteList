package dev.rafi.vitelist.database.model;


import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DatabaseTable(tableName = "vitelist_schem")
public class ServerModel {
    public ServerModel() {
    }

    public ServerModel(String serverName) {
        this.serverName = serverName;
    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(/*unique = true, */canBeNull = false, columnName = "server_name")
    private String serverName;

    @DatabaseField(canBeNull = false, columnName = "whitelist_status", defaultValue = "0")
    private boolean whitelistedStatus;

    @DatabaseField(canBeNull = true, columnName = "whitelisted_players", dataType = DataType.LONG_STRING)
    private String whitelistedPlayers;
}
