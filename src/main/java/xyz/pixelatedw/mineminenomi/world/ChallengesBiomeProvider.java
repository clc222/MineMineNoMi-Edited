/*    */ package xyz.pixelatedw.mineminenomi.world;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.List;
/*    */ import java.util.function.BiFunction;
/*    */ import net.minecraft.util.registry.Registry;
/*    */ import net.minecraft.util.registry.RegistryLookupCodec;
/*    */ import net.minecraft.world.biome.Biome;
/*    */ import net.minecraft.world.biome.BiomeRegistry;
/*    */ import net.minecraft.world.biome.provider.BiomeProvider;
/*    */ 
/*    */ public class ChallengesBiomeProvider extends BiomeProvider {
/* 17 */   private static final List<Biome> BIOME_KEYS = Lists.newArrayList((Object[])new Biome[] { BiomeRegistry.field_244201_b });
/*    */   static {
/* 19 */     CODEC = RecordCodecBuilder.create(builder -> builder.group((App)Codec.LONG.fieldOf("seed").stable().forGetter(()), (App)RegistryLookupCodec.func_244331_a(Registry.field_239720_u_).forGetter(())).apply((Applicative)builder, builder.stable(ChallengesBiomeProvider::new)));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static final Codec<ChallengesBiomeProvider> CODEC;
/*    */ 
/*    */ 
/*    */   
/*    */   private final long seed;
/*    */ 
/*    */   
/*    */   private Registry<Biome> lookupRegistry;
/*    */ 
/*    */ 
/*    */   
/*    */   public ChallengesBiomeProvider(long seed, Registry<Biome> lookupRegistry) {
/* 37 */     super(BIOME_KEYS);
/* 38 */     this.seed = seed;
/* 39 */     this.lookupRegistry = lookupRegistry;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Biome func_225526_b_(int pX, int pY, int pZ) {
/* 45 */     return BiomeRegistry.field_244201_b;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected Codec<? extends BiomeProvider> func_230319_a_() {
/* 51 */     return (Codec)CODEC;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public BiomeProvider func_230320_a_(long seed) {
/* 57 */     return new ChallengesBiomeProvider(seed, this.lookupRegistry);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\ChallengesBiomeProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */