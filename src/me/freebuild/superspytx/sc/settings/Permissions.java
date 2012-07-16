package me.freebuild.superspytx.sc.settings;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum Permissions
{

    LOGIN, // user required to abide by SecretWord?
    HALFHOUR, // user is required to relogin after half hour.
    SHOWJOIN, // user's join message will show, regardless if it needs to be hidden by config.
    
    // Admin
    ADMIN_RESET,
    ADMIN_RELOAD;

    String perm;

    Permissions()
    {
        this.perm = "secretword." + this.name().toLowerCase().replace("_", ".");
    }

    public boolean check(CommandSender cs)
    {
        return cs.hasPermission(this.perm);
    }

    public boolean check(Player pl)
    {
        return pl.hasPermission(this.perm);
    }

}
