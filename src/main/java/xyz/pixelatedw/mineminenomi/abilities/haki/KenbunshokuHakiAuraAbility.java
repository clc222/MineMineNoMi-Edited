/*    */ package xyz.pixelatedw.mineminenomi.abilities.haki;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ 
/*    */ public class KenbunshokuHakiAuraAbility extends Ability {
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kenbunshoku_haki_aura", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("Uses Observation Haki to see the auras of all nearby creatures, differentiated by colors.", null)
/*    */       });
/*    */   
/*    */   private static final int HOLD_TIME = 2400;
/*    */   private static final int MIN_COOLDOWN = 60;
/* 29 */   public static final AbilityCore<KenbunshokuHakiAuraAbility> INSTANCE = (new AbilityCore.Builder("Kenbunshoku Haki: Aura", AbilityCategory.HAKI, KenbunshokuHakiAuraAbility::new))
/* 30 */     .addDescriptionLine(DESCRIPTION)
/* 31 */     .setUnlockCheck(KenbunshokuHakiAuraAbility::canUnlock)
/* 32 */     .build();
/*    */   
/* 34 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::startContinuityEvent).addEndEvent(this::endContinuityEvent);
/*    */   
/*    */   public KenbunshokuHakiAuraAbility(AbilityCore<KenbunshokuHakiAuraAbility> core) {
/* 37 */     super(core);
/*    */     
/* 39 */     this.isNew = true;
/* 40 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent });
/*    */     
/* 42 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 46 */     this.continuousComponent.triggerContinuity(entity, 2400.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 53 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.KENBUNSHOKU_HAKI_ON_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 57 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.KENBUNSHOKU_HAKI_OFF.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*    */     
/* 59 */     float cooldown = Math.max(60.0F, this.continuousComponent.getContinueTime());
/* 60 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity user) {
/* 64 */     IHakiData props = HakiDataCapability.get(user);
/* 65 */     IEntityStats statsProps = EntityStatsCapability.get(user);
/*    */     
/* 67 */     return (statsProps.getDoriki() > 1500.0D && props.getKenbunshokuHakiExp() > HakiHelper.getKenbunshokuAuraExpNeeded(user));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\haki\KenbunshokuHakiAuraAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */