/*    */ package xyz.pixelatedw.mineminenomi.api.charactercreator;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraftforge.registries.ForgeRegistryEntry;
/*    */ 
/*    */ public class RaceId extends ForgeRegistryEntry<RaceId> implements ICharacterCreatorEntry {
/*    */   private final CharacterCreatorSelectionMap.SelectionInfo selectionMap;
/*    */   private final boolean inBook;
/* 10 */   private List<String> subRaces = new ArrayList<>();
/*    */   private final int order;
/*    */   
/*    */   public RaceId(CharacterCreatorSelectionMap.SelectionInfo info, boolean inBook) {
/* 14 */     this(info, inBook, -1);
/*    */   }
/*    */   
/*    */   public RaceId(CharacterCreatorSelectionMap.SelectionInfo info, boolean inBook, int order) {
/* 18 */     this.selectionMap = info;
/* 19 */     this.inBook = inBook;
/* 20 */     this.order = order;
/*    */   }
/*    */ 
/*    */   
/*    */   public CharacterCreatorSelectionMap.SelectionInfo getInfo() {
/* 25 */     return this.selectionMap;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isInBook() {
/* 30 */     return this.inBook;
/*    */   }
/*    */   
/*    */   public List<String> getSubRaces() {
/* 34 */     return this.subRaces;
/*    */   }
/*    */   
/*    */   public void setSubRaces(List<String> subRaces) {
/* 38 */     this.subRaces = subRaces;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBookOrder() {
/* 43 */     return this.order;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\charactercreator\RaceId.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */