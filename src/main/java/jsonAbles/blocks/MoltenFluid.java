package jsonAbles.blocks;

import jsonAbles.JsonAbles;
import jsonAbles.ModProps;
import jsonAbles.api.FluidSet;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MoltenFluid extends BlockFluidClassic {
	public IIcon stillIcon;
	public IIcon flowingIcon;
	private String stillIconTexture; // = "liquid_gray";
	private String flowIconTexture; // = "liquid_gray_flow";
	private Fluid fluid;
	public int color;
	public FluidSet set;

	public MoltenFluid(Fluid fluid, int color, Material mat, FluidSet set) {
		super(fluid,mat);
		setLightLevel(set.getLuminosity());
		setHardness(1.0F);
		setBlockName(fluid.getName());
		this.fluid = fluid;
		String path = JsonAbles.configDir.getPath();
		this.set = set;
		this.stillIconTexture = (ModProps.modid + ":" + fluid.getUnlocalizedName());
		this.flowIconTexture = (ModProps.modid + ":" + fluid.getUnlocalizedName() + "_flow");
		this.color = color;
	}

	@SideOnly(Side.CLIENT)
	public void func_149651_a(IIconRegister icon) {
		this.stillIcon = icon.registerIcon(this.stillIconTexture);
		this.flowingIcon = icon.registerIcon(this.flowIconTexture);

		getFluid().setIcons(this.stillIcon, this.flowingIcon);
	}

	public IIcon getStillIcon() {
		return this.stillIcon;
	}

	@Override
	public int getRenderColor(int p_149741_1_) {
		return color;
	}

	@Override
	public int getBlockColor() {
		return color;
	}

	@Override
	public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_) {

		return color;
	}

	@Override
	public int getRenderType() {
		return JsonAbles.liquidRender;
	}

	public IIcon getFlowingIcon() {
		return this.flowingIcon;
	}

	@SideOnly(Side.CLIENT)
	public IIcon func_149691_a(int side, int meta) {
		if (side <= 1) {
			return this.stillIcon;
		}
		return this.flowingIcon;
	}
}