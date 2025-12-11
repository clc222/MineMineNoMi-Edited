/*     */ package xyz.pixelatedw.mineminenomi.data.entity.devilfruit;
/*     */ 
/*     */ import joptsimple.internal.Strings;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.common.capabilities.Capability;
/*     */ import net.minecraftforge.common.capabilities.CapabilityInject;
/*     */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ 
/*     */ public class DevilFruitCapability {
/*  24 */   public static final Logger LOGGER = LogManager.getLogger("mineminenomi.DEVIL_FRUIT_CAPABILITY");
/*     */   
/*     */   @CapabilityInject(IDevilFruit.class)
/*  27 */   public static final Capability<IDevilFruit> INSTANCE = null;
/*     */   
/*     */   public static void register() {
/*  30 */     CapabilityManager.INSTANCE.register(IDevilFruit.class, new Capability.IStorage<IDevilFruit>()
/*     */         {
/*     */           public INBT writeNBT(Capability<IDevilFruit> capability, IDevilFruit instance, Direction side) {
/*  33 */             CompoundNBT props = new CompoundNBT();
/*     */             
/*  35 */             props.func_74768_a("version", instance.getVersion());
/*  36 */             String devilFruit = instance.getDevilFruit().<String>map(ResourceLocation::toString).orElse("");
/*  37 */             props.func_74778_a("devilFruit", devilFruit);
/*  38 */             props.func_74757_a("hasAwakenedFruit", instance.hasAwakenedFruit());
/*  39 */             props.func_74757_a("hasYamiPower", instance.hasYamiPower());
/*  40 */             props.func_74778_a("zoanPoint", instance.getZoanPoint());
/*  41 */             instance.getCurrentMorph().ifPresent(morph -> props.func_74778_a("morph", morph.getRegistryName().toString()));
/*     */ 
/*     */ 
/*     */             
/*  45 */             return (INBT)props;
/*     */           }
/*     */ 
/*     */           
/*     */           public void readNBT(Capability<IDevilFruit> capability, IDevilFruit instance, Direction side, INBT nbt) {
/*  50 */             CompoundNBT props = (CompoundNBT)nbt;
/*     */             
/*  52 */             if (!props.func_74764_b("version") || props.func_74762_e("version") < 2) {
/*  53 */               DevilFruitCapability.portToV2(instance, props);
/*     */             }
/*     */             
/*  56 */             String devilFruitProp = props.func_74779_i("devilFruit");
/*  57 */             ResourceLocation devilFruit = null;
/*  58 */             if (props.func_74764_b("devilFruit") && !Strings.isNullOrEmpty(devilFruitProp)) {
/*  59 */               devilFruit = new ResourceLocation(devilFruitProp);
/*     */             }
/*  61 */             instance.setDevilFruit(devilFruit);
/*  62 */             if (CommonConfig.INSTANCE.hasAwakeningsEnabled()) {
/*  63 */               instance.setAwakenedFruit(props.func_74767_n("hasAwakenedFruit"));
/*     */             } else {
/*     */               
/*  66 */               instance.setAwakenedFruit(false);
/*     */             } 
/*  68 */             instance.setYamiPower(props.func_74767_n("hasYamiPower"));
/*  69 */             instance.setZoanPoint(props.func_74779_i("zoanPoint"));
/*  70 */             if (props.func_74764_b("morph")) {
/*  71 */               ResourceLocation morphId = new ResourceLocation(props.func_74779_i("morph"));
/*  72 */               MorphInfo morph = (MorphInfo)ModRegistries.MORPHS.getValue(morphId);
/*  73 */               instance.addMorph(morph);
/*     */             } 
/*     */           }
/*     */         }() -> new DevilFruitBase());
/*     */   }
/*     */   private static void portToV2(IDevilFruit instance, CompoundNBT props) {
/*     */     AkumaNoMiItem akumaNoMiItem;
/*     */     Item item1;
/*  81 */     String oldId = props.func_74779_i("devilFruit");
/*  82 */     Item df = null;
/*  83 */     if (oldId.equals("yamidummy")) {
/*  84 */       akumaNoMiItem = ModAbilities.YAMI_YAMI_NO_MI;
/*     */     }
/*     */     
/*  87 */     if (akumaNoMiItem == null) {
/*  88 */       item1 = DevilFruitHelper.getLegacyDevilFruitItem(oldId);
/*     */     }
/*     */     
/*  91 */     if (item1 != null && item1 != Items.field_190931_a) {
/*  92 */       props.func_74778_a("devilFruit", item1.getRegistryName().toString());
/*  93 */       LOGGER.info("Succesfully ported the legacy fruit {} into {}", oldId, instance.getDevilFruit());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static IDevilFruit get(LivingEntity entity) {
/*  98 */     IDevilFruit props = (IDevilFruit)entity.getCapability(INSTANCE, null).orElse(new DevilFruitBase());
/*  99 */     props.setOwner(entity);
/* 100 */     return props;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\devilfruit\DevilFruitCapability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */