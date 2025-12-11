/*    */ package xyz.pixelatedw.mineminenomi.events.items;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.WyLivingDamageEvent;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class BubblyCoralEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onBubblyCoralCheck(LivingEvent.LivingUpdateEvent event) {
/* 18 */     if (event.getEntityLiving().func_70644_a((Effect)ModEffects.BUBBLY_CORAL.get()))
/*    */     {
/* 20 */       event.getEntityLiving().func_70050_g(event.getEntityLiving().func_205010_bg());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void livingDamage(WyLivingDamageEvent event) {
/* 27 */     if (event.getEntityLiving().func_70644_a((Effect)ModEffects.BUBBLY_CORAL.get())) {
/*    */       
/* 29 */       float val = ((EffectInstance)Objects.<EffectInstance>requireNonNull(event.getEntityLiving().func_70660_b((Effect)ModEffects.BUBBLY_CORAL.get()))).func_76459_b() / 4500.0F;
/*    */       
/* 31 */       if (Math.random() > val)
/* 32 */         event.getEntityLiving().func_195063_d((Effect)ModEffects.BUBBLY_CORAL.get()); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\items\BubblyCoralEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */