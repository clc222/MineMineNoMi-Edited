/*     */ package xyz.pixelatedw.mineminenomi.entities;
/*     */ 
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
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ 
/*     */ public class BottomHalfBodyEntity extends OPEntity {
/*  31 */   private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.func_187226_a(BottomHalfBodyEntity.class, DataSerializers.field_187203_m);
/*  32 */   private Ability parentAbility = null;
/*     */   
/*     */   public BottomHalfBodyEntity(EntityType type, World world) {
/*  35 */     super(type, world);
/*     */   }
/*     */   
/*     */   public BottomHalfBodyEntity(World world) {
/*  39 */     this((EntityType)ModEntities.BOTTOM_HALF_BODY.get(), world);
/*     */   }
/*     */   
/*     */   public void setParentAbility(Ability ability) {
/*  43 */     this.parentAbility = ability;
/*     */   }
/*     */   
/*     */   public void setOwner(LivingEntity owner) {
/*  47 */     this.field_70180_af.func_187227_b(OWNER, Optional.of(owner.func_110124_au()));
/*  48 */     EntityStatsCapability.get((LivingEntity)this).setFaction(EntityStatsCapability.get(owner).getFaction());
/*  49 */     EntityStatsCapability.get((LivingEntity)this).setRace(EntityStatsCapability.get(owner).getRace());
/*     */ 
/*     */     
/*  52 */     ForgeRegistries.ATTRIBUTES.forEach(attr -> {
/*     */           if (owner.func_110148_a(attr) != null && func_110148_a(attr) != null) {
/*     */             double val = owner.func_110148_a(attr).func_111125_b();
/*     */             func_110148_a(attr).func_111128_a(val);
/*     */           } 
/*     */         });
/*  58 */     func_110148_a(Attributes.field_233821_d_).func_111128_a(0.30000001192092896D);
/*     */ 
/*     */     
/*  61 */     func_70606_j(owner.func_110143_aJ());
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public UUID getOwnerUUID() {
/*  66 */     if (((Optional)func_184212_Q().func_187225_a(OWNER)).isPresent()) {
/*  67 */       return ((Optional<UUID>)func_184212_Q().func_187225_a(OWNER)).get();
/*     */     }
/*  69 */     return null;
/*     */   }
/*     */   
/*     */   public LivingEntity getOwner() {
/*  73 */     if (this.field_70170_p.field_72995_K) {
/*  74 */       return null;
/*     */     }
/*     */     
/*  77 */     Optional<UUID> ownerUUID = (Optional<UUID>)func_184212_Q().func_187225_a(OWNER);
/*  78 */     if (ownerUUID.isPresent()) {
/*  79 */       PlayerEntity playerOwner = this.field_70170_p.func_217371_b(ownerUUID.get());
/*  80 */       if (playerOwner != null) {
/*  81 */         return (LivingEntity)playerOwner;
/*     */       }
/*     */       
/*  84 */       Entity entityOwner = ((ServerWorld)this.field_70170_p).func_217461_a(ownerUUID.get());
/*  85 */       if (entityOwner != null && entityOwner instanceof LivingEntity) {
/*  86 */         return (LivingEntity)entityOwner;
/*     */       }
/*     */     } 
/*  89 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource source, float amount) {
/*  94 */     LivingEntity owner = getOwner();
/*  95 */     if (owner == null) {
/*  96 */       return false;
/*     */     }
/*     */     
/*  99 */     owner.func_70097_a(ModDamageSource.OUT_OF_BODY, amount);
/* 100 */     func_70606_j(owner.func_110143_aJ());
/*     */     
/* 102 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70645_a(DamageSource cause) {
/* 107 */     LivingEntity owner = getOwner();
/* 108 */     if (owner == null) {
/*     */       return;
/*     */     }
/*     */     
/* 112 */     owner.func_70097_a(ModDamageSource.OUT_OF_BODY, owner.func_110138_aP());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 117 */     if (!this.field_70170_p.field_72995_K) {
/* 118 */       LivingEntity owner = getOwner();
/* 119 */       boolean hasParentAbilityActive = false;
/*     */       
/* 121 */       if (owner != null) {
/* 122 */         IAbilityData abilityProps = AbilityDataCapability.get(owner);
/* 123 */         Ability parentAbilityInstance = (Ability)abilityProps.getEquippedAbility((IAbility)this.parentAbility);
/* 124 */         if (parentAbilityInstance != null && parentAbilityInstance.isContinuous()) {
/* 125 */           hasParentAbilityActive = true;
/*     */         }
/*     */       } 
/*     */       
/* 129 */       if (owner == null || !owner.func_70089_S() || this.parentAbility == null || !hasParentAbilityActive) {
/* 130 */         func_70106_y();
/*     */         
/*     */         return;
/*     */       } 
/* 134 */       if (func_70032_d((Entity)getOwner()) > 5.0F) {
/* 135 */         func_70661_as().func_75497_a((Entity)getOwner(), 1.25D);
/*     */       }
/*     */     } 
/*     */     
/* 139 */     super.func_70071_h_();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 144 */     super.func_70088_a();
/* 145 */     func_184212_Q().func_187214_a(OWNER, Optional.empty());
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 149 */     return OPEntity.createAttributes().func_233815_a_(Attributes.field_233821_d_, 0.30000001192092896D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70039_c(CompoundNBT compound) {
/* 154 */     if (((Optional)func_184212_Q().func_187225_a(OWNER)).isPresent()) {
/* 155 */       compound.func_74778_a("OwnerUUID", ((UUID)((Optional<UUID>)func_184212_Q().func_187225_a(OWNER)).get()).toString());
/*     */     }
/* 157 */     return super.func_70039_c(compound);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70020_e(CompoundNBT compound) {
/* 162 */     super.func_70020_e(compound);
/* 163 */     func_184212_Q().func_187227_b(OWNER, Optional.of(UUID.fromString(compound.func_74779_i("OwnerUUID"))));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\BottomHalfBodyEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */