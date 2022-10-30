package clasesBasicas;

public class Torneo {

	private int code;
	private String name;
	private String city;
	
	public Torneo(int code) {
		this.code = code;
		tourDefiner(code);
		/*this.name = name;
		this.city = city;
		*/
	}
	
	public void tourDefiner(int code) {
		switch (code) {
		case 1: 
			this.name = "Australian Open";
			this.city = "Melbourne";
			break;
		case 2:
			this.name = "Roland Garros";
			this.city = "Paris";
			break;
		case 3:
			this.name = "Wimbledon";
			this.city = "London";
			break;
		case 4:
			this.name = "US Open";
			this.city = "New York";
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + code);
		}
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
//
	@Override
	public String toString() {
		return "Torneo [code=" + code + ", name=" + name + ", city=" + city + "]";
	}
	
}
