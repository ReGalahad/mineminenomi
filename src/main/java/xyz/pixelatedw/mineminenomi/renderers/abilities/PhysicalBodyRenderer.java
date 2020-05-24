package xyz.pixelatedw.mineminenomi.renderers.abilities;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.PhysicalBodyEntity;
import xyz.pixelatedw.mineminenomi.entities.zoan.YomiZoanInfo;
import xyz.pixelatedw.mineminenomi.models.abilities.PhysicalBodyModel;
import xyz.pixelatedw.wypi.APIConfig;

@OnlyIn(Dist.CLIENT)
public class PhysicalBodyRenderer extends EntityRenderer<PhysicalBodyEntity>
{
	private PhysicalBodyModel model = new PhysicalBodyModel();
	
	public PhysicalBodyRenderer(EntityRendererManager renderManager)
	{
		super(renderManager);
	}
	
	@Override
	public void doRender(PhysicalBodyEntity entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		boolean isSkeleton = false;
		if(entity.getOwner() != null)
		{
			IDevilFruit devilFruitProps = DevilFruitCapability.get(entity.getOwner());
			isSkeleton = devilFruitProps.getDevilFruit().equals("yomi_yomi") && devilFruitProps.getZoanPoint().equalsIgnoreCase(YomiZoanInfo.FORM);
		}
		
		GlStateManager.pushMatrix();
		{
			GlStateManager.color4f(1, 1, 1, 1);
			GlStateManager.translated(x, y + 1.5, z);
			GlStateManager.rotated(180, 0, 0, 1);
			GlStateManager.rotated(entity.rotationYaw + 180, 0, 1, 0);
			
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
			
			GlStateManager.scaled(1, 1, 1);

			if (entity.hurtTime > 0)
			{
				GL11.glPushMatrix();
				GL11.glColor3f(1.0f, 0, 0);
				GL11.glPopMatrix();
			}
			
			if(isSkeleton)
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/models/zoanmorph/yomi_skeleton.png"));			
			else
				Minecraft.getInstance().getTextureManager().bindTexture(this.getEntityTexture(entity));
			
			this.model.render(entity, (float) x, (float) y, (float) z, 0.0F, 0.0F, 0.0625F);
		}
		GlStateManager.popMatrix();
	}	

	@Override
	protected ResourceLocation getEntityTexture(PhysicalBodyEntity entity)
	{
		AbstractClientPlayerEntity abstractOwner = (AbstractClientPlayerEntity) entity.getOwner();
		return abstractOwner.getLocationSkin();
	}
	
	public static class Factory implements IRenderFactory<PhysicalBodyEntity>
	{
		public Factory() {}
		
		@Override
		public EntityRenderer<? super PhysicalBodyEntity> createRenderFor(EntityRendererManager manager)
		{
			return new PhysicalBodyRenderer(manager);
		}
	}
}
