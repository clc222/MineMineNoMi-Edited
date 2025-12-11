/*    */ package xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.model.Model;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.baroqueworks.Mr0Entity;
/*    */ 
/*    */ public class Mr0Model
/*    */   extends HumanoidModel<Mr0Entity>
/*    */ {
/*    */   public Mr0Model() {
/* 14 */     this(RenderType::func_228640_c_, 0.0F, 0.0F, 64, 32);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Mr0Model(Function<ResourceLocation, RenderType> p_i225946_1_, float p_i225946_2_, float p_i225946_3_, int p_i225946_4_, int p_i225946_5_) {
/* 20 */     this.field_178724_i = new ModelRenderer((Model)this, 40, 16);
/* 21 */     this.field_178724_i.field_78809_i = true;
/* 22 */     this.field_178724_i.func_228301_a_(-1.0F, -2.0F, -2.0F, 3.8F, 6.5F, 3.8F, p_i225946_2_);
/* 23 */     this.field_178724_i.func_78793_a(5.0F, 2.0F + p_i225946_3_, 0.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\models\entities\mobs\humanoids\Mr0Model.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */