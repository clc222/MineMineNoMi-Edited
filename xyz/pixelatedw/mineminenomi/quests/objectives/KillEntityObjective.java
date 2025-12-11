/*    */ package xyz.pixelatedw.mineminenomi.quests.objectives;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IKillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KillEntityObjective
/*    */   extends Objective
/*    */   implements IKillEntityObjective
/*    */ {
/*    */   protected ICheckKill killEvent;
/*    */   
/*    */   public KillEntityObjective(String title, int count) {
/* 21 */     this(title, count, (ICheckKill)null);
/*    */   }
/*    */ 
/*    */   
/*    */   public KillEntityObjective(String title, int count, ICheckKill check) {
/* 26 */     super(title); this.killEvent = ((player, target, source) -> { ModifiableAttributeInstance attackAttribute = target.func_233645_dx_().func_233779_a_(Attributes.field_233823_f_); return (attackAttribute != null && attackAttribute.func_111126_e() > 0.0D);
/* 27 */       }); setMaxProgress(count);
/* 28 */     if (check != null) {
/* 29 */       this.killEvent = check;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source) {
/* 35 */     return this.killEvent.test(player, target, source);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface ICheckKill
/*    */   {
/*    */     default ICheckKill and(ICheckKill check) {
/* 45 */       return (player, target, source) -> 
/* 46 */         (test(player, target, source) && check.test(player, target, source));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     default ICheckKill or(ICheckKill check) {
/* 52 */       return (player, target, source) -> 
/* 53 */         (test(player, target, source) || check.test(player, target, source));
/*    */     }
/*    */     
/*    */     boolean test(PlayerEntity param1PlayerEntity, LivingEntity param1LivingEntity, DamageSource param1DamageSource);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\objectives\KillEntityObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */