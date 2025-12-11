/*    */ package xyz.pixelatedw.mineminenomi.api.charactercreator;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ 
/*    */ public interface ICharacterCreatorEntry
/*    */ {
/*  8 */   public static final ICharacterCreatorEntry RANDOM = new ICharacterCreatorEntry()
/*    */     {
/*    */       public CharacterCreatorSelectionMap.SelectionInfo getInfo() {
/* 11 */         return new CharacterCreatorSelectionMap.SelectionInfo(ModResources.RANDOM);
/*    */       }
/*    */ 
/*    */       
/*    */       public ResourceLocation getRegistryName() {
/* 16 */         return new ResourceLocation("mineminenomi", "random");
/*    */       }
/*    */ 
/*    */       
/*    */       public boolean isInBook() {
/* 21 */         return true;
/*    */       }
/*    */ 
/*    */       
/*    */       public int getBookOrder() {
/* 26 */         return 0;
/*    */       }
/*    */     };
/*    */   
/*    */   CharacterCreatorSelectionMap.SelectionInfo getInfo();
/*    */   
/*    */   ResourceLocation getRegistryName();
/*    */   
/*    */   boolean isInBook();
/*    */   
/*    */   int getBookOrder();
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\charactercreator\ICharacterCreatorEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */