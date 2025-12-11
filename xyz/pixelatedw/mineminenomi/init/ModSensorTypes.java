/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.ai.brain.sensor.SensorType;
/*    */ import net.minecraftforge.eventbus.api.IEventBus;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModSensorTypes
/*    */ {
/* 14 */   private static final DeferredRegister<SensorType<?>> SENSOR_TYPES = DeferredRegister.create(ForgeRegistries.SENSOR_TYPES, "mineminenomi");
/*    */ 
/*    */ 
/*    */   
/*    */   private static <T extends net.minecraft.entity.ai.brain.sensor.Sensor<?>> RegistryObject<SensorType<T>> register(String name, Supplier<T> sensor) {
/* 19 */     return SENSOR_TYPES.register(name, () -> new SensorType(sensor));
/*    */   }
/*    */   
/*    */   public static void register(IEventBus bus) {
/* 23 */     SENSOR_TYPES.register(bus);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModSensorTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */