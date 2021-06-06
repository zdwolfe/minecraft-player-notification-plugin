package io.github.zdwolfe.mc.playernotification.notification;

public class NotificationFormatter {
  private String leaveFormat;
  private String joinFormat;

  public NotificationFormatter(final String leaveFormat, final String joinFormat) {
    this.leaveFormat = leaveFormat;
    this.joinFormat = joinFormat;
  }

  public String format(final Notification notification) {
    if (notification.playerDirection == Notification.Direction.joined) {
      return String.format(joinFormat, notification.player.getDisplayName());
    } else if (notification.playerDirection == Notification.Direction.left) {
      return String.format(leaveFormat, notification.player.getDisplayName());
    } else {
      return null;
    }
  }
}
