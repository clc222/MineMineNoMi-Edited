/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.ILivingEntityData;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.SpawnReason;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifierMap;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.world.DifficultyInstance;
/*    */ import net.minecraft.world.IServerWorld;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.TrainerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ArtOfWeatherTrainerEntity extends TrainerEntity implements IHakiTrainer {
/*    */   public ArtOfWeatherTrainerEntity(EntityType type, World world) {
/* 37 */     super(type, world, MobsHelper.WEATHER_TRAINER_TEXTURES);
/* 38 */     EntityStatsCapability.get((LivingEntity)this).setFightingStyle(ModValues.ART_OF_WEATHER);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_184651_r() {
/* 44 */     super.func_184651_r();
/*    */     
/* 46 */     this.field_70715_bh.func_75776_a(2, (Goal)new HurtByTargetGoal((CreatureEntity)this, new Class[0]));
/*    */   }
/*    */ 
/*    */   
/*    */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 51 */     return OPEntity.createAttributes()
/* 52 */       .func_233815_a_(Attributes.field_233819_b_, 35.0D)
/* 53 */       .func_233815_a_(Attributes.field_233821_d_, 0.2199999988079071D)
/* 54 */       .func_233815_a_(Attributes.field_233823_f_, 2.0D)
/* 55 */       .func_233815_a_(Attributes.field_233818_a_, 50.0D)
/* 56 */       .func_233815_a_(Attributes.field_233826_i_, 2.0D)
/* 57 */       .func_233815_a_((Attribute)ModAttributes.TOUGHNESS.get(), 12.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_70088_a() {
/* 63 */     super.func_70088_a();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public ILivingEntityData func_213386_a(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/* 70 */     spawnData = super.func_213386_a(world, difficulty, reason, spawnData, dataTag);
/*    */     
/* 72 */     if (this.field_70146_Z.nextDouble() < 0.4D) {
/*    */       
/* 74 */       ItemStack climaTact = new ItemStack((IItemProvider)ModWeapons.CLIMA_TACT.get());
/* 75 */       func_184201_a(EquipmentSlotType.MAINHAND, climaTact);
/*    */     } 
/*    */     
/* 78 */     setDoriki(1000.0D + WyHelper.randomWithRange(0, 1000));
/* 79 */     setBelly(20.0D + WyHelper.randomWithRange(0, 10));
/*    */     
/* 81 */     return spawnData;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<QuestId> getAvailableQuests(PlayerEntity player) {
/* 87 */     IEntityStats entityProps = EntityStatsCapability.get((LivingEntity)player);
/* 88 */     List<QuestId> availableQuests = new ArrayList<>();
/*    */     
/* 90 */     if (entityProps.isWeatherWizard()) {
/* 91 */       availableQuests.addAll((Collection<? extends QuestId>)ModQuests.ART_OF_WEATHER_TRIALS.stream().map(reg -> reg).collect(Collectors.toList()));
/*    */     }
/* 93 */     return availableQuests;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public HakiType getTrainingHaki() {
/* 99 */     return HakiType.KENBUNSHOKU;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\quest\givers\ArtOfWeatherTrainerEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */