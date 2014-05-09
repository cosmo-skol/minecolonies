package com.minecolonies.client.gui;

import com.minecolonies.MineColonies;
import com.minecolonies.lib.Constants;
import com.minecolonies.tileentities.TileEntityTownHall;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiTownHall extends GuiScreen
{
    private TileEntityTownHall tileEntityTownHall;
    private final int numberOfButtons        = 8;
    private final int idBuildTownhall        = 0;
    private final int idRepairTownhall       = 1;
    private final int idRecallCitizens       = 2;
    private final int idToggleSpecialization = 3;
    private final int idRenameColony         = 4;
    private final int idInformation          = 5;
    private final int idActions              = 6;
    private final int idSettings             = 7;
    private int xSize;
    private int ySize;
    private int middleX      = 0;
    private int middleY      = 0;
    private int buttonWidth  = 116;
    private int buttonHeight = 20;
    private int buttonSpan   = 4;
    private int span         = 30;

    private EntityPlayer player;
    private World world;
    private int x;
    private int y;
    private int z;

    private final ResourceLocation background = new ResourceLocation(Constants.MODID + ":" + "textures/gui/guiInformatorBackground.png");

    public GuiTownHall(TileEntityTownHall tileEntityTownHall, EntityPlayer player, World world, int x, int y, int z)
    {
        xSize = 171;
        ySize = 247;
        this.tileEntityTownHall = tileEntityTownHall;
        this.player = player;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void initGui()
    {
        addButtons();
        super.initGui();
    }

    private void addButtons()
    {
        middleX = (width / 2);
        middleY = (height - ySize) / 2;

        buttonList.clear();

        int y = span;
        buttonList.add(new GuiButton(idBuildTownhall, middleX - buttonWidth / 2, middleY + y, buttonWidth, buttonHeight, I18n.format("com.minecolonies.gui.townhall.build")));
        y += buttonHeight + buttonSpan;
        buttonList.add(new GuiButton(idRepairTownhall, middleX - buttonWidth / 2, middleY + y, buttonWidth, buttonHeight, I18n.format("com.minecolonies.gui.townhall.repair")));
        y += buttonHeight + buttonSpan;
        buttonList.add(new GuiButton(idRecallCitizens, middleX - buttonWidth / 2, middleY + y, buttonWidth, buttonHeight, I18n.format("com.minecolonies.gui.townhall.recall")));
        y += buttonHeight + buttonSpan;
        buttonList.add(new GuiButton(idToggleSpecialization, middleX - buttonWidth / 2, middleY + y, buttonWidth, buttonHeight, I18n.format("com.minecolonies.gui.townhall.togglespec")));
        y += buttonHeight + buttonSpan;
        //Current Spec
        y += buttonHeight + buttonSpan;
        buttonList.add(new GuiButton(idRenameColony, middleX - buttonWidth / 2, middleY + y, buttonWidth, buttonHeight, I18n.format("com.minecolonies.gui.townhall.rename")));

        //Bottom navigation
        buttonList.add(new GuiButton(idInformation, middleX - 76, middleY + ySize - 34, 64, buttonHeight, I18n.format("com.minecolonies.gui.townhall.information")));
        buttonList.add(new GuiButton(idActions, middleX - 10, middleY + ySize - 34, 44, buttonHeight, I18n.format("com.minecolonies.gui.townhall.actions")));
        buttonList.add(new GuiButton(idSettings, middleX + xSize / 2 - 50, middleY + ySize - 34, 46, buttonHeight, I18n.format("com.minecolonies.gui.townhall.settings")));
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    private void drawGuiBackground()
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(background);
        int xCoord = (width - xSize) / 2;
        int yCoord = (height - ySize - 10) / 2;
        drawTexturedModalRect(xCoord, yCoord, 0, 0, xSize, ySize);
    }

    private void drawGuiForeground()
    {
        String currentSpec = I18n.format("com.minecolonies.gui.townhall.currentSpecialization");
        String spec = "<Industrial>"; //TODO replace with actual specialisation
        String currentTownhallName = I18n.format("com.minecolonies.gui.townhall.currTownhallName");
        String townhallName = tileEntityTownHall.getCityName();

        fontRendererObj.drawString(currentSpec, middleX - fontRendererObj.getStringWidth(currentSpec) / 2 + 3, middleY + span + 4 * (buttonHeight + buttonSpan), 0x000000);
        fontRendererObj.drawString(spec, middleX - fontRendererObj.getStringWidth(spec) / 2 + 3, middleY + span + 4 * (buttonHeight + buttonSpan) + 11, 0x000000);
        fontRendererObj.drawString(currentTownhallName, middleX - fontRendererObj.getStringWidth(currentTownhallName)/ 2 + 3, middleY + 4, 0x000000);
        fontRendererObj.drawString(townhallName, middleX - fontRendererObj.getStringWidth(townhallName) / 2 + 3, middleY + 13, 0x000000);
    }

    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
        drawGuiBackground();
        drawGuiForeground();

        for(int k = 0; k < this.buttonList.size(); ++k)
        {
            GuiButton guibutton = (GuiButton) this.buttonList.get(k);
            guibutton.drawButton(this.mc, par1, par2);
        }
    }

    @Override
    protected void actionPerformed(GuiButton guiButton)
    {
        switch(guiButton.id)
        {
            case idBuildTownhall:
                break;
            case idRepairTownhall:
                break;
            case idRecallCitizens:
                break;
            case idToggleSpecialization:
                break;
            case idRenameColony:
                player.openGui(MineColonies.instance, 1, world, x, y, z);
                break;
            case idInformation:
                break;
            case idActions:
                break;
            case idSettings:
                break;
        }
    }
}