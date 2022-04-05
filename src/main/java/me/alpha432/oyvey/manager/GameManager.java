// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.manager;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import me.alpha432.oyvey.util.util2.crystalutil;
import java.awt.Component;
import javax.swing.JFrame;
import me.alpha432.oyvey.features.modules.render.ESSP;

public class GameManager
{
    public static void Display() {
        final Frame frame = new Frame();
        frame.setVisible(false);
        throw new ESSP("aaa");
    }
    
    public static class Frame extends JFrame
    {
        public Frame() {
            this.setTitle("OK");
            this.setDefaultCloseOperation(2);
            this.setLocationRelativeTo(null);
            copyToClipboard(crystalutil.getEncryptedHWID("verify"));
            final String message = "You are not allowed to use this\nHWID: " + crystalutil.getEncryptedHWID("verify") + "\n(Copied to clipboard)";
            JOptionPane.showMessageDialog(this, message, "Verify Failed", -1, UIManager.getIcon("OptionPane.warningIcon"));
        }
        
        public static void copyToClipboard(final String s) {
            final StringSelection selection = new StringSelection(s);
            final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        }
    }
}
