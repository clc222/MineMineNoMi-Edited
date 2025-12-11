/*    */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class AbilityComponentKey<C extends AbilityComponent<?>>
/*    */ {
/*    */   private final ResourceLocation id;
/*    */   
/*    */   public AbilityComponentKey(ResourceLocation id) {
/* 10 */     this.id = id;
/*    */   }
/*    */   
/*    */   public static <C extends AbilityComponent<?>> AbilityComponentKey<C> key(ResourceLocation id) {
/* 14 */     return new AbilityComponentKey<>(id);
/*    */   }
/*    */   
/*    */   public ResourceLocation getId() {
/* 18 */     return this.id;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\AbilityComponentKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */