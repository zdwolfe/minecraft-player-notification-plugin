package io.github.zdwolfe.mc.playernotification.notification;

import org.bukkit.entity.Player;

public class Notification {
  public Notification(Player player, Direction playerDirection) {
    this.player = player;
    this.playerDirection = playerDirection;
  }

  Player player;
  Direction playerDirection;

  public enum Direction {
    joined,
    left
  }
}
