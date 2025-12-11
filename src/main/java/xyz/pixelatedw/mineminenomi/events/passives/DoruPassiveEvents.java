/*     */ package xyz.pixelatedw.mineminenomi.events.passives;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.vector.Quaternion;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent;
/*     */ import net.minecraftforge.client.event.RenderPlayerEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doru.DoruDoruBallAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.models.abilities.CandleLockModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.abilities.SphereModel;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class DoruPassiveEvents {
/*     */   @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*     */   public static class ClientEvents {
/*  34 */     private static final String[] COLORS = new String[] { "#c21d1f", "#8f176b", "#4d178f", "#17508d", "#158d7b", "#128d21", "#c8cb17", "#5ae163" };
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  39 */     private static Color randomColor1 = chooseRandomColor();
/*  40 */     private static Color randomColor2 = chooseRandomColor();
/*     */ 
/*     */ 
/*     */     
/*     */     private static Color chooseRandomColor() {
/*  45 */       int i = (int)WyHelper.randomWithRange(0, COLORS.length - 1);
/*  46 */       String hex = COLORS[i];
/*  47 */       return WyHelper.hexToRGB(hex);
/*     */     }
/*     */     
/*  50 */     private static final SphereModel DORU_BALL = new SphereModel();
/*  51 */     private static final CandleLockModel CANDLE_LOCK = new CandleLockModel();
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onEntityRendered(RenderLivingEvent.Pre event) {
/*  56 */       LivingEntity entity = event.getEntity();
/*     */       
/*  58 */       Color color = Color.WHITE;
/*  59 */       if (!entity.func_70644_a((Effect)ModEffects.CANDLE_LOCK.get())) {
/*     */         return;
/*     */       }
/*  62 */       if (entity.func_70660_b((Effect)ModEffects.CANDLE_LOCK.get()).func_76459_b() <= 0) {
/*     */         
/*  64 */         entity.func_195063_d((Effect)ModEffects.CANDLE_LOCK.get());
/*     */         
/*     */         return;
/*     */       } 
/*  68 */       if (entity.func_70660_b((Effect)ModEffects.CANDLE_LOCK.get()).func_76458_c() == 2) {
/*  69 */         color = randomColor1;
/*     */       }
/*  71 */       event.getMatrixStack().func_227860_a_();
/*     */       
/*  73 */       event.getMatrixStack().func_227861_a_(0.0D, -0.8D, 0.0D);
/*  74 */       event.getMatrixStack().func_227863_a_(new Quaternion(Vector3f.field_229180_c_, entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * event.getPartialRenderTick() + 180.0F, true));
/*     */       
/*  76 */       CANDLE_LOCK.func_225598_a_(event.getMatrixStack(), event.getBuffers().getBuffer(ModRenderTypes.getAbilityHand(ModResources.CANDLE_LOCK)), event.getLight(), 0, color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 1.0F);
/*     */       
/*  78 */       event.getMatrixStack().func_227865_b_();
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onPlayerRendered(RenderPlayerEvent.Pre event) {
/*  83 */       PlayerEntity player = event.getPlayer();
/*     */       
/*  85 */       IAbilityData data = AbilityDataCapability.get((LivingEntity)player);
/*     */       
/*  87 */       if (data == null) {
/*     */         return;
/*     */       }
/*     */       
/*  91 */       Color color = Color.WHITE;
/*     */       
/*  93 */       DoruDoruBallAbility ability = (DoruDoruBallAbility)data.getEquippedAbility(DoruDoruBallAbility.INSTANCE);
/*     */       
/*  95 */       if (ability != null && ability.isContinuous()) {
/*  96 */         event.setCanceled(true);
/*  97 */         if ((event.getPlayer()).field_71071_by.func_70431_c(new ItemStack((IItemProvider)ModItems.COLOR_PALETTE.get()))) {
/*  98 */           color = randomColor2;
/*     */         }
/* 100 */         float zoanHeight = 1.0F;
/* 101 */         MorphInfo info = MorphHelper.getZoanInfo((LivingEntity)player);
/* 102 */         if (info != null)
/*     */         {
/* 104 */           zoanHeight = ((EntitySize)info.getSizes().get(Pose.STANDING)).field_220316_b;
/*     */         }
/*     */         
/* 107 */         ability.rotateAngleX += (player.func_213322_ci()).field_72449_c;
/* 108 */         ability.rotateAngleZ -= (player.func_213322_ci()).field_72450_a;
/* 109 */         event.getMatrixStack().func_227860_a_();
/*     */         
/* 111 */         event.getMatrixStack().func_227861_a_(0.0D, (player.func_70047_e() - 0.5F), 0.0D);
/* 112 */         float scale = 8.0F + zoanHeight;
/* 113 */         event.getMatrixStack().func_227862_a_(scale, scale, scale);
/* 114 */         DORU_BALL.setRotateAngle(DORU_BALL.shape1, (float)ability.rotateAngleX, 0.0F, (float)ability.rotateAngleZ);
/* 115 */         DORU_BALL.func_225598_a_(event.getMatrixStack(), event.getBuffers().getBuffer(ModRenderTypes.getAbilityBody(ModResources.CANDLE_LOCK)), event.getLight(), 0, color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 1.0F);
/*     */         
/* 117 */         event.getMatrixStack().func_227865_b_();
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\passives\DoruPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */