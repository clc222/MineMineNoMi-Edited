/*    */ package xyz.pixelatedw.mineminenomi.abilities.rokushiki;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChangeStatsComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ 
/*    */ public class ShiganAbility
/*    */   extends PunchAbility2 {
/* 29 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "shigan", new Pair[] {
/* 30 */         (Pair)ImmutablePair.of("The user thrusts their finger at the opponent to pierce them", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 160.0F;
/* 34 */   public static final AbilityCore<ShiganAbility> INSTANCE = (new AbilityCore.Builder("Shigan", AbilityCategory.RACIAL, ShiganAbility::new))
/* 35 */     .addDescriptionLine(DESCRIPTION)
/* 36 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(160.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 37 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 38 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 39 */       }).setUnlockCheck(ShiganAbility::canUnlock)
/* 40 */     .build();
/*    */   
/*    */   public ShiganAbility(AbilityCore<ShiganAbility> core) {
/* 43 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 48 */     entity.field_70170_p.func_184133_a(null, target.func_233580_cy_(), (SoundEvent)ModSounds.SHIGAN_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*    */     
/* 50 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 55 */     return entity -> (this.continuousComponent.isContinuous() && entity.func_184614_ca().func_190926_b());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 60 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isParallel() {
/* 65 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchDamage() {
/* 70 */     return 25.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 75 */     return 160.0F;
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity user) {
/* 79 */     IEntityStats props = EntityStatsCapability.get(user);
/* 80 */     boolean raceCheck = (props.isHuman() || DevilFruitCapability.get(user).hasDevilFruit(ModAbilities.HITO_HITO_NO_MI));
/* 81 */     return (raceCheck && props.getDoriki() >= 3000.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\rokushiki\ShiganAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */