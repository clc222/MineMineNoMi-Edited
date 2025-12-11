/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.mero;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class SlaveArrowProjectile extends AbilityProjectileEntity {
/*    */   public SlaveArrowProjectile(EntityType type, World world) {
/* 15 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public SlaveArrowProjectile(World world, LivingEntity player, Ability ability) {
/* 20 */     super((EntityType)MeroProjectiles.SLAVE_ARROW.get(), world, player, ability);
/*    */     
/* 22 */     setDamage(1.6F);
/* 23 */     setMaxLife(28);
/*    */     
/* 25 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance((Effect)ModEffects.LOVESTRUCK.get(), 100, 0) });
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\mero\SlaveArrowProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */