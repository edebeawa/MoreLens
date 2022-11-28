package edebe.more_lens.common.helper;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.api.recipe.IManaInfusionRecipe;
import vazkii.botania.common.crafting.ModRecipeTypes;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface RecipeHelper {
    static List<IManaInfusionRecipe> manaInfusionRecipes(World world) {
        return ModRecipeTypes.getRecipes(world, ModRecipeTypes.MANA_INFUSION_TYPE).values().stream()
                .filter(r -> r instanceof IManaInfusionRecipe)
                .map(r -> (IManaInfusionRecipe) r)
                .collect(Collectors.toList());
    }

    static IManaInfusionRecipe getMatchingRecipe(World world, @Nonnull ItemStack stack, @Nonnull BlockState state) {
        List<IManaInfusionRecipe> matchingNonCatRecipes = new ArrayList<>();
        List<IManaInfusionRecipe> matchingCatRecipes = new ArrayList<>();

        for (IManaInfusionRecipe recipe : manaInfusionRecipes(world)) {
            if (recipe.matches(stack)) {
                if (recipe.getRecipeCatalyst() == null) {
                    matchingNonCatRecipes.add(recipe);
                } else if (recipe.getRecipeCatalyst().test(state)) {
                    matchingCatRecipes.add(recipe);
                }
            }
        }

        return !matchingCatRecipes.isEmpty() ? matchingCatRecipes.get(0) : !matchingNonCatRecipes.isEmpty() ? matchingNonCatRecipes.get(0) : null;
    }
}
