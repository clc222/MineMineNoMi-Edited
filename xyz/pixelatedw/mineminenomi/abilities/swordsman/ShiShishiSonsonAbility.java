/*     */ package xyz.pixelatedw.mineminenomi.abilities.swordsman;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.projectile.ProjectileEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.play.server.SAnimateHandPacket;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SPinCameraPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.entities.SUnpinCameraPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class ShiShishiSonsonAbility extends Ability {
/*  54 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "shi_shishi_sonson", new Pair[] {
/*  55 */         (Pair)ImmutablePair.of("The user dashes forward and rapidly slashes the opponent", null)
/*     */       });
/*     */   
/*     */   private static final float COOLDOWN = 180.0F;
/*     */   private static final int CHARGE_TIME = 20;
/*     */   private static final float DAMAGE = 35.0F;
/*     */   private static final float RANGE = 2.5F;
/*     */   private static final float MAX_TELEPORT_DISTANCE = 30.0F;
/*     */   private static final float MAX_YAW_CHANGE = 90.0F;
/*     */   private static final float MAX_PITCH_CHANGE = 12.0F;
/*  65 */   public static final AbilityCore<ShiShishiSonsonAbility> INSTANCE = (new AbilityCore.Builder("Shi Shishi Sonson", AbilityCategory.STYLE, ShiShishiSonsonAbility::new))
/*  66 */     .addDescriptionLine(DESCRIPTION)
/*  67 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(180.0F), ChargeComponent.getTooltip(20.0F), DealDamageComponent.getTooltip(35.0F), RangeComponent.getTooltip(30.0F, RangeComponent.RangeType.LINE)
/*  68 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/*  69 */     .setSourceType(new SourceType[] { SourceType.SLASH
/*  70 */       }).setUnlockCheck(ShiShishiSonsonAbility::canUnlock)
/*  71 */     .build();
/*     */   
/*  73 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addStartEvent(this::startChargeEvent).addTickEvent(this::duringChargeEvent).addEndEvent(this::endChargeEvent);
/*  74 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*  75 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  76 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*  77 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/*     */   
/*     */   private boolean isFakeOut = false;
/*     */   
/*     */   public ShiShishiSonsonAbility(AbilityCore<ShiShishiSonsonAbility> core) {
/*  82 */     super(core);
/*     */     
/*  84 */     this.isNew = true;
/*     */     
/*  86 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.hitTrackerComponent });
/*     */     
/*  88 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*  89 */     addCanUseCheck(AbilityHelper::canUseSwordsmanAbilities);
/*     */     
/*  91 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  95 */     if (!this.chargeComponent.isCharging()) {
/*  96 */       this.isFakeOut = false;
/*     */       
/*  98 */       this.chargeComponent.startCharging(entity, 20.0F);
/*     */     } else {
/* 100 */       this.isFakeOut = true;
/*     */       
/* 102 */       this.chargeComponent.stopCharging(entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void startChargeEvent(LivingEntity entity, IAbility ability) {
/* 107 */     this.hitTrackerComponent.clearHits();
/* 108 */     this.animationComponent.start(entity, ModAnimations.ITTORYU_CHARGE);
/*     */     
/* 110 */     if (entity instanceof net.minecraft.entity.player.ServerPlayerEntity) {
/* 111 */       WyNetwork.sendTo(SPinCameraPacket.pinClampedYawAndPitch(entity.field_70177_z, 90.0F, entity.field_70125_A, 12.0F), (PlayerEntity)entity);
/*     */     }
/*     */   }
/*     */   
/*     */   private void duringChargeEvent(LivingEntity entity, IAbility ability) {
/* 116 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1, false, false));
/*     */   }
/*     */   
/*     */   private void endChargeEvent(LivingEntity entity, IAbility ability) {
/* 120 */     if (!this.isFakeOut) {
/* 121 */       ItemStack stack = entity.func_184614_ca();
/*     */       
/* 123 */       stack.func_222118_a(1, entity, user -> user.func_213361_c(EquipmentSlotType.MAINHAND));
/*     */       
/* 125 */       BlockPos.Mutable blockPos = WyHelper.rayTraceBlockSafe(entity, 30.0F).func_239590_i_();
/*     */       
/* 127 */       AbilityDamageSource source = (AbilityDamageSource)((ModDamageSource)this.dealDamageComponent.getDamageSource(entity)).setSlash();
/*     */       
/* 129 */       Vector3d startPos = entity.func_213303_ch();
/*     */       
/* 131 */       float actualTeleportDistance = 30.0F;
/*     */       double f;
/* 133 */       for (f = 0.0D; f < 1.0D; f += 0.13D) {
/* 134 */         double x = MathHelper.func_219803_d(f, startPos.func_82615_a(), blockPos.func_177958_n());
/* 135 */         double y = MathHelper.func_219803_d(f, startPos.func_82617_b(), blockPos.func_177956_o());
/* 136 */         double z = MathHelper.func_219803_d(f, startPos.func_82616_c(), blockPos.func_177952_p());
/*     */         
/* 138 */         Vector3d pos = new Vector3d(x, y, z);
/*     */         
/* 140 */         List<ProjectileEntity> projectiles = WyHelper.getNearbyEntities(pos, (IWorld)entity.field_70170_p, entity.func_213311_cf(), entity.func_213302_cg(), entity.func_213311_cf(), null, new Class[] { ProjectileEntity.class });
/*     */         
/* 142 */         if (!projectiles.isEmpty()) {
/* 143 */           projectiles.sort(TargetHelper.closestComparator(startPos));
/*     */           
/* 145 */           actualTeleportDistance = MathHelper.func_76133_a(((ProjectileEntity)projectiles.get(0)).func_195048_a(startPos));
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */       
/* 151 */       blockPos.func_189533_g((Vector3i)WyHelper.rayTraceBlockSafe(entity, actualTeleportDistance));
/*     */       
/* 153 */       double floorDifference = DevilFruitHelper.getDifferenceToFloor((Entity)entity);
/*     */       
/* 155 */       if (floorDifference > 1.0D && blockPos.func_177956_o() > entity.func_226278_cu_()) {
/* 156 */         blockPos.func_185336_p((int)entity.func_226278_cu_());
/*     */       }
/*     */       
/* 159 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInLine(entity, actualTeleportDistance, 2.5F);
/*     */       
/* 161 */       for (LivingEntity target : targets) {
/* 162 */         if (this.hitTrackerComponent.canHit((Entity)target)) {
/* 163 */           boolean flag = this.dealDamageComponent.hurtTarget(entity, target, 35.0F, (DamageSource)source);
/*     */           
/* 165 */           if (flag && !entity.field_70170_p.field_72995_K) {
/* 166 */             WyHelper.spawnParticles((IParticleData)ParticleTypes.field_197603_N, (ServerWorld)entity.field_70170_p, target.func_226277_ct_(), target.func_226278_cu_() + target.func_70047_e(), target.func_226281_cx_());
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 171 */       entity.func_184210_p();
/* 172 */       entity.func_223102_j(blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p());
/*     */       
/* 174 */       if (!entity.field_70170_p.field_72995_K) {
/* 175 */         ((ServerWorld)entity.field_70170_p).func_72863_F().func_217216_a((Entity)entity, (IPacket)new SAnimateHandPacket((Entity)entity, 0));
/*     */       }
/*     */       
/* 178 */       entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.DASH_ABILITY_SWOOSH_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*     */     } 
/*     */     
/* 181 */     if (entity instanceof net.minecraft.entity.player.ServerPlayerEntity) {
/* 182 */       WyNetwork.sendTo(new SUnpinCameraPacket(), (PlayerEntity)entity);
/*     */     }
/*     */     
/* 185 */     this.animationComponent.stop(entity);
/* 186 */     this.cooldownComponent.startCooldown(entity, 180.0F);
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 190 */     if (!(entity instanceof PlayerEntity)) {
/* 191 */       return false;
/*     */     }
/*     */     
/* 194 */     PlayerEntity player = (PlayerEntity)entity;
/* 195 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 196 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 198 */     return (props.isSwordsman() && questProps.hasFinishedQuest(ModQuests.SWORDSMAN_TRIAL_01));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\swordsman\ShiShishiSonsonAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */