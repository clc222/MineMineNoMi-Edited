/*    */ package xyz.pixelatedw.mineminenomi.abilities.bari;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.LivingEntity;
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
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ 
/*    */ public class BariBariNoPistolAbility
/*    */   extends PunchAbility2
/*    */ {
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "bari_bari_no_pistol", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("Shapes a barrier aroud the user's fist, which greatly increases the power of a punch.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 100;
/* 28 */   public static final AbilityCore<BariBariNoPistolAbility> INSTANCE = (new AbilityCore.Builder("Bari Bari no Pistol", AbilityCategory.DEVIL_FRUITS, BariBariNoPistolAbility::new))
/* 29 */     .addDescriptionLine(DESCRIPTION)
/* 30 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F), ContinuousComponent.getTooltip(), ChangeStatsComponent.getTooltip()
/* 31 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 32 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 33 */       }).build();
/*    */   
/*    */   public BariBariNoPistolAbility(AbilityCore<BariBariNoPistolAbility> core) {
/* 36 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchDamage() {
/* 41 */     return 12.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 46 */     return 100.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 51 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 56 */     return entity -> this.continuousComponent.isContinuous();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 61 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\bari\BariBariNoPistolAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */