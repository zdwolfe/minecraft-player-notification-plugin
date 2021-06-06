package io.github.zdwolfe.mc.playernotification.event;

import io.github.zdwolfe.mc.playernotification.Main;
import io.github.zdwolfe.mc.playernotification.notification.Notification;
import io.github.zdwolfe.mc.playernotification.notification.Notifiers;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {
  private Main plugin;
  private final Notifiers notifiers;

  public PlayerLeaveListener(Main plugin, Notifiers notifiers) {
    this.plugin = plugin;
    this.notifiers = notifiers;
  }

  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent ev) {
    plugin.getLogger().info(String.format("Player [%s] quit!", ev.getPlayer().getDisplayName()));
    final Notification notification = new Notification(ev.getPlayer(), Notification.Direction.left);
    notifiers.notifyAll(notification);
  }
}
