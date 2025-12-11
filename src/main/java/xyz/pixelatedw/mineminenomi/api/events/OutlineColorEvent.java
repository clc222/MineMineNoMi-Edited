/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraftforge.event.entity.EntityEvent;
/*    */ 
/*    */ public class OutlineColorEvent
/*    */   extends EntityEvent
/*    */ {
/* 10 */   private Color color = Color.WHITE;
/*    */ 
/*    */   
/*    */   public OutlineColorEvent(Entity entity) {
/* 14 */     super(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setColor(Color color) {
/* 19 */     this.color = color;
/*    */   }
/*    */ 
/*    */   
/*    */   public Color getColor() {
/* 24 */     return this.color;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\OutlineColorEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */