package me.improperissues.ayocoolitems.events;

import me.improperissues.ayocoolitems.files.UUIDLogs;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class ServerEvents implements Listener {

    @EventHandler
    public static void ServerCommandEvent(ServerCommandEvent e) {
        String command = e.getCommand();
        switch (command.toLowerCase().trim()) {
            case "save-all":
                UUIDLogs.save();
                break;
        }
    }

    @EventHandler
    public static void PlayerCommandEvent(PlayerCommandPreprocessEvent e) {
        String command = e.getMessage();
        switch (command.toLowerCase().trim()) {
            case "/save-all":
                UUIDLogs.save();
                break;
        }
    }
}
