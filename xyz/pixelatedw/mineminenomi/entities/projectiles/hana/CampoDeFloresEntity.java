/*     */ package xyz.pixelatedw.mineminenomi.entities.projectiles.hana;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.hana.CampoDeFloresAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ 
/*     */ public class CampoDeFloresEntity extends Entity implements IEntityAdditionalSpawnData {
/*     */   private LivingEntity owner;
/*     */   
/*     */   static {
/*  33 */     TARGETS_PREDICATE = (new TargetsPredicate()).testEnemyFaction().selector(entity -> (DevilFruitHelper.getDifferenceToFloor((Entity)entity) < 3.0D));
/*     */   } private CampoDeFloresAbility ability; private static final TargetsPredicate TARGETS_PREDICATE;
/*     */   public CampoDeFloresEntity(EntityType type, World pLevel) {
/*  36 */     super(type, pLevel);
/*     */   }
/*     */   
/*     */   public CampoDeFloresEntity(World level, CampoDeFloresAbility ability) {
/*  40 */     super((EntityType)HanaProjectiles.CAMPO_DE_FLORES.get(), level);
/*  41 */     this.ability = ability;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {}
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  50 */     super.func_70071_h_();
/*  51 */     if (!this.field_70170_p.field_72995_K) {
/*     */       
/*  53 */       if (this.field_70173_aa > 100) {
/*  54 */         func_70106_y();
/*     */         
/*     */         return;
/*     */       } 
/*  58 */       if (this.owner == null || !this.owner.func_70089_S()) {
/*  59 */         func_70106_y();
/*     */         
/*     */         return;
/*     */       } 
/*  63 */       List<LivingEntity> targets = this.ability.getComponent(ModAbilityKeys.RANGE).map(comp -> comp.getTargetsInArea(this.owner, func_233580_cy_(), 10.0F, TARGETS_PREDICATE)).orElse(new ArrayList<>());
/*     */       
/*  65 */       for (Iterator<LivingEntity> iterator = targets.iterator(); iterator.hasNext(); ) { LivingEntity target = iterator.next();
/*  66 */         boolean flag = ((Boolean)this.ability.getComponent(ModAbilityKeys.DAMAGE).map(comp -> Boolean.valueOf(comp.hurtTarget(this.owner, target, 10.0F))).orElse(Boolean.valueOf(false))).booleanValue();
/*  67 */         if (flag) {
/*  68 */           target.func_195064_c(new EffectInstance((Effect)ModEffects.DIZZY.get(), 20, 0));
/*  69 */           AbilityHelper.setDeltaMovement((Entity)target, 0.0D, 1.75D, 0.0D);
/*     */         }  }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySize func_213305_a(Pose pose) {
/*  77 */     EntitySize newSize = func_200600_R().func_220334_j().func_220312_a(20.0F, 1.0F);
/*  78 */     return newSize;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70037_a(CompoundNBT pCompound) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_213281_b(CompoundNBT pCompound) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOwner(LivingEntity owner) {
/*  92 */     this.owner = owner;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getOwner() {
/*  97 */     return this.owner;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeSpawnData(PacketBuffer buffer) {
/* 102 */     buffer.func_179252_a((this.owner != null) ? this.owner.func_110124_au() : Util.field_240973_b_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readSpawnData(PacketBuffer buffer) {
/* 107 */     this.owner = (LivingEntity)this.field_70170_p.func_217371_b(buffer.func_179253_g());
/*     */   }
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 112 */     return NetworkHooks.getEntitySpawningPacket(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\hana\CampoDeFloresEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */