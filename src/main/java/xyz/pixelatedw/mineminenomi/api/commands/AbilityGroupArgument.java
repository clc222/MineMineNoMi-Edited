/*    */ package xyz.pixelatedw.mineminenomi.api.commands;
/*    */ 
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.suggestion.Suggestions;
/*    */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*    */ import java.util.Collection;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import java.util.stream.Collectors;
/*    */ import java.util.stream.Stream;
/*    */ import net.minecraft.command.ISuggestionProvider;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.AbilityCommandGroup;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AbilityGroupArgument<T extends Enum<T>>
/*    */   implements ArgumentType<T>
/*    */ {
/*    */   private final Class<T> enumClass;
/*    */   
/*    */   public static AbilityGroupArgument<AbilityCommandGroup> abilityGroup() {
/* 24 */     return new AbilityGroupArgument<>(AbilityCommandGroup.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbilityGroupArgument(Class<T> enumClass) {
/* 29 */     this.enumClass = enumClass;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public T parse(StringReader reader) throws CommandSyntaxException {
/* 35 */     return Enum.valueOf(this.enumClass, reader.readUnquotedString());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
/* 41 */     return ISuggestionProvider.func_197013_a(Stream.of((Object[])this.enumClass.getEnumConstants()).map(Object::toString), builder);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 47 */     return (Collection<String>)Stream.of((Object[])this.enumClass.getEnumConstants()).map(Object::toString).collect(Collectors.toList());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\commands\AbilityGroupArgument.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */