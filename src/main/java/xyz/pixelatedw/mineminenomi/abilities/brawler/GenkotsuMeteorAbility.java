/*     */ package xyz.pixelatedw.mineminenomi.abilities.brawler;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.Style;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ public class GenkotsuMeteorAbility extends Ability {
/*  44 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "genkotsu_meteor", new Pair[] {
/*  45 */         (Pair)ImmutablePair.of("Throws a cannon ball from the user's hand or multiple cannon balls in §aRyuseigun§r mode", null)
/*     */       });
/*  47 */   private static final TranslationTextComponent GENKOTSU_MODE_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.genkotsu_meteor", "Genkotsu Meteor"));
/*  48 */   private static final TranslationTextComponent RYUSEIGUN_MODE_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.genkotsu_meteor_ryuseigun", "Ryuseigun"));
/*     */   
/*  50 */   private static final ResourceLocation GENKOTSU_METEOR_NORMAL_ICON = new ResourceLocation("mineminenomi", "textures/abilities/genkotsu_meteor.png");
/*  51 */   private static final ResourceLocation GENKOTSU_METEOR_RYUSEIGUN_ICON = new ResourceLocation("mineminenomi", "textures/abilities/genkotsu_meteor_ryuseigun.png");
/*     */   
/*     */   private static final float NORMAL_COOLDOWN = 60.0F;
/*     */   
/*     */   private static final float RYUSEIGUN_COOLDOWN = 200.0F;
/*     */   private static final float DAMAGE = 15.0F;
/*  57 */   public static final AbilityCore<GenkotsuMeteorAbility> INSTANCE = (new AbilityCore.Builder("Genkotsu Meteor", AbilityCategory.STYLE, GenkotsuMeteorAbility::new))
/*  58 */     .addDescriptionLine(DESCRIPTION)
/*  59 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] {
/*     */         
/*  61 */         AbilityDescriptionLine.NEW_LINE, (e, a) -> GENKOTSU_MODE_NAME.func_230532_e_().func_230530_a_(Style.field_240709_b_.func_240712_a_(TextFormatting.GREEN)), CooldownComponent.getTooltip(60.0F), 
/*  62 */         DealDamageComponent.getTooltip(15.0F)
/*  63 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] {
/*     */         
/*  65 */         AbilityDescriptionLine.NEW_LINE, (e, a) -> RYUSEIGUN_MODE_NAME.func_230532_e_().func_230530_a_(Style.field_240709_b_.func_240712_a_(TextFormatting.GREEN)), CooldownComponent.getTooltip(200.0F), 
/*  66 */         DealDamageComponent.getTooltip(15.0F)
/*  67 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/*  68 */     .setSourceType(new SourceType[] { SourceType.PHYSICAL, SourceType.INDIRECT
/*  69 */       }).setUnlockCheck(GenkotsuMeteorAbility::canUnlock)
/*  70 */     .build();
/*     */   
/*  72 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::onContinuityStart);
/*  73 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(this::onRepeaterTrigger).addStopEvent(this::onRepeaterStop);
/*  74 */   private final AltModeComponent<Mode> altModeComponent = (new AltModeComponent((IAbility)this, Mode.class, Mode.NORMAL)).addChangeModeEvent(this::onAltModeChange);
/*  75 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*     */   
/*  77 */   private ItemStack cannonBalls = null;
/*     */   
/*     */   public GenkotsuMeteorAbility(AbilityCore<GenkotsuMeteorAbility> core) {
/*  80 */     super(core);
/*     */     
/*  82 */     this.isNew = true;
/*  83 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.repeaterComponent, (AbilityComponent)this.altModeComponent, (AbilityComponent)this.projectileComponent });
/*     */     
/*  85 */     addCanUseCheck(this::canUseAbility);
/*  86 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  90 */     if (this.continuousComponent.isContinuous()) {
/*  91 */       this.repeaterComponent.stop(entity);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  96 */     for (ItemStack itemStack : ItemsHelper.getAllInventoryItems(entity)) {
/*  97 */       if (itemStack.func_77973_b().equals(ModItems.CANNON_BALL.get())) {
/*  98 */         this.cannonBalls = itemStack;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 103 */     this.continuousComponent.triggerContinuity(entity);
/*     */   }
/*     */   
/*     */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/* 107 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 111 */     if (this.altModeComponent.getCurrentMode() == Mode.NORMAL) {
/* 112 */       this.repeaterComponent.start(entity, 1, 1);
/* 113 */     } else if (this.altModeComponent.getCurrentMode() == Mode.RYUSEIGUN) {
/* 114 */       this.repeaterComponent.start(entity, 10, 4);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onRepeaterTrigger(LivingEntity entity, IAbility ability) {
/* 119 */     if (this.cannonBalls == null || this.cannonBalls.func_190916_E() == 0 || canUseAbility(entity, ability).isFail()) {
/* 120 */       entity.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_FIST_OR_CANNONBALLS), Util.field_240973_b_);
/*     */       
/* 122 */       this.repeaterComponent.stop(entity);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 127 */     this.cannonBalls.func_190918_g(1);
/*     */     
/* 129 */     float inaccuracy = (this.altModeComponent.getCurrentMode() == Mode.NORMAL) ? 0.0F : 2.0F;
/*     */     
/* 131 */     this.projectileComponent.shoot(entity, 3.0F, inaccuracy);
/*     */   }
/*     */   
/*     */   private void onRepeaterStop(LivingEntity entity, IAbility ability) {
/* 135 */     this.continuousComponent.stopContinuity(entity);
/*     */     
/* 137 */     if (this.altModeComponent.getCurrentMode() == Mode.NORMAL) {
/* 138 */       this.cooldownComponent.startCooldown(entity, 60.0F);
/* 139 */     } else if (this.altModeComponent.getCurrentMode() == Mode.RYUSEIGUN) {
/* 140 */       this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onAltModeChange(LivingEntity entity, IAbility ability, Mode mode) {
/* 145 */     if (mode == Mode.NORMAL) {
/* 146 */       setDisplayIcon(GENKOTSU_METEOR_NORMAL_ICON);
/* 147 */     } else if (mode == Mode.RYUSEIGUN) {
/* 148 */       setDisplayIcon(GENKOTSU_METEOR_RYUSEIGUN_ICON);
/*     */     } 
/*     */   }
/*     */   
/*     */   private CannonBallProjectile createProjectile(LivingEntity entity) {
/* 153 */     CannonBallProjectile proj = new CannonBallProjectile(entity.field_70170_p, entity, INSTANCE);
/*     */     
/* 155 */     proj.setDamage(15.0F);
/*     */     
/* 157 */     proj.onBlockImpactEvent = (hit -> {
/*     */         if (proj.field_70173_aa < 2) {
/*     */           return;
/*     */         }
/*     */         
/*     */         ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)proj.getThrower(), entity.field_70170_p, hit.func_177958_n(), hit.func_177956_o(), hit.func_177952_p(), 1.0F);
/*     */         
/*     */         explosion.setStaticDamage(5.0F);
/*     */         
/*     */         explosion.setDestroyBlocks(false);
/*     */         explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/*     */         explosion.doExplosion();
/*     */       });
/* 170 */     return proj;
/*     */   }
/*     */   
/*     */   private AbilityUseResult canUseAbility(LivingEntity entity, IAbility ability) {
/* 174 */     if ((entity.func_184614_ca().func_190926_b() && ItemsHelper.countItemInInventory(entity, (Item)ModItems.CANNON_BALL.get()) > 0) || entity.func_184614_ca().func_77973_b().equals(ModItems.CANNON_BALL.get())) {
/* 175 */       return AbilityUseResult.success();
/*     */     }
/*     */     
/* 178 */     return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_FIST_OR_CANNONBALLS));
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 182 */     if (!(entity instanceof PlayerEntity)) {
/* 183 */       return false;
/*     */     }
/*     */     
/* 186 */     PlayerEntity player = (PlayerEntity)entity;
/* 187 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 188 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 190 */     return (props.isBrawler() && questProps.hasFinishedQuest(ModQuests.BRAWLER_TRIAL_03));
/*     */   }
/*     */   
/*     */   public enum Mode {
/* 194 */     NORMAL, RYUSEIGUN;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\brawler\GenkotsuMeteorAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */