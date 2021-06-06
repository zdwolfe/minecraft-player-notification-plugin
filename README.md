# minecraft-plugin-player-notification
A Minecraft Spigot/Bukkit server plugin that sends notifications when players join/leave the server.

Currently supported notifications:
1. Discord

# Local Development

### Testing a local build
```
mvn package
``` 

```
docker run --rm -e EULA=true  -p 25565:25565 -v $(pwd)/target:/data/plugins cmunroe/spigot:1.16.4
```

and you should see the following in the server logs as it boots up 

```
[03:36:19] [Server thread/INFO]: [PlayerNotificationPlugin] Enabling PlayerNotificationPlugin v1.0
```


Then start the minecraft client and connect to ``127.0.0.1:25565``.


# Configuration
Run your minecraft Spigot or Bukkit server once with the PlayerNotificationPlugin installed. 
After it runs for the first time it will create the directory structure:

```
plugins/
    PlayerNotificationPlugin/
        config.yml
```

Open up ``config.yml`` and edit, replacing WEBHOOK-URL-GOES-HERE with the webhook URL configured in Discord:

```
notifiers:
  -
    type: "webhook"
    url: "WEBHOOK-URL-GOES-HERE"
    leaveFormat: "%s left!"
    joinFormat: "%s joined!"
```


# Discord Webhook Setup
Visit the [official Discord documentation](https://support.discord.com/hc/en-us/articles/228383668-Intro-to-Webhooks) on webhooks for instructions on how to create a webhook.