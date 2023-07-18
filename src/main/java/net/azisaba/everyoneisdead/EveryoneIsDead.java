package net.azisaba.everyoneisdead;

import com.comphenix.protocol.ProtocolLibrary;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class EveryoneIsDead extends JavaPlugin {

    public static EveryoneIsDead INSTANCE;
    private PacketHandler handler;

    @Override
    public void onEnable() {
        // Plugin startup logic
        INSTANCE = this;
        handler = new PacketHandler();
        ProtocolLibrary.getProtocolManager().addPacketListener(handler);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ProtocolLibrary.getProtocolManager().removePacketListener(handler);
    }
}
