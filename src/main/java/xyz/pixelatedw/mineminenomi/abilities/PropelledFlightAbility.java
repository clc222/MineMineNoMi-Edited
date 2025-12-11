/*     */ package xyz.pixelatedw.mineminenomi.abilities;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.awt.Color;
/*     */ import java.text.DecimalFormat;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.IWorldReader;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.api.IPlayerAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.GaugeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public abstract class PropelledFlightAbility extends PassiveAbility2 {
/*  36 */   protected double stamina = 100.0D; protected boolean isRecovering = false;
/*     */   protected boolean hasLanded = false;
/*  38 */   protected float speed = 0.0F;
/*     */   
/*  40 */   private Vector3d oldMovement = Vector3d.field_186680_a;
/*     */   
/*  42 */   private Interval staminaInterval = new Interval(20);
/*     */   
/*  44 */   private static final ResourceLocation SPECIAL_FLY_ICON = new ResourceLocation("mineminenomi", "textures/abilities/special_fly.png");
/*     */   
/*     */   public PropelledFlightAbility(AbilityCore<? extends PropelledFlightAbility> core) {
/*  47 */     super(core);
/*     */     
/*  49 */     setDisplayIcon(SPECIAL_FLY_ICON);
/*     */     
/*  51 */     if (isClientSide()) {
/*  52 */       GaugeComponent gaugeComponent = new GaugeComponent((IAbility)this, this::renderGauge);
/*     */       
/*  54 */       addComponents(new AbilityComponent[] { (AbilityComponent)gaugeComponent });
/*     */     } 
/*     */     
/*  57 */     addRemoveEvent(this::onRemove);
/*     */     
/*  59 */     this.pauseTickComponent.addResumeEvent(100, this::onResume).addPauseEvent(100, this::onPause);
/*     */     
/*  61 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/*     */     
/*  63 */     addDuringPassiveEvent(this::onDuringPassive);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick(LivingEntity entity) {
/*  68 */     super.tick(entity);
/*     */     
/*  70 */     if (!(entity instanceof PlayerEntity) || this.pauseTickComponent.isPaused()) {
/*     */       return;
/*     */     }
/*     */     
/*  74 */     PlayerEntity player = (PlayerEntity)entity;
/*     */     
/*  76 */     if (!AbilityHelper.canUseMomentumAbilities((LivingEntity)player)) {
/*  77 */       player.field_71075_bZ.field_75100_b = false;
/*     */     }
/*     */     
/*  80 */     float health = player.func_110143_aJ();
/*  81 */     float maxHealth = player.func_110138_aP();
/*     */     
/*  83 */     if (this.stamina <= 0.0D) {
/*  84 */       this.isRecovering = true;
/*     */     }
/*     */     
/*  87 */     if (player.func_233570_aj_() && this.stamina < 100.0D && this.staminaInterval.canTick()) {
/*  88 */       alterStamina((health / maxHealth) * 2.5D);
/*     */     }
/*     */     
/*  91 */     if (this.isRecovering && this.stamina >= 100.0D) {
/*  92 */       this.isRecovering = false;
/*  93 */       this.hasLanded = false;
/*     */       
/*  95 */       enableFlight(player);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void onRemove(LivingEntity entity, PassiveAbility2 ability) {
/* 100 */     if (entity instanceof PlayerEntity) {
/* 101 */       disableFlight((PlayerEntity)entity);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onResume(LivingEntity entity, IAbility ability) {
/* 106 */     if (!(entity instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*     */     
/* 110 */     PlayerEntity player = (PlayerEntity)entity;
/*     */     
/* 112 */     if (canUse(entity).isSuccess()) {
/* 113 */       enableFlight(player);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onDuringPassive(LivingEntity entity) {
/* 118 */     if (!(entity instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*     */     
/* 122 */     PlayerEntity player = (PlayerEntity)entity;
/*     */     
/* 124 */     if (this.isRecovering) {
/* 125 */       if (!this.hasLanded && !entity.field_70170_p.func_175623_d(entity.func_233580_cy_().func_177979_c(5))) {
/* 126 */         this.hasLanded = true;
/*     */       }
/*     */       
/* 129 */       if (this.hasLanded) {
/* 130 */         disableFlight(player);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/* 136 */     float health = player.func_110143_aJ();
/* 137 */     float maxHealth = player.func_110138_aP();
/*     */     
/* 139 */     if (!this.isRecovering && this.stamina > 0.0D && player.field_71075_bZ.field_75100_b && health < maxHealth && this.staminaInterval.canTick()) {
/* 140 */       alterStamina(((maxHealth - health) / maxHealth) * -5.0D);
/*     */     }
/*     */     
/* 143 */     if (AbilityHelper.isInCreativeOrSpectator(entity)) {
/* 144 */       if (((IPlayerAbilities)player.field_71075_bZ).hasCustomFlight()) {
/* 145 */         disableFlight(player);
/*     */       }
/*     */       return;
/*     */     } 
/* 149 */     if (!((IPlayerAbilities)player.field_71075_bZ).hasCustomFlight()) {
/* 150 */       enableFlight(player);
/*     */     }
/*     */     
/* 153 */     if (!player.field_71075_bZ.field_75100_b || !AbilityHelper.canUseMomentumAbilities((LivingEntity)player) || canUse((LivingEntity)player).isFail()) {
/* 154 */       this.speed = 0.0F;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 159 */     boolean isFlying = isFlying((LivingEntity)player);
/*     */     
/* 161 */     float maxSpeed = getMaxSpeed((LivingEntity)player);
/* 162 */     float acceleration = getAcceleration((LivingEntity)player);
/*     */     
/* 164 */     acceleration *= (this.speed > 0.0F) ? (1.0F - this.speed / maxSpeed) : 1.0F;
/* 165 */     acceleration = isFlying ? acceleration : (-maxSpeed / 20.0F);
/*     */     
/* 167 */     this.speed = MathHelper.func_76131_a(this.speed + acceleration, (acceleration > 0.0F) ? (maxSpeed / 5.0F) : 0.0F, maxSpeed);
/* 168 */     this.speed = getSpeed((LivingEntity)player);
/*     */     
/* 170 */     BlockPos belowPos = player.func_226270_aj_();
/*     */     
/* 172 */     float slipperiness = player.field_70170_p.func_180495_p(belowPos).getSlipperiness((IWorldReader)player.field_70170_p, belowPos, (Entity)player);
/* 173 */     float friction = player.func_233570_aj_() ? (slipperiness * 0.91F) : 0.91F;
/*     */     
/* 175 */     friction = !player.field_70123_F ? friction : 0.0F;
/*     */     
/* 177 */     Vector3d movementVec = getMovementVector(entity);
/* 178 */     Vector3d flightMovement = new Vector3d(movementVec.field_72450_a, this.isRecovering ? -0.5D : movementVec.field_72448_b, movementVec.field_72449_c);
/* 179 */     Vector3d movementDiff = flightMovement.func_178788_d(this.oldMovement.func_216372_d(friction, 0.6D, friction));
/* 180 */     Vector3d currentMovement = player.func_213322_ci();
/* 181 */     Vector3d newMovement = currentMovement.func_178787_e(movementDiff);
/*     */     
/* 183 */     this.oldMovement = flightMovement;
/*     */     
/* 185 */     AbilityHelper.setDeltaMovement((Entity)player, newMovement);
/*     */     
/* 187 */     if (!CommonConfig.INSTANCE.hasYRestrictionRemoved() && !DevilFruitHelper.flyingAtMaxHeight((LivingEntity)player, getHeightDifference((LivingEntity)player))) {
/* 188 */       AbilityHelper.setDeltaMovement((Entity)player, 0.0D, -1.0D, 0.0D);
/*     */     }
/*     */     
/* 191 */     player.field_70143_R = 0.0F;
/*     */   }
/*     */   
/*     */   private void onPause(LivingEntity entity, IAbility ability) {
/* 195 */     if (!(entity instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*     */     
/* 199 */     PlayerEntity player = (PlayerEntity)entity;
/*     */     
/* 201 */     disableFlight(player);
/*     */   }
/*     */   
/*     */   public boolean canRenderGauge(PlayerEntity entity) {
/* 205 */     return true;
/*     */   }
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   private void renderGauge(PlayerEntity player, MatrixStack matrixStack, int posX, int posY, IAbility ability) {
/* 210 */     if (!canRenderGauge(player)) {
/*     */       return;
/*     */     }
/*     */     
/* 214 */     Minecraft mc = Minecraft.func_71410_x();
/*     */     
/* 216 */     mc.func_110434_K().func_110577_a(ModResources.WIDGETS);
/*     */     
/* 218 */     RendererHelper.drawIcon(SPECIAL_FLY_ICON, matrixStack, posX, (posY - 38), 0.0F, 32.0F, 32.0F);
/*     */     
/* 220 */     DecimalFormat staminaFormat = new DecimalFormat("#0.0");
/*     */     
/* 222 */     String strStamina = staminaFormat.format(this.stamina);
/*     */     
/* 224 */     WyHelper.drawStringWithBorder(mc.field_71466_p, matrixStack, strStamina, posX + 15 - mc.field_71466_p.func_78256_a(strStamina) / 2, posY - 25, Color.WHITE.getRGB());
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT save(CompoundNBT nbt) {
/* 229 */     nbt = super.save(nbt);
/* 230 */     nbt.func_74757_a("isRecovering", this.isRecovering);
/* 231 */     nbt.func_74757_a("hasLanded", this.hasLanded);
/* 232 */     nbt.func_74780_a("stamina", this.stamina);
/*     */     
/* 234 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 239 */     super.load(nbt);
/*     */     
/* 241 */     this.isRecovering = nbt.func_74767_n("isRecovering");
/* 242 */     this.hasLanded = nbt.func_74767_n("hasLanded");
/* 243 */     this.stamina = nbt.func_74769_h("stamina");
/*     */   }
/*     */   
/*     */   public double getStamina() {
/* 247 */     return this.stamina;
/*     */   }
/*     */   
/*     */   public void alterStamina(double stamina) {
/* 251 */     this.stamina = MathHelper.func_151237_a(this.stamina + stamina, 0.0D, 100.0D);
/*     */   }
/*     */   
/*     */   public boolean isFlying(LivingEntity entity) {
/* 255 */     boolean isMovingAboveGround = ((entity.field_191988_bg != 0.0F || entity.field_70702_br != 0.0F) && !entity.field_70124_G && !entity.field_70123_F);
/*     */     
/* 257 */     if (entity instanceof PlayerEntity) {
/* 258 */       return (((PlayerEntity)entity).field_71075_bZ.field_75100_b && isMovingAboveGround);
/*     */     }
/*     */     
/* 261 */     return isMovingAboveGround;
/*     */   }
/*     */   
/*     */   public float getSpeed(LivingEntity entity) {
/* 265 */     return this.speed;
/*     */   }
/*     */   
/*     */   protected Vector3d getMovementVector(LivingEntity entity) {
/* 269 */     Vector3d lookVector = entity.func_70040_Z();
/*     */     
/* 271 */     double x = lookVector.field_72450_a * this.speed * entity.field_191988_bg;
/* 272 */     double y = lookVector.field_72448_b * this.speed * entity.field_191988_bg + Math.cos(entity.field_70173_aa / 4.0D) / 15.0D;
/* 273 */     double z = lookVector.field_72449_c * this.speed * entity.field_191988_bg;
/*     */     
/* 275 */     return new Vector3d(x, y, z);
/*     */   }
/*     */   
/*     */   public abstract float getMaxSpeed(LivingEntity paramLivingEntity);
/*     */   
/*     */   protected abstract float getAcceleration(LivingEntity paramLivingEntity);
/*     */   
/*     */   protected abstract int getHeightDifference(LivingEntity paramLivingEntity);
/*     */   
/*     */   public static void enableFlight(PlayerEntity player) {
/* 285 */     if (AbilityHelper.isInCreativeOrSpectator((LivingEntity)player)) {
/*     */       return;
/*     */     }
/*     */     
/* 289 */     ((IPlayerAbilities)player.field_71075_bZ).setCustomFlight(true);
/*     */     
/* 291 */     player.field_71075_bZ.field_75101_c = true;
/*     */     
/* 293 */     if (!player.field_70170_p.field_72995_K) {
/* 294 */       ((ServerPlayerEntity)player).field_71135_a.func_147359_a((IPacket)new SPlayerAbilitiesPacket(player.field_71075_bZ));
/*     */     }
/*     */   }
/*     */   
/*     */   public static void disableFlight(PlayerEntity player) {
/* 299 */     if (!AbilityHelper.isInCreativeOrSpectator((LivingEntity)player)) {
/* 300 */       player.field_71075_bZ.field_75101_c = false;
/* 301 */       player.field_71075_bZ.field_75100_b = false;
/*     */     } 
/*     */     
/* 304 */     ((IPlayerAbilities)player.field_71075_bZ).setCustomFlight(false);
/*     */     
/* 306 */     if (!player.field_70170_p.field_72995_K)
/* 307 */       ((ServerPlayerEntity)player).field_71135_a.func_147359_a((IPacket)new SPlayerAbilitiesPacket(player.field_71075_bZ)); 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\PropelledFlightAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */