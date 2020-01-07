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
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;

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
			IAbilityData abilityData = AbilityDataCapability.get(livingEntity);
			boolean mainHandFlag = livingEntity.getHeldItemMainhand() == itemStack;
			boolean hakiActiveFlag = false;//abilityData.isPassiveActive(ModAttributes.BUSOSHOKU_HAKI);
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

	public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5)
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

		if (isPoisonous)
			target.addPotionEffect(new EffectInstance(Effects.POISON, this.poisonTimer, 0));

		if (isFireAspect)
			target.setFire(this.fireAspectTimer);

		if (isSlownessInducing)
		{
			if (isStackable)
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

	public CoreSwordItem setIsPoisonous()
	{
		this.isPoisonous = true;
		this.poisonTimer = 100;
		return this;
	}

	public CoreSwordItem setIsPoisonous(int timer)
	{
		this.isPoisonous = true;
		this.poisonTimer = timer;
		return this;
	}

	public CoreSwordItem setIsFireAspect()
	{
		this.isFireAspect = true;
		return this;
	}

	public CoreSwordItem setIsFireAspect(int timer)
	{
		this.isFireAspect = true;
		this.fireAspectTimer = timer;
		return this;
	}

	public CoreSwordItem setIsSlownessInducing()
	{
		this.isSlownessInducing = true;
		return this;
	}

	public CoreSwordItem setIsSlownessInducing(int timer)
	{
		this.isSlownessInducing = true;
		this.slownessTimer = timer;
		return this;
	}

	public CoreSwordItem setIsSlownessInducing(int timer, boolean isStackable)
	{
		this.isSlownessInducing = true;
		this.slownessTimer = timer;
		this.isStackable = isStackable;

		return this;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot)
	{
		Multimap<String, AttributeModifier> multimap = HashMultimap.create();

		if(equipmentSlot == EquipmentSlotType.MAINHAND)
		{
			ItemStack stack = new ItemStack(this);
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
