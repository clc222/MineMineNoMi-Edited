/*    */ package xyz.pixelatedw.mineminenomi.entities.dummies;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.buggypirates.CabajiEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SToggleAnimationPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class CabajiDummyEntity extends CabajiEntity {
/*    */   public CabajiDummyEntity(EntityType type, World world) {
/* 14 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 19 */     this.field_70145_X = true;
/* 20 */     super.func_70071_h_();
/* 21 */     this.field_70145_X = false;
/* 22 */     func_189654_d(true);
/* 23 */     AbilityHelper.setDeltaMovement((Entity)this, 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void initBoss() {
/* 28 */     WyNetwork.sendToAllTrackingAndSelf(SToggleAnimationPacket.playAnimation((LivingEntity)this, ModAnimations.RIDE_UNICYCLE, -1, true), (Entity)this);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\dummies\CabajiDummyEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */