package com.xiii.moocowio.core.listener;

import io.github.retrooper.packetevents.event.PacketListenerAbstract;
import io.github.retrooper.packetevents.event.PacketListenerPriority;

public class PacketListener extends PacketListenerAbstract {
    public PacketListener() {
        super(PacketListenerPriority.HIGH);
    }
}
