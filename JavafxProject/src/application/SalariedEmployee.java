package application;

public class SalariedEmployee extends Employee{
	
	private double weeklySalary;
	
	SalariedEmployee(String firstName, String lastName, String SSN, double weeklySalary) {
		super(firstName, lastName, SSN);
		if(weeklySalary>=0) {
			this.weeklySalary=weeklySalary;
		}
		else {
			System.out.println("Weekly Salary must be >= 0 .\n");
			System.exit(0);
		}
		
	}
	
	public double getWeeklySalary() {
		return weeklySalary;
	}
	
	public void setWeeklySalary(double weeklySalary) {
		if(weeklySalary>=0) {
			this.weeklySalary=weeklySalary;
		}
		else {
			System.out.println("Weekly Salary must be >= 0 .\n");
			System.exit(0);
		}
	}
	
	@Override
	public String toString() {
		
		return "Salaried employee : "+super.getFirstName()
		+" " + super.getLastName() +"\nSocial Security Number : " + super.getSSN()
		+"\nWeekly Salary :$" +this.weeklySalary		;
	}

	@Override
	public double getPaymentAmount() {

		return weeklySalary;
	}
	

}
