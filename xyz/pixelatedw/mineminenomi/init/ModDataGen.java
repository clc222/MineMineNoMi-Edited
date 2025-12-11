/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ import net.minecraft.data.BlockTagsProvider;
/*    */ import net.minecraft.data.DataGenerator;
/*    */ import net.minecraft.data.IDataProvider;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
/*    */ import org.apache.logging.log4j.Level;
/*    */ import xyz.pixelatedw.mineminenomi.ModMain;
/*    */ import xyz.pixelatedw.mineminenomi.datagen.ModAdvancementsDataGen;
/*    */ import xyz.pixelatedw.mineminenomi.datagen.ModRecipesDataGen;
/*    */ import xyz.pixelatedw.mineminenomi.datagen.loottables.BlocksLootTablesDataGen;
/*    */ import xyz.pixelatedw.mineminenomi.datagen.loottables.EntitiesLootTablesDataGen;
/*    */ import xyz.pixelatedw.mineminenomi.datagen.loottables.RewardsLootTablesDataGen;
/*    */ import xyz.pixelatedw.mineminenomi.datagen.loottables.StructuresLootTablesDataGen;
/*    */ import xyz.pixelatedw.mineminenomi.datagen.loottables.TraderLootTablesDataGen;
/*    */ import xyz.pixelatedw.mineminenomi.datagen.tags.ModBlockTagsDataGen;
/*    */ import xyz.pixelatedw.mineminenomi.datagen.tags.ModEntityTagsDataGen;
/*    */ import xyz.pixelatedw.mineminenomi.datagen.tags.ModItemTagsDataGen;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
/*    */ public class ModDataGen {
/*    */   @SubscribeEvent
/*    */   public static void onGatherData(GatherDataEvent event) {
/* 26 */     ModMain.LOGGER.log(Level.INFO, "Started Generating JSON files");
/* 27 */     DataGenerator generator = event.getGenerator();
/*    */ 
/*    */     
/* 30 */     generator.func_200390_a((IDataProvider)new BlocksLootTablesDataGen(generator));
/* 31 */     generator.func_200390_a((IDataProvider)new EntitiesLootTablesDataGen(generator));
/* 32 */     generator.func_200390_a((IDataProvider)new TraderLootTablesDataGen(generator));
/* 33 */     generator.func_200390_a((IDataProvider)new DFBoxesLootTablesDataGen(generator));
/* 34 */     generator.func_200390_a((IDataProvider)new StructuresLootTablesDataGen(generator));
/* 35 */     generator.func_200390_a((IDataProvider)new RewardsLootTablesDataGen(generator));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 43 */     generator.func_200390_a((IDataProvider)new ModRecipesDataGen(generator));
/* 44 */     generator.func_200390_a((IDataProvider)new ModAdvancementsDataGen(generator, event.getExistingFileHelper()));
/*    */ 
/*    */     
/* 47 */     ModBlockTagsDataGen blockTagGenerator = new ModBlockTagsDataGen(generator, event.getExistingFileHelper());
/* 48 */     generator.func_200390_a((IDataProvider)blockTagGenerator);
/* 49 */     generator.func_200390_a((IDataProvider)new ModItemTagsDataGen(generator, (BlockTagsProvider)blockTagGenerator, event.getExistingFileHelper()));
/* 50 */     generator.func_200390_a((IDataProvider)new ModEntityTagsDataGen(generator, event.getExistingFileHelper()));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModDataGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */