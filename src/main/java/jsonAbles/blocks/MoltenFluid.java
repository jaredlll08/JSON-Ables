package jsonAbles.blocks;

import jsonAbles.JsonAbles;
import jsonAbles.ModProps;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MoltenFluid extends BlockFluidClassic {
	private IIcon stillIcon;
	private IIcon flowingIcon;
	private String stillIconTexture = "liquid_gray";
	private String flowIconTexture = "liquid_gray_flow";
	private Fluid fluid;
	public int color;

	public MoltenFluid(Fluid fluid, int color) {
		super(fluid, Material.lava);
		setLightLevel(100.0F);
		setHardness(1.0F);
		setBlockName(fluid.getName());
		this.fluid = fluid;
		String path = JsonAbles.configDir.getPath();
		
		this.stillIconTexture = (ModProps.modid + ":" + stillIconTexture);
		this.flowIconTexture = (ModProps.modid + ":" + flowIconTexture);
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