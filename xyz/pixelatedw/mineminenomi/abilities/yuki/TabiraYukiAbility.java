/*    */ package xyz.pixelatedw.mineminenomi.abilities.yuki;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ItemSpawnComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class TabiraYukiAbility extends Ability {
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "tabira_yuki", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("The user creates a sword made of solid hardened snow. Will inflict %s on hit.", new Object[] { ModEffects.FROSTBITE })
/*    */       });
/* 26 */   public static final AbilityCore<TabiraYukiAbility> INSTANCE = (new AbilityCore.Builder("Tabira Yuki", AbilityCategory.DEVIL_FRUITS, TabiraYukiAbility::new))
/* 27 */     .addDescriptionLine(DESCRIPTION)
/* 28 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 29 */     .setSourceType(new SourceType[] { SourceType.SLASH
/* 30 */       }).setSourceElement(SourceElement.ICE)
/* 31 */     .build();
/*    */   
/* 33 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::onContinuityStart).addEndEvent(this::onContinuityEnd);
/* 34 */   private final ItemSpawnComponent itemSpawnComponent = new ItemSpawnComponent((IAbility)this);
/*    */   
/*    */   public TabiraYukiAbility(AbilityCore<TabiraYukiAbility> core) {
/* 37 */     super(core);
/*    */     
/* 39 */     this.isNew = true;
/*    */     
/* 41 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.itemSpawnComponent });
/*    */     
/* 43 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 47 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/* 51 */     this.itemSpawnComponent.spawnItem(entity, new ItemStack((IItemProvider)ModWeapons.TABIRA_YUKI.get()));
/*    */   }
/*    */   
/*    */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 55 */     this.itemSpawnComponent.despawnItems(entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\yuki\TabiraYukiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */