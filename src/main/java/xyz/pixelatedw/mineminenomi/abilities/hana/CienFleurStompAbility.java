/*     */ package xyz.pixelatedw.mineminenomi.abilities.hana;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.hana.HanaFeetEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class CienFleurStompAbility extends Ability {
/*  35 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "cien_fleur_stomp", new Pair[] {
/*  36 */         (Pair)ImmutablePair.of("Stomps the ground in front of the user using giant feet.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 200;
/*     */   private static final int TRIGGERS = 7;
/*     */   private static final int INTERVAL = 4;
/*     */   private static final int RANGE = 10;
/*  43 */   public static final AbilityCore<CienFleurStompAbility> INSTANCE = (new AbilityCore.Builder("Cien Fleur: Stomp", AbilityCategory.DEVIL_FRUITS, CienFleurStompAbility::new))
/*  44 */     .addDescriptionLine(DESCRIPTION)
/*  45 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F)
/*  46 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/*  47 */     .setSourceHakiNature(SourceHakiNature.HARDENING)
/*  48 */     .setSourceType(new SourceType[] { SourceType.FIST
/*  49 */       }).build();
/*     */   
/*  51 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addEndEvent(this::endContinuityEvent);
/*  52 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(this::triggerRepeaterEvent).addStopEvent(this::stopRepeaterEvent);
/*  53 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*  54 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*     */   
/*     */   private Iterator<BlockPos> targetedBlocks;
/*     */   
/*     */   public CienFleurStompAbility(AbilityCore<CienFleurStompAbility> ability) {
/*  59 */     super(ability);
/*     */     
/*  61 */     this.isNew = true;
/*  62 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.repeaterComponent, (AbilityComponent)this.projectileComponent, (AbilityComponent)this.animationComponent });
/*     */     
/*  64 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  68 */     this.continuousComponent.triggerContinuity(entity);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  72 */     this.animationComponent.start(entity, ModAnimations.CROSSED_ARMS);
/*  73 */     HanaHelper.spawnBlossomEffect(entity);
/*     */     
/*  75 */     Predicate<BlockPos> predicate = pos -> (entity.field_70170_p.func_180495_p(pos.func_177984_a()).func_196958_f() && pos.func_177956_o() > entity.func_226278_cu_() - 3.0D);
/*  76 */     Vector3d look = entity.func_213303_ch().func_178787_e(entity.func_70040_Z().func_216372_d(7.0D, 1.0D, 7.0D));
/*  77 */     BlockPos ogPos = new BlockPos(look.func_82615_a(), entity.func_226278_cu_(), look.func_82616_c());
/*  78 */     List<BlockPos> poses = WyHelper.getNearbyBlocks(ogPos, (IWorld)entity.field_70170_p, 10, predicate, (List)ImmutableList.of(Blocks.field_150350_a));
/*  79 */     this.targetedBlocks = WyHelper.shuffle(poses).stream().limit(7L).iterator();
/*     */     
/*  81 */     this.repeaterComponent.start(entity, 7, 4);
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  85 */     this.animationComponent.stop(entity);
/*  86 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */   }
/*     */   
/*     */   private void triggerRepeaterEvent(LivingEntity entity, IAbility ability) {
/*  90 */     if (this.targetedBlocks == null || !this.targetedBlocks.hasNext()) {
/*     */       return;
/*     */     }
/*     */     
/*  94 */     BlockPos pos = this.targetedBlocks.next();
/*  95 */     HanaFeetEntity stompFeet = (HanaFeetEntity)this.projectileComponent.getNewProjectile(entity);
/*  96 */     stompFeet.func_225653_b_(pos.func_177958_n(), (pos.func_177956_o() + 15), pos.func_177952_p());
/*  97 */     AbilityHelper.setDeltaMovement((Entity)stompFeet, 0.0D, -0.9D, 0.0D);
/*  98 */     entity.field_70170_p.func_217376_c((Entity)stompFeet);
/*     */   }
/*     */   
/*     */   private void stopRepeaterEvent(LivingEntity entity, IAbility ability) {
/* 102 */     this.continuousComponent.stopContinuity(entity);
/*     */   }
/*     */   
/*     */   private HanaFeetEntity createProjectile(LivingEntity entity) {
/* 106 */     HanaFeetEntity stompFeet = new HanaFeetEntity(entity.field_70170_p, entity, this);
/* 107 */     return stompFeet;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\hana\CienFleurStompAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */