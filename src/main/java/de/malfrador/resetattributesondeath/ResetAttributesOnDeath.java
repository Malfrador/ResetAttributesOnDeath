package de.malfrador.resetattributesondeath;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class ResetAttributesOnDeath extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    private void onDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        for (Attribute attribute : Attribute.values()) {
            if (player.getAttribute(attribute) == null) {
                continue;
            }
            for (AttributeModifier modifier : player.getAttribute(attribute).getModifiers()) {
                if (!(modifier.getKey().getNamespace().equals("minecraft"))) {
                    continue;
                }
                player.getAttribute(attribute).removeModifier(modifier);
            }
        }

    }
}
