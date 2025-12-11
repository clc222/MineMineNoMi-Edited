/*    */ package xyz.pixelatedw.mineminenomi.abilities.sniper;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BowTriggerComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.HiNoToriBoshiProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.SniperProjectiles;
/*    */ 
/*    */ public class KaenBoshiAbility extends Ability {
/* 25 */   private static final ResourceLocation ICON = new ResourceLocation("mineminenomi", "textures/abilities/hi_no_tori_boshi.png");
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kaen_boshi", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("Fires a flaming pellet that sets the target on fire", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 100.0F;
/* 31 */   public static final AbilityCore<KaenBoshiAbility> INSTANCE = (new AbilityCore.Builder("Kaen Boshi", AbilityCategory.STYLE, KaenBoshiAbility::new))
/* 32 */     .setIcon(ICON)
/* 33 */     .addDescriptionLine(DESCRIPTION)
/* 34 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F), ContinuousComponent.getTooltip()
/* 35 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 36 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 37 */     .setSourceElement(SourceElement.FIRE)
/* 38 */     .setSourceType(new SourceType[] { SourceType.BULLET
/* 39 */       }).build();
/*    */   
/* 41 */   private final ContinuousComponent continuousComponent = new ContinuousComponent((IAbility)this);
/* 42 */   private final BowTriggerComponent bowTriggerComponent = (new BowTriggerComponent((IAbility)this)).addShootEvent(this::shoot);
/* 43 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public KaenBoshiAbility(AbilityCore<KaenBoshiAbility> core) {
/* 46 */     super(core);
/*    */     
/* 48 */     this.isNew = true;
/* 49 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.bowTriggerComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 51 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   public void onUseEvent(LivingEntity entity, IAbility ability) {
/* 55 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   public boolean shoot(LivingEntity entity, IAbility ability) {
/* 59 */     if (this.continuousComponent.isContinuous()) {
/* 60 */       boolean isHitScan = HissatsuAbility.checkHitScan(entity);
/* 61 */       this.projectileComponent.setHitScan(isHitScan);
/* 62 */       this.projectileComponent.shoot(entity, 4.0F, 1.0F);
/* 63 */       this.continuousComponent.stopContinuity(entity);
/* 64 */       this.cooldownComponent.startCooldown(entity, 100.0F);
/* 65 */       return true;
/*    */     } 
/* 67 */     return false;
/*    */   }
/*    */   
/*    */   public HiNoToriBoshiProjectile createProjectile(LivingEntity entity) {
/* 71 */     HiNoToriBoshiProjectile proj = new HiNoToriBoshiProjectile((EntityType)SniperProjectiles.KAEN_BOSHI.get(), entity.field_70170_p, entity, INSTANCE);
/* 72 */     proj.func_70107_b(entity.func_226277_ct_(), entity.func_226278_cu_() + entity.func_70047_e(), entity.func_226281_cx_());
/* 73 */     proj.setDamage(10.0F);
/* 74 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\sniper\KaenBoshiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */