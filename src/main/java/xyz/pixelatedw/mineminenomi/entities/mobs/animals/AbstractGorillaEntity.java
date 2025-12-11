/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
/*     */ import net.minecraft.entity.merchant.villager.VillagerEntity;
/*     */ import net.minecraft.entity.monster.MonsterEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.JumpOutOfHoleGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.SprintTowardsTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.BellyFlopWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.SlamWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public abstract class AbstractGorillaEntity extends CreatureEntity implements IEntityAdditionalSpawnData {
/*     */   private float size;
/*     */   
/*     */   protected AbstractGorillaEntity(EntityType entity, World world) {
/*  52 */     super(entity, world);
/*     */     
/*  54 */     if (world != null && !world.field_72995_K) {
/*  55 */       func_184201_a(EquipmentSlotType.MAINHAND, getRandomAxe(MobsHelper.GORILLA_AXES));
/*     */       
/*  57 */       double attackDamage = func_110148_a(Attributes.field_233823_f_).func_111125_b();
/*  58 */       double maxHealth = func_110148_a(Attributes.field_233818_a_).func_111125_b();
/*     */       
/*  60 */       func_110148_a(Attributes.field_233823_f_).func_111128_a(attackDamage + ((getSize() > 1.0F) ? getSize() : 0.0F));
/*  61 */       func_110148_a(Attributes.field_233818_a_).func_111128_a(maxHealth + (getSize() * 10.0F));
/*  62 */       func_110148_a((Attribute)ModAttributes.ATTACK_RANGE.get()).func_111128_a((0.0F + getSize() * 0.5F));
/*     */       
/*  64 */       func_70606_j((float)func_110148_a(Attributes.field_233818_a_).func_111126_e());
/*     */       
/*  66 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/*  67 */       props.setDoriki(1500.0D + WyHelper.randomWithRange(world.func_201674_k(), 0, 500));
/*  68 */       props.setBelly(0L);
/*     */       
/*  70 */       this.field_70714_bg.func_75776_a(0, (Goal)new SwimGoal((MobEntity)this));
/*  71 */       this.field_70714_bg.func_75776_a(0, (Goal)new JumpOutOfHoleGoal((MobEntity)this));
/*  72 */       if (this.field_70146_Z.nextFloat() < 0.6F) {
/*  73 */         this.field_70714_bg.func_75776_a(0, (Goal)new SprintTowardsTargetGoal((MobEntity)this));
/*     */       }
/*  75 */       this.field_70714_bg.func_75776_a(0, (Goal)new ImprovedMeleeAttackGoal(this, 1.25D, true));
/*  76 */       if (this.field_70146_Z.nextBoolean()) {
/*  77 */         this.field_70714_bg.func_75776_a(2, (Goal)new SlamWrapperGoal((MobEntity)this));
/*     */       } else {
/*     */         
/*  80 */         this.field_70714_bg.func_75776_a(2, (Goal)new BellyFlopWrapperGoal((MobEntity)this));
/*     */       } 
/*  82 */       this.field_70714_bg.func_75776_a(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
/*  83 */       this.field_70714_bg.func_75776_a(4, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/*  84 */       this.field_70714_bg.func_75776_a(4, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */       
/*  86 */       this.field_70715_bh.func_75776_a(1, (Goal)new HurtByTargetGoal(this, new Class[0]));
/*  87 */       this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, PlayerEntity.class, true));
/*  88 */       this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, OPEntity.class, true));
/*  89 */       this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, VillagerEntity.class, true));
/*  90 */       this.field_70715_bh.func_75776_a(3, (Goal)new NearestAttackableTargetGoal((MobEntity)this, MonsterEntity.class, true));
/*  91 */       this.field_70715_bh.func_75776_a(3, (Goal)new NearestAttackableTargetGoal((MobEntity)this, HumandrillEntity.class, true));
/*     */     } 
/*     */   }
/*     */   private static final float MIN_SIZE = 2.5F; private static final float MAX_SIZE = 3.5F;
/*     */   
/*     */   protected void func_70088_a() {
/*  97 */     super.func_70088_a();
/*  98 */     this.size = Math.min(2.5F + this.field_70146_Z.nextFloat(), 3.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySize func_213305_a(Pose pose) {
/* 103 */     float scale = getSize() / 1.3F;
/* 104 */     EntitySize newSize = func_200600_R().func_220334_j().func_220313_a(scale);
/* 105 */     return newSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70636_d() {
/* 110 */     func_82168_bl();
/* 111 */     super.func_70636_d();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT compound) {
/* 116 */     super.func_213281_b(compound);
/* 117 */     compound.func_74776_a("size", this.size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT compound) {
/* 122 */     super.func_70037_a(compound);
/* 123 */     this.size = compound.func_74760_g("size");
/*     */   }
/*     */   
/*     */   public ItemStack getRandomAxe(List<Supplier<? extends Item>> list) {
/* 127 */     return new ItemStack(((Supplier<IItemProvider>)list.get(this.field_70146_Z.nextInt(list.size()))).get());
/*     */   }
/*     */   
/*     */   public float getSize() {
/* 131 */     return this.size;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent func_184639_G() {
/* 136 */     return SoundEvents.field_190026_er;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent func_184601_bQ(DamageSource pDamageSource) {
/* 141 */     return SoundEvents.field_219680_ht;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent func_184615_bR() {
/* 146 */     return SoundEvents.field_219673_hm;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180429_a(BlockPos pos, BlockState block) {
/* 151 */     func_184185_a(SoundEvents.field_190030_ev, 0.15F, 1.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70599_aP() {
/* 156 */     return 0.4F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70647_i() {
/* 161 */     return func_70631_g_() ? (0.5F + this.field_70146_Z.nextFloat() / 2.0F) : (0.2F + this.field_70146_Z.nextFloat() / 3.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 166 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeSpawnData(PacketBuffer buffer) {
/* 171 */     buffer.writeFloat(this.size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readSpawnData(PacketBuffer buffer) {
/* 176 */     this.size = buffer.readFloat();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\animals\AbstractGorillaEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */