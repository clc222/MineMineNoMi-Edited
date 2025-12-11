/*    */ package xyz.pixelatedw.mineminenomi.datagen;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import java.util.function.Consumer;
/*    */ import net.minecraft.advancements.Advancement;
/*    */ import net.minecraft.data.AdvancementProvider;
/*    */ import net.minecraft.data.DataGenerator;
/*    */ import net.minecraftforge.common.data.ExistingFileHelper;
/*    */ import xyz.pixelatedw.mineminenomi.datagen.advancements.AdventureAdvancements;
/*    */ import xyz.pixelatedw.mineminenomi.datagen.advancements.ChallengeAdvancements;
/*    */ 
/*    */ 
/*    */ public class ModAdvancementsDataGen
/*    */   extends AdvancementProvider
/*    */ {
/*    */   public ModAdvancementsDataGen(DataGenerator generator, ExistingFileHelper fileHelper) {
/* 17 */     super(generator, fileHelper);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper fileHelper) {
/* 22 */     ImmutableList immutableList = ImmutableList.of(new ChallengeAdvancements(fileHelper), new AdventureAdvancements(fileHelper));
/*    */ 
/*    */ 
/*    */     
/* 26 */     for (Consumer<Consumer<Advancement>> consumer1 : (Iterable<Consumer<Consumer<Advancement>>>)immutableList)
/* 27 */       consumer1.accept(consumer); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\ModAdvancementsDataGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */