/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifierMap;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
/*     */ import net.minecraft.entity.monster.MonsterEntity;
/*     */ import net.minecraft.entity.passive.AnimalEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
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
/*     */ import xyz.pixelatedw.mineminenomi.api.util.WeightedList;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.SprintTowardsTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.GapCloserGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.ChargedPunchWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.SuplexWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.brawler.TackleWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.HiryuKaenWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.ShiShishiSonsonWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.swordsman.YakkodoriWrapperGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class HumandrillEntity extends CreatureEntity implements IEntityAdditionalSpawnData {
/*  58 */   protected static final List<Supplier<? extends Item>> SWORDS = Arrays.asList((Supplier<? extends Item>[])new Supplier[] { (Supplier)ModWeapons.MARINE_SWORD, () -> Items.field_151040_l, () -> Items.field_151052_q, (Supplier)ModWeapons.SANDAI_KITETSU, (Supplier)ModWeapons.NIDAI_KITETSU, (Supplier)ModWeapons.WADO_ICHIMONJI, () -> Items.field_151048_u });
/*     */   
/*     */   private static final float MIN_SIZE = 0.8F;
/*     */   private static final float MAX_SIZE = 2.0F;
/*     */   private float size;
/*     */   
/*     */   public HumandrillEntity(EntityType type, World world) {
/*  65 */     super(type, world);
/*     */     
/*  67 */     if (world != null && !world.field_72995_K) {
/*  68 */       boolean hasSword = false;
/*  69 */       if (func_70681_au().nextDouble() < 0.7D) {
/*     */         ItemStack randomSword;
/*     */         
/*  72 */         if (this.field_70146_Z.nextDouble() < 0.9D) {
/*  73 */           randomSword = new ItemStack(((Supplier<IItemProvider>)SWORDS.get(this.field_70146_Z.nextInt(SWORDS.size()))).get());
/*     */         } else {
/*     */           
/*  76 */           this.size = 2.5F;
/*     */           
/*  78 */           randomSword = new ItemStack((IItemProvider)ModWeapons.YORU.get());
/*     */         } 
/*     */         
/*  81 */         randomSword.func_196082_o().func_74757_a("isClone", true);
/*     */         
/*  83 */         func_184201_a(EquipmentSlotType.MAINHAND, randomSword);
/*  84 */         hasSword = true;
/*     */       } 
/*     */       
/*  87 */       func_110148_a(Attributes.field_233823_f_).func_111128_a((3.0F + ((getSize() > 1.0F) ? getSize() : 0.0F)));
/*  88 */       func_110148_a(Attributes.field_233818_a_).func_111128_a((60.0F + getSize() * 10.0F));
/*  89 */       func_110148_a(Attributes.field_233820_c_).func_111128_a((getSize() * 0.2F));
/*  90 */       func_110148_a((Attribute)ModAttributes.ATTACK_RANGE.get()).func_111128_a((1.2F + getSize() * 0.5F));
/*     */       
/*  92 */       func_70606_j((float)func_110148_a(Attributes.field_233818_a_).func_111126_e());
/*     */       
/*  94 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/*  95 */       props.setDoriki(1500.0D + WyHelper.randomWithRange(300, 600));
/*  96 */       props.setBelly(0L);
/*     */       
/*  98 */       this.field_70714_bg.func_75776_a(0, (Goal)new SwimGoal((MobEntity)this));
/*  99 */       if (this.field_70146_Z.nextFloat() < 0.6F) {
/* 100 */         this.field_70714_bg.func_75776_a(0, (Goal)new SprintTowardsTargetGoal((MobEntity)this));
/*     */       }
/* 102 */       this.field_70714_bg.func_75776_a(0, (Goal)new JumpOutOfHoleGoal((MobEntity)this));
/* 103 */       this.field_70714_bg.func_75776_a(0, (Goal)new ImprovedMeleeAttackGoal(this, 1.0D, true));
/* 104 */       this.field_70714_bg.func_75776_a(2, (Goal)new GapCloserGoal((MobEntity)this));
/* 105 */       this.field_70714_bg.func_75776_a(4, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
/* 106 */       this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/* 107 */       this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, AnimalEntity.class, 8.0F));
/* 108 */       this.field_70714_bg.func_75776_a(5, (Goal)new LookAtGoal((MobEntity)this, HumandrillEntity.class, 8.0F));
/* 109 */       this.field_70714_bg.func_75776_a(5, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */       
/* 111 */       this.field_70715_bh.func_75776_a(1, (Goal)new HurtByTargetGoal(this, new Class[0]));
/* 112 */       this.field_70715_bh.func_75776_a(1, (Goal)new PersonalSpaceTargetGoal((MobEntity)this));
/* 113 */       this.field_70715_bh.func_75776_a(3, (Goal)new NearestAttackableTargetGoal((MobEntity)this, MonsterEntity.class, true));
/* 114 */       this.field_70715_bh.func_75776_a(3, (Goal)new NearestAttackableTargetGoal((MobEntity)this, LapahnEntity.class, true));
/*     */       
/* 116 */       if (hasSword) {
/* 117 */         WeightedList<Supplier<Goal>> goals = new WeightedList(new Object[0]);
/* 118 */         goals.addEntry(() -> new HiryuKaenWrapperGoal((MobEntity)this), 4.0F);
/* 119 */         goals.addEntry(() -> new ShiShishiSonsonWrapperGoal((MobEntity)this), 3.0F);
/* 120 */         goals.addEntry(() -> new YakkodoriWrapperGoal((MobEntity)this), 1.0F);
/*     */         
/* 122 */         MobsHelper.getRandomizedGoals((MobEntity)this, 2, goals).forEach(goal -> this.field_70714_bg.func_75776_a(3, goal));
/*     */       } else {
/*     */         
/* 125 */         WeightedList<Supplier<Goal>> goals = new WeightedList(new Object[0]);
/* 126 */         goals.addEntry(() -> new ChargedPunchWrapperGoal((MobEntity)this), 4.0F);
/* 127 */         goals.addEntry(() -> new TackleWrapperGoal((MobEntity)this), 2.0F);
/* 128 */         goals.addEntry(() -> new SuplexWrapperGoal((MobEntity)this), 1.0F);
/*     */         
/* 130 */         MobsHelper.getRandomizedGoals((MobEntity)this, 2, goals).forEach(goal -> this.field_70714_bg.func_75776_a(3, goal));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 136 */     return OPEntity.createAttributes()
/* 137 */       .func_233815_a_(Attributes.field_233819_b_, 80.0D)
/* 138 */       .func_233815_a_((Attribute)ModAttributes.FALL_RESISTANCE.get(), 10.0D)
/* 139 */       .func_233815_a_(Attributes.field_233821_d_, 0.27000001072883606D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 144 */     super.func_70088_a();
/* 145 */     this.size = Math.min(0.8F + this.field_70146_Z.nextFloat(), 2.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySize func_213305_a(Pose pose) {
/* 150 */     float scale = getSize();
/* 151 */     EntitySize newSize = func_200600_R().func_220334_j().func_220313_a(scale);
/* 152 */     return newSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT compound) {
/* 157 */     super.func_213281_b(compound);
/* 158 */     compound.func_74776_a("size", this.size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT compound) {
/* 163 */     super.func_70037_a(compound);
/* 164 */     this.size = compound.func_74760_g("size");
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent func_184639_G() {
/* 169 */     return SoundEvents.field_190026_er;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent func_184601_bQ(DamageSource pDamageSource) {
/* 174 */     return SoundEvents.field_219680_ht;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent func_184615_bR() {
/* 179 */     return SoundEvents.field_219673_hm;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180429_a(BlockPos pos, BlockState block) {
/* 184 */     func_184185_a(SoundEvents.field_190030_ev, 0.15F, 1.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70599_aP() {
/* 189 */     return 0.4F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70647_i() {
/* 194 */     return func_70631_g_() ? (0.5F + this.field_70146_Z.nextFloat() / 2.0F) : (0.2F + this.field_70146_Z.nextFloat() / 3.0F);
/*     */   }
/*     */   
/*     */   public float getSize() {
/* 198 */     return this.size;
/*     */   }
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 203 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeSpawnData(PacketBuffer buffer) {
/* 208 */     buffer.writeFloat(this.size);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readSpawnData(PacketBuffer buffer) {
/* 213 */     this.size = buffer.readFloat();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\animals\HumandrillEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */