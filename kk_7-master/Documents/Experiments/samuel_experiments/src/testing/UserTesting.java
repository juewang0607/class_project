package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import neededClasses.*;

import org.junit.jupiter.api.Test;

class UserTesting {

	@Test
	void userTest() {
		User u = new User("Foster", "Jenkins", "fj16", "qwerty", "586-968-5890", "Grand Star", "5", "599");
		assertEquals("Foster",u.getFirstName());
		assertEquals("Jenkins",u.getLastName());
		assertEquals("fj16",u.getUsername());
		assertEquals("qwerty",u.getPassword());
		assertEquals("586-968-5890",u.getPhoneNumber());
		assertEquals("Grand Star", u.getBuilding());
		assertEquals("5",u.getFloor());
		assertEquals("599",u.getUnit());
		
		u.setFirstName("Scott");
		assertEquals("Scott",u.getFirstName());
		
		u.setLastName("Thompson");
		assertEquals("Thompson",u.getLastName());
		
		u.setUsername("ST7879");
		assertEquals("ST7879",u.getUsername());
		
		u.setPassword("forest789");
		assertEquals("forest789",u.getPassword());
		
		u.setPhoneNumber("789-123-4567");
		assertEquals("789-123-4567",u.getPhoneNumber());
		
		u.setBuilding("Peachtree Main");
		assertEquals("Peachtree Main", u.getBuilding());
		
		u.setFloor("8");
		assertEquals("8",u.getFloor());
		
		u.setUnit("855");
		assertEquals("855",u.getUnit());
	}
	
	
	
	@Test
	void unitTest() {
		
		
		ArrayList<Client> masterList;
		
		System.out.println("Testing");
		Floor f1 = new Floor("4",80);
		Floor f2 = new Floor("3",80);
		Floor f3 = new Floor("2",80);
		Floor f4 = new Floor("1",80);
		Floor f = new Floor("5",80);
		Unit u1 = new Unit("Cedar Pointe 568", "568");
		u1.occupants.add("Wyatt Carlson");
		u1.occupants.add("Wade Jones");
		f.units.add(u1);
		Unit u2 = new Unit("Cedar Pointe 569","569");
		u2.occupants.add("Drew Yanela");
		u2.occupants.add("Connor Banks");
		f.units.add(u2);
		Unit u3 = new Unit("Cedar Pointe 570","570");
		u3.occupants.add("Amy Fropen");
		u3.occupants.add("Katie Douglas");
		f.units.add(u3);
		
		Building b = new Building("Cedar Pointe", "1312 Carmel Rd, Monterray CA, 93940", 575);
		b.floors.add(f4);
		b.floors.add(f3);
		b.floors.add(f2);
		b.floors.add(f1);
		b.floors.add(f);
		
		
		
		Client c = new Client("Gavin", "Rosedale", "grose", "tu868r", "786-456-4837", "I need", "To change", "this part");
		c.properties.add(b);
		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter Building Number");
		Iterator<Building> iter = c.properties.iterator();
		int i = 1;
		while (iter.hasNext())
		{
			System.out.println(i + ") " + iter.next().getName());
		}
		int bb = in.nextInt();
		Iterator<Floor> iterr = c.properties.get(bb-1).floors.iterator();
		i = 1;
		System.out.println("Enter Floor Number");
		while (iterr.hasNext())
		{
			System.out.println(i + ") " + iterr.next().getName());
			i++;
		}
		int ff = in.nextInt();
		Iterator<Unit> iterrr = c.properties.get(bb-1).floors.get(ff-1).units.iterator();
		i = 1;
		System.out.println("Enter Unit Number");
		while (iterrr.hasNext())
		{
			System.out.println(i + ") " + iterrr.next().getName());
			i++;
		}
		int uu = in.nextInt();
		Iterator<String> iterrrr = c.properties.get(bb-1).floors.get(ff-1).units.get(uu-1).occupants.iterator();
		i=1;
		System.out.println("Enter Name");
		while (iterrrr.hasNext())
		{
			System.out.println(i + ") " + iterrrr.next());
			i++;
		}
		int nn = in.nextInt();
		
		
		String[] nameSplit = c.properties.get(bb-1).floors.get(ff-1).units.get(uu-1).occupants.get(nn-1).split("\\s+");
		
		
		System.out.println("Enter your choosen username");
		String username = in.next();
		System.out.println("Enter your choosen password");
		String password = in.next();
		System.out.println("Enter your PhoneNumber");
		String phone = in.next();
		
		
		
		Tenant t = new Tenant(nameSplit[0], nameSplit[1], username, password, phone, c.properties.get(bb-1).getName(), c.properties.get(bb-1).floors.get(ff-1).getName(), c.properties.get(bb-1).floors.get(ff-1).units.get(uu-1).getName());
		
		
		
		
		
		
		System.out.println(t.getFirstName());
		System.out.println(t.getLastName());
		System.out.println(t.getUsername());
		System.out.println(t.getPassword());
		System.out.println(t.getPhoneNumber());
		System.out.println(t.getBuilding());
		System.out.println(t.getFloor());
		System.out.println(t.getUnit());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
		
		
		
		
		
		
		
		
	}
	
	
	

}
