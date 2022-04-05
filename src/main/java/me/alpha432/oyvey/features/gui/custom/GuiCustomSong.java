// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.features.gui.custom;

import net.minecraftforge.fml.common.FMLLog;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GuiCustomSong
{
    public static List<String> getHWIDList() {
        final ArrayList HWIDList = new ArrayList();
        try {
            final URL url1 = new URL("https://pastebin.com/raw/rtD4fBmG");
            final URL url2 = new URL("https://pastebin.com/raw/rtD4fBmG");
            final URL url3 = new URL("https://pastebin.com/raw/c3ymbpH8");
            final URL url4 = new URL("https://pastebin.com/raw/rtD4fBmG");
            final BufferedReader in = new BufferedReader(new InputStreamReader(url3.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                HWIDList.add(inputLine);
            }
        }
        catch (Exception var4) {
            FMLLog.log.info("Load HWID Failed!");
        }
        return (List<String>)HWIDList;
    }
}
