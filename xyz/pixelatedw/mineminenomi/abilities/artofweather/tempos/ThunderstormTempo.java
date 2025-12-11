/*    */ package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;
/*    */ import java.awt.Color;
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
/*    */ public class ThunderstormTempo extends TempoAbility {
/* 30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "thunderstorm_tempo", new Pair[] {
/* 31 */         (Pair)ImmutablePair.of("Hits multiple enemies below the cloud with multiple lightning bolts", null)
/*    */       });
/* 33 */   public static final AbilityCore<ThunderstormTempo> INSTANCE = (new AbilityCore.Builder("Thunderstorm Tempo", AbilityCategory.STYLE, AbilityType.PASSIVE, ThunderstormTempo::new))
/* 34 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { TempoAbility.getTooltip(true, 5, WeatherBallKind.THUNDER)
/* 35 */       }).addDescriptionLine(DESCRIPTION)
/* 36 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 37 */     .setUnlockCheck(ThunderstormTempo::canUnlock)
/* 38 */     .build();
/*    */   
/*    */   public ThunderstormTempo(AbilityCore<ThunderstormTempo> core) {
/* 41 */     super(core);
/*    */   }
/*    */   
/*    */   public void useTempo(LivingEntity entity, WeatherCloudEntity cloud) {
/* 45 */     List<LivingEntity> targets = WyHelper.getNearbyLiving(entity.func_213303_ch(), (IWorld)entity.field_70170_p, 25.0D, ModEntityPredicates.getEnemyFactions(entity));
/* 46 */     Collections.shuffle(targets);
/*    */     
/* 48 */     for (LivingEntity target : targets) {
/* 49 */       if (!isUnderWeatherCloud(cloud, target)) {
/*    */         continue;
/*    */       }
/* 52 */       double distance = Math.sqrt(target.func_213303_ch().func_72441_c(0.0D, 14.0D, 0.0D).func_186679_c(target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_()));
/* 53 */       if (target.func_226278_cu_() <= cloud.func_226278_cu_() && entity.func_70685_l((Entity)target)) {
/* 54 */         LightningEntity bolt = new LightningEntity((Entity)entity, target.func_226277_ct_(), target.func_226278_cu_() + 14.0D, target.func_226281_cx_(), 0.0F, 90.0F, (float)(distance + 1.0D), 5.0F, INSTANCE);
/* 55 */         bolt.setAngle(30);
/* 56 */         bolt.setBranches(6);
/* 57 */         bolt.setSegments(15);
/* 58 */         bolt.setColor(new Color(253, 208, 35, 205));
/* 59 */         bolt.setSize(0.05F);
/* 60 */         bolt.setExplosion(1);
/* 61 */         bolt.setDamage(60.0F);
/* 62 */         bolt.setMaxLife(20);
/* 63 */         entity.field_70170_p.func_217376_c((Entity)bolt);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 69 */     if (!(entity instanceof PlayerEntity)) {
/* 70 */       return false;
/*    */     }
/*    */     
/* 73 */     PlayerEntity player = (PlayerEntity)entity;
/* 74 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 75 */     IQuestData questProps = QuestDataCapability.get(player);
/*    */     
/* 77 */     return (props.isWeatherWizard() && questProps.hasFinishedQuest(ModQuests.ART_OF_WEATHER_TRIAL_03));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\tempos\ThunderstormTempo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */