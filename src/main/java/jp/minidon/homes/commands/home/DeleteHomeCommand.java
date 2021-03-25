package jp.minidon.homes.commands.home;

import jp.minidon.homes.home.Home;
import jp.minidon.homes.home.HomeManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteHomeCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        final Player player = (Player) sender;
        final String homeName = args[0];

        Home home = HomeManager.get().loadHome(player, homeName).orElse(null);
        if (home == null)
        {
            sender.sendMessage(ChatColor.RED + homeName + "は存在しません");
            return true;
        }

        HomeManager.get().deleteHome(home);
        sender.sendMessage(ChatColor.RED + homeName + "を削除しました");
        return true;
    }
}
