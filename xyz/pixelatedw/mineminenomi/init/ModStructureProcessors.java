/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.registry.Registry;
/*    */ import net.minecraft.world.gen.feature.template.IStructureProcessorType;
/*    */ import net.minecraftforge.eventbus.api.IEventBus;
/*    */ import xyz.pixelatedw.mineminenomi.world.RandomizeSkyBlocksProcessor;
/*    */ import xyz.pixelatedw.mineminenomi.world.ReplaceWaterloggedStructureProcessor;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModStructureProcessors
/*    */ {
/* 16 */   public static final IStructureProcessorType<ReplaceWaterloggedStructureProcessor> WATERLOGGED_REPLACE = register("waterlogged_replace", ReplaceWaterloggedStructureProcessor.CODEC);
/* 17 */   public static final IStructureProcessorType<RandomizeSkyBlocksProcessor> SKYBLOCK_RANDOMIZER = register("skyblock_randomizer", RandomizeSkyBlocksProcessor.CODEC);
/*    */ 
/*    */   
/*    */   private static <P extends net.minecraft.world.gen.feature.template.StructureProcessor> IStructureProcessorType<P> register(String id, Codec<P> codec) {
/* 21 */     return (IStructureProcessorType<P>)Registry.func_218322_a(Registry.field_218364_E, new ResourceLocation("mineminenomi", id), () -> codec);
/*    */   }
/*    */   
/*    */   public static void register(IEventBus eventBus) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModStructureProcessors.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */