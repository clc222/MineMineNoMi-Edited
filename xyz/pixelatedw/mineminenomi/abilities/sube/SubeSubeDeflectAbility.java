/*    */ package xyz.pixelatedw.mineminenomi.abilities.sube;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.events.FactionEvents;
/*    */ 
/*    */ public class SubeSubeDeflectAbility extends Ability {
/* 24 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "sube_sube_deflect", new Pair[] {
/* 25 */         (Pair)ImmutablePair.of("Temporarily makes the user immune to physical attacks, either by weapon or by hand to hand combat, as those attacks would just slip off their body.", null)
/*    */       });
/* 27 */   private static final List<String> BLOCK_SOURCES = Arrays.asList(new String[] { "mob", "player", "arrow", "sting", "trident", "thrown", "sweetBerryBush", "cactus" });
/*    */   
/*    */   private static final int HOLD_TIME = 240;
/*    */   
/*    */   private static final int MIN_COOLDOWN = 80;
/*    */   private static final int MAX_COOLDOWN = 200;
/* 33 */   public static final AbilityCore<SubeSubeDeflectAbility> INSTANCE = (new AbilityCore.Builder("Sube Sube Deflect", AbilityCategory.DEVIL_FRUITS, SubeSubeDeflectAbility::new))
/* 34 */     .addDescriptionLine(DESCRIPTION)
/* 35 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(80.0F, 200.0F), ContinuousComponent.getTooltip(240.0F)
/* 36 */       }).build();
/*    */   
/* 38 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addEndEvent(this::endContinuityEvent);
/* 39 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::damageTakenEvent);
/*    */   
/*    */   public SubeSubeDeflectAbility(AbilityCore<SubeSubeDeflectAbility> core) {
/* 42 */     super(core);
/*    */     
/* 44 */     this.isNew = true;
/* 45 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.damageTakenComponent });
/*    */     
/* 47 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 51 */     this.continuousComponent.triggerContinuity(entity, 240.0F);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 55 */     float cooldown = 80.0F + this.continuousComponent.getContinueTime() / 2.0F;
/*    */     
/* 57 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*    */   }
/*    */   
/*    */   private float damageTakenEvent(LivingEntity user, IAbility ability, DamageSource damageSource, float damage) {
/* 61 */     if (!isContinuous()) {
/* 62 */       return damage;
/*    */     }
/*    */     
/* 65 */     if (FactionEvents.isDirectHit(damageSource))
/* 66 */       return 0.0F; 
/* 67 */     if (damageSource.func_76364_f() instanceof AbilityProjectileEntity && ((AbilityProjectileEntity)damageSource.func_76364_f()).isPhysical())
/* 68 */       return 0.0F; 
/* 69 */     if (BLOCK_SOURCES.contains(damageSource.func_76355_l())) {
/* 70 */       return 0.0F;
/*    */     }
/* 72 */     return damage;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\sube\SubeSubeDeflectAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */