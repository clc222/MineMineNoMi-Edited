/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraftforge.fml.loading.LoadingModList;
/*    */ import org.objectweb.asm.tree.ClassNode;
/*    */ import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
/*    */ import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModMixinPlugin
/*    */   implements IMixinConfigPlugin
/*    */ {
/*    */   private static final Supplier<Boolean> VS_LOADED = () -> Boolean.valueOf((LoadingModList.get().getModFileById("valkyrienskies") != null));
/* 20 */   private static final Map<String, Supplier<Boolean>> CONDITIONS = (Map<String, Supplier<Boolean>>)ImmutableMap.of("xyz.pixelatedw.mineminenomi.mixin.valkyrienskies.TickStageEnforcerImplMixin", VS_LOADED);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void acceptTargets(Set<String> arg0, Set<String> arg1) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public List<String> getMixins() {
/* 30 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRefMapperConfig() {
/* 35 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onLoad(String arg0) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void preApply(String arg0, ClassNode arg1, String arg2, IMixinInfo arg3) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void postApply(String arg0, ClassNode arg1, String arg2, IMixinInfo arg3) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
/* 55 */     return ((Boolean)((Supplier<Boolean>)CONDITIONS.getOrDefault(mixinClassName, () -> Boolean.valueOf(true))).get()).booleanValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModMixinPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */