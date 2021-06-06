package io.github.zdwolfe.mc.playernotification.notification.discord;

import io.github.zdwolfe.mc.playernotification.notification.*;

import java.util.concurrent.Future;

public class DiscordNotifier implements Notifier {
  private final MessageSender sender;
  private final NotificationFormatter formatter;

  public DiscordNotifier(final MessageSender sender, final NotificationFormatter formatter) {
    this.sender = sender;
    this.formatter = formatter;
  }

  public MessageSender getSender() {
    return sender;
  }

  public Future<NotificationResult> send(Notification notification) {
    final String message = formatter.format(notification);
    if (message == null) return null;

    return sender.sendMessage(message);
  }
}
