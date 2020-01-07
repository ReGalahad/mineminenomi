/*
 * package xyz.pixelatedw.mineminenomi.abilities;
 * import java.util.Random;
 * import net.minecraft.entity.LivingEntity;
 * import net.minecraft.entity.player.PlayerEntity;
 * import net.minecraft.util.DamageSource;
 * import xyz.pixelatedw.mineminenomi.abilities.extra.ZoanAbility;
 * import xyz.pixelatedw.mineminenomi.api.WyHelper;
 * import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
 * import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
 * import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
 * import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.ToriPhoenixProjectiles;
 * import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoPhoenixFull;
 * import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoPhoenixHybrid;
 * import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
 * import xyz.pixelatedw.mineminenomi.init.ModAttributes;
 * import xyz.pixelatedw.mineminenomi.init.ModNetwork;
 * import xyz.pixelatedw.mineminenomi.init.ModValues;
 * import xyz.pixelatedw.mineminenomi.packets.server.SDevilFruitSyncPacket;
 * public class ToriPhoenixAbilities
 * {
 * static
 * {
 * ModValues.abilityWebAppExtraParams.put("hybridpoint", new String[] {"desc", "The user transforms into a phoenix-human hybrid, which allows them to fly. Allows the user to use 'Phoenix Goen' "});
 * ModValues.abilityWebAppExtraParams.put("phoenixpoint", new String[] {"desc", "The user fully transforms into a phoenix, allowing them to fly at great speed. Allows the user to use both 'Phoenix Goen' and 'Tensei no Soen'"});
 * ModValues.abilityWebAppExtraParams.put("phoenixgoen", new String[] {"desc", "Launches a powerful fiery shockwave made of blue flames at the target."});
 * ModValues.abilityWebAppExtraParams.put("tenseinosoen", new String[] {"desc", "While in the air, the user amasses spiraling flames, then slams into the ground, releasing a massive shockwave."});
 * ModValues.abilityWebAppExtraParams.put("blueflamesofresurrection", new String[] {"desc", "Blue phoenix flames grant the user regeneration."});
 * ModValues.abilityWebAppExtraParams.put("flameofrestoration", new String[] {"desc", "Uses the blue flames to heal the target."});
 * }
 * public static Ability[] abilitiesArray = new Ability[] {new PhoenixPoint(), new HybridPoint(), new BlueFlamesOfResurrection(), new FlameOfRestoration(), new PhoenixGoen(), new TenseiNoSoen()};
 * public static class TenseiNoSoen extends Ability
 * {
 * private int particlesSpawned = 0;
 * public TenseiNoSoen()
 * {
 * super(ModAttributes.TENSEI_NO_SOEN);
 * }
 * @Override
 * public void startCharging(PlayerEntity player)
 * {
 * IDevilFruit props = DevilFruitCapability.get(player);
 * particlesSpawned = 0;
 * boolean canMorphFlag = DevilFruitsHelper.canMorph(player, ZoanInfoPhoenixFull.FORM);
 * if(canMorphFlag && !this.isOnCooldown)
 * {
 * if(!player.onGround)
 * {
 * // ModNetwork.sendToAllAround(new SParticlesPacket(ID.PARTICLEFX_TENSEINOSOEN1, player), player);
 * super.startCharging(player);
 * }
 * else
 * WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while airborne !");
 * }
 * else if(!canMorphFlag)
 * WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Phoenix Point is active !");
 * }
 * @Override
 * public void endCharging(PlayerEntity player)
 * {
 * player.fallDistance = 0;
 * DevilFruitsHelper.changeMotion("=", 0, -100, 0, player);
 * super.endCharging(player);
 * }
 * @Override
 * public void duringCooldown(PlayerEntity player, int currentCooldown)
 * {
 * if(currentCooldown > 28 * 20)
 * {
 * if(player.onGround && particlesSpawned < 10)
 * {
 * // ModNetwork.sendToAllAround(new SParticlesPacket(ID.PARTICLEFX_TENSEINOSOEN2, player), player);
 * particlesSpawned++;
 * }
 * for(LivingEntity e : WyHelper.getEntitiesNear(player, 20))
 * {
 * e.attackEntityFrom(DamageSource.causePlayerDamage(player), 30);
 * }
 * }
 * }
 * }
 * public static class PhoenixGoen extends Ability
 * {
 * public PhoenixGoen()
 * {
 * super(ModAttributes.PHOENIX_GOEN);
 * }
 * @Override
 * public void use(PlayerEntity player)
 * {
 * IDevilFruit props = DevilFruitCapability.get(player);
 * boolean canMorphFlag = DevilFruitsHelper.canMorph(player, ZoanInfoPhoenixFull.FORM, ZoanInfoPhoenixHybrid.FORM);
 * if(canMorphFlag && !this.isOnCooldown)
 * {
 * for (int i = 0; i < 100; i++)
 * {
 * double offsetX = (new Random().nextInt(20) + 1.0D - 5.0D) / 5.0D;
 * double offsetY = (new Random().nextInt(20) + 1.0D - 20.0D) / 10.0D;
 * double offsetZ = (new Random().nextInt(20) + 1.0D - 5.0D) / 5.0D;
 * this.projectile = new ToriPhoenixProjectiles.PhoenixGoen(player.world, player, attr);
 * this.projectile.setLocationAndAngles(player.posX - 1 + offsetX, player.posY + 2 + offsetY, player.posZ + offsetZ, player.rotationPitch, player.cameraYaw);
 * player.world.addEntity(projectile);
 * projectile.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
 * }
 * ModNetwork.sendToAll(new SDevilFruitSyncPacket(player.getEntityId(), props));
 * super.use(player);
 * }
 * else if(!canMorphFlag)
 * WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Phoenix Point or Hybrid Point is active !");
 * }
 * }
 * public static class FlameOfRestoration extends Ability
 * {
 * public FlameOfRestoration()
 * {
 * super(ModAttributes.FLAME_OF_RESTORATION);
 * }
 * @Override
 * public void hitEntity(PlayerEntity player, LivingEntity target)
 * {
 * super.hitEntity(player, target);
 * if(target.getHealth() + 6 < target.getMaxHealth())
 * target.setHealth(target.getHealth() + 6);
 * else
 * target.setHealth(target.getMaxHealth());
 * // ModNetwork.sendTo(new SParticlesPacket(ID.PARTICLEFX_BLUEFLAMES, player), (ServerPlayerEntity) player);
 * }
 * }
 * public static class BlueFlamesOfResurrection extends Ability
 * {
 * public BlueFlamesOfResurrection()
 * {
 * super(ModAttributes.BLUE_FLAMES_OF_RESURRECTION);
 * }
 * @Override
 * public void use(PlayerEntity player)
 * {
 * if(!isOnCooldown)
 * {
 * // ModNetwork.sendTo(new SParticlesPacket(ID.PARTICLEFX_BLUEFLAMES, player), (ServerPlayerEntity) player);
 * }
 * super.use(player);
 * }
 * }
 * public static class PhoenixPoint extends ZoanAbility
 * {
 * public PhoenixPoint()
 * {
 * super(ModAttributes.PHOENIX_PHOENIX_POINT, ZoanInfoPhoenixFull.FORM);
 * }
 * }
 * public static class HybridPoint extends ZoanAbility
 * {
 * public HybridPoint()
 * {
 * super(ModAttributes.PHOENIX_HYBRID_POINT, ZoanInfoPhoenixHybrid.FORM);
 * }
 * }
 * }
 */