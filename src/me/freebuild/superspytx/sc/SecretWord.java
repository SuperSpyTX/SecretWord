package me.freebuild.superspytx.sc;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import me.freebuild.superspytx.sc.Metrics.Graph;
import me.freebuild.superspytx.sc.database.DatabaseCore;
import me.freebuild.superspytx.sc.database.SecretPlayer;
import me.freebuild.superspytx.sc.events.CoreEvents;
import me.freebuild.superspytx.sc.events.EntityEvents;
import me.freebuild.superspytx.sc.events.PlayerEvents;
import me.freebuild.superspytx.sc.settings.Configuration;
import me.freebuild.superspytx.sc.settings.Permissions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class SecretWord extends JavaPlugin
{

    // cores
    private DatabaseCore dbcore = null;
    private CoreEvents clisten = null;
    private PlayerEvents plisten = null;
    private EntityEvents elisten = null;
    public static SecretWord instance = null;

    public void onDisable()
    {
        // lol.
    }

    public void onEnable()
    {
        instance = this;
        Configuration.reloadConfiguration();
        dbcore = new DatabaseCore(this);
        clisten = new CoreEvents(this);
        plisten = new PlayerEvents(this);
        elisten = new EntityEvents(this);
        getServer().getPluginManager().registerEvents(clisten, this);
        getServer().getPluginManager().registerEvents(plisten, this);
        getServer().getPluginManager().registerEvents(elisten, this);
        PluginDescriptionFile pdfFile = getDescription();
        Configuration.version = "v" + pdfFile.getVersion();
        getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable()
        {
            public void run()
            {
                for (Player pl : Bukkit.getOnlinePlayers())
                {
                    SecretPlayer player = dbcore.secplayers.get(pl.getName());
                    if (player != null)
                    {
                        if (!player.isLoggedIn() && player.isRegistered() && !player.thirtySeconds())
                        {
                            pl.kickPlayer("You've taken too long to login.");
                        }
                    }
                }
            }
        }, 600L, 600L);
        try
        {
            Metrics metrics = new Metrics(this);

            Graph graph = metrics.createGraph("SecretWord Data");

            graph.addPlotter(new Metrics.Plotter("Blocked MITM Attacks")
            {

                @Override
                public int getValue()
                {
                    return dbcore.failedlogins;
                }

            });

            metrics.start();

            // report version to ingame.

        }
        catch (IOException e)
        {
            System.out.println("Metrics haz failed.");
        }

        getLogger().log(Level.INFO, "SecretWord " + Configuration.version + " has been enabled! Please check for updates quite often for latest bug fixes!");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (args.length < 1)
        {
            returnMotd(sender);
            return true;
        }
        String arg1 = "all";
        String arg2 = "your";
        //String arg3 = "base"; // are belong to us.
        try
        {
            arg1 = args[0];
            arg2 = args[1];
            //arg3 = args[2];
        }
        catch (Exception e)
        {

        }
        // check permissions.
        if (!Permissions.ADMIN_RELOAD.check(sender) && !Permissions.ADMIN_RESET.check(sender))
        {
            sender.sendMessage(Configuration.prefix + ChatColor.RED + "You do not have permission!");
            return true;
        }

        if (arg1.equalsIgnoreCase("help") || arg1.equalsIgnoreCase("all"))
        {
            sender.sendMessage(Configuration.prefix + "SecretWord Help: ");

            if (Permissions.ADMIN_RELOAD.check(sender))
                sender.sendMessage(Configuration.prefix + "/sw reload - Reload the configuration");

            if (Permissions.ADMIN_RESET.check(sender))
                sender.sendMessage(Configuration.prefix + "/sw reset [player] - Reset a player's secret word (Relogin required for player).");

            sender.sendMessage(Configuration.prefix + "Lack of commands? I thought so too, so suggest them on the BukkitDev page!!");

            // remember, all your base are belong to us.
        }
        else if (arg1.equalsIgnoreCase("reload"))
        {
            if (!Permissions.ADMIN_RELOAD.check(sender))
            {
                sender.sendMessage(Configuration.prefix + ChatColor.RED + "You do not have permission!");
                return true;
            }

            Configuration.reloadConfiguration();

            sender.sendMessage(Configuration.prefix + ChatColor.GREEN + "Configuration reload should be successful!");
            return true;
        }
        else if (arg1.equalsIgnoreCase("reset"))
        {
            if (!Permissions.ADMIN_RESET.check(sender))
            {
                sender.sendMessage(Configuration.prefix + ChatColor.RED + "You do not have permission!");
                return true;
            }

            if (arg2.equalsIgnoreCase("your"))
            {
                sender.sendMessage(Configuration.prefix + ChatColor.RED + "You need a player argument like so:");
                sender.sendMessage(Configuration.prefix + ChatColor.RED + "/sw reset [name]");
                return true;
            }

            // reset data

            SecretPlayer data = dbcore.secplayers.get(arg2);

            if (data != null)
            {
                data.resetData();
                sender.sendMessage(Configuration.prefix + ChatColor.GREEN + "You've reset " + arg2 + "'s secret word successfully!");

                if (Configuration.notifyPlayerOfReset)
                    sender.sendMessage(Configuration.prefix + ChatColor.GREEN + "The player was notified of the reset.");
            }
            else
            {
                // alternative way of resetting!
                File alt = new File(new File(getDataFolder(), "players"), arg2);
                if (alt.exists())
                {
                    alt.delete();
                    sender.sendMessage(Configuration.prefix + ChatColor.GREEN + "You've reset " + arg2 + "'s secret word successfully!");
                    sender.sendMessage(Configuration.prefix + ChatColor.GREEN + "When they log back in, they will be told to make a new secret word.");
                }
                else
                {
                    sender.sendMessage(Configuration.prefix + ChatColor.RED + "Cannot find player!");
                }
            }

        }

        return true;
    }

    public void returnMotd(CommandSender sender)
    {
        sender.sendMessage(Configuration.prefix + "SecretWord " + Configuration.version + " developed by SuperSpyTX");
        sender.sendMessage(Configuration.prefix + "Designed for the next level of authentication.");
        sender.sendMessage(Configuration.prefix + "Keep your word safe! If you need a reset, ask your admin.");
    }

    public DatabaseCore getDB()
    {
        return dbcore;
    }

}
