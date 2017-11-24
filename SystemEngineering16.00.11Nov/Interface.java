
public class Interface {
	Systems firstSystem;
	public Systems getFirstSystem() {
		return firstSystem;
	}
	public void setFirstSystem(Systems firstSystem) {
		this.firstSystem = firstSystem;
	}
	public Systems getSecondSystem() {
		return secondSystem;
	}
	public void setSecondSystem(Systems secondSystem) {
		this.secondSystem = secondSystem;
	}
	public int getProvideEnergy() {
		return provideEnergy;
	}
	public void setProvideEnergy(int provideEnergy) {
		this.provideEnergy = provideEnergy;
	}
	public int getConsumeEnergy() {
		return consumeEnergy;
	}
	public void setConsumeEnergy(int consumeEnergy) {
		this.consumeEnergy = consumeEnergy;
	}
	Systems secondSystem;
	int provideEnergy;
	int consumeEnergy;
	

}
