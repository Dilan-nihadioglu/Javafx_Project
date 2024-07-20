package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Main extends Application {
	int index = 0; // number of elements in array, only changes when person added into array.
	Employee[] employees = new Employee[200];
	
	String fileName = "records.txt";
    String path = System.getProperty("user.dir") + System.getProperty("file.separator") + "src" 
    + System.getProperty("file.separator") + "application" + System.getProperty("file.separator") + fileName;

	
	File file = new File(path);

	// Labels
	Label firstName_L = new Label("First Name ");
	Label lastName_L = new Label("Last Name ");
	Label sSN_L = new Label("SSN ");
	Label searchUpdateSsn_L = new Label("Search / Update SSN ");
	Label salary_L = new Label("Salary ");
	Label grossSales_L = new Label("Gross Sales ");
	Label commissionRate_L = new Label("Commission Rate ");
	Label baseSalary_L = new Label("Base Salary ");
	Label weeklySalary_L = new Label("Weekly Salary ");
	Label wage_L = new Label("Wage ");
	Label hours_L = new Label("Hours ");
	Label combo_L = new Label("Choose the Employee Type ");
	Label value = new Label("Value ");

	// Text Field
	TextField firstName_T = new TextField();
	TextField lastName_T = new TextField();
	TextField sSN_T = new TextField();
	TextField searchUpdateSsn_T = new TextField();
	TextField grossSales_T = new TextField();
	TextField commissionRate_T = new TextField();
	TextField baseSalary_T = new TextField();
	TextField weeklySalary_T = new TextField();
	TextField wage_T = new TextField();
	TextField hours_T = new TextField();

	String firstName = "";
	String lastName = "";
	String ssn = "";
	String week = "";
	String gross = "";
	String comm = "";
	String base = "";
	String wage = "";
	String hours = "";
	String records = "";
	String selected_text = "";
	// Buttons
	Button add_B = new Button("Add ");
	Button search = new Button("Search by SSN ");
	Button update = new Button("Update by SSN ");
	Button clean = new Button("Clean ");

	ComboBox<String> cbox = new ComboBox<String>();

	@Override
	public void start(Stage primaryStage) {
		try {
			storeToArray();
			sSN_T.setPromptText("Read only text field");
			sSN_T.setEditable(false);
			searchUpdateSsn_T.setEditable(true);
			firstName_T.setPrefColumnCount(12);
			lastName_T.setPrefColumnCount(12);
			sSN_T.setPrefColumnCount(12);
			searchUpdateSsn_T.setPrefColumnCount(12);
			grossSales_T.setPrefColumnCount(8);
			commissionRate_T.setPrefColumnCount(8);
			baseSalary_T.setPrefColumnCount(8);
			weeklySalary_T.setPrefColumnCount(8);
			wage_T.setPrefColumnCount(8);
			hours_T.setPrefColumnCount(8);

			GridPane p1 = new GridPane();
			p1.setAlignment(Pos.CENTER);
			p1.setHgap(5);
			p1.setVgap(5);
			p1.add(firstName_L, 0, 0);
			p1.add(firstName_T, 1, 0);
			p1.add(lastName_L, 0, 1);
			p1.add(lastName_T, 1, 1);
			p1.add(sSN_L, 0, 2);
			p1.add(sSN_T, 1, 2);
			p1.add(searchUpdateSsn_L, 0, 3);
			p1.add(searchUpdateSsn_T, 1, 3);
			p1.add(salary_L, 0, 4);
			p1.add(value, 1, 4);
			p1.add(grossSales_L, 10, 0);
			p1.add(grossSales_T, 11, 0);
			p1.add(commissionRate_L, 10, 1);
			p1.add(commissionRate_T, 11, 1);
			p1.add(baseSalary_L, 10, 2);
			p1.add(baseSalary_T, 11, 2);
			p1.add(weeklySalary_L, 10, 3);
			p1.add(weeklySalary_T, 11, 3);
			p1.add(wage_L, 10, 4);
			p1.add(wage_T, 11, 4);
			p1.add(hours_L, 10, 5);
			p1.add(hours_T, 11, 5);

			HBox hbox2 = new HBox(10);
			hbox2.setAlignment(Pos.CENTER);
			hbox2.setPadding(new Insets(10));
			hbox2.getChildren().addAll(add_B, search, update, clean);

			HBox hbox3 = new HBox(10);
			hbox3.getChildren().add(combo_L);
			hbox3.getChildren().add(cbox);
			hbox3.setAlignment(Pos.CENTER);
			hbox3.setPadding(new Insets(20));

			cbox.getItems().addAll("Salaried Employee", "Hourly Employee", "Commission Employee",
					"Base Plus Commission Employee", "none");

			clean.setOnAction(evt -> clear());
			add_B.setOnAction(evt -> {
				add();
			});

			search.setOnAction(evt -> search());
			update.setOnAction(evt -> {
				update();
			});

			cbox.setOnAction(evt -> {
				selected_text = cbox.getSelectionModel().getSelectedItem();
				activeField(selected_text);
			});

			BorderPane root = new BorderPane();
			root.setTop(hbox3);
			root.setCenter(p1);
			root.setBottom(hbox2);

			Scene scene = new Scene(root, 700, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("EMPLOYEE SALARY CALCULATOR");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void clear() {

		// clear all text fields
		firstName_T.clear();
		lastName_T.clear();
		sSN_T.clear();
		searchUpdateSsn_T.clear();
		grossSales_T.clear();
		commissionRate_T.clear();
		baseSalary_T.clear();
		weeklySalary_T.clear();
		wage_T.clear();
		hours_T.clear();
		value.setText("Value");
	}

	public void activeField(String selected_text) {

		switch (selected_text.charAt(0)) {
		case 'S':
			clear();
			grossSales_T.setEditable(false);
			commissionRate_T.setEditable(false);
			baseSalary_T.setEditable(false);
			wage_T.setEditable(false);
			hours_T.setEditable(false);
			firstName_T.setEditable(true);
			lastName_T.setEditable(true);
			weeklySalary_T.setEditable(true);
			break;
		case 'H':
			clear();
			grossSales_T.setEditable(false);
			commissionRate_T.setEditable(false);
			baseSalary_T.setEditable(false);
			weeklySalary_T.setEditable(false);
			wage_T.setEditable(true);
			hours_T.setEditable(true);
			firstName_T.setEditable(true);
			lastName_T.setEditable(true);
			break;

		case 'C':
			clear();
			grossSales_T.setEditable(true);
			commissionRate_T.setEditable(true);
			baseSalary_T.setEditable(false);
			weeklySalary_T.setEditable(false);
			wage_T.setEditable(false);
			hours_T.setEditable(false);
			firstName_T.setEditable(true);
			lastName_T.setEditable(true);
			break;
		case 'B':
			clear();
			grossSales_T.setEditable(true);
			commissionRate_T.setEditable(true);
			baseSalary_T.setEditable(true);
			weeklySalary_T.setEditable(false);
			wage_T.setEditable(false);
			hours_T.setEditable(false);
			firstName_T.setEditable(true);
			lastName_T.setEditable(true);

			break;
		case 'n':
			clear();
			grossSales_T.setEditable(false);
			commissionRate_T.setEditable(false);
			baseSalary_T.setEditable(false);
			weeklySalary_T.setEditable(false);
			wage_T.setEditable(false);
			hours_T.setEditable(false);
			firstName_T.setEditable(false);
			lastName_T.setEditable(false);
			break;
		default:
			 System.out.println("Error");
			break;

		}

	}

	public String add() {

		firstName = firstName_T.getText();
		lastName = lastName_T.getText();
		ssn = searchUpdateSsn_T.getText();
		week = weeklySalary_T.getText();
		gross = grossSales_T.getText();
		comm = commissionRate_T.getText();
		base = baseSalary_T.getText();
		wage = wage_T.getText();
		hours = hours_T.getText();

		records += selected_text + "," + firstName + "," + lastName + "," + ssn;
		if (!(week.isEmpty())) {

			Double week_D = Double.parseDouble(week);
			SalariedEmployee obj = new SalariedEmployee(firstName, lastName, ssn, week_D);
			value.setText(String.valueOf(obj.getPaymentAmount()));
			sSN_T.setText(ssn);
			records += "," + week + ",payment:" + obj.getPaymentAmount();

		}
		if (!(wage.isEmpty()) && !(hours.isEmpty())) {
			Double wage_D = Double.parseDouble(wage);
			Double hours_D = Double.parseDouble(hours);
			HourlyEmployee obj = new HourlyEmployee(firstName, lastName, ssn, wage_D, hours_D);
			value.setText(String.valueOf(obj.getPaymentAmount()));
			sSN_T.setText(ssn);
			records += "," + wage + "," + hours + ",payment:" + obj.getPaymentAmount();
		}
		if (!(gross.isEmpty()) && !(comm.isEmpty()) && (base.isEmpty())) {
			Double gross_D = Double.parseDouble(gross);
			Double comm_D = Double.parseDouble(comm);
			CommissionEmployee obj = new CommissionEmployee(firstName, lastName, ssn, gross_D, comm_D);
			value.setText(String.valueOf(obj.getPaymentAmount()));
			sSN_T.setText(ssn);
			records += "," + gross + "," + comm + ",payment:" + obj.getPaymentAmount();
		} else if (!(base.isEmpty()) && !(gross.isEmpty()) && !(comm.isEmpty())) {
			Double gross_D = Double.parseDouble(gross);
			Double comm_D = Double.parseDouble(comm);
			Double base_D = Double.parseDouble(base);
			BasePlusCommissionEmployee obj = new BasePlusCommissionEmployee(firstName, lastName, ssn, gross_D, comm_D,
					base_D);
			value.setText(String.valueOf(obj.getPaymentAmount()));
			sSN_T.setText(ssn);
			records += "," + gross + "," + comm + "," + base + ",payment:" + obj.getPaymentAmount();
		}

		if (selected_text.charAt(0) == 'n') {
			System.out.println("none seçili");
			Alert info = new Alert(AlertType.ERROR);
			info.setTitle("Error Adding Null Value");
			info.setContentText("Select employe type!");
			info.showAndWait();
		} else {

			try {

				FileWriter fileWriter = new FileWriter(file, true);
				fileWriter.write(records + "\n");
				fileWriter.close();
				records = "";
				Alert info = new Alert(AlertType.INFORMATION);
				info.setTitle("Text Operation");
				info.setContentText("Employee information saved.");
				info.showAndWait();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			employees[index] = new Employee(firstName, lastName, ssn) {

				@Override
				public double getPaymentAmount() {
					return 0;
				}
			};
			//System.out.println(employees[index] + " " + index);
			System.out.println("Index: " + index + " - " + employees[index].getFirstName() + " - "
					+ employees[index].getLastName() + "-" + employees[index].getSSN());
			index++;
		}
		return records;
	}

	public String search() {
		String search_info = "";
		String text = "";
		String s = "";
		try {

			char data[] = new char[(int) file.length()];
			FileReader fileReader = new FileReader(file);
			fileReader.read(data);
			text = new String(data);
			String[] info = text.split("\n");
			String[] smallInfo = text.split(",");
			Boolean found = false;
			for (int i = 0; i < smallInfo.length; i++) {
				//
				if (smallInfo[i].contains("-") && smallInfo[i].equals(searchUpdateSsn_T.getText())) {
					search_info = smallInfo[i];
					found = true;
					break;
				}
			}

			if (!found) {

				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("ERROR SSN");
				alert.setContentText("There is no employee registered with this SSN");
				alert.showAndWait();
			} else {
				for (int j = 0; j < info.length; j++) {
					if (info[j].contains(search_info)) {
						s += info[j];
						// System.out.println("search employee:" + info[j]);
						String[] for_num = info[j].split(",");
						// System.out.println("length : " + for_num.length);
						switch (for_num.length) {

						case 6:
							cbox.getSelectionModel().select("Salaried Employee");
							firstName_T.setText(for_num[1]);
							lastName_T.setText(for_num[2]);
							sSN_T.setText(for_num[3]);
							weeklySalary_T.setText(for_num[4]);
							value.setText(for_num[5]);
							searchUpdateSsn_T.setText(search_info);
							Arrays.fill(for_num, null);
							break;
						case 7:
							if (for_num[0].charAt(0) == 'H') {
								cbox.getSelectionModel().select("Hourly Employee");
								firstName_T.setText(for_num[1]);
								lastName_T.setText(for_num[2]);
								sSN_T.setText(for_num[3]);
								wage_T.setText(for_num[4]);
								hours_T.setText(for_num[5]);
								value.setText(for_num[6]);
								searchUpdateSsn_T.setText(search_info);
								Arrays.fill(for_num, null);
							} else if (for_num[0].charAt(0) == 'C') {
								cbox.getSelectionModel().select("Commission Employee");
								firstName_T.setText(for_num[1]);
								lastName_T.setText(for_num[2]);
								sSN_T.setText(for_num[3]);
								grossSales_T.setText(for_num[4]);
								commissionRate_T.setText(for_num[5]);
								value.setText(for_num[6]);
								searchUpdateSsn_T.setText(search_info);
								Arrays.fill(for_num, null);
							}
							break;
						case 8:
							cbox.getSelectionModel().select("Base Plus Commission Employee");
							firstName_T.setText(for_num[1]);
							lastName_T.setText(for_num[2]);
							sSN_T.setText(for_num[3]);
							grossSales_T.setText(for_num[4]);
							commissionRate_T.setText(for_num[5]);
							baseSalary_T.setText(for_num[6]);
							value.setText(for_num[7]);
							searchUpdateSsn_T.setText(search_info);
							Arrays.fill(for_num, null);
							break;
						default:
							System.out.println("error");
							break;
						}
					}

				}
			}

			fileReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public void update() {
		records="";
		String result ="";
		
		firstName = firstName_T.getText();
		lastName = lastName_T.getText();
		ssn = searchUpdateSsn_T.getText();
		week = weeklySalary_T.getText();
		gross = grossSales_T.getText();
		comm = commissionRate_T.getText();
		base = baseSalary_T.getText();
		wage = wage_T.getText();
		hours = hours_T.getText();
		records += selected_text + "," + firstName + "," + lastName + "," + ssn;
		if (!(week.isEmpty())) {

			Double week_D = Double.parseDouble(week);
			SalariedEmployee obj = new SalariedEmployee(firstName, lastName, ssn, week_D);
			records += "," + week + ",payment:" + obj.getPaymentAmount();
			weeklySalary_T.setText(Double.toString(obj.getWeeklySalary()));
			salary_L.setText(Double.toString(obj.getPaymentAmount()));

		}
		if (!(wage.isEmpty()) && !(hours.isEmpty())) {
			Double wage_D = Double.parseDouble(wage);
			Double hours_D = Double.parseDouble(hours);
			HourlyEmployee obj = new HourlyEmployee(firstName, lastName, ssn, wage_D, hours_D);
			records += "," + wage_D + "," + hours_D + ",payment:" + obj.getPaymentAmount();
			wage_T.setText(Double.toString(obj.getWage()));
			hours_T.setText(Double.toString(obj.getHours()));
			salary_L.setText(Double.toString(obj.getPaymentAmount()));
		}
		if (!(gross.isEmpty()) && !(comm.isEmpty()) && (base.isEmpty())) {
			Double gross_D = Double.parseDouble(gross);
			Double comm_D = Double.parseDouble(comm);
			CommissionEmployee obj = new CommissionEmployee(firstName, lastName, ssn, gross_D, comm_D);
			records += "," + gross + "," + comm + ",payment:" + obj.getPaymentAmount();
			grossSales_T.setText(Double.toString(obj.getGrossSales()));
			commissionRate_T.setText(Double.toString(obj.getCommissionRate()));
			salary_L.setText(Double.toString(obj.getPaymentAmount()));
		} else if (!(base.isEmpty()) && !(gross.isEmpty()) && !(comm.isEmpty())) {
			Double gross_D = Double.parseDouble(gross);
			Double comm_D = Double.parseDouble(comm);
			Double base_D = Double.parseDouble(base);
			BasePlusCommissionEmployee obj = new BasePlusCommissionEmployee(firstName, lastName, ssn, gross_D, comm_D,
					base_D);
			records += "," + gross + "," + comm + "," + base + ",payment:" + obj.getPaymentAmount();
			grossSales_T.setText(Double.toString(obj.getGrossSales()));
			commissionRate_T.setText(Double.toString(obj.getCommissionRate()));
			baseSalary_T.setText(Double.toString(obj.getBaseSalary()));
			salary_L.setText(Double.toString(obj.getPaymentAmount()));
		}

		result=search();
		// System.out.println(result);
		// System.out.println(records);
		try {
			// Read the current data on the file
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuilder newData = new StringBuilder();
			String line;

			while ((line = bufferedReader.readLine()) != null) {
				// We will replace the result we obtained with the search with the new result
				String updatedLine = line.replace(result, records);
				newData.append(updatedLine).append("\n");
			}

			for (int i = 0; i < employees.length; i++) {
				if (employees[i] != null && result.contains(employees[i].getSSN())) {
					// System.out.println("updated employee name: " +employee.getFirstName());
					employees[i].setFirstName(firstName);
					employees[i].setLastName(lastName);
					employees[i].setSSN(ssn);
					firstName_T.setText(employees[i].getFirstName());
					lastName_T.setText(employees[i].getLastName());
					searchUpdateSsn_T.setText(employees[i].getSSN());
					System.out.println("\nUpdated array :");
					for (int j = 0; j < employees.length; j++) {
						if (employees[j] == null) {
							break;
						} else {
							System.out.println("Index: " + j + " - " + employees[j].getFirstName() + " - "
									+ employees[j].getLastName() + "-" + employees[j].getSSN());
						}
					}

					break;
				}
			}
			bufferedReader.close();
			// Create the file containing the new data
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(newData.toString());
			bufferedWriter.close();

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Update Message");
			alert.setContentText("Employee information has been updated.");
			alert.showAndWait();
			//clear();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public void storeToArray() {

		try {
			char employee_info[] = new char[(int) file.length()];
			FileReader fileReader = new FileReader(file);
			fileReader.read(employee_info);
			String text = new String(employee_info);
			String[] lines = text.split("\n");
			String[] necesnecessary_info = null;

			for (int i = 0; i < lines.length; i++) {
				necesnecessary_info = lines[i].split(",");

				if (necesnecessary_info.length >= 3) {
					if (index < employees.length) {
						employees[index] = new Employee(necesnecessary_info[1], necesnecessary_info[2],
								necesnecessary_info[3]) {
							@Override
							public double getPaymentAmount() {
								return 0;
							}
						};
						index++;
					}
					//System.out.println(employees[i] + " " + i);
					System.out.println("Index: " + i + " - " + employees[i].getFirstName() + " - "
							+ employees[i].getLastName() + "-" + employees[i].getSSN());

				} else {
					break;
				}

			}
			fileReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
