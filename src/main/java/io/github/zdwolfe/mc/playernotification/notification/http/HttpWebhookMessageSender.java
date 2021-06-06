package io.github.zdwolfe.mc.playernotification.notification.http;

import io.github.zdwolfe.mc.playernotification.config.WebhookConfig;
import io.github.zdwolfe.mc.playernotification.notification.NotificationResult;
import io.github.zdwolfe.mc.playernotification.notification.MessageSender;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Sends webhookConfig.webhookUrl a JSON message such as:
 * { "content": "<username> has joined!" }
 */
public class HttpWebhookMessageSender implements MessageSender {

  public static final String SOURCE = "discord";
  private final WebhookConfig webhookConfig;
  private final Plugin plugin;

  public HttpWebhookMessageSender(final WebhookConfig webhookConfig, final Plugin plugin) {
    this.webhookConfig = webhookConfig;
    this.plugin = plugin;
  }

  @Override
  public Future<NotificationResult> sendMessage(final String message) {

    URL url = null;
    try {
      url = new URL(this.webhookConfig.getWebhookUrl());
    } catch (MalformedURLException e) {
      return CompletableFuture.supplyAsync(() -> new NotificationResult(e, SOURCE));
    }

    final URL finalUrl = url;
    return CompletableFuture.supplyAsync(() -> {
      try {
        HttpURLConnection connection = (HttpURLConnection) finalUrl.openConnection();

        connection.setRequestProperty("Content-Type", "application/json;");
        connection.addRequestProperty("User-Agent", String.format("MinecraftPluginDiscordNotifier"));

        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        try(OutputStream os = connection.getOutputStream()) {
          byte[] input = getJsonRequestString(message).getBytes("utf-8");
          os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(
            new InputStreamReader(connection.getInputStream(), "utf-8"))) {
          StringBuilder response = new StringBuilder();
          String responseLine = null;
          while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
          }
          final String responseString = response.toString();
          plugin.getLogger().info(String.format("Response from discord: %s", responseString));
        }

        return new NotificationResult(SOURCE);

      } catch (Exception e) {
        return new NotificationResult(e, SOURCE);
      }

    });
  }

  private String getJsonRequestString(final String message) {
    return String.format("{\"content\": \"%s\"}", message);
  }

}
