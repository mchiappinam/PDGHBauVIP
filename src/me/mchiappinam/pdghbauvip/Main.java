package me.mchiappinam.pdghbauvip;

import java.io.File;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	protected Location spawn;
	protected int slot = 0;
	
	public void onEnable() {
		
		File file=new File(getDataFolder(),"config.yml");
		if(!file.exists()) {
			try {
				saveResource("config_template.yml",false);
				File file2=new File(getDataFolder(),"config_template.yml");
				file2.renameTo(new File(getDataFolder(),"config.yml"));
			}
			catch(Exception e) {}
		}
	    getServer().getPluginCommand("bauvip").setExecutor(new Comando(this));
	    delay30Min();
	    getServer().getConsoleSender().sendMessage("§3[PDGHBauVIP] §2ativado - Plugin by: mchiappinam");
	    getServer().getConsoleSender().sendMessage("§3[PDGHBauVIP] §2Acesse: http://pdgh.com.br/");
	}

	public void onDisable() {
	    getServer().getConsoleSender().sendMessage("§3[PDGHBauVIP] §2desativado - Plugin by: mchiappinam");
	    getServer().getConsoleSender().sendMessage("§3[PDGHBauVIP] §2Acesse: http://pdgh.com.br/");
	}
	
	public void delay1MinSumirBau() {
		getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			public void run() {
  				Block bau = spawn.clone().add(0, 0, 0).getBlock();
  			    bau.setType(Material.AIR);
  				getServer().broadcastMessage("§6[BaúVIP] §eBaú premiado da loja §6§lVIP§e sumiu! Aguarde o próximo horário!");
			}
		}, 60*20L);
	}
	
	public void delay30Min() {
		getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			public void run() {
				reset();
				delay1H();
			}
		}, 30*60*20L);
	}
	
	public void delay1H() {
		getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			public void run() {
				reset();
			}
		}, 60*60*20L);
	}
	
	public void getSlot() {
	    Random randomgen=new Random();
    	slot=randomgen.nextInt(26)+1;
    	if((slot==13)||(slot==0)||(slot==8)||(slot==18)||(slot==26)||(slot==10)||(slot==16)) {
    		getSlot();
    	}
	}
	
	public void reset() {
		String ent[] = getConfig().getString("bau").split(";");
		spawn = new Location(getServer().getWorld(ent[0]),Double.parseDouble(ent[1]),Double.parseDouble(ent[2]),Double.parseDouble(ent[3]),Float.parseFloat(ent[4]),Float.parseFloat(ent[5]));
	    spawn.setYaw(Float.parseFloat(ent[4]));
		spawn.setPitch(Float.parseFloat(ent[5]));
		Block bau = spawn.clone().add(0, 0, 0).getBlock();
	    bau.setType(Material.CHEST);
	    Chest addbau = (Chest)bau.getState();
	    addbau.getBlockInventory().clear();
	    
	    addbau.getBlockInventory().setItem(13, new ItemStack(Material.GHAST_TEAR, 32));
	    addbau.getBlockInventory().setItem(0, new ItemStack(Material.GOLDEN_APPLE, 5));
	    addbau.getBlockInventory().setItem(8, new ItemStack(Material.GOLDEN_APPLE, 5));
	    addbau.getBlockInventory().setItem(18, new ItemStack(Material.GOLDEN_APPLE, 5));
	    addbau.getBlockInventory().setItem(26, new ItemStack(Material.GOLDEN_APPLE, 5));
	    addbau.getBlockInventory().setItem(10, new ItemStack(Material.GOLD_AXE, 1));
	    addbau.getBlockInventory().setItem(16, new ItemStack(Material.IRON_AXE, 1));
	    getSlot();
	    Random randomgen=new Random();
    	int i=randomgen.nextInt(5)+1;
    	if(i==1) {
    	    addbau.getBlockInventory().setItem(slot, new ItemStack(Material.GOLDEN_APPLE, 1,(short)1));
    	}else if(i==2) {
		    ItemStack elmo = new ItemStack(Material.DIAMOND_HELMET, 1);
		    elmo.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
    	    addbau.getBlockInventory().setItem(slot, elmo);
    	}else if(i==3) {
		    ItemStack peito = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		    peito.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
    	    addbau.getBlockInventory().setItem(slot, peito);
    	}else if(i==4) {
		    ItemStack calca = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
		    calca.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
    	    addbau.getBlockInventory().setItem(slot, calca);
    	}else if(i==5) {
		    ItemStack bota = new ItemStack(Material.DIAMOND_BOOTS, 1);
		    bota.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
    	    addbau.getBlockInventory().setItem(slot, bota);
    	}
	    addbau.update();
		getServer().broadcastMessage("§6[BaúVIP] §eBaú premiado da loja §6§lVIP§e spawnado com sucesso.");
		delay1MinSumirBau();
	}
}