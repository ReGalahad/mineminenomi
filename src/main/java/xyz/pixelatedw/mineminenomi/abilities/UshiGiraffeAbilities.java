/*
 * package xyz.pixelatedw.mineminenomi.abilities;
 * import net.minecraft.entity.player.PlayerEntity;
 * import net.minecraft.potion.EffectInstance;
 * import net.minecraft.potion.Effects;
 * import xyz.pixelatedw.mineminenomi.abilities.extra.ZoanAbility;
 * import xyz.pixelatedw.mineminenomi.api.WyHelper;
 * import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
 * import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
 * import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
 * import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.UshiGiraffeProjectiles;
 * import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoGiraffeHeavy;
 * import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoGiraffeWalk;
 * import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
 * import xyz.pixelatedw.mineminenomi.init.ModAttributes;
 * public class UshiGiraffeAbilities
 * {
 * public static Ability[] abilitiesArray = new Ability[] {new PowerPoint(), new SpeedPoint(), new Bigan()};
 * public static class Bigan extends Ability
 * {
 * public Bigan()
 * {
 * super(ModAttributes.BIGAN);
 * }
 * @Override
 * public void use(PlayerEntity player)
 * {
 * IDevilFruit props = DevilFruitCapability.get(player);
 * boolean canMorphFlag = DevilFruitsHelper.canMorph(player, ZoanInfoGiraffeHeavy.FORM, ZoanInfoGiraffeWalk.FORM);
 * if(canMorphFlag && !this.isOnCooldown)
 * {
 * this.projectile = new UshiGiraffeProjectiles.Bigan(player.world, player, attr);
 * super.use(player);
 * }
 * else if(!canMorphFlag)
 * {
 * WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while " + WyHelper.upperCaseFirst(ZoanInfoGiraffeHeavy.FORM) + "Point or " + WyHelper.upperCaseFirst(ZoanInfoGiraffeWalk.FORM) + "Point is active!");
 * }
 * }
 * }
 * public static class PowerPoint extends ZoanAbility
 * {
 * public PowerPoint()
 * {
 * super(ModAttributes.GIRAFFE_HEAVY_POINT, ZoanInfoGiraffeHeavy.FORM);
 * }
 * }
 * public static class SpeedPoint extends ZoanAbility
 * {
 * public SpeedPoint()
 * {
 * super(ModAttributes.GIRAFFE_WALK_POINT, ZoanInfoGiraffeWalk.FORM);
 * }
 * @Override
 * public void duringPassive(PlayerEntity player, int timer)
 * {
 * player.addPotionEffect(new EffectInstance(Effects.SPEED, 2 * 20, 1, false, false));
 * }
 * }
 * }
 */