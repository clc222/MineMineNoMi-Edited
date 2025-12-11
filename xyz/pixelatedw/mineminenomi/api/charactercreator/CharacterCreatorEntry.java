/*    */ package xyz.pixelatedw.mineminenomi.api.charactercreator;
/*    */ 
/*    */ import com.google.common.base.Joiner;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraftforge.registries.IForgeRegistry;
/*    */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*    */ 
/*    */ public enum CharacterCreatorEntry {
/* 11 */   FACTION(ModRegistries.FACTIONS),
/* 12 */   RACE(ModRegistries.RACES),
/* 13 */   STYLE(ModRegistries.STYLES);
/*    */   
/*    */   CharacterCreatorEntry(IForgeRegistry<? extends ICharacterCreatorEntry> registry) {
/* 16 */     this.registry = registry;
/*    */   }
/*    */   
/*    */   private final IForgeRegistry<? extends ICharacterCreatorEntry> registry;
/*    */   
/*    */   public ITextComponent getLocalizedTitle(ResourceLocation location) {
/* 22 */     return (ITextComponent)new TranslationTextComponent(Joiner.on('.').join(this.registry.getRegistryName().func_110623_a(), location.func_110624_b(), new Object[] { location.func_110623_a() }));
/*    */   }
/*    */   
/*    */   public ICharacterCreatorEntry getInfo(int id) {
/* 26 */     return getEntries()[id];
/*    */   }
/*    */   
/*    */   private ICharacterCreatorEntry[] getEntries() {
/* 30 */     return (ICharacterCreatorEntry[])this.registry.getValues().stream()
/* 31 */       .filter(rec$ -> ((ICharacterCreatorEntry)rec$).isInBook())
/* 32 */       .sorted((e1, e2) -> e1.getBookOrder() - e2.getBookOrder())
/* 33 */       .toArray(x$0 -> new ICharacterCreatorEntry[x$0]);
/*    */   }
/*    */   
/*    */   public int getSize() {
/* 37 */     return (getEntries()).length;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\charactercreator\CharacterCreatorEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */