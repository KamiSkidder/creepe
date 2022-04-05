//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

// 
// Decompiled by Procyon v0.5.36
// 

package me.alpha432.oyvey.mixin.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.client.gui.GuiButton;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.GuiScreen;

@Mixin({ GuiMainMenu.class })
class GuiMainMenu2 extends GuiScreen
{
    @Inject(method = { "initGui" }, at = { @At("RETURN") })
    private void GuiMainMenu(final CallbackInfo callbackInfo) {
        this.buttonList.add(new GuiButton(500, this.width / 2 + 104, this.height / 4 + 48 + 48, 98, 20, "login"));
    }
    
    @Inject(method = { "actionPerformed" }, at = { @At("HEAD") })
    private void actionPerformed(final GuiButton button, final CallbackInfo ci) {
        if (button.id == 500) {}
    }
}
