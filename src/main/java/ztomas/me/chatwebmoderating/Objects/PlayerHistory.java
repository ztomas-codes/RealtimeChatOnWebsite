package ztomas.me.chatwebmoderating.Objects;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PlayerHistory {

    private static ArrayList<PlayerHistory> playerHistories = new java.util.ArrayList<>();
    private OfflinePlayer player;
    private ArrayList<Message> messages;

    private LocalDateTime messageTime;

    public PlayerHistory(OfflinePlayer player, ArrayList<Message> messages) {

        this.player = player;
        this.messages = messages;

        playerHistories.add(this);
    }


    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void addMessage(String message) {
        messages.add(new Message(LocalDateTime.now(), message));
    }

    public OfflinePlayer getOfflinePlayer() {
        return player;
    }

    public static ArrayList<PlayerHistory> getPlayerHistories() {
        return playerHistories;
    }

    public static PlayerHistory getPlayerHistoryFromPlayer(OfflinePlayer player) {

        for (PlayerHistory playerHistory: playerHistories) {
            if (playerHistory.getOfflinePlayer().equals(player)) {
                return playerHistory;
            }
        }
        return null;
    }
}
