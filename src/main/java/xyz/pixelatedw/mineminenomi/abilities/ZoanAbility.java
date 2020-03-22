package xyz.pixelatedw.mineminenomi.abilities;
/*
 * package xyz.pixelatedw.mineminenomi.abilities.extra;
 * import net.minecraft.entity.player.PlayerEntity;
 * import net.minecraft.entity.player.ServerPlayerEntity;
 * import xyz.pixelatedw.mineminenomi.ModMain;
 * import xyz.pixelatedw.mineminenomi.api.WyHelper;
 * import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
 * import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
 * import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability;
 * import xyz.pixelatedw.mineminenomi.api.data.ability.IAbilityData;
 * import xyz.pixelatedw.mineminenomi.api.network.packets.server.SAbilityDataSyncPacket;
 * import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
 * import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
 * import xyz.pixelatedw.mineminenomi.init.ModNetwork;
 * import xyz.pixelatedw.mineminenomi.packets.server.SDevilFruitSyncPacket;
 * import xyz.pixelatedw.mineminenomi.packets.server.SRecalculateEyeHeightPacket;
 * public class ZoanAbility extends Ability
 * {
 * private String form;
 * public ZoanAbility(AbilityAttribute attr, String form)
 * {
 * super(attr);
 * this.form = form;
 * }
 * @Override
 * public void passive(PlayerEntity player)
 * {
 * IDevilFruit props = DevilFruitCapability.get(player);
 * if (!this.isOnCooldown && this.canMorphIn(player, this.form))
 * {
 * super.passive(player);
 * }
 * }
 * @Override
 * public void startPassive(PlayerEntity player)
 * {
 * IDevilFruit props = DevilFruitCapability.get(player);
 * IAbilityData abilityProps = AbilityDataCapability.get(player);
 * if (props.getZoanPoint().isEmpty())
 * props.setZoanPoint("");
 * props.setZoanPoint(this.form);
 * ModNetwork.sendToAll(new SDevilFruitSyncPacket(player.getEntityId(), props));
 * ModNetwork.sendToAll(new SAbilityDataSyncPacket(player.getEntityId(), abilityProps));
 * ModMain.proxy.updateEyeHeight(player);
 * ModNetwork.sendTo(new SRecalculateEyeHeightPacket(), (ServerPlayerEntity) player);
 * }
 * @Override
 * public void endPassive(PlayerEntity player)
 * {
 * IDevilFruit props = DevilFruitCapability.get(player);
 * props.setZoanPoint("");
 * ModNetwork.sendToAll(new SDevilFruitSyncPacket(player.getEntityId(), props));
 * ModMain.proxy.updateEyeHeight(player);
 * ModNetwork.sendTo(new SRecalculateEyeHeightPacket(), (ServerPlayerEntity) player);
 * }
 * protected boolean canMorphIn(PlayerEntity player, String form)
 * {
 * IDevilFruit props = DevilFruitCapability.get(player);
 * return WyHelper.isNullOrEmpty(props.getZoanPoint()) || props.getZoanPoint().equalsIgnoreCase(form);
 * }
 * }
 */