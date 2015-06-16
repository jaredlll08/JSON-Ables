package jsonAbles.api;

import jsonAbles.ModUtils;
import jsonAbles.config.json.FluidType;
import net.minecraft.item.ItemStack;

public class FluidSet {

	String unlocalizedName;
	int density;
	boolean gaseous;
	int luminosity;
	int temperature;
	int viscosity;
	int color;
	int castingMaterialID;
	boolean setFire;

	public FluidSet(FluidType type) {
		this(type.unlocalizedName, type.density, type.gaseous, type.luminosity, type.temperature, type.viscosity, type.color, type.castingMaterialID, type.setFire);
	}

	public FluidSet(String unlocalizedName, int density, boolean gaseous, int luminosity, int temperature, int viscosity, int color, int castingMaterialID, boolean setFire) {
		this.unlocalizedName = unlocalizedName;
		this.density = density;
		this.gaseous = gaseous;
		this.luminosity = luminosity;
		this.temperature = temperature;
		this.viscosity = viscosity;
		this.color = color;
		this.castingMaterialID = castingMaterialID;
		this.setFire = setFire;
	}

	public String getUnlocalizedName() {
		return unlocalizedName;
	}

	public int getDensity() {
		return density;
	}

	public int getCastingMaterialID() {
		return castingMaterialID;
	}

	public boolean isGaseous() {
		return gaseous;
	}

	public int getLuminosity() {
		return luminosity;
	}

	public int getTemperature() {
		return temperature;
	}

	public int getViscosity() {
		return viscosity;
	}

	public int getColor() {
		return color;
	}

}
