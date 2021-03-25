package jp.minidon.homes.db;

public enum SQLQuery
{
    HOME_CREATE_TABLE(
            "CREATE TABLE IF NOT EXISTS Homes (" +
                    "id INTEGER PRIMARY KEY NOT NULL," +
                    "name VARCHAR(36) NOT NULL," +
                    "uniqueId VARCHAR(36) NOT NULL," +
                    "world VARCHAR(64) NOT NULL," +
                    "x BIGINT NOT NULL," +
                    "y BIGINT NOT NULL," +
                    "z BIGINT NOT NULL," +
                    "yaw BIGINT NOT NULL," +
                    "pitch BIGINT NOT NULL," +
                    ")",

            "CREATE TABLE IF NOT EXISTS Homes (" +
                    "id INTEGER PRIMARY KEY NOT NULL," +
                    "name VARCHAR(36) NOT NULL," +
                    "uniqueId VARCHAR(36) NOT NULL," +
                    "world VARCHAR(64) NOT NULL," +
                    "x BIGINT NOT NULL," +
                    "y BIGINT NOT NULL," +
                    "z BIGINT NOT NULL," +
                    "yaw BIGINT NOT NULL," +
                    "pitch BIGINT NOT NULL" +
                    ")"
    ),
    HOME_INSERT_DEFAULT(
            "INSERT INTO Homes " +
                    "(name, uniqueId, world, x, y, z, yaw, pitch) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",

            "INSERT INTO Homes " +
                    "(name, uniqueId, world, x, y, z, yaw, pitch) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
    ),
    HOME_SELECT_ALL(
            "SELECT name, uniqueId FROM 'Homes",
            "SELECT name, uniqueId FROM Homes"
    ),
    HOMES_SELECT_ALL_HOME_BY_UUID_NAME(
            "SELECT * FROM Homes WHERE uniqueId = ? AND name = ?",
            "SELECT * FROM Homes WHERE uniqueId = ? AND name = ?"
    ),
    HOMES_SELECT_ALL_HOME_BY_UUID(
            "SELECT * FROM Homes WHERE uniqueId = ?",
            "SELECT * FROM Homes WHERE uniqueId = ?"
    ),
    HOME_DELETE(
            "DELETE FROM Homes WHERE id = ?",
            "DELETE FROM Homes WHERE id = ?"
    );
    private String mysql;
    private String sqlite;

    SQLQuery(String mysql, String sqlite)
    {
        this.mysql = mysql;
        this.sqlite = sqlite;
    }

    @Override
    public String toString()
    {
        return Database.get().isUseMySQL() ? mysql : sqlite;
    }
}