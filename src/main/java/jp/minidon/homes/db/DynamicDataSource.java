package jp.minidon.homes.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jp.minidon.homes.Homes;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public class DynamicDataSource
{
    private HikariConfig config = new HikariConfig();

    public DynamicDataSource(boolean preferMySQL) throws ClassNotFoundException
    {
        if (preferMySQL)
        {
            String ip = getConfiguration().getString("MySQL.IP");
            String dbName = getConfiguration().getString("MySQL.DB-Name");
            String usrName = getConfiguration().getString("MySQL.Username");
            String password = getConfiguration().getString("MySQL.Password");
            String properties = getConfiguration().getString("MySQL.Properties");
            int port = getConfiguration().getInt("MySQL.Port");

            Class.forName("com.mysql.jdbc.Driver");
            config.setJdbcUrl("jdbc:mysql://" + ip + ":" + port + "/" + dbName + "?"+properties);
            config.setUsername(usrName);
            config.setPassword(password);
        }
        else
        {
            String path = Homes.getPlugin().getDataFolder().getPath() + "/data/";

            File dataFolder = new File(path);
            if (!dataFolder.exists()) dataFolder.mkdirs();

            File databaseFile = new File(dataFolder, "sqlite.db");
            if (!databaseFile.exists())
            {
                try { databaseFile.createNewFile(); }
                catch (IOException e) { }
            }
            String driverClassName = "org.sqlite.JDBC";
            Class.forName(driverClassName);
            config.setDriverClassName(driverClassName);
            config.setJdbcUrl("jdbc:sqlite:" + path + "sqlite.db");
        }
    }

    public HikariDataSource generateDataSource()
    {
        return new HikariDataSource(config);
    }

    public FileConfiguration getConfiguration()
    {
        return Homes.getPlugin().getDatabaseConfig().getConfiguration();
    }
}