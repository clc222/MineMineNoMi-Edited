/*     */ package xyz.pixelatedw.mineminenomi.entities;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifierMap;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ 
/*     */ public class PhysicalBodyEntity extends OPEntity {
/*  33 */   private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.func_187226_a(PhysicalBodyEntity.class, DataSerializers.field_187203_m);
/*  34 */   private Ability parentAbility = null;
/*     */ 
/*     */   
/*     */   public PhysicalBodyEntity(EntityType type, World world) {
/*  38 */     super(type, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public PhysicalBodyEntity(World world) {
/*  43 */     this((EntityType)ModEntities.PHYSICAL_BODY.get(), world);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParentAbility(Ability ability) {
/*  48 */     this.parentAbility = ability;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwner(LivingEntity owner) {
/*  53 */     this.field_70180_af.func_187227_b(OWNER, Optional.of(owner.func_110124_au()));
/*  54 */     IEntityStats entityStats = EntityStatsCapability.get((LivingEntity)this);
/*  55 */     IEntityStats ownerStats = EntityStatsCapability.get(owner);
/*  56 */     entityStats.setFaction(ownerStats.getFaction());
/*  57 */     entityStats.setRace(ownerStats.getRace());
/*     */ 
/*     */     
/*  60 */     ForgeRegistries.ATTRIBUTES.forEach(attr -> {
/*     */           if (owner.func_110148_a(attr) != null && func_110148_a(attr) != null) {
/*     */             double val = owner.func_110148_a(attr).func_111125_b();
/*     */ 
/*     */             
/*     */             func_110148_a(attr).func_111128_a(val);
/*     */           } 
/*     */         });
/*     */ 
/*     */     
/*  70 */     func_70606_j(owner.func_110143_aJ());
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public UUID getOwnerUUID() {
/*  76 */     return ((Optional)func_184212_Q().func_187225_a(OWNER)).isPresent() ? ((Optional<UUID>)func_184212_Q().func_187225_a(OWNER)).get() : null;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PlayerEntity getOwner() {
/*  82 */     UUID uuid = getOwnerUUID();
/*  83 */     if (uuid != null)
/*  84 */       return this.field_70170_p.func_217371_b(uuid); 
/*  85 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float amount) {
/*  91 */     PlayerEntity owner = getOwner();
/*  92 */     if (owner == null) {
/*  93 */       return false;
/*     */     }
/*     */     
/*  96 */     owner.func_70097_a(ModDamageSource.OUT_OF_BODY, amount);
/*  97 */     func_70606_j(owner.func_110143_aJ());
/*     */     
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70645_a(DamageSource cause) {
/* 104 */     PlayerEntity owner = getOwner();
/* 105 */     if (owner == null) {
/*     */       return;
/*     */     }
/*     */     
/* 109 */     owner.func_70097_a(ModDamageSource.OUT_OF_BODY, owner.func_110138_aP());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 114 */     if (!this.field_70170_p.field_72995_K) {
/* 115 */       PlayerEntity owner = getOwner();
/* 116 */       boolean hasParentAbilityActive = false;
/*     */       
/* 118 */       if (owner != null) {
/* 119 */         IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)owner);
/* 120 */         Ability parentAbilityInstance = (Ability)abilityProps.getEquippedAbility((IAbility)this.parentAbility);
/* 121 */         if (parentAbilityInstance != null && parentAbilityInstance.isContinuous()) {
/* 122 */           hasParentAbilityActive = true;
/* 123 */           if ((AbilityHelper.isAffectedByWater((LivingEntity)this) || func_184218_aH()) && getOwner() != null) {
/* 124 */             ((ContinuousComponent)parentAbilityInstance.getComponent(ModAbilityKeys.CONTINUOUS).get()).stopContinuity((LivingEntity)getOwner());
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 129 */       if (owner == null || !owner.func_70089_S() || this.parentAbility == null || !hasParentAbilityActive) {
/* 130 */         func_70106_y();
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 135 */     super.func_70071_h_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 141 */     super.func_70088_a();
/* 142 */     this.field_70180_af.func_187214_a(OWNER, Optional.empty());
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 147 */     return OPEntity.createAttributes()
/* 148 */       .func_233815_a_(Attributes.field_233818_a_, 100.0D)
/* 149 */       .func_233815_a_(Attributes.field_233820_c_, 1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT compound) {
/* 155 */     super.func_213281_b(compound);
/* 156 */     if (this.field_70180_af.func_187225_a(OWNER) != null)
/*     */     {
/* 158 */       compound.func_74778_a("OwnerUUID", ((UUID)((Optional<UUID>)this.field_70180_af.func_187225_a(OWNER)).get()).toString());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT compound) {
/* 165 */     super.func_70037_a(compound);
/* 166 */     this.field_70180_af.func_187227_b(OWNER, Optional.of(UUID.fromString(compound.func_74779_i("OwnerUUID"))));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 172 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\PhysicalBodyEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */