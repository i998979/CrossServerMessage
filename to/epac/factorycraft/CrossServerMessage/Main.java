package to.epac.factorycraft.CrossServerMessage;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	}

	@Override
	public void onDisable() {

	}

	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String label,
			final String[] args) {
		//csm <Player> <Message>
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length < 2){
				player.sendMessage(ChatColor.RED + "/csm <Player> <Message>");
				return false;
			}
			
			String playerToSend = args[0];
			
			String msg = ChatColor.GRAY + "From " + sender.getName() + ": ";
			for(int i = 1; i < args.length; i++)
				msg += args[i] + " ";
			
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(b);
			
			try{
				out.writeUTF("Message");
				out.writeUTF(playerToSend); 
				out.writeUTF(msg);
			}catch (Exception e){
			}
			player.sendPluginMessage(this, "BungeeCord", b.toByteArray());
			
			//Send the original message back to the player
			String msg2 = "";
			for(int i = 1; i < args.length; i++)
				msg2 += args[i] + " ";
			player.sendMessage(ChatColor.GRAY + "To " + playerToSend + ": " + msg2);
			//
			
		}
		return false;
	}

}
