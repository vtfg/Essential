package vitogd.me.essential.commands;
import vitogd.me.essential.Essential;
import vitogd.me.essential.utils.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChat implements CommandExecutor {
    private Essential plugin;

    public ClearChat(Essential plugin) {
        this.plugin = plugin;

        plugin.getCommand("cc").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console_message")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("essentials.cc")) {
            for(int i=0; i < 100; i ++)
            {
                p.sendMessage("");
            }
            p.sendMessage(Utils.chat(plugin.getConfig().getString("ClearChat.chat_cleared")));
            return true;
        } else {
            p.sendMessage(Utils.chat(plugin.getConfig().getString("no_perm_message")));
        }

        return false;
    }
}