/*    */ package xyz.pixelatedw.mineminenomi.abilities.magu;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SkinOverlayComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.magu.DaiFunkaProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ 
/*    */ public class DaiFunkaAbility
/*    */   extends Ability {
/* 30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "dai_funka", new Pair[] {
/* 31 */         (Pair)ImmutablePair.of("Transforms the user's fist into pure magma before expanding and throwing it forward", null)
/*    */       });
/*    */   
/*    */   private static final float CHARGE_TIME = 20.0F;
/*    */   private static final float COOLDOWN = 500.0F;
/* 36 */   public static final AbilityCore<DaiFunkaAbility> INSTANCE = (new AbilityCore.Builder("Dai Funka", AbilityCategory.DEVIL_FRUITS, DaiFunkaAbility::new))
/* 37 */     .addDescriptionLine(DESCRIPTION)
/* 38 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(500.0F), ChargeComponent.getTooltip(20.0F)
/* 39 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 40 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 41 */     .setSourceElement(SourceElement.MAGMA)
/* 42 */     .build();
/*    */   
/* 44 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::onChargeStart).addEndEvent(this::onChargeEnd);
/* 45 */   private final SkinOverlayComponent skinOverlayComponent = new SkinOverlayComponent((IAbility)this, OVERLAY, new AbilityOverlay[0]);
/* 46 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/* 48 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay.Builder()).setOverlayPart(AbilityOverlay.OverlayPart.LIMB).setTexture(ModResources.DOKU_COATING).setColor(new Color(160, 0, 0)).build();
/*    */   
/*    */   public DaiFunkaAbility(AbilityCore<DaiFunkaAbility> core) {
/* 51 */     super(core);
/*    */     
/* 53 */     this.isNew = true;
/*    */     
/* 55 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.skinOverlayComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 57 */     addUseEvent(this::onUse);
/*    */   }
/*    */   
/*    */   private void onUse(LivingEntity entity, IAbility ability) {
/* 61 */     this.chargeComponent.startCharging(entity, 20.0F);
/*    */   }
/*    */   
/*    */   private void onChargeStart(LivingEntity entity, IAbility ability) {
/* 65 */     this.skinOverlayComponent.showAll(entity);
/*    */   }
/*    */   
/*    */   private void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 69 */     this.projectileComponent.shoot(entity, 3.0F, 1.0F);
/*    */     
/* 71 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.MAGU_SFX.get(), SoundCategory.PLAYERS, 3.0F, 1.0F);
/*    */     
/* 73 */     this.skinOverlayComponent.hideAll(entity);
/*    */     
/* 75 */     this.cooldownComponent.startCooldown(entity, 500.0F);
/*    */   }
/*    */   
/*    */   private DaiFunkaProjectile createProjectile(LivingEntity entity) {
/* 79 */     return new DaiFunkaProjectile(entity.field_70170_p, entity, (IAbility)this);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\magu\DaiFunkaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */