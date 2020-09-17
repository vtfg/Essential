package vitogd.me.essential.commands.warp;
import vitogd.me.essential.Essential;
import vitogd.me.essential.utils.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelWarp implements CommandExecutor {

    private Essential plugin;

    public DelWarp(Essential plugin) {
        this.plugin = plugin;

        plugin.getCommand("delwarp").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_message")));
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("essential.warp.delwarp")) {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
            return false;
        }

        if (args.length == 0) {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("Warp.provide_warp_name")));
            return false;
        }

        String warp_name = args[0].toLowerCase();

        if (plugin.getConfig().get(warp_name) == null) {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("Warp.no_warp_name").replace("<warpname>", warp_name)));
            return false;
        }

        plugin.getConfig().set(warp_name, null);
        plugin.saveConfig();
        p.sendMessage(Utils.chat(plugin.getConfig().getString("Warp.warp_deleted").replace("<warpname", warp_name)));

        return true;
    }
}