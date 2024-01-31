package com.goodbird.cnpcgeckoaddon.network;

import com.goodbird.cnpcgeckoaddon.entity.EntityCustomModel;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;
import noppes.npcs.entity.EntityCustomNpc;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.builder.RawAnimation;

import java.util.function.Supplier;

public class PacketSyncAnimation {
    private int id;
    private AnimationBuilder builder;

    public PacketSyncAnimation(int entityId, AnimationBuilder builder) {
        this.id = entityId;
        this.builder = builder;
    }

    public PacketSyncAnimation(){

    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(id);

        CompoundTag compound = new CompoundTag();
        ListTag animList = new ListTag();
        for(RawAnimation anim: builder.getRawAnimationList()){
            CompoundTag animTag = new CompoundTag();
            animTag.putString("name", anim.animationName);
            if(anim.loopType!=null) {
                animTag.putInt("loop", ((ILoopType.EDefaultLoopTypes) anim.loopType).ordinal());
            }else{
                animTag.putInt("loop",1);
            }
            animList.add(animTag);
        }
        compound.put("anims",animList);
        buf.writeNbt(compound);
    }

    public static PacketSyncAnimation decode(FriendlyByteBuf buf) {
        int id = buf.readInt();
        AnimationBuilder builder = new AnimationBuilder();
        CompoundTag compound = buf.readNbt();
        ListTag animList = compound.getList("anims",10);
        for(int i=0;i<animList.size();i++){
            CompoundTag animTag = (CompoundTag) animList.get(i);
            builder.addAnimation(animTag.getString("name"),
                    ILoopType.EDefaultLoopTypes.values()[animTag.getInt("loop")]);
        }
        return new PacketSyncAnimation(id,builder);
    }

    public static void handle(PacketSyncAnimation packet, Supplier<NetworkEvent.Context> ctx) {
        Entity entity = Minecraft.getInstance().player.getCommandSenderWorld().getEntity(packet.id);
        if(!(entity instanceof EntityCustomNpc)) return;
        EntityCustomNpc npc = (EntityCustomNpc) entity;
        if(npc.modelData==null || !(npc.modelData.getEntity(npc) instanceof EntityCustomModel)) return;
        EntityCustomModel entityCustomModel = (EntityCustomModel) npc.modelData.getEntity(npc);
        entityCustomModel.manualAnim = packet.builder;
    }
}

