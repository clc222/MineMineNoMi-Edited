/*     */ package xyz.pixelatedw.mineminenomi.mixins;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.advancements.DisplayInfo;
/*     */ import net.minecraft.advancements.FrameType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.util.JSONUtils;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.IFormattableTextComponent;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*     */ import org.spongepowered.asm.mixin.Final;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Shadow;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*     */ import xyz.pixelatedw.mineminenomi.api.AbilityDisplayInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Mixin({DisplayInfo.class})
/*     */ public abstract class DisplayInfoMixin
/*     */ {
/*     */   @Shadow
/*     */   @Final
/*     */   private boolean field_193227_g;
/*     */   @Shadow
/*     */   @Final
/*     */   private boolean field_193226_f;
/*     */   
/*     */   @Inject(method = {"fromJson"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private static void fromJson(JsonObject pObject, CallbackInfoReturnable<DisplayInfo> callback) {
/*  45 */     IFormattableTextComponent iFormattableTextComponent1 = ITextComponent.Serializer.func_240641_a_(pObject.get("title"));
/*  46 */     IFormattableTextComponent iFormattableTextComponent2 = ITextComponent.Serializer.func_240641_a_(pObject.get("description"));
/*  47 */     if (iFormattableTextComponent1 != null && iFormattableTextComponent2 != null) {
/*     */       
/*  49 */       JsonObject iconObj = JSONUtils.func_152754_s(pObject, "icon");
/*  50 */       if (iconObj.has("ability"))
/*     */       {
/*  52 */         AbilityCore<?> icon = getAbility(iconObj);
/*  53 */         if (icon == null) {
/*     */           return;
/*     */         }
/*  56 */         ResourceLocation bg = pObject.has("background") ? new ResourceLocation(JSONUtils.func_151200_h(pObject, "background")) : null;
/*  57 */         FrameType frame = pObject.has("frame") ? FrameType.func_192308_a(JSONUtils.func_151200_h(pObject, "frame")) : FrameType.TASK;
/*  58 */         boolean flag = JSONUtils.func_151209_a(pObject, "show_toast", true);
/*  59 */         boolean flag1 = JSONUtils.func_151209_a(pObject, "announce_to_chat", true);
/*  60 */         boolean flag2 = JSONUtils.func_151209_a(pObject, "hidden", false);
/*  61 */         callback.setReturnValue(new AbilityDisplayInfo(icon, (ITextComponent)iFormattableTextComponent1, (ITextComponent)iFormattableTextComponent2, bg, frame, flag, flag1, flag2));
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  66 */       throw new JsonSyntaxException("Both title and description must be set");
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static AbilityCore<?> getAbility(JsonObject json) {
/*  72 */     ResourceLocation key = new ResourceLocation(JSONUtils.func_151200_h(json, "ability"));
/*  73 */     AbilityCore<?> ability = (AbilityCore)ModRegistries.ABILITIES.getValue(key);
/*  74 */     return ability;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Inject(method = {"serializeToNetwork"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void serializeToNetwork(PacketBuffer buf, CallbackInfo callback) {
/*  87 */     DisplayInfo info = (DisplayInfo)this;
/*  88 */     if (info instanceof AbilityDisplayInfo) {
/*     */       
/*  90 */       AbilityDisplayInfo ablInfo = (AbilityDisplayInfo)info;
/*  91 */       ResourceLocation rs = new ResourceLocation("mineminenomi", WyHelper.getResourceName(ablInfo.getAbility().getUnlocalizedName()));
/*  92 */       buf.writeInt(1);
/*  93 */       buf.func_192572_a(rs);
/*     */     }
/*     */     else {
/*     */       
/*  97 */       buf.writeInt(0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Inject(method = {"fromNetwork"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private static void fromNetwork(PacketBuffer buf, CallbackInfoReturnable<DisplayInfo> callback) {
/* 104 */     int v = buf.readInt();
/* 105 */     if (v == 1) {
/*     */       
/* 107 */       ResourceLocation rs = buf.func_192575_l();
/* 108 */       AbilityCore ability = (AbilityCore)GameRegistry.findRegistry(AbilityCore.class).getValue(rs);
/* 109 */       ITextComponent itextcomponent = buf.func_179258_d();
/* 110 */       ITextComponent itextcomponent1 = buf.func_179258_d();
/* 111 */       ItemStack itemstack = buf.func_150791_c();
/* 112 */       FrameType frametype = (FrameType)buf.func_179257_a(FrameType.class);
/* 113 */       int i = buf.readInt();
/* 114 */       ResourceLocation resourcelocation = ((i & 0x1) != 0) ? buf.func_192575_l() : null;
/* 115 */       boolean flag = ((i & 0x2) != 0);
/* 116 */       boolean flag1 = ((i & 0x4) != 0);
/* 117 */       AbilityDisplayInfo displayinfo = new AbilityDisplayInfo(ability, itextcomponent, itextcomponent1, resourcelocation, frametype, flag, false, flag1);
/* 118 */       displayinfo.func_192292_a(buf.readFloat(), buf.readFloat());
/* 119 */       callback.setReturnValue(displayinfo);
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"serializeIcon"}, at = {@At("HEAD")}, cancellable = true)
/*     */   private void serializeIcon(CallbackInfoReturnable<JsonObject> callback) {
/* 125 */     DisplayInfo info = (DisplayInfo)this;
/* 126 */     if (info instanceof AbilityDisplayInfo) {
/* 127 */       AbilityDisplayInfo ablInfo = (AbilityDisplayInfo)info;
/* 128 */       JsonObject jsonobject = new JsonObject();
/* 129 */       if (ablInfo.getAbility() != null) {
/* 130 */         String abilityIcon = ablInfo.getAbility().getRegistryName().toString();
/* 131 */         jsonobject.addProperty("ability", abilityIcon);
/*     */       } 
/* 133 */       String itemIcon = ForgeRegistries.ITEMS.getKey((IForgeRegistryEntry)info.func_192298_b().func_77973_b()).toString();
/* 134 */       jsonobject.addProperty("item", itemIcon);
/* 135 */       if (info.func_192298_b().func_77942_o()) {
/* 136 */         jsonobject.addProperty("nbt", info.func_192298_b().func_77978_p().toString());
/*     */       }
/* 138 */       callback.setReturnValue(jsonobject);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\DisplayInfoMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */