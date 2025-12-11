/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SChatPacket;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.util.Util;
/*    */ import net.minecraft.util.text.ChatType;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.client.event.RenderNameplateEvent;
/*    */ import net.minecraftforge.common.ForgeHooks;
/*    */ import net.minecraftforge.event.ServerChatEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.mane.ManeManeMemoryAbility;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class ManePassiveEvents {
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void onNameplateRender(RenderNameplateEvent event) {
/* 30 */     Entity entity = event.getEntity();
/* 31 */     if (entity instanceof PlayerEntity) {
/* 32 */       PlayerEntity player = (PlayerEntity)entity;
/* 33 */       IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)player);
/* 34 */       ManeManeMemoryAbility ability = (ManeManeMemoryAbility)abilityData.getEquippedAbility(ManeManeMemoryAbility.INSTANCE);
/* 35 */       if (ability != null && ability.isContinuous()) {
/* 36 */         event.setContent((ITextComponent)new StringTextComponent(ability.getMemory().getDisplayName()));
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onServerChat(ServerChatEvent event) {
/* 43 */     ServerPlayerEntity serverPlayerEntity = event.getPlayer();
/* 44 */     IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
/* 45 */     ManeManeMemoryAbility ability = (ManeManeMemoryAbility)abilityData.getEquippedAbility(ManeManeMemoryAbility.INSTANCE);
/*    */     
/* 47 */     if (ability != null && ability.isContinuous()) {
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 52 */       event.setComponent(null);
/*    */       
/* 54 */       TranslationTextComponent translationTextComponent1 = new TranslationTextComponent("chat.type.text", new Object[] { ability.getMemory().getDisplayName(), ForgeHooks.newChatWithLinks(event.getMessage()) });
/* 55 */       TranslationTextComponent translationTextComponent2 = new TranslationTextComponent("chat.type.text", new Object[] { ability.getMemory().getDisplayName() + " §2(" + event.getPlayer().func_146103_bH().getName() + ")§r", ForgeHooks.newChatWithLinks(event.getMessage()) });
/*    */       
/* 57 */       MinecraftServer server = serverPlayerEntity.func_184102_h();
/*    */       
/* 59 */       server.func_145747_a((ITextComponent)translationTextComponent1, Util.field_240973_b_);
/* 60 */       for (ServerPlayerEntity serverPlayer : server.func_184103_al().func_181057_v()) {
/* 61 */         TranslationTextComponent translationTextComponent = translationTextComponent1;
/* 62 */         boolean isOp = (server.func_211833_a(serverPlayer.func_146103_bH()) >= 4);
/* 63 */         if (isOp) {
/* 64 */           translationTextComponent = translationTextComponent2;
/*    */         }
/*    */         
/* 67 */         serverPlayer.field_71135_a.func_147359_a((IPacket)new SChatPacket((ITextComponent)translationTextComponent, ChatType.CHAT, Util.field_240973_b_));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\passives\ManePassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */