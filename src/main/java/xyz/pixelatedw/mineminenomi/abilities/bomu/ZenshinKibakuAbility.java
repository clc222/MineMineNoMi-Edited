/*    */ package xyz.pixelatedw.mineminenomi.abilities.bomu;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.StackComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class ZenshinKibakuAbility extends Ability {
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "zenshin_kibaku", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("The user creates a massive explosion from their body", null)
/*    */       });
/*    */   
/*    */   private static final float CHARGE_TIME = 100.0F;
/*    */   private static final float MIN_COOLDOWN = 40.0F;
/*    */   private static final float MIAX_COOLDOWN = 140.0F;
/* 30 */   public static final AbilityCore<ZenshinKibakuAbility> INSTANCE = (new AbilityCore.Builder("Zenshin Kibaku", AbilityCategory.DEVIL_FRUITS, ZenshinKibakuAbility::new))
/* 31 */     .addDescriptionLine(DESCRIPTION)
/* 32 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(40.0F, 140.0F), ChargeComponent.getTooltip(100.0F)
/* 33 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 34 */     .setSourceElement(SourceElement.EXPLOSION)
/* 35 */     .build();
/*    */   
/*    */   private final ChargeComponent chargeComponent;
/*    */   
/*    */   private final StackComponent stackComponent;
/*    */   private int previousPower;
/*    */   
/*    */   public ZenshinKibakuAbility(AbilityCore<ZenshinKibakuAbility> core) {
/* 43 */     super(core); this.chargeComponent = (new ChargeComponent((IAbility)this, comp -> (comp.getChargePercentage() > 0.1D))).addTickEvent(100, this::tickChargeEvent).addEndEvent(100, this::endChargeEvent);
/*    */     this.stackComponent = new StackComponent((IAbility)this);
/* 45 */     this.isNew = true;
/* 46 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.stackComponent });
/*    */     
/* 48 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 52 */     this.chargeComponent.startCharging(entity, 100.0F);
/*    */   }
/*    */   
/*    */   private void tickChargeEvent(LivingEntity entity, IAbility ability) {
/* 56 */     int power = (int)(this.chargeComponent.getChargePercentage() * 10.0F);
/* 57 */     if (power != this.previousPower) {
/* 58 */       this.stackComponent.setStacks(entity, (IAbility)this, power);
/* 59 */       this.previousPower = power;
/*    */     } 
/*    */   }
/*    */   
/*    */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/* 64 */     float power = this.chargeComponent.getChargePercentage() * 10.0F;
/* 65 */     float cooldown = this.chargeComponent.getChargeTime() + 40.0F;
/*    */     
/* 67 */     if (!entity.field_70170_p.field_72995_K) {
/* 68 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.field_70170_p, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), power);
/* 69 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect((int)power));
/* 70 */       explosion.setStaticDamage(power * 12.0F);
/* 71 */       explosion.doExplosion();
/*    */     } 
/*    */     
/* 74 */     this.stackComponent.setStacks(entity, ability, 0);
/* 75 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\bomu\ZenshinKibakuAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */