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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ 
/*    */ 
/*    */ public class AbilityArgument
/*    */   implements ArgumentType<AbilityCore>
/*    */ {
/*    */   public AbilityCore parse(StringReader reader) throws CommandSyntaxException {
/* 20 */     ResourceLocation resourcelocation = ResourceLocation.func_195826_a(reader);
/* 21 */     AbilityCore ability = (AbilityCore)GameRegistry.findRegistry(AbilityCore.class).getValue(resourcelocation);
/* 22 */     return ability;
/*    */   }
/*    */   
/*    */   public static AbilityArgument ability() {
/* 26 */     return new AbilityArgument();
/*    */   }
/*    */   
/*    */   public static <S> AbilityCore getAbility(CommandContext<S> context, String name) {
/* 30 */     return (AbilityCore)context.getArgument(name, AbilityCore.class);
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
/* 42 */     return ISuggestionProvider.func_197014_a(GameRegistry.findRegistry(AbilityCore.class).getKeys(), builder);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\commands\AbilityArgument.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */