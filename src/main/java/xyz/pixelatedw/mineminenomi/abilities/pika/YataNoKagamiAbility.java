package xyz.pixelatedw.mineminenomi.abilities.pika;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;

public class YataNoKagamiAbility extends Ability
{
	public static final Ability INSTANCE = new YataNoKagamiAbility();
	
	public YataNoKagamiAbility()
	{
		super("Yata no Kagami", Category.DEVIL_FRUIT);
		this.setMaxCooldown(4);
		this.setDescription("Uses light to instantly teleport the user to their desired location.");

		this.onUseEvent = this::onUseEvent;
	}
	
	private void onUseEvent(PlayerEntity player, Ability ability)
	{
		if (WyHelper.rayTraceBlocks(player) != null)
		{
			RayTraceResult mop = WyHelper.rayTraceBlocks(player);
			
			double x = mop.getHitVec().x;
			double y = mop.getHitVec().y;
			double z = mop.getHitVec().z;
			
			if (player.getRidingEntity() != null)
				player.dismountEntity(player.getRidingEntity());
			
			EnderTeleportEvent event = new EnderTeleportEvent(player, x, y, z, 5.0F);
			// ModNetwork.sendToAllAround(new SParticlesPacket(ID.PARTICLEFX_YATANOKAGAMI, player), (ServerPlayerEntity) player);
			player.setPositionAndUpdate(event.getTargetX(), event.getTargetY() + 1, event.getTargetZ());
			// ModNetwork.sendToAllAround(new SParticlesPacket(ID.PARTICLEFX_YATANOKAGAMI, player), (ServerPlayerEntity) player);
			player.fallDistance = 0.0F;
		}
	}
}