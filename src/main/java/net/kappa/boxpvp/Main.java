package net.kappa.boxpvp;

import lib.fastboard.FastManager;
import lib.ziggurat.TabAdapter;
import lib.ziggurat.tab.Ziggurat;
import net.kappa.boxpvp.commands.admin.ClaimCommand;
import net.kappa.boxpvp.commands.admin.GamemodeCommand;
import net.kappa.boxpvp.commands.admin.MineCommand;
import net.kappa.boxpvp.commands.admin.RemoveClaimCommand;
import net.kappa.boxpvp.commands.others.MsgCommand;
import net.kappa.boxpvp.commands.social.DiscordCommand;
import net.kappa.boxpvp.commands.social.StoreCommand;
import net.kappa.boxpvp.commands.social.TeamSpeakCommand;
import net.kappa.boxpvp.commands.social.TwitterCommand;
import net.kappa.boxpvp.events.EventProvider;
import net.kappa.boxpvp.files.FileProvider;
import net.kappa.boxpvp.listeners.ListenerProvider;
import net.kappa.boxpvp.managers.ManagerProvider;
import net.kappa.boxpvp.tasks.TaskProvider;
import net.kappa.boxpvp.utils.StatusUtil;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.util.Objects;

public class Main extends JavaPlugin {
    //Instance & Protocol
    public static Main plugin;
    public static int pseudoProtocol;
    //Scheduler
    public static BukkitScheduler scheduler;
    //Plugin Manager
    public static PluginManager pluginManager;
    //File system
    public static File file;
    //Providers
    private ManagerProvider managerProvider;
    public TaskProvider taskProvider;
    //Impl
    private Ziggurat tab;
    private FastManager scoreboard;

    @Override
    public void onEnable() {
        // Plugin startup logic
        final String a = getServer().getClass().getPackage().getName();
        final String version = a.substring(a.lastIndexOf('.') + 1);

        pseudoProtocol = Integer.parseInt(version.split("_")[1]);
        plugin = this;
        scheduler = this.getServer().getScheduler();
        pluginManager = this.getServer().getPluginManager();
        file = this.getDataFolder();

        if (pluginManager.isPluginEnabled("PlaceholderAPI"))
            StatusUtil.setPlaceholder(true);

        this.setupManagers();
        this.setupFiles();
        this.setupEvents();
        this.setupTasks();
        this.setupListeners();
        this.setupCommands();
        this.setupImplements();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.tab.disable();
        this.scoreboard.disable();

        this.taskProvider.disable();
        this.managerProvider.disable();
    }

    private void setupFiles() {
        new FileProvider();
    }

    private void setupManagers() {
        this.managerProvider = new ManagerProvider();
    }

    private void setupTasks() {
        this.taskProvider = new TaskProvider();
    }

    private void setupListeners() {
        new ListenerProvider();
    }

    private void setupEvents() {
        new EventProvider();
    }

    private void setupCommands() {
        // - Player Commands
        Objects.requireNonNull(this.getCommand("message")).setExecutor(new MsgCommand());
        // - Social Player Commands
        Objects.requireNonNull(this.getCommand("discord")).setExecutor(new DiscordCommand());
        Objects.requireNonNull(this.getCommand("store")).setExecutor(new StoreCommand());
        Objects.requireNonNull(this.getCommand("teamspeak")).setExecutor(new TeamSpeakCommand());
        Objects.requireNonNull(this.getCommand("twitter")).setExecutor(new TwitterCommand());
        // - Player Admin Commands
        Objects.requireNonNull(this.getCommand("claim")).setExecutor(new ClaimCommand());
        Objects.requireNonNull(this.getCommand("claimremove")).setExecutor(new RemoveClaimCommand());
        Objects.requireNonNull(this.getCommand("mine")).setExecutor(new MineCommand());
        Objects.requireNonNull(this.getCommand("gamemode")).setExecutor(new GamemodeCommand());
    }

    private void setupImplements() {
        this.tab = new Ziggurat(this, new TabAdapter());
        this.scoreboard = new FastManager();
    }
}
