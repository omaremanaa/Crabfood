package testcrab;

public class Bag<T>  implements BagInterface<T> {
    private static final int DEFAULT_CAPACITY = 1;
    private T[] bag = (T[]) new Object[DEFAULT_CAPACITY];  //initialese array size
    private int numberOfEntries;

    public Bag(){
        this(DEFAULT_CAPACITY);
    }

    public Bag(int sizeofUnionbag) {
        bag = (T[]) new Object[sizeofUnionbag];
    }

    public int getCurrentSize() {
        return numberOfEntries;
    }

    public boolean isFull() {
        if (numberOfEntries == DEFAULT_CAPACITY) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty() {
        if (numberOfEntries == 0)
            return true;
        else
            return false;
    }

    public boolean add(T newEntry) {
        if (!isFull()) {
            bag[numberOfEntries++] = newEntry;
            return true;
        } else
            return false;
    }

    public T remove() {
        T result = removeEntry(numberOfEntries - 1);
        return result;
    }

    private T removeEntry(int givenIndex) {
        T result = null;
        if (!isEmpty() && givenIndex >= 0) {
            result = bag[givenIndex];
            numberOfEntries--;
            bag[givenIndex] = bag[numberOfEntries];
            bag[numberOfEntries] = null;
        }
        return result;
    }

    public boolean remove(T anEntry) {
        int flag = 0;
        for (int i = 0; i < numberOfEntries; i++) {
            if (anEntry == bag[i] || anEntry.equals(bag[i])) {
                bag[i] = bag[numberOfEntries--];
                flag = 1;
                break;
            }
        }
        if (flag == 1)
            return true;
        else
            return false;
    }


    public void clear() {
        while (!isEmpty())
            remove();
    }

    public int getFrequencyOf(T anEntry) {
        int index = 0;
        for (int i = 0; i < numberOfEntries; i++) {
            if (anEntry == bag[i] || anEntry.equals(bag[i])) {
                index++;
            }
        }
        return index;
    }

    @Override
    public boolean contains(T anEntry) {
        int flag = 0;
        for (int i = 0; i < numberOfEntries; i++) {
            if (anEntry == bag[i] || anEntry.equals(bag[i])) {
                flag = 1;
            }
        }
        if (flag == 1)
            return true;
        else
            return false;
    }

    @Override
    public T[] toArray() {
        T[] result = (T[]) new Object[numberOfEntries];
        for (int i = 0; i < numberOfEntries; i++) {
            result[i] = bag[i];
        }
        return result;
    }

    public String toString() {
        String a= " ";
        for(int i = 0; i <= numberOfEntries; i++){
            a +=  " " + bag[i];
        }
        return a;
    }

    /**
     * Creates a new bag by combining another bag and this bag.
     * @param anotherBag bag is to be combined.
     * @return the totalbag.
     */

    public BagInterface<T> union (BagInterface<T> anotherBag){
        int sizeofUnionbag = anotherBag.getCurrentSize() + getCurrentSize();
        BagInterface unionbag = new Bag(sizeofUnionbag);
        Bag<T> otherBag = (Bag<T>) anotherBag;
        for (int i = 0; i < numberOfEntries; i++){
            unionbag.add(bag[i]);
        }
        for (int i = 0; i < anotherBag.getCurrentSize(); i++){
            unionbag.add(otherBag.bag[i]);
        }
        return unionbag;
    }

    /**
     * Create new bag by combining the bag item that has the same with another bag
     * @param anotherBag bag that combined
     * @return the interesection bag.
     */

    public BagInterface<T> intersection (BagInterface<T> anotherBag){
        BagInterface<T> intersectionbag = new Bag<>();
        Bag<T> secondbag = (Bag<T>) anotherBag;
        BagInterface<T> anotherbag = new Bag<>();
        for (int i = 0; i< secondbag.getCurrentSize(); i++){
            anotherbag.add(secondbag.bag[i]);
        }
        for (int i = 0; i < getCurrentSize(); i++){
            if (anotherBag.contains((bag[i]))){
                intersectionbag.add(bag[i]);
                anotherBag.remove(bag[i]);
            }
        }
        return intersectionbag;
    }

    /** Creates a new bag of objects that would be left in this bag
     after removing those that also occur in anotherBag.
     @param anotherBag  the bag that is to be removed
     @return a combined bag */
    public BagInterface<T> difference(BagInterface<T> anotherBag) {
        BagInterface<T> differenceBag = new Bag<T>();
        Bag<T> otherBag = (Bag<T>)anotherBag;
        int index;
        // copy this bag
        for (index = 0; index < numberOfEntries; index++)
        {
            differenceBag.add(bag[index]);
        } // end for
        // remove the ones that are in anotherBag
        for (index = 0; index < otherBag.getCurrentSize(); index++)
        {
            if (differenceBag.contains(otherBag.bag[index]))
            {
                differenceBag.remove(otherBag.bag[index]);
            } // end if
        } // end for
        return differenceBag;
    }
}
