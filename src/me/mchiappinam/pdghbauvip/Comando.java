package me.mchiappinam.pdghbauvip;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Comando implements CommandExecutor {
	private Main plugin;

	public Comando(Main main) {
		plugin = main;
	}
	
  	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("bauvip")) {
        	if(!sender.hasPermission("pdgh.admin")) {
                sender.sendMessage("§cSem permissões");
        		return true;
        	}
        	if((args.length==0)||(args.length>1)) {
        		sender.sendMessage("§3§lPDGH BAU VIP - Comandos:");
        		sender.sendMessage("§2/bauvip reiniciar -§a- Reinicia o baú manualmente.");
        		sender.sendMessage("§2/bauvip setbau -§a- Marca a posição do baú.");
        		return true;
        	}
        	if(args[0].equalsIgnoreCase("reiniciar")) {
            	if(!sender.hasPermission("pdgh.admin")) {
                    sender.sendMessage("§cSem permissões");
            		return true;
            	}
        		sender.sendMessage("§6[BaúVIP] §eBaú resetado!");
        		plugin.reset();
        		return true;
        	}else if(args[0].equalsIgnoreCase("setbau")) {
            	if(!sender.hasPermission("pdgh.admin")) {
                    sender.sendMessage("§cSem permissões");
            		return true;
            	}
				Player p = (Player)sender;
				plugin.spawn=p.getLocation();
				plugin.getConfig().set("bau", plugin.spawn.getWorld().getName()+";"+plugin.spawn.getX()+";"+plugin.spawn.getY()+";"+plugin.spawn.getZ()+";"+plugin.spawn.getYaw()+";"+plugin.spawn.getPitch());
				plugin.saveConfig();
	    		sender.sendMessage("§6[BaúVIP] §eBaú marcado com sucesso!");
            	return true;
            }
        	if((args.length==0)||(args.length>1)) {
        		sender.sendMessage("§3§lPDGH BAU VIP - Comandos:");
        		sender.sendMessage("§2/bauvip reiniciar -§a- Reinicia o baú manualmente.");
        		sender.sendMessage("§2/bauvip setbau -§a- Marca a posição do baú.");
        		return true;
        	}
        	return true;
        }
		return false;
    }
  	
}