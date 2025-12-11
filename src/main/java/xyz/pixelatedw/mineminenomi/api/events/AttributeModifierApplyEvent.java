/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifierManager;
/*    */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ 
/*    */ public class AttributeModifierApplyEvent
/*    */   extends LivingEvent
/*    */ {
/*    */   private AttributeModifierManager map;
/*    */   
/*    */   public AttributeModifierApplyEvent(LivingEntity entity, AttributeModifierManager map, int amplifier) {
/* 15 */     super(entity);
/* 16 */     this.map = map;
/*    */   }
/*    */ 
/*    */   
/*    */   public ModifiableAttributeInstance getAttribute(Attribute attr) {
/* 21 */     return this.map.func_233779_a_(attr);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\AttributeModifierApplyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */