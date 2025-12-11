/*    */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HitTrackerComponent
/*    */   extends AbilityComponent<IAbility>
/*    */ {
/* 21 */   private Set<Entity> hits = new HashSet<>();
/* 22 */   private Set<UUID> hitsUUIDs = new HashSet<>();
/*    */   
/*    */   public HitTrackerComponent(IAbility ability) {
/* 25 */     super(ModAbilityKeys.HIT_TRACKER, ability);
/*    */   }
/*    */   
/*    */   public void clearHits() {
/* 29 */     this.hits.clear();
/* 30 */     this.hitsUUIDs.clear();
/*    */   }
/*    */   
/*    */   public boolean canHit(Entity target) {
/* 34 */     ensureIsRegistered();
/* 35 */     if (this.hits.contains(target)) {
/* 36 */       return false;
/*    */     }
/*    */     
/* 39 */     this.hits.add(target);
/* 40 */     this.hitsUUIDs.add(target.func_110124_au());
/* 41 */     return true;
/*    */   }
/*    */   
/*    */   public Set<Entity> getHits() {
/* 45 */     return this.hits;
/*    */   }
/*    */   
/*    */   public Set<UUID> getHitsUUIDs() {
/* 49 */     return this.hitsUUIDs;
/*    */   }
/*    */   
/*    */   protected void doTick(LivingEntity entity) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\HitTrackerComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */