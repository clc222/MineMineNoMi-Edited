/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifierMap;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.MeleeAttackGoal;
/*     */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
/*     */ import net.minecraft.entity.passive.RabbitEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.LeapAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BonusOperation;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.LeapWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.lapahn.LapahnChaseGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.lapahn.LapahnRageGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.lapahn.LapahnRestGoal;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class LapahnEntity extends CreatureEntity {
/*  46 */   private static final UUID LEAP_COOLDOWN_BONUS_UUID = UUID.fromString("5711f1b8-d776-44fb-9cd7-afdbd51a9837");
/*  47 */   private static final DataParameter<Byte> DATA_FLAGS_ID = EntityDataManager.func_187226_a(LapahnEntity.class, DataSerializers.field_187191_a);
/*     */   
/*     */   private static final int JUMP_ANIM_CYCLE = 20;
/*     */   private static final byte JUMP_EVENT = 100;
/*  51 */   private int clientJumpTick = 0;
/*  52 */   private int jumpTick = 0;
/*     */   
/*     */   public LapahnEntity(EntityType type, World world) {
/*  55 */     super(type, world);
/*     */     
/*  57 */     if (world != null && !world.field_72995_K) {
/*     */       
/*  59 */       LeapWrapperGoal leapWrapper = new LeapWrapperGoal((MobEntity)this);
/*  60 */       ((LeapAbility)leapWrapper.getAbility()).setLeapHeight(0.4D);
/*  61 */       ((LeapAbility)leapWrapper.getAbility()).getComponent(ModAbilityKeys.COOLDOWN).ifPresent(comp -> comp.getBonusManager().addBonus(LEAP_COOLDOWN_BONUS_UUID, "Leap Cooldown Bonus", BonusOperation.MUL, 0.5F));
/*     */ 
/*     */ 
/*     */       
/*  65 */       this.field_70714_bg.func_75776_a(0, (Goal)new JumpOutOfHoleGoal((MobEntity)this));
/*  66 */       this.field_70714_bg.func_75776_a(0, (Goal)new SwimGoal((MobEntity)this));
/*  67 */       this.field_70714_bg.func_75776_a(0, (Goal)new LapahnChaseGoal(this));
/*  68 */       this.field_70714_bg.func_75776_a(0, (Goal)new LapahnRageGoal(this));
/*  69 */       this.field_70714_bg.func_75776_a(0, (Goal)new LapahnRestGoal(this));
/*     */       
/*  71 */       this.field_70714_bg.func_75776_a(1, (Goal)new MeleeAttackGoal(this, 1.0D, true));
/*  72 */       this.field_70714_bg.func_75776_a(1, (Goal)leapWrapper);
/*  73 */       this.field_70714_bg.func_75776_a(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
/*  74 */       this.field_70714_bg.func_75776_a(4, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/*  75 */       this.field_70714_bg.func_75776_a(4, (Goal)new LookAtGoal((MobEntity)this, RabbitEntity.class, 8.0F));
/*  76 */       this.field_70714_bg.func_75776_a(4, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */       
/*  78 */       this.field_70715_bh.func_75776_a(1, (Goal)new HurtByTargetGoal(this, new Class[0]));
/*  79 */       this.field_70715_bh.func_75776_a(1, (Goal)new PersonalSpaceTargetGoal((MobEntity)this));
/*  80 */       this.field_70715_bh.func_75776_a(3, (Goal)new NearestAttackableTargetGoal((MobEntity)this, HumandrillEntity.class, true));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/*  85 */     return OPEntity.createAttributes()
/*  86 */       .func_233815_a_(Attributes.field_233819_b_, 55.0D)
/*  87 */       .func_233815_a_(Attributes.field_233821_d_, 0.2D)
/*  88 */       .func_233815_a_(Attributes.field_233823_f_, 5.0D)
/*  89 */       .func_233815_a_(Attributes.field_233818_a_, 60.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70636_d() {
/*  94 */     super.func_70636_d();
/*  95 */     if (this.field_70170_p.field_72995_K) {
/*  96 */       if (this.clientJumpTick > 0) {
/*  97 */         this.clientJumpTick--;
/*     */       
/*     */       }
/*     */     }
/* 101 */     else if (isIdling() && --this.jumpTick <= 0 && func_233570_aj_()) {
/* 102 */       this.field_70170_p.func_72960_a((Entity)this, (byte)100);
/* 103 */       func_70683_ar().func_75660_a();
/* 104 */       func_184185_a(SoundEvents.field_190030_ev, 0.2F, 0.5F);
/* 105 */       this.jumpTick = 20;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List<LapahnEntity> getNearbyLapahns() {
/* 111 */     List<LapahnEntity> targets = WyHelper.getNearbyLiving(func_213303_ch(), (IWorld)this.field_70170_p, 16.0D, LapahnEntity.class::isInstance);
/* 112 */     targets.remove(this);
/* 113 */     return targets;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 118 */     super.func_70088_a();
/* 119 */     func_184212_Q().func_187214_a(DATA_FLAGS_ID, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void func_70103_a(byte id) {
/* 125 */     switch (id) {
/*     */       case 100:
/* 127 */         this.clientJumpTick = 20;
/*     */         break;
/*     */     } 
/* 130 */     super.func_70103_a(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public ILivingEntityData func_213386_a(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/* 135 */     spawnData = super.func_213386_a(world, difficulty, reason, spawnData, dataTag);
/*     */     
/* 137 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/*     */     
/* 139 */     props.setDoriki(1000.0D + WyHelper.randomWithRange(0, 500));
/* 140 */     props.setBelly(0L);
/*     */     
/* 142 */     return spawnData;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT nbt) {
/* 147 */     super.func_213281_b(nbt);
/* 148 */     nbt.func_74757_a("isEnraged", isEnraged());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT nbt) {
/* 153 */     super.func_70037_a(nbt);
/* 154 */     setEnraged(nbt.func_74767_n("isEnraged"));
/*     */   }
/*     */   
/*     */   public void setEnraged(boolean set) {
/* 158 */     setFlag(0, set);
/*     */   }
/*     */   
/*     */   public void setChasing(boolean flag) {
/* 162 */     setFlag(1, flag);
/* 163 */     if (flag) {
/* 164 */       setResting(false);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setResting(boolean flag) {
/* 169 */     setFlag(2, flag);
/*     */   }
/*     */   
/*     */   private void setFlag(int flag, boolean set) {
/* 173 */     byte b0 = ((Byte)this.field_70180_af.func_187225_a(DATA_FLAGS_ID)).byteValue();
/* 174 */     if (set) {
/* 175 */       this.field_70180_af.func_187227_b(DATA_FLAGS_ID, Byte.valueOf((byte)(b0 | 1 << flag)));
/*     */     } else {
/*     */       
/* 178 */       this.field_70180_af.func_187227_b(DATA_FLAGS_ID, Byte.valueOf((byte)(b0 & (1 << flag ^ 0xFFFFFFFF))));
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean getFlag(int id) {
/* 183 */     return ((((Byte)this.field_70180_af.func_187225_a(DATA_FLAGS_ID)).byteValue() & 1 << id) != 0);
/*     */   }
/*     */   
/*     */   public boolean isIdling() {
/* 187 */     return (!isResting() && !isChasing());
/*     */   }
/*     */   
/*     */   public boolean isEnraged() {
/* 191 */     return getFlag(0);
/*     */   }
/*     */   
/*     */   public boolean isChasing() {
/* 195 */     return getFlag(1);
/*     */   }
/*     */   
/*     */   public boolean isResting() {
/* 199 */     return getFlag(2);
/*     */   }
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public float getJumpAnimationProgress(float partialTicks) {
/* 204 */     return ((20 - this.clientJumpTick) + partialTicks) / 20.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\animals\LapahnEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */