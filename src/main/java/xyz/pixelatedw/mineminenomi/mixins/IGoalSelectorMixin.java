package xyz.pixelatedw.mineminenomi.mixins;

import java.util.Set;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.PrioritizedGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({GoalSelector.class})
public interface IGoalSelectorMixin {
  @Accessor("availableGoals")
  Set<PrioritizedGoal> getAvailableGoals();
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\IGoalSelectorMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */