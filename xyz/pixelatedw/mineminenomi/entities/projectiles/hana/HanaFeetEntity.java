/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.hana;
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.BlockParticleData;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class HanaFeetEntity extends AbilityProjectileEntity {
/*    */   public HanaFeetEntity(EntityType type, World world) {
/* 29 */     super(type, world);
/*    */   }
/*    */   
/*    */   public HanaFeetEntity(World world, LivingEntity player, Ability ability) {
/* 33 */     super((EntityType)HanaProjectiles.FEET.get(), world, player, ability);
/* 34 */     setMaxLife(35);
/* 35 */     setDamage(10.0F);
/* 36 */     setPassThroughEntities();
/* 37 */     setFist();
/*    */ 
/*    */     
/* 40 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */   
/*    */   public void onBlockImpactEvent(BlockPos pos) {
/* 44 */     if (getThrower() == null) {
/*    */       return;
/*    */     }
/*    */     
/* 48 */     List<LivingEntity> list = WyHelper.getNearbyLiving(new Vector3d(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p()), (IWorld)this.field_70170_p, 5.0D, ModEntityPredicates.getEnemyFactions(getThrower()));
/* 49 */     list.remove(getThrower());
/* 50 */     Iterator<LivingEntity> iter = list.iterator();
/*    */     
/* 52 */     while (iter.hasNext()) {
/* 53 */       LivingEntity target = iter.next();
/* 54 */       if (target.func_70097_a((DamageSource)ModDamageSource.causeAbilityDamage(getThrower(), CienFleurStompAbility.INSTANCE), getDamage())) {
/* 55 */         AbilityHelper.setDeltaMovement((Entity)target, target.func_213322_ci().func_72441_c(0.0D, 1.0D, 0.0D));
/*    */       }
/*    */     } 
/*    */     
/* 59 */     List<BlockPos> blocks = WyHelper.getNearbyBlocks(func_233580_cy_(), (IWorld)this.field_70170_p, 5, p -> FoliageBlockProtectionRule.INSTANCE.isApproved(this.field_70170_p.func_180495_p(p)), (List)ImmutableList.of(Blocks.field_150350_a));
/* 60 */     for (BlockPos p : blocks) {
/*    */       
/* 62 */       BlockState blockState1 = this.field_70170_p.func_180495_p(new BlockPos(p.func_177958_n(), p.func_177956_o(), p.func_177952_p()));
/* 63 */       for (int i = 0; i < 150; i++) {
/*    */         
/* 65 */         double offsetX = WyHelper.randomDouble();
/* 66 */         double offsetY = WyHelper.randomDouble();
/* 67 */         double offsetZ = WyHelper.randomDouble();
/*    */         
/* 69 */         ((ServerWorld)this.field_70170_p).func_195598_a((IParticleData)new BlockParticleData(ParticleTypes.field_197611_d, blockState1), p
/*    */             
/* 71 */             .func_177958_n() + offsetX, p
/* 72 */             .func_177956_o() + offsetY, p
/* 73 */             .func_177952_p() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */       
/* 76 */       AbilityHelper.placeBlockIfAllowed(getThrower(), p, Blocks.field_150350_a.func_176223_P(), FoliageBlockProtectionRule.INSTANCE);
/*    */     } 
/*    */     
/* 79 */     BlockState blockState = this.field_70170_p.func_180495_p(new BlockPos(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p()));
/* 80 */     if (blockState.func_185904_a().func_76220_a())
/*    */     {
/* 82 */       for (int i = 0; i < 150; i++) {
/*    */         
/* 84 */         double x = WyHelper.randomDouble();
/* 85 */         double z = WyHelper.randomDouble();
/*    */         
/* 87 */         ((ServerWorld)this.field_70170_p).func_195598_a((IParticleData)new BlockParticleData(ParticleTypes.field_197611_d, blockState), pos
/* 88 */             .func_177958_n() + WyHelper.randomWithRange(-3, 3) + x, (pos
/* 89 */             .func_177956_o() + 1), pos
/* 90 */             .func_177952_p() + WyHelper.randomWithRange(-3, 3) + z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   public void onProjectileCollision(AbilityProjectileEntity owner, AbilityProjectileEntity target) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\hana\HanaFeetEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */