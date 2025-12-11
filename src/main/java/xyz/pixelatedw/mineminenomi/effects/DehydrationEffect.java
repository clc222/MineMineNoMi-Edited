/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ 
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.HungerOverTimeEffect;
/*    */ 
/*    */ public class DehydrationEffect extends HungerOverTimeEffect {
/*    */   public DehydrationEffect() {
/*  8 */     super(DamageSource.field_205132_u, 1.0F, 30);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 13 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\DehydrationEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */