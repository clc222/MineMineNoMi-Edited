/*    */ package xyz.pixelatedw.mineminenomi.abilities.wara;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class WarabideSwordAbility extends ItemAbility2 {
/* 21 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "warabide_sword", new Pair[] {
/* 22 */         (Pair)ImmutablePair.of("Creates a sword that can shoot long thin straw-like projectiles.", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 10.0F;
/* 26 */   public static final AbilityCore<WarabideSwordAbility> INSTANCE = (new AbilityCore.Builder("Warabide Sword", AbilityCategory.DEVIL_FRUITS, WarabideSwordAbility::new))
/* 27 */     .addDescriptionLine(DESCRIPTION)
/* 28 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(10.0F), ContinuousComponent.getTooltip()
/* 29 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/* 30 */     .build();
/*    */   
/*    */   public WarabideSwordAbility(AbilityCore<WarabideSwordAbility> core) {
/* 33 */     super(core);
/*    */     
/* 35 */     this.continuousComponent.addEndEvent(100, this::endContinuityEvent);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 39 */     this.cooldownComponent.startCooldown(entity, 10.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack createItemStack(LivingEntity entity) {
/* 44 */     ItemStack itemStack = new ItemStack((IItemProvider)ModWeapons.WARABIDE_SWORD.get());
/* 45 */     return itemStack;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\wara\WarabideSwordAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */