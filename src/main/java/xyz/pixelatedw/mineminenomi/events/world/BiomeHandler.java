/*    */ package xyz.pixelatedw.mineminenomi.events.world;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.api.math.EasingFunctionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBiomes;
/*    */ 
/*    */ public class BiomeHandler {
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public static class Client {
/* 19 */     private static float rainThreshold = 0.0F;
/* 20 */     private static long firstTick = 0L;
/*    */     
/*    */     public static float getDrumSnowLevel(Minecraft minecraft, float currentRainLevel, ResourceLocation biomeId) {
/* 23 */       ClientPlayerEntity player = minecraft.field_71439_g;
/* 24 */       IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*    */       
/* 26 */       if (biomeId.equals(ModBiomes.DRUM.getId())) {
/* 27 */         if (props.hasDevilFruit(ModAbilities.MERA_MERA_NO_MI)) {
/* 28 */           return decreaseSnow(player, currentRainLevel, 0.0F);
/*    */         }
/*    */         
/* 31 */         return increaseSnow(player, currentRainLevel, 1.0F);
/*    */       } 
/* 33 */       return decreaseSnow(player, currentRainLevel, currentRainLevel);
/*    */     }
/*    */ 
/*    */     
/*    */     private static float increaseSnow(ClientPlayerEntity player, float currentRainLevel, float def) {
/* 38 */       if (rainThreshold < 0.95F) {
/* 39 */         if (firstTick == 0L) {
/* 40 */           firstTick = player.field_70173_aa;
/*    */         }
/*    */         
/* 43 */         rainThreshold = EasingFunctionHelper.easeInOutQuad(Float.valueOf((float)(player.field_70173_aa - firstTick) % 100.0F / 100.0F));
/* 44 */         float newRainLevel = MathHelper.func_76131_a(currentRainLevel + rainThreshold * 0.7F, 0.0F, 1.0F);
/*    */         
/* 46 */         return newRainLevel;
/*    */       } 
/* 48 */       firstTick = 0L;
/* 49 */       return def;
/*    */     }
/*    */ 
/*    */     
/*    */     private static float decreaseSnow(ClientPlayerEntity player, float currentRainLevel, float def) {
/* 54 */       if (rainThreshold > 0.0D) {
/* 55 */         if (firstTick == 0L) {
/* 56 */           firstTick = player.field_70173_aa;
/*    */         }
/*    */         
/* 59 */         float level = EasingFunctionHelper.easeInOutQuad(Float.valueOf((float)(player.field_70173_aa - firstTick) % 100.0F / 100.0F));
/* 60 */         rainThreshold = MathHelper.func_76131_a(rainThreshold + currentRainLevel - level, 0.0F, 1.0F);
/*    */         
/* 62 */         return rainThreshold;
/*    */       } 
/* 64 */       firstTick = 0L;
/* 65 */       return def;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\world\BiomeHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */