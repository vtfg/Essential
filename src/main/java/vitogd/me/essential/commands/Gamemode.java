package vitogd.me.essential.commands;
import org.bukkit.GameMode;
import vitogd.me.essential.Essential;
import vitogd.me.essential.utils.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {

    private Essential plugin;

    public Gamemode(Essential plugin) {
        this.plugin = plugin;

        plugin.getCommand("gm").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_message")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("essentials.gm")) {
            if (args.length == 0) {
                p.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.no_args_message")));
                return true;
            }
            else {
                if (args[0].equals("0") || args[0].equalsIgnoreCase("s")) {
                    if (p.getGameMode() != GameMode.SURVIVAL) {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.gamemode_survival")));
                        return true;
                    }
                    else if (p.getGameMode() == GameMode.SURVIVAL) {
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.already_gamemode")));
                        return true;
                    }
                }
                else if (args[0].equals("1") || args[0].equalsIgnoreCase("c")) {
                    if (p.getGameMode() != GameMode.CREATIVE) {
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.gamemode_creative")));
                        return true;
                    }
                    else if (p.getGameMode() == GameMode.CREATIVE) {
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.already_gamemode")));
                        return true;
                    }
                }
                else if (args[0].equals("2") || args[0].equalsIgnoreCase("a")) {
                    if (p.getGameMode() != GameMode.ADVENTURE) {
                        p.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.gamemode_adventure")));
                        return true;
                    }
                    else if (p.getGameMode() == GameMode.ADVENTURE) {
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.already_gamemode")));
                        return true;
                    }
                }
                else {
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("Gamemode.gamemode_not_found")));
                    return true;
                }
            }
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
        }

        return false;
    }
}