/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.BoatEntity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyVariable;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.kairosekicoating.IKairosekiCoating;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.kairosekicoating.KairosekiCoatingCapability;
/*    */ 
/*    */ @Mixin({BoatEntity.class})
/*    */ public class BoatEntityMixin
/*    */ {
/*    */   @ModifyVariable(method = {"setDamage"}, at = @At("HEAD"), ordinal = 0)
/*    */   public float setDamage(float damage) {
/* 17 */     BoatEntity boat = (BoatEntity)this;
/* 18 */     IKairosekiCoating coatingData = (IKairosekiCoating)KairosekiCoatingCapability.get((Entity)boat).orElse(null);
/* 19 */     if (coatingData != null) {
/* 20 */       float modifier = 1.0F - coatingData.getCoatingLevel() / 5.0F;
/* 21 */       modifier = MathHelper.func_76131_a(modifier, 0.5F, 1.0F);
/* 22 */       damage *= modifier;
/*    */     } 
/* 24 */     return damage;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\BoatEntityMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */