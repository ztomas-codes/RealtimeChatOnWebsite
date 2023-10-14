package ztomas.me.chatwebmoderating.Commands;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ztomas.me.chatwebmoderating.Objects.Message;
import ztomas.me.chatwebmoderating.Objects.PlayerHistory;

public class PlayerChatHistoryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {


        if (!(commandSender instanceof Player))
        {
            commandSender.sendMessage("You must be a player to use this command!");
            return false;
        }

        Player p = (Player) commandSender;

        if (strings.length == 0) {
            p.sendMessage("You must specify a player!");
            return false;
        }

        OfflinePlayer target = p.getServer().getOfflinePlayer(strings[0]);

        for (Message message: PlayerHistory.getPlayerHistoryFromPlayer(target).getMessages()) {
            p.sendMessage(" -" + message.getMessage());
        }

        return false;
    }
}
