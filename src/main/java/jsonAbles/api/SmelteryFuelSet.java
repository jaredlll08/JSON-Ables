package jsonAbles.api;

public class SmelteryFuelSet {
	public String fluidName;
	public int temperature;
	public int duration;

	public SmelteryFuelSet(String fluidName, int temperature, int duration) {
		this.fluidName = fluidName;
		this.temperature = temperature;
		this.duration = duration;
	}

}
