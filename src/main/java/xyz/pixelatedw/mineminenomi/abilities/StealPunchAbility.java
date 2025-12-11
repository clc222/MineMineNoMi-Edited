/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.item.ItemEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ 
/*    */ public class StealPunchAbility
/*    */   extends PunchAbility2 {
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "steal_punch", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("Hits the enemy and throws their held item away from them.", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 160.0F;
/* 31 */   public static final AbilityCore<StealPunchAbility> INSTANCE = (new AbilityCore.Builder("Steal Punch", AbilityCategory.STYLE, StealPunchAbility::new))
/* 32 */     .addDescriptionLine(DESCRIPTION)
/* 33 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(160.0F), ContinuousComponent.getTooltip()
/* 34 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 35 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 36 */       }).build();
/*    */   
/*    */   public StealPunchAbility(AbilityCore<StealPunchAbility> core) {
/* 39 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 44 */     ItemStack droppedStack = ItemStack.field_190927_a;
/*    */     
/* 46 */     if (!target.func_184614_ca().func_190926_b()) {
/* 47 */       droppedStack = target.func_184614_ca();
/*    */     }
/* 49 */     else if (!target.func_184592_cb().func_190926_b()) {
/* 50 */       droppedStack = target.func_184592_cb();
/*    */     }
/* 52 */     else if (target.func_213343_cS() > 0.0F) {
/* 53 */       for (ItemStack armorStack : target.func_184193_aE()) {
/* 54 */         if (armorStack != null && !armorStack.func_190926_b() && target.func_70681_au().nextDouble() < 0.35D) {
/* 55 */           droppedStack = armorStack;
/*    */           
/*    */           break;
/*    */         } 
/*    */       } 
/*    */     } 
/* 61 */     if (droppedStack != null && !droppedStack.func_190926_b()) {
/* 62 */       Vector3d lookVec = target.func_70040_Z();
/* 63 */       entity.func_70107_b(target.func_226277_ct_() + lookVec.field_72450_a, target.func_226278_cu_(), target.func_226281_cx_() + lookVec.field_72449_c);
/*    */       
/* 65 */       double d0 = target.func_226280_cw_() - 0.30000001192092896D;
/* 66 */       ItemEntity itementity = new ItemEntity(target.field_70170_p, target.func_226277_ct_(), d0, target.func_226281_cx_(), droppedStack.func_77946_l());
/* 67 */       itementity.func_174867_a(40);
/* 68 */       itementity.func_200216_c(target.func_110124_au());
/*    */       
/* 70 */       float f = target.func_70681_au().nextFloat() * 1.5F;
/* 71 */       float f1 = target.func_70681_au().nextFloat() * 6.2831855F;
/* 72 */       AbilityHelper.setDeltaMovement((Entity)itementity, (-MathHelper.func_76126_a(f1) * f), 0.75D, (MathHelper.func_76134_b(f1) * f));
/* 73 */       target.field_70170_p.func_217376_c((Entity)itementity);
/* 74 */       droppedStack.func_190918_g(droppedStack.func_190916_E());
/*    */     } 
/*    */     
/* 77 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 82 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 87 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isParallel() {
/* 92 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 97 */     return 160.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\StealPunchAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */