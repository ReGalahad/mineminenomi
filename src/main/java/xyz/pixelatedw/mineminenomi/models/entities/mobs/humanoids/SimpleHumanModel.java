package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SimpleHumanModel extends BipedModel
{
	public SimpleHumanModel()
	{
		super(0, 0, 64, 64);
		this.bipedHeadwear.showModel = false;
	}
}
