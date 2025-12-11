/*    */ package xyz.pixelatedw.mineminenomi.api.commands;
/*    */ 
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.suggestion.Suggestions;
/*    */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*    */ import java.util.List;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.command.ISuggestionProvider;
/*    */ import net.minecraft.world.IWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ 
/*    */ public class CrewArgument
/*    */   implements ArgumentType<Crew>
/*    */ {
/*    */   public Crew parse(StringReader reader) throws CommandSyntaxException {
/* 21 */     ExtendedWorldData data = ExtendedWorldData.get();
/* 22 */     if (data == null)
/*    */     {
/*    */       
/* 25 */       data = ExtendedWorldData.get((IWorld)(Minecraft.func_71410_x()).field_71441_e);
/*    */     }
/*    */ 
/*    */     
/* 29 */     return data.getCrewByName(reader.readQuotedString());
/*    */   }
/*    */   
/*    */   public static CrewArgument crew() {
/* 33 */     return new CrewArgument();
/*    */   }
/*    */   
/*    */   public static <S> Crew getCrew(CommandContext<S> context, String name) {
/* 37 */     return (Crew)context.getArgument(name, Crew.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
/* 43 */     StringReader stringreader = new StringReader(builder.getInput());
/* 44 */     stringreader.setCursor(builder.getStart());
/*    */     
/* 46 */     return suggestCrew(builder);
/*    */   }
/*    */   
/*    */   private CompletableFuture<Suggestions> suggestCrew(SuggestionsBuilder builder) {
/* 50 */     String input = builder.getInput();
/* 51 */     int start = builder.getStart();
/* 52 */     StringReader stringReader = new StringReader(input);
/* 53 */     stringReader.setCursor(start);
/*    */     
/* 55 */     boolean quoted = (stringReader.canRead() && stringReader.peek() == '"');
/*    */     
/* 57 */     if (quoted) {
/* 58 */       stringReader.skip();
/*    */     }
/*    */     
/* 61 */     ExtendedWorldData data = ExtendedWorldData.get();
/* 62 */     if (data == null)
/*    */     {
/*    */       
/* 65 */       data = ExtendedWorldData.get((IWorld)(Minecraft.func_71410_x()).field_71441_e);
/*    */     }
/*    */     
/* 68 */     List<Crew> crews = data.getCrews();
/* 69 */     if (crews == null) {
/* 70 */       return ISuggestionProvider.func_197008_a(new String[0], builder);
/*    */     }
/*    */     
/* 73 */     CompletableFuture<Suggestions> suggestions = ISuggestionProvider.func_197008_a((String[])crews
/* 74 */         .stream().map(crew -> '"' + crew.getName() + '"').toArray(x$0 -> new String[x$0]), builder);
/*    */ 
/*    */     
/* 77 */     return suggestions;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\commands\CrewArgument.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */