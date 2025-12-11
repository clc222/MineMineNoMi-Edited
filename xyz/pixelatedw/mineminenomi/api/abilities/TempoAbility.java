/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.Pose;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.ability.AbilityUseEvent;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.WeatherCloudEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ 
/*    */ public abstract class TempoAbility extends PassiveAbility2 {
/*    */   public static AbilityDescriptionLine.IDescriptionLine getTooltip(boolean requiresCloud, int amount, WeatherBallKind kind) {
/* 17 */     return (e, a) -> {
/*    */         TranslationTextComponent tooltip = new TranslationTextComponent(ModI18n.ABILITY_DESCRIPTION_TEMPO_SAME_TYPE_COMBINATION, new Object[] { Integer.valueOf(amount), kind.toString() });
/*    */         if (requiresCloud) {
/*    */           tooltip.func_240702_b_("\n");
/*    */           tooltip.func_230529_a_((ITextComponent)ModI18n.ABILITY_DESCRIPTION_TEMPO_REQUIRES_WEATHER_CLOUD);
/*    */         } 
/*    */         return (ITextComponent)tooltip;
/*    */       };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static AbilityDescriptionLine.IDescriptionLine getTooltip(boolean requiresCloud, WeatherBallKind... balls) {
/* 31 */     return (e, a) -> {
/*    */         WeatherBallKind previousKind = null;
/*    */         boolean sameType = true;
/*    */         for (WeatherBallKind kind : balls) {
/*    */           if (previousKind == null) {
/*    */             previousKind = kind;
/*    */           } else if (!kind.equals(previousKind)) {
/*    */             sameType = false;
/*    */             break;
/*    */           } 
/*    */         } 
/*    */         TranslationTextComponent tooltip = null;
/*    */         if (sameType) {
/*    */           tooltip = new TranslationTextComponent(ModI18n.ABILITY_DESCRIPTION_TEMPO_SAME_TYPE_COMBINATION, new Object[] { Integer.valueOf(balls.length), balls[0].toString() });
/*    */         }
/*    */         if (balls.length == 2) {
/*    */           tooltip = new TranslationTextComponent(ModI18n.ABILITY_DESCRIPTION_TEMPO_COMBINATION_2, new Object[] { balls[0].toString(), balls[1].toString() });
/*    */         } else if (balls.length == 3) {
/*    */           tooltip = new TranslationTextComponent(ModI18n.ABILITY_DESCRIPTION_TEMPO_COMBINATION_3, new Object[] { balls[0].toString(), balls[1].toString(), balls[2].toString() });
/*    */         } 
/*    */         if (tooltip != null && requiresCloud) {
/*    */           tooltip.func_240702_b_("\n");
/*    */           tooltip.func_230529_a_((ITextComponent)ModI18n.ABILITY_DESCRIPTION_TEMPO_REQUIRES_WEATHER_CLOUD);
/*    */         } 
/*    */         return (ITextComponent)tooltip;
/*    */       };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TempoAbility(AbilityCore<?> core) {
/* 70 */     super(core);
/* 71 */     setDisplayIcon(ModResources.TEMPO_ICON);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(LivingEntity entity) {
/* 76 */     AbilityUseEvent.Pre pre = new AbilityUseEvent.Pre(entity, this);
/* 77 */     if (MinecraftForge.EVENT_BUS.post((Event)pre)) {
/*    */       return;
/*    */     }
/*    */     
/* 81 */     useTempo(entity);
/*    */     
/* 83 */     AbilityUseEvent.Post post = new AbilityUseEvent.Post(entity, this);
/* 84 */     MinecraftForge.EVENT_BUS.post((Event)post);
/*    */   }
/*    */ 
/*    */   
/*    */   public void useTempo(LivingEntity entity) {}
/*    */   
/*    */   public AbilityUseResult canUse(LivingEntity entity) {
/* 91 */     return super.canUse(entity);
/*    */   }
/*    */   
/*    */   public boolean isUnderWeatherCloud(WeatherCloudEntity cloud, LivingEntity target) {
/* 95 */     Vector3d cloudPos = new Vector3d(cloud.func_226277_ct_(), target.func_226278_cu_(), cloud.func_226281_cx_());
/* 96 */     if (Math.sqrt(target.func_195048_a(cloudPos)) < ((cloud.func_213305_a(Pose.STANDING)).field_220315_a / 2.0F)) {
/* 97 */       return true;
/*    */     }
/* 99 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\TempoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */