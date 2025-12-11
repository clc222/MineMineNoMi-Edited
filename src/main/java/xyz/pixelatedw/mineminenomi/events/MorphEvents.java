/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import com.google.common.base.Strings;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.client.event.RenderHandEvent;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent;
/*     */ import net.minecraftforge.event.entity.EntityEvent;
/*     */ import net.minecraftforge.event.entity.EntityMountEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SkinOverlayComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.morphs.ZoanMorphRenderer2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MorphEvents
/*     */ {
/*     */   @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*     */   public static class Client
/*     */   {
/*     */     @SubscribeEvent
/*     */     public static void onEntityRendered(RenderLivingEvent.Pre event) {
/*  59 */       IDevilFruit props = DevilFruitCapability.get(event.getEntity());
/*     */       
/*  61 */       if (!Strings.isNullOrEmpty(props.getZoanPoint())) {
/*     */         try {
/*  63 */           MorphInfo info = MorphHelper.getZoanInfo(event.getEntity());
/*  64 */           if (info != null) {
/*  65 */             event.setCanceled(true);
/*  66 */             EntityRenderer<LivingEntity> renderer = info.getRendererFactory(event.getEntity()).createRenderFor(Minecraft.func_71410_x().func_175598_ae());
/*  67 */             if (renderer instanceof ZoanMorphRenderer2) {
/*  68 */               ((ZoanMorphRenderer2)renderer).setOriginalRenderer(event.getRenderer());
/*     */             }
/*  70 */             renderer.func_225623_a_((Entity)event.getEntity(), (event.getEntity()).field_70177_z, event.getPartialRenderTick(), event.getMatrixStack(), event.getBuffers(), event.getLight());
/*     */           }
/*     */         
/*  73 */         } catch (Exception e) {
/*     */           
/*  75 */           event.setCanceled(false);
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onHandRendering(RenderHandEvent event) {
/*  83 */       if (event.getHand() != Hand.MAIN_HAND) {
/*     */         return;
/*     */       }
/*     */       
/*  87 */       ClientPlayerEntity clientPlayerEntity = (Minecraft.func_71410_x()).field_71439_g;
/*  88 */       if (clientPlayerEntity == null) {
/*     */         return;
/*     */       }
/*     */       
/*  92 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)clientPlayerEntity);
/*  93 */       IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)clientPlayerEntity);
/*     */       
/*  95 */       if (abilityProps == null) {
/*     */         return;
/*     */       }
/*     */       
/*  99 */       boolean renderHand = false;
/* 100 */       boolean hasEmptyHand = clientPlayerEntity.func_184614_ca().func_190926_b();
/*     */       
/* 102 */       MorphInfo info = MorphHelper.getZoanInfo((LivingEntity)clientPlayerEntity);
/* 103 */       boolean isOverlay = false;
/* 104 */       Optional<AbilityOverlay> overlay = Optional.empty();
/* 105 */       for (IAbility ability : abilityProps.getEquippedAbilities()) {
/* 106 */         Optional<SkinOverlayComponent> comp = ability.getComponent(ModAbilityKeys.SKIN_OVERLAY);
/* 107 */         if (comp.isPresent()) {
/*     */           
/* 109 */           overlay = ((SkinOverlayComponent)comp.get()).getShownOverlay(AbilityOverlay.OverlayPart.values());
/* 110 */           if (overlay.isPresent()) {
/* 111 */             isOverlay = hasEmptyHand;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 116 */         if (info != null) {
/* 117 */           renderHand = true;
/*     */         }
/*     */       } 
/*     */       
/* 121 */       boolean isZoan = (hasEmptyHand && renderHand);
/* 122 */       boolean isBlackLeg = (props.isBlackLeg() && hasEmptyHand && (props.isInCombatMode() || ClientConfig.INSTANCE.isBlackLegAlwaysUp()));
/*     */       
/* 124 */       if (isZoan || isOverlay || isBlackLeg) {
/* 125 */         event.setCanceled(true);
/* 126 */         MorphHelper.renderLimbFirstPerson(event.getMatrixStack(), event.getBuffers(), event.getLight(), event.getEquipProgress(), event.getSwingProgress(), clientPlayerEntity.func_184591_cq(), overlay, info);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   @EventBusSubscriber(modid = "mineminenomi")
/*     */   public static class Common {
/*     */     @SubscribeEvent
/*     */     public static void onZoanSizeChange(EntityEvent.Size event) {
/* 135 */       if (!(event.getEntity() instanceof LivingEntity)) {
/*     */         return;
/*     */       }
/*     */       
/* 139 */       LivingEntity living = (LivingEntity)event.getEntity();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 152 */       if (living.func_70047_e() <= 0.0F) {
/*     */         return;
/*     */       }
/*     */       
/* 156 */       IDevilFruit props = DevilFruitCapability.get(living);
/*     */       
/* 158 */       Pose pose = living.func_213283_Z();
/* 159 */       EntitySize size = living.func_213305_a(pose);
/*     */       
/* 161 */       float eyeHeight = living.getEyeHeightAccess(pose, size);
/*     */       
/* 163 */       MorphInfo info = null;
/*     */       
/* 165 */       if (!Strings.isNullOrEmpty(props.getZoanPoint())) {
/* 166 */         info = MorphHelper.getZoanInfo(living);
/*     */         
/* 168 */         if (info == null) {
/*     */           return;
/*     */         }
/*     */         
/* 172 */         if (info.getEyeHeight() <= 0.0D) {
/*     */           return;
/*     */         }
/*     */         
/* 176 */         eyeHeight = (float)(1.62D * info.getEyeHeight() / 1.75D);
/* 177 */         if (eyeHeight < 0.22F) {
/* 178 */           eyeHeight = 0.22F;
/*     */         }
/*     */       } 
/*     */       
/* 182 */       if (info != null && living.func_213453_ef()) {
/* 183 */         eyeHeight -= 0.3F;
/* 184 */         eyeHeight = (float)Math.max(0.3D, eyeHeight);
/*     */       } 
/*     */       
/* 187 */       event.setNewEyeHeight(eyeHeight);
/*     */     }
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onZoanMounts(EntityMountEvent event) {
/* 193 */       if (event.getEntityBeingMounted() == null) {
/*     */         return;
/*     */       }
/* 196 */       if (event.getEntityMounting() instanceof PlayerEntity) {
/*     */         
/* 198 */         PlayerEntity player = (PlayerEntity)event.getEntityMounting();
/* 199 */         MorphInfo info = MorphHelper.getZoanInfo((LivingEntity)player);
/*     */         
/* 201 */         if (info != null && !info.canMount()) {
/*     */           
/* 203 */           player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_CANNOT_MOUNT_ZOAN), Util.field_240973_b_);
/* 204 */           event.setCanceled(true);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\MorphEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */