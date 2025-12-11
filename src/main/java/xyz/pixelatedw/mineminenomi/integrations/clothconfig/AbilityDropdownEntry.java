/*     */ package xyz.pixelatedw.mineminenomi.integrations.clothconfig;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import java.util.function.Function;
/*     */ import me.shedaniel.clothconfig2.forge.gui.entries.DropdownBoxEntry;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.AbstractGui;
/*     */ import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ 
/*     */ 
/*     */ public class AbilityDropdownEntry
/*     */ {
/*     */   public static final Function<String, AbilityCore> ABILITY_FUNCTION;
/*     */   
/*     */   static {
/*  25 */     ABILITY_FUNCTION = (str -> {
/*     */         try {
/*     */           ResourceLocation identifier = new ResourceLocation(str);
/*     */ 
/*     */ 
/*     */           
/*     */           return AbilityCore.get(identifier);
/*  32 */         } catch (Exception exception) {
/*     */           return null;
/*     */         } 
/*     */       });
/*     */   }
/*     */   
/*  38 */   private static final ItemStack BARRIER = new ItemStack((IItemProvider)Items.field_221803_eL);
/*     */ 
/*     */   
/*     */   public static DropdownBoxEntry.SelectionTopCellElement<AbilityCore> ofAbilityObject(AbilityCore ability) {
/*  42 */     return (DropdownBoxEntry.SelectionTopCellElement<AbilityCore>)new DropdownBoxEntry.DefaultSelectionTopCellElement<AbilityCore>(ability, ABILITY_FUNCTION, i -> ITextComponent.func_244388_a(ModRegistries.ABILITIES.getKey((IForgeRegistryEntry)i).toString()))
/*     */       {
/*     */         
/*     */         public void render(MatrixStack matrixStack, int mouseX, int mouseY, int x, int y, int width, int height, float delta)
/*     */         {
/*  47 */           this.textFieldWidget.field_230690_l_ = x + 4;
/*  48 */           this.textFieldWidget.field_230691_m_ = y + 6;
/*  49 */           this.textFieldWidget.func_230991_b_(width - 4 - 20);
/*  50 */           this.textFieldWidget.func_146184_c(getParent().isEditable());
/*  51 */           this.textFieldWidget.func_146193_g(getPreferredTextColor());
/*  52 */           this.textFieldWidget.func_230430_a_(matrixStack, mouseX, mouseY, delta);
/*  53 */           if (hasConfigError()) {
/*     */             
/*  55 */             ItemRenderer itemRenderer = Minecraft.func_71410_x().func_175599_af();
/*  56 */             itemRenderer.func_175042_a(AbilityDropdownEntry.BARRIER, x + width - 18, y + 2);
/*     */           }
/*     */           else {
/*     */             
/*  60 */             RendererHelper.drawAbilityIcon((AbilityCore)getValue(), matrixStack, (x + width - 18), (y + 2), 1.0F, 16.0F, 16.0F, 1.0F, 1.0F, 1.0F);
/*     */           } 
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public static DropdownBoxEntry.SelectionCellCreator<AbilityCore> ofAbilityObject() {
/*  68 */     return ofAbilityObject(20, 170, 10);
/*     */   }
/*     */ 
/*     */   
/*     */   public static DropdownBoxEntry.SelectionCellCreator<AbilityCore> ofAbilityObject(int maxItems) {
/*  73 */     return ofAbilityObject(20, 170, maxItems);
/*     */   }
/*     */ 
/*     */   
/*     */   public static DropdownBoxEntry.SelectionCellCreator<AbilityCore> ofAbilityObject(final int cellHeight, final int cellWidth, final int maxItems) {
/*  78 */     return (DropdownBoxEntry.SelectionCellCreator<AbilityCore>)new DropdownBoxEntry.DefaultSelectionCellCreator<AbilityCore>(i -> ITextComponent.func_244388_a(ModRegistries.ABILITIES.getKey((IForgeRegistryEntry)i).toString()))
/*     */       {
/*     */         
/*     */         public DropdownBoxEntry.SelectionCellElement<AbilityCore> create(final AbilityCore selection)
/*     */         {
/*  83 */           return (DropdownBoxEntry.SelectionCellElement<AbilityCore>)new DropdownBoxEntry.DefaultSelectionCellElement<AbilityCore>(selection, this.toTextFunction)
/*     */             {
/*     */               
/*     */               public void render(MatrixStack matrixStack, int mouseX, int mouseY, int x, int y, int width, int height, float delta)
/*     */               {
/*  88 */                 this.rendering = true;
/*  89 */                 this.x = x;
/*  90 */                 this.y = y;
/*  91 */                 this.width = width;
/*  92 */                 this.height = height;
/*  93 */                 boolean b = (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height);
/*     */                 
/*  95 */                 if (b) {
/*  96 */                   AbstractGui.func_238467_a_(matrixStack, x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
/*     */                 }
/*     */                 
/*  99 */                 Minecraft mc = Minecraft.func_71410_x();
/*     */                 
/* 101 */                 mc.field_71466_p.func_238407_a_(matrixStack, ((ITextComponent)this.toTextFunction.apply(this.r)).func_241878_f(), (x + 6 + 18), (y + 6), b ? 16777215 : 8947848);
/* 102 */                 RendererHelper.drawAbilityIcon(selection, matrixStack, (x + 4), (y + 2), 1.0F, 16.0F, 16.0F, 1.0F, 1.0F, 1.0F);
/*     */               }
/*     */             };
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public int getCellHeight() {
/* 110 */           return cellHeight;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public int getCellWidth() {
/* 116 */           return cellWidth;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public int getDropBoxMaxHeight() {
/* 122 */           return getCellHeight() * maxItems;
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\integrations\clothconfig\AbilityDropdownEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */