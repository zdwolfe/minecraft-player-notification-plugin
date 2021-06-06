package io.github.zdwolfe.mc.playernotification.notification;

import io.github.zdwolfe.mc.playernotification.config.NotificationConfig;
import io.github.zdwolfe.mc.playernotification.config.WebhookConfig;
import io.github.zdwolfe.mc.playernotification.notification.discord.DiscordNotifier;
import io.github.zdwolfe.mc.playernotification.notification.http.HttpWebhookMessageSender;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NotifierFactory {

  public static final String WEBHOOK = "webhook";

  public static Notifiers getNotifiers(final Plugin plugin, final NotificationConfig notificationConfig) {

    List<Notifier> notifiers = new ArrayList<>();

    final List<Map<String, String>> configs = notificationConfig.getNotifierConfigs();
    for (Map<String, String> config : configs) {
      if (WEBHOOK.equals(config.get("type"))) {
        final NotificationFormatter formatter = new NotificationFormatter(
            config.get("leaveFormat"),
            config.get("joinFormat")
        );
        final DiscordNotifier notifier = new DiscordNotifier(
            new HttpWebhookMessageSender(
                new WebhookConfig(config.get("url")), plugin
            ),
            formatter
        );
        notifiers.add(notifier);
      }
    }

    return new Notifiers(notifiers, plugin);
  }
}
