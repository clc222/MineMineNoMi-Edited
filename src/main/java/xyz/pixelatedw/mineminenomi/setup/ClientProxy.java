/*    */ package xyz.pixelatedw.mineminenomi.setup;
/*    */ import java.util.function.BiFunction;
/*    */ import net.minecraftforge.fml.ExtensionPoint;
/*    */ import net.minecraftforge.fml.ModLoadingContext;
/*    */ import net.minecraftforge.fml.config.ModConfig;
/*    */ import xyz.pixelatedw.mineminenomi.ModMain;
/*    */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*    */ import xyz.pixelatedw.mineminenomi.integrations.clothconfig.ClothConfigIntegration;
/*    */ 
/*    */ public class ClientProxy {
/*    */   public ClientProxy() {
/* 12 */     ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
/*    */     
/* 14 */     if (ModMain.hasClothConfigInstalled())
/* 15 */       ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> ClothConfigIntegration.CONFIG_FACTORY); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\setup\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */