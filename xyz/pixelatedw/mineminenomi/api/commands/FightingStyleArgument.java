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
/*    */ import xyz.pixelatedw.mineminenomi.api.charactercreator.StyleId;
/*    */ 
/*    */ public class FightingStyleArgument
/*    */   implements ArgumentType<StyleId> {
/*    */   public StyleId parse(StringReader reader) throws CommandSyntaxException {
/* 18 */     ResourceLocation resourcelocation = ResourceLocation.func_195826_a(reader);
/* 19 */     StyleId faction = (StyleId)GameRegistry.findRegistry(StyleId.class).getValue(resourcelocation);
/* 20 */     return faction;
/*    */   }
/*    */   
/*    */   public static FightingStyleArgument fightingStyle() {
/* 24 */     return new FightingStyleArgument();
/*    */   }
/*    */   
/*    */   public static <S> StyleId getFightingStyle(CommandContext<S> context, String name) {
/* 28 */     return (StyleId)context.getArgument(name, StyleId.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
/* 33 */     StringReader stringreader = new StringReader(builder.getInput());
/* 34 */     stringreader.setCursor(builder.getStart());
/*    */     
/* 36 */     return suggestAbility(builder);
/*    */   }
/*    */   
/*    */   private CompletableFuture<Suggestions> suggestAbility(SuggestionsBuilder builder) {
/* 40 */     return ISuggestionProvider.func_197014_a(GameRegistry.findRegistry(StyleId.class).getKeys(), builder);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\commands\FightingStyleArgument.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */