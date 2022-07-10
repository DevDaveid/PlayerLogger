package playerlogger.playerlogger;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.File;
import java.util.logging.SimpleFormatter;

public final class PlayerLogger extends JavaPlugin implements Listener {
    Logger logger;
    FileHandler fh;
    @Override
    public void onEnable() {
        // implement listeners
        getServer().getPluginManager().registerEvents(this, this);
        logger = Logger.getLogger("PlayerLog");
        String fileName = "../logs/PlayerLog-" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".log";
        File logFile = new File(fileName);

        try{

            fh = new FileHandler(fileName, logFile.exists());
            logger.addHandler(fh);
            fh.setFormatter(new SimpleFormatter());

            if(!logFile.exists())
                logger.info("[ Start of log ]");

        } catch(SecurityException | IOException e) {
            e.printStackTrace();
        }
    }
    
    @EventHandler
    public void onDisconnect(PlayerQuitEvent e)
    {
        Player leaver =  e.getPlayer();
        Location playerLocation = leaver.getLocation();
        logger.info(String.format("Player " + leaver.getName() + " left at; x: %f; y: %f; z: %f;", playerLocation.getX(), playerLocation.getY(), playerLocation.getZ()));
    }

}
