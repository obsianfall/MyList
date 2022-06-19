import java.util.Arrays;
import java.util.ListIterator;
import java.util.NoSuchElementException;

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
        if (count == this.array.length) {
            expand(count);
        }
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
            temp = null;            //in order to avoid Exception

        } else {
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
        Object elem;
        try {
            elem = this.array[index];        //in order to avoid Exception
            shiftLeft(index);
            count--;


        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            throw new IndexOutOfBoundsException(e.getMessage());
        }

        return elem;
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
        Object elem;
        try {
            elem = array[index];
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            throw new IndexOutOfBoundsException(e.getMessage());
        }
        return elem;
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
    public ListIterator iterator() {
        return new MyIterator();
    }


    private class MyIterator implements ListIterator {

        private int nextCount = 0;
        private int lastReturnedIndex = -1;
        private int previousCount = -1;

        @Override
        public boolean hasNext() {
            return count > nextCount;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                Object elem = array[nextCount++];
                lastReturnedIndex = nextCount - 1;
                previousCount = nextCount - 1;
                return elem;
            }

            else {
                throw new NoSuchElementException();
            }

        }

        @Override
        public boolean hasPrevious() {
            return previousCount >= 0;
        }

        @Override
        public Object previous() {
            if (hasPrevious()) {
                Object elem = array[previousCount--];
                lastReturnedIndex = previousCount + 1;
                nextCount = previousCount + 1;
                return elem;
            }

            else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public int nextIndex() {
            if (hasNext()) {
                return nextCount;
            }

            else {
                return count; //list size, according to the condition
            }
        }

        @Override
        public int previousIndex() {
            if (hasPrevious()) {
                return previousCount;
            }

            else {
                return -1;
            }
        }

        @Override
        public void remove() {
            if (lastReturnedIndex == -1) {
                throw new IllegalStateException();
            }
            else {
                MyArrayList.this.remove(lastReturnedIndex);
                lastReturnedIndex = -1;
            }

        }

        @Override
        public void set(Object o) {
            if (lastReturnedIndex == -1) {
                throw new IllegalStateException();
            }
            else {
                MyArrayList.this.set(lastReturnedIndex, o);
            }

        }

        @Override
        public void add(Object o) {
            MyArrayList.this.add(nextCount++, o);
            previousCount = nextCount - 1;
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
        System.out.println("add 8s8");
        obj.add(8, "8s8");
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

        obj.printAll();


        int x;
        x = obj.indexOf("f6");
        System.out.println("indexOf 'f6' output: " + x);
        System.out.println(obj.contains("f23"));

        System.out.println("indexOf 'f100500' output: " + obj.indexOf("f100500"));
        System.out.println("indexOf 'null' output: " + obj.indexOf(null));

//        obj.remove("f7");
//        obj.remove("f13");
//        System.out.println("count = " + obj.count);

        System.out.println(obj.remove(7));
        System.out.println(obj.remove(12));
        System.out.println("count = " + obj.count);

        obj.printAll();


        /*
        System.out.println(obj.contains("f2"));

        obj.clear();
        System.out.println(obj.get(0));
        System.out.println("count = " + obj.count);
        */

        System.out.println("ListIterator starts here");

        ListIterator iterator = obj.iterator();
        System.out.println("hasNext: " + iterator.hasNext());
        System.out.println("nextIndex: " + iterator.nextIndex());
        System.out.println("next: " + iterator.next());
        System.out.println("hasPrevious: " + iterator.hasPrevious());
        System.out.println("previousIndex: " + iterator.previousIndex());
        System.out.println("nextIndex: " + iterator.nextIndex());
        System.out.println("previous: " + iterator.previous());
        System.out.println("next: " + iterator.next());
        System.out.println("next: " + iterator.next());
        System.out.println("next: " + iterator.next());
        System.out.println("previousIndex: " + iterator.previousIndex());
        System.out.println("nextIndex: " + iterator.nextIndex());
        iterator.set("F1");
        System.out.println("set 'F1' worked out here");
        System.out.println("previous: " + iterator.previous());
        System.out.println("next: " + iterator.next());
        iterator.add("f1.5");
        System.out.println("add 'f1.5' worked out here");
        obj.printAll();
        System.out.println("previous: " + iterator.previous());
        System.out.println("next: " + iterator.next());
        System.out.println("next: " + iterator.next());
        System.out.println("nextIndex: " + iterator.nextIndex());

        while (iterator.hasPrevious()) {
            iterator.previous();
        }

        System.out.println("While loop worked out here; returned to the top of the list");

        System.out.println("nextIndex: " + iterator.nextIndex());
        System.out.println("previousIndex: " + iterator.previousIndex());


        obj.printAll();

        for (int i = 0; iterator.hasNext(); i++) {
            if (i != 0 & i % 4 == 0) {
                iterator.remove();
                iterator.previous();
            }

            iterator.next();
        }

        System.out.println("For loop worked out here; deleted each fourth element of the list");

        obj.printAll();
//
//        iterator.next();
//
//        System.out.println(obj.remove(93));
    }
}
