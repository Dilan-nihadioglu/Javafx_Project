package application;

public class HourlyEmployee extends Employee{
	
	private double wage;
	private double hours;

	HourlyEmployee(String firstName, String lastName, String SSN, double wage, double hours) {
		super(firstName, lastName, SSN);
		if(wage>=0) {
			this.wage=wage;
		}
		else {
			System.out.println("Wage must be >= 0 .");
			System.exit(0);
		}
		
		if(hours>=0 &&hours<168) {
			this.hours=hours;
		}
		else {
			System.out.println("Hours must be >=0 and <168 .");
			System.exit(0);
		}
		
	}
	
	public double getWage() {
		return wage;
	}
	public void setWage(double wage) {
		if(wage>=0) {
			this.wage=wage;
		}
		else {
			System.out.println("Wage must be >= 0 .");
			System.exit(0);
		}
	}
	public double getHours() {
		return hours;
	}
	public void setHours(double hours) {
		if(hours>=0 &&hours<168) {
			this.hours=hours;
		}
		else {
			System.out.println("Hours must be >=0 and <168 .");
			System.exit(0);
		}
		
	}
	@Override
	public String toString() {
		return "Hourly employee : " +super.getFirstName() + " " + super.getLastName()
		+ "\nSocial Security Number : " +super.getSSN()
		+ "\nHourly wage :$" +this.wage
		+ "\nHours worked : " +this.hours;
	}

	@Override
	public double getPaymentAmount() {
		double payment=0;
		if(hours<=40) {
			payment=wage*hours;
		}
		else if(hours>40) {
			payment=40*wage + (hours-40)*wage*1.5;
		}
		return payment;
	}

}
