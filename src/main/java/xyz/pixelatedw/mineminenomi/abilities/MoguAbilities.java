/*
 * package xyz.pixelatedw.mineminenomi.abilities;
 * import net.minecraft.block.Block;
 * import net.minecraft.block.Blocks;
 * import net.minecraft.entity.LivingEntity;
 * import net.minecraft.entity.player.PlayerEntity;
 * import net.minecraft.entity.player.ServerPlayerEntity;
 * import net.minecraft.item.ItemStack;
 * import net.minecraft.network.play.server.SAnimateHandPacket;
 * import net.minecraft.potion.EffectInstance;
 * import net.minecraft.potion.Effects;
 * import net.minecraft.util.math.BlockPos;
 * import net.minecraft.world.server.ServerWorld;
 * import xyz.pixelatedw.mineminenomi.abilities.extra.ZoanAbility;
 * import xyz.pixelatedw.mineminenomi.api.WyHelper;
 * import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
 * import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability;
 * import xyz.pixelatedw.mineminenomi.api.data.ability.IAbilityData;
 * import xyz.pixelatedw.mineminenomi.api.network.packets.server.SAbilityDataSyncPacket;
 * import xyz.pixelatedw.mineminenomi.config.CommonConfig;
 * import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
 * import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
 * import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoMoguMole;
 * import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
 * import xyz.pixelatedw.mineminenomi.init.ModAttributes;
 * import xyz.pixelatedw.mineminenomi.init.ModNetwork;
 * public class MoguAbilities
 * {
 * static
 * {
 * }
 * public static Ability[] abilitiesArray = new Ability[]
 * {
 * new PowerPoint(), new MoguraBanana(), new MoguraTonpo()
 * };
 * public static class MoguraTonpo extends Ability
 * {
 * private int initialY;
 * private boolean breakBlocks;
 * public MoguraTonpo()
 * {
 * super(ModAttributes.MOGURA_TONPO);
 * }
 * @Override
 * public void use(PlayerEntity player)
 * {
 * IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
 * boolean canMorphFlag = DevilFruitsHelper.canMorph(player, ZoanInfoMoguMole.FORM);
 * if (canMorphFlag && !this.isOnCooldown && CommonConfig.instance.isGriefingEnabled())
 * {
 * if (player.isSneaking())
 * {
 * int i = 0;
 * for (int x = -1; x < 1; x++)
 * for (int y = 0; y < 10; y++)
 * for (int z = -1; z < 1; z++)
 * {
 * int posX = (int) player.posX + x;
 * int posY = (int) player.posY - y;
 * int posZ = (int) player.posZ + z;
 * player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 50, 100, true, true));
 * player.addPotionEffect(new EffectInstance(Effects.HASTE, 400, 2, true, true));
 * Block tempBlock = player.world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock();
 * if (WyHelper.placeBlockIfAllowed(player.world, posX, posY, posZ, Blocks.AIR, "all", "restricted", "ignore liquids"))
 * {
 * player.inventory.addItemStackToInventory(new ItemStack(tempBlock));
 * // ModNetwork.sendToAllAround(new SParticlesPacket(ID.PARTICLEFX_BAKUMUNCH, posX, posY, posZ), player);
 * }
 * }
 * this.attr.setAbilityCooldown(2);
 * }
 * else
 * {
 * double[] speed = DevilFruitsHelper.propulsion(player, 4, 4);
 * DevilFruitsHelper.changeMotion("=", speed[0], player.getMotion().y, speed[1], player);
 * this.attr.setAbilityCooldown(10);
 * }
 * super.use(player);
 * }
 * else if (!canMorphFlag)
 * WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Mole Point is active !");
 * }
 * @Override
 * public void duringCooldown(PlayerEntity player, int currentCooldown)
 * {
 * IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
 * if (currentCooldown > 100 && player.posY >= this.initialY && devilFruitProps.getZoanPoint().equals(ZoanInfoMoguMole.FORM))
 * {
 * for (int[] location : WyHelper.getBlockLocationsNearby(player, 2))
 * {
 * if (location[1] >= player.posY)
 * {
 * Block tempBlock = player.world.getBlockState(new BlockPos(location[0], location[1], location[2])).getBlock();
 * if (WyHelper.placeBlockIfAllowed(player.world, location[0], location[1], location[2], Blocks.AIR, "core", "foliage"))
 * {
 * player.inventory.addItemStackToInventory(new ItemStack(tempBlock));
 * // ModNetwork.sendToAllAround(new SParticlesPacket(ID.PARTICLEFX_BAKUMUNCH, location[0], location[1], location[2]), player);
 * if (player.world instanceof ServerWorld)
 * ((ServerWorld) player.world).getChunkProvider().sendToTrackingAndSelf(player, new SAnimateHandPacket(player, 0));
 * }
 * }
 * }
 * }
 * else if (currentCooldown < 10)
 * {
 * this.breakBlocks = false;
 * }
 * }
 * }
 * public static class MoguraBanana extends Ability
 * {
 * public MoguraBanana()
 * {
 * super(ModAttributes.MOGURA_BANANA);
 * }
 * @Override
 * public void startPassive(PlayerEntity player)
 * {
 * IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
 * IAbilityData abilityDataProps = AbilityDataCapability.get(player);
 * boolean canMorphFlag = DevilFruitsHelper.canMorph(player, ZoanInfoMoguMole.FORM);
 * if (!canMorphFlag)
 * {
 * this.setPassiveActive(false);
 * ModNetwork.sendTo(new SAbilityDataSyncPacket(player.getEntityId(), abilityDataProps), (ServerPlayerEntity) player);
 * WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Mole Point is active !");
 * }
 * }
 * @Override
 * public void hitEntity(PlayerEntity player, LivingEntity target)
 * {
 * double[] speed = DevilFruitsHelper.propulsion(player, 2.5, 2.5);
 * target.setMotion(speed[0], 0.1, speed[1]);
 * super.hitEntity(player, target);
 * }
 * }
 * public static class PowerPoint extends ZoanAbility
 * {
 * public PowerPoint()
 * {
 * super(ModAttributes.MOGU_MOLE_POINT, ZoanInfoMoguMole.FORM);
 * }
 * @Override
 * public void duringPassive(PlayerEntity player, int timer)
 * {
 * player.addPotionEffect(new EffectInstance(Effects.HASTE, 200, 3));
 * if (player.world.getLight(new BlockPos(player.posX, player.posY, player.posZ)) < 7)
 * {
 * player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 300, 1));
 * }
 * }
 * @Override
 * public void endPassive(PlayerEntity player)
 * {
 * super.endPassive(player);
 * player.removePotionEffect(Effects.HASTE);
 * player.removePotionEffect(Effects.NIGHT_VISION);
 * }
 * }
 * }
 */