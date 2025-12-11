/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.sniper.SakuretsuSabotenBoshiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class SakuretsuSabotenBoshiProjectile extends AbilityProjectileEntity {
/*    */   public SakuretsuSabotenBoshiProjectile(EntityType type, World world) {
/* 13 */     super(type, world);
/*    */   }
/*    */   
/*    */   public SakuretsuSabotenBoshiProjectile(World world, LivingEntity player) {
/* 17 */     super((EntityType)SniperProjectiles.SAKURETSU_SABOTEN_BOSHI.get(), world, player, SakuretsuSabotenBoshiAbility.INSTANCE);
/*    */     
/* 19 */     setDamage(15.0F);
/*    */     
/* 21 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity target) {
/* 25 */     EffectInstance inst = new EffectInstance((Effect)ModEffects.SABOTEN_BOSHI.get(), 200, 6);
/* 26 */     target.func_195064_c(inst);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\sniper\SakuretsuSabotenBoshiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */