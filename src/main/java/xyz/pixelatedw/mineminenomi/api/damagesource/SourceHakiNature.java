/*    */ package xyz.pixelatedw.mineminenomi.api.damagesource;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ 
/*    */ 
/*    */ public enum SourceHakiNature
/*    */ {
/* 10 */   UNKNOWN(null),
/* 11 */   HARDENING(ModResources.SOURCE_HAKI_HARDENING),
/* 12 */   IMBUING(ModResources.SOURCE_HAKI_IMBUING),
/* 13 */   SPECIAL(ModResources.SOURCE_HAKI_SPECIAL);
/*    */   
/*    */   @Nullable
/*    */   private final ResourceLocation texture;
/*    */ 
/*    */   
/*    */   SourceHakiNature(ResourceLocation texture) {
/* 20 */     this.texture = texture;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public ResourceLocation getTexture() {
/* 25 */     return this.texture;
/*    */   }
/*    */   
/*    */   public String getUnlocalizedName() {
/* 29 */     return toString().toLowerCase();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\damagesource\SourceHakiNature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */