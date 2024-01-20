package draylar.gofish;

import draylar.gofish.command.FishCommand;
import draylar.gofish.registry.*;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTab.DisplayItemsGenerator;
import net.minecraft.world.item.CreativeModeTab.ItemDisplayParameters;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.CreativeModeTab.TabVisibility;
import net.minecraft.world.item.CreativeModeTabs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(GoFish.ID)
public class GoFish {

    public static final String ID = "gofish";
    public static final String NAME = "GoFish";
    public static final String VERSION = "0.1.0";

    public static final RegistryKey<ItemGroup> ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, id("group"));
    public static final Logger LOGGER = LogManager.getLogger();

    public GoFish(){
        onInitialize();
    }

    public void onInitialize() {
        Registry.register(Registries.ITEM_GROUP, ITEM_GROUP, CreativeModeTab.builder()
                .icon(() -> new ItemStack(GoFishItems.GOLDEN_FISH))
                .displayName(Text.translatable("itemGroup.gofish.group"))
                .build());

        GoFishBlocks.init();
        GoFishItems.init();
        GoFishEnchantments.init();
        GoFishLoot.init();
        GoFishLootHandler.init();
        GoFishParticles.init();
        GoFishEntities.init();

        FishCommand.register();

        registerBrewingRecipes();
    }

    public static Identifier id(String name) {
        return new Identifier("gofish", name);
    }

    public static void registerBrewingRecipes() {
        BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, GoFishItems.CLOUDY_CRAB, Potions.SLOW_FALLING);
        BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, GoFishItems.CHARFISH, Potions.WEAKNESS);
        BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, GoFishItems.RAINY_BASS, Potions.WATER_BREATHING);
        BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, GoFishItems.MAGMA_COD, Potions.FIRE_RESISTANCE);
    }
}
