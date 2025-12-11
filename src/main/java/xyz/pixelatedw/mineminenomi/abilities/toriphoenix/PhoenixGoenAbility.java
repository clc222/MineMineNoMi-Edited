/*    */ package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.RepeaterAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RequireMorphComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.toriphoenix.PhoenixGoenProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ 
/*    */ public class PhoenixGoenAbility extends RepeaterAbility2 {
/* 21 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "phoenix_goen", new Pair[] {
/* 22 */         (Pair)ImmutablePair.of("Launches high speed blue flames while midair.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 160;
/*    */   private static final int TRIGGERS = 5;
/*    */   private static final int INTERVAL = 4;
/* 28 */   public static final AbilityCore<PhoenixGoenAbility> INSTANCE = (new AbilityCore.Builder("Phoenix Goen", AbilityCategory.DEVIL_FRUITS, PhoenixGoenAbility::new))
/* 29 */     .addDescriptionLine(DESCRIPTION)
/* 30 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, RequireMorphComponent.getTooltip()
/* 31 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(160.0F)
/* 32 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 33 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 34 */     .build();
/*    */   
/* 36 */   private final RequireMorphComponent requireMorphComponent = new RequireMorphComponent((IAbility)this, (MorphInfo)ModMorphs.PHOENIX_ASSAULT.get(), new MorphInfo[] { (MorphInfo)ModMorphs.PHOENIX_FLY.get() });
/*    */   
/*    */   public PhoenixGoenAbility(AbilityCore<PhoenixGoenAbility> core) {
/* 39 */     super(core);
/*    */     
/* 41 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.requireMorphComponent });
/*    */     
/* 43 */     addCanUseCheck(AbilityHelper::requiresInAir);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxTriggers() {
/* 48 */     return 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTriggerInterval() {
/* 53 */     return 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getRepeaterCooldown() {
/* 58 */     return 160.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getProjectileSpeed() {
/* 63 */     return 2.5F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getProjectileSpread() {
/* 68 */     return 5.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public PhoenixGoenProjectile getProjectileFactory(LivingEntity entity) {
/* 73 */     PhoenixGoenProjectile proj = new PhoenixGoenProjectile(entity.field_70170_p, entity, (Ability)this, entity.func_70040_Z());
/* 74 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\toriphoenix\PhoenixGoenAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */