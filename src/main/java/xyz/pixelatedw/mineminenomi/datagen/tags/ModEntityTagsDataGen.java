/*    */ package xyz.pixelatedw.mineminenomi.datagen.tags;
/*    */ 
/*    */ import net.minecraft.data.DataGenerator;
/*    */ import net.minecraft.data.EntityTypeTagsProvider;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.tags.ITag;
/*    */ import net.minecraftforge.common.data.ExistingFileHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.ExtraProjectiles;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*    */ 
/*    */ public class ModEntityTagsDataGen
/*    */   extends EntityTypeTagsProvider {
/*    */   public ModEntityTagsDataGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
/* 15 */     super(generator, "mineminenomi", existingFileHelper);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_200432_c() {
/* 20 */     func_240522_a_((ITag.INamedTag)ModTags.Entities.KAIROSEKI).func_240534_a_((Object[])new EntityType[] { (EntityType)ModEntities.KAIROSEKI_NET.get(), (EntityType)ExtraProjectiles.KAIROSEKI_BULLET.get() });
/*    */     
/* 22 */     func_240522_a_((ITag.INamedTag)ModTags.Entities.MAGNETIC).func_240534_a_((Object[])new EntityType[] { EntityType.field_200757_aw, (EntityType)ModEntities.PACIFISTA.get(), (EntityType)ExtraProjectiles.CANNON_BALL.get(), (EntityType)ExtraProjectiles.NORMAL_BULLET.get() });
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\datagen\tags\ModEntityTagsDataGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */