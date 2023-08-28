/**
 * The ItemInfoNode class turns an iteminfo object into a node in a doubly linkedlist 
 * 
 * 
 * @author Joshua James
 * e-mail: Joshua.james@stonybrook.edu
 * Stony Brook ID: 115113767
 */

public class ItemInfoNode {
    private ItemInfo ref; //connects to the iteminfo object that contains all the variables for name, price, etc 
    private ItemInfoNode prev; //keeps track of the prev node in the linkedlist 
    private ItemInfoNode next;//keeps track of the next node in the linkedlist 
    /**
     * default constructor
     */
    public ItemInfoNode(){
        ref = null;
        prev = null;
        next = null; 
    }
    /**
     * mutator for ref
     * @param info
     * input for the itemInfo
     */
    public void setInfo(ItemInfo info){
        ref = info;
    }
    /**
     * accessor for ref
     * @return
     * the iteminfo object 
     */
    public ItemInfo getInfo(){
        return ref;
    }
    /**
     * mutator for next
     * @param node
     *  the next node in the list 
     */
    public void setNext(ItemInfoNode node){
        next = node;
    }
    /**
     * mutator for prev
     * @param node
     *  the prev node in the list 
     */
    public void setPrev(ItemInfoNode node){
        prev = node;
    }
    /**
     * accessor for the next node in the list 
     * @return
     * the next node in the list 
     */
    public ItemInfoNode getNext(){
        return next;
    }
    /**
     * accessor for the previous node in the list 
     * @return
     * the previous node in the list 
     */
    public ItemInfoNode getPrev(){
        return prev; 
    }
}
