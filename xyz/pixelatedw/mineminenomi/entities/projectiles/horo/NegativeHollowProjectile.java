/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.horo;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class NegativeHollowProjectile extends AbilityProjectileEntity {
/*    */   public NegativeHollowProjectile(EntityType type, World world) {
/* 14 */     super(type, world);
/*    */   }
/*    */   
/*    */   public NegativeHollowProjectile(World world, LivingEntity player, Ability ability) {
/* 18 */     super((EntityType)HoroProjectiles.NEGATIVE_HOLLOW.get(), world, player, ability);
/*    */     
/* 20 */     setDamage(10.0F);
/*    */     
/* 22 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance(Effects.field_76431_k, 200, 1), new EffectInstance((Effect)ModEffects.NEGATIVE.get(), 40, 1) });
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\horo\NegativeHollowProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */