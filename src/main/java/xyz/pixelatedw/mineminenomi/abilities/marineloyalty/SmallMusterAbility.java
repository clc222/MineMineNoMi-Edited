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
/*    */ public class SmallMusterAbility
/*    */   extends Ability {
/* 28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "small_muster", new Pair[] {
/* 29 */         (Pair)ImmutablePair.of("The user musters a few low level reinforcements.", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 400.0F;
/* 33 */   public static final AbilityCore<SmallMusterAbility> INSTANCE = (new AbilityCore.Builder("Small Muster", AbilityCategory.FACTION, SmallMusterAbility::new))
/* 34 */     .addDescriptionLine(DESCRIPTION)
/* 35 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(400.0F)
/* 36 */       }).setUnlockCheck(SmallMusterAbility::canUnlock)
/* 37 */     .build();
/*    */   
/*    */   public SmallMusterAbility(AbilityCore<SmallMusterAbility> core) {
/* 40 */     super(core);
/*    */     
/* 42 */     this.isNew = true;
/*    */     
/* 44 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 48 */     EntityType gruntEntity = (EntityType)ModEntities.MARINE_GRUNT.get();
/* 49 */     for (int i = 0; i < WyHelper.randomWithRange(1, 5); i++) {
/* 50 */       BlockPos spawnPos = WyHelper.findOnGroundSpawnLocation(entity.field_70170_p, gruntEntity, entity.func_233580_cy_(), 10);
/* 51 */       if (spawnPos != null) {
/* 52 */         gruntEntity.func_220342_a((ServerWorld)entity.field_70170_p, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, spawnPos, SpawnReason.EVENT, false, false);
/*    */       }
/*    */     } 
/*    */     
/* 56 */     this.cooldownComponent.startCooldown(entity, 400.0F);
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 60 */     IEntityStats props = EntityStatsCapability.get(entity);
/* 61 */     if (props.hasMarineRank(FactionHelper.MarineRank.LIEUTENANT)) {
/* 62 */       return true;
/*    */     }
/*    */     
/* 65 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\marineloyalty\SmallMusterAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */