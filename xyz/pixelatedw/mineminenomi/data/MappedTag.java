/*    */ package xyz.pixelatedw.mineminenomi.data;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.client.resources.JsonReloadListener;
/*    */ import net.minecraft.profiler.IProfiler;
/*    */ import net.minecraft.resources.IResourceManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.registries.IForgeRegistry;
/*    */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*    */ 
/*    */ public class MappedTag<K extends IForgeRegistryEntry<K>>
/*    */   extends JsonReloadListener
/*    */ {
/* 19 */   private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
/*    */   
/* 21 */   private HashMap<K, Float> values = new HashMap<>();
/*    */   
/*    */   private final ResourceLocation id;
/*    */   
/*    */   private final IForgeRegistry<K> registry;
/*    */   
/*    */   public MappedTag(ResourceLocation id, String directory, IForgeRegistry<K> registry) {
/* 28 */     super(GSON, directory);
/*    */     
/* 30 */     this.id = id;
/*    */     
/* 32 */     this.registry = registry;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void apply(Map<ResourceLocation, JsonElement> pObject, IResourceManager pResourceManager, IProfiler pProfiler) {
/* 37 */     JsonObject obj = ((JsonElement)pObject.get(this.id)).getAsJsonObject();
/*    */     
/* 39 */     for (Map.Entry<String, JsonElement> objEntry : (Iterable<Map.Entry<String, JsonElement>>)obj.entrySet()) {
/* 40 */       ResourceLocation resourceId = new ResourceLocation(objEntry.getKey());
/*    */       
/* 42 */       IForgeRegistryEntry iForgeRegistryEntry = this.registry.getValue(resourceId);
/*    */       
/* 44 */       float value = ((JsonElement)objEntry.getValue()).getAsFloat();
/*    */       
/* 46 */       this.values.put((K)iForgeRegistryEntry, Float.valueOf(value));
/*    */     } 
/*    */   }
/*    */   
/*    */   public float getValue(K type) {
/* 51 */     return ((Float)this.values.getOrDefault(type, Float.valueOf(0.0F))).floatValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\MappedTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */