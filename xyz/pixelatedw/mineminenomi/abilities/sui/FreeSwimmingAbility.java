/*     */ package xyz.pixelatedw.mineminenomi.abilities.sui;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ 
/*     */ public class FreeSwimmingAbility extends Ability {
/*  29 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "free_swimming", new Pair[] {
/*  30 */         (Pair)ImmutablePair.of("Lets the user swim trough blocks. Swimming is activated by running into a ground block.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 20;
/*  34 */   public static final AbilityCore<FreeSwimmingAbility> INSTANCE = (new AbilityCore.Builder("Free Swimming", AbilityCategory.DEVIL_FRUITS, FreeSwimmingAbility::new))
/*  35 */     .addDescriptionLine(DESCRIPTION)
/*  36 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(20.0F), ContinuousComponent.getTooltip()
/*  37 */       }).build();
/*     */   
/*  39 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addTickEvent(this::tickContinuityEvent).addEndEvent(this::endContinuityEvent);
/*     */   
/*     */   private boolean isSwimming = false;
/*     */   
/*     */   public FreeSwimmingAbility(AbilityCore<FreeSwimmingAbility> core) {
/*  44 */     super(core);
/*     */     
/*  46 */     this.isNew = true;
/*  47 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent });
/*     */     
/*  49 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*     */     
/*  51 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  55 */     this.continuousComponent.triggerContinuity(entity);
/*     */   }
/*     */   
/*     */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/*  59 */     entity.field_70145_X = true;
/*     */     
/*  61 */     IAbilityData props = AbilityDataCapability.get(entity);
/*  62 */     boolean flying = false;
/*     */     
/*  64 */     if (entity instanceof PlayerEntity) {
/*  65 */       PlayerEntity player = (PlayerEntity)entity;
/*  66 */       flying = player.field_71075_bZ.field_75100_b;
/*     */     } 
/*     */     
/*  69 */     BlockPos pos = entity.func_233580_cy_();
/*  70 */     boolean isMidAir = (entity.field_70170_p.func_180495_p(pos.func_177984_a()).func_196958_f() && entity.field_70170_p.func_180495_p(pos.func_177977_b()).func_196958_f());
/*  71 */     boolean groundCheck = ((entity.func_213303_ch()).field_72448_b - DevilFruitHelper.getFloorLevel((Entity)entity).func_82617_b() > 0.0D && entity.field_70170_p.func_180495_p(pos.func_177977_b()).func_196958_f());
/*     */     
/*  73 */     NekomimiPunchAbility nekomimiPunch = (NekomimiPunchAbility)props.getEquippedAbility(NekomimiPunchAbility.INSTANCE);
/*  74 */     boolean isNekomimiPunchActive = (nekomimiPunch != null && nekomimiPunch.isContinuous());
/*  75 */     boolean isNekomimiFresh = (nekomimiPunch != null && entity.field_70170_p.func_82737_E() - nekomimiPunch.getLastUseGametime() < 100L);
/*  76 */     boolean canMove = AbilityHelper.canUseMomentumAbilities(entity);
/*     */     
/*  78 */     if (!entity.field_70170_p.field_72995_K && 
/*  79 */       entity.func_70090_H()) {
/*  80 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  85 */     boolean isOutsideGround = false;
/*     */     
/*  87 */     if (groundCheck && !isNekomimiPunchActive && !isNekomimiFresh && !isEntityInsideOpaqueBlock(entity)) {
/*  88 */       isOutsideGround = true;
/*     */     }
/*     */     
/*  91 */     if (isOutsideGround) {
/*  92 */       entity.field_70145_X = false;
/*     */       
/*     */       return;
/*     */     } 
/*  96 */     boolean isInsideBlock = isEntityInsideOpaqueBlock(entity);
/*     */     
/*  98 */     if ((isInsideBlock || entity.func_70051_ag()) && !flying) {
/*  99 */       AbilityHelper.setPose(entity, Pose.SWIMMING);
/* 100 */       entity.field_70143_R = 0.0F;
/*     */     } 
/*     */     
/* 103 */     if (isMidAir && !isNekomimiPunchActive) {
/* 104 */       entity.field_70145_X = true;
/*     */       
/*     */       return;
/*     */     } 
/* 108 */     NyanNyanSuplexAbility nyanSuplex = (NyanNyanSuplexAbility)props.getEquippedAbility(NyanNyanSuplexAbility.INSTANCE);
/* 109 */     if (nyanSuplex != null && nyanSuplex.isCharging()) {
/*     */       return;
/*     */     }
/*     */     
/* 113 */     entity.func_204711_a(true);
/* 114 */     boolean swimming = (entity.func_213283_Z() == Pose.SWIMMING || isNekomimiPunchActive);
/*     */     
/* 116 */     if (!entity.field_70170_p.field_72995_K) {
/* 117 */       this.isSwimming = swimming;
/*     */     }
/*     */     
/* 120 */     if (swimming && (isInsideBlock || (isMidAir && isNekomimiPunchActive))) {
/* 121 */       double x = 0.0D;
/* 122 */       double y = 0.0D;
/* 123 */       double z = 0.0D;
/* 124 */       double swimSpeed = entity.func_110148_a((Attribute)ForgeMod.SWIM_SPEED.get()).func_111126_e() / 2.0D;
/* 125 */       Vector3d lookVector = entity.func_70040_Z();
/*     */       
/* 127 */       if (entity.field_191988_bg != 0.0F && canMove) {
/* 128 */         double speed = MathHelper.func_151237_a(swimSpeed, 1.3D, 6.0D);
/*     */         
/* 130 */         x = lookVector.field_72450_a * speed * entity.field_191988_bg;
/* 131 */         y = lookVector.field_72448_b * speed * entity.field_191988_bg;
/* 132 */         z = lookVector.field_72449_c * speed * entity.field_191988_bg;
/*     */       } 
/*     */       
/* 135 */       if (isNekomimiPunchActive) {
/* 136 */         double speed = 1.6D;
/*     */         
/* 138 */         x = lookVector.field_72450_a * speed * 1.0D;
/* 139 */         y = lookVector.field_72448_b * speed * 1.0D;
/* 140 */         z = lookVector.field_72449_c * speed * 1.0D;
/*     */       } 
/*     */       
/* 143 */       if (entity.func_225608_bj_()) {
/* 144 */         y = -0.20000000298023224D;
/*     */       }
/* 146 */       else if (AbilityHelper.isJumping(entity) && !entity.field_70170_p.func_180495_p(pos.func_177977_b()).func_196958_f()) {
/* 147 */         y = 0.20000000298023224D;
/* 148 */         if (entity.field_70170_p.func_180495_p(pos.func_177984_a()).func_196958_f()) {
/* 149 */           y = 0.6000000238418579D;
/*     */         }
/*     */       } 
/*     */       
/* 153 */       BlockPos frontPos = new BlockPos(entity.func_213303_ch().func_72441_c(x, y + entity.func_70047_e(), z));
/* 154 */       BlockState frontBlock = entity.field_70170_p.func_180495_p(frontPos);
/*     */       
/* 156 */       if (RestrictedBlockProtectionRule.INSTANCE.isBanned(frontBlock)) {
/* 157 */         Vector3d reversedLookVector = (entity.field_191988_bg < 0.0F) ? lookVector : lookVector.func_216371_e();
/* 158 */         x = reversedLookVector.field_72450_a;
/* 159 */         y = reversedLookVector.field_72448_b;
/* 160 */         z = reversedLookVector.field_72449_c;
/*     */       } 
/*     */       
/* 163 */       if (entity.func_226278_cu_() < 5.0D) {
/* 164 */         y = 0.0D;
/*     */       }
/*     */       
/* 167 */       AbilityHelper.setDeltaMovement((Entity)entity, x, y, z);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 172 */     this.isSwimming = false;
/* 173 */     this.cooldownComponent.startCooldown(entity, 20.0F);
/* 174 */     entity.field_70145_X = false;
/*     */   }
/*     */   
/*     */   public void setSwimming(boolean flag) {
/* 178 */     this.isSwimming = flag;
/*     */   }
/*     */   
/*     */   public boolean isSwimming() {
/* 182 */     return this.isSwimming;
/*     */   }
/*     */   
/*     */   public boolean isEntityInsideOpaqueBlock(LivingEntity entity) {
/* 186 */     BlockPos.Mutable blockPos = new BlockPos.Mutable();
/* 187 */     for (int i = 0; i < 8; i++) {
/* 188 */       int j = MathHelper.func_76128_c(entity.func_226278_cu_() + ((((i >> 0) % 2) - 0.5F) * 0.1F) + entity.func_70047_e());
/* 189 */       int k = MathHelper.func_76128_c(entity.func_226277_ct_() + ((((i >> 1) % 2) - 0.5F) * entity.func_213302_cg() * 0.8F));
/* 190 */       int l = MathHelper.func_76128_c(entity.func_226281_cx_() + ((((i >> 2) % 2) - 0.5F) * entity.func_213311_cf() * 0.8F));
/* 191 */       if (blockPos.func_177958_n() != k || blockPos.func_177956_o() != j || blockPos.func_177952_p() != l) {
/* 192 */         blockPos.func_181079_c(k, j, l);
/* 193 */         boolean isSolid = entity.field_70170_p.func_180495_p((BlockPos)blockPos).func_224755_d((IBlockReader)entity.field_70170_p, (BlockPos)blockPos, Direction.UP);
/* 194 */         boolean isLiquid = entity.field_70170_p.func_204610_c((BlockPos)blockPos).func_206889_d();
/* 195 */         if (isSolid || isLiquid) {
/* 196 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 200 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\sui\FreeSwimmingAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */