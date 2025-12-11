/*    */ package xyz.pixelatedw.mineminenomi.abilities.bari;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ItemAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class BarrierbilityBatAbility extends ItemAbility2 {
/* 22 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "barrierbility_bat", new Pair[] {
/* 23 */         (Pair)ImmutablePair.of("Shapes the barriers in the form of a bat that the user can hold.", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 10.0F;
/* 27 */   public static final AbilityCore<BarrierbilityBatAbility> INSTANCE = (new AbilityCore.Builder("Barrierbility Bat", AbilityCategory.DEVIL_FRUITS, BarrierbilityBatAbility::new))
/* 28 */     .addDescriptionLine(DESCRIPTION)
/* 29 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(10.0F), ContinuousComponent.getTooltip()
/* 30 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/* 31 */     .setSourceType(new SourceType[] { SourceType.BLUNT
/* 32 */       }).build();
/*    */   
/*    */   public BarrierbilityBatAbility(AbilityCore<BarrierbilityBatAbility> core) {
/* 35 */     super(core);
/*    */     
/* 37 */     this.continuousComponent.addEndEvent(100, this::endContinuityEvent);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 41 */     this.cooldownComponent.startCooldown(entity, 10.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack createItemStack(LivingEntity entity) {
/* 46 */     ItemStack itemStack = new ItemStack((IItemProvider)ModWeapons.BARRIERBILITY_BAT.get());
/* 47 */     return itemStack;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\bari\BarrierbilityBatAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */