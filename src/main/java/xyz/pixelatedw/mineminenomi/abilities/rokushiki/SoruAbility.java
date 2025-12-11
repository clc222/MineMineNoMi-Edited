/*    */ package xyz.pixelatedw.mineminenomi.abilities.rokushiki;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.StackComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ 
/*    */ public class SoruAbility extends Ability {
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "soru", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("Allows the user to move at an extremely high speed in bursts", null)
/*    */       });
/*    */   
/*    */   private static final float LONG_COOLDOWN = 200.0F;
/*    */   private static final float SHORT_COOLDOWN = 10.0F;
/* 32 */   public static final AbilityCore<SoruAbility> INSTANCE = (new AbilityCore.Builder("Soru", AbilityCategory.RACIAL, SoruAbility::new))
/* 33 */     .addDescriptionLine(DESCRIPTION)
/* 34 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, AbilityHelper.createShortLongCooldownStat(10.0F, 200.0F)
/* 35 */       }).setUnlockCheck(SoruAbility::canUnlock)
/* 36 */     .build();
/*    */   
/* 38 */   private final StackComponent stackComponent = (new StackComponent((IAbility)this, 5)).addStackChangeEvent(this::onStacksChange);
/*    */   
/*    */   public SoruAbility(AbilityCore<SoruAbility> core) {
/* 41 */     super(core);
/*    */     
/* 43 */     this.isNew = true;
/* 44 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.stackComponent });
/* 45 */     setOGCD();
/*    */     
/* 47 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/* 48 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 52 */     Vector3d look = entity.func_70040_Z().func_216372_d(1.75D, ((entity.func_70040_Z()).field_72448_b >= 0.0D) ? 0.0D : 0.6D, 1.75D);
/*    */     
/* 54 */     if (entity.field_191988_bg < 0.0F) {
/* 55 */       look = look.func_216372_d(-1.0D, 1.0D, -1.0D);
/*    */     }
/*    */     
/* 58 */     if (entity.func_70090_H()) {
/* 59 */       look = look.func_216372_d(0.2D, 0.2D, 0.2D);
/*    */     }
/*    */     
/* 62 */     AbilityHelper.setDeltaMovement((Entity)entity, look);
/*    */     
/* 64 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.VANISH.get(), 5, 0, false, false));
/*    */     
/* 66 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.TELEPORT_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*    */     
/* 68 */     this.stackComponent.addStacks(entity, (IAbility)this, -1);
/*    */   }
/*    */   
/*    */   private void onStacksChange(LivingEntity entity, IAbility ability, int stacks) {
/* 72 */     if (stacks <= 0) {
/* 73 */       this.cooldownComponent.startCooldown(entity, 200.0F);
/* 74 */       this.stackComponent.revertStacksToDefault(entity, (IAbility)this);
/*    */     } 
/* 76 */     this.cooldownComponent.startCooldown(entity, 10.0F);
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity user) {
/* 80 */     IEntityStats props = EntityStatsCapability.get(user);
/* 81 */     boolean raceCheck = (props.isHuman() || DevilFruitCapability.get(user).hasDevilFruit(ModAbilities.HITO_HITO_NO_MI));
/* 82 */     return (raceCheck && props.getDoriki() >= 500.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\rokushiki\SoruAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */