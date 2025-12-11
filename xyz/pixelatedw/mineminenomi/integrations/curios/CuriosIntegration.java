/*     */ package xyz.pixelatedw.mineminenomi.integrations.curios;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.function.Function;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.event.TextureStitchEvent;
/*     */ import net.minecraftforge.common.capabilities.Capability;
/*     */ import net.minecraftforge.common.capabilities.ICapabilityProvider;
/*     */ import net.minecraftforge.common.util.LazyOptional;
/*     */ import net.minecraftforge.event.AttachCapabilitiesEvent;
/*     */ import net.minecraftforge.fml.InterModComms;
/*     */ import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
/*     */ import top.theillusivec4.curios.api.CuriosCapability;
/*     */ import top.theillusivec4.curios.api.SlotTypeMessage;
/*     */ import top.theillusivec4.curios.api.SlotTypePreset;
/*     */ import top.theillusivec4.curios.api.type.capability.ICurio;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.integrations.curios.renderers.ArmorCurioRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.integrations.curios.renderers.ICurioRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.integrations.curios.renderers.ItemCurioRenderer;
/*     */ 
/*     */ 
/*     */ public class CuriosIntegration
/*     */ {
/*     */   private static final String FACE_SLOT_ID = "face";
/*  31 */   private static final ResourceLocation FACE_SLOT_ICON = new ResourceLocation("mineminenomi", "curios/face");
/*  32 */   private static final SlotTypeMessage.Builder FACE_SLOT = (new SlotTypeMessage.Builder("face"))
/*  33 */     .priority(45)
/*  34 */     .icon(FACE_SLOT_ICON);
/*     */   
/*  36 */   private static final Map<Item, Function<ItemStack, ICurio>> CURIO_ITEMS = new HashMap<>();
/*  37 */   private static final Map<Item, ICurioRenderer> CURIO_RENDERERS = new HashMap<>();
/*     */   
/*     */   public static final void setup(InterModEnqueueEvent event) {
/*  40 */     InterModComms.sendTo("curios", "register_type", () -> SlotTypePreset.BACK.getMessageBuilder().build());
/*  41 */     InterModComms.sendTo("curios", "register_type", FACE_SLOT::build);
/*  42 */     InterModComms.sendTo("curios", "register_type", () -> SlotTypePreset.HEAD.getMessageBuilder().build());
/*  43 */     InterModComms.sendTo("curios", "register_type", () -> SlotTypePreset.CHARM.getMessageBuilder().build());
/*     */   }
/*     */ 
/*     */   
/*     */   public static final void setupIcons(TextureStitchEvent.Pre event) {
/*  48 */     event.addSprite(FACE_SLOT_ICON);
/*     */   }
/*     */   
/*     */   public static ICurioRenderer getRenderer(Item item) {
/*  52 */     return CURIO_RENDERERS.get(item);
/*     */   }
/*     */   
/*     */   public static void setupCurioCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
/*  56 */     ItemStack stack = (ItemStack)event.getObject();
/*  57 */     Item item = stack.func_77973_b();
/*  58 */     if (CURIO_ITEMS.containsKey(item)) {
/*  59 */       final LazyOptional<ICurio> curioItem = LazyOptional.of(() -> (ICurio)((Function<ItemStack, ICurio>)CURIO_ITEMS.get(item)).apply(stack));
/*  60 */       event.addCapability(CuriosCapability.ID_ITEM, new ICapabilityProvider()
/*     */           {
/*     */             public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
/*  63 */               return CuriosCapability.ITEM.orEmpty(cap, curioItem);
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void registerCurioItems() {
/*  71 */     CURIO_ITEMS.put(ModArmors.CELESTIAL_DRAGON_BUBBLE.get(), SimpleCurioItem::new);
/*  72 */     CURIO_ITEMS.put(ModArmors.BICORNE.get(), SimpleCurioItem::new);
/*  73 */     CURIO_ITEMS.put(ModArmors.CHOPPERS_HAT.get(), SimpleCurioItem::new);
/*  74 */     CURIO_ITEMS.put(ModArmors.KILLER_MASK.get(), SimpleCurioItem::new);
/*  75 */     CURIO_ITEMS.put(ModArmors.LAW_HAT.get(), SimpleCurioItem::new);
/*  76 */     CURIO_ITEMS.put(ModArmors.TRICORNE.get(), SimpleCurioItem::new);
/*  77 */     CURIO_ITEMS.put(ModArmors.SABO_HAT.get(), SimpleCurioItem::new);
/*  78 */     CURIO_ITEMS.put(ModArmors.MIHAWK_HAT.get(), SimpleCurioItem::new);
/*  79 */     CURIO_ITEMS.put(ModArmors.FLEET_ADMIRAL_HAT.get(), SimpleCurioItem::new);
/*  80 */     CURIO_ITEMS.put(ModArmors.PLUME_HAT.get(), SimpleCurioItem::new);
/*  81 */     CURIO_ITEMS.put(ModArmors.WIDE_BRIM_HAT.get(), SimpleCurioItem::new);
/*  82 */     CURIO_ITEMS.put(ModArmors.STRAW_HAT.get(), SimpleCurioItem::new);
/*  83 */     CURIO_ITEMS.put(ModArmors.ACES_HAT.get(), SimpleCurioItem::new);
/*     */ 
/*     */     
/*  86 */     CURIO_ITEMS.put(ModArmors.FRANKY_GLASSES.get(), SimpleCurioItem::new);
/*  87 */     CURIO_ITEMS.put(ModArmors.CABAJI_SCARF.get(), SimpleCurioItem::new);
/*  88 */     CURIO_ITEMS.put(ModArmors.BIG_RED_NOSE.get(), SimpleCurioItem::new);
/*  89 */     CURIO_ITEMS.put(ModArmors.KURO_GLASSES.get(), SimpleCurioItem::new);
/*  90 */     CURIO_ITEMS.put(ModArmors.MR3_GLASSES.get(), SimpleCurioItem::new);
/*  91 */     CURIO_ITEMS.put(ModArmors.MR5_GLASSES.get(), SimpleCurioItem::new);
/*  92 */     CURIO_ITEMS.put(ModArmors.SNIPER_GOGGLES.get(), SimpleCurioItem::new);
/*  93 */     CURIO_ITEMS.put(ModArmors.MH5_GAS_MASK.get(), SimpleCurioItem::new);
/*  94 */     CURIO_ITEMS.put(ModArmors.KIZARU_GLASSES.get(), SimpleCurioItem::new);
/*  95 */     CURIO_ITEMS.put(ModArmors.DOFFY_GLASSES.get(), SimpleCurioItem::new);
/*  96 */     CURIO_ITEMS.put(ModArmors.HEART_GLASSES.get(), SimpleCurioItem::new);
/*  97 */     CURIO_ITEMS.put(ModItems.CIGAR.get(), SimpleCurioItem::new);
/*  98 */     CURIO_ITEMS.put(ModItems.CIGARETTE.get(), SimpleCurioItem::new);
/*  99 */     CURIO_ITEMS.put(ModItems.SMOKING_PIPE.get(), SimpleCurioItem::new);
/* 100 */     CURIO_ITEMS.put(ModItems.THREE_CIGARS.get(), SimpleCurioItem::new);
/*     */ 
/*     */     
/* 103 */     CURIO_ITEMS.put(ModArmors.FLUFFY_CAPE.get(), SimpleCurioItem::new);
/* 104 */     CURIO_ITEMS.put(ModArmors.MARINE_CAPTAIN_CAPE.get(), SimpleCurioItem::new);
/* 105 */     CURIO_ITEMS.put(ModArmors.PIRATE_CAPTAIN_CAPE.get(), SimpleCurioItem::new);
/* 106 */     CURIO_ITEMS.put(ModArmors.COLA_BACKPACK.get(), SimpleCurioItem::new);
/* 107 */     CURIO_ITEMS.put(ModArmors.MEDIC_BAG.get(), SimpleCurioItem::new);
/* 108 */     CURIO_ITEMS.put(ModArmors.TOMOE_DRUMS.get(), SimpleCurioItem::new);
/*     */ 
/*     */     
/* 111 */     CURIO_ITEMS.put(ModItems.COLOR_PALETTE.get(), SimpleCurioItem::new);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void registerCurioRenderers() {
/* 116 */     CURIO_RENDERERS.put(ModArmors.CELESTIAL_DRAGON_BUBBLE.get(), new ArmorCurioRenderer());
/* 117 */     CURIO_RENDERERS.put(ModArmors.BICORNE.get(), new ArmorCurioRenderer());
/* 118 */     CURIO_RENDERERS.put(ModArmors.CHOPPERS_HAT.get(), new ArmorCurioRenderer());
/* 119 */     CURIO_RENDERERS.put(ModArmors.KILLER_MASK.get(), new ArmorCurioRenderer());
/* 120 */     CURIO_RENDERERS.put(ModArmors.LAW_HAT.get(), new ArmorCurioRenderer());
/* 121 */     CURIO_RENDERERS.put(ModArmors.TRICORNE.get(), new ArmorCurioRenderer());
/* 122 */     CURIO_RENDERERS.put(ModArmors.SABO_HAT.get(), new ArmorCurioRenderer());
/* 123 */     CURIO_RENDERERS.put(ModArmors.MIHAWK_HAT.get(), new ArmorCurioRenderer());
/* 124 */     CURIO_RENDERERS.put(ModArmors.FLEET_ADMIRAL_HAT.get(), new ArmorCurioRenderer());
/* 125 */     CURIO_RENDERERS.put(ModArmors.PLUME_HAT.get(), new ArmorCurioRenderer());
/* 126 */     CURIO_RENDERERS.put(ModArmors.WIDE_BRIM_HAT.get(), new ArmorCurioRenderer());
/* 127 */     CURIO_RENDERERS.put(ModArmors.STRAW_HAT.get(), new ArmorCurioRenderer());
/* 128 */     CURIO_RENDERERS.put(ModArmors.ACES_HAT.get(), new ArmorCurioRenderer());
/*     */ 
/*     */     
/* 131 */     CURIO_RENDERERS.put(ModArmors.FRANKY_GLASSES.get(), new ArmorCurioRenderer());
/* 132 */     CURIO_RENDERERS.put(ModArmors.CABAJI_SCARF.get(), new ArmorCurioRenderer());
/* 133 */     CURIO_RENDERERS.put(ModArmors.BIG_RED_NOSE.get(), new ArmorCurioRenderer());
/* 134 */     CURIO_RENDERERS.put(ModArmors.KURO_GLASSES.get(), new ArmorCurioRenderer());
/* 135 */     CURIO_RENDERERS.put(ModArmors.MR3_GLASSES.get(), new ArmorCurioRenderer());
/* 136 */     CURIO_RENDERERS.put(ModArmors.MR5_GLASSES.get(), new ArmorCurioRenderer());
/* 137 */     CURIO_RENDERERS.put(ModArmors.SNIPER_GOGGLES.get(), new ArmorCurioRenderer());
/* 138 */     CURIO_RENDERERS.put(ModArmors.MH5_GAS_MASK.get(), new ArmorCurioRenderer());
/* 139 */     CURIO_RENDERERS.put(ModArmors.KIZARU_GLASSES.get(), new ArmorCurioRenderer());
/* 140 */     CURIO_RENDERERS.put(ModArmors.DOFFY_GLASSES.get(), new ArmorCurioRenderer());
/* 141 */     CURIO_RENDERERS.put(ModArmors.HEART_GLASSES.get(), new ArmorCurioRenderer());
/* 142 */     CURIO_RENDERERS.put(ModItems.CIGAR.get(), new ItemCurioRenderer());
/* 143 */     CURIO_RENDERERS.put(ModItems.CIGARETTE.get(), new ItemCurioRenderer());
/* 144 */     CURIO_RENDERERS.put(ModItems.SMOKING_PIPE.get(), new ItemCurioRenderer());
/* 145 */     CURIO_RENDERERS.put(ModItems.THREE_CIGARS.get(), new ItemCurioRenderer());
/*     */ 
/*     */     
/* 148 */     CURIO_RENDERERS.put(ModArmors.FLUFFY_CAPE.get(), new ArmorCurioRenderer());
/* 149 */     CURIO_RENDERERS.put(ModArmors.MARINE_CAPTAIN_CAPE.get(), new ArmorCurioRenderer());
/* 150 */     CURIO_RENDERERS.put(ModArmors.PIRATE_CAPTAIN_CAPE.get(), new ArmorCurioRenderer());
/* 151 */     CURIO_RENDERERS.put(ModArmors.COLA_BACKPACK.get(), new ArmorCurioRenderer());
/* 152 */     CURIO_RENDERERS.put(ModArmors.MEDIC_BAG.get(), new ArmorCurioRenderer());
/* 153 */     CURIO_RENDERERS.put(ModArmors.TOMOE_DRUMS.get(), new ArmorCurioRenderer());
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\integrations\curios\CuriosIntegration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */