/*    */ package xyz.pixelatedw.mineminenomi.api.damagesource;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ 
/*    */ 
/*    */ public enum SourceElement
/*    */ {
/* 10 */   NONE(null),
/* 11 */   FIRE(ModResources.SOURCE_ELEMENT_FIRE),
/* 12 */   MAGMA(ModResources.SOURCE_ELEMENT_MAGMA),
/* 13 */   ICE(ModResources.SOURCE_ELEMENT_ICE),
/* 14 */   WATER(ModResources.SOURCE_ELEMENT_WATER),
/* 15 */   LIGHT(ModResources.SOURCE_ELEMENT_LIGHT),
/* 16 */   LIGHTNING(ModResources.SOURCE_ELEMENT_LIGHTNING),
/* 17 */   RUBBER(null),
/* 18 */   EXPLOSION(ModResources.SOURCE_ELEMENT_EXPLOSION),
/* 19 */   WAX(ModResources.SOURCE_ELEMENT_WAX),
/* 20 */   POISON(ModResources.SOURCE_ELEMENT_POISON),
/* 21 */   RUST(null),
/* 22 */   SHOCKWAVE(ModResources.SOURCE_ELEMENT_SHOCKWAVE),
/* 23 */   SMOKE(ModResources.SOURCE_ELEMENT_SMOKE),
/* 24 */   METAL(ModResources.SOURCE_ELEMENT_METAL),
/* 25 */   AIR(null),
/* 26 */   SLIME(null),
/* 27 */   GRAVITY(null);
/*    */   
/*    */   @Nullable
/*    */   private final ResourceLocation texture;
/*    */ 
/*    */   
/*    */   SourceElement(ResourceLocation texture) {
/* 34 */     this.texture = texture;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public ResourceLocation getTexture() {
/* 39 */     return this.texture;
/*    */   }
/*    */   
/*    */   public String getUnlocalizedName() {
/* 43 */     return toString().toLowerCase();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\damagesource\SourceElement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */