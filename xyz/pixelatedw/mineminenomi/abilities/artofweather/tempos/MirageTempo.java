/*    */ package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.WeatherBallKind;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.MirageCloneEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.MirageTempoCloudEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ 
/*    */ public class MirageTempo extends ChargedTempoAbility {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "mirage_tempo", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("Creates several clones of the user", null)
/*    */       });
/* 28 */   public static final AbilityCore<MirageTempo> INSTANCE = (new AbilityCore.Builder("Mirage Tempo", AbilityCategory.STYLE, AbilityType.PASSIVE, MirageTempo::new))
/* 29 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { CHARGED_TEMPO_DESCRIPTION
/* 30 */       }).addDescriptionLine(DESCRIPTION)
/* 31 */     .setUnlockCheck(MirageTempo::canUnlock)
/* 32 */     .build();
/*    */   
/*    */   public MirageTempo(AbilityCore<MirageTempo> core) {
/* 35 */     super(core);
/*    */   }
/*    */ 
/*    */   
/*    */   public WeatherBallKind[] getTempoOrder() {
/* 40 */     return new WeatherBallKind[] { WeatherBallKind.COOL, WeatherBallKind.COOL, WeatherBallKind.HEAT };
/*    */   }
/*    */ 
/*    */   
/*    */   public void useTempo(LivingEntity entity) {
/* 45 */     MirageTempoCloudEntity smokeCloud = new MirageTempoCloudEntity(entity.field_70170_p);
/* 46 */     smokeCloud.setLife(50);
/* 47 */     smokeCloud.func_70012_b(entity.func_226277_ct_(), entity.func_226278_cu_() + 1.0D, entity.func_226281_cx_(), 0.0F, 0.0F);
/* 48 */     AbilityHelper.setDeltaMovement((Entity)smokeCloud, 0.0D, 0.0D, 0.0D);
/* 49 */     smokeCloud.setThrower(entity);
/* 50 */     entity.field_70170_p.func_217376_c((Entity)smokeCloud);
/*    */     
/* 52 */     for (int i = 0; i < 5; i++) {
/* 53 */       MirageCloneEntity mirageClone = new MirageCloneEntity(entity.field_70170_p);
/* 54 */       mirageClone.func_70012_b(entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), 180.0F, 0.0F);
/* 55 */       mirageClone.setOwner(entity);
/* 56 */       mirageClone.func_70604_c(entity);
/* 57 */       mirageClone.setMaxAliveTicks(300);
/* 58 */       for (EquipmentSlotType slot : EquipmentSlotType.values()) {
/* 59 */         ItemStack stack = entity.func_184582_a(slot);
/* 60 */         mirageClone.func_184201_a(slot, stack);
/*    */       } 
/* 62 */       entity.field_70170_p.func_217376_c((Entity)mirageClone);
/*    */     } 
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 67 */     if (!(entity instanceof PlayerEntity)) {
/* 68 */       return false;
/*    */     }
/*    */     
/* 71 */     PlayerEntity player = (PlayerEntity)entity;
/* 72 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 73 */     IQuestData questProps = QuestDataCapability.get(player);
/*    */     
/* 75 */     return (props.isWeatherWizard() && questProps.hasFinishedQuest(ModQuests.ART_OF_WEATHER_TRIAL_02));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\tempos\MirageTempo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */