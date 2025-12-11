/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.controller.LookController;
/*     */ import net.minecraft.entity.ai.controller.MovementController;
/*     */ import net.minecraft.entity.ai.goal.FindWaterGoal;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
/*     */ import net.minecraft.entity.monster.IMob;
/*     */ import net.minecraft.entity.passive.WaterMobEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.pathfinding.PathNavigator;
/*     */ import net.minecraft.pathfinding.SwimmerPathNavigator;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.util.RegistryKey;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.biome.Biomes;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.ai.controllers.FishSwimMoveController;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.EatNearbyFishGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class PandaSharkEntity extends WaterMobEntity implements IMob, IEntityAdditionalSpawnData {
/*  50 */   private static final List<RegistryKey<Biome>> SPAWN_BIOMES = new ArrayList<>(Arrays.asList((RegistryKey<Biome>[])new RegistryKey[] { Biomes.field_76771_b, Biomes.field_150575_M, Biomes.field_203615_U, Biomes.field_203618_X, Biomes.field_203614_T, Biomes.field_203617_W }));
/*     */   
/*  52 */   private float size = 1.0F;
/*     */   
/*     */   public PandaSharkEntity(EntityType type, World world) {
/*  55 */     super(type, world);
/*  56 */     this.field_70765_h = (MovementController)new FishSwimMoveController((MobEntity)this);
/*  57 */     this.field_70749_g = (LookController)new LandFishLookController((MobEntity)this, 10);
/*     */     
/*  59 */     if (world != null && !world.field_72995_K) {
/*  60 */       this.size = 1.0F + this.field_70146_Z.nextFloat();
/*  61 */       double reach = ((1.0F - this.size) * 30.0F);
/*  62 */       func_110148_a((Attribute)ModAttributes.ATTACK_RANGE.get()).func_111128_a(-reach);
/*  63 */       func_110148_a(Attributes.field_233818_a_).func_111128_a(func_233637_b_(Attributes.field_233818_a_) * Math.max(this.size, 0.5D));
/*     */       
/*  65 */       func_70606_j((float)func_110148_a(Attributes.field_233818_a_).func_111126_e());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_184651_r() {
/*  71 */     this.field_70714_bg.func_75776_a(0, (Goal)new FindWaterGoal((CreatureEntity)this));
/*  72 */     this.field_70714_bg.func_75776_a(0, (Goal)new AvoidBlockTagGoal((CreatureEntity)this, (ITag.INamedTag)ModTags.Blocks.KAIROSEKI));
/*  73 */     this.field_70714_bg.func_75776_a(0, (Goal)new AvoidCoatedBoatGoal((CreatureEntity)this));
/*  74 */     this.field_70714_bg.func_75776_a(0, (Goal)new EatNearbyFishGoal((MobEntity)this));
/*  75 */     this.field_70714_bg.func_75776_a(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.7999999523162842D, true));
/*  76 */     this.field_70714_bg.func_75776_a(1, (Goal)new RandomSwimmingGoal((CreatureEntity)this, 1.0D, 10));
/*  77 */     this.field_70714_bg.func_75776_a(1, (Goal)new LookRandomlyGoal((MobEntity)this));
/*  78 */     this.field_70714_bg.func_75776_a(2, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 12.0F));
/*     */     
/*  80 */     this.field_70715_bh.func_75776_a(1, (Goal)new HurtByTargetGoal((CreatureEntity)this, new Class[0]));
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/*  84 */     return MobEntity.func_233666_p_()
/*  85 */       .func_233815_a_(Attributes.field_233821_d_, 1.2999999523162842D)
/*  86 */       .func_233815_a_(Attributes.field_233819_b_, 55.0D)
/*  87 */       .func_233815_a_(Attributes.field_233823_f_, 5.0D)
/*  88 */       .func_233815_a_(Attributes.field_233818_a_, 20.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySize func_213305_a(Pose pose) {
/*  93 */     float fishScale = getSize();
/*  94 */     EntitySize newSize = func_200600_R().func_220334_j().func_220313_a(fishScale);
/*  95 */     return newSize;
/*     */   }
/*     */   
/*     */   public float getSize() {
/*  99 */     return this.size;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_70646_bf() {
/* 104 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_184649_cE() {
/* 109 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70648_aU() {
/* 114 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_184652_a(PlayerEntity pPlayer) {
/* 119 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected PathNavigator func_175447_b(World level) {
/* 124 */     if (WyHelper.isAprilFirst()) {
/* 125 */       return super.func_175447_b(level);
/*     */     }
/* 127 */     return (PathNavigator)new SwimmerPathNavigator((MobEntity)this, level);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213352_e(Vector3d moveVector) {
/* 132 */     if (func_70613_aW() && func_70090_H()) {
/* 133 */       func_213309_a(func_70689_ay(), moveVector);
/* 134 */       func_213315_a(MoverType.SELF, func_213322_ci());
/* 135 */       AbilityHelper.setDeltaMovement((Entity)this, func_213322_ci().func_186678_a(0.9D));
/* 136 */       if (func_70638_az() == null) {
/* 137 */         AbilityHelper.setDeltaMovement((Entity)this, func_213322_ci().func_72441_c(0.0D, -0.005D, 0.0D));
/*     */       }
/*     */     } else {
/*     */       
/* 141 */       super.func_213352_e(moveVector);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT nbt) {
/* 147 */     super.func_213281_b(nbt);
/* 148 */     nbt.func_74776_a("size", this.size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT nbt) {
/* 153 */     super.func_70037_a(nbt);
/* 154 */     this.size = nbt.func_74760_g("size");
/*     */   }
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 159 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeSpawnData(PacketBuffer buffer) {
/* 164 */     buffer.writeFloat(this.size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readSpawnData(PacketBuffer buffer) {
/* 169 */     this.size = buffer.readFloat();
/*     */   }
/*     */   
/*     */   public static boolean checkSpawnRules(EntityType<PandaSharkEntity> entity, IWorld world, SpawnReason spawnType, BlockPos pos, Random random) {
/* 173 */     if (pos.func_177956_o() >= world.func_181545_F() - 10) {
/* 174 */       return false;
/*     */     }
/*     */     
/* 177 */     return world.func_204610_c(pos).func_206884_a((ITag)FluidTags.field_206959_a);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\animals\PandaSharkEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */