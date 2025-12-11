/*    */ package xyz.pixelatedw.mineminenomi.api.poi;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ 
/*    */ public class POIEventTarget {
/*    */   private ServerWorld level;
/*    */   private Vector3d pos;
/*    */   private long startTime;
/*    */   private long openTime;
/*    */   private EventTriggerAction triggerAction;
/*    */   
/*    */   public POIEventTarget() {}
/*    */   
/*    */   public POIEventTarget(ServerWorld level, Vector3d pos, long openTime) {
/* 19 */     this.level = level;
/* 20 */     this.pos = pos;
/* 21 */     this.startTime = level.func_82737_E();
/* 22 */     this.openTime = openTime;
/*    */   }
/*    */   
/*    */   public ServerWorld getLevel() {
/* 26 */     return this.level;
/*    */   }
/*    */   
/*    */   public Vector3d getPosition() {
/* 30 */     return this.pos;
/*    */   }
/*    */   
/*    */   public long getStartTime() {
/* 34 */     return this.startTime;
/*    */   }
/*    */   
/*    */   public boolean shouldTrigger(ServerPlayerEntity player) {
/* 38 */     return (getTriggerAction() != null && player.func_195048_a(getPosition()) < 10000.0D);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public EventTriggerAction getTriggerAction() {
/* 43 */     return this.triggerAction;
/*    */   }
/*    */   
/*    */   public void setTriggerAction(EventTriggerAction event) {
/* 47 */     this.triggerAction = event;
/*    */   }
/*    */   
/*    */   public boolean hasExpired(ServerWorld world) {
/* 51 */     return (world.func_82737_E() > this.startTime + this.openTime);
/*    */   }
/*    */   
/*    */   public void tick() {}
/*    */   
/*    */   public CompoundNBT save() {
/* 57 */     CompoundNBT tag = new CompoundNBT();
/*    */     
/* 59 */     tag.func_74780_a("x", this.pos.field_72450_a);
/* 60 */     tag.func_74780_a("y", this.pos.field_72448_b);
/* 61 */     tag.func_74780_a("z", this.pos.field_72449_c);
/* 62 */     tag.func_74772_a("startTime", this.startTime);
/* 63 */     tag.func_74772_a("openTime", this.openTime);
/*    */     
/* 65 */     return tag;
/*    */   }
/*    */   
/*    */   public void load(CompoundNBT tag) {
/* 69 */     double x = tag.func_74769_h("x");
/* 70 */     double y = tag.func_74769_h("y");
/* 71 */     double z = tag.func_74769_h("z");
/* 72 */     Vector3d pos = new Vector3d(x, y, z);
/* 73 */     this.pos = pos;
/* 74 */     this.startTime = tag.func_74763_f("startTime");
/* 75 */     this.openTime = tag.func_74763_f("openTime");
/*    */   }
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface EventTriggerAction {
/*    */     void trigger(ServerWorld param1ServerWorld, POIEventTarget param1POIEventTarget);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\poi\POIEventTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */