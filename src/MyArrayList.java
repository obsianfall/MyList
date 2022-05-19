import java.util.Arrays;
import java.util.Iterator;

public class MyArrayList implements MyList, Iterable {

    private int count;
    private Object[] array;

    private MyArrayList() {
        count = 0;
        array = new Object[10];
    }

    private void printAll() {
        System.out.println(Arrays.toString(array));
    }

    private void expand(int index) {
        int temp = this.array.length;

        while (index >= temp) {
            temp = temp * 2;
        }

        Object[] array = new Object[temp];
        System.arraycopy(this.array, 0, array, 0, this.array.length);
        this.array = array;
    }

    private void shiftRight(int index) {
        Object[] array = new Object[this.array.length];
        System.arraycopy(this.array, 0, array, 0, index);
        System.arraycopy(this.array, index, array, index + 1, this.array.length - index - 1);
        this.array = array;
    }

    private void shiftLeft(int index) {
        Object[] array = new Object[this.array.length];
        System.arraycopy(this.array, 0, array, 0, this.array.length);
        System.arraycopy(array, index + 1, this.array, index, array.length - index - 1);
    }

    @Override
    public void add(Object elem) {
        add(count, elem);
    }

    @Override
    public void add(int index, Object elem) {
        if (index < count) {
            shiftRight(index);
            array[index] = elem;
        }

        else if (index < array.length) {
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

        Object temp;

        if (index >= array.length) {
              expand(index);
              array[index] = elem;
              temp = null;            //здесь мог быть Exception
          }

         else {
              temp = array[index];
              array[index] = elem;
          }

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

    }

    @Override
    public Object remove(int index) {
        Object temp = this.array[index];        //здесь может быть Exception
        shiftLeft(index);
        count--;

        return temp;
    }

    @Override
    public void clear() {
        Object[] array = new Object[this.array.length];
        Arrays.fill(array, 0, this.array.length, null);
        this.array = array;
        count = 0;
    }

    @Override
    public Object get(int index) {
        return array[index];            //здесь может быть Exception
    }

    @Override
    public int indexOf(Object elem) {
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
    }

    @Override
    public boolean contains(Object elem) {
        return indexOf(elem) != -1;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }


    private class MyIterator implements Iterator {

        private int iteratorCount = 0;

        @Override
        public boolean hasNext() {
            return count > iteratorCount;
        }

        @Override
        public Object next() {
            return array[iteratorCount++];          //здесь может быть Exception
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--iteratorCount);           //здесь может быть ArrayIndexOutOfBoundsException
            iteratorCount++;
        }


    }


    public static void main(String[] args) {
        MyArrayList obj = new MyArrayList();

        obj.add("f0");
        obj.printAll();
        System.out.println("count = " + obj.count);
        obj.add("f1");
        obj.printAll();
        System.out.println("count = " + obj.count);
        obj.add("f2");
        obj.printAll();
        System.out.println("count = " + obj.count);
        obj.add("f3");
        obj.printAll();
        System.out.println("count = " + obj.count);
        obj.add("f4");
        obj.printAll();
        System.out.println("count = " + obj.count);
        obj.add("f5");
        obj.printAll();
        System.out.println("count = " + obj.count);
        obj.add("f6");
        obj.printAll();
        System.out.println("count = " + obj.count);
        obj.add("f7");
        obj.printAll();
        System.out.println("count = " + obj.count);
        obj.add("f8");
        obj.printAll();
        System.out.println("count = " + obj.count);
        obj.add("f9");
        obj.printAll();
        System.out.println("count = " + obj.count);
        obj.add("f10");
        obj.printAll();
        System.out.println("count = " + obj.count);

        System.out.println("add11");
        obj.add(11, "f11");
        obj.printAll();

        System.out.println("add8");
        obj.add(8, "s8");
        obj.printAll();
        System.out.println("count = " + obj.count);

        System.out.println("set4");
        System.out.println(obj.set(4, "s4"));
        obj.printAll();
        System.out.println("count = " + obj.count);

        System.out.println("set19");
        obj.set(19, "s19");
        obj.printAll();
        System.out.println("count = " + obj.count);

        System.out.println("set20");
        obj.set(20, "s20");
        obj.printAll();
        System.out.println("count = " + obj.count);

        obj.add(21, "f21");
        obj.printAll();
        System.out.println("count = " + obj.count);

        System.out.println("remove19");
        obj.remove(19);
        obj.printAll();
        System.out.println("count = " + obj.count);

        System.out.println("remove18");
        obj.remove(18);
        obj.printAll();
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


        /*
        System.out.println(obj.contains("f2"));

        obj.clear();
        System.out.println(obj.get(0));
        System.out.println("count = " + obj.count);
        */


        Iterator iterator = obj.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        iterator.remove();
        obj.printAll();
    }
}
