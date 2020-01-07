/*
 * package xyz.pixelatedw.mineminenomi.abilities;
 * import net.minecraft.entity.player.PlayerEntity;
 * import net.minecraft.item.ItemStack;
 * import net.minecraft.item.Items;
 * import net.minecraft.util.math.RayTraceResult;
 * import net.minecraftforge.event.entity.living.EnderTeleportEvent;
 * import xyz.pixelatedw.mineminenomi.api.WyHelper;
 * import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
 * import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.PikaProjectiles;
 * import xyz.pixelatedw.mineminenomi.init.ModAttributes;
 * import xyz.pixelatedw.mineminenomi.init.ModValues;
 * import xyz.pixelatedw.mineminenomi.init.ModWeapons;
 * public class PikaAbilities
 * {
 * static
 * {
 * ModValues.abilityWebAppExtraParams.put("yasakaninomagatama", new String[] {"desc", "Fires a torrent of deadly light particles, causing huge destruction."});
 * ModValues.abilityWebAppExtraParams.put("yatanokagami", new String[] {"desc", "Uses light to instantly teleport the user to their desired location."});
 * ModValues.abilityWebAppExtraParams.put("amaterasu", new String[] {"desc", "Creates an immense beam of light, which causes massive damage."});
 * ModValues.abilityWebAppExtraParams.put("flash", new String[] {"desc", "The user creates a bright flash of light, blinding their opponents."});
 * ModValues.abilityWebAppExtraParams.put("amanomurakumo", new String[] {"desc", "Focuses light in the user's hand to create a sword."});
 * }
 * public static Ability[] abilitiesArray = new Ability[] {new YataNoKagami(), new AmaNoMurakumo(), new YasakaniNoMagatama(), new Amaterasu(), new Flash()};
 * public static class Flash extends Ability
 * {
 * public Flash()
 * {
 * super(ModAttributes.FLASH);
 * }
 * @Override
 * public void use(PlayerEntity player)
 * {
 * //if(!this.isOnCooldown)
 * // ModNetwork.sendToAllAround(new SParticlesPacket(ID.PARTICLEFX_FLASH, player), (ServerPlayerEntity) player);
 * super.use(player);
 * }
 * }
 * public static class AmaNoMurakumo extends Ability
 * {
 * public AmaNoMurakumo()
 * {
 * super(ModAttributes.AMA_NO_MURAKUMO);
 * }
 * @Override
 * public void startPassive(PlayerEntity player)
 * {
 * if(player.getHeldItemMainhand() == ItemStack.EMPTY || player.getHeldItemMainhand().getItem() == Items.AIR)
 * player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ModWeapons.amaNoMurakumo));
 * else
 * {
 * WyHelper.sendMsgToPlayer(player, "Cannot equip " + this.getAttribute().getAttributeName() + " while holding another item in hand !");
 * this.passive(player);
 * }
 * }
 * @Override
 * public void endPassive(PlayerEntity player)
 * {
 * WyHelper.removeStackFromInventory(player, new ItemStack(ModWeapons.amaNoMurakumo));
 * }
 * }
 * public static class Amaterasu extends Ability
 * {
 * public Amaterasu()
 * {
 * super(ModAttributes.AMATERASU);
 * }
 * @Override
 * public void startCharging(PlayerEntity player)
 * {
 * //if(!this.isOnCooldown)
 * // ModNetwork.sendToAllAround(new SParticlesPacket(ID.PARTICLEFX_AMATERASU, player), (ServerPlayerEntity) player);
 * super.startCharging(player);
 * }
 * @Override
 * public void endCharging(PlayerEntity player)
 * {
 * this.projectile = new PikaProjectiles.Amaterasu(player.world, player, attr);
 * super.endCharging(player);
 * }
 * }
 * public static class YasakaniNoMagatama extends Ability
 * {
 * public YasakaniNoMagatama()
 * {
 * super(ModAttributes.YASAKANI_NO_MAGATAMA);
 * }
 * @Override
 * public void use(PlayerEntity player)
 * {
 * this.projectile = new PikaProjectiles.YasakaniNoMagatama(player.world, player, attr);
 * super.use(player);
 * }
 * }
 * public static class YataNoKagami extends Ability
 * {
 * public YataNoKagami()
 * {
 * super(ModAttributes.YATA_NO_KAGAMI);
 * }
 * @Override
 * public void use(PlayerEntity player)
 * {
 * if(!this.isOnCooldown)
 * {
 * if(WyHelper.rayTraceBlocks(player) != null)
 * {
 * RayTraceResult mop = WyHelper.rayTraceBlocks(player);
 * double x = mop.getHitVec().x;
 * double y = mop.getHitVec().y;
 * double z = mop.getHitVec().z;
 * if (player.getRidingEntity() != null)
 * player.dismountEntity(player.getRidingEntity());
 * EnderTeleportEvent event = new EnderTeleportEvent(player, x, y, z, 5.0F);
 * //ModNetwork.sendToAllAround(new SParticlesPacket(ID.PARTICLEFX_YATANOKAGAMI, player), (ServerPlayerEntity) player);
 * player.setPositionAndUpdate(event.getTargetX(), event.getTargetY() + 1, event.getTargetZ());
 * //ModNetwork.sendToAllAround(new SParticlesPacket(ID.PARTICLEFX_YATANOKAGAMI, player), (ServerPlayerEntity) player);
 * player.fallDistance = 0.0F;
 * }
 * super.use(player);
 * }
 * }
 * }
 * }
 */