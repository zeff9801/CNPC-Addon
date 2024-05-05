package com.goodbird.cnpcgeckoaddon.client.gui;

import com.goodbird.cnpcgeckoaddon.data.CustomModelData;
import com.goodbird.cnpcgeckoaddon.data.CustomModelDataProvider;
import com.goodbird.cnpcgeckoaddon.data.ICustomModelData;
import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.util.*;
import noppes.npcs.entity.EntityNPCInterface;

public class SubGuiModelExtras extends SubGuiInterface implements ITextfieldListener, ISubGuiListener {
    public SubGuiModelExtras(EntityNPCInterface npc){
        this.npc = npc;
        closeOnEsc = true;
    }

    @Override
    public void initGui() {
        super.initGui();
        int y = guiTop + 44;
        addLabel(new GuiNpcLabel(1,"Head Bone Name", guiLeft - 85, y + 5,0xffffff));
        addTextField(new GuiNpcTextField(1,this, fontRenderer, guiLeft + 50, y, 200, 20, getModelData(npc).getHeadBoneName()));
        y+=23;
        addLabel(new GuiNpcLabel(2,"Transition Length (ticks)", guiLeft - 85, y + 5,0xffffff));
        GuiNpcTextField transitionLength = new GuiNpcTextField(2,this, fontRenderer, guiLeft + 50, y,
                200, 20, ""+getModelData(npc).getTransitionLengthTicks());
        transitionLength.setNumbersOnly();
        transitionLength.setMinMaxDefault(0, Integer.MAX_VALUE, getModelData(npc).getTransitionLengthTicks());
        addTextField(transitionLength);
        addButton(new GuiNpcButton(670, width - 22, 2, 20, 20, "X"));
    }

    public ICustomModelData getModelData(EntityNPCInterface npc){
        return npc.getCapability(CustomModelDataProvider.DATA_CAP, null);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        super.actionPerformed(button);
        if(button.id == 670){
            close();
        }
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if(textfield.id == 1){
            getModelData(npc).setHeadBoneName(textfield.getText());
        }
        if(textfield.id == 2){
            getModelData(npc).setTransitionLengthTicks(textfield.getInteger());
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subGuiInterface) {
        initGui();
    }
}