package application;

public class CommissionEmployee extends Employee{
	
	private double grossSales;
	private double commissionRate;

	CommissionEmployee(String firstName, String lastName, String SSN, double grossSales , double commissionRate) {
		super(firstName, lastName, SSN);
		if(grossSales>=0) {
			this.grossSales=grossSales;
		}
		else {
			System.out.println("Gross Sales must be >=0");
			System.exit(0);
		}
		if(commissionRate>=0 && commissionRate<1) {
			this.commissionRate=commissionRate;
		}
		else {
			System.out.println("CommissionRate>0 and <1");
			System.exit(0);
		}
		
	}
	
	public double getCommissionRate() {
		return commissionRate;
	}
	public void setCommissionRate(double commissionRate) {
		if(commissionRate>=0 && commissionRate<1) {
			this.commissionRate=commissionRate;
		}
		else {
			System.out.println("CommissionRate>0 and <1");
			System.exit(0);
		}
	}
	
	public double getGrossSales() {
		return grossSales;
	}
	public void setGrossSales(double grossSales) {
		if(grossSales>=0) {
			this.grossSales=grossSales;
		}
		else {
			System.out.println("Gross Sales must be >=0");
			System.exit(0);
		}
	}
	
	@Override
	public String toString() {
		return "Commision Employee: " + super.getFirstName() + " " + super.getLastName()
		+"\nSocial Security Number : " + super.getSSN()
		+"\nGross Sales :$" + this.grossSales
		+"\nCommission Rate :" + this.commissionRate;
		
	}
	

	@Override
	public double getPaymentAmount() {
		
		return commissionRate*grossSales;
	}

}
