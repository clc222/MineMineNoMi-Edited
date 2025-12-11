/*     */ package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.awt.Color;
/*     */ import java.text.DecimalFormat;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.projectile.ProjectileEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ConsumptionComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.GaugeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdatePassiveAbilityDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class FlamesOfRegenerationAbility
/*     */   extends PassiveAbility2 {
/*     */   private static final int MAX_ENERGY = 100;
/*  47 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "flames_of_regeneration", new Pair[] {
/*  48 */         (Pair)ImmutablePair.of("Protects the user and heals them back up when damage is taken, has an initial reserve of §a%s Energy§r which increases with time and decreses with each heal.", new Object[] { Integer.valueOf(100) })
/*     */       });
/*  50 */   public static final AbilityCore<FlamesOfRegenerationAbility> INSTANCE = (new AbilityCore.Builder("Flames of Regeneration", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, FlamesOfRegenerationAbility::new))
/*  51 */     .addDescriptionLine(DESCRIPTION)
/*  52 */     .build();
/*     */   
/*  54 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::onDamageTaken);
/*  55 */   private final ConsumptionComponent consumptionComponent = (new ConsumptionComponent((IAbility)this)).addConsumptionEvent(this::onConsumption);
/*     */   
/*     */   private static final int MAX_COOLDOWN = 100;
/*     */   
/*  59 */   private double energy = 100.0D;
/*     */   
/*  61 */   private int cooldown = 0;
/*  62 */   private int invulnerableTime = 0;
/*     */   
/*  64 */   private Interval recuperationInterval = new Interval(10);
/*  65 */   private Interval regenerationInterval = new Interval(40);
/*     */   
/*     */   public FlamesOfRegenerationAbility(AbilityCore<FlamesOfRegenerationAbility> ability) {
/*  68 */     super(ability);
/*     */     
/*  70 */     if (isClientSide()) {
/*  71 */       GaugeComponent gaugeComponent = new GaugeComponent((IAbility)this, this::renderGauge);
/*     */       
/*  73 */       addComponents(new AbilityComponent[] { (AbilityComponent)gaugeComponent });
/*     */     } 
/*     */     
/*  76 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.damageTakenComponent, (AbilityComponent)this.consumptionComponent });
/*     */     
/*  78 */     addDuringPassiveEvent(this::duringPassive);
/*     */   }
/*     */   
/*     */   private void duringPassive(LivingEntity entity) {
/*  82 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  86 */     this.invulnerableTime--;
/*     */     
/*  88 */     if (this.regenerationInterval.canTick() && 
/*  89 */       this.energy - 4.0D >= 0.0D && entity.func_110143_aJ() < entity.func_110138_aP()) {
/*  90 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.FLAMES_OF_REGEN.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */       
/*  92 */       entity.func_70691_i(4.0F);
/*     */       
/*  94 */       addEnergy(entity, -5.0D);
/*     */     } 
/*     */ 
/*     */     
/*  98 */     if (WyHelper.isInCombat(entity)) {
/*     */       return;
/*     */     }
/*     */     
/* 102 */     if (entity.func_70608_bn() && this.recuperationInterval.canTick()) {
/* 103 */       addEnergy(entity, 10.0D);
/*     */     }
/*     */     
/* 106 */     if (this.cooldown > 0) {
/* 107 */       this.cooldown--;
/*     */       return;
/*     */     } 
/* 110 */     if (this.cooldown <= 0 && this.energy <= 0.0D) {
/* 111 */       addEnergy(entity, 1.0D);
/*     */     }
/*     */     
/* 114 */     if (this.energy <= 0.0D) {
/* 115 */       this.cooldown = 100;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 120 */     addEnergy(entity, 0.05D);
/*     */   }
/*     */   public float onDamageTaken(LivingEntity entity, IAbility ability, DamageSource source, float damage) {
/*     */     int i;
/* 124 */     if (isPaused()) {
/* 125 */       return damage;
/*     */     }
/*     */     
/* 128 */     if (this.invulnerableTime > 0) {
/* 129 */       return 0.0F;
/*     */     }
/*     */     
/* 132 */     boolean hasShadow = EntityStatsCapability.get(entity).hasShadow();
/*     */     
/* 134 */     if ((source instanceof ModDamageSource && ((ModDamageSource)source).isBypassingLogia()) || (source.func_76347_k() && !hasShadow)) {
/* 135 */       return damage;
/*     */     }
/*     */     
/* 138 */     if (DevilFruitCapability.get(entity).hasYamiPower()) {
/* 139 */       return damage;
/*     */     }
/*     */     
/* 142 */     boolean isImmune = true;
/*     */     
/* 144 */     if (source.func_76364_f() != null) {
/* 145 */       LivingEntity sourceOwner = null;
/*     */       
/* 147 */       if (source.func_76364_f() instanceof LivingEntity) {
/* 148 */         sourceOwner = (LivingEntity)source.func_76364_f();
/* 149 */       } else if (source.func_76364_f() instanceof AbilityProjectileEntity) {
/* 150 */         sourceOwner = ((AbilityProjectileEntity)source.func_76364_f()).getThrower();
/* 151 */       } else if (source.func_76364_f() instanceof ProjectileEntity) {
/* 152 */         Entity projectileOwner = ((ProjectileEntity)source.func_76364_f()).func_234616_v_();
/*     */         
/* 154 */         if (projectileOwner != null && projectileOwner instanceof LivingEntity) {
/* 155 */           sourceOwner = (LivingEntity)projectileOwner;
/*     */           
/* 157 */           boolean hasImbuingActive = HakiHelper.hasImbuingActive((LivingEntity)projectileOwner);
/*     */           
/* 159 */           i = isImmune & (!hasImbuingActive ? 1 : 0);
/*     */         } 
/*     */       } 
/*     */       
/* 163 */       if (sourceOwner != null) {
/* 164 */         boolean hasImbuingActive = HakiHelper.hasImbuingActive(sourceOwner);
/* 165 */         boolean hasHardeningActive = HakiHelper.hasHardeningActive(sourceOwner);
/*     */         
/* 167 */         if (source instanceof ModDamageSource) {
/* 168 */           ModDamageSource mSource = (ModDamageSource)source;
/*     */           
/* 170 */           if (mSource.getHakiNature() == SourceHakiNature.IMBUING && hasImbuingActive) {
/* 171 */             isImmune = false;
/* 172 */           } else if (mSource.getHakiNature() == SourceHakiNature.HARDENING && hasHardeningActive) {
/* 173 */             isImmune = false;
/* 174 */           } else if (mSource.getHakiNature() == SourceHakiNature.SPECIAL && (hasImbuingActive || hasHardeningActive)) {
/* 175 */             isImmune = false;
/*     */           } 
/* 177 */         } else if (source instanceof net.minecraft.util.EntityDamageSource) {
/* 178 */           i = isImmune & (!hasHardeningActive ? 1 : 0);
/* 179 */         } else if (source instanceof net.minecraft.util.IndirectEntityDamageSource) {
/* 180 */           i &= !hasImbuingActive ? 1 : 0;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 185 */     if (i != 0 && this.energy - damage >= 0.0D) {
/* 186 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.FLAMES_OF_REGEN.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */       
/* 188 */       addEnergy(entity, -damage);
/*     */       
/* 190 */       this.invulnerableTime = 10;
/*     */       
/* 192 */       return 0.0F;
/*     */     } 
/* 194 */     this.cooldown = 100;
/*     */     
/* 196 */     return damage;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onConsumption(LivingEntity entity, IAbility ability, int nutrition, float saturationModifier) {
/* 201 */     addEnergy(entity, (nutrition * saturationModifier) * 2.0D);
/*     */     
/* 203 */     return true;
/*     */   }
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   private void renderGauge(PlayerEntity player, MatrixStack matrixStack, int posX, int posY, FlamesOfRegenerationAbility ability) {
/* 208 */     Minecraft mc = Minecraft.func_71410_x();
/*     */     
/* 210 */     mc.func_110434_K().func_110577_a(ModResources.WIDGETS);
/*     */     
/* 212 */     RendererHelper.drawAbilityIcon(PhoenixFlyPointAbility.INSTANCE, matrixStack, posX, (posY - 38), 0, 32.0F, 32.0F);
/*     */     
/* 214 */     DecimalFormat energyFormat = new DecimalFormat("#0.0");
/*     */     
/* 216 */     String energy = energyFormat.format(ability.energy);
/*     */     
/* 218 */     WyHelper.drawStringWithBorder(mc.field_71466_p, matrixStack, energy, posX + 15 - mc.field_71466_p.func_78256_a(energy) / 2, posY - 25, Color.WHITE.getRGB());
/*     */   }
/*     */   
/*     */   public void addEnergy(LivingEntity entity, double energy) {
/* 222 */     this.energy = MathHelper.func_151237_a(this.energy + energy, 0.0D, 100.0D);
/*     */     
/* 224 */     if (entity instanceof PlayerEntity) {
/* 225 */       WyNetwork.sendTo(new SUpdatePassiveAbilityDataPacket(entity, (IAbility)this), (PlayerEntity)entity);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT save(CompoundNBT nbt) {
/* 231 */     nbt = super.save(nbt);
/* 232 */     nbt.func_74780_a("energy", this.energy);
/*     */     
/* 234 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 239 */     super.load(nbt);
/*     */     
/* 241 */     this.energy = nbt.func_74769_h("energy");
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\toriphoenix\FlamesOfRegenerationAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */