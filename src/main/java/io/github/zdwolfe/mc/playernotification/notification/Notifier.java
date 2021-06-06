package io.github.zdwolfe.mc.playernotification.notification;

import java.util.concurrent.Future;

public interface Notifier {
  Future<NotificationResult> send(Notification notification);
}
