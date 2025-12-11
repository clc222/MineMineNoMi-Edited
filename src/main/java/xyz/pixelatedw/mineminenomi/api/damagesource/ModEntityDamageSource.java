/*    */ package xyz.pixelatedw.mineminenomi.api.damagesource;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ 
/*    */ public class ModEntityDamageSource
/*    */   extends ModDamageSource {
/*    */   @Nullable
/*    */   protected final Entity entity;
/*    */   
/*    */   public ModEntityDamageSource(String damageTypeIn, @Nullable Entity entity) {
/* 18 */     super(damageTypeIn, false, false, false);
/*    */     
/* 20 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Entity func_76346_g() {
/* 26 */     return this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public ITextComponent func_151519_b(LivingEntity entityLivingBaseIn) {
/* 31 */     ItemStack itemstack = (this.entity != null && this.entity instanceof LivingEntity) ? ((LivingEntity)this.entity).func_184614_ca() : ItemStack.field_190927_a;
/*    */     
/* 33 */     String s = "death.attack." + this.field_76373_n;
/*    */     
/* 35 */     return (!itemstack.func_190926_b() && itemstack.func_82837_s()) ? (ITextComponent)new TranslationTextComponent(s + ".item", new Object[] { entityLivingBaseIn.func_145748_c_(), this.entity.func_145748_c_(), itemstack.func_200301_q() }) : (ITextComponent)new TranslationTextComponent(s, new Object[] { entityLivingBaseIn.func_145748_c_(), this.entity.func_145748_c_() });
/*    */   }
/*    */   
/*    */   public boolean isDifficultyScaled() {
/* 39 */     return (this.entity != null && this.entity instanceof LivingEntity && !(this.entity instanceof net.minecraft.entity.player.PlayerEntity));
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Vector3d getDamageLocation() {
/* 44 */     return (this.entity != null) ? this.entity.func_213303_ch() : null;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\damagesource\ModEntityDamageSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */