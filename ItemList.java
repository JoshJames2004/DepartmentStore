/**
 * The ItemList class contains the linkedlist of iteminfonodes and the method for manipulating this list 
 * 
 * @author Joshua James
 * e-mail: Joshua.james@stonybrook.edu
 * Stony Brook ID: 115113767
 */
public class ItemList {

    private ItemInfoNode head; //keeps track of the first node in the list 
    private ItemInfoNode tail; //keeps track of the last node in the list 
    private ItemInfoNode cursor; //used for iterating over the list 
    /**
     * default constructor 
     */
    public ItemList(){
        head = null;
        tail = null; 
        cursor = head;
    }
    /**
     * nserts the info into the list in its correct position based on its rfidTagNumber.
     * @param name
     * name of the item
     * @param rfidTag
     * rfidtag of the item 
     * @param price
     * price of the item 
     * @param initPosition
     * original and current location of the item 
     */
    public void insertInfo(String name, String rfidTag, double price, String initPosition){
        
        ItemInfoNode one = new ItemInfoNode();
        cursor = head;

        one.setInfo(new ItemInfo(name, price, rfidTag, initPosition));
        
        if(cursor == null){
            head = one; //sets head to the first node in the list 
        }
        else{
            //Time Complexity: O(n)
            //explanation: worst case scenario the loop iterates through the entire linkedlist once resulting in the number of steps being the number of nodes in the list 
            //best case this is O(1)
            while(cursor != null){  //while loop that determines the location of new nodes sorted least to greatest by rfidTagNumber 
                if(hexToDec(one.getInfo().getTag()) >= hexToDec(cursor.getInfo().getTag())){ 
                   
                    if(cursor.getNext() == null){ //incase the that is being compared to the new node is the tail
                        cursor.setNext(one);
                        one.setPrev(cursor);
                        
                        break;
                    }
                    else{
                        cursor = cursor.getNext();
                    } 
                }
                else if(hexToDec(one.getInfo().getTag()) < hexToDec(cursor.getInfo().getTag())){ //if it finds a node with an rfid larger than the new node's
                    if(cursor.getPrev() == null){ //incase the node is the head 
                        one.setNext(cursor);
                        cursor.setPrev(one); 
                        head = one;
                    }
                    else {
                        one.setNext(cursor);
                        one.setPrev(cursor.getPrev());
                        cursor.getPrev().setNext(one);
                        cursor.setPrev(one);
                    }
                    

                   break;

                }
                
            }
        }
        

    }
    /**
     * converts the hexadecimal number to decimal base 10
     * @param hex
     * hex string input
     * @return
     * returns the number as an int 
     */
    public int hexToDec(String hex){
        int base = 1;
        int dec = 0; 
        for(int i = hex.length() - 1; i >= 0; i--){
            if(hex.charAt(i) > '0' && hex.charAt(i) <= '9'){ //converts int characters to int 
                dec += (hex.charAt(i) - 48) * base;
                base *= 16; 
            }

            else if(hex.charAt(i) >= 'A' && hex.charAt(i) <= 'F'){ //converts letter character to int 
                dec +=(hex.charAt(i) - 55) * base;
                base *= 16;
            }
        }

        return dec; 
    }
    /**
     * removes every item that has been purchased(has current location as out)
     * prints out list of the items that were removed 
     * Precondition:
     * items in the itemlist 
     */
    public void removeAllPurchased(){
        
        cursor = head;
        System.out.println("the following items have been removed: ");
        System.out.println(String.format("%-20.20s" + "%-20.9s" + "%-20.6s" + "%-20.6s" + "\tPrice", "Name", "TagNumber", "CurLoc", "OrigLo"));
        System.out.println("----------------------------------------------------------------------------------------------");
        //Time Complexity: O(n)
        //explanation: The loop will always loop through the entire linkedlist to find all purchased items, therefore the number of steps = number of nodes 
        while(cursor != null){
            if(cursor.getInfo().getCurLoc().equalsIgnoreCase("out")){
                System.out.print(String.format("%-20.20s" + "%-20.9s" + "%-20.6s" + "%-20.6s", cursor.getInfo().getName(), cursor.getInfo().getTag(), cursor.getInfo().getCurLoc(), cursor.getInfo().getOriLoc()));
                System.out.println("\t" + cursor.getInfo().getPrice());
                removeNode(cursor);
            }

            cursor = cursor.getNext();
            
        }
    }
    /**
     * used to remove a node from the linkedlist 
     * @param n
     * the node to be removed
     */
    public void removeNode(ItemInfoNode n){
        //different options depending on whether the node is head, tail, or in the middle
        if(n.getPrev() == null && n.getNext() == null){ //node is only node in list 
            head = null;
        }
        else if(n.getPrev() == null && n.getNext() != null){ //node is the head 
            head = n.getNext();
            n.getNext().setPrev(null);
        }
        else if(n.getPrev() != null && n.getNext() == null){ //node is the tail
            tail = n.getPrev();
            n.getPrev().setNext(null);
            
        }
        else if(n.getPrev() != null && n.getNext() != null){ //node is in the middle 
            n.getNext().setPrev(n.getPrev()); 
            n.getPrev().setNext(n.getNext()); 
            
            
        }
    }
    /**
     * changes a certain item's current location
     * @param rfidTag
     *  tag used to identify the item 
     * @param source
     *  currentlocation of the item used to identify the item 
     * @param dest
     *  where the item should be moved to 
     * @return
     *  true or false depending on whether it worked or not 
     */
    public boolean moveItem(String rfidTag, String source, String dest){
        
        cursor = head;
        //error checking the source and destination
        if((validCart(dest) == false) && (!(dest.equals("out")) && (validShelf(dest) == false))){
            throw new IllegalArgumentException("Invalid destination"); 
        }

        if(source.equals("out")){
            throw new IllegalArgumentException("Invalid source"); 
        }
        //Time Complexity: O(n)
        //Explanation: Worst case scenerio the loop iterates through the entire linked list, the number of steps = the number of nodes in the list 
        while(cursor != null){
            if(cursor.getInfo().getTag().equals(rfidTag) && cursor.getInfo().getCurLoc().equals(source)){
                cursor.getInfo().setCurLoc(dest);
                return true;
            }

            cursor = cursor.getNext();
            
        }
        
        

        return false;
    }
    /**
     * checks whether the input is a valid cart code 
     * @param cNum
     * input for cart code 
     * @return
     * true or false depending on whether it is valid or not 
     */
    public boolean validCart(String cNum){
        if(cNum.length() == 4){
            for(int i = 0; i < 4; i++){
                if(i == 0){
                    if(cNum.charAt(i) == 'c'){
                    }
                    else{
                        return false;
                    }
                }
                if(i > 0){
                    if(cNum.charAt(i) >= '0' && cNum.charAt(i) <= '9'){
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        else {
            return false;
        }
        return true; 
    }
    /**
     * checks whether the input is a valid shelf code 
     * @param cNum
     * input for shelf code 
     * @return
     * true or false depending whether its valid or not 
     */
    public boolean validShelf(String cNum){
        if(cNum.length() == 6){
            for(int i = 0; i < 6; i++){
                if(i == 0){
                    if(cNum.charAt(i) == 's'){
                    }
                    else{
                        return false;
                    }
                }
                if(i > 0){
                    if(cNum.charAt(i) >= '0' && cNum.charAt(i) <= '9'){
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        else {
            return false;
        }
        return true; 
    }
    /**
     * prints a table of all the items in the store and their variable values 
     * Precondition: the items are sorted by rfidtagnumber 
     * Postcondition: displays a table of all the items in the store 
     */
    public void printAll(){
        
        System.out.println(String.format("%-20.20s" + "%-20.9s" + "%-20.6s" + "%-20.6s" + "\tPrice", "Name", "TagNumber", "CurLoc", "OrigLo"));
        System.out.println("----------------------------------------------------------------------------------------------");

        cursor = head;
        //Time Complexity: O(n)
        //Explanation: the loop will always iterate through the entire linked list, the number of steps = number of nodes 
        while(cursor != null){
            System.out.print(String.format("%-20.20s" + "%-20.9s" + "%-20.6s" + "%-20.6s", cursor.getInfo().getName(), cursor.getInfo().getTag(), cursor.getInfo().getCurLoc(), cursor.getInfo().getOriLoc()));
            System.out.println("\t" + cursor.getInfo().getPrice()); 
            if(cursor.getNext() == null){
                break;
            }
            cursor = cursor.getNext();
        }
    }
    /**
     * prints all the items at a certain location in a table 
     * @param location
     *  input for the location
     */
    public void printByLocation(String location){
        
        boolean run = true;
        cursor = head;
        System.out.println(String.format("%-20.20s" + "%-20.9s" + "%-20.6s" + "%-20.6s" + "\tPrice", "Name", "TagNumber", "CurLoc", "OrigLo"));
        System.out.println("----------------------------------------------------------------------------------------------");
        //Time Complexity: O(n) the loop will go through the entire linkedList, number of steps = number of nodes 
        while(run){
            if(cursor.getInfo().getCurLoc().equalsIgnoreCase(location)){
                System.out.print(String.format("%-20.20s" + "%-20.9s" + "%-20.6s" + "%-20.6s", cursor.getInfo().getName(), cursor.getInfo().getTag(), cursor.getInfo().getCurLoc(), cursor.getInfo().getOriLoc()));
                System.out.println("\t" + cursor.getInfo().getPrice()); 
            }
            if(cursor.getNext() == null){
                run = false;
            }
            
            cursor = cursor.getNext();
        }
        
    }
    /**
     * Takes all items that are on a shelf different than their original shelf and changes curlocation to orilocation 
     * displays a table of the items that were moved 
     */
    public void cleanStore(){
    
        cursor = head;
        System.out.println("The following item(s) have been moved back to their original locations: "); 
        System.out.println(String.format("%-20.20s" + "%-20.9s" + "%-20.6s" + "%-20.6s" + "\tPrice", "Name", "TagNumber", "CurLoc", "OrigLo"));
        System.out.println("----------------------------------------------------------------------------------------------");
        //Time Complexity: O(n) the loop will go through the entire linkedList, number of steps = number of nodes 
        while(cursor != null){
            if((!(cursor.getInfo().getCurLoc().equalsIgnoreCase(cursor.getInfo().getOriLoc()))) && (cursor.getInfo().getCurLoc().length() == 6)){
                System.out.print(String.format("%-20.20s" + "%-20.9s" + "%-20.6s" + "%-20.6s", cursor.getInfo().getName(), cursor.getInfo().getTag(), cursor.getInfo().getCurLoc(), cursor.getInfo().getOriLoc()));
                System.out.println("\t" + cursor.getInfo().getPrice()); 
                cursor.getInfo().setCurLoc(cursor.getInfo().getOriLoc());
            }
            cursor = cursor.getNext(); 
            
        }
    }
    /**
     * takes all the items in a cart and changes their current location to out 
     * @param cartNumber
     * the code for the cart 
     * @return
     * the price of the all the items in the cart 
     * @exception IllegalArgumentException
     *  the cartnumber is invalid 
     */
    public double checkOut(String cartNumber){

        cursor = head; 
        System.out.println(String.format("%-20.20s" + "%-20.9s" + "%-20.6s" + "%-20.6s" + "\tPrice", "Name", "TagNumber", "CurLoc", "OrigLo"));
        System.out.println("----------------------------------------------------------------------------------------------");
        double total = 0.0;
        if(validCart(cartNumber) == false){
            throw new IllegalArgumentException("Invalid Cart Number"); 
        }
        //Time Complexity: O(n) the loop will go through the entire linkedList, number of steps = number of nodes 
        while(cursor != null){
            if(cursor.getInfo().getCurLoc().equals(cartNumber)){
                System.out.print(String.format("%-20.20s" + "%-20.9s" + "%-20.6s" + "%-20.6s", cursor.getInfo().getName(), cursor.getInfo().getTag(), cursor.getInfo().getCurLoc(), cursor.getInfo().getOriLoc()));
                System.out.println("\t" + cursor.getInfo().getPrice());
                cursor.getInfo().setCurLoc("out");
                total += cursor.getInfo().getPrice();
            }

            cursor = cursor.getNext();
        }
        System.out.print("Total: ");

        return total;
    }
    //extra credit
    /**
     * prints a table of all the items with the same rfidtag number 
     * @param tag
     *  the tagnumber of the items 
     */
    public void printRFID(String tag){
        cursor = head; 
        System.out.println(String.format("%-20.20s" + "%-20.9s" + "%-20.6s" + "%-20.6s" + "\tPrice", "Name", "TagNumber", "CurLoc", "OrigLo"));
        System.out.println("----------------------------------------------------------------------------------------------");
        //Time Complexity: O(n) the loop will go through the entire linkedList, number of steps = number of nodes 
        while(cursor != null){
            if(cursor.getInfo().getTag().equals(tag)){
                System.out.print(String.format("%-20.20s" + "%-20.9s" + "%-20.6s" + "%-20.6s", cursor.getInfo().getName(), cursor.getInfo().getTag(), cursor.getInfo().getCurLoc(), cursor.getInfo().getOriLoc()));
                System.out.println("\t" + cursor.getInfo().getPrice());
            }

            cursor = cursor.getNext();
        }
    }

    
}
