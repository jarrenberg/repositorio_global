package ejercicio;

public class Pizza {
	private String masa;
	private String salsa;
	private String topping;
	
	public Pizza(String masa, String salsa, String topping) {
		super();
		this.masa = masa;
		this.salsa = salsa;
		this.topping = topping;
	}
	
	public String getMasa() {
		return masa;
	}
	public void setMasa(String masa) {
		this.masa = masa;
	}
	public String getSalsa() {
		return salsa;
	}
	public void setSalsa(String salsa) {
		this.salsa = salsa;
	}
	public String getTopping() {
		return topping;
	}
	public void setTopping(String topping) {
		this.topping = topping;
	}
	
	@Override
	public String toString() {
		return "Pizza [masa=" + masa + ", salsa=" + salsa + ", topping=" + topping + "]";
	}

}
