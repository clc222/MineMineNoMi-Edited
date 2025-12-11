/*    */ package xyz.pixelatedw.mineminenomi.abilities.doku;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.doku.HydraProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class HydraAbility extends Ability {
/* 27 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "hydra", new Pair[] {
/* 28 */         (Pair)ImmutablePair.of("Launches a dragon made out of liquid poison at the opponent", null)
/*    */       });
/* 30 */   private static final ITextComponent NORMAL_NAME = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.hydra", "Hydra"));
/* 31 */   private static final ITextComponent VENOM_NAME = (ITextComponent)new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.hydra_venom", "Venom Hydra"));
/*    */   
/* 33 */   private static final ResourceLocation NORMAL_ICON = new ResourceLocation("mineminenomi", "textures/abilities/hydra.png");
/* 34 */   private static final ResourceLocation VENOM_ICON = new ResourceLocation("mineminenomi", "textures/abilities/hydra_venom.png");
/*    */   
/*    */   private static final int COOLDOWN = 100;
/*    */   
/* 38 */   public static final AbilityCore<HydraAbility> INSTANCE = (new AbilityCore.Builder("Hydra", AbilityCategory.DEVIL_FRUITS, HydraAbility::new))
/* 39 */     .addDescriptionLine(DESCRIPTION)
/* 40 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F)
/* 41 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 42 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 43 */     .setSourceElement(SourceElement.POISON)
/* 44 */     .build();
/*    */   
/* 46 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/* 47 */   private final AltModeComponent<Mode> altModeComponent = (new AltModeComponent((IAbility)this, Mode.class, Mode.NORMAL, true)).addChangeModeEvent(this::onAltModeChange);
/*    */   
/*    */   public HydraAbility(AbilityCore<HydraAbility> core) {
/* 50 */     super(core);
/*    */     
/* 52 */     this.isNew = true;
/* 53 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent, (AbilityComponent)this.altModeComponent });
/*    */     
/* 55 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 59 */     boolean isDemonForm = ((MorphInfo)ModMorphs.VENOM_DEMON.get()).isActive(entity);
/* 60 */     this.projectileComponent.shoot(entity, isDemonForm ? 4.0F : 2.0F, 0.0F);
/* 61 */     this.cooldownComponent.startCooldown(entity, 100.0F);
/*    */   }
/*    */   
/*    */   private HydraProjectile createProjectile(LivingEntity entity) {
/* 65 */     boolean isDemonForm = ((MorphInfo)ModMorphs.VENOM_DEMON.get()).isActive(entity);
/* 66 */     HydraProjectile proj = new HydraProjectile(entity.field_70170_p, entity, isDemonForm);
/* 67 */     return proj;
/*    */   }
/*    */   
/*    */   public void setNormalMode(LivingEntity entity) {
/* 71 */     this.altModeComponent.setMode(entity, Mode.NORMAL);
/*    */   }
/*    */   
/*    */   public void setVenomMode(LivingEntity entity) {
/* 75 */     this.altModeComponent.setMode(entity, Mode.VENOM);
/*    */   }
/*    */   
/*    */   private void onAltModeChange(LivingEntity entity, IAbility ability, Mode mode) {
/* 79 */     if (mode == Mode.VENOM) {
/* 80 */       setDisplayName(VENOM_NAME);
/* 81 */       setDisplayIcon(VENOM_ICON);
/*    */     }
/* 83 */     else if (mode == Mode.NORMAL) {
/* 84 */       setDisplayName(NORMAL_NAME);
/* 85 */       setDisplayIcon(NORMAL_ICON);
/*    */     } 
/*    */   }
/*    */   
/*    */   private enum Mode {
/* 90 */     NORMAL, VENOM;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\doku\HydraAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */