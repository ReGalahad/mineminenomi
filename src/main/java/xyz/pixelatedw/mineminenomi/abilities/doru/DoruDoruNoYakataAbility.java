package xyz.pixelatedw.mineminenomi.abilities.doru;

import net.minecraft.block.AirBlock;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.WaxPlayerEntity;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class DoruDoruNoYakataAbility extends Ability{

	public static final DoruDoruNoYakataAbility INSTANCE = new DoruDoruNoYakataAbility();
	public DoruDoruNoYakataAbility() {
		super("Doru Doru no Yakata", AbilityCategory.DEVIL_FRUIT);
	

		this.setMaxCooldown(5);
		this.setDescription("The user creates a few wax copies of himself.");
	
		this.onUseEvent = this::onUseEvent;
	
	}
	

	private boolean onUseEvent(PlayerEntity p) {
		if(!p.world.isRemote) {
		WaxPlayerEntity waxplayer = new WaxPlayerEntity(p.world);
		WaxPlayerEntity waxplayer2 = new WaxPlayerEntity(p.world);
		WaxPlayerEntity waxplayer3 = new WaxPlayerEntity(p.world);

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
		return false;
	}
}
