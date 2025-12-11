/*    */ package xyz.pixelatedw.mineminenomi.abilities.ito;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.BlackKnightEntity;
/*    */ 
/*    */ public class BlackKnightAbility extends Ability {
/* 24 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "black_knight", new Pair[] {
/* 25 */         (Pair)ImmutablePair.of("Creates a clone of the user made entirely out of compressed strings", null)
/*    */       });
/*    */   
/*    */   private static final int HOLD_TIME = 20000;
/*    */   private static final int MIN_COOLDOWN = 40;
/*    */   private static final int MAX_COOLDOWN = 6000;
/* 31 */   public static final AbilityCore<BlackKnightAbility> INSTANCE = (new AbilityCore.Builder("Black Knight", AbilityCategory.DEVIL_FRUITS, BlackKnightAbility::new))
/* 32 */     .addDescriptionLine(DESCRIPTION)
/* 33 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(40.0F, 6000.0F), ContinuousComponent.getTooltip()
/* 34 */       }).build();
/*    */   
/* 36 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(100, this::startContinuityEvent).addTickEvent(100, this::onTickEvent).addEndEvent(100, this::stopContinuityEvent);
/*    */   
/* 38 */   private BlackKnightEntity blackKnight = null;
/*    */   
/*    */   public BlackKnightAbility(AbilityCore<BlackKnightAbility> core) {
/* 41 */     super(core);
/*    */     
/* 43 */     this.isNew = true;
/* 44 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent });
/*    */     
/* 46 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 50 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 54 */     this.blackKnight = new BlackKnightEntity(entity.field_70170_p, entity);
/* 55 */     this.blackKnight.func_70012_b(entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), entity.field_70177_z, entity.field_70125_A);
/*    */     
/* 57 */     for (EquipmentSlotType slot : EquipmentSlotType.values()) {
/* 58 */       ItemStack stack = entity.func_184582_a(slot);
/* 59 */       this.blackKnight.func_184201_a(slot, stack);
/*    */     } 
/*    */     
/* 62 */     entity.field_70170_p.func_217376_c((Entity)this.blackKnight);
/*    */   }
/*    */   
/*    */   private void onTickEvent(LivingEntity entity, IAbility ability) {
/* 66 */     if (this.blackKnight == null || !this.blackKnight.func_70089_S()) {
/* 67 */       this.continuousComponent.stopContinuity(entity);
/*    */       return;
/*    */     } 
/*    */   }
/*    */   
/*    */   private void stopContinuityEvent(LivingEntity entity, IAbility ability) {
/* 73 */     if (this.blackKnight != null) {
/* 74 */       this.blackKnight.func_70106_y();
/*    */     }
/*    */     
/* 77 */     float cooldown = MathHelper.func_76131_a(this.continuousComponent.getContinueTime(), 40.0F, 6000.0F);
/* 78 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public BlackKnightEntity getBlackKnight() {
/* 83 */     return this.blackKnight;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ito\BlackKnightAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */