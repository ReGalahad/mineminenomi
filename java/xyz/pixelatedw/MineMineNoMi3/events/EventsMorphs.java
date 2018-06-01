package xyz.pixelatedw.MineMineNoMi3.events;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.util.glu.Project;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.EntityZoanMorph;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.RenderZoanMorph;
import xyz.pixelatedw.MineMineNoMi3.events.customevents.MorphRenderEvent;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;

public class EventsMorphs 
{
	
	private Minecraft mc;
	
	public EventsMorphs(Minecraft mc)
	{	
		this.mc = mc;
	}
	
	@SubscribeEvent
	public void onEntityConstructing(EntityJoinWorldEvent event) 
	{
		if(event.entity instanceof EntityPlayer)
		{
			EntityPlayer owner = (EntityPlayer) event.entity;
			ExtendedEntityStats props = ExtendedEntityStats.get(owner);
			
			if(props.getUsedFruit().equals("ushiushibison"))
			{
				for(EntityLivingBase zm : WyHelper.getEntitiesNear(owner, 1.5, EntityZoanMorph.class))
					zm.setDead();
				owner.removePotionEffect(Potion.invisibility.id);
				props.setZoanPoint("n/a");
			}
		}
	}
	
	@SubscribeEvent
	public void morphRendering(MorphRenderEvent event)
	{ 
		EntityPlayer owner = Minecraft.getMinecraft().thePlayer;
		ExtendedEntityStats props = ExtendedEntityStats.get(owner);

		if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 0)
		{
			event.setCanceled(true);
			
			/*if(props.getUsedFruit().equals("dokudoku") && event.morph instanceof EntityMorphVenomDemon)
				event.setCanceled(true);*/
		}
	}
	
	@SubscribeEvent
	public void morphHandRendering(RenderHandEvent event)
	{
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		ExtendedEntityStats props = ExtendedEntityStats.get(player);

		if(( (props.getUsedFruit().equals("dokudoku") || props.getUsedFruit().equals("ushiushibison")) && WyHelper.getEntitiesNear(player, 1, EntityZoanMorph.class).size() > 0) || props.hasBusoHakiActive() )
		{
			event.setCanceled(true);
	
			this.renderHand((EntityClientPlayerMP) player, 0, 0);
		}
	}

    private void renderHand(EntityClientPlayerMP player, float f, int i)
    {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        
        float f1 = 0.07F;

        if (this.mc.gameSettings.anaglyph)
        {
            GL11.glTranslatef((float)(-(i * 2 - 1)) * f1, 0.0F, 0.0F);
        }
        
        Project.gluPerspective(this.mc.gameSettings.fovSetting, (float)this.mc.displayWidth / (float)this.mc.displayHeight, 0.20F, (float)(this.mc.gameSettings.renderDistanceChunks * 16) * 2.0F);

        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        
        if (this.mc.gameSettings.anaglyph)
        {
            GL11.glTranslatef((float)(i * 2 - 1) * 0.1F, 0.0F, 0.0F);
        }
        
        GL11.glPushMatrix();

        if (this.mc.gameSettings.viewBobbing)
        {
            this.setupViewBobbing(f);
        }
        
        if (this.mc.gameSettings.thirdPersonView == 0 && !this.mc.renderViewEntity.isPlayerSleeping() && !this.mc.gameSettings.hideGUI && !this.mc.playerController.enableEverythingIsScrewedUpMode())
        {
        	Minecraft.getMinecraft().entityRenderer.enableLightmap((double)f);
        	if(player.inventory.getCurrentItem() != null)
	            Minecraft.getMinecraft().entityRenderer.itemRenderer.renderItemInFirstPerson(f);
        	else
	            renderCustomHand(player);
            Minecraft.getMinecraft().entityRenderer.disableLightmap((double)f);
        }

        GL11.glPopMatrix();
        
        if (this.mc.gameSettings.viewBobbing)
        {
            this.setupViewBobbing(f);
        }
    }
	
    private void renderCustomHand(EntityClientPlayerMP player)
    {
    	ExtendedEntityStats props = ExtendedEntityStats.get(player);
    	
        float f5;
        float f6;
        float f7;

        GL11.glPushMatrix();
        float f13 = 0.8F;
        f5 = player.getSwingProgress(0);
        f6 = MathHelper.sin(f5 * (float)Math.PI);
        f7 = MathHelper.sin(MathHelper.sqrt_float(f5) * (float)Math.PI);
        GL11.glTranslatef(-f7 * 0.3F, MathHelper.sin(MathHelper.sqrt_float(f5) * (float)Math.PI * 2.0F) * 0.4F, -f6 * 0.4F);
        GL11.glTranslatef(0.8F * f13, -0.75F * f13 - (1.0F - 1) * 0.6F, -0.9F * f13);
        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        f5 = player.getSwingProgress(0);
        f6 = MathHelper.sin(f5 * f5 * (float)Math.PI);
        f7 = MathHelper.sin(MathHelper.sqrt_float(f5) * (float)Math.PI);
        GL11.glRotatef(f7 * 70.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-f6 * 20.0F, 0.0F, 0.0F, 1.0F);
        if(props.hasBusoHakiActive())
        	this.mc.getTextureManager().bindTexture(ID.HANDTEXTURE_ZOANMORPH_BUSO);
        else
        	this.mc.getTextureManager().bindTexture(player.getLocationSkin());
        GL11.glTranslatef(-1.0F, 3.6F, 3.5F);
        GL11.glRotatef(120.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(200.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(1.0F, 1.0F, 1.0F);
        GL11.glTranslatef(5.6F, 0.0F, 0.0F);
        Render render = null;
        
        if(props.getZoanPoint().toLowerCase().equals("n/a"))
        {
        	render = RenderManager.instance.getEntityRenderObject(this.mc.thePlayer);
            RenderPlayer renderplayer = (RenderPlayer)render;
            float i = 1.0F;
            GL11.glScalef(i, i, i);
            renderplayer.renderFirstPersonArm(this.mc.thePlayer);
        }
        else
        {
        	for(EntityLivingBase zm : WyHelper.getEntitiesNear(this.mc.thePlayer, 1.2, EntityZoanMorph.class))
            	render = RenderManager.instance.getEntityRenderObject(zm);
        	RenderZoanMorph renderZoan = (RenderZoanMorph)render;
            float i = 1.0F;
            GL11.glScalef(i, i, i);
            GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(0.2f, 0.0f, -0.5f);
            renderZoan.renderFirstPersonArm(this.mc.thePlayer);   
        }
        
        GL11.glPopMatrix();
        
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        RenderHelper.disableStandardItemLighting();
    }
    
    private void setupViewBobbing(float p_78475_1_)
    {
        if (this.mc.renderViewEntity instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)this.mc.renderViewEntity;
            float f1 = entityplayer.distanceWalkedModified - entityplayer.prevDistanceWalkedModified;
            float f2 = -(entityplayer.distanceWalkedModified + f1 * p_78475_1_);
            float f3 = entityplayer.prevCameraYaw + (entityplayer.cameraYaw - entityplayer.prevCameraYaw) * p_78475_1_;
            float f4 = entityplayer.prevCameraPitch + (entityplayer.cameraPitch - entityplayer.prevCameraPitch) * p_78475_1_;
            GL11.glTranslatef(MathHelper.sin(f2 * (float)Math.PI) * f3 * 0.5F, -Math.abs(MathHelper.cos(f2 * (float)Math.PI) * f3), 0.0F);
            GL11.glRotatef(MathHelper.sin(f2 * (float)Math.PI) * f3 * 3.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(Math.abs(MathHelper.cos(f2 * (float)Math.PI - 0.2F) * f3) * 5.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(f4, 1.0F, 0.0F, 0.0F);
        }
    }

}
