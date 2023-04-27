package com.yeferson_31_.randomkey;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;


public class RandomKey extends JavaPlugin {

    private BukkitTask tareaInteraccion;
    private long tiempoRestante;

    private final int tiempoEntreInteracciones = 15 * 60 * 20; //15 minutos en ticks

    @Override
    public void onEnable() {

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new placeholder(this).register();
        }
        // Iniciar la tarea programada para la interacción cada 15 minutos
        tareaInteraccion = new BukkitRunnable() {
            @Override
            public void run() {
                // Obtener todos los jugadores del servidor
                Player[] jugadores = Bukkit.getServer().getOnlinePlayers().toArray(new Player[0]);

                // Escoger un jugador aleatorio
                int indiceJugadorAleatorio = (int) (Math.random() * jugadores.length);
                Player jugadorAleatorio = jugadores[indiceJugadorAleatorio];

                ejecutarComando(jugadorAleatorio);
                //PlaceholderAPI

                tiempoRestante = tiempoEntreInteracciones;
            }
        }.runTaskTimer(this, tiempoEntreInteracciones, tiempoEntreInteracciones);

        // Inicializar la variable tiempoRestante
        tiempoRestante = tiempoEntreInteracciones;
    }

    @Override
    public void onDisable() {
        // Cancelar la tarea programada si el plugin se desactiva
        tareaInteraccion.cancel();
    }

    public String getTiempoRestante() {
        long segundosRestantes = tiempoRestante / 20L; // Convertir ticks a segundos
        long minutos = segundosRestantes / 60L;
        long segundos = segundosRestantes % 60L;
        return String.format("%02d:%02d", minutos, segundos); // Formato "mm:ss"
    }


    // Ejecuta un comando en la consola del servidor en nombre de un jugador específico
    private void ejecutarComando(Player jugador) {
        String comando = "crate givekey afk " + jugador.getName();
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), comando);
    }
}
