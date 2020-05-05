package xyz.pixelatedw.mineminenomi.items.weapons;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;

public class UmbrellaItem extends Item
{

	private IItemPropertyGetter openProperty = (itemStack, world, livingEntity) ->
	{
		if (livingEntity == null || !(livingEntity instanceof PlayerEntity))
		{
			return 0.0F;
		}
		else
		{
			boolean hasUmbrella = livingEntity.getHeldItemMainhand().getItem() == ModWeapons.UMBRELLA;
			boolean isInAir = !livingEntity.onGround && livingEntity.getMotion().y < 0;
			return (hasUmbrella && isInAir) ? 1.0F : 0.0F;
		}
	};
	
	public UmbrellaItem()
	{
		super(new Properties().group(ModCreativeTabs.WEAPONS).maxStackSize(1).defaultMaxDamage(500));
		this.addPropertyOverride(new ResourceLocation("open"), this.openProperty);
	}

}
