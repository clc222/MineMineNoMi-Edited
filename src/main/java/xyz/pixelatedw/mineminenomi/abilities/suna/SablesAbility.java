/*    */ package xyz.pixelatedw.mineminenomi.abilities.suna;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.TornadoEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class SablesAbility extends Ability {
/*    */   private static final float COOLDOWN_BONUS = 0.8F;
/* 24 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "sables", new Pair[] {
/* 25 */         (Pair)ImmutablePair.of("The user launches a compressed sandstorm at the opponent, which sends them flying.", null), 
/* 26 */         (Pair)ImmutablePair.of("While in a desert the cooldown of this ability is reduced by %s.", new Object[] {
/* 27 */             "§a" + Math.round(19.999998F) + "%§r"
/*    */           })
/*    */       });
/*    */   private static final int COOLDOWN = 360;
/*    */   private static final int HOLD_TIME = 120;
/* 32 */   public static final AbilityCore<SablesAbility> INSTANCE = (new AbilityCore.Builder("Sables", AbilityCategory.DEVIL_FRUITS, SablesAbility::new))
/* 33 */     .addDescriptionLine(DESCRIPTION)
/* 34 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(360.0F), ContinuousComponent.getTooltip(120.0F)
/* 35 */       }).build();
/*    */   
/* 37 */   private final ContinuousComponent continuityComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*    */   
/* 39 */   private TornadoEntity proj = null;
/*    */   
/*    */   public SablesAbility(AbilityCore<SablesAbility> core) {
/* 42 */     super(core);
/*    */     
/* 44 */     this.isNew = true;
/* 45 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuityComponent });
/*    */     
/* 47 */     addCanUseCheck(AbilityHelper::requiresDryUser);
/*    */     
/* 49 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 53 */     this.continuityComponent.triggerContinuity(entity, 120.0F);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 57 */     this.proj = null;
/*    */   }
/*    */   
/*    */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 61 */     RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)entity, 100.0D);
/*    */     
/* 63 */     if (this.proj == null) {
/* 64 */       this.proj = new TornadoEntity(entity.field_70170_p, entity);
/* 65 */       this.proj.setMaxLife(300);
/* 66 */       this.proj.setSize(30.0F);
/* 67 */       this.proj.setSpeed(-2.0F);
/* 68 */       this.proj.func_70107_b((mop.func_216347_e()).field_72450_a, (mop.func_216347_e()).field_72448_b, (mop.func_216347_e()).field_72449_c);
/* 69 */       entity.field_70170_p.func_217376_c((Entity)this.proj);
/*    */     } 
/*    */     
/* 72 */     if (!this.proj.func_70089_S() || this.proj == null) {
/* 73 */       this.continuityComponent.stopContinuity(entity);
/*    */       
/*    */       return;
/*    */     } 
/* 77 */     double distance = Math.sqrt(this.proj.func_195048_a(mop.func_216347_e()));
/* 78 */     if (mop.func_216346_c().equals(RayTraceResult.Type.BLOCK) && distance < 100.0D) {
/* 79 */       this.proj.setVector(mop.func_216347_e().func_72441_c(0.0D, 10.0D, 0.0D));
/*    */     }
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 84 */     if (this.proj != null && this.proj.func_70089_S()) {
/* 85 */       this.proj.func_70106_y();
/*    */     }
/*    */     
/* 88 */     this.cooldownComponent.getBonusManager().removeBonus(SunaHelper.DESERT_COOLDOWN_BONUS);
/* 89 */     if (SunaHelper.isFruitBoosted(entity)) {
/* 90 */       this.cooldownComponent.getBonusManager().addBonus(SunaHelper.DESERT_COOLDOWN_BONUS, "Desert Cooldown Bonus", BonusOperation.MUL, 0.8F);
/*    */     }
/*    */     
/* 93 */     this.cooldownComponent.startCooldown(entity, 360.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\suna\SablesAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */