/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.doku;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doku.VenomRoadAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ 
/*     */ public class VenomRoadProjectile extends AbilityProjectileEntity {
/*     */   private boolean isDemonMode = false;
/*     */   
/*     */   public VenomRoadProjectile(EntityType type, World world) {
/*  26 */     super(type, world);
/*     */   }
/*     */   private Vector3d startPos; private VenomRoadAbility parent;
/*     */   
/*     */   public VenomRoadProjectile(World world, LivingEntity player, VenomRoadAbility parent, boolean isDemonMode) {
/*  31 */     super((EntityType)DokuProjectiles.VENOM_ROAD.get(), world, player, VenomRoadAbility.INSTANCE);
/*     */     
/*  33 */     this.parent = parent;
/*     */     
/*  35 */     setDamage(isDemonMode ? 15.0F : 8.0F);
/*  36 */     this.field_70158_ak = true;
/*  37 */     setMaxLife(isDemonMode ? 40 : 30);
/*     */     
/*  39 */     setPassThroughEntities();
/*  40 */     this.isDemonMode = isDemonMode;
/*  41 */     this.startPos = player.func_213303_ch();
/*     */     
/*  43 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*  44 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*     */   }
/*     */   
/*     */   private void onBlockImpactEvent(BlockPos pos) {
/*  48 */     LivingEntity thrower = getThrower();
/*     */     
/*  50 */     if (thrower == null) {
/*     */       return;
/*     */     }
/*     */     
/*  54 */     if (!AbilityHelper.canUseMomentumAbilities(thrower)) {
/*     */       return;
/*     */     }
/*     */     
/*  58 */     BlockState state = thrower.field_70170_p.func_180495_p(pos);
/*     */     
/*  60 */     if (state.func_177230_c().isAir(state, (IBlockReader)this.field_70170_p, pos)) {
/*     */       return;
/*     */     }
/*     */     
/*  64 */     if (thrower.func_184187_bx() != null) {
/*  65 */       thrower.func_184210_p();
/*     */     }
/*     */     
/*  68 */     thrower.func_184210_p();
/*  69 */     thrower.func_223102_j(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*  70 */     thrower.field_70143_R = 0.0F;
/*     */     
/*  72 */     if (getThrower() != null) {
/*  73 */       this.parent.getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.startCooldown(getThrower(), 80.0F));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  79 */     if (getLife() <= 0 && getLife() > -1200) {
/*  80 */       setLife(getLife() - 1);
/*     */       
/*  82 */       AbilityHelper.setDeltaMovement((Entity)this, 0.0D, 0.0D, 0.0D);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  87 */     super.func_70071_h_();
/*     */   }
/*     */ 
/*     */   
/*     */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/*  92 */     hitEntity.func_195064_c(new EffectInstance((Effect)ModEffects.DOKU_POISON.get(), 400, this.isDemonMode ? 3 : 1));
/*  93 */     hitEntity.func_195064_c(new EffectInstance(Effects.field_76421_d, 400, this.isDemonMode ? 2 : 1));
/*  94 */     hitEntity.func_195064_c(new EffectInstance(Effects.field_76419_f, 400, this.isDemonMode ? 2 : 1));
/*  95 */     hitEntity.func_195064_c(new EffectInstance(Effects.field_76431_k, 400, this.isDemonMode ? 2 : 1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeSpawnData(PacketBuffer buffer) {
/* 101 */     super.writeSpawnData(buffer);
/* 102 */     buffer.writeBoolean(this.isDemonMode);
/* 103 */     buffer.writeDouble((this.startPos != null) ? this.startPos.field_72450_a : 0.0D);
/* 104 */     buffer.writeDouble((this.startPos != null) ? this.startPos.field_72448_b : 0.0D);
/* 105 */     buffer.writeDouble((this.startPos != null) ? this.startPos.field_72449_c : 0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readSpawnData(PacketBuffer buffer) {
/* 111 */     super.readSpawnData(buffer);
/* 112 */     this.isDemonMode = buffer.readBoolean();
/* 113 */     this.startPos = new Vector3d(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
/*     */   }
/*     */   
/*     */   public Vector3d getStartPos() {
/* 117 */     return this.startPos;
/*     */   }
/*     */   
/*     */   public boolean isDemonMode() {
/* 121 */     return this.isDemonMode;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\doku\VenomRoadProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */