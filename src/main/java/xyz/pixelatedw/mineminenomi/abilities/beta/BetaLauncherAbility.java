/*    */ package xyz.pixelatedw.mineminenomi.abilities.beta;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.RepeaterAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.beta.StickyProjectile;
/*    */ 
/*    */ public class BetaLauncherAbility extends RepeaterAbility2 {
/* 21 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "beta_launcher", new Pair[] {
/* 22 */         (Pair)ImmutablePair.of("Shoots sticky Mucus which cause explosions on contact when combined with fire (also holding Flint & Steel).", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 180;
/*    */   private static final int TRIGGERS = 6;
/*    */   private static final int INTERVAL = 3;
/* 28 */   public static final AbilityCore<BetaLauncherAbility> INSTANCE = (new AbilityCore.Builder("Beta Launcher", AbilityCategory.DEVIL_FRUITS, BetaLauncherAbility::new))
/* 29 */     .addDescriptionLine(DESCRIPTION)
/* 30 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(180.0F)
/* 31 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 32 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 33 */     .setSourceElement(SourceElement.SLIME)
/* 34 */     .build();
/*    */   
/*    */   public BetaLauncherAbility(AbilityCore<BetaLauncherAbility> core) {
/* 37 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getProjectileSpeed() {
/* 42 */     return 1.5F;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxTriggers() {
/* 47 */     return 6;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTriggerInterval() {
/* 52 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getRepeaterCooldown() {
/* 57 */     return 180.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public StickyProjectile getProjectileFactory(LivingEntity entity) {
/* 62 */     StickyProjectile proj = new StickyProjectile(entity.field_70170_p, entity, (Ability)this);
/* 63 */     if (entity.func_184614_ca().func_77973_b() == Items.field_151033_d) {
/* 64 */       proj.setDamage(8.0F);
/* 65 */       proj.setCauseExplosion();
/*    */     } 
/* 67 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\beta\BetaLauncherAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */