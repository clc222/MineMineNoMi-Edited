/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SAnimeScreamPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ @Deprecated
/*    */ public abstract class HitAbility
/*    */   extends StatsChangeAbility
/*    */   implements IHitAbility
/*    */ {
/*    */   private boolean isStoppingAfterHit = true;
/*    */   protected IOnHitEntity onHitEntityEvent = (player, target) -> 0.0F;
/*    */   
/*    */   public HitAbility(AbilityCore core) {
/* 24 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setStoppingAfterHit(boolean flag) {
/* 29 */     this.isStoppingAfterHit = flag;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isStoppingAfterHit() {
/* 35 */     return this.isStoppingAfterHit;
/*    */   }
/*    */ 
/*    */   
/*    */   public <T extends ModDamageSource> T getDamageSource(PlayerEntity player, @Nullable T source) {
/*    */     AbilityDamageSource abilityDamageSource;
/* 41 */     if (source == null) abilityDamageSource = ModDamageSource.causeAbilityDamage((LivingEntity)player, this); 
/* 42 */     return (T)abilityDamageSource;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isActive(PlayerEntity player) {
/* 48 */     return isContinuous();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float hitEntity(PlayerEntity player, LivingEntity target) {
/* 59 */     float result = this.onHitEntityEvent.onHitEntity(player, target);
/*    */     
/* 61 */     if (((Boolean)CommonConfig.INSTANCE.animeScreaming.get()).booleanValue()) {
/* 62 */       WyNetwork.sendToAllTrackingAndSelf(new SAnimeScreamPacket(player.func_145782_y(), getDisplayNameOld()), (Entity)player);
/*    */     }
/* 64 */     if (this.isStoppingAfterHit) {
/* 65 */       tryStoppingContinuity(player);
/*    */     }
/* 67 */     return result;
/*    */   }
/*    */   
/*    */   public static interface IOnHitEntity extends Serializable {
/*    */     float onHitEntity(PlayerEntity param1PlayerEntity, LivingEntity param1LivingEntity);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\HitAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */