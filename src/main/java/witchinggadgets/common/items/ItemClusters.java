package witchinggadgets.common.items;

import gregtech.api.enums.Materials;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;

public class ItemClusters extends Item {
    @Deprecated
    public static String[] subNames = {
        // TCon
        "Aluminum",
        "Cobalt",
        "Ardite",
        // ThermalFoundation
        "Nickel",
        // Factorization
        "FZDarkIron",
        // Metallurgy
        "Manganese",
        "Zinc",
        "Platinum",
        "Ignatius",
        "ShadowIron",
        "Lemurite",
        "Midasium",
        "Vyroxeres",
        "Ceruclase",
        "Alduorite",
        "Kalendrite",
        "Vulcanite",
        "Sanguinite",
        "Prometheum",
        "DeepIron",
        "Infuscolium",
        "Oureclase",
        "AstralSilver",
        "Carmot",
        "Mithril",
        "Rubracium",
        "Orichalcum",
        "Adamantine",
        "Atlarus",
        "Eximite",
        "Meutoite",
        // Gregtech
        "Beryllium",
        "Cobalt",
        "Iridium",
        "Molybdenum",
        "Naquadah",
        "Neodymium",
        "Nickel",
        "Palladium",
        "Platinum",
        "Thorium",
        "Uranium235",
        "Uranium238",
        "Zinc",
        "Casserite"
    };

    public static HashMap<String, String> loccodename = new HashMap();

    @Deprecated
    public static HashMap<String, Integer[]> materialMap = new HashMap();

    IIcon iconMetal;
    IIcon[] iconOverlay = new IIcon[3];

    public ItemClusters() {
        super();
        maxStackSize = 64;
        setCreativeTab(WitchingGadgets.tabWG);
        setHasSubtypes(true);
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int pass) {
        if (pass == 0 && stack.getItemDamage() < witchinggadgets.common.WGContent.GT_Cluster.length) {
            if (WGContent.GT_Cluster_Color.get(witchinggadgets.common.WGContent.GT_Cluster[stack.getItemDamage()])
                    != null)
                return WGContent.GT_Cluster_Color.get(
                        witchinggadgets.common.WGContent.GT_Cluster[stack.getItemDamage()])[0];
        }
        return 0xffffff;
    }

    public static ItemStack getCluster(String ore) {
        if (WGConfig.allowClusters)
            for (int sn = 0; sn < witchinggadgets.common.WGContent.GT_Cluster.length; sn++)
                if (witchinggadgets.common.WGContent.GT_Cluster[sn].equalsIgnoreCase(ore))
                    return new ItemStack(WGContent.ItemCluster, 1, sn);
        return null;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.iconMetal = iconRegister.registerIcon("witchinggadgets:cluster_metal");
        this.iconOverlay[0] = iconRegister.registerIcon("witchinggadgets:cluster_overlay");
        this.iconOverlay[1] = iconRegister.registerIcon("witchinggadgets:cluster_overlayNether");
        this.iconOverlay[2] = iconRegister.registerIcon("witchinggadgets:cluster_overlayEnd");
    }

    @Override
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @Override
    public IIcon getIconFromDamageForRenderPass(int damage, int pass) {
        if (pass == 0) return this.iconMetal;
        else if (damage < witchinggadgets.common.WGContent.GT_Cluster.length
                && WGContent.GT_Cluster_Color.get(witchinggadgets.common.WGContent.GT_Cluster[damage]) != null)
            return this.iconOverlay[
                    WGContent.GT_Cluster_Color.get(witchinggadgets.common.WGContent.GT_Cluster[damage])[1]];
        else return this.iconOverlay[0];
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return getIconFromDamageForRenderPass(stack.getItemDamage(), pass);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        String ss = "";
        /*
        if(!OreDictionary.getOres("ingot"+witchinggadgets.common.WGContent.GT_Cluster[stack.getItemDamage()]).isEmpty())
        {
        	ItemStack ingot = OreDictionary.getOres("ingot"+witchinggadgets.common.WGContent.GT_Cluster[stack.getItemDamage()]).get(0);
        	int limit = ingot.getDisplayName().lastIndexOf(" ");
        	ss = ingot.getDisplayName().substring(0, Math.max(0, limit));
        }
        else if(!OreDictionary.getOres("gem"+witchinggadgets.common.WGContent.GT_Cluster[stack.getItemDamage()]).isEmpty())
        {
        	ItemStack ingot = OreDictionary.getOres("gem"+witchinggadgets.common.WGContent.GT_Cluster[stack.getItemDamage()]).get(0);
        	int limit = ingot.getDisplayName().lastIndexOf(" ");
        	ss = ingot.getDisplayName().substring(0, Math.max(0, limit));
        }
        else if(!OreDictionary.getOres("dust"+witchinggadgets.common.WGContent.GT_Cluster[stack.getItemDamage()]).isEmpty())
        {
        	ItemStack ingot = OreDictionary.getOres("dust"+witchinggadgets.common.WGContent.GT_Cluster[stack.getItemDamage()]).get(0);
        	int limit = ingot.getDisplayName().lastIndexOf(" ");
        	ss = ingot.getDisplayName().substring(0, Math.max(0, limit));
        }
        */
        ss = Materials.get(witchinggadgets.common.WGContent.GT_Cluster[stack.getItemDamage()]).mLocalizedName;
        return StatCollector.translateToLocalFormatted(this.getUnlocalizedNameInefficiently(stack) + ".name", ss)
                .trim();
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List itemList) {
        if (WGConfig.allowClusters)
            for (int iOre = 0; iOre < witchinggadgets.common.WGContent.GT_Cluster.length; iOre++)
                if (!OreDictionary.getOres("ore" + witchinggadgets.common.WGContent.GT_Cluster[iOre])
                        .isEmpty()) // &&
                    // !OreDictionary.getOres("ingot"+witchinggadgets.common.WGContent.GT_Cluster[iOre]).isEmpty())
                    itemList.add(new ItemStack(item, 1, iOre));
    }

    /*public static void setupClusters()
    {
    	if(WGConfig.allowClusters)
    		for(String ore : witchinggadgets.common.WGContent.GT_Cluster) {
    				materialMap.put(ore, new Integer[]{ClientUtilities.getVibrantColourToInt(witchinggadgets.common.WGContent.GT_Cluster_Color.get(ore)),0});

    				try{
    					List<Integer> colList = ClientUtilities.getItemColours( OreDictionary.getOres("ore"+ore).get(0) );
    					if(!colList.isEmpty())

    					{
    						int oreBlockColour = colList.get(0);
    						int[] rgb = {oreBlockColour>>16&0xff, oreBlockColour>>8&0xff, oreBlockColour&0xff};
    						int clustertype = rgb[0]>rgb[2]&&rgb[1]>rgb[2]?2 :rgb[0]>rgb[1]&&rgb[0]>rgb[2]?1 : 0;
    						List<Integer> colours = ClientUtilities.getItemColours(OreDictionary.getOres("ingot"+ore).get(0));

    						int colour = ClientUtilities.getVibrantColourToInt(colours.get((int)(colours.size()*.65)));
    						colour = ClientUtilities.getVibrantColourToInt(colour);

    						materialMap.put(ore, new Integer[]{colour, clustertype} );
    					}
    				}catch(Exception e){
    					WitchingGadgets.logger.log(Level.ERROR, "Error setting up cluster for "+ore);
    				}
    			}
    }*/
}
