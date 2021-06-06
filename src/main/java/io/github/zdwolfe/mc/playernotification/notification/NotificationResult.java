package io.github.zdwolfe.mc.playernotification.notification;

public class NotificationResult {
  private final Exception e;
  private final String source;

  public NotificationResult(Exception e, String source) {
    this.e = e;
    this.source = source;
  }

  public NotificationResult(String source) {
    this(null, source);
  }

  public String getSource() {
    return this.source;
  }

  public Exception getException() {
    return this.e;
  }

  public boolean wasSuccessful() {
    return this.e == null;
  }
}
