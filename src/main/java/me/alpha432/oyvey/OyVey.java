// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey;

import org.apache.logging.log4j.LogManager;
import org.lwjgl.opengl.Display;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import me.alpha432.oyvey.manager.SafetyManager;
import me.alpha432.oyvey.manager.TextManager;
import me.alpha432.oyvey.manager.eventManager2;
import me.alpha432.oyvey.manager.EventManager;
import me.alpha432.oyvey.manager.ServerManager;
import me.alpha432.oyvey.manager.ConfigManager;
import me.alpha432.oyvey.manager.FileManager;
import me.alpha432.oyvey.manager.SongManager;
import me.alpha432.oyvey.manager.ReloadManager;
import me.alpha432.oyvey.manager.SpeedManager;
import me.alpha432.oyvey.manager.PositionManager;
import me.alpha432.oyvey.manager.RotationManager;
import me.alpha432.oyvey.manager.PotionManager;
import me.alpha432.oyvey.manager.InventoryManager;
import me.alpha432.oyvey.manager.HoleManager;
import me.alpha432.oyvey.manager.DonatorFont;
import me.alpha432.oyvey.manager.ColorManager;
import me.alpha432.oyvey.manager.PacketManager;
import me.alpha432.oyvey.manager.ModuleManager;
import me.alpha432.oyvey.manager.MenuFont;
import me.alpha432.oyvey.manager.GuiFont;
import me.alpha432.oyvey.manager.FriendManager;
import me.alpha432.oyvey.manager.TextManager2;
import me.alpha432.oyvey.manager.CommandManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = "crepe", name = "Crepe", version = "0.0.4")
public class OyVey
{
    public static final String MODID = "crepe";
    public static final String MODNAME = "Crepe";
    public static final String MODVER = "0.0.4";
    public static final Logger LOGGER;
    public static CommandManager commandManager;
    public static TextManager2 textManager2;
    public static FriendManager friendManager;
    public static GuiFont GUI_FONT_MANAGER;
    public static MenuFont MENU_FONT_MANAGER;
    public static ModuleManager moduleManager;
    public static FriendManager FRIEND_MANAGER;
    public static PacketManager packetManager;
    public static ColorManager colorManager;
    public static DonatorFont DONATOR_FONT_MANAGER;
    public static HoleManager holeManager;
    public static InventoryManager inventoryManager;
    public static PotionManager potionManager;
    public static RotationManager rotationManager;
    public static PositionManager positionManager;
    public static SpeedManager speedManager;
    public static ReloadManager reloadManager;
    public static SongManager SONG_MANAGER;
    public static FileManager fileManager;
    public static ConfigManager configManager;
    public static ServerManager serverManager;
    public static EventManager eventManager;
    public static eventManager2 eventManager2;
    public static TextManager textManager;
    public static SafetyManager safetyManager;
    @Mod.Instance
    public static OyVey INSTANCE;
    private static boolean unloaded;
    private static Object SongManager;
    
    public static void load() {
        OyVey.LOGGER.info("");
        OyVey.unloaded = false;
        RPC.startRPC();
        if (OyVey.reloadManager != null) {
            OyVey.reloadManager.unload();
            OyVey.reloadManager = null;
        }
        OyVey.SongManager = new SongManager();
        OyVey.textManager = new TextManager();
        OyVey.commandManager = new CommandManager();
        OyVey.SONG_MANAGER = new SongManager();
        OyVey.friendManager = new FriendManager();
        OyVey.moduleManager = new ModuleManager();
        OyVey.rotationManager = new RotationManager();
        OyVey.packetManager = new PacketManager();
        OyVey.eventManager = new EventManager();
        OyVey.eventManager2 = new eventManager2();
        OyVey.speedManager = new SpeedManager();
        OyVey.GUI_FONT_MANAGER = new GuiFont();
        OyVey.MENU_FONT_MANAGER = new MenuFont();
        OyVey.potionManager = new PotionManager();
        OyVey.inventoryManager = new InventoryManager();
        OyVey.DONATOR_FONT_MANAGER = new DonatorFont();
        OyVey.serverManager = new ServerManager();
        OyVey.FRIEND_MANAGER = new FriendManager();
        OyVey.fileManager = new FileManager();
        OyVey.colorManager = new ColorManager();
        OyVey.positionManager = new PositionManager();
        OyVey.configManager = new ConfigManager();
        OyVey.holeManager = new HoleManager();
        OyVey.safetyManager = new SafetyManager();
        OyVey.LOGGER.info("Managers loaded.");
        OyVey.moduleManager.init();
        OyVey.LOGGER.info("Modules loaded.");
        OyVey.configManager.init();
        OyVey.eventManager.init();
        OyVey.LOGGER.info("EventManager loaded.");
        RPC.startRPC();
        OyVey.textManager.init(true);
        OyVey.moduleManager.onLoad();
        OyVey.LOGGER.info("");
    }
    
    public static void unload(final boolean unload) {
        OyVey.LOGGER.info("");
        if (unload) {
            (OyVey.reloadManager = new ReloadManager()).init((OyVey.commandManager != null) ? OyVey.commandManager.getPrefix() : ".");
        }
        onUnload();
        OyVey.eventManager = null;
        OyVey.eventManager2 = null;
        OyVey.holeManager = null;
        OyVey.moduleManager = null;
        OyVey.serverManager = null;
        OyVey.colorManager = null;
        OyVey.speedManager = null;
        OyVey.rotationManager = null;
        OyVey.positionManager = null;
        OyVey.commandManager = null;
        OyVey.configManager = null;
        OyVey.textManager2 = null;
        OyVey.fileManager = null;
        OyVey.textManager = null;
        OyVey.friendManager = null;
        OyVey.potionManager = null;
        OyVey.inventoryManager = null;
        OyVey.safetyManager = null;
        OyVey.LOGGER.info("");
    }
    
    public static void reload() {
        unload(false);
        load();
    }
    
    public static void onUnload() {
        if (!OyVey.unloaded) {
            OyVey.eventManager.onUnload();
            OyVey.moduleManager.onUnload();
            OyVey.configManager.saveConfig(OyVey.configManager.config.replaceFirst("crepe/", ""));
            OyVey.moduleManager.onUnloadPost();
            OyVey.unloaded = true;
        }
    }
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        OyVey.LOGGER.info("I am gona gas you kike - Alpha432");
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        Display.setTitle("Crepe / 0.0.4");
        load();
    }
    
    static {
        LOGGER = LogManager.getLogger("crepe");
        OyVey.unloaded = false;
    }
}
