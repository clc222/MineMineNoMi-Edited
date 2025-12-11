/*    */ package xyz.pixelatedw.mineminenomi.api.damagesource;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ 
/*    */ public enum SourceType
/*    */ {
/*  9 */   UNKNOWN(null),
/* 10 */   SLASH(ModResources.SOURCE_TYPE_SLASH),
/* 11 */   BLUNT(ModResources.SOURCE_TYPE_BLUNT),
/*    */   
/* 13 */   INDIRECT(null),
/*    */   
/* 15 */   FIST(ModResources.SOURCE_TYPE_FIST),
/*    */   
/* 17 */   PHYSICAL(ModResources.SOURCE_TYPE_PHYSICAL),
/* 18 */   INTERNAL(ModResources.SOURCE_TYPE_INTERNAL),
/* 19 */   PROJECTILE(ModResources.SOURCE_TYPE_PROJECTILE),
/* 20 */   BULLET(ModResources.SOURCE_TYPE_BULLET);
/*    */   
/*    */   @Nullable
/*    */   private final ResourceLocation texture;
/*    */ 
/*    */   
/*    */   SourceType(ResourceLocation texture) {
/* 27 */     this.texture = texture;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public ResourceLocation getTexture() {
/* 32 */     return this.texture;
/*    */   }
/*    */   
/*    */   public String getUnlocalizedName() {
/* 36 */     return toString().toLowerCase();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\damagesource\SourceType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */