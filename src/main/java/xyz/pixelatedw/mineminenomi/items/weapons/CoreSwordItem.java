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
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiImbuingAbility;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class CoreSwordItem extends Item
{
	private double damage = 1;
	private double multiplier = 1;
	private boolean canUseSpecial = false;
	protected boolean isPoisonous = false, isFireAspect = false, isSlownessInducing = false, isStackable = false;
	protected int poisonTimer = 100, fireAspectTimer = 100, slownessTimer = 100;

	private IItemPropertyGetter hakiProperty = (itemStack, world, livingEntity) ->
	{
		if (livingEntity == null || !(livingEntity instanceof PlayerEntity))
		{
			return 0.0F;
		}
		else
		{
			IAbilityData props = AbilityDataCapability.get(livingEntity);
			boolean mainHandFlag = livingEntity.getHeldItemMainhand() == itemStack;
			BusoshokuHakiImbuingAbility ability = props.getEquippedAbility(BusoshokuHakiImbuingAbility.INSTANCE);
			boolean hakiActiveFlag = ability != null && ability.isContinuous();
			return mainHandFlag && hakiActiveFlag ? 1.0F : 0.0F;
		}
	};
	
	private IItemPropertyGetter sheathedProperty = (itemStack, world, livingEntity) ->
	{
		if (livingEntity == null || !(livingEntity instanceof PlayerEntity))
		{
			return 0.0F;
		}
		else
		{
			boolean mainHandFlag = livingEntity.getHeldItemMainhand() != itemStack;
			return mainHandFlag ? 1.0F : 0.0F;
		}
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

	public CoreSwordItem(int damage, boolean canUseSpecial)
	{
		super(new Properties().group(ModCreativeTabs.WEAPONS).maxStackSize(1).defaultMaxDamage(500));
		this.damage = damage;
		this.canUseSpecial = canUseSpecial;
		this.addPropertyOverride(new ResourceLocation("haki"), this.hakiProperty);
		this.addPropertyOverride(new ResourceLocation("sheathed"), this.sheathedProperty);
	}

	public void inventoryTick(ItemStack itemStack, World world, Entity entity, int par4, boolean par5)
	{
		if (!itemStack.hasTag())
		{
			itemStack.setTag(new CompoundNBT());
			itemStack.getTag().putInt("metadata", 0);
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
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}

	@Override
	public int getItemEnchantability()
	{
		return 14;
	}

	public CoreSwordItem setQuality()
	{
		return this;
	}

	@Override
	public boolean hitEntity(ItemStack itemStack, LivingEntity target, LivingEntity attacker)
	{
		IAbilityData abilityProps = AbilityDataCapability.get(attacker);
		
		//if (!props.hasBusoHakiActive())
		//	itemStack.damageItem(1, attacker);

		if (this.isPoisonous)
			target.addPotionEffect(new EffectInstance(Effects.POISON, this.poisonTimer, 0));

		if (this.isFireAspect)
			target.setFire(this.fireAspectTimer);

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
			double multiplier;
			if (stack.getTag() != null)
				multiplier = stack.getTag().getDouble("multiplier");
			else
				multiplier = 1;
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.damage * multiplier, Operation.ADDITION));		
		}	
		
		return multimap;
	}
}
