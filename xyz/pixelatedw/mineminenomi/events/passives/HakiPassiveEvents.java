/*     */ package xyz.pixelatedw.mineminenomi.events.passives;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.settings.PointOfView;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.math.vector.Quaternion;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.client.event.EntityViewRenderEvent;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent;
/*     */ import net.minecraftforge.event.TickEvent;
/*     */ import net.minecraftforge.event.entity.item.ItemTossEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiAuraAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.ability.RenderMorphEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class HakiPassiveEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
/*  46 */     if (event.getEntityLiving() instanceof PlayerEntity) {
/*  47 */       PlayerEntity playerEntity = (PlayerEntity)event.getEntityLiving();
/*  48 */       ItemStack heldItem = playerEntity.func_184614_ca();
/*     */       
/*  50 */       boolean hasDefaultImbuing = false;
/*  51 */       if (heldItem.func_77973_b() instanceof xyz.pixelatedw.mineminenomi.items.weapons.ModSwordItem && heldItem.func_77973_b() instanceof xyz.pixelatedw.mineminenomi.items.weapons.AbilitySwordItem) {
/*  52 */         hasDefaultImbuing = true;
/*  53 */       } else if (!(heldItem.func_77973_b() instanceof xyz.pixelatedw.mineminenomi.items.weapons.ModSwordItem)) {
/*  54 */         hasDefaultImbuing = heldItem.func_77984_f();
/*     */       } 
/*     */       
/*  57 */       if (!heldItem.func_190926_b() && hasDefaultImbuing) {
/*  58 */         if (HakiHelper.hasImbuingActive((LivingEntity)playerEntity) && !heldItem.func_196082_o().func_74767_n("imbuingHakiActive")) {
/*  59 */           heldItem.func_196082_o().func_74757_a("imbuingHakiActive", true);
/*  60 */         } else if (heldItem.func_196082_o().func_74764_b("imbuingHakiActive") && !HakiHelper.hasImbuingActive((LivingEntity)playerEntity) && heldItem.func_196082_o().func_74767_n("imbuingHakiActive")) {
/*  61 */           heldItem.func_196082_o().func_82580_o("imbuingHakiActive");
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onItemTossed(ItemTossEvent event) {
/*  71 */     ItemStack stack = event.getEntityItem().func_92059_d();
/*  72 */     if (stack != null && !stack.func_190926_b() && stack.func_77942_o() && stack.func_77978_p().func_74767_n("imbuingHakiActive")) {
/*  73 */       stack.func_196082_o().func_82580_o("imbuingHakiActive");
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
/*  79 */     if (event.phase != TickEvent.Phase.END || event.player.field_70170_p.field_72995_K || event.player.field_70173_aa % 20 != 0) {
/*     */       return;
/*     */     }
/*  82 */     PlayerEntity player = event.player;
/*     */     
/*  84 */     IAbilityData ablData = AbilityDataCapability.get((LivingEntity)player);
/*  85 */     IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
/*  86 */     float maxOveruse = hakiProps.getMaxOveruse();
/*  87 */     float hakiOveruse = hakiProps.getHakiOveruse();
/*     */     
/*  89 */     if (maxOveruse <= 0.0F) {
/*     */       return;
/*     */     }
/*     */     
/*  93 */     if (hakiOveruse >= maxOveruse * 0.9D) {
/*     */       
/*  95 */       player.func_195064_c(new EffectInstance((Effect)ModEffects.HAKI_OVERUSE.get(), 40, 0));
/*     */ 
/*     */       
/*  98 */       if (hakiOveruse >= maxOveruse * 0.95D) {
/*     */         
/* 100 */         player.func_195064_c(new EffectInstance((Effect)ModEffects.HAKI_OVERUSE.get(), 80, 1));
/*     */         
/* 102 */         if (hakiOveruse >= maxOveruse)
/*     */         {
/* 104 */           player.func_195064_c(new EffectInstance((Effect)ModEffects.HAKI_OVERUSE.get(), 100, 2));
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 114 */     boolean hasAnyHaki = false;
/*     */     
/* 116 */     if (ablData == null) {
/*     */       return;
/*     */     }
/*     */     
/* 120 */     for (IAbility abl : ablData.getEquippedAbilities(AbilityCategory.HAKI.isAbilityPartofCategory().and(a -> !(a instanceof KenbunshokuHakiAuraAbility)))) {
/* 121 */       if (abl == null) {
/*     */         continue;
/*     */       }
/*     */       
/* 125 */       Optional<ContinuousComponent> comp = abl.getComponent(ModAbilityKeys.CONTINUOUS);
/* 126 */       if (comp.isPresent() && ((ContinuousComponent)comp.get()).isContinuous()) {
/* 127 */         hasAnyHaki = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 132 */     if (hasAnyHaki) {
/*     */       return;
/*     */     }
/* 135 */     int overuseHeal = (int)(-Math.max(5.0F, hakiProps.getTotalHakiExp() / 30.0F) * 6.0F);
/* 136 */     hakiProps.alterHakiOveruse(overuseHeal);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void onZoanRendered(RenderMorphEvent.Pre event) {
/* 145 */     if (event.getEntity() == null) {
/*     */       return;
/*     */     }
/* 148 */     LivingEntity entity = event.getEntityLiving();
/*     */     
/* 150 */     if (entity.func_70644_a((Effect)ModEffects.UNCONSCIOUS.get()))
/*     */     {
/* 152 */       if (entity.func_70660_b((Effect)ModEffects.UNCONSCIOUS.get()).func_76459_b() <= 0) {
/* 153 */         entity.func_195063_d((Effect)ModEffects.UNCONSCIOUS.get());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void onEntityRendered(RenderLivingEvent.Pre event) {
/* 162 */     if (event.getEntity() == null) {
/*     */       return;
/*     */     }
/*     */     
/* 166 */     ClientPlayerEntity player = (Minecraft.func_71410_x()).field_71439_g;
/* 167 */     LivingEntity entity = event.getEntity();
/*     */     
/* 169 */     if (AbilityHelper.isCarrying((LivingEntity)player, entity)) {
/*     */       
/* 171 */       boolean isFirstPerson = ((Minecraft.func_71410_x()).field_71474_y.func_243230_g() == PointOfView.FIRST_PERSON);
/* 172 */       if (isFirstPerson) {
/* 173 */         event.setCanceled(true);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   private static void mulPoseEntityModel(LivingEntity entity, MatrixStack matrixStack, float angle) {
/* 188 */     if (angle == 0.0F) {
/*     */       
/* 190 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229179_b_, 90.0F, true));
/* 191 */       entity.field_70759_as = 0.0F;
/* 192 */       entity.field_70758_at = 0.0F;
/*     */     }
/* 194 */     else if (angle == 1.0F) {
/*     */       
/* 196 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, 45.0F, true));
/* 197 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229183_f_, 90.0F, true));
/* 198 */       entity.field_70759_as = 45.0F;
/* 199 */       entity.field_70758_at = 45.0F;
/*     */     }
/* 201 */     else if (angle == 2.0F) {
/*     */       
/* 203 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229183_f_, 90.0F, true));
/* 204 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229180_c_, 90.0F, true));
/* 205 */       entity.field_70759_as = 90.0F;
/* 206 */       entity.field_70758_at = 90.0F;
/*     */     }
/* 208 */     else if (angle == 3.0F) {
/*     */       
/* 210 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, 45.0F, true));
/* 211 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229178_a_, 90.0F, true));
/* 212 */       entity.field_70759_as = 90.0F;
/* 213 */       entity.field_70758_at = 90.0F;
/*     */     }
/* 215 */     else if (angle == 4.0F) {
/*     */       
/* 217 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229178_a_, 90.0F, true));
/* 218 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, 180.0F, true));
/* 219 */       entity.field_70759_as = 90.0F;
/* 220 */       entity.field_70758_at = 90.0F;
/*     */     }
/* 222 */     else if (angle == 5.0F) {
/*     */       
/* 224 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, 45.0F, true));
/* 225 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229182_e_, 90.0F, true));
/* 226 */       entity.field_70759_as = 90.0F;
/* 227 */       entity.field_70758_at = 90.0F;
/*     */     }
/* 229 */     else if (angle == 6.0F) {
/*     */       
/* 231 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229182_e_, 90.0F, true));
/* 232 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, 90.0F, true));
/* 233 */       entity.field_70759_as = 360.0F;
/* 234 */       entity.field_70758_at = 360.0F;
/*     */     }
/* 236 */     else if (angle == 7.0F) {
/*     */       
/* 238 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229180_c_, 45.0F, true));
/* 239 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229182_e_, 90.0F, true));
/* 240 */       entity.field_70759_as = 360.0F;
/* 241 */       entity.field_70758_at = 360.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void onFogRendered(EntityViewRenderEvent.FogDensity event) {
/* 249 */     Minecraft mc = Minecraft.func_71410_x();
/*     */     
/* 251 */     ClientPlayerEntity clientPlayerEntity = mc.field_71439_g;
/* 252 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)clientPlayerEntity);
/*     */     
/* 254 */     if (props == null) {
/*     */       return;
/*     */     }
/*     */     
/* 258 */     Ability ability = (Ability)props.getEquippedAbility(KenbunshokuHakiAuraAbility.INSTANCE);
/* 259 */     boolean isActive = (ability != null && ability.isContinuous());
/*     */     
/* 261 */     if (isActive && clientPlayerEntity.func_70644_a(Effects.field_76440_q)) {
/*     */       
/* 263 */       event.setCanceled(true);
/* 264 */       event.setDensity(1.0E-4F);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\passives\HakiPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */