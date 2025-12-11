/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.netsu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.netsu.NetsuEnhancementAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class NekkaiGyoraiProjectile extends AbilityProjectileEntity {
/* 23 */   private int damage = 10;
/*    */ 
/*    */   
/*    */   public NekkaiGyoraiProjectile(EntityType type, World world) {
/* 27 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public NekkaiGyoraiProjectile(World world, LivingEntity player, Ability ability) {
/* 32 */     super((EntityType)NetsuProjectiles.NEKKAI_GYORAI.get(), world, player, ability);
/*    */     
/* 34 */     IAbilityData abilityProps = AbilityDataCapability.get(player);
/* 35 */     NetsuEnhancementAbility netsuAbility = (NetsuEnhancementAbility)abilityProps.getEquippedAbility(NetsuEnhancementAbility.INSTANCE);
/* 36 */     if (netsuAbility != null && netsuAbility.isContinuous()) {
/* 37 */       this.damage += 5;
/*    */     }
/* 39 */     setDamage(this.damage);
/* 40 */     setMaxLife(30);
/*    */     
/* 42 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 43 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 44 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 49 */     hitEntity.func_70015_d(4);
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 54 */     AbilityHelper.placeBlockIfAllowed(getThrower(), hit.func_177982_a(0, 1, 0), Blocks.field_150480_ab.func_176223_P(), AirBlockProtectionRule.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 59 */     if (!this.field_70170_p.field_72995_K)
/*    */     {
/* 61 */       for (int i = 0; i < 20; i++) {
/*    */         
/* 63 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 64 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 65 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 67 */         ParticleType<SimpleParticleData> particle = (ParticleType<SimpleParticleData>)ModParticleTypes.NETSU.get();
/* 68 */         if (i % 3 == 0)
/* 69 */           particle = (ParticleType<SimpleParticleData>)ModParticleTypes.NETSU2.get(); 
/* 70 */         if (i % 7 == 0) {
/* 71 */           particle = (ParticleType<SimpleParticleData>)ModParticleTypes.MERA.get();
/*    */         }
/* 73 */         SimpleParticleData data = new SimpleParticleData(particle);
/* 74 */         data.setLife(10);
/* 75 */         data.setSize(1.3F);
/* 76 */         WyHelper.spawnParticles((IParticleData)data, (ServerWorld)this.field_70170_p, func_226277_ct_() + offsetX, func_226278_cu_() + offsetY, func_226281_cx_() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\netsu\NekkaiGyoraiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */