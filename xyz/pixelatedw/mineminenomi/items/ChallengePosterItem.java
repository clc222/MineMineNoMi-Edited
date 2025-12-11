/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import com.google.common.base.Strings;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.Util;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ 
/*    */ public class ChallengePosterItem extends Item {
/*    */   public ChallengePosterItem() {
/* 22 */     super((new Item.Properties()).func_200917_a(1));
/*    */   }
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World world, PlayerEntity player, Hand hand) {
/* 27 */     ItemStack itemStack = player.func_184586_b(hand);
/*    */     
/* 29 */     if (!world.field_72995_K) {
/* 30 */       IChallengesData props = ChallengesDataCapability.get(player);
/*    */       
/* 32 */       String challengeStrId = itemStack.func_196082_o().func_74779_i("challengeId");
/* 33 */       if (!Strings.isNullOrEmpty(challengeStrId)) {
/* 34 */         ResourceLocation challengeId = new ResourceLocation(challengeStrId);
/* 35 */         ChallengeCore<?> challenge = (ChallengeCore)ModRegistries.CHALLENGES.getValue(challengeId);
/*    */         
/* 37 */         if (props.hasChallenge(challenge)) {
/* 38 */           player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.CHALLENGE_MESSAGE_ALREADY_UNLOCKED, new Object[] { challenge.getLocalizedTitle() }), Util.field_240973_b_);
/*    */         } else {
/*    */           
/* 41 */           props.addChallenge(challenge);
/* 42 */           player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.CHALLENGE_MESSAGE_UNLOCKED, new Object[] { challenge.getLocalizedTitle() }), Util.field_240973_b_);
/* 43 */           player.field_71071_by.func_184437_d(itemStack);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 50 */     return ActionResult.func_226249_b_(itemStack);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\ChallengePosterItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */