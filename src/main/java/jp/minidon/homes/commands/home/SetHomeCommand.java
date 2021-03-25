package jp.minidon.homes.commands.home;

import jp.minidon.homes.home.HomeManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        final Player player = (Player) sender;
        final String homeName = args[0];

        if (HomeManager.get().loadHome(player, homeName).isPresent())
        {
            sender.sendMessage(ChatColor.RED + "同じ名前での保存はできません");
            return true;
        }

        if (HomeManager.get().getPlayerHomeBy(player.getUniqueId()).size() >= 5)
        {
            sender.sendMessage(ChatColor.RED + "5個以上は設定できません");
            return true;
        }

        HomeManager.get().createAndLoadHome(player, player.getLocation(), homeName);
        sender.sendMessage(ChatColor.GREEN + homeName + "を設定しました");

        return true;
    }
}
