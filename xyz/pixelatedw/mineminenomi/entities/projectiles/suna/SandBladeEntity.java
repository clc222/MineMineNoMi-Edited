/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.suna;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.network.NetworkHooks;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.suna.DesertGrandeEspadaAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.TargetHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class SandBladeEntity extends Entity {
/* 23 */   private int chargeTime = 20; private LivingEntity owner;
/*    */   
/*    */   public SandBladeEntity(World level, LivingEntity owner) {
/* 26 */     super((EntityType)SunaProjectiles.SAND_BLADE.get(), level);
/* 27 */     this.owner = owner;
/*    */   }
/*    */   
/*    */   public SandBladeEntity(EntityType type, World level) {
/* 31 */     super(type, level);
/*    */   }
/*    */   
/*    */   public void setChargeTime(int time) {
/* 35 */     this.chargeTime = time;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 40 */     if (!this.field_70170_p.field_72995_K) {
/* 41 */       if (this.owner == null || !this.owner.func_70089_S()) {
/* 42 */         func_70106_y();
/*    */         
/*    */         return;
/*    */       } 
/* 46 */       if (this.field_70173_aa < this.chargeTime) {
/* 47 */         WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.SAND_BLADE_IDLE.get(), this, func_226277_ct_(), func_226278_cu_(), func_226281_cx_());
/*    */       }
/* 49 */       else if (this.field_70173_aa >= this.chargeTime) {
/* 50 */         WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.SAND_BLADE_ACTIVE.get(), this, func_226277_ct_(), func_226278_cu_(), func_226281_cx_());
/* 51 */         if (this.field_70173_aa <= this.chargeTime + 10) {
/* 52 */           List<LivingEntity> targets = TargetHelper.getEntitiesInArea(this.owner, func_233580_cy_(), 6.0D, TargetsPredicate.DEFAULT_AREA_CHECK, new Class[] { LivingEntity.class });
/* 53 */           AbilityDamageSource abilityDamageSource = ModDamageSource.causeAbilityDamage(this.owner, DesertGrandeEspadaAbility.INSTANCE);
/* 54 */           for (LivingEntity target : targets) {
/* 55 */             if (target.func_70097_a((DamageSource)abilityDamageSource, 10.0F)) {
/* 56 */               double dir = WyHelper.randomDouble() / 2.0D;
/* 57 */               AbilityHelper.setDeltaMovement((Entity)target, dir, 0.2D, dir);
/*    */             } 
/*    */           } 
/*    */         } 
/*    */         
/* 62 */         func_70106_y();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_70088_a() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_70037_a(CompoundNBT pCompound) {}
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_213281_b(CompoundNBT pCompound) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public IPacket<?> func_213297_N() {
/* 84 */     return NetworkHooks.getEntitySpawningPacket(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\suna\SandBladeEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */