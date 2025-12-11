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
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class QuestArgument
/*    */   implements ArgumentType<QuestId>
/*    */ {
/*    */   public QuestId parse(StringReader reader) throws CommandSyntaxException {
/* 22 */     ResourceLocation resourcelocation = ResourceLocation.func_195826_a(reader);
/* 23 */     QuestId quest = (QuestId)GameRegistry.findRegistry(QuestId.class).getValue(resourcelocation);
/* 24 */     return quest;
/*    */   }
/*    */ 
/*    */   
/*    */   public static QuestArgument quest() {
/* 29 */     return new QuestArgument();
/*    */   }
/*    */ 
/*    */   
/*    */   public static <S> QuestId getQuest(CommandContext<S> context, String name) {
/* 34 */     return (QuestId)context.getArgument(name, QuestId.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
/* 40 */     StringReader stringreader = new StringReader(builder.getInput());
/* 41 */     stringreader.setCursor(builder.getStart());
/*    */     
/* 43 */     return suggestQuest(builder);
/*    */   }
/*    */ 
/*    */   
/*    */   private CompletableFuture<Suggestions> suggestQuest(SuggestionsBuilder builder) {
/* 48 */     return ISuggestionProvider.func_197014_a(GameRegistry.findRegistry(QuestId.class).getKeys(), builder);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\commands\QuestArgument.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */