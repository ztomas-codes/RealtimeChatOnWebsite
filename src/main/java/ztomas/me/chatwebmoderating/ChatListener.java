package ztomas.me.chatwebmoderating;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import ztomas.me.chatwebmoderating.Objects.PlayerHistory;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(PlayerChatEvent event) {
        String message = event.getMessage();

        Player p = event.getPlayer();

        PlayerHistory playerHistory = PlayerHistory.getPlayerHistoryFromPlayer(p);
        playerHistory.addMessage(message);
    }

}
