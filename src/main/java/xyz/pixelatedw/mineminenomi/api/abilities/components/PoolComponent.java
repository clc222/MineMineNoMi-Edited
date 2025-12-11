/*     */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.util.Collections;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.components.SSetPoolInUsePacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class PoolComponent
/*     */   extends AbilityComponent<IAbility> {
/*     */   private final Set<AbilityPool2> pools;
/*     */   private boolean poolInUse;
/*     */   
/*     */   public PoolComponent(IAbility ability, AbilityPool2 pool, AbilityPool2... pools) {
/*  27 */     super(ModAbilityKeys.POOL, ability);
/*  28 */     this.pools = Sets.newHashSet((Object[])new AbilityPool2[] { pool });
/*  29 */     Collections.addAll(this.pools, pools);
/*     */   }
/*     */   private float ticksLocked; private Predicate<IAbility> predicate;
/*     */   
/*     */   public void postInit(IAbility ability) {
/*  34 */     ability.getComponent(ModAbilityKeys.SLOT_DECORATION).ifPresent(component -> component.addPreRenderEvent(500, ()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  58 */     this.pools.forEach(pool -> pool.addAbilityCore(ability.getCore()));
/*  59 */     this.predicate = (abl -> {
/*     */         if (abl.equals(ability)) {
/*     */           return false;
/*     */         }
/*     */         Optional<PoolComponent> poolComponent = abl.getComponent(ModAbilityKeys.POOL);
/*  64 */         return (poolComponent.isPresent() && hasAtLeastOneSamePool(poolComponent.get()));
/*     */       });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doTick(LivingEntity entity) {
/*  73 */     if (getAbility().hasComponent(ModAbilityKeys.DISABLE) && ((DisableComponent)getAbility().getComponent(ModAbilityKeys.DISABLE).get()).isDisabled()) {
/*     */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPool(AbilityPool2 pool) {
/*  94 */     this.pools.add(pool);
/*     */   }
/*     */   
/*     */   public Set<AbilityPool2> getPools() {
/*  98 */     return this.pools;
/*     */   }
/*     */   
/*     */   public boolean containsPool(AbilityPool2 pool) {
/* 102 */     for (AbilityPool2 ablpool : this.pools) {
/* 103 */       if (ablpool.equals(pool)) {
/* 104 */         return true;
/*     */       }
/*     */     } 
/* 107 */     return false;
/*     */   }
/*     */   
/*     */   public boolean hasAtLeastOneSamePool(PoolComponent otherComponent) {
/* 111 */     for (AbilityPool2 p1 : this.pools) {
/* 112 */       for (AbilityPool2 p2 : otherComponent.getPools()) {
/* 113 */         if (p1.equals(p2)) {
/* 114 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 118 */     return false;
/*     */   }
/*     */   
/*     */   public Set<IAbility> getAbilitiesInPool(LivingEntity entity) {
/* 122 */     return AbilityDataCapability.get(entity).getEquippedAbilities(this.predicate);
/*     */   }
/*     */   
/*     */   public boolean isPoolInUse() {
/* 126 */     return this.poolInUse;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAbilityFromPoolInUse(LivingEntity entity, boolean flag) {
/* 141 */     this.poolInUse = flag;
/* 142 */     if (!flag) {
/* 143 */       getAbility().getComponent(ModAbilityKeys.SLOT_DECORATION).ifPresent(c -> c.resetDecoration());
/*     */     }
/* 145 */     if (!entity.field_70170_p.field_72995_K) {
/* 146 */       WyNetwork.sendToAllTrackingAndSelf(new SSetPoolInUsePacket(entity, getAbility(), flag, 0), (Entity)entity);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startPoolInUse(LivingEntity entity) {
/* 155 */     ensureIsRegistered();
/* 156 */     getAbilitiesInPool(entity).forEach(otherAbility -> otherAbility.getComponent(ModAbilityKeys.POOL).ifPresent(()));
/*     */   }
/*     */   
/*     */   public void stopPoolInUse(LivingEntity entity) {
/* 160 */     ensureIsRegistered();
/* 161 */     getAbilitiesInPool(entity).forEach(otherAbility -> otherAbility.getComponent(ModAbilityKeys.POOL).ifPresent(()));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\PoolComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */