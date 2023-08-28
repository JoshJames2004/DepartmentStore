/**
 * The ItemList class contains the menu and user interaction of the program
 * 
 * @author Joshua James
 * e-mail: Joshua.james@stonybrook.edu
 * Stony Brook ID: 115113767
 */
import java.util.Scanner;
public class DepartmentStore {
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        Scanner sc = new Scanner(System.in); 

        ItemList store = new ItemList();

        boolean cont = true;

        while(cont == true){
            System.out.println("C - Clean Store");
            System.out.println("I - Insert an Item into the List");
            System.out.println("L - List by Location");
            System.out.println("M - Move an Item in the Store");
            System.out.println("O - Check Out");
            System.out.println("P - Print All Items in the Store");
            System.out.println("U - Update Inventory System");
            System.out.println("R - Print by RFID Tag Number");
            System.out.println("Q - Exit");

            System.out.println("Please Select an Option");

            String input = s.nextLine(); 

            if(input.equalsIgnoreCase("C")){
                store.cleanStore();
            }

            else if(input.equalsIgnoreCase("I")){
                System.out.println("Enter the Name");
                String name = s.nextLine();
                System.out.println("enter the RFID");
                String RFID = s.nextLine();
                System.out.println("Enter the Location");
                String loc = s.nextLine();
                System.out.println("Enter the price");
                double p = sc.nextDouble();

                store.insertInfo(name, RFID, p, loc);

                System.out.println("Item Added"); 
            }

            else if(input.equalsIgnoreCase("L")){
                System.out.println("Enter the location");
                String loc = s.nextLine();

                store.printByLocation(loc);
            }

            else if(input.equalsIgnoreCase("M")){
                System.out.println("Enter the RFID");
                String rfid = s.nextLine();
                System.out.println("Enter the original Location");
                String oriLoc = s.nextLine();
                System.out.println("Enter the new location");
                String curLoc = s.nextLine();

                store.moveItem(rfid, oriLoc, curLoc);

            }
            else if(input.equalsIgnoreCase("O")){
                System.out.println("Enter the cart  number");
                String cart = s.nextLine();

                System.out.println(store.checkOut(cart)); 
            }
            else if(input.equalsIgnoreCase("P")){
                store.printAll();
            }
            else if(input.equalsIgnoreCase("U")){
                store.removeAllPurchased();
            }
            else if(input.equalsIgnoreCase("R")){
                System.out.println("Enter the RFID tag");
                String rfidTag = s.nextLine();
                store.printRFID(rfidTag);
            }
            else if(input.equalsIgnoreCase("Q")){
                cont = false;
            }
            else {
                System.out.println("Invalid Input"); 
            }
            System.out.println();
        }

        s.close();

    }
}
