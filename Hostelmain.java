import java.util.*;
import java.util.Random;
 class hostel
{
	private int id;
	private String name;
	private String number;
	private int apid;
	private int roomnumber;

	public hostel(int id,String name,String number,int apid){
		this.id=id;
		this.name=name;
		this.number=number;
		this.apid=apid;
		this.roomnumber=-1;
	}
	int getId(){
		return id;
	}
	String getName(){
		return name;
	}
	String number(){
		return number;
	}
	int getApid(){
		return apid;
	}
	int getRoomnumber(){
		return roomnumber;
	}

	void setRoomnumber(int roomnumber){
		this.roomnumber=roomnumber;
	}
}
interface Hostelmanagement
{
	void register();
	
	void allocateRoom();

    void displaydetailes();

}

class Hosteldetailes implements Hostelmanagement
{
	int idlimit=100000;
	Scanner sc=new Scanner(System.in);
	ArrayList<hostel> a=new ArrayList<>();
    int rooms[]=new int[10];
	public void register(){
		System.out.print("Enter ID to Register = ");
		int id=sc.nextInt();
		System.out.print("Enter Your Name = ");
		String name=sc.next();
		System.out.print("Enter Your Mobile Number = ");
		String number=sc.next();
		Random random=new Random();
		int apid=random.nextInt(idlimit);
		System.out.println("Your Application ID\n"+apid);
		System.out.println("Please Note Your Application ID For Future Referance");
          a.add(new hostel(id,name,number,apid));
		  System.out.println();
	}


	int findAvailableRoom() {
        for (int i=0; i<rooms.length; i++) {
            if (rooms[i]==0) {  
                rooms[i]=1;  
               return i+1; 
            }
			
        }
        return -1;
    }

	public void allocateRoom(){
		System.out.println("Enter Application ID To Alloacted Room");
		int apid=sc.nextInt();		
		for (hostel h : a )
		{
			if (h.getApid()==apid)
			{
				if (h.getRoomnumber()!=-1)
				{
					System.out.println("Room is already Allocated");
				}
				else{
					int availableRoom=findAvailableRoom();
                    if (availableRoom!=-1) {
                        h.setRoomnumber(availableRoom);
                        System.out.println("Room allocated successfully\nRoom Number= "+availableRoom);
                    } 
					
					else {
                        System.out.println("No available rooms.");
                    }
					System.out.println();
                }
                return;
            }
			
        }
		
        System.out.println("Application ID not Registered/found.");
		
		
	}

	public void displaydetailes(){
		System.out.println("Enter you Application ID");	
		int apid=sc.nextInt();

		for (hostel detailes : a )
		{
			if (detailes.getApid()==apid)
			{
				System.out.println("Your Name= "+detailes.getName());
				System.out.println("Your Number= "+detailes.number());
				System.out.println("Your Appication ID= "+detailes.getApid());
				if (detailes.getRoomnumber()!=-1)
				{
					System.out.println("Your Allocated Room="+detailes.getRoomnumber());
				}
				else{
					System.out.println("Room is not Allocated Please choose the option to Allocated Room");
				}
			}
			else
			{
				System.out.println("Application ID Not Registered/Found");
			}
		}
		System.out.println();
		}
	
}
class HostelDao
{
Hostelmanagement instanceHostel(){
	return new Hosteldetailes();
}
}
			

  class Hostelmain
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		HostelDao ha=new HostelDao();
		Hostelmanagement h=ha.instanceHostel();
		while (true)
		{
		System.out.println("Select Choice\n1.Student Register\n2.Room Allocation\n3.Display Detailes\n4.Exit");
		int a=sc.nextInt();
			switch(a){
				case 1:
                    h.register();
				break;
				case 2:
					h.allocateRoom();
				break;
				case 3:
					h.displaydetailes();
				break;
				case 4:
					System.out.println("Exited....");
				System.exit(0);
				default:
					System.out.println("invalid Choice....Please Choose Right CHoice");
				break;
			}
		}
	}
}
