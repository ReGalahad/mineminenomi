package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.init.ModResources;

@OnlyIn(Dist.CLIENT)
public class SimpleHumanModel extends BipedModel
{
	public SimpleHumanModel()
	{
		super(0, 0, 64, 64);
		this.bipedHeadwear.showModel = false;
	}
	
	@Override
	public void render(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

    	boolean hasHaki = false;
    	
    	if(entity instanceof GenericNewEntity)
    		hasHaki = ((GenericNewEntity)entity).hasBusoHaki();

        this.bipedHead.render(scale);
        this.bipedBody.render(scale);
        this.bipedLeftArm.render(scale);
        this.bipedRightLeg.render(scale);
        this.bipedLeftLeg.render(scale);
        this.bipedHeadwear.render(scale);
        
		if (hasHaki)
		{
			Minecraft.getInstance().getTextureManager().bindTexture(ModResources.BUSOSHOKU_HAKI_ARM);
	        this.bipedRightArm.render(scale);
		}
		else
		{
	        this.bipedRightArm.render(scale);
		}
	}
}
