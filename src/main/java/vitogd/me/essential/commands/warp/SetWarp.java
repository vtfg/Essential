package vitogd.me.essential.commands.warp;
import vitogd.me.essential.Essential;
import vitogd.me.essential.utils.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Location;

public class SetWarp implements CommandExecutor {

    private Essential plugin;

    public SetWarp(Essential plugin) {
        this.plugin = plugin;

        plugin.getCommand("setwarp").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_message")));
            return false;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("essential.warp.setwarp")) {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
            return false;
        }

        if (args.length == 0) {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("Warp.provide_warp_name")));
            return false;
        }

        String warp_name = args[0].toLowerCase();

        if (plugin.getConfig().get(warp_name) != null) {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("Warp.warp_name_exists").replace("<warpname>", warp_name)));
            return false;
        }

        Location loc = p.getLocation();
        plugin.getConfig().set(warp_name + ".World", loc.getWorld().getName());
        plugin.getConfig().set(warp_name + ".X", loc.getX());
        plugin.getConfig().set(warp_name + ".Y", loc.getY());
        plugin.getConfig().set(warp_name + ".Z", loc.getZ());
        plugin.getConfig().set(warp_name + ".Pitch", loc.getPitch());
        plugin.getConfig().set(warp_name + ".Yaw", loc.getYaw());
        plugin.saveConfig();

        p.sendMessage(Utils.chat(plugin.getConfig().getString("Warp.warp_set").replace("<warpname>", warp_name)));
        return true;
    }
}