/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ 
/*    */ public class ZoomAbility extends Ability {
/* 21 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "zoom", new Pair[] {
/* 22 */         (Pair)ImmutablePair.of("Zooms into the direction the user is looking, massively reduces inaccuracy when shooting projectiles.", null)
/*    */       });
/* 24 */   public static final AbilityCore<ZoomAbility> INSTANCE = (new AbilityCore.Builder("Zoom", AbilityCategory.EQUIPMENT, ZoomAbility::new))
/* 25 */     .addDescriptionLine(DESCRIPTION)
/* 26 */     .setUnlockCheck(ZoomAbility::canUnlock)
/* 27 */     .build();
/*    */   
/* 29 */   private final ContinuousComponent continuousComponent = new ContinuousComponent((IAbility)this, true);
/*    */   
/*    */   public ZoomAbility(AbilityCore<ZoomAbility> core) {
/* 32 */     super(core);
/*    */     
/* 34 */     this.isNew = true;
/* 35 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent });
/*    */     
/* 37 */     addCanUseCheck(this::canStartAbility);
/* 38 */     addUseEvent(this::onUseEvent);
/* 39 */     addTickEvent(this::onTickEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 43 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void onTickEvent(LivingEntity entity, IAbility ability) {
/* 47 */     if (isContinuous()) {
/* 48 */       ItemStack headStack = entity.func_184582_a(EquipmentSlotType.HEAD);
/* 49 */       if (headStack.func_190926_b() || headStack.func_77973_b() != ModArmors.SNIPER_GOGGLES.get()) {
/* 50 */         this.continuousComponent.stopContinuity(entity);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   private AbilityUseResult canStartAbility(LivingEntity entity, IAbility ability) {
/* 56 */     if (!ItemsHelper.hasItemInSlot(entity, EquipmentSlotType.HEAD, (Item)ModArmors.SNIPER_GOGGLES.get())) {
/* 57 */       return AbilityUseResult.fail((ITextComponent)ModI18n.ABILITY_MESSAGE_NEED_SNIPER_GOGGLES);
/*    */     }
/*    */     
/* 60 */     return AbilityUseResult.success();
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 64 */     ItemStack headStack = entity.func_184582_a(EquipmentSlotType.HEAD);
/* 65 */     if (!headStack.func_190926_b() && headStack.func_77973_b() == ModArmors.SNIPER_GOGGLES.get()) {
/* 66 */       return true;
/*    */     }
/*    */     
/* 69 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ZoomAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */