/*    */ package xyz.pixelatedw.mineminenomi.data.entity.kairosekicoating;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SKairosekiCoatingPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class KairosekiCoatingBase
/*    */   implements IKairosekiCoating
/*    */ {
/*    */   public static final int MAX_COATING_AMOUNT = 5;
/*    */   private Entity owner;
/*    */   private int coatingLevel;
/*    */   
/*    */   public void setOwner(Entity entity) {
/* 16 */     this.owner = entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isFullyCoated() {
/* 21 */     return (this.coatingLevel >= 5);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCoatingLevel() {
/* 26 */     return this.coatingLevel;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean addCoatingLevel(int levels) {
/* 31 */     if (levels > 0 && isFullyCoated())
/* 32 */       return false; 
/* 33 */     if (levels < 0 && getCoatingLevel() == 0) {
/* 34 */       return false;
/*    */     }
/*    */     
/* 37 */     setCoatingLevel(this.coatingLevel + levels);
/* 38 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCoatingLevel(int level) {
/* 43 */     this.coatingLevel = MathHelper.func_76125_a(level, 0, 5);
/* 44 */     if (this.owner != null && !this.owner.field_70170_p.field_72995_K)
/* 45 */       WyNetwork.sendToAllTracking(new SKairosekiCoatingPacket(this.owner.func_145782_y(), this.coatingLevel), this.owner); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\kairosekicoating\KairosekiCoatingBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */