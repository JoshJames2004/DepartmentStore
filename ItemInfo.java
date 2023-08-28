/**
 * The ItemInfo class contains all the values that makes up an item in the store
 * 
 * @author Joshua James
 * e-mail: Joshua.james@stonybrook.edu
 * Stony Brook ID: 115113767
 */
public class ItemInfo {
    private String name; //name of item
    private double price; //price of item
    private String rfidTagNumber; //9 character hexadecimal tag for item
    private String oriLoc; //6 character code for the shelf the item is on
    private String curLoc; // keeps track of where the item is now 
    /**
     * default constructor
     */
    public ItemInfo(){
        name = null;
        price = 0.0;
        rfidTagNumber = null;
        oriLoc = null;
        curLoc = null;

    }
    /**
     * Constructor for iteminfo object
     * @param n
     * sets equal to name
     * @param p
     *  used for price
     * @param num
     *  used for rfidtag
     * @param loc
     *  used for oriLocation and curLocation
     * @exception IllegalArgumentException
     *  thrown for invalid rfidTagnumber and invalid location
     */
    public ItemInfo(String n, double p, String num, String loc){
        name = n;
        price = p;
        if(validTag(num)){
            rfidTagNumber = num;
        }
        else{
            throw new IllegalArgumentException("Invalid Tag");
        }
        if(validShelf(loc) == true){
            oriLoc = loc;
            curLoc = loc; 
        }
        else {
            throw new IllegalArgumentException("Invalid Location"); 
        }
    }
    /**
     * checks the input for the rfidTag entered by the user to see if it is in the valid style
     * @param tNum
     *  rfidtag input 
     * @return
     *  returns true or false depending on if it is valid or not 
     */
    public boolean validTag(String tNum){
        if(tNum.length() == 9){
            for(int i = 0; i < 9; i++){
                //checks whether the character is a number 1-9 or a letter A-F which makes up hexadecimal numbers
                if(tNum.charAt(i) >= '0' && tNum.charAt(i) <= '9'){ 
                }
                else if(tNum.charAt(i) >= 'A' && tNum.charAt(i) <= 'F'){
                }
                else {
                    return false;
                }
                
            }
            return true;
        }
        return false;
    }
    /**
     * checks whether a shelf code is valid
     * @param cNum
     *  input for the sheld code 
     * @return
     *  true or false depending on whether it is valid or not 
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
     * mutator for name 
     * @param n
     *  input for name 
     */
    public void setName(String n){
        name = n;
    }
    /**
     * accessor for name 
     * @return
     * name string
     */
    public String getName(){
        return name; 
    }
    /**
     * mutator for price
     * @param p
     * input for price 
     */
    public void setPrice(double p){
        price = p;
    }
    /**
     * accessor for price 
     * @return
     * double price 
     */
    public double getPrice(){
        return price; 
    }
    /**
     * mutator for rfidtag
     * @param t
     * input for the tag
     */
    public void setTag(String t){
        rfidTagNumber = t;
    }
    /**
     * accessor for rfidtag
     * @return
     *  the rfidtagnumber 
     */
    public String getTag(){
        return rfidTagNumber; 
    }
    /**
     * mutator for original location
     * @param o
     * input for the original location
     */
    public void setOriLoc(String o){
        if(validShelf(o)){
            oriLoc = o;
        }
    }
    /**
     * accessor for original location
     * @return
     * the original location
     */
    public String getOriLoc(){
        return oriLoc; 
    }
    /**
     * mutator for current location
     * @param c
     * input for current location
     */
    public void setCurLoc(String c){
        curLoc = c;
    }
    /**
     * accessor for current location
     * @return
     * the current location string 
     */
    public String getCurLoc(){
        return curLoc; 
    }

}
