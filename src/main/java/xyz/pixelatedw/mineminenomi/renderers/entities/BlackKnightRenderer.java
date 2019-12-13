package xyz.pixelatedw.mineminenomi.renderers.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.EntityBlackKnight;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.SimpleHumanModel;

@OnlyIn(Dist.CLIENT)
public class BlackKnightRenderer extends GenericMobRenderer
{

	public BlackKnightRenderer(EntityRendererManager EntityRendererManager)
	{
		super(EntityRendererManager, new SimpleHumanModel());
	}
		
	@SuppressWarnings("resource")
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
		System.out.println( ((EntityBlackKnight)entity).getOwner() );
        Minecraft minecraft = Minecraft.getInstance();
        ResourceLocation rs = ((AbstractClientPlayerEntity)minecraft.player).getLocationSkin();
        
		return rs;
	}
	
	public static class Factory implements IRenderFactory<EntityBlackKnight>
	{
		public Factory() {}
		
		@Override
		public EntityRenderer<? super EntityBlackKnight> createRenderFor(EntityRendererManager manager)
		{
			return new BlackKnightRenderer(manager);
		}
	}
}
