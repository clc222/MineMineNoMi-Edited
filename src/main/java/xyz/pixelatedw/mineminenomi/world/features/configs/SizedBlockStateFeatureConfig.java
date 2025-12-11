/*    */ package xyz.pixelatedw.mineminenomi.world.features.configs;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import net.minecraft.block.BlockState;
/*    */ 
/*    */ public class SizedBlockStateFeatureConfig implements IFeatureConfig {
/*    */   static {
/*  9 */     CODEC = RecordCodecBuilder.create(codec -> codec.group((App)BlockState.field_235877_b_.fieldOf("state").forGetter(()), (App)Codec.intRange(0, 10).orElse(Integer.valueOf(2)).fieldOf("size").forGetter(())).apply((Applicative)codec, SizedBlockStateFeatureConfig::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<SizedBlockStateFeatureConfig> CODEC;
/*    */   
/*    */   public final BlockState state;
/*    */   
/*    */   public final int size;
/*    */   
/*    */   public SizedBlockStateFeatureConfig(BlockState state, int size) {
/* 20 */     this.state = state;
/* 21 */     this.size = size;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\world\features\configs\SizedBlockStateFeatureConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */