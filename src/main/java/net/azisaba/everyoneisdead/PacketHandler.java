package net.azisaba.everyoneisdead;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class PacketHandler extends PacketAdapter {
    public PacketHandler() {
        super(EveryoneIsDead.INSTANCE, PacketType.Play.Server.ENTITY_METADATA);
    }

    @Override
    public void onPacketSending(PacketEvent e){
        final Entity entity = e.getPacket().getEntityModifier(e).read(0);
        if(!(entity instanceof Player) || entity.getEntityId() == e.getPlayer().getEntityId()){
            return;
        }
        final PacketContainer packet = e.getPacket().deepClone();
        final WrappedDataWatcher dataWatcher = WrappedDataWatcher.getEntityWatcher(entity).deepClone();
        dataWatcher.setObject(7, Float.NaN);
        packet.getWatchableCollectionModifier().write(0, dataWatcher.getWatchableObjects());
        e.setPacket(packet);
    }

}
