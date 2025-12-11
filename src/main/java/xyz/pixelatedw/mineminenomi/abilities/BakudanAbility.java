/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.IntRange;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*    */ import xyz.pixelatedw.mineminenomi.entities.BombEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BakudanAbility extends Ability {
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "bakudan", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("Throws a bunch of bombs near the user, when enemies come in close proximity with these bombs they'll explode.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 200;
/*    */   private static final int RANGE = 15;
/* 32 */   public static final TargetsPredicate TARGET_TEST = (new TargetsPredicate()).testEnemyFaction().testSimpleInView();
/*    */   
/* 34 */   public static final AbilityCore<BakudanAbility> INSTANCE = (new AbilityCore.Builder("Bakudan", AbilityCategory.STYLE, BakudanAbility::new))
/* 35 */     .addDescriptionLine(DESCRIPTION)
/* 36 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), RangeComponent.getTooltip(15.0F, RangeComponent.RangeType.AOE)
/* 37 */       }).build();
/*    */   
/* 39 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/* 41 */   private IntRange amount = new IntRange(5, 7);
/*    */   
/*    */   public BakudanAbility(AbilityCore<BakudanAbility> core) {
/* 44 */     super(core);
/*    */     
/* 46 */     this.isNew = true;
/* 47 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.animationComponent });
/*    */     
/* 49 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 53 */     this.animationComponent.start(entity, ModAnimations.RAISE_RIGHT_ARM, 7);
/*    */     
/* 55 */     int amount = (int)WyHelper.randomWithRange(entity.func_70681_au(), this.amount.getMin().intValue(), this.amount.getMax().intValue());
/* 56 */     for (int i = 0; i < amount; i++) {
/* 57 */       BombEntity bomb = new BombEntity(entity.field_70170_p, entity);
/* 58 */       BlockPos pos = WyHelper.findValidGroundLocation((Entity)entity, entity.func_233580_cy_(), 15, 7);
/* 59 */       if (pos != null) {
/*    */ 
/*    */         
/* 62 */         bomb.func_70012_b(pos.func_177958_n(), (pos.func_177956_o() + 1.5F), pos.func_177952_p(), entity.func_70681_au().nextInt(360), 0.0F);
/* 63 */         entity.field_70170_p.func_217376_c((Entity)bomb);
/*    */       } 
/*    */     } 
/* 66 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*    */   }
/*    */   
/*    */   public void setAmount(int amount) {
/* 70 */     setAmount(amount, amount);
/*    */   }
/*    */   
/*    */   public void setAmount(int min, int max) {
/* 74 */     this.amount = new IntRange(min, max);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\BakudanAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */