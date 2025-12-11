/*     */ package xyz.pixelatedw.mineminenomi.events.passives;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.awt.Color;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.particles.BlockParticleData;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.math.vector.Matrix4f;
/*     */ import net.minecraft.util.math.vector.Quaternion;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHealEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.eventbus.api.EventPriority;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.yomi.YomiNoReikiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.ability.YomiTriggerEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAdvancements;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*     */ import xyz.pixelatedw.mineminenomi.models.morphs.YomiModel;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.morphs.ZoanMorphRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class YomiPassiveEvents {
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void onRenderOverlay(RenderGameOverlayEvent.Pre event) {
/*  67 */     ClientPlayerEntity clientPlayerEntity = (Minecraft.func_71410_x()).field_71439_g;
/*  68 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)clientPlayerEntity);
/*     */     
/*  70 */     if (event.getType() == RenderGameOverlayEvent.ElementType.FOOD && props.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) && ((MorphInfo)ModMorphs.YOMI_SKELETON.get()).isActive((LivingEntity)clientPlayerEntity)) {
/*  71 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onClonePlayer(PlayerEvent.Clone event) {
/*  77 */     if (event.isWasDeath()) {
/*  78 */       IDevilFruit oldPlayerProps = DevilFruitCapability.get((LivingEntity)event.getOriginal());
/*  79 */       IDevilFruit newPlayerProps = DevilFruitCapability.get((LivingEntity)event.getPlayer());
/*     */       
/*  81 */       YomiTriggerEvent yomiEvent = new YomiTriggerEvent(event.getPlayer(), oldPlayerProps, newPlayerProps);
/*  82 */       MinecraftForge.EVENT_BUS.post((Event)yomiEvent);
/*     */       
/*  84 */       if (event.getPlayer() instanceof ServerPlayerEntity) {
/*  85 */         ModAdvancements.YOMI_REVIVE.trigger((ServerPlayerEntity)event.getOriginal());
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/*  92 */     if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*     */     
/*  96 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*     */     
/*  98 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/*     */     
/* 100 */     if (!devilFruitProps.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) || !((MorphInfo)ModMorphs.YOMI_SKELETON.get()).isActive((LivingEntity)player)) {
/*     */       return;
/*     */     }
/*     */     
/* 104 */     if (!player.field_70170_p.field_72995_K) {
/* 105 */       if (player.func_71024_bL().func_75116_a() <= 18)
/*     */       {
/*     */ 
/*     */         
/* 109 */         player.func_71024_bL().func_75114_a(18);
/*     */       }
/*     */       
/* 112 */       player.func_195064_c(new EffectInstance(Effects.field_76424_c, 10, player.func_70051_ag() ? 4 : 0, false, false));
/*     */       
/* 114 */       if (player.field_70173_aa % 500 == 0) {
/* 115 */         WyNetwork.sendTo(new SSyncDevilFruitPacket(player.func_145782_y(), devilFruitProps), player);
/* 116 */         WyNetwork.sendToAllAround(new SSyncDevilFruitPacket(player.func_145782_y(), devilFruitProps), (Entity)player);
/*     */       } 
/*     */     } 
/*     */     
/* 120 */     BlockState state = player.field_70170_p.func_180495_p(player.func_233580_cy_().func_177977_b());
/* 121 */     boolean isRunning = (Math.abs((player.func_213322_ci()).field_72450_a) >= 0.15D || Math.abs((player.func_213322_ci()).field_72449_c) >= 0.15D);
/* 122 */     if (state.func_204520_s().func_206889_d() && state.func_185904_a().equals(Material.field_151586_h) && isRunning) {
/* 123 */       Vector3d moveVec = player.func_70040_Z().func_72432_b().func_216372_d(0.5D, 0.0D, 0.5D);
/*     */       
/* 125 */       if (player.field_191988_bg == 0.0D) {
/*     */         return;
/*     */       }
/*     */       
/* 129 */       AbilityHelper.setDeltaMovement((Entity)player, moveVec);
/*     */       
/* 131 */       player.field_70143_R = 0.0F;
/*     */       
/* 133 */       if (!player.field_70170_p.field_72995_K) {
/* 134 */         for (int i = 0; i < 10; i++) {
/* 135 */           double newPosX = player.func_226277_ct_() + WyHelper.randomDouble();
/* 136 */           double newPosY = player.func_226278_cu_();
/* 137 */           double newPosZ = player.func_226281_cx_() + WyHelper.randomDouble();
/*     */           
/* 139 */           ((ServerWorld)player.field_70170_p).func_195598_a((IParticleData)new BlockParticleData(ParticleTypes.field_197611_d, state), newPosX, newPosY, newPosZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onHeal(LivingHealEvent event) {
/* 147 */     if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*     */     
/* 151 */     PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/* 152 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/*     */     
/* 154 */     if (!devilFruitProps.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) || !((MorphInfo)ModMorphs.YOMI_SKELETON.get()).isActive((LivingEntity)player)) {
/*     */       return;
/*     */     }
/*     */     
/* 158 */     event.setAmount(event.getAmount());
/*     */   }
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent(priority = EventPriority.HIGH)
/*     */   public static void onEntityRendered(RenderLivingEvent.Pre event) {
/* 164 */     if (!(event.getEntity() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*     */     
/* 168 */     PlayerEntity player = (PlayerEntity)event.getEntity();
/*     */     
/* 170 */     if (!isSpirit(player)) {
/*     */       return;
/*     */     }
/*     */     
/* 174 */     MorphInfo info = MorphHelper.getZoanInfo((LivingEntity)player);
/* 175 */     if (info == null) {
/*     */       return;
/*     */     }
/*     */     
/* 179 */     ZoanMorphRenderer render = (ZoanMorphRenderer)info.getRendererFactory().createRenderFor(Minecraft.func_71410_x().func_175598_ae());
/* 180 */     IVertexBuilder vertex = event.getBuffers().getBuffer(RenderType.func_228644_e_(render.func_110775_a((AbstractClientPlayerEntity)player)));
/* 181 */     event.setCanceled(true);
/*     */     
/* 183 */     event.getMatrixStack().func_227860_a_();
/*     */     
/* 185 */     event.getMatrixStack().func_227861_a_(0.0D, 1.5D, 0.0D);
/* 186 */     event.getMatrixStack().func_227863_a_(new Quaternion(Vector3f.field_229179_b_, 180.0F, true));
/* 187 */     event.getMatrixStack().func_227863_a_(new Quaternion(Vector3f.field_229181_d_, 180.0F, true));
/* 188 */     event.getMatrixStack().func_227863_a_(new Quaternion(Vector3f.field_229181_d_, player.field_70126_B + (player.field_70177_z - player.field_70126_B) * event
/* 189 */           .getPartialRenderTick() - 180.0F, true));
/* 190 */     event.getMatrixStack().func_227863_a_(new Quaternion(Vector3f.field_229179_b_, player.field_70127_C + (player.field_70125_A - player.field_70127_C) * event.getPartialRenderTick(), true));
/*     */     
/* 192 */     ((YomiModel)render.func_217764_d()).field_78116_c.func_228309_a_(event.getMatrixStack(), vertex, event.getLight(), OverlayTexture.field_229196_a_, 0.3F, 0.9F, 0.5F, 0.6F);
/*     */     
/* 194 */     event.getMatrixStack().func_227860_a_();
/*     */     
/* 196 */     IVertexBuilder vertexBuilder = event.getBuffers().getBuffer(RenderType.func_228657_l_());
/* 197 */     event.getMatrixStack().func_227861_a_(0.0D, -0.35D, 0.0D);
/* 198 */     float randMovement = ((player.field_70173_aa / 200) + event.getPartialRenderTick()) / 500.0F;
/* 199 */     for (int i = 0; i < 100; i++) {
/* 200 */       event.getMatrixStack().func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(player.func_70681_au().nextFloat() * 360.0F));
/* 201 */       event.getMatrixStack().func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(player.func_70681_au().nextFloat() * 360.0F));
/* 202 */       event.getMatrixStack().func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(player.func_70681_au().nextFloat() * 360.0F));
/* 203 */       event.getMatrixStack().func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(player.func_70681_au().nextFloat() * 360.0F));
/* 204 */       event.getMatrixStack().func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(player.func_70681_au().nextFloat() * 360.0F));
/* 205 */       event.getMatrixStack().func_227863_a_(Vector3f.field_229183_f_.func_229187_a_(player.func_70681_au().nextFloat() * 360.0F + randMovement * 90.0F));
/* 206 */       float f3 = 0.6F * player.func_70681_au().nextFloat();
/* 207 */       float f4 = 0.6F * player.func_70681_au().nextFloat();
/* 208 */       Matrix4f matrix4f = event.getMatrixStack().func_227866_c_().func_227870_a_();
/*     */       
/* 210 */       int alpha = 5;
/* 211 */       Color primaryColor = new Color(0, 255, 0, alpha);
/* 212 */       Color secondaryColor = new Color(0, 255, 50, alpha);
/*     */       
/* 214 */       RendererHelper.drawA(vertexBuilder, matrix4f, primaryColor);
/* 215 */       RendererHelper.drawB(vertexBuilder, matrix4f, f3, f4, secondaryColor);
/* 216 */       RendererHelper.drawC(vertexBuilder, matrix4f, f3, f4, secondaryColor);
/* 217 */       RendererHelper.drawA(vertexBuilder, matrix4f, primaryColor);
/* 218 */       RendererHelper.drawC(vertexBuilder, matrix4f, f3, f4, secondaryColor);
/* 219 */       RendererHelper.drawD(vertexBuilder, matrix4f, f3, f4, secondaryColor);
/* 220 */       RendererHelper.drawA(vertexBuilder, matrix4f, primaryColor);
/* 221 */       RendererHelper.drawD(vertexBuilder, matrix4f, f3, f4, secondaryColor);
/* 222 */       RendererHelper.drawB(vertexBuilder, matrix4f, f3, f4, secondaryColor);
/*     */     } 
/*     */     
/* 225 */     event.getMatrixStack().func_227865_b_();
/*     */     
/* 227 */     event.getMatrixStack().func_227865_b_();
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onDrinkMilk(LivingEntityUseItemEvent.Finish event) {
/* 232 */     if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*     */     
/* 236 */     IDevilFruit devilFruitProps = DevilFruitCapability.get(event.getEntityLiving());
/*     */     
/* 238 */     if (!devilFruitProps.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI)) {
/*     */       return;
/*     */     }
/*     */     
/* 242 */     if (event.getItem().func_77973_b() == Items.field_151117_aB && ((MorphInfo)ModMorphs.YOMI_SKELETON.get()).isActive(event.getEntityLiving())) {
/* 243 */       event.getEntityLiving().func_70691_i(8.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   private static boolean isSpirit(PlayerEntity player) {
/* 248 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 250 */     if (abilityProps == null) {
/* 251 */       return false;
/*     */     }
/*     */     
/* 254 */     if (player.func_184812_l_() || player.func_175149_v()) {
/* 255 */       return false;
/*     */     }
/*     */     
/* 258 */     IAbility ability = abilityProps.getEquippedAbility(YomiNoReikiAbility.INSTANCE);
/*     */     
/* 260 */     if (ability == null) {
/* 261 */       return false;
/*     */     }
/*     */     
/* 264 */     Optional<ContinuousComponent> comp = ability.getComponent(ModAbilityKeys.CONTINUOUS);
/*     */     
/* 266 */     return (comp.isPresent() && ((ContinuousComponent)comp.get()).isContinuous());
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\passives\YomiPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */