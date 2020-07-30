package vitogd.me.essential;
import org.bukkit.plugin.java.JavaPlugin;

import vitogd.me.essential.commands.ClearChat;
import vitogd.me.essential.commands.FlyCommand;
import vitogd.me.essential.commands.Gamemode;
import vitogd.me.essential.commands.Warp.Warp;
import vitogd.me.essential.commands.Warp.delWarp;
import vitogd.me.essential.commands.Warp.setWarp;

public final class Essential extends JavaPlugin {

    @Override
    public void onEnable() {
        loadConfig();

        // Plugins
        new FlyCommand(this);
        new Gamemode(this);
        new ClearChat(this);
        // Warp
        new Warp(this);
        new setWarp(this);
        new delWarp(this);
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {
        saveDefaultConfig();
    }
}