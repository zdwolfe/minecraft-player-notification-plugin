package io.github.zdwolfe.mc.playernotification.notification;

import java.util.concurrent.Future;

public interface MessageSender {
  Future<NotificationResult> sendMessage(String message);
}
