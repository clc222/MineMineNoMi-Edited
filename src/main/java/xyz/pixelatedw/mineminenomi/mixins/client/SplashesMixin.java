/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.util.Splashes;
/*    */ import net.minecraft.resources.IResource;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({Splashes.class})
/*    */ public class SplashesMixin
/*    */ {
/* 27 */   private static final ResourceLocation MMNM_SPLASHES = new ResourceLocation("mineminenomi", "texts/splashes.txt");
/* 28 */   private static final Random RANDOM = new Random();
/*    */   
/*    */   @Inject(method = {"getSplash"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void getSplash(CallbackInfoReturnable<String> cir) {
/* 32 */     if (!ClientConfig.INSTANCE.isModSplashTextEnabled()) {
/*    */       return;
/*    */     }
/* 35 */     List<String> splashes = new ArrayList<>();
/* 36 */     try(IResource iresource = Minecraft.func_71410_x().func_195551_G().func_199002_a(MMNM_SPLASHES); 
/* 37 */         BufferedReader reader = new BufferedReader(new InputStreamReader(iresource.func_199027_b(), StandardCharsets.UTF_8))) {
/* 38 */       reader.lines().map(String::trim).filter(splash -> (splash.hashCode() != 125780783))
/*    */         
/* 40 */         .forEach(splashes::add);
/*    */     }
/* 42 */     catch (IOException iOException) {}
/*    */ 
/*    */ 
/*    */     
/* 46 */     if (RANDOM.nextInt(3) == 0)
/* 47 */       cir.setReturnValue(splashes.get(RANDOM.nextInt(splashes.size()))); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\client\SplashesMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */