/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ItemSpawnComponent;
/*    */ 
/*    */ public abstract class ItemAbility2
/*    */   extends Ability {
/*    */   private static final int COOLDOWN = 10;
/* 12 */   protected final ContinuousComponent continuousComponent = (new ContinuousComponent(this, true)).addStartEvent(90, this::startContinuityEvent).addEndEvent(90, this::endContinuityEvent);
/* 13 */   protected final ItemSpawnComponent itemSpawnComponent = (new ItemSpawnComponent(this)).addMissingItemEvent(90, this::missingItemEvent);
/*    */   
/*    */   public ItemAbility2(AbilityCore<? extends IAbility> core) {
/* 16 */     super(core);
/*    */     
/* 18 */     this.isNew = true;
/* 19 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.itemSpawnComponent });
/*    */     
/* 21 */     addUseEvent(200, this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 25 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 29 */     this.itemSpawnComponent.spawnItem(entity, createItemStack(entity));
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 33 */     this.itemSpawnComponent.despawnItems(entity);
/* 34 */     this.cooldownComponent.startCooldown(entity, 10.0F);
/*    */   }
/*    */   
/*    */   private void missingItemEvent(LivingEntity entity, IAbility ability) {
/* 38 */     this.continuousComponent.stopContinuity(entity);
/*    */   }
/*    */   
/*    */   public abstract ItemStack createItemStack(LivingEntity paramLivingEntity);
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\ItemAbility2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */