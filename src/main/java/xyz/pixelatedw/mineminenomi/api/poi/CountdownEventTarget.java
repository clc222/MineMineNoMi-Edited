/*    */ package xyz.pixelatedw.mineminenomi.api.poi;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import java.util.UUID;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ 
/*    */ public class CountdownEventTarget extends POIEventTarget {
/*    */   private int countdown;
/*    */   private UUID targetId;
/*    */   
/*    */   public CountdownEventTarget() {}
/*    */   
/*    */   public CountdownEventTarget(ServerWorld world, LivingEntity target, long openTime, int ticks) {
/* 19 */     super(world, target.func_213303_ch(), openTime);
/* 20 */     this.countdown = ticks;
/* 21 */     this.targetId = target.func_110124_au();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldTrigger(ServerPlayerEntity player) {
/* 26 */     return (this.countdown <= 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick() {
/* 31 */     if (this.countdown > 0) {
/* 32 */       this.countdown--;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public CompoundNBT save() {
/* 38 */     CompoundNBT tag = super.save();
/*    */     
/* 40 */     tag.func_74768_a("countdown", this.countdown);
/* 41 */     tag.func_186854_a("targetId", this.targetId);
/*    */     
/* 43 */     return tag;
/*    */   }
/*    */ 
/*    */   
/*    */   public void load(CompoundNBT tag) {
/* 48 */     super.load(tag);
/*    */     
/* 50 */     this.countdown = tag.func_74762_e("countdown");
/* 51 */     this.targetId = tag.func_186857_a("targetId");
/*    */   }
/*    */   
/*    */   public UUID getTargetId() {
/* 55 */     return this.targetId;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Optional<LivingEntity> getTarget(ServerWorld world) {
/* 60 */     PlayerEntity playerTarget = world.func_217371_b(this.targetId);
/* 61 */     if (playerTarget != null) {
/* 62 */       return (Optional)Optional.ofNullable(playerTarget);
/*    */     }
/*    */     
/* 65 */     return world.getEntities()
/* 66 */       .filter(LivingEntity.class::isInstance)
/* 67 */       .map(LivingEntity.class::cast)
/* 68 */       .filter(entity -> entity.func_110124_au().equals(this.targetId))
/* 69 */       .findFirst();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\poi\CountdownEventTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */