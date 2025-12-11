/*    */ package xyz.pixelatedw.mineminenomi.api.poi;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.NPCWorldData;
/*    */ 
/*    */ public class NTEventTarget extends POIEventTarget {
/*    */   private TrackedNPC tracked;
/*    */   
/*    */   public NTEventTarget() {}
/*    */   
/*    */   public NTEventTarget(ServerWorld world, Vector3d pos, long openTime, TrackedNPC tracked) {
/* 15 */     super(world, pos, openTime);
/* 16 */     setTracked(tracked);
/*    */   }
/*    */   
/*    */   private void setTracked(TrackedNPC tracked) {
/* 20 */     this.tracked = tracked;
/* 21 */     setTriggerAction((world, poi) -> this.tracked.spawnTrackedMob(world, poi.getPosition()));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CompoundNBT save() {
/* 28 */     CompoundNBT tag = super.save();
/*    */     
/* 30 */     tag.func_74772_a("trackedId", this.tracked.getId());
/*    */     
/* 32 */     return tag;
/*    */   }
/*    */ 
/*    */   
/*    */   public void load(CompoundNBT tag) {
/* 37 */     super.load(tag);
/*    */     
/* 39 */     long id = tag.func_74763_f("trackedId");
/*    */     
/* 41 */     Optional<TrackedNPC> tracked = NPCWorldData.get().getTrackedMob(id);
/*    */     
/* 43 */     if (tracked.isPresent()) {
/* 44 */       setTracked(tracked.get());
/*    */     }
/*    */   }
/*    */   
/*    */   public TrackedNPC getTrackedNPC() {
/* 49 */     return this.tracked;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\poi\NTEventTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */