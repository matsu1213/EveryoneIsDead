package net.azisaba.everyoneisdead;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class PacketHandler extends PacketAdapter {
    public PacketHandler() {
        super(EveryoneIsDead.INSTANCE, PacketType.Play.Server.ENTITY_METADATA);
    }

    @Override
    public void onPacketSending(PacketEvent e){
        final PacketContainer packet = e.getPacket();
        final Entity entity = packet.getEntityModifier(e).read(0);
        if(!(entity instanceof LivingEntity) || entity == e.getPlayer()){
            return;
        }
        final WrappedDataWatcher dataWatcher = WrappedDataWatcher.getEntityWatcher(entity).deepClone();
        dataWatcher.setObject(7, Float.NaN);
        packet.getWatchableCollectionModifier().write(0, dataWatcher.getWatchableObjects());
        e.setPacket(packet);
    }

}
