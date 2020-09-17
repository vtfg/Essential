package vitogd.me.essential.commands.warp;
import org.bukkit.Bukkit;
import vitogd.me.essential.Essential;
import vitogd.me.essential.utils.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Location;

public class Warp implements CommandExecutor {

    private Essential plugin;

    public Warp (Essential plugin) {
        this.plugin = plugin;
        plugin.getCommand("warp").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_message")));
            return true;
        }

        Player p = (Player) sender;

        if (args.length == 0) {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("Warp.provide_warp_name")));
            return false;
        }

        String warp_name = args[0].toLowerCase();

        if (plugin.getConfig().get(warp_name) == null) {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("Warp.no_warp_name").replace("<warpname>", warp_name)));
            return false;
        }

        Location loc;
        double x = plugin.getConfig().getDouble(warp_name + ".X");
        double y = plugin.getConfig().getDouble(warp_name + ".Y");
        double z = plugin.getConfig().getDouble(warp_name + ".Z");
        float yaw = (float) plugin.getConfig().getDouble(warp_name + ".Yaw");
        float pitch = (float) plugin.getConfig().getDouble(warp_name + ".Pitch");
        String world = plugin.getConfig().getString(warp_name + ".World");
        loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);

        p.teleport(loc);
        p.sendMessage(Utils.chat(plugin.getConfig().getString("Warp.warp_teleported").replace("<warpname>", warp_name)));

        return true;
    }
}