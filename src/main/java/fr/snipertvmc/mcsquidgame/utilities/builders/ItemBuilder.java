package fr.snipertvmc.mcsquidgame.utilities.builders;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import de.tr7zw.nbtapi.iface.ReadableNBT;
import de.tr7zw.nbtapi.iface.ReadableNBTList;
import kotlin.Pair;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class ItemBuilder {
	
	
	// -------------------------------------------------- //
	

	private ItemStack item;
	private ItemMeta meta;

	private Material material;
	private int amount = 1;
	private int durability = 0;

	private String displayName;
	private List<String> lore = new ArrayList<>();
	private List<Pair<Enchantment, Integer>> enchantments = new ArrayList<>();
	private List<ItemFlag> flags = new ArrayList<>();

	private String skullOwner;
	private String skullTexture;

	
	// -------------------------------------------------- //
	
	
	public ItemBuilder(Material material) {
		this.material = material;
	}

	
	public ItemBuilder(Material material, int amount) {
		this.amount = amount;
		this.material = material;
	}

	
	public ItemBuilder(Material material, int amount, String displayName) {
		this.material = material;
		this.amount = amount;
		this.displayName = displayName;
	}

	
	public ItemBuilder(Material material, String displayName) {
		if(material == null) material = Material.AIR;
		this.material = material;
		this.displayName = displayName;
	}


	public ItemBuilder(ItemStack item) {

		if (item == null || !item.hasItemMeta()) {
			return;
		}

		this.item = item;
		this.material = item.getType();
		this.amount = item.getAmount();

		ItemMeta meta = item.getItemMeta();
		if (meta instanceof Damageable) {
			this.durability = ((Damageable) meta).getDamage();
		} else {
			this.durability = 0;
		}

		this.meta = item.getItemMeta();
		if (meta.hasDisplayName()) {
			this.displayName = meta.getDisplayName();
		}
		if (meta.hasLore()) {
			this.lore = new ArrayList<>(meta.getLore());
		}
		if (!meta.getEnchants().isEmpty()) {
			for (Map.Entry<Enchantment, Integer> entry : meta.getEnchants().entrySet()) {
				this.enchantments.add(new Pair<>(entry.getKey(), entry.getValue()));
			}
		}
		if (!meta.getItemFlags().isEmpty()) {
			this.flags = new ArrayList<>(meta.getItemFlags());
		}


		if (item.getType() == Material.PLAYER_HEAD && meta instanceof SkullMeta skullMeta) {
			if (skullMeta.hasOwner()) {
				this.skullOwner = skullMeta.getOwningPlayer().getName();
			}

			NBT.get(item, nbt -> {
				ReadableNBT skullOwnerCompound = nbt.getCompound("SkullOwner");
				if (skullOwnerCompound != null) {
					ReadableNBT properties = skullOwnerCompound.getCompound("Properties");
					if (properties != null) {
						ReadableNBTList<ReadWriteNBT> texturesList = properties.getCompoundList("textures");
						if (texturesList != null && !texturesList.isEmpty()) {
							this.skullTexture = texturesList.get(0).getString("Value");
						}
					}
				}
			});
		}
	}


	// -------------------------------------------------- //


	public ItemBuilder amount(int amount) {
		this.amount = amount;
		return this;
	}


	public ItemBuilder durability(short damage) {
		this.durability = damage;
		return this;
	}


	public ItemBuilder material(Material material) {
		this.material = material;
		return this;
	}


	public ItemBuilder meta(ItemMeta meta) {
		this.meta = meta;
		return this;
	}


	public ItemBuilder enchant(Enchantment enchantment, int level) {
		Pair<Enchantment, Integer> enchantmentPair = new Pair<>(enchantment, level);
		this.enchantments.add(enchantmentPair);
		return this;
	}


	public ItemBuilder enchant(List<Pair<Enchantment, Integer>> enchantments) {
		this.enchantments = enchantments;
		return this;
	}



	public ItemBuilder displayName(String displayName) {
		this.displayName = displayName;
		return this;
	}


	public ItemBuilder addLore(String line) {
		lore.add(line);
		return this;
	}


	public ItemBuilder setLore(String line, int index) {
		lore.set(index, line);
		return this;
	}


	public ItemBuilder removeLore(int index) {
		lore.remove(index);
		return this;
	}


	public ItemBuilder lore(String... lore) {
		this.lore = Arrays.stream(lore).toList();
		return this;
	}


	public ItemBuilder lore(List<String> lore) {
		this.lore = lore;
		return this;
	}


	public ItemBuilder flag(ItemFlag flag) {
		flags.add(flag);
		return this;
	}


	public ItemBuilder flag(List<ItemFlag> flags) {
		this.flags = flags;
		return this;
	}


	public ItemBuilder unbreakable(boolean unbreakable) {
		meta.setUnbreakable(unbreakable);
		return this;
	}


	public ItemBuilder glow() {
		enchant(material != Material.BOW ? Enchantment.ARROW_INFINITE : Enchantment.LUCK, 10);
		flag(ItemFlag.HIDE_ENCHANTS);
		return this;
	}


	public ItemBuilder owner(String skullOwner) {
		if (material == Material.PLAYER_HEAD) {
			this.skullOwner = skullOwner;
		}
		return this;
	}


	public ItemBuilder skullTexture(String skullTexture) {
		if (material == Material.PLAYER_HEAD) {
			this.skullTexture = skullTexture;
		}
		return this;
	}


	// -------------------------------------------------- //


	public ItemMeta getMeta() {
		return meta;
	}


	public Material getMaterial() {
		return material;
	}


	public String getSkullOwner() {
		return skullOwner;
	}


	public String getSkullTexture() {
		return skullTexture;
	}


	public int getAmount() {
		return amount;
	}


	public int getDurability() {
		return durability;
	}


	public String getDisplayName() {
		return displayName;
	}


	public List<String> getLore() {
		return lore;
	}


	public List<Pair<Enchantment, Integer>> getEnchantments() {
		return enchantments;
	}


	public List<ItemFlag> getFlags() {
		return flags;
	}


	// -------------------------------------------------- //


	public ItemStack build() {
		item = new ItemStack(material, amount);
		meta = item.getItemMeta();
		if(!enchantments.isEmpty()) {
			for (Pair<Enchantment, Integer> enchantment : enchantments) {
				meta.addEnchant(enchantment.getFirst(), enchantment.getSecond(), true);
			}
		}
		if (durability > 0 && meta instanceof Damageable) {
			((Damageable) meta).setDamage(durability);
		}
		if(displayName != null) {
			meta.setDisplayName(displayName);
		}
		if(!lore.isEmpty()) {
			meta.setLore(lore);
		}
		if(!flags.isEmpty()) {
			for (ItemFlag f : flags) {
				meta.addItemFlags(f);
			}
		}
		item.setItemMeta(meta);
		if (skullOwner != null) {
			SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
			OfflinePlayer owner = Bukkit.getOfflinePlayer(skullOwner);
			skullMeta.setOwningPlayer(owner);
			item.setItemMeta(skullMeta);
		}

		if (skullTexture != null) {
			NBT.modify(item, nbt -> {
				ReadWriteNBT skullOwnerCompound = nbt.getOrCreateCompound("SkullOwner");
				// Assurez-vous que l'UUID est défini de manière cohérente
				skullOwnerCompound.setUUID("Id", UUID.randomUUID());
				skullOwnerCompound.getOrCreateCompound("Properties")
						.getCompoundList("textures")
						.addCompound()
						.setString("Value", skullTexture);
			});
		}
		return item;
	}


	// -------------------------------------------------- //
}
