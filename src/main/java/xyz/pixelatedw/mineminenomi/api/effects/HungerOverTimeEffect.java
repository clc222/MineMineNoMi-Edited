/*    */ package xyz.pixelatedw.mineminenomi.api.effects;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.EffectType;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HungerOverTimeEffect
/*    */   extends ModEffect
/*    */ {
/*    */   private final DamageSource source;
/*    */   private final float baseHunger;
/*    */   private final int freq;
/*    */   private Function<Integer, Float> hungerFunction;
/*    */   
/*    */   public HungerOverTimeEffect() {
/* 22 */     this(DamageSource.field_76366_f, 2.0F, 20);
/*    */   }
/*    */ 
/*    */   
/*    */   public HungerOverTimeEffect(DamageSource source, float baseDamage, int freq) {
/* 27 */     super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
/* 28 */     this.source = source;
/* 29 */     this.baseHunger = baseDamage;
/* 30 */     this.freq = freq;
/* 31 */     this.hungerFunction = (amp -> Float.valueOf(this.baseHunger * amp.intValue()));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRender(EffectInstance effect) {
/* 36 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldRenderHUD(EffectInstance effect) {
/* 41 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_76397_a(int duration, int amplifier) {
/* 47 */     return (duration % this.freq == 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_76394_a(LivingEntity entity, int amplifier) {
/* 53 */     if (entity instanceof PlayerEntity) {
/*    */       
/* 55 */       float damage = Math.max(this.baseHunger, ((Float)this.hungerFunction.apply(Integer.valueOf(amplifier))).floatValue());
/* 56 */       int foodLevel = (int)(((PlayerEntity)entity).func_71024_bL().func_75116_a() - damage);
/* 57 */       foodLevel = Math.max(foodLevel, 0);
/* 58 */       ((PlayerEntity)entity).func_71024_bL().func_75114_a(foodLevel);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public float getBaseHunger() {
/* 64 */     return this.baseHunger;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHungerFunction(Function<Integer, Float> func) {
/* 69 */     this.hungerFunction = func;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\effects\HungerOverTimeEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */