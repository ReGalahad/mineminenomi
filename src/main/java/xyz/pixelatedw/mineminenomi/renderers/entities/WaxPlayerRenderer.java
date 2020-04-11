package xyz.pixelatedw.mineminenomi.renderers.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.WaxPlayerEntity;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.SimpleHumanModel;
import xyz.pixelatedw.wypi.APIConfig;

public class WaxPlayerRenderer extends GenericMobRenderer {

	public static final ResourceLocation WAX_LOCATION = new ResourceLocation(APIConfig.PROJECT_ID, "textures/models/wax_player.png");

	public WaxPlayerRenderer(EntityRendererManager EntityRendererManager)
	{
		super(EntityRendererManager, new SimpleHumanModel());
	}
		
	@SuppressWarnings("resource")
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
		if(((WaxPlayerEntity)entity).getTextureId() == 0) {
			return WAX_LOCATION;
		} else {
        Minecraft minecraft = Minecraft.getInstance();
        ResourceLocation rs = ((AbstractClientPlayerEntity)minecraft.player).getLocationSkin();
        
		return rs;
		}
	}
	
	public static class Factory implements IRenderFactory<WaxPlayerEntity>
	{
		public Factory() {}
		
		@Override
		public EntityRenderer<? super WaxPlayerEntity> createRenderFor(EntityRendererManager manager)
		{
			return new WaxPlayerRenderer(manager);
		}
	}
}
