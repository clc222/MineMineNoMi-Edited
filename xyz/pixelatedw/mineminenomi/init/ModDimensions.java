/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.registry.Registry;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.world.ChallengesBiomeProvider;
/*    */ import xyz.pixelatedw.mineminenomi.world.ChallengesChunkGenerator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber
/*    */ public class ModDimensions
/*    */ {
/*    */   public static void setupDimensions() {
/* 16 */     Registry.func_218322_a(Registry.field_239689_aA_, new ResourceLocation("mineminenomi", "challenges_biome_provider"), ChallengesBiomeProvider.CODEC);
/* 17 */     Registry.func_218322_a(Registry.field_239690_aB_, new ResourceLocation("mineminenomi", "challenges_chunk_generator"), ChallengesChunkGenerator.CODEC);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModDimensions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */