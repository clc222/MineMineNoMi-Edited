/*    */ package xyz.pixelatedw.mineminenomi.api.commands;
/*    */ 
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.suggestion.Suggestions;
/*    */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import net.minecraft.command.ISuggestionProvider;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ 
/*    */ 
/*    */ public class ChallengeArgument
/*    */   implements ArgumentType<ChallengeCore>
/*    */ {
/*    */   public ChallengeCore parse(StringReader reader) throws CommandSyntaxException {
/* 20 */     ResourceLocation resourcelocation = ResourceLocation.func_195826_a(reader);
/* 21 */     ChallengeCore challenge = (ChallengeCore)GameRegistry.findRegistry(ChallengeCore.class).getValue(resourcelocation);
/* 22 */     return challenge;
/*    */   }
/*    */   
/*    */   public static ChallengeArgument challenge() {
/* 26 */     return new ChallengeArgument();
/*    */   }
/*    */   
/*    */   public static <S> ChallengeCore getChallenge(CommandContext<S> context, String name) {
/* 30 */     return (ChallengeCore)context.getArgument(name, ChallengeCore.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
/* 35 */     StringReader stringreader = new StringReader(builder.getInput());
/* 36 */     stringreader.setCursor(builder.getStart());
/*    */     
/* 38 */     return suggestAbility(builder);
/*    */   }
/*    */   
/*    */   private CompletableFuture<Suggestions> suggestAbility(SuggestionsBuilder builder) {
/* 42 */     return ISuggestionProvider.func_197014_a(GameRegistry.findRegistry(ChallengeCore.class).getKeys(), builder);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\commands\ChallengeArgument.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */