/*    */ package xyz.pixelatedw.mineminenomi.api.effects;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class DamageOverTimeEffect
/*    */   extends ModEffect
/*    */ {
/*    */   private final DamageSource source;
/*    */   private final float baseDamage;
/*    */   private final int freq;
/*    */   private Function<Integer, Float> damageFunction;
/*    */   
/*    */   public DamageOverTimeEffect() {
/* 18 */     this(DamageSource.field_76376_m, 2.0F, 20);
/*    */   }
/*    */   
/*    */   public DamageOverTimeEffect(DamageSource source, float baseDamage, int freq) {
/* 22 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/* 23 */     this.source = source;
/* 24 */     this.baseDamage = baseDamage;
/* 25 */     this.freq = freq;
/* 26 */     this.damageFunction = (amp -> Float.valueOf(this.baseDamage * amp.intValue()));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier) {
/* 31 */     return (duration % this.freq == 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_76394_a(LivingEntity entity, int amplifier) {
/* 36 */     float damage = Math.max(this.baseDamage, ((Float)this.damageFunction.apply(Integer.valueOf(amplifier))).floatValue());
/*    */     
/* 38 */     entity.func_70097_a(this.source, damage);
/*    */   }
/*    */   
/*    */   public float getBaseDamage() {
/* 42 */     return this.baseDamage;
/*    */   }
/*    */   
/*    */   public Function<Integer, Float> getDamageFunction() {
/* 46 */     return this.damageFunction;
/*    */   }
/*    */   
/*    */   public void setDamageFunction(Function<Integer, Float> func) {
/* 50 */     this.damageFunction = func;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRemoveable() {
/* 55 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\effects\DamageOverTimeEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */