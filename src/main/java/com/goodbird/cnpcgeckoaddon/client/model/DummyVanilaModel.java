package com.goodbird.cnpcgeckoaddon.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DummyVanilaModel extends ModelBase {
    public GeoEntityRenderer renderer;

    public DummyVanilaModel() {
    }

    public DummyVanilaModel(GeoEntityRenderer renderer) {
        this.renderer = renderer;
    }

    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
    }
}

