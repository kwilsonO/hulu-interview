import java.util.*;
import java.io.*;


public class OrgChart{

	public static HashMap<String, ArrayList<Employee>> emps; 
	public static Employee ceo;
	public static void main(String[] args){

		Scanner input;

		try{
			input = new Scanner(new File("org_chart.in"));
		}catch(Exception e){
			System.out.println("Could not open file: " + e.toString());
			return;
		}


		int cases = input.nextInt();
		input.nextLine();
		String s; 
		int caseCount = 1;	
		while(input.hasNext()){
			emps = new HashMap<String, ArrayList<Employee>>();
			OrgChart.ceo = null;
			s = input.nextLine();
			System.out.println(s);
			String[] empData;
			s = s.replaceAll("--", ":");
			for(String e : s.split(":")){

				empData = e.split(",");
				if(empData.length < 4){
					System.out.println("Data format incorrect.");
				}

				String boss = empData[1];
				if( boss == "NULL" ){
					OrgChart.ceo = new Employee(boss, null, empData[2], 
							Integer.parseInt(empData[3]));
				
				}
				ArrayList<Employee> t = emps.get(boss);
				if( t == null ){
					t = new ArrayList<Employee>();
				} else {
					t.add(new Employee(empData[0], empData[1], empData[2],
								Integer.parseInt(empData[3])));
				}

				emps.put(boss, t);
			System.out.println(emps);
			System.out.println(ceo);
			printData(caseCount);
			caseCount++;
		}

		}
	}


	public static void printData(int caseCount){

		System.out.printf("Case #%d\n", caseCount);
		printEmpData(ceo, 0);
		String boss = ceo.name;
		
		ArrayList<Employee> tmp;
			
		int steps = 1;
		while(emps.get(boss).size() > 0){

			for(Employee e : emps.get(boss)){
				recurseEmployees(e, steps);
			}
		}
	}

	public static void recurseEmployees(Employee e, int steps){


			if( emps.get(e.name) == null || emps.get(e.name).size() == 0){
				return;
			}

			for(Employee x : emps.get(e.name)){
				printEmpData(e, ++steps);
				recurseEmployees(x, steps);
			}

	}

	public static void printEmpData(Employee e, int steps){
		
		
		while(steps-- > 0)
			System.out.print("-");

		System.out.printf("%s (%s) %d\n", e.name, e.title, e.year);

	}

	private static class Employee{

		String name;
		String boss;
		String title;
		int year;

		public Employee(String name, String boss, String title, int year){
			this.name = name;
			this.boss = boss;
			this.title = title;
			this.year = year;
		}
	}
}



