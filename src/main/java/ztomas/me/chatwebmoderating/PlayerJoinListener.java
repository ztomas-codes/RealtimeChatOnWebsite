package ztomas.me.chatwebmoderating;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ztomas.me.chatwebmoderating.Objects.PlayerHistory;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        new PlayerHistory(event.getPlayer(), null);
    }
}
