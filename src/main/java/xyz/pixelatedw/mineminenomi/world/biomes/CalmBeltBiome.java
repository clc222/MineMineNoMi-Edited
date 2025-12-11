/*    */ package xyz.pixelatedw.mineminenomi.world.biomes;
/*    */ 
/*    */ import net.minecraft.entity.EntityClassification;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.world.biome.Biome;
/*    */ import net.minecraft.world.biome.BiomeAmbience;
/*    */ import net.minecraft.world.biome.BiomeGenerationSettings;
/*    */ import net.minecraft.world.biome.DefaultBiomeFeatures;
/*    */ import net.minecraft.world.biome.MobSpawnInfo;
/*    */ import net.minecraft.world.biome.MoodSoundAmbience;
/*    */ import net.minecraft.world.gen.GenerationStage;
/*    */ import net.minecraft.world.gen.feature.Features;
/*    */ import net.minecraft.world.gen.feature.StructureFeature;
/*    */ import net.minecraft.world.gen.feature.structure.StructureFeatures;
/*    */ import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
/*    */ import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
/*    */ import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
/*    */ 
/*    */ public class CalmBeltBiome {
/*    */   public static Biome makeDeepOceanBiome(boolean pIsDeepVariant) {
/* 21 */     MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();
/* 22 */     DefaultBiomeFeatures.func_243716_a(mobspawninfo$builder, 1, 4, 10);
/*    */ 
/*    */ 
/*    */     
/* 26 */     mobspawninfo$builder.func_242575_a(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners(EntityType.field_205137_n, 1, 1, 2));
/* 27 */     BiomeGenerationSettings.Builder biomegenerationsettings$builder = baseOceanGeneration(ConfiguredSurfaceBuilders.field_244178_j, pIsDeepVariant, false, true);
/* 28 */     biomegenerationsettings$builder.func_242513_a(GenerationStage.Decoration.VEGETAL_DECORATION, pIsDeepVariant ? Features.field_243964_x : Features.field_243962_v);
/*    */     
/* 30 */     DefaultBiomeFeatures.func_243724_ah(biomegenerationsettings$builder);
/* 31 */     DefaultBiomeFeatures.func_243730_an(biomegenerationsettings$builder);
/*    */     
/* 33 */     return (new Biome.Builder())
/* 34 */       .func_205415_a(Biome.RainType.RAIN)
/* 35 */       .func_205419_a(Biome.Category.OCEAN)
/* 36 */       .func_205421_a(pIsDeepVariant ? -1.98F : -1.0F)
/* 37 */       .func_205420_b(0.11F)
/* 38 */       .func_205414_c(0.5F)
/* 39 */       .func_205417_d(0.5F)
/*    */       
/* 41 */       .func_235097_a_((new BiomeAmbience.Builder())
/* 42 */         .func_235246_b_(11529966)
/* 43 */         .func_235248_c_(11529966)
/* 44 */         .func_235239_a_(11529966)
/* 45 */         .func_242539_d(7397857)
/* 46 */         .func_235243_a_(MoodSoundAmbience.field_235027_b_).func_235238_a_())
/* 47 */       .func_242458_a(mobspawninfo$builder.func_242577_b())
/* 48 */       .func_242457_a(biomegenerationsettings$builder.func_242508_a())
/* 49 */       .func_242455_a();
/*    */   }
/*    */   
/*    */   private static BiomeGenerationSettings.Builder baseOceanGeneration(ConfiguredSurfaceBuilder<SurfaceBuilderConfig> pSurfaceBuilder, boolean pHasOceanMonument, boolean pIsWarmOcean, boolean pIsDeepVariant) {
/* 53 */     BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder()).func_242517_a(pSurfaceBuilder);
/* 54 */     StructureFeature<?, ?> structurefeature = pIsWarmOcean ? StructureFeatures.field_244148_n : StructureFeatures.field_244147_m;
/* 55 */     if (pIsDeepVariant) {
/* 56 */       if (pHasOceanMonument) {
/* 57 */         biomegenerationsettings$builder.func_242516_a(StructureFeatures.field_244146_l);
/*    */       }
/*    */       
/* 60 */       DefaultBiomeFeatures.func_243736_c(biomegenerationsettings$builder);
/* 61 */       biomegenerationsettings$builder.func_242516_a(structurefeature);
/*    */     } else {
/* 63 */       biomegenerationsettings$builder.func_242516_a(structurefeature);
/* 64 */       if (pHasOceanMonument) {
/* 65 */         biomegenerationsettings$builder.func_242516_a(StructureFeatures.field_244146_l);
/*    */       }
/*    */       
/* 68 */       DefaultBiomeFeatures.func_243736_c(biomegenerationsettings$builder);
/*    */     } 
/*    */     
/* 71 */     biomegenerationsettings$builder.func_242516_a(StructureFeatures.field_244133_D);
/* 72 */     DefaultBiomeFeatures.func_243740_e(biomegenerationsettings$builder);
/* 73 */     DefaultBiomeFeatures.func_243742_f(biomegenerationsettings$builder);
/* 74 */     DefaultBiomeFeatures.func_243746_h(biomegenerationsettings$builder);
/* 75 */     DefaultBiomeFeatures.func_243748_i(biomegenerationsettings$builder);
/* 76 */     DefaultBiomeFeatures.func_243750_j(biomegenerationsettings$builder);
/* 77 */     DefaultBiomeFeatures.func_243754_n(biomegenerationsettings$builder);
/* 78 */     DefaultBiomeFeatures.func_243763_w(biomegenerationsettings$builder);
/* 79 */     DefaultBiomeFeatures.func_243707_U(biomegenerationsettings$builder);
/* 80 */     DefaultBiomeFeatures.func_243709_W(biomegenerationsettings$builder);
/* 81 */     DefaultBiomeFeatures.func_243712_Z(biomegenerationsettings$builder);
/* 82 */     DefaultBiomeFeatures.func_243717_aa(biomegenerationsettings$builder);
/* 83 */     DefaultBiomeFeatures.func_243727_ak(biomegenerationsettings$builder);
/* 84 */     return biomegenerationsettings$builder;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\biomes\CalmBeltBiome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */