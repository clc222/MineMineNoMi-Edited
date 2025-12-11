/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class KairosekiBulletProjectile extends AbilityProjectileEntity {
/*    */   public KairosekiBulletProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public KairosekiBulletProjectile(World world, LivingEntity player) {
/* 22 */     super((EntityType)ExtraProjectiles.KAIROSEKI_BULLET.get(), world, player, SourceElement.NONE, SourceHakiNature.IMBUING, new SourceType[] { SourceType.PROJECTILE, SourceType.PHYSICAL, SourceType.BULLET });
/* 23 */     setDamage(8.0F);
/*    */     
/* 25 */     setDamageSource(getDamageSource().bypassLogia());
/*    */     
/* 27 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance((Effect)ModEffects.ABILITY_OFF.get(), 20, 2) });
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\extra\KairosekiBulletProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */