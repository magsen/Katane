package Katane;

public abstract class Ressource {
	protected int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	public Ressource() {
		this.value = 0;
	}
}
