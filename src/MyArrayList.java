import java.util.Arrays;

public class MyArrayList implements MyList {

    public int count;
    Object[] array;

    public MyArrayList() {
        count = 0;
        array = new Object[10];
    }

    public void printAll() {
        System.out.println(Arrays.toString(array));
    }

    public void expand(int index) {
        int temp = this.array.length;

        while (index >= temp) {
            temp = temp * 2;
        }

        Object[] array = new Object[temp];

        if (this.array.length > index) {
            System.arraycopy(this.array, 0, array, 0, index);
            System.arraycopy(this.array, index, array, index + 1, this.array.length - index - 1);
        }

        else {
            System.arraycopy(this.array, 0, array, 0, this.array.length);
        }

        this.array = array;
    }

    @Override
    public void add(Object elem) {
        add(count, elem);

//        if (count == this.array.length) {
//            Object[] array = new Object[this.array.length * 2];
//            System.arraycopy(this.array, 0, array, 0, this.array.length);
//            System.arraycopy(array, 0, this.array, 0, this.array.length);
//            this.array = array;
//        }
//
//        this.array[count] = elem;
//        count++;
    }

    @Override
    public void add(int index, Object elem) {

//        int temp = index >= this.array.length ? (index / 10 + 1) * 10 : this.array.length;
//        Object[] array = new Object[temp];
//
//        if (this.array.length > index) {
//            System.arraycopy(this.array, 0, array, 0, index);
//            array[index] = elem;
//            System.arraycopy(this.array, index, array, index + 1, this.array.length - index - 1);
//            count++;
//        }
//
//        else {
//            System.arraycopy(this.array, 0, array, 0, this.array.length);
//            array[index] = elem;
//            count++;
//        }
//
//        this.array = array;

        if (index > count && index < array.length) {
            array[index] = elem;
        }
        else {
            expand(index);
            array[index] = elem;
        }

        count = index > count ? ++index : ++count;

    }

    @Override
    public Object set(int index, Object elem) {
        Object temp = array[index];
//        array[index] = elem;
//        count = index > count ? ++index : count++;

//          if (index > array.length) {
            array[index] = elem;
//        }
//        else {
//            expand(index);
//            array[index] = elem;
//        }

        count = index > count ? ++index : count;

        return temp;
    }

    @Override
    public boolean remove(Object elem) {
        int temp = indexOf(elem);

        if (temp == -1) {
            return false;
        }

        else {
            remove(temp);
            return true;
        }



//        boolean temp = false;
//
//        for (int i = 0; i < array.length; i++) {
//            if (array[i] == null) continue;
//            else if (array[i].equals(elem)) {
//                array[i] = null;
//                temp = true;
//                count--;
//            }
//        }
//        return temp;
    }


    @Override
    public Object remove(int index) {
        Object temp = this.array[index];
        Object[] array = new Object[this.array.length];
        System.arraycopy(this.array, 0, array, 0, this.array.length);
        System.arraycopy(array, index + 1, this.array, index, array.length - index - 1);

//        if (temp != null) {
        count--;
//        }

        return temp;
    }

    @Override
    public void clear() {
        Object[] array = new Object[this.array.length];
        Arrays.fill(array, 0, this.array.length, null);
        this.array = array;
                                    //System.arraycopy(array, 0, this.array, 0, array.length);
        count = 0;
    }

    @Override
    public Object get(int index) {
        return array[index];
    }

    @Override
    public int indexOf(Object elem) {
//        int temp = 0;
//
//        for (int i = 0; i < 10; i++) {
//            if (elem == null && array[i] == null) {
//                temp = i;
//            } else if (array[i] == null) {
//                continue;
//            } else if (array[i].equals(elem)) {
//                temp = i;
//                break;
//            }
//        }

        for (int i = 0; i < array.length; i++) {
            if (elem == null) {
                if (array[i] == null) {
                    return i;
                }
            }
            else {
                if (elem.equals(array[i])) {
                    return i;
                }
            }
        }

        return -1;


        /*
        int temp = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(elem)) temp = i;
        }
        return temp;
        */
    }

    @Override
    public boolean contains(Object elem) {
//        boolean temp = false;
//
//        for (int i = 0; i < array.length; i++) {
//            if (array[i] == null) continue;
//            else if (array[i].equals(elem)) temp = true;
//        }
//
//        return temp;
        return indexOf(elem) != -1;
    }

    @Override
    public int size() {
        return count;
    }

    public static void main(String[] args) {
        MyArrayList obj = new MyArrayList();

        obj.add("f0");
        obj.printAll();
//        System.out.println(obj.get(0));
        System.out.println("count = " + obj.count);
        obj.add("f1");
        obj.printAll();
//        System.out.println(obj.get(1));
        System.out.println("count = " + obj.count);
        obj.add("f2");
        obj.printAll();
//        System.out.println(obj.get(2));
        System.out.println("count = " + obj.count);

        obj.set(18, "f18");
        System.out.println(obj.get(18));
        System.out.println("set отработал; count = " + obj.count);

        obj.add("f3");
        obj.printAll();
//        System.out.println(obj.get(3));
        System.out.println("count = " + obj.count);
        obj.add("f4");
        obj.printAll();
//        System.out.println(obj.get(4));
        System.out.println("count = " + obj.count);
        obj.add("f5");
        obj.printAll();
//        System.out.println(obj.get(5));
        System.out.println("count = " + obj.count);
        obj.add("f6");
        obj.printAll();
//        System.out.println(obj.get(6));
        System.out.println("count = " + obj.count);
        obj.add("f7");
        obj.printAll();
//        System.out.println(obj.get(7));
        System.out.println("count = " + obj.count);
        obj.add("f8");
        obj.printAll();
//        System.out.println(obj.get(8));
        System.out.println("count = " + obj.count);
        obj.add("f9");
        obj.printAll();
//        System.out.println(obj.get(9));
        System.out.println("count = " + obj.count);
        obj.add("f10");
        obj.printAll();
//        System.out.println(obj.get(10));
        System.out.println("count = " + obj.count);


        obj.add(21, "f21");
        obj.printAll();
//        System.out.println(obj.get(21));
        System.out.println("count = " + obj.count);

//        obj.set(8, "f8");
//        System.out.println(obj.get(8));
//        System.out.println("count = " + obj.count);

        obj.printAll();

        int x;
        x = obj.indexOf("f6");
        System.out.println("indexOf отработал: " + x);
        System.out.println(obj.contains("f23"));

        System.out.println("indexOf отработал: " + obj.indexOf("f100500"));
        System.out.println("indexOf отработал: " + obj.indexOf(null));

//        obj.remove("f7");
//        obj.remove("f13");
//        System.out.println("count = " + obj.count);

        System.out.println(obj.remove(7));
        System.out.println(obj.remove(13));
        System.out.println("count = " + obj.count);

        obj.printAll();

        //obj.clear();
        //System.out.println(obj.get(8));
        //System.out.println("count = " + obj.count);

        /*
        obj.add("f0");
        System.out.println(obj.get(0));
        System.out.println("count = " + obj.count);

        obj.add(1, "f1");
        System.out.println(obj.get(1));
        System.out.println("count = " + obj.count);

        obj.add("f1");
        System.out.println(obj.get(2));
        System.out.println("count = " + obj.count);

        obj.set(2, "f2");
        System.out.println(obj.get(2));
        System.out.println("count = " + obj.count);

        obj.remove("f1");
        System.out.println(obj.get(1));
        System.out.println("count = " + obj.count);

        System.out.println(obj.contains("f2"));

        obj.clear();
        System.out.println(obj.get(0));
        System.out.println("count = " + obj.count);
        */
    }
}
