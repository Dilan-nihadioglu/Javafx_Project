package application;

public class BasePlusCommissionEmployee extends CommissionEmployee {
	
	private double baseSalary;


	BasePlusCommissionEmployee(String firstName, String lastName, String SSN, double grossSales,
			double commissionRate , double baseSalary) {
		super(firstName, lastName, SSN, grossSales, commissionRate);
		
		if(baseSalary>=0) {
			this.baseSalary=baseSalary;
		}
		else {
			System.out.println("Base Salary must be >=0");
			System.exit(0);
		}
	}
	
	public double getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(double baseSalary) {
		if(baseSalary>=0) {
			this.baseSalary=baseSalary;
		}
		else {
			System.out.println("Base Salary must be >=0");
			System.exit(0);
		}
	}
	
	public double isInstanceOf() {
		baseSalary += (baseSalary * 10)/100;
		return baseSalary;
	}
	
	@Override
	public String toString() {
		return "Base Salaried Commission Employee : " + super.getFirstName() +" " + super.getLastName()
		+"\nSocial Security Number : " +super.getSSN()
		+"\nGross Sales :$" + super.getGrossSales() 
		+"\nCommission Rate : " +super.getCommissionRate()
		+"\nBase Salary :$" +this.baseSalary;
		
	}
	@Override
	public double getPaymentAmount() {
		double payment = (super.getCommissionRate() * super.getGrossSales())+ this.baseSalary;
		return payment;
	}

}
