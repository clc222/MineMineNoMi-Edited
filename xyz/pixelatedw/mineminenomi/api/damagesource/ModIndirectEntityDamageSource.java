/*    */ package xyz.pixelatedw.mineminenomi.api.damagesource;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ 
/*    */ public class ModIndirectEntityDamageSource
/*    */   extends ModEntityDamageSource {
/*    */   private final Entity trueSource;
/*    */   
/*    */   public ModIndirectEntityDamageSource(String damageTypeIn, Entity source, @Nullable Entity trueSource) {
/* 15 */     super(damageTypeIn, source);
/*    */     
/* 17 */     this.trueSource = trueSource;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Entity func_76346_g() {
/* 23 */     return this.trueSource;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Entity func_76364_f() {
/* 33 */     return this.entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ITextComponent func_151519_b(LivingEntity entityLivingBaseIn) {
/* 41 */     ITextComponent itextcomponent = (this.trueSource == null) ? this.entity.func_145748_c_() : this.trueSource.func_145748_c_();
/* 42 */     ItemStack itemstack = (this.trueSource instanceof LivingEntity) ? ((LivingEntity)this.trueSource).func_184614_ca() : ItemStack.field_190927_a;
/* 43 */     String s = "death.attack." + this.field_76373_n;
/* 44 */     String s1 = s + ".item";
/* 45 */     return (!itemstack.func_190926_b() && itemstack.func_82837_s()) ? (ITextComponent)new TranslationTextComponent(s1, new Object[] { entityLivingBaseIn.func_145748_c_(), itextcomponent, itemstack.func_200301_q() }) : (ITextComponent)new TranslationTextComponent(s, new Object[] { entityLivingBaseIn.func_145748_c_(), itextcomponent });
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\damagesource\ModIndirectEntityDamageSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */