package xyz.pixelatedw.mineminenomi.api.abilities;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ArrowModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.BazookaModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.BrickBatModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.FistModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.HeartModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.HydraModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.MeigoModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.MiniHollowModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.NegativeHollowModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.NoroNoroBeamModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.PawModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.PheasantModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.SharkModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.SpearModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.TokuHollowModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.TridentModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.YukiRabiModel;

@OnlyIn(Dist.CLIENT)
public class AbilityRenderer extends EntityRenderer<AbilityProjectile>
{
	private double scaleX, scaleY, scaleZ, rotAngle, rotX, rotY, rotZ, red, blue, green, renderPosX, renderPosY, renderPosZ;
	private float alpha;
	private EntityModel model;
	private AbilityAttribute ablAttr;
	private ResourceLocation texture;

	public AbilityRenderer(EntityRendererManager renderManager, AbilityAttribute attr)
	{
		super(renderManager);
		this.ablAttr = attr;
		this.texture = ablAttr.getProjectileTexture();
	}

	@Override
	public void doRender(AbilityProjectile entity, double par2, double par4, double par6, float par8, float par9)
	{
		GlStateManager.pushMatrix();
		{
			this.scaleX = this.ablAttr.getProjectileSize()[0];
			this.scaleY = this.ablAttr.getProjectileSize()[1];
			this.scaleZ = this.ablAttr.getProjectileSize()[2];
	
			this.red = this.ablAttr.getProjectileColor().getRed();
			this.green = this.ablAttr.getProjectileColor().getGreen();
			this.blue = this.ablAttr.getProjectileColor().getBlue();
			this.alpha = this.ablAttr.getProjectileAlpha();
	
			this.rotX = this.ablAttr.getProjectileXRotation();
			this.rotY = this.ablAttr.getProjectileYRotation();
			this.rotZ = this.ablAttr.getProjectileZRotation();
	
			this.renderPosX = this.ablAttr.getModelOffsets()[0];
			this.renderPosY = this.ablAttr.getModelOffsets()[1];
			this.renderPosZ = this.ablAttr.getModelOffsets()[2];
			
			//System.out.println(this.model);
			
			if(this.ablAttr.getProjectileModel() != null)
			{
				switch(this.ablAttr.getProjectileModel())
				{
					case CUBE:
						this.model = new ModelCube(); break;
					case SPHERE:
						this.model = new ModelSphere(); break;
					case FIST:
						this.model = new FistModel(); break;
					case ARROW:
						this.model = new ArrowModel(); break;	
					case HEART:
						this.model = new HeartModel(); break;	
					case SPEAR:
						this.model = new SpearModel(); break;	
					case HYDRA:
						this.model = new HydraModel(); break;	
					case PAW:
						this.model = new PawModel(); break;				
					case TRIDENT:
						this.model = new TridentModel(); break;	
					case SHARK:
						this.model = new SharkModel(); break;	
					case PHEASANT:
						this.model = new PheasantModel(); break;	
						
					case NORO_NORO_BEAM:
						this.model = new NoroNoroBeamModel(); break;	
					case MEIGO:
						this.model = new MeigoModel(); break;	
					case BRICK_BAT:
						this.model = new BrickBatModel(); break;	
					case NEGATIVE_HOLLOW:
						this.model = new NegativeHollowModel(); break;	
					case MINI_HOLLOW:
						this.model = new MiniHollowModel(); break;	
					case TOKU_HOLLOW:
						this.model = new TokuHollowModel(); break;	
					case YUKI_RABI:
						this.model = new YukiRabiModel(); break;	
					case BAZOOKA:
						this.model = new BazookaModel(); break;
						
					default:
						this.model = new ModelCube();
		
				}
			}
			
			GlStateManager.pushMatrix();
			{
				GL11.glTranslated(par2 + renderPosX, par4 + renderPosY, par6 + renderPosZ);
				if (this.texture == null)
					GL11.glDisable(GL11.GL_TEXTURE_2D);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
				GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * par9 - 180.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * par9, 1.0F, 0.0F, 0.0F);
		
				GL11.glRotatef(180, 0, 0, 1);
		
				if (rotX != 0)
					GL11.glRotated(rotX, 1, 0, 0);
				if (rotY != 0)
					GL11.glRotated(rotY, 0, 1, 0);
				if (rotZ != 0)
					GL11.glRotated(rotZ, 0, 0, 1);
		
				GL11.glColor4f((float) this.red / 255, (float) this.green / 255, (float) this.blue / 255, this.alpha / 255);
				GL11.glScaled(this.scaleX, this.scaleY, this.scaleZ);
		
				if (this.texture != null)
					Minecraft.getInstance().textureManager.bindTexture(this.getEntityTexture(entity));
		
				if (this.model != null)
					this.model.render(entity, (float) par2, (float) par4, (float) par6, 0.0F, 0.0F, 0.0625F);
		
				GL11.glDisable(GL11.GL_BLEND);
				if (this.texture == null)
					GL11.glEnable(GL11.GL_TEXTURE_2D);
			}
			GlStateManager.popMatrix();
		}
		GL11.glColor4f(1, 1, 1, 1);
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(AbilityProjectile entity)
	{
		return this.texture;
	}

	public static class Factory implements IRenderFactory<AbilityProjectile>
	{
		private AbilityAttribute attr;
		
		public Factory() {}
		
		public Factory(AbilityAttribute attr)
		{
			this.attr = attr;
		}

		@Override
		public EntityRenderer<? super AbilityProjectile> createRenderFor(EntityRendererManager manager)
		{
			return new AbilityRenderer(manager, this.attr);
		}
	}

}
