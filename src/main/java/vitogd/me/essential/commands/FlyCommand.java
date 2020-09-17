package vitogd.me.essential.commands;
import vitogd.me.essential.Essential;
import vitogd.me.essential.utils.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    private Essential plugin;

    public FlyCommand(Essential plugin) {
        this.plugin = plugin;

        plugin.getCommand("fly").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_message")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("essentials.fly")) {
            if (p.isFlying()) {
                p.setAllowFlight(false);
                p.setFlying(false);
                p.sendMessage(Utils.chat(plugin.getConfig().getString("FlyCommand.flying_disabled")));
                return true;
            }
                p.setAllowFlight(true);
                p.setFlying(true);
                p.sendMessage(Utils.chat(plugin.getConfig().getString("FlyCommand.flying_enabled")));
                return true;
        }
        p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
        return false;
    }
}