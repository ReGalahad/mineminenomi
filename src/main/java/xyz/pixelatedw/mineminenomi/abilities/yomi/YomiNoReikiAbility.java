package xyz.pixelatedw.mineminenomi.abilities.yomi;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.entities.PhysicalBodyEntity;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class YomiNoReikiAbility extends ContinuousAbility
{
	public static final YomiNoReikiAbility INSTANCE = new YomiNoReikiAbility();

	private double posX, posY, posZ;
	private PhysicalBodyEntity body;
	
	public YomiNoReikiAbility()
	{
		super("Yomi no Reiki", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("The user's spirit leaves their body. Allowing them to freely explore the nearby areas.");
		this.setThreshold(200);
		
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.duringContinuityEvent = this::duringContinuityEvent;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		if(player.isCreative() || player.isSpectator())
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_SUVIVAL_ONLY).getFormattedText());
			return false;
		}
		
		player.setMotion(0, 5, 0);
		((ServerPlayerEntity)player).connection.sendPacket(new SEntityVelocityPacket(player));
		player.velocityChanged = true;
		
		this.posX = player.getPositionVec().getX();
		this.posY = player.getPositionVec().getY();
		this.posZ = player.getPositionVec().getZ();
		
		this.body = new PhysicalBodyEntity(player.world);
		this.body.setPositionAndRotation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
		this.body.setOwner(player.getUniqueID());
		player.world.addEntity(this.body);
		
		return true;
	}
	
	private void duringContinuityEvent(PlayerEntity player, int continueTime)
	{
		if(Math.sqrt(player.getDistanceSq(this.posX, this.posY, this.posZ)) > 60)
		{
			this.stopContinuity(player);
		}
	}
	
	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		double cooldown = this.continueTime / 20;
		
		this.setMaxCooldown(cooldown);
		
		if(player.isCreative() || player.isSpectator())
			return true;
		
		if(this.body != null)
		{
			this.body.remove();
			this.body = null;
			player.setPositionAndUpdate(this.posX, this.posY, this.posZ);
		}
		
		return true;
	}
}