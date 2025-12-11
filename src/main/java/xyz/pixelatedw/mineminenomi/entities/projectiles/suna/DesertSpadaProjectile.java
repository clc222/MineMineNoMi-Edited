/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.suna;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class DesertSpadaProjectile extends AbilityProjectileEntity {
/* 20 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { CoreBlockProtectionRule.INSTANCE })).build();
/*    */   
/*    */   private double initialY;
/*    */ 
/*    */   
/*    */   public DesertSpadaProjectile(EntityType type, World world) {
/* 26 */     super(type, world);
/*    */   }
/*    */   
/*    */   public DesertSpadaProjectile(World world, LivingEntity player) {
/* 30 */     super((EntityType)SunaProjectiles.DESERT_SPADA.get(), world, player, DesertSpadaAbility.INSTANCE);
/*    */     
/* 32 */     setDamage(30.0F);
/* 33 */     setMaxLife(45);
/* 34 */     setPassThroughEntities();
/* 35 */     setPassThroughBlocks();
/*    */     
/* 37 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance((Effect)ModEffects.DEHYDRATION.get(), 200, 1) });
/*    */ 
/*    */ 
/*    */     
/* 41 */     this.initialY = player.func_226278_cu_();
/*    */     
/* 43 */     setEntityCollisionSize(2.0D);
/*    */     
/* 45 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 50 */     BlockPos pos = null;
/* 51 */     int j = 1;
/*    */     
/* 53 */     while (pos == null) {
/* 54 */       BlockState state = this.field_70170_p.func_180495_p(func_233580_cy_().func_177979_c(j));
/*    */       
/* 56 */       if (state.func_185904_a().func_76220_a()) {
/* 57 */         pos = func_233580_cy_().func_177979_c(j);
/*    */         
/*    */         break;
/*    */       } 
/* 61 */       if (j > 5) {
/*    */         break;
/*    */       }
/* 64 */       j++;
/*    */     } 
/*    */     
/* 67 */     if (pos == null) {
/*    */       return;
/*    */     }
/*    */     
/* 71 */     AbilityHelper.createFilledSphere(this.field_70170_p, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), 2, Blocks.field_150350_a, GRIEF_RULE);
/* 72 */     AbilityHelper.createFilledSphere(this.field_70170_p, pos.func_177958_n(), pos.func_177956_o() - 1, pos.func_177952_p(), 2, Blocks.field_150350_a, GRIEF_RULE);
/*    */     
/* 74 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.DESERT_SPADA.get(), (Entity)this, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\suna\DesertSpadaProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */