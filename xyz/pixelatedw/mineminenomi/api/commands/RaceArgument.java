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
/*    */ import xyz.pixelatedw.mineminenomi.api.charactercreator.RaceId;
/*    */ 
/*    */ public class RaceArgument
/*    */   implements ArgumentType<RaceId> {
/*    */   public RaceId parse(StringReader reader) throws CommandSyntaxException {
/* 18 */     ResourceLocation resourcelocation = ResourceLocation.func_195826_a(reader);
/* 19 */     RaceId race = (RaceId)GameRegistry.findRegistry(RaceId.class).getValue(resourcelocation);
/* 20 */     return race;
/*    */   }
/*    */   
/*    */   public static RaceArgument race() {
/* 24 */     return new RaceArgument();
/*    */   }
/*    */   
/*    */   public static <S> RaceId getRace(CommandContext<S> context, String name) {
/* 28 */     return (RaceId)context.getArgument(name, RaceId.class);
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
/* 40 */     return ISuggestionProvider.func_197014_a(GameRegistry.findRegistry(RaceId.class).getKeys(), builder);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\commands\RaceArgument.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */