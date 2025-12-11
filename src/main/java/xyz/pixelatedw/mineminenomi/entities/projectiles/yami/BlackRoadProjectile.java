/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.yami;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class BlackRoadProjectile extends AbilityProjectileEntity {
/* 19 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { CoreBlockProtectionRule.INSTANCE })).build();
/*    */   
/*    */   public BlackRoadProjectile(EntityType<Entity> type, World world) {
/* 22 */     super(type, world);
/*    */   }
/*    */   
/*    */   public BlackRoadProjectile(World world, LivingEntity player, Ability ability) {
/* 26 */     super((EntityType)YamiProjectiles.BLACK_ROAD.get(), world, player, ability.getCore());
/*    */     
/* 28 */     setMaxLife(20);
/* 29 */     setDamage(10.0F);
/* 30 */     setPassThroughEntities();
/* 31 */     setPassThroughBlocks();
/*    */     
/* 33 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 37 */     BlockPos pos = null;
/*    */     
/* 39 */     int j = 1;
/*    */     
/* 41 */     while (pos == null) {
/* 42 */       BlockState state = this.field_70170_p.func_180495_p(func_233580_cy_().func_177979_c(j));
/*    */       
/* 44 */       if (state.func_185904_a().func_76220_a()) {
/* 45 */         pos = func_233580_cy_().func_177979_c(j);
/*    */         
/*    */         break;
/*    */       } 
/* 49 */       if (j > 2) {
/*    */         break;
/*    */       }
/*    */       
/* 53 */       j++;
/*    */     } 
/*    */     
/* 56 */     if (pos == null) {
/*    */       return;
/*    */     }
/*    */     
/* 60 */     int size = Math.round(2.0F + 4.0F * (1.0F - getLife() / getMaxLife()));
/*    */     
/* 62 */     AbilityHelper.createFilledSphere(this.field_70170_p, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), size, (Block)ModBlocks.DARKNESS.get(), GRIEF_RULE);
/*    */     
/* 64 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.DARK_MATTER_CHARGING.get(), (Entity)this, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\yami\BlackRoadProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */