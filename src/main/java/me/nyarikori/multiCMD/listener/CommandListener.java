package me.nyarikori.multiCMD.listener;

import me.nyarikori.commons.annotation.Autowired;
import me.nyarikori.commons.annotation.Component;
import me.nyarikori.multiCMD.service.ConfigService;
import me.nyarikori.multiCMD.service.LanguageService;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * @author NyariKori
 */
@Component
public class CommandListener implements Listener {
    @Autowired
    private MiniMessage miniMessage;
    @Autowired
    private ConfigService configService;
    @Autowired
    private LanguageService languageService;

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPermission("multicmd.use")) {
            player.sendMessage(miniMessage.deserialize(languageService.getMessage("no-permission-message")));
            return;
        }

        String commandMessage = event.getMessage().replaceFirst("/", "");
        
        if (!commandMessage.contains("&")) {
            return;
        }

        String[] multicommands = commandMessage.split(configService.get("command-separator-symbol"));

        for (String command : multicommands) {
            System.out.println(command);
            player.performCommand(command);
        }

        event.setCancelled(true);
    }
}
