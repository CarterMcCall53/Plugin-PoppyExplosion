package dev.cmccall.poppyexplosionplugin;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Events implements Listener {
    @EventHandler
    public void onPoppyClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
            var block = e.getClickedBlock().getType();
            if (block == Material.POPPY) {
                var numGen = Math.random() * 100;
                int num = (int) numGen;
                if (num == 1) {
                    var loc = e.getClickedBlock().getLocation();
                    e.getPlayer().getWorld().createExplosion(loc, 5f);
                    e.setCancelled(true);
                    var world = e.getPlayer().getWorld();
                    var locX = e.getClickedBlock().getX();
                    var yloc = e.getClickedBlock().getY();
                    var locZ = e.getClickedBlock().getZ();
                    var locY = yloc - 1;
                    for (int y = yloc - 2; y > -65; y--) {
                        if (world.getType(locX, y, locZ) != Material.AIR) {
                            e.getPlayer().getWorld().setType(locX, y, locZ, Material.GRASS_BLOCK);
                            e.getPlayer().getWorld().setType(locX, y+1, locZ, Material.WITHER_ROSE);
                            break;
                        }
                    }
                }
            }
        }
    }
}