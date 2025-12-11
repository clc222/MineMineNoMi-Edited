/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks;
/*    */ import net.minecraft.entity.CreatureEntity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifierMap;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.InProgressChallenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.FindPartnerGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.IGoalPartner;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.ChargedCleaveWrapperGoal;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class Mr4Entity extends OPBossEntity<Mr4Entity> implements IGoalPartner<MissMerryChristmasEntity> {
/*    */   public Mr4Entity(EntityType type, World world) {
/* 29 */     super(type, world);
/*    */   }
/*    */   private MissMerryChristmasEntity partner;
/*    */   public Mr4Entity(InProgressChallenge challenge) {
/* 33 */     super((EntityType)ModEntities.MR4.get(), challenge);
/*    */   }
/*    */ 
/*    */   
/*    */   public void initBoss() {
/* 38 */     ExtendedWorldData worldData = ExtendedWorldData.get();
/*    */     
/* 40 */     this.entityStats.setFaction(ModValues.PIRATE);
/* 41 */     this.entityStats.setRace(ModValues.HUMAN);
/*    */     
/* 43 */     this.entityStats.setDoriki(2000.0D);
/* 44 */     worldData.addTemporaryCrewMember(ModNPCGroups.BAROQUE_WORKS, (LivingEntity)this);
/*    */     
/* 46 */     func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_111128_a(3.0D);
/* 47 */     func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(1.0D);
/*    */     
/* 49 */     func_184201_a(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.HASSAIKAI.get()));
/*    */ 
/*    */     
/* 52 */     MobsHelper.addBasicNPCGoals((CreatureEntity)this);
/*    */     
/* 54 */     this.field_70714_bg.func_75776_a(0, (Goal)new FindPartnerGoal((MobEntity)this, (EntityType)ModEntities.MISS_MERRY_CHRISTMAS.get()));
/*    */     
/* 56 */     this.field_70714_bg.func_75776_a(1, (Goal)(new ImprovedMeleeAttackGoal((CreatureEntity)this, 0.8999999761581421D, true)).setMoveTowardsTarget(false));
/* 57 */     this.field_70714_bg.func_75776_a(3, (Goal)new ChargedCleaveWrapperGoal((MobEntity)this));
/*    */     
/* 59 */     if (getChallengeInfo().isDifficultyStandard());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 68 */     return OPEntity.createAttributes()
/* 69 */       .func_233815_a_(Attributes.field_233819_b_, 60.0D)
/* 70 */       .func_233815_a_(Attributes.field_233821_d_, 0.20000000298023224D)
/* 71 */       .func_233815_a_(Attributes.field_233823_f_, 3.0D)
/* 72 */       .func_233815_a_(Attributes.field_233818_a_, 200.0D)
/* 73 */       .func_233815_a_(Attributes.field_233826_i_, 3.0D)
/* 74 */       .func_233815_a_(Attributes.field_233827_j_, 2.0D)
/* 75 */       .func_233815_a_(Attributes.field_233820_c_, 0.8D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 80 */     super.func_70071_h_();
/*    */   }
/*    */ 
/*    */   
/*    */   public MissMerryChristmasEntity getPartner() {
/* 85 */     return this.partner;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPartner(MissMerryChristmasEntity partner) {
/* 90 */     this.partner = partner;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\baroqueworks\Mr4Entity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */