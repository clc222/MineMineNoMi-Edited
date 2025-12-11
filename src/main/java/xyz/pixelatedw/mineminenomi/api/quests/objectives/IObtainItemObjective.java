package xyz.pixelatedw.mineminenomi.api.quests.objectives;

import java.util.ArrayList;
import net.minecraft.item.ItemStack;

public interface IObtainItemObjective {
  boolean checkItem(ItemStack paramItemStack);
  
  int checkItems(ArrayList<ItemStack> paramArrayList);
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\quests\objectives\IObtainItemObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */