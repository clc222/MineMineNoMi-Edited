/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AbilityAttributeModifier
/*    */   extends AttributeModifier
/*    */ {
/*    */   private AbilityCore core;
/*    */   
/*    */   public AbilityAttributeModifier(UUID uuid, Ability ability, String name, double amount, AttributeModifier.Operation operation) {
/* 17 */     this(uuid, ability.getCore(), name, amount, operation);
/*    */   }
/*    */   
/*    */   public AbilityAttributeModifier(UUID uuid, AbilityCore ability, String name, double amount, AttributeModifier.Operation operation) {
/* 21 */     super(uuid, name, amount, operation);
/* 22 */     this.core = ability;
/*    */   }
/*    */   
/*    */   public AbilityCore getAbilityCore() {
/* 26 */     return this.core;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\AbilityAttributeModifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */