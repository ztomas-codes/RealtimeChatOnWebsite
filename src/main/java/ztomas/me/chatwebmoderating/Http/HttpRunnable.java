package ztomas.me.chatwebmoderating.Http;

import com.sammy.httplib.server.HttpServer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import ztomas.me.chatwebmoderating.Objects.Message;
import ztomas.me.chatwebmoderating.Objects.PlayerHistory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

public class HttpRunnable implements Runnable {
    @Override
    public void run() {
        HttpServer server = new HttpServer();

        server.get("/", (req, res) -> {

            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("index.html");
            String data = readFromInputStream(inputStream);

            res.send(data);
            res.flush();
        });

        server.get("/players", (req, res) -> {
            String response = "[";
            for (PlayerHistory history: PlayerHistory.getPlayerHistories())
            {
                response += "{\"username\": \"" + history.getOfflinePlayer().getName() + "\"}";
                if (PlayerHistory.getPlayerHistories().indexOf(history) != PlayerHistory.getPlayerHistories().size() - 1) {
                    response += ",";
                }
            }
            response += "]";
            res.send(response);
            res.flush();
        });

        server.get("/messages/:username", (req, res) -> {


            OfflinePlayer player = Bukkit.getServer().getOfflinePlayer(req.getParam("username"));
            PlayerHistory playerHistory = PlayerHistory.getPlayerHistoryFromPlayer(player);

            String response = "[";
            for (Message message: playerHistory.getMessages()) {
                DateTimeFormatter format =  DateTimeFormatter.ofPattern("d.M.yyyy (HH:mm)");

                response += "{\"date\": \"" + message.getMessageTime().format(format) + "\", \"message\": \"" + message.getMessage() + "\"}";
                if (playerHistory.getMessages().indexOf(message) != playerHistory.getMessages().size() - 1) {
                    response += ",";
                }
            }

             response += "]";

            res.send(response);
            res.flush();
        });

        try {
            server.listen(9586, "127.0.0.1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }


}
