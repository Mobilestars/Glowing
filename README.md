# ✨ Glowing

A **Minecraft Spigot/Paper plugin** that makes players **glow periodically**, providing a visual effect and optional gameplay consequences.  
Perfect for events, minigames, or PvP servers wanting dynamic glowing effects.

---

## ⚙️ Features

- 🌟 Periodically applies **Glowing potion effect** to all online players  
- ⏱ Configurable duration and interval of the glow effect  
- ⚰️ Optionally penalizes players who log out during a glow (drops inventory and kills the player)  
- 💬 Sends messages to players when glow starts and ends  
- 🔧 Automatically handles glow removal on logout  

---

## 📁 Installation

1. Download the plugin `.jar` file  
2. Place it inside your server’s `plugins/` folder  
3. Start or reload your server — the glow cycle begins automatically  

---

## ⚙️ Configuration (`config.yml`)

```yaml
# ==== Glowing ====
glowing: true
glow-interval-minutes: 20
glow-duration-seconds: 60
glow-die-on-logout: true
```

---

## 💡 How it Works

- **Glow cycle**: Every interval, all online players receive a glowing effect for a defined duration  
- **Logout handling**: Players who log out while glowing can optionally drop their inventory and die  
- **Glow removal**: Automatically removes the effect when the duration ends or the player logs out  
- **Messaging**: Players are notified when their glow starts and ends  

---

## 🧩 Developer Information

**Main classes:**
- `de.scholle.glowing.Glowing` – main plugin class  
- `de.scholle.glowing.GlowManager` – handles glowing cycles and player state  
- `de.scholle.glowing.GlowLogoutListener` – handles player logout events  
- `de.scholle.glowing.utils.Msg` – utility for sending formatted messages  

**Key behavior:**
- Uses `BukkitRunnable` for timing and effect management  
- Uses `PotionEffectType.GLOWING` to apply the visual effect  
- Tracks active glows per player using a `HashMap<UUID, BukkitRunnable>`  

---

## 🧰 Compatibility

- ✅ Works on **Spigot**, **Paper**, and **Purpur**  
- 🧱 Compatible with **Minecraft 1.18+**  
- ⚙️ Requires **Java 17+**

---

## 🧑‍💻 Author

**Developed by:** Scholle  
**Package:** `de.scholle.glowing`

---

## 📜 License

This project is licensed under the **Apache License 2.0**.  
You may use, modify, and distribute it in compliance with the terms of that license.  
For more information, see: [http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)
