package me.nyarikori.multiCMD.command;

import dev.rollczi.litecommands.annotations.async.Async;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.join.Join;
import dev.rollczi.litecommands.annotations.permission.Permission;
import me.nyarikori.commons.annotation.Autowired;
import me.nyarikori.commons.annotation.Component;
import me.nyarikori.commons.annotation.command.CommandType;
import me.nyarikori.commons.annotation.command.NCommand;
import me.nyarikori.multiCMD.service.ConfigService;
import me.nyarikori.multiCMD.service.LanguageService;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

/**
 * @author NyariKori
 */
@Component
@NCommand(commandType = CommandType.LITE_COMMANDS)
@Command(name = "mcmd", aliases = {"multicmd", "multicommand", "multicommands"})
public class MultiCMDCommand {
    @Autowired
    private MiniMessage miniMessage;

    @Autowired
    private ConfigService configService;

    @Autowired
    private LanguageService languageService;

    @Async
    @Execute(name = "reload")
    @Permission("multicmd.reload")
    void reload(@Context CommandSender sender) {
        configService.reload();
        languageService.reload();
        sender.sendMessage(miniMessage.deserialize(languageService.getMessage("reload-message")));
    }

    @Async
    @Execute(name = "cmd")
    @Permission("multicmd.use")
    void cmd(@Context CommandSender sender, @Join String commands) {
        if (!commands.contains("&")) {
            sender.sendMessage(miniMessage.deserialize(languageService.getMessage("no-separator-message")));
            return;
        }

        String[] multicommands = commands.split(configService.get("command-separator-symbol"));

        for (String command : multicommands) {
            Bukkit.getServer().dispatchCommand(sender, command);
        }

        sender.sendMessage(miniMessage.deserialize(languageService.getMessage("multicmd-use-message")));
    }
}
