/*    */ package xyz.pixelatedw.mineminenomi.abilities.bomu;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class KickBombAbility extends Ability {
/* 21 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kick_bomb", new Pair[] {
/* 22 */         (Pair)ImmutablePair.of("The user kicks the ground, detonating their leg on impact", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 400.0F;
/* 26 */   public static final AbilityCore<KickBombAbility> INSTANCE = (new AbilityCore.Builder("Kick Bomb", AbilityCategory.DEVIL_FRUITS, KickBombAbility::new))
/* 27 */     .addDescriptionLine(DESCRIPTION)
/* 28 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(400.0F)
/* 29 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 30 */     .setSourceElement(SourceElement.EXPLOSION)
/* 31 */     .build();
/*    */   
/*    */   public KickBombAbility(AbilityCore<KickBombAbility> core) {
/* 34 */     super(core);
/*    */     
/* 36 */     this.isNew = true;
/*    */     
/* 38 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 42 */     if (!entity.field_70170_p.field_72995_K) {
/* 43 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.field_70170_p, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), 7.0F);
/* 44 */       explosion.setStaticDamage(70.0F);
/* 45 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(8));
/* 46 */       explosion.doExplosion();
/*    */     } 
/*    */     
/* 49 */     this.cooldownComponent.startCooldown(entity, 400.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\bomu\KickBombAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */