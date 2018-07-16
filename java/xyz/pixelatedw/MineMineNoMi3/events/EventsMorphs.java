package xyz.pixelatedw.MineMineNoMi3.events;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.util.glu.Project;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.RenderZoanMorph;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.models.ModelPowerBison;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.models.ModelSpeedBison;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.models.ModelVenomDemon;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncInfo;

public class EventsMorphs
{

	private Minecraft mc;

	// Doku Doku no Mi
	private RenderZoanMorph morphVenomDemon = new RenderZoanMorph(new ModelVenomDemon(), "venomdemon");

	// Ushi Ushi no Mi : Model Bison
	private RenderZoanMorph zoanBisonPower = new RenderZoanMorph(new ModelPowerBison(), "bisonpower", 1.4, new float[] { 0, 0.8f, 0 });
	private RenderZoanMorph zoanBisonSpeed = new RenderZoanMorph(new ModelSpeedBison(), "bisonspeed", 1.4, new float[] { 0, 0.8f, 0 });

	public EventsMorphs(Minecraft mc)
	{
		this.mc = mc;
	}

	@SubscribeEvent
	public void onPlayerRendered(RenderPlayerEvent.Pre event)
	{
		ExtendedEntityStats props = ExtendedEntityStats.get(event.entityPlayer);

		if (!props.getZoanPoint().toLowerCase().equals("n/a"))
		{
			event.setCanceled(true);
			if (props.getUsedFruit().equals("dokudoku"))
			{
				if (props.getZoanPoint().toLowerCase().equals(ID.ZOANMORPH_DOKU))
					this.doRenderZoanMorph(morphVenomDemon, event.entityPlayer);
			}
			if (props.getUsedFruit().equals("ushiushibison"))
			{
				if (props.getZoanPoint().toLowerCase().equals(ID.ZOANMORPH_POWER))
					this.doRenderZoanMorph(zoanBisonPower, event.entityPlayer);
				if (props.getZoanPoint().toLowerCase().equals(ID.ZOANMORPH_SPEED))
					this.doRenderZoanMorph(zoanBisonSpeed, event.entityPlayer);
			}
		}
	}

	private void doRenderZoanMorph(RenderZoanMorph render, EntityPlayer player)
	{
		if (Minecraft.getMinecraft().thePlayer.equals(player))
			render.doRender(player, 0D, -1.625D, 0D, 0F, 0.0625F);
		else
			render.doRender(player, player.posX - Minecraft.getMinecraft().thePlayer.posX, (player.posY - Minecraft.getMinecraft().thePlayer.posY), player.posZ - Minecraft.getMinecraft().thePlayer.posZ, 0F, 0.0625F);
	}

	@SubscribeEvent
	public void onEntityConstructing(EntityJoinWorldEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer owner = (EntityPlayer) event.entity;
			ExtendedEntityStats props = ExtendedEntityStats.get(owner);

			if (!props.getZoanPoint().toLowerCase().equals("n/a"))
			{
				props.setZoanPoint("n/a");

				WyNetworkHelper.sendToServer(new PacketSync(props));
				WyNetworkHelper.sendToAll(new PacketSyncInfo(owner.getDisplayName(), props));
			}
		}
	}

	@SubscribeEvent
	public void morphHandRendering(RenderHandEvent event)
	{
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		ExtendedEntityStats props = ExtendedEntityStats.get(player);

		if ((props.getUsedFruit().equals("dokudoku") && props.getZoanPoint().equals(ID.ZOANMORPH_DOKU)) || (props.getUsedFruit().equals("ushiushibison") && (props.getZoanPoint().equals(ID.ZOANMORPH_SPEED) || props.getZoanPoint().equals(ID.ZOANMORPH_POWER))) || props.hasBusoHakiActive())
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
			GL11.glTranslatef((float) (-(i * 2 - 1)) * f1, 0.0F, 0.0F);

		Project.gluPerspective(this.mc.gameSettings.fovSetting, (float) this.mc.displayWidth / (float) this.mc.displayHeight, 0.20F, (float) (this.mc.gameSettings.renderDistanceChunks * 16) * 2.0F);

		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();

		if (this.mc.gameSettings.anaglyph)
			GL11.glTranslatef((float) (i * 2 - 1) * 0.1F, 0.0F, 0.0F);

		GL11.glPushMatrix();

		if (this.mc.gameSettings.viewBobbing)
			this.setupViewBobbing(f);

		if (this.mc.gameSettings.thirdPersonView == 0 && !this.mc.renderViewEntity.isPlayerSleeping() && !this.mc.gameSettings.hideGUI)
		{
			// Minecraft.getMinecraft().entityRenderer.enableLightmap((double)f);
			if (player.inventory.getCurrentItem() != null)
				Minecraft.getMinecraft().entityRenderer.itemRenderer.renderItemInFirstPerson(f);
			else
				renderCustomHand(player);
			// Minecraft.getMinecraft().entityRenderer.disableLightmap((double)f);
		}

		GL11.glPopMatrix();

		if (this.mc.gameSettings.viewBobbing)
			this.setupViewBobbing(f);
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
		f6 = MathHelper.sin(f5 * (float) Math.PI);
		f7 = MathHelper.sin(MathHelper.sqrt_float(f5) * (float) Math.PI);
		GL11.glTranslatef(-f7 * 0.3F, MathHelper.sin(MathHelper.sqrt_float(f5) * (float) Math.PI * 2.0F) * 0.4F, -f6 * 0.4F);
		GL11.glTranslatef(0.8F * f13, -0.75F * f13 - (1.0F - 1) * 0.6F, -0.9F * f13);
		GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		f5 = player.getSwingProgress(0);
		f6 = MathHelper.sin(f5 * f5 * (float) Math.PI);
		f7 = MathHelper.sin(MathHelper.sqrt_float(f5) * (float) Math.PI);
		GL11.glRotatef(f7 * 70.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-f6 * 20.0F, 0.0F, 0.0F, 1.0F);
		if (props.hasBusoHakiActive())
			this.mc.getTextureManager().bindTexture(ID.HANDTEXTURE_ZOANMORPH_BUSO);
		else
			this.mc.getTextureManager().bindTexture(getTextureFromMorph(player));
		GL11.glTranslatef(-1.0F, 3.6F, 3.5F);
		GL11.glRotatef(120.0F, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(200.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(1.0F, 1.0F, 1.0F);
		GL11.glTranslatef(5.6F, 0.0F, 0.0F);
		Render render = null;

		if (props.getZoanPoint().toLowerCase().equals("n/a"))
		{
			render = RenderManager.instance.getEntityRenderObject(this.mc.thePlayer);
			RenderPlayer renderplayer = (RenderPlayer) render;
			float i = 1.0F;
			GL11.glScalef(i, i, i);
			renderplayer.renderFirstPersonArm(this.mc.thePlayer);
		} 
		else
		{
			if (props.getUsedFruit().equals("ushiushibison"))
			{
				if (props.getZoanPoint().equals(ID.ZOANMORPH_POWER))
					render = zoanBisonPower;
				if (props.getZoanPoint().equals(ID.ZOANMORPH_SPEED))
					render = zoanBisonSpeed;
			}
			if (props.getUsedFruit().equals("dokudoku") && props.getZoanPoint().equals(ID.ZOANMORPH_DOKU))
				render = this.morphVenomDemon;

			RenderZoanMorph renderZoan = (RenderZoanMorph) render;
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

	private ResourceLocation getTextureFromMorph(EntityClientPlayerMP player)
	{
		ExtendedEntityStats props = ExtendedEntityStats.get(player);
		RenderZoanMorph render = null;
		
		if (props.getUsedFruit().equals("ushiushibison"))
		{
			if (props.getZoanPoint().equals(ID.ZOANMORPH_POWER))
				render = zoanBisonPower;
			if (props.getZoanPoint().equals(ID.ZOANMORPH_SPEED))
				render = zoanBisonSpeed;
		}
		if (props.getUsedFruit().equals("dokudoku") && props.getZoanPoint().equals(ID.ZOANMORPH_DOKU))
			render = this.morphVenomDemon;
		
		if(render != null)
			return render.getEntityTexture(null);
		
		return player.getLocationSkin();
	}

	private void setupViewBobbing(float p_78475_1_)
	{
		if (this.mc.renderViewEntity instanceof EntityPlayer)
		{
			EntityPlayer entityplayer = (EntityPlayer) this.mc.renderViewEntity;
			float f1 = entityplayer.distanceWalkedModified - entityplayer.prevDistanceWalkedModified;
			float f2 = -(entityplayer.distanceWalkedModified + f1 * p_78475_1_);
			float f3 = entityplayer.prevCameraYaw + (entityplayer.cameraYaw - entityplayer.prevCameraYaw) * p_78475_1_;
			float f4 = entityplayer.prevCameraPitch + (entityplayer.cameraPitch - entityplayer.prevCameraPitch) * p_78475_1_;
			GL11.glTranslatef(MathHelper.sin(f2 * (float) Math.PI) * f3 * 0.5F, -Math.abs(MathHelper.cos(f2 * (float) Math.PI) * f3), 0.0F);
			GL11.glRotatef(MathHelper.sin(f2 * (float) Math.PI) * f3 * 3.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(Math.abs(MathHelper.cos(f2 * (float) Math.PI - 0.2F) * f3) * 5.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(f4, 1.0F, 0.0F, 0.0F);
		}
	}

}
