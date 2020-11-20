import java.util.*;
//Hospital Class All data members are private and are accessed through getters and setters, relevant data members are also declared as final.
class All_hospitals{
	 private final String hname ;
	 private final float max_temp;
	 private final int min_oxygen;
	 private final int no_of_beds;
	 private ArrayList<All_patients> patient_hosp;
	 private int avl_beds;
	 private boolean status;
	 All_hospitals(String hname, float max_temp,int min_oxygen, int no_of_beds){
		 this.patient_hosp =new ArrayList<All_patients>();
		 this.hname=hname;
		 this.max_temp=max_temp;
		 this.min_oxygen=min_oxygen;
		 this.no_of_beds=no_of_beds;

	 }
	public float getMax_temp() {
		return max_temp;
	}

	public int getMin_oxygen() {
		return min_oxygen;
	}
	public String getHname() {
		return hname;
	}
	public int getNo_of_beds() {
		return no_of_beds;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getAvl_beds() {
		return avl_beds;
	}
	public void setAvl_beds(int avl_beds) {
		this.avl_beds = avl_beds;
	}
	public void print_hospital() {
		System.out.println(this.getHname());
		System.out.println("Temperature should be <= "+this.getMax_temp());
		System.out.println("Oxygen levels should be >= "+this.getMin_oxygen());
		System.out.println("Number of Available Beds: "+ this.getAvl_beds());
		if(isStatus()) {
			System.out.println("Admission Status: OPEN");
		}
		else {
			System.out.println("Admission Status: CLOSED");
		}
	}
	public void print_hospital_patients() {
		int c=0;
		for(All_patients obj : patient_hosp) {
			c++;
			System.out.println(obj.getPname()+", recover time is "+obj.getRec_day()+" days");	
		}
		if(c==0)
			System.out.println("No Patients");	

	}
	public boolean check_condition1(All_patients patient) {
		if(patient.getOxygen()>=this.getMin_oxygen() &&  patient.isA_status()==false)
			return true;
		else
			return false;
	}
	public boolean check_condition2(All_patients patient) {
		if(patient.getTemp()<=this.getMax_temp() &&  patient.isA_status()==false)
			return true;
		else
			return false;
	}
	public void add_patient(All_patients obj) {
		this.patient_hosp.add(obj);
	}

}
//Patient Class All data members are private and are accessed through getters and setters, relevant data members are also declared as final.
class All_patients{
	private final int id;
	private final String pname;
	private final float temp;
	private final int oxygen;
	private final int age;
	private boolean a_status;
	private int rec_day;
	private String hosp;
	All_patients(int id, String pname, float temp,int oxygen,int age){
		this.id = id;
		this.pname = pname;
		this.temp = temp;
		this.oxygen = oxygen;
		this.age = age;
		
	}
	public void setA_status(boolean a_status) {
		this.a_status = a_status;
	}
	public void setRec_day(int rec_day) {
		this.rec_day = rec_day;
	}
	public int getId() {
		return id;
	}
	public String getPname() {
		return pname;
	}
	public float getTemp() {
		return temp;
	}
	public int getOxygen() {
		return oxygen;
	}
	public boolean isA_status() {
		return a_status;
	}

	public int getAge() {
		return age;
	}
	public int getRec_day() {
		return rec_day;
	}
	public String getHosp() {
		return hosp;
	}
	public void setHosp(String hosp) {
		this.hosp = hosp;
	}
    public void one_patient_deet() {
		System.out.println(this.getPname());
		System.out.println("Temperature is "+this.getTemp());
		System.out.println("Oxygen level is "+this.getOxygen());
		if(this.isA_status()) {
			System.out.println("Admission status - Admitted");
			System.out.println("Admitting Institute - "+ this.getHosp());
		}
		else {
			System.out.println("Admission status - Not Admitted");

		}
	}
    public void brief_details() {
		System.out.println(this.getId()+" "+this.getPname());	
    }

}
//Health Manager class containing arraylist of both hospital and patient type
class Health_cood{
	private ArrayList<All_patients> patientList;
	private ArrayList<All_hospitals> hospitalList;
	private int patient_in_camp;
	Scanner inp =new Scanner(System.in);
	Health_cood(){
		this.patientList= new ArrayList<All_patients>();
		this.hospitalList= new ArrayList<All_hospitals>();
	}
	public void add_hospital() {
		System.out.print("Name: ");
		String hname= inp.next();
		float temp;
		int oxygen;
		int beds;
		System.out.print("Temperature Criteria - ");
		temp=inp.nextFloat();
		System.out.print("Oxygen Level - ");
		oxygen=inp.nextInt();
		System.out.print("Number of available beds - ");
		beds=inp.nextInt();
		All_hospitals obj=new All_hospitals(hname,temp,oxygen,beds);
		obj.setStatus(true);
		obj.setAvl_beds(beds);
		obj.print_hospital();
		for(All_patients patient: patientList)
		{
			if(obj.getAvl_beds()>0)
			{
				if(obj.check_condition1(patient))
				{
					this.setPatient_in_camp(this.getPatient_in_camp()-1);
					patient.setA_status(true);
					System.out.print("Recovery days for admitted patient ID "+patient.getId()+" ");
					int rec_days=inp.nextInt();
					patient.setRec_day(rec_days);
					patient.setHosp(obj.getHname());
					obj.setAvl_beds(obj.getAvl_beds()-1);
					obj.add_patient(patient);
				}

			}
		}
		if(obj.getAvl_beds()>0) {
			for(All_patients patient: patientList)
			{
				if(obj.getAvl_beds()>0)
				{
					if(obj.check_condition2(patient))
					{
						this.setPatient_in_camp(this.getPatient_in_camp()-1);
						patient.setA_status(true);
						System.out.print("Recovery days for admitted patient ID "+patient.getId()+" ");
						int rec_days=inp.nextInt();
						patient.setRec_day(rec_days);
						patient.setHosp(obj.getHname());
						obj.setAvl_beds(obj.getAvl_beds()-1);
						obj.add_patient(patient);
					}

				}
			}
		}
		if(obj.getAvl_beds()==0)
			obj.setStatus(false);
		else if(obj.getAvl_beds()==obj.getNo_of_beds())
			System.out.println("0 patients admitted");
		hospitalList.add(obj);
	}
	public void get_patient_info(int n) {
		for(int i=1;i<=n;i++) {
			String[] arr=inp.nextLine().split(" ");
			All_patients obj=new All_patients(i,arr[0],Float.parseFloat(arr[1]),Integer.parseInt(arr[2]),Integer.parseInt(arr[3]));
			obj.setA_status(false);
			obj.setRec_day(Integer.MAX_VALUE);
			obj.setHosp("");
			this.patientList.add(obj);
		}
	}
	public void display_patients() {
		for(All_patients obj: patientList) {
			obj.brief_details();
		}
	}
	public void patient_deet(int x) {
		for(All_patients obj: patientList) {
			if(x==obj.getId()) {
				obj.one_patient_deet();
				break;
			}
		}
	}
	public void forget_patients() {
		int c=0;
		ArrayList<All_patients> temp=new ArrayList<All_patients>();
		System.out.println("Account ID removed of admitted patients");
		for(All_patients obj: patientList) {
			if(obj.isA_status()) {
				System.out.println(obj.getId());
				c++;
			}
			else {
				temp.add(obj);
			}
		}
		this.patientList=temp;
		if(c==0)
		{
			System.out.println("No patient removed");
		}
	}
	public void hosp_count(){
		int count=0;
		for(All_hospitals obj: hospitalList) {
			if(obj.isStatus())
				count++;
	     }
	    System.out.println(count +" institutes are admitting patients currently");
    }
	public void check_hosp(String hname) {
		for(All_hospitals obj: hospitalList) {
			if(hname.equals(obj.getHname()))
			{
				obj.print_hospital();
				break;
			}
	     }
	}
	public void patients_in_hospital_x(String hospital) {
		for(All_hospitals obj: hospitalList) {
			if(hospital.equals(obj.getHname()))
			{
				obj.print_hospital_patients();
				break;
			}
	     }
	}
	public void close_hospitals() {
		ArrayList<All_hospitals> temp=new ArrayList<All_hospitals>();
		int c=0;
		System.out.println("Accounts removed of Institute whose admission is CLOSED");
		for(All_hospitals obj: hospitalList) {
			if(!obj.isStatus())
			{
				System.out.println(obj.getHname());
				c++;
			}
			else {
				temp.add(obj);
			}
	     }
		this.hospitalList=temp;
		if(c==0)
		{
			System.out.println("No hospital removed");
		}
	}
	public int getPatient_in_camp() {
		return patient_in_camp;
	}
	public void setPatient_in_camp(int patient_in_camp) {
		this.patient_in_camp = patient_in_camp;
	}
}
//Main class containing query handling
public class Covid {
	static Scanner inp =new Scanner(System.in);
	static int total_patients;
	public static void main(String[] args) {
		total_patients=inp.nextInt();
		Health_cood manager=new Health_cood();
		manager.setPatient_in_camp(total_patients);
		manager.get_patient_info(total_patients);
		do {
			System.out.print("Enter Query : ");
			int ch=inp.nextInt();
			if(ch==1) {
				manager.add_hospital();
			}
			else if(ch==2) {
				manager.forget_patients();
			}
			else if(ch==3) {
				manager.close_hospitals();
			}
			else if(ch==4) {
				 System.out.println(manager.getPatient_in_camp() +" patients");	
			}
			else if(ch==5) {
				manager.hosp_count();
			}
			else if(ch==6) {
				String hospital=inp.next();
				manager.check_hosp(hospital);
			}
			else if(ch==7) {
				int id=inp.nextInt();
				manager.patient_deet(id);
			}
			else if(ch==8) {
				manager.display_patients();
			}
			else if(ch==9) {
				String hospital=inp.next();
				manager.patients_in_hospital_x(hospital);
			}
			else{
				System.out.println("Invalid Input");
			}
		}while(manager.getPatient_in_camp()>0);
	}
}
