package me.nyarikori.multiCMD;

import me.nyarikori.bukkit.injector.BukkitInjectorInitializer;
import me.nyarikori.commons.container.DependencyContainer;
import me.nyarikori.multiCMD.listener.CommandListener;
import me.nyarikori.multiCMD.provider.CustomDependencyProvider;
import me.nyarikori.multiCMD.service.LanguageService;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MultiCMD extends JavaPlugin {
    private LanguageService languageService;

    @Override
    public void onEnable() {
        BukkitInjectorInitializer.setProvider(new CustomDependencyProvider(this));
        BukkitInjectorInitializer.init(this, "me.nyarikori");

        languageService = (LanguageService) DependencyContainer.getDependency(LanguageService.class);
        getServer().getPluginManager().registerEvents((Listener) DependencyContainer.getDependency(CommandListener.class), this);


        getLogger().info(languageService.getMessage("enabled-message"));
    }

    @Override
    public void onDisable() {
        getLogger().info(languageService.getMessage("disabled-message"));
    }
}
