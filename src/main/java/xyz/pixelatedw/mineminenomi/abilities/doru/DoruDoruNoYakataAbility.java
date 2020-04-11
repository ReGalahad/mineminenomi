package xyz.pixelatedw.mineminenomi.abilities.doru;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.WaxPlayerEntity;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class DoruDoruNoYakataAbility extends Ability {

	public static final DoruDoruNoYakataAbility INSTANCE = new DoruDoruNoYakataAbility();

	public DoruDoruNoYakataAbility() {
		super("Doru Doru no Yakata", AbilityCategory.DEVIL_FRUIT);

		this.setMaxCooldown(6);
		this.setDescription("The user creates a few wax copies of himself.");

		this.onUseEvent = this::onUseEvent;

	}

	private boolean onUseEvent(PlayerEntity p) {
		if (!p.world.isRemote) {
			if (p.inventory.hasItemStack(new ItemStack(ModItems.COLOR_PALETTE))) {
				WaxPlayerEntity waxplayer = new WaxPlayerEntity(p.world, 1);
				WaxPlayerEntity waxplayer2 = new WaxPlayerEntity(p.world, 1);
				WaxPlayerEntity waxplayer3 = new WaxPlayerEntity(p.world, 1);

				BlockPos pos1 = new BlockPos(p.posX + 5, p.posY, p.posZ + 5);
				BlockPos pos2 = new BlockPos(p.posX, p.posY, p.posZ + 5);
				BlockPos pos3 = new BlockPos(p.posX + 5, p.posY, p.posZ);

				waxplayer.setCustomName(p.getCustomName());
				waxplayer2.setCustomName(p.getCustomName());
				waxplayer3.setCustomName(p.getCustomName());

				waxplayer.setPosition(pos1.getX(), pos1.getY(), pos1.getZ());
				waxplayer2.setPosition(pos2.getX(), pos2.getY(), pos2.getZ());
				waxplayer3.setPosition(pos3.getX(), pos3.getY(), pos3.getZ());

				p.world.addEntity(waxplayer);
				p.world.addEntity(waxplayer2);
				p.world.addEntity(waxplayer3);
				return true;

			} else {
				WaxPlayerEntity waxplayer = new WaxPlayerEntity(p.world, 0);
				WaxPlayerEntity waxplayer2 = new WaxPlayerEntity(p.world, 0);
				WaxPlayerEntity waxplayer3 = new WaxPlayerEntity(p.world, 0);

				BlockPos pos1 = new BlockPos(p.posX + 5, p.posY, p.posZ + 5);
				BlockPos pos2 = new BlockPos(p.posX, p.posY, p.posZ + 5);
				BlockPos pos3 = new BlockPos(p.posX + 5, p.posY, p.posZ);

				waxplayer.setPosition(pos1.getX(), pos1.getY(), pos1.getZ());
				waxplayer2.setPosition(pos2.getX(), pos2.getY(), pos2.getZ());
				waxplayer3.setPosition(pos3.getX(), pos3.getY(), pos3.getZ());

				p.world.addEntity(waxplayer);
				p.world.addEntity(waxplayer2);
				p.world.addEntity(waxplayer3);
				return true;
			}
		}
		return false;
	}
}
