package io.github.zdwolfe.mc.playernotification.event;

import io.github.zdwolfe.mc.playernotification.Main;
import io.github.zdwolfe.mc.playernotification.notification.Notification;
import io.github.zdwolfe.mc.playernotification.notification.Notifiers;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
  private Main plugin;
  private final Notifiers notifiers;

  public PlayerJoinListener(Main plugin, Notifiers notifiers) {
    this.plugin = plugin;
    this.notifiers = notifiers;
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent ev) {
    plugin.getLogger().info(String.format("Player [%s] joined!", ev.getPlayer().getDisplayName()));
    final Notification notification = new Notification(ev.getPlayer(), Notification.Direction.joined);
    notifiers.notifyAll(notification);
  }
}
