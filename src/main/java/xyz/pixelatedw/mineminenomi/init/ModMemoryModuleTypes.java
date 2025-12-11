/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
/*    */ import net.minecraft.util.math.GlobalPos;
/*    */ import net.minecraftforge.eventbus.api.IEventBus;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.animals.AbstractDugongEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModMemoryModuleTypes
/*    */ {
/* 18 */   private static final DeferredRegister<MemoryModuleType<?>> MEMORY_MODULE_TYPES = DeferredRegister.create(ForgeRegistries.MEMORY_MODULE_TYPES, "mineminenomi");
/*    */   
/* 20 */   public static final RegistryObject<MemoryModuleType<List<AbstractDugongEntity>>> NEARBY_ADULT_DUGONGS = register("nearby_adult_dugongs");
/* 21 */   public static final RegistryObject<MemoryModuleType<GlobalPos>> LAST_EXPLOSION_HEARD = register("last_explosion_heard", GlobalPos.field_239645_a_);
/*    */   
/*    */   private static <T> RegistryObject<MemoryModuleType<T>> register(String name, Codec<T> codec) {
/* 24 */     return MEMORY_MODULE_TYPES.register(name, () -> new MemoryModuleType(Optional.of(codec)));
/*    */   }
/*    */   
/*    */   private static <T> RegistryObject<MemoryModuleType<T>> register(String name) {
/* 28 */     return MEMORY_MODULE_TYPES.register(name, () -> new MemoryModuleType(Optional.empty()));
/*    */   }
/*    */   
/*    */   public static void register(IEventBus bus) {
/* 32 */     MEMORY_MODULE_TYPES.register(bus);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModMemoryModuleTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */