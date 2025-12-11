/*    */ package xyz.pixelatedw.mineminenomi.abilities.hie;
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
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class IceSaberAbility extends Ability {
/* 22 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "ice_saber", new Pair[] {
/* 23 */         (Pair)ImmutablePair.of("Creates a sharp blade made of compressed ice", null)
/*    */       });
/* 25 */   public static final AbilityCore<IceSaberAbility> INSTANCE = (new AbilityCore.Builder("Ice Saber", AbilityCategory.DEVIL_FRUITS, IceSaberAbility::new))
/* 26 */     .addDescriptionLine(DESCRIPTION)
/* 27 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 28 */     .setSourceElement(SourceElement.ICE)
/* 29 */     .setSourceType(new SourceType[] { SourceType.SLASH
/* 30 */       }).build();
/*    */   
/* 32 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::onContinuityStart).addEndEvent(this::onContinuityEnd);
/* 33 */   private final ItemSpawnComponent itemSpawnComponent = new ItemSpawnComponent((IAbility)this);
/*    */   
/*    */   public IceSaberAbility(AbilityCore<IceSaberAbility> core) {
/* 36 */     super(core);
/*    */     
/* 38 */     this.isNew = true;
/*    */     
/* 40 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.itemSpawnComponent });
/*    */     
/* 42 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 46 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/* 50 */     this.itemSpawnComponent.spawnItem(entity, new ItemStack((IItemProvider)ModWeapons.ICE_SABER.get()));
/*    */   }
/*    */   
/*    */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 54 */     this.itemSpawnComponent.despawnItems(entity);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\hie\IceSaberAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */