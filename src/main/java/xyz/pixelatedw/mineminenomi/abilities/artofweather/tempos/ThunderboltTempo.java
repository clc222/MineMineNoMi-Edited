/*    */ package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.world.IWorld;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.WeatherBallKind;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.WeatherCloudEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ThunderboltTempo extends TempoAbility {
/* 32 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "thunderbolt_tempo", new Pair[] {
/* 33 */         (Pair)ImmutablePair.of("Hits a single enemy below the cloud with lightning bolt", null)
/*    */       });
/* 35 */   public static final AbilityCore<ThunderboltTempo> INSTANCE = (new AbilityCore.Builder("Thunderbolt Tempo", AbilityCategory.STYLE, AbilityType.PASSIVE, ThunderboltTempo::new))
/* 36 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { TempoAbility.getTooltip(true, 1, WeatherBallKind.THUNDER)
/* 37 */       }).addDescriptionLine(DESCRIPTION)
/* 38 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 39 */     .setUnlockCheck(ThunderboltTempo::canUnlock)
/* 40 */     .build();
/*    */   
/*    */   public ThunderboltTempo(AbilityCore<ThunderboltTempo> core) {
/* 43 */     super(core);
/*    */   }
/*    */   
/*    */   public void useTempo(LivingEntity entity, WeatherCloudEntity cloud) {
/* 47 */     List<LivingEntity> targets = WyHelper.getNearbyLiving(entity.func_213303_ch(), (IWorld)entity.field_70170_p, 25.0D, ModEntityPredicates.getEnemyFactions(entity));
/* 48 */     Collections.shuffle(targets);
/*    */     
/* 50 */     for (LivingEntity target : targets) {
/* 51 */       if (!isUnderWeatherCloud(cloud, target)) {
/*    */         continue;
/*    */       }
/* 54 */       double distance = Math.sqrt(target.func_213303_ch().func_72441_c(0.0D, 14.0D, 0.0D).func_186679_c(target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_()));
/* 55 */       if (target.func_226278_cu_() <= entity.func_226278_cu_() && entity.func_70685_l((Entity)target)) {
/* 56 */         LightningEntity bolt = new LightningEntity((Entity)entity, target.func_226277_ct_(), target.func_226278_cu_() + 14.0D, target.func_226281_cx_(), 0.0F, 90.0F, (float)(distance + 1.0D), 5.0F, INSTANCE);
/* 57 */         bolt.setAngle(30);
/* 58 */         bolt.setBranches(6);
/* 59 */         bolt.setSegments(15);
/* 60 */         bolt.setColor(new Color(253, 208, 35, 205));
/* 61 */         bolt.setSize(0.035F);
/* 62 */         bolt.setExplosion(0, false);
/* 63 */         bolt.setDamage(40.0F);
/* 64 */         bolt.setMaxLife(10);
/* 65 */         entity.field_70170_p.func_217376_c((Entity)bolt);
/*    */         break;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 72 */     if (!(entity instanceof PlayerEntity)) {
/* 73 */       return false;
/*    */     }
/*    */     
/* 76 */     PlayerEntity player = (PlayerEntity)entity;
/* 77 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 78 */     IQuestData questProps = QuestDataCapability.get(player);
/*    */     
/* 80 */     return (props.isWeatherWizard() && questProps.hasFinishedQuest(ModQuests.ART_OF_WEATHER_TRIAL_02));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\tempos\ThunderboltTempo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */