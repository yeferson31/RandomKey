package com.yeferson_31_.randomkey;
import org.bukkit.OfflinePlayer;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class placeholder extends PlaceholderExpansion {

    private final RandomKey plugin;

    public placeholder(RandomKey plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getAuthor() {
        return "Yeferson";
    }

    @Override
    public String getIdentifier() {
        return "randomkey";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("placeholder1")){
            return plugin.getConfig().getString("placeholders.placeholder1", "default1");
        }

        if(params.equalsIgnoreCase("time")) {
            return plugin.getTiempoRestante();
        }

        return null; // Placeholder is unknown by the Expansion
    }
}