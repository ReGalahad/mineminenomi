package xyz.pixelatedw.mineminenomi.renderers.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.EntityDoppelman;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.SimpleHumanModel;

@OnlyIn(Dist.CLIENT)
public class DoppelmanRenderer extends GenericMobRenderer
{

	public DoppelmanRenderer(EntityRendererManager renderManager)
	{
		super(renderManager, new SimpleHumanModel(), "doppelman");
	}
	
	@Override
	protected void preRenderCallback(LivingEntity livingBase, float f) 
	{
		IEntityStats props = EntityStatsCapability.get(livingBase);
		float scale = 1 + ((float)props.getDoriki() / 7);

		if(scale < 1)
			scale = 1;
		
		GL11.glScalef(scale, scale, scale);
	}
	
	public static class Factory implements IRenderFactory<EntityDoppelman>
	{
		public Factory() {}
		
		@Override
		public EntityRenderer<? super EntityDoppelman> createRenderFor(EntityRendererManager manager)
		{
			return new DoppelmanRenderer(manager);
		}
	}
}
