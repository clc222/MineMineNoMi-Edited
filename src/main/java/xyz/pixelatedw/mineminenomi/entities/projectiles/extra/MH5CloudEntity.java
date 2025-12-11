/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class MH5CloudEntity extends EntityCloud {
/* 14 */   private static final ModDamageSource SOURCE = (ModDamageSource)(new ModDamageSource("mh5_gas")).setAreaOfEffect().setSourceElement(SourceElement.POISON).setInternal().func_82726_p().func_151518_m();
/*    */   
/*    */   public MH5CloudEntity(World world) {
/* 17 */     super(world);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_70106_y() {
/* 22 */     for (LivingEntity target : WyHelper.getNearbyLiving(func_213303_ch(), (IWorld)this.field_70170_p, 100.0D, null)) {
/* 23 */       if (target.func_204231_K()) {
/*    */         continue;
/*    */       }
/*    */       
/* 27 */       boolean hasGasMask = ItemsHelper.hasItemInSlot(target, EquipmentSlotType.HEAD, (Item)ModArmors.MH5_GAS_MASK.get());
/* 28 */       if (hasGasMask) {
/*    */         continue;
/*    */       }
/*    */       
/* 32 */       target.func_70097_a((DamageSource)SOURCE, Float.MAX_VALUE);
/*    */     } 
/*    */     
/* 35 */     super.func_70106_y();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 40 */     super.func_70071_h_();
/* 41 */     if (!this.field_70170_p.field_72995_K && 
/* 42 */       this.field_70173_aa % 2 == 0)
/* 43 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.MH5_GAS.get(), this, func_226277_ct_(), func_226278_cu_(), func_226281_cx_()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\extra\MH5CloudEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */