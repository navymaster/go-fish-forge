package draylar.gofish;

import draylar.gofish.command.FishCommand;
import draylar.gofish.registry.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
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

    public static final Logger LOGGER = LogManager.getLogger();

    private static final DeferredRegister<CreativeModeTab> REGISTER =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GoFish.ID);

    public GoFish(){
        onInitialize();
    }

    public void onInitialize() {

        REGISTER.register("base", () -> CreativeModeTab.builder(), CreativeModeTab.builder()
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
        BrewingRecipeRegistry.addRecipe(Potions.AWKWARD, GoFishItems.CLOUDY_CRAB, Potions.SLOW_FALLING);
        BrewingRecipeRegistry.addRecipe(Potions.AWKWARD, GoFishItems.CHARFISH, Potions.WEAKNESS);
        BrewingRecipeRegistry.addRecipe(Potions.AWKWARD, GoFishItems.RAINY_BASS, Potions.WATER_BREATHING);
        BrewingRecipeRegistry.addRecipe(Potions.AWKWARD, GoFishItems.MAGMA_COD, Potions.FIRE_RESISTANCE);
    }
}
