/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifierMap;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.TrainerEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class DoctorTrainerEntity extends TrainerEntity implements IHakiTrainer {
/*     */   public DoctorTrainerEntity(EntityType type, World world) {
/*  37 */     super(type, world, MobsHelper.DOCTOR_TRAINER_TEXTURES);
/*  38 */     EntityStatsCapability.get((LivingEntity)this).setFightingStyle(ModValues.DOCTOR);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_184651_r() {
/*  44 */     super.func_184651_r();
/*     */     
/*  46 */     this.field_70715_bh.func_75776_a(2, (Goal)new HurtByTargetGoal((CreatureEntity)this, new Class[0]));
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/*  51 */     return OPEntity.createAttributes()
/*  52 */       .func_233815_a_(Attributes.field_233819_b_, 35.0D)
/*  53 */       .func_233815_a_(Attributes.field_233821_d_, 0.23999999463558197D)
/*  54 */       .func_233815_a_(Attributes.field_233823_f_, 1.0D)
/*  55 */       .func_233815_a_(Attributes.field_233818_a_, 50.0D)
/*  56 */       .func_233815_a_(Attributes.field_233826_i_, 2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/*  62 */     super.func_70088_a();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData func_213386_a(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/*  69 */     spawnData = super.func_213386_a(world, difficulty, reason, spawnData, dataTag);
/*     */     
/*  71 */     if (this.field_70146_Z.nextDouble() < 0.4D) {
/*     */       
/*  73 */       ItemStack medicBag = new ItemStack((IItemProvider)ModArmors.MEDIC_BAG.get());
/*  74 */       func_184201_a(EquipmentSlotType.CHEST, medicBag);
/*     */     } 
/*     */     
/*  77 */     setDoriki(1000.0D + WyHelper.randomWithRange(0, 500));
/*  78 */     setBelly(10.0D + WyHelper.randomWithRange(0, 10));
/*     */     
/*  80 */     func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(12.0D);
/*     */     
/*  82 */     return spawnData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<QuestId> getAvailableQuests(PlayerEntity player) {
/*  88 */     IEntityStats entityProps = EntityStatsCapability.get((LivingEntity)player);
/*  89 */     List<QuestId> availableQuests = new ArrayList<>();
/*     */     
/*  91 */     if (entityProps.isDoctor()) {
/*  92 */       availableQuests.addAll((Collection<? extends QuestId>)ModQuests.DOCTOR_TRIALS.stream().map(reg -> reg).collect(Collectors.toList()));
/*     */     }
/*  94 */     return availableQuests;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public HakiType getTrainingHaki() {
/* 100 */     return HakiType.KENBUNSHOKU;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\quest\givers\DoctorTrainerEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */