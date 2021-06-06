package io.github.zdwolfe.mc.playernotification;

import io.github.zdwolfe.mc.playernotification.config.NotificationConfig;
import io.github.zdwolfe.mc.playernotification.event.PlayerJoinListener;
import io.github.zdwolfe.mc.playernotification.event.PlayerLeaveListener;
import io.github.zdwolfe.mc.playernotification.notification.NotifierFactory;
import io.github.zdwolfe.mc.playernotification.notification.Notifiers;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {
  @Override
  public void onEnable() {
    Notifiers notifiers = NotifierFactory.getNotifiers(this, NotificationConfig.fromFileConfiguration(this));
    Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this, notifiers),this);
    Bukkit.getPluginManager().registerEvents(new PlayerLeaveListener(this, notifiers),this);
    getLogger().info(String.format("Enabled %s", this.getName()));
  }
}
