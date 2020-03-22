package xyz.pixelatedw.mineminenomi.models.entities.zoans;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class ZoanMorphModel extends EntityModel 
{

	public abstract RendererModel getHandRenderer();
	public abstract RendererModel getArmRenderer();
	
}
