package xyz.pixelatedw.mineminenomi.entities.mobs.misc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Hand;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.screens.TraderScreen;

public class TraderEntity extends GenericNewEntity {

	private static final DataParameter<CompoundNBT> ITEMS = EntityDataManager.createKey(TraderEntity.class,
			DataSerializers.COMPOUND_NBT);
	public List<ItemStack> STACKS = new ArrayList<ItemStack>();

	public TraderEntity(World worldIn) {
		super(ModEntities.TRADER, worldIn, null);

		this.textures = new String[] { "weapontrader", "dialtrader", "clothingtrader", "resourcetrader" };

	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(ITEMS, new CompoundNBT());
	}

	@Override
	public void writeAdditional(CompoundNBT nbt) {
		super.writeAdditional(nbt);
		nbt.put("itemstacks", this.getNBT());

	}

	@Override
	public void readAdditional(CompoundNBT nbt) {
		super.readAdditional(nbt);

		this.setNBT(nbt.getCompound("itemstacks"));
	}

	public CompoundNBT clear(CompoundNBT nbt) {
		
		Iterator<String> iterator = nbt.keySet().iterator();
		while(iterator.hasNext()) {
			String str = iterator.next();
			iterator.remove();
		}
		return nbt;
	}

	public CompoundNBT getNBT() {
		CompoundNBT nbt = this.getDataManager().get(ITEMS);
		this.clear(nbt);
		for (ItemStack stack : this.STACKS) {

			nbt.putInt(stack.getItem().getRegistryName().getPath(), stack.getCount());

		}
		return this.getDataManager().get(ITEMS);

	}

	public void setNBT(CompoundNBT val) {
		this.getDataManager().set(ITEMS, val);
		this.setStacksFromNBT(this.getNBT());
	}

	public void setStacksFromNBT(CompoundNBT nbt) {

		this.STACKS.clear();
		for (Item item : ForgeRegistries.ITEMS.getValues()) {
			if (nbt.contains(item.getRegistryName().getPath())) {
				ItemStack stack = new ItemStack(item);
				stack.setCount(nbt.getInt(item.getRegistryName().getPath()));
				this.STACKS.add(new ItemStack(item));
			}
		}
	}
	public void changeStackCount(ItemStack stack, int newCount) {
		for(ItemStack tempStack : this.STACKS) {
			if(tempStack.getItem() == stack.getItem()) {
				tempStack.setCount(newCount);
				break;
			}
		}
	}

	public void generate() {
		if (!this.world.isRemote) {
			LootTable loottable = this.world.getServer().getLootTableManager()
					.getLootTableFromLocation(ModResources.TRADER_WEAPON);
			LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld) this.world));

			LootContext context = lootcontext$builder.build(LootParameterSets.EMPTY);
			List<ItemStack> stacks = loottable.generate(context);

			if (this.checkForDuplicate(stacks)) {
				this.generate();

			} else {
				this.STACKS = stacks;
			}

		}
	}

	public boolean checkForDuplicate(List<ItemStack> stacks) {
		for (ItemStack stack : stacks) {
			List<ItemStack> stacks2 = new ArrayList<ItemStack>(stacks);
			stacks2.remove(stack);
			for (ItemStack stack2 : stacks2) {
				if (stack.getItem() == stack2.getItem()) {
					return true;
				}
			}
		}
		return false;
	}
	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) 
	{
		spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
		
	
		this.generate();
		
		return spawnData;
	}

	@Override
	   protected boolean processInteract(PlayerEntity player, Hand hand) {
	      if(world.isRemote()) {
	    	  if(Minecraft.getInstance().currentScreen == null) {
	    	  Minecraft.getInstance().displayGuiScreen(new TraderScreen(this));
	    	  return true;
	    	  }
	      }
		return false;
	   }

	
}
