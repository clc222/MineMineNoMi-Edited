/*    */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SChatPacket;
/*    */ import net.minecraft.util.text.ChatType;
/*    */ import net.minecraft.util.text.IFormattableTextComponent;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.Style;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.ForgeHooks;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class AnimeScreamComponent extends AbilityComponent<IAbility> {
/*    */   static {
/* 18 */     SIMPLE_SCREAM = ((e, a) -> a.getComponent(ModAbilityKeys.ANIME_SCREAM).ifPresent(()));
/*    */   } private static final Ability.IOnUse2Event<LivingEntity, IAbility> SIMPLE_SCREAM;
/*    */   public AnimeScreamComponent(IAbility ability) {
/* 21 */     super(ModAbilityKeys.ANIME_SCREAM, ability);
/*    */   }
/*    */   
/*    */   public void setupDefaultScreams(IAbility ability) {
/* 25 */     if (ability.hasComponent(ModAbilityKeys.CHARGE)) {
/* 26 */       ChargeComponent comp = ability.getComponent(ModAbilityKeys.CHARGE).get();
/*    */       
/* 28 */       String[] split = getAbility().getDisplayName().getString().split(" ", 0);
/*    */       
/* 30 */       comp.addStartEvent((e, a) -> scream(e, split[0] + ((split.length > 1) ? "..." : "")));
/*    */       
/* 32 */       if (split.length > 1) {
/* 33 */         StringBuilder endMessage = new StringBuilder();
/*    */         
/* 35 */         for (int i = 1; i < split.length; i++) {
/* 36 */           endMessage.append(split[i]);
/*    */           
/* 38 */           if (i < split.length - 1) {
/* 39 */             endMessage.append(" ");
/*    */           }
/*    */         } 
/*    */         
/* 43 */         comp.addEndEvent((e, a) -> scream(e, "..." + endMessage.toString()));
/*    */       } 
/* 45 */     } else if (ability.hasComponent(ModAbilityKeys.CONTINUOUS)) {
/* 46 */       ContinuousComponent comp = ability.getComponent(ModAbilityKeys.CONTINUOUS).get();
/*    */       
/* 48 */       comp.addStartEvent((e, a) -> scream(e));
/* 49 */     } else if (ability instanceof Ability && 
/* 50 */       !((Ability)ability).hasUseEvent(SIMPLE_SCREAM)) {
/* 51 */       ((Ability)ability).addUseEvent(SIMPLE_SCREAM);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void scream(LivingEntity entity) {
/* 57 */     scream(entity, getAbility().getDisplayName().getString());
/*    */   }
/*    */   
/*    */   public void scream(LivingEntity entity, String message) {
/* 61 */     ensureIsRegistered();
/*    */     
/* 63 */     if (!entity.field_70170_p.field_72995_K && ((Boolean)CommonConfig.INSTANCE.animeScreaming.get()).booleanValue()) {
/* 64 */       World world = entity.func_130014_f_();
/*    */       
/* 66 */       IFormattableTextComponent iFormattableTextComponent = (new TranslationTextComponent("chat.type.text", new Object[] { entity.func_145748_c_(), ForgeHooks.newChatWithLinks(message.toUpperCase()) })).func_230530_a_(Style.field_240709_b_.func_240722_b_(Boolean.valueOf(true)));
/*    */       
/* 68 */       world.func_73046_m().func_184103_al().func_148543_a(null, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), 100.0D, world.func_234923_W_(), (IPacket)new SChatPacket((ITextComponent)iFormattableTextComponent, ChatType.CHAT, entity.func_110124_au()));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\AnimeScreamComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */