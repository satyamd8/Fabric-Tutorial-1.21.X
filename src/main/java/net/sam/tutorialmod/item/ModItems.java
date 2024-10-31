package net.sam.tutorialmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sam.tutorialmod.TutorialMod;
import net.sam.tutorialmod.item.custom.ChiselItem;
import net.sam.tutorialmod.item.custom.PolisherItem;

public class ModItems {

    public static final Item PINK_GARNET = registerItem("pink_garnet", new Item(new Item.Settings()));
    public static final Item RAW_PINK_GARNET = registerItem("raw_pink_garnet", new Item(new Item.Settings()));
    public static final Item BRK_BITS = registerItem("brk_bits", new Item(new Item.Settings()));
    public static final Item PRETZEL_BURGER = registerItem("pretzel_burger", new Item(new Item.Settings()
            .food(new FoodComponent.Builder()
                    .nutrition(8)
                    .saturationModifier(1f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 2), 0.75f)
                    .build())
    ));
    public static final Item CHISEL = registerItem("chisel", new ChiselItem(new Item.Settings().maxDamage(32)));
    public static final Item POLISHER = registerItem("polisher", new PolisherItem(new Item.Settings().maxDamage(32)));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        TutorialMod.LOGGER.info("Registering Mod Items for " + TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries ->  {
            fabricItemGroupEntries.add(PINK_GARNET);
            fabricItemGroupEntries.add(RAW_PINK_GARNET);
            fabricItemGroupEntries.add(BRK_BITS);
            fabricItemGroupEntries.add(PRETZEL_BURGER);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(fabricItemGroupEntries ->  {
            fabricItemGroupEntries.add(CHISEL);
            fabricItemGroupEntries.add(POLISHER);
        });
    }
}
