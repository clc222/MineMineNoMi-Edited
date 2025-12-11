/*    */ package xyz.pixelatedw.mineminenomi.abilities.marineloyalty;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.SpawnReason;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class MusterAbility
/*    */   extends Ability {
/* 28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "muster", new Pair[] {
/* 29 */         (Pair)ImmutablePair.of("The user musters some reinforcements.", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 1200.0F;
/* 33 */   public static final AbilityCore<MusterAbility> INSTANCE = (new AbilityCore.Builder("Muster", AbilityCategory.FACTION, MusterAbility::new))
/* 34 */     .addDescriptionLine(DESCRIPTION)
/* 35 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(1200.0F)
/* 36 */       }).setUnlockCheck(MusterAbility::canUnlock)
/* 37 */     .build();
/*    */   
/*    */   public MusterAbility(AbilityCore<MusterAbility> core) {
/* 40 */     super(core);
/*    */     
/* 42 */     this.isNew = true;
/*    */     
/* 44 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 48 */     EntityType gruntEntity = (EntityType)ModEntities.MARINE_GRUNT.get();
/* 49 */     EntityType captain = (EntityType)ModEntities.MARINE_CAPTAIN.get();
/*    */     int i;
/* 51 */     for (i = 0; i < WyHelper.randomWithRange(3, 10); i++) {
/* 52 */       BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation(entity.field_70170_p, gruntEntity, entity.func_233580_cy_(), 10);
/* 53 */       if (spawnPos != null) {
/* 54 */         gruntEntity.func_220342_a((ServerWorld)entity.field_70170_p, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 59 */     if (entity.field_70170_p instanceof ServerWorld) {
/* 60 */       for (i = 0; i < WyHelper.randomWithRange(1, 3); i++) {
/* 61 */         BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation(entity.field_70170_p, captain, entity.func_233580_cy_(), 10);
/* 62 */         captain.func_220342_a((ServerWorld)entity.field_70170_p, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
/*    */       } 
/*    */     }
/*    */     
/* 66 */     this.cooldownComponent.startCooldown(entity, 1200.0F);
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 70 */     IEntityStats props = EntityStatsCapability.get(entity);
/* 71 */     if (props.hasMarineRank(FactionHelper.MarineRank.CAPTAIN)) {
/* 72 */       return true;
/*    */     }
/*    */     
/* 75 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\marineloyalty\MusterAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */