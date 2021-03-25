package jp.minidon.homes;

import jp.minidon.homes.commands.home.DeleteHomeCommand;
import jp.minidon.homes.commands.home.HomeCommand;
import jp.minidon.homes.commands.home.SetHomeCommand;
import jp.minidon.homes.db.Database;
import jp.minidon.homes.home.HomeManager;
import jp.minidon.homes.util.CustomConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Homes extends JavaPlugin
{
    public static Homes plugin;

    private CustomConfig db_config;

    @Override
    public void onEnable()
    {
        super.onEnable();
        plugin = this;

        try { Database.get().setup(false); }
        catch (Exception e) { e.printStackTrace(); }

        db_config = new CustomConfig(this, "database.yml");
        db_config.saveDefault();

        File file = new File("/plugins/Homes/data/sqlite.db");
        if (file.exists()) HomeManager.get().loadAllHomes();

        getCommand("home").setExecutor(new HomeCommand());
        getCommand("sethome").setExecutor(new SetHomeCommand());
        getCommand("delhome").setExecutor(new DeleteHomeCommand());
    }

    @Override
    public void onDisable()
    {
        super.onDisable();
        Database.get().shutdown();
    }

    public static Homes getPlugin()
    {
        return plugin;
    }

    public CustomConfig getDatabaseConfig()
    {
        return db_config;
    }
}
