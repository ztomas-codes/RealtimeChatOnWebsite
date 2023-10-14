package ztomas.me.chatwebmoderating;

import com.sammy.httplib.server.HttpServer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import ztomas.me.chatwebmoderating.Commands.PlayerChatHistoryCommand;
import ztomas.me.chatwebmoderating.Http.HttpRunnable;
import ztomas.me.chatwebmoderating.Objects.Message;
import ztomas.me.chatwebmoderating.Objects.PlayerHistory;

import java.util.ArrayList;

public final class ChatWebModerating extends JavaPlugin {

    private static Plugin plugin;

    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        //registering commands
        getCommand("chathistory").setExecutor(new PlayerChatHistoryCommand());

        for (Player p: getServer().getOnlinePlayers()) {
            PlayerHistory playerHistory = new PlayerHistory(p, new ArrayList<Message>());
        }

        plugin = this;

        Bukkit.getScheduler().scheduleAsyncDelayedTask(this, new HttpRunnable(), 1L);
    }

    @Override
    public void onDisable() {
        HttpServer.close();
    }
}
