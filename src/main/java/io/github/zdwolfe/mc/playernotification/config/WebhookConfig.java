package io.github.zdwolfe.mc.playernotification.config;

public class WebhookConfig {
  private final String webhookUrl;

  public WebhookConfig(String webhookUrl) {
    this.webhookUrl = webhookUrl;
  }

  public String getWebhookUrl() {
    return this.webhookUrl;
  }
}
