package io.github.zdwolfe.mc.playernotification.notification;

import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class Notifiers {
  private final List<Notifier> notifiers;
  private final Plugin plugin;

  public Notifiers(final List<Notifier> notifiers, Plugin plugin) {
    this.plugin = plugin;
    if (notifiers == null) {
      this.notifiers = new ArrayList<>();
    } else {
      this.notifiers = notifiers;
    }
  }

  public void notifyAll(final Notification notification) {
    final List<Future<NotificationResult>> resultFutures = notifiers.parallelStream()
        .map(notifier -> notifier.send(notification))
        .collect(Collectors.toList());

    final List<NotificationResult> results = resultFutures.stream()
        .map(future -> {
          try {
            return future.get();
          } catch (Exception e) {
            return new NotificationResult(e, "Notifiers");
          }
        })
        .collect(Collectors.toList());

    for (NotificationResult result : results) {
      this.plugin.getLogger().info(String.format("%s was successful? : %s", result.getSource(), result.wasSuccessful()));
      if (result.getException() != null) {
        this.plugin.getLogger().info(String.format("%s encountered exception: %s", result.getSource(), result.getException()));
      }
    }
  }
}
