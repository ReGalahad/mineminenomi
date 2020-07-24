package xyz.pixelatedw.mineminenomi.items.weapons;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiImbuingAbility;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.wypi.abilities.events.SetOnFireEvent;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class CoreSwordItem extends Item
{
	private double damage = 1;
	private double speed = -2.4D;
	protected boolean isPoisonous = false, isFireAspect = false, isSlownessInducing = false, isStackable = false;
	protected int poisonTimer = 100, fireAspectTimer = 100, slownessTimer = 100;
	private boolean isBlunt = false;

	private IItemPropertyGetter hakiProperty = (itemStack, world, livingEntity) ->
	{
		if(livingEntity == null)
			return 0;
		
		float hasHakiActive = 0;
		if (livingEntity instanceof PlayerEntity)
		{
			IAbilityData props = AbilityDataCapability.get(livingEntity);
			boolean mainHandFlag = livingEntity.getHeldItemMainhand().getItem() == itemStack.getItem();
			boolean offHandFlag = livingEntity.getHeldItemOffhand().getItem() == itemStack.getItem();
			BusoshokuHakiImbuingAbility ability = props.getEquippedAbility(BusoshokuHakiImbuingAbility.INSTANCE);
			boolean hakiActiveFlag = ability != null && ability.isContinuous();
			hasHakiActive = (mainHandFlag || offHandFlag) && hakiActiveFlag ? 1 : 0;
		}
		else if (livingEntity instanceof GenericNewEntity)
		{
			hasHakiActive = ((GenericNewEntity) livingEntity).hasBusoHaki() ? 1 : 0;
		}
		return hasHakiActive;
	};
	
	private IItemPropertyGetter sheathedProperty = (itemStack, world, livingEntity) ->
	{
		if(livingEntity == null)
			return 1;
		
		boolean mainHandFlag = livingEntity.getHeldItemMainhand().getItem() != itemStack.getItem();
		boolean offHandFlag = livingEntity.getHeldItemOffhand().getItem() != itemStack.getItem();
		return (mainHandFlag && offHandFlag) ? 1.0F : 0.0F;
	};
	
	public CoreSwordItem(Properties props, int damage)
	{
		super(props);
		this.damage = damage;
		this.addPropertyOverride(new ResourceLocation("haki"), this.hakiProperty);
		this.addPropertyOverride(new ResourceLocation("sheathed"), this.sheathedProperty);
	}
	
	public CoreSwordItem(int damage, int durability)
	{
		super(new Properties().group(ModCreativeTabs.WEAPONS).maxStackSize(1).defaultMaxDamage(durability));
		this.damage = damage;
		this.addPropertyOverride(new ResourceLocation("haki"), this.hakiProperty);
		this.addPropertyOverride(new ResourceLocation("sheathed"), this.sheathedProperty);
	}

	@Override
	public void inventoryTick(ItemStack itemStack, World world, Entity entity, int par4, boolean par5)
	{
		if (!itemStack.hasTag())
		{
			itemStack.setTag(new CompoundNBT());
			itemStack.getTag().putDouble("multiplier", 1);
		}

		if (!world.isRemote)
		{
			IEntityStats statProps = EntityStatsCapability.get((LivingEntity) entity);
			IAbilityData abilityProps = AbilityDataCapability.get( (PlayerEntity) entity);

			double multiplier = 1;

			BusoshokuHakiImbuingAbility ability = abilityProps.getEquippedAbility(BusoshokuHakiImbuingAbility.INSTANCE);
			boolean hakiActiveFlag = ability != null && ability.isContinuous();

			if(hakiActiveFlag)
				multiplier += 0.50;
			
			if (statProps.isSwordsman())
				multiplier += 0.25;

			itemStack.getTag().putDouble("multiplier", multiplier);
			
			if(itemStack.getTag().getBoolean("isClone") && !itemStack.getTag().getBoolean("hasCloneTag"))
			{
				itemStack.setDisplayName(new StringTextComponent(TextFormatting.RESET + itemStack.getDisplayName().getFormattedText() + " (Clone)"));
				itemStack.getTag().putBoolean("hasCloneTag", true);
			}
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		return new ActionResult<>(ActionResultType.PASS, player.getHeldItem(hand));
	}

	@Override
	public int getItemEnchantability()
	{
		return 14;
	}

	@Override
	public boolean hitEntity(ItemStack itemStack, LivingEntity target, LivingEntity attacker)
	{
		IAbilityData abilityProps = AbilityDataCapability.get(attacker);
		
		BusoshokuHakiImbuingAbility ability = abilityProps.getEquippedAbility(BusoshokuHakiImbuingAbility.INSTANCE);
		boolean hasBusoHaki = ability != null && ability.isContinuous();
		
		if (!hasBusoHaki)
		{
			int damage = itemStack.getOrCreateTag().getBoolean("isClone") ? 3 : 1;
			itemStack.damageItem(damage, attacker, (entity) ->
			{
				entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
			});
		}

		if (this.isPoisonous)
			target.addPotionEffect(new EffectInstance(Effects.POISON, this.poisonTimer, 0));

		if (this.isFireAspect)
		{
			SetOnFireEvent event = new SetOnFireEvent(attacker, target, this.fireAspectTimer);
			if (!MinecraftForge.EVENT_BUS.post(event))
				target.setFire(this.fireAspectTimer);
		}

		if (this.isSlownessInducing)
		{
			if (this.isStackable)
			{
				if (target.isPotionActive(Effects.SLOWNESS))
				{
					int timer = target.getActivePotionEffect(Effects.SLOWNESS).getDuration();
					target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, timer + this.slownessTimer, 0));
				}
				else
					target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, this.slownessTimer, 0));
			}
			else
				target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, this.slownessTimer, 0));
		}

		return true;
	}

	public <T extends CoreSwordItem> T setIsPoisonous()
	{
		this.isPoisonous = true;
		this.poisonTimer = 100;
		return (T) this;
	}

	public <T extends CoreSwordItem> T setIsPoisonous(int timer)
	{
		this.isPoisonous = true;
		this.poisonTimer = timer;
		return (T) this;
	}

	public  <T extends CoreSwordItem> T setSwordSpeed(double speed) {
		this.speed = speed;
		return (T) this;
	}

	public  <T extends CoreSwordItem> T setBlunt() {
		this.isBlunt = true;
		return (T) this;
	}

	public boolean isBlunt() {
		return this.isBlunt;
	}

	public <T extends CoreSwordItem> T setIsFireAspect()
	{
		this.isFireAspect = true;
		return (T) this;
	}

	public <T extends CoreSwordItem> T setIsFireAspect(int timer)
	{
		this.isFireAspect = true;
		this.fireAspectTimer = timer;
		return (T) this;
	}

	public <T extends CoreSwordItem> T setIsSlownessInducing()
	{
		this.isSlownessInducing = true;
		return (T) this;
	}

	public <T extends CoreSwordItem> T setIsSlownessInducing(int timer)
	{
		this.isSlownessInducing = true;
		this.slownessTimer = timer;
		return (T) this;
	}

	public <T extends CoreSwordItem> T setIsSlownessInducing(int timer, boolean isStackable)
	{
		this.isSlownessInducing = true;
		this.slownessTimer = timer;
		this.isStackable = isStackable;

		return (T) this;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack)
	{
		Multimap<String, AttributeModifier> multimap = HashMultimap.create();

		if(equipmentSlot == EquipmentSlotType.MAINHAND)
		{
			double multiplier = 1;
			if (stack.getTag() != null)
			{
				multiplier = stack.getTag().getDouble("multiplier");
				if(stack.getTag().getBoolean("isClone"))
					multiplier /= 1.25;
			}
			
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", Math.round(this.damage * multiplier), Operation.ADDITION));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Speed modifier", this.speed, Operation.ADDITION));
		}
		
		return multimap;
	}
}
