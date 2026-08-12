import java.util.*;

class RandomizedSet {
    List<Integer> rset = new ArrayList<>();
    int size = 0;
    Random rd = new Random();

    public RandomizedSet() {
    }

    public boolean insert(int val) {
        if (rset.indexOf(val) == -1) {
            rset.add(val);
            size++;
            return true;
        } else {
            return false;
        }
    }

    public boolean remove(int val) {
        if (rset.indexOf(val) != -1) {
            rset.remove(Integer.valueOf(val));
            size--;
            return true;
        } else {
            return false;
        }
    }

    public int getRandom() {
        return rset.get(rd.nextInt(size));
    }
}