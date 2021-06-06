package io.github.zdwolfe.mc.playernotification.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NotificationConfig {
  private final List<Map<String, String>> notifiers;

  public NotificationConfig(final List<Map<String, String>> notifiers) {
    if (notifiers == null) {
      this.notifiers = new ArrayList<>();
    } else {
      this.notifiers = notifiers;
    }
  }

  public List<Map<String, String>> getNotifierConfigs() {
    return notifiers;
  }

  public static NotificationConfig fromFileConfiguration(final Plugin plugin) {
    FileConfiguration config = plugin.getConfig();
    config.options().copyDefaults(true);
    config.addDefault("notifiers", new ArrayList<>());
    plugin.saveDefaultConfig();

    final List<Map<String, String >> notifiers = (List<Map<String, String>>) config.getList("notifiers");
    plugin.getLogger().info(String.format("Found %s notifiers in config.yml.", notifiers.size()));
    return new NotificationConfig(notifiers);
  }
}
