/*    */ package xyz.pixelatedw.mineminenomi.api.charactercreator;
/*    */ 
/*    */ import net.minecraftforge.registries.ForgeRegistryEntry;
/*    */ 
/*    */ public class StyleId extends ForgeRegistryEntry<StyleId> implements ICharacterCreatorEntry {
/*    */   private final CharacterCreatorSelectionMap.SelectionInfo selectionMap;
/*    */   private final boolean inBook;
/*    */   private final int order;
/*    */   
/*    */   public StyleId(CharacterCreatorSelectionMap.SelectionInfo info, boolean inBook) {
/* 11 */     this(info, inBook, -1);
/*    */   }
/*    */   
/*    */   public StyleId(CharacterCreatorSelectionMap.SelectionInfo info, boolean inBook, int order) {
/* 15 */     this.selectionMap = info;
/* 16 */     this.inBook = inBook;
/* 17 */     this.order = order;
/*    */   }
/*    */ 
/*    */   
/*    */   public CharacterCreatorSelectionMap.SelectionInfo getInfo() {
/* 22 */     return this.selectionMap;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isInBook() {
/* 27 */     return this.inBook;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBookOrder() {
/* 32 */     return this.order;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\charactercreator\StyleId.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */