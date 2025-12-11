/*    */ package xyz.pixelatedw.mineminenomi.effects;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.IWorld;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.jiki.GenocideRaidAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.DamageOverTimeEffect;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.jiki.GenocideRaidEffectEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class GenocideRaidEffect extends DamageOverTimeEffect {
/*    */   public GenocideRaidEffect() {
/* 13 */     super((DamageSource)ModDamageSource.GENOCIDE_RAID, 10.0F, 20);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_76394_a(LivingEntity entity, int amplifier) {
/* 18 */     for (GenocideRaidEffectEntity effectEntity : WyHelper.getNearbyEntities(entity.func_213303_ch(), (IWorld)entity.field_70170_p, 1.0D, null, new Class[] { GenocideRaidEffectEntity.class })) {
/* 19 */       LivingEntity owner = effectEntity.getOwner();
/*    */       
/* 21 */       if (owner != null && owner.func_70089_S()) {
/* 22 */         float damage = Math.max(getBaseDamage(), ((Float)getDamageFunction().apply(Integer.valueOf(amplifier))).floatValue());
/*    */         
/* 24 */         entity.field_70172_ad = 0;
/* 25 */         entity.func_70097_a((DamageSource)AbilityDamageSource.causeAbilityDamage(owner, GenocideRaidAbility.INSTANCE), damage);
/*    */         
/*    */         return;
/*    */       } 
/*    */     } 
/*    */     
/* 31 */     super.func_76394_a(entity, amplifier);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldUpdateClient() {
/* 36 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\effects\GenocideRaidEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */