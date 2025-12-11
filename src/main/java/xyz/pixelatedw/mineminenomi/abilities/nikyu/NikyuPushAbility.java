/*    */ package xyz.pixelatedw.mineminenomi.abilities.nikyu;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.BlockRayTraceResult;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class NikyuPushAbility extends Ability {
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "nikyu_push", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("Allows the user to push themselves in the direction they're looking at incredible speed", null)
/*    */       });
/*    */   
/*    */   private static final float MAX_TELEPORT_DISTANCE = 100.0F;
/*    */   private static final int COOLDOWN = 140;
/* 32 */   public static final AbilityCore<NikyuPushAbility> INSTANCE = (new AbilityCore.Builder("Nikyu Push", AbilityCategory.DEVIL_FRUITS, NikyuPushAbility::new))
/* 33 */     .addDescriptionLine(DESCRIPTION)
/* 34 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(140.0F)
/* 35 */       }).build();
/*    */   
/* 37 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::onDamageTaken);
/*    */   
/*    */   private boolean hasFallDamage = true;
/*    */ 
/*    */   
/*    */   public NikyuPushAbility(AbilityCore<NikyuPushAbility> core) {
/* 43 */     super(core);
/*    */     
/* 45 */     this.isNew = true;
/* 46 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.damageTakenComponent });
/*    */     
/* 48 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/* 49 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/*    */     BlockPos blockpos;
/* 53 */     BlockRayTraceResult mop = WyHelper.rayTraceBlocks((Entity)entity, 100.0D);
/*    */ 
/*    */     
/* 56 */     if (mop == null || mop.func_216346_c() == RayTraceResult.Type.MISS) {
/* 57 */       blockpos = WyHelper.rayTraceBlockSafe(entity, 64.0F);
/*    */     } else {
/* 59 */       blockpos = WyHelper.getClearPositionForPlayer(entity, mop.func_216350_a());
/*    */     } 
/*    */     
/* 62 */     if (blockpos == null) {
/* 63 */       blockpos = WyHelper.rayTraceBlockSafe(entity, 64.0F);
/*    */     }
/*    */     
/* 66 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.PAD_HO_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*    */     
/* 68 */     entity.func_184210_p();
/* 69 */     entity.func_223102_j(blockpos.func_177958_n(), blockpos.func_177956_o(), blockpos.func_177952_p());
/*    */     
/* 71 */     this.hasFallDamage = false;
/*    */     
/* 73 */     this.cooldownComponent.startCooldown(entity, 140.0F);
/*    */   }
/*    */   
/*    */   private float onDamageTaken(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/* 77 */     if (!this.hasFallDamage && damageSource == DamageSource.field_76379_h) {
/* 78 */       this.hasFallDamage = true;
/* 79 */       return 0.0F;
/*    */     } 
/*    */     
/* 82 */     return damage;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\nikyu\NikyuPushAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */