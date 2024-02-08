package com.goodbird.cnpcgeckoaddon.data;

import net.minecraft.nbt.NBTTagCompound;

public interface ICustomModelData {
    String getModel();

    void setModel(String model);

    String getAnimFile();

    void setAnimFile(String animFile);

    String getIdleAnim();

    void setIdleAnim(String idleAnim);

    String getWalkAnim();

    void setWalkAnim(String walkAnim);

    String getMeleeAttackAnim();

    void setMeleeAttackAnim(String meleeAttackAnim);

    String getHurtAnim();

    void setHurtAnim(String hurtAnim);

    String getRangedAttackAnim();

    void setRangedAttackAnim(String rangedAttackAnim);

    NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound);

    void readFromNBT(NBTTagCompound nbttagcompound);
}