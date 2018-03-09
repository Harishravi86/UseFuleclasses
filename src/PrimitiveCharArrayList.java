public class PrimitiveCharArrayList {

    private char[] array;

    private int currentIndex;

    private int arraySize;
    private int factor;
    private static final int DEFAULT_ARRAY_SIZE = 16;


    public PrimitiveCharArrayList() {
        this(new char[DEFAULT_ARRAY_SIZE]);
    }

    public PrimitiveCharArrayList(char[] array) {
        this(array, 2, -1);
    }

    public PrimitiveCharArrayList(int arraySize) {
        this(arraySize, 2);
    }

    public PrimitiveCharArrayList(int arraySize, int factor) {
        this(new char[arraySize], factor);
    }


    public PrimitiveCharArrayList(char[] array, int factor) {
        this(array, factor, -1);
    }

    public PrimitiveCharArrayList(char[] array, int factor, int currentIndex) {
        this.array = array;
        this.arraySize = array.length;
        this.currentIndex = currentIndex;
        this.factor = factor;
    }

    public int size() {
        return currentIndex + 1;
    }

    public boolean isEmpty() {
        return currentIndex == -1;
    }


    public void fastAdd(char element) throws ArrayIndexOutOfBoundsException {
        array[++currentIndex] = element;
    }

    public void add(char element) {
        addWithResizeFactor(element, this.factor);
    }

    public void addWithResizeFactor(char element, int factor) {
        if (currentIndex >= arraySize - 1) {
            multiplyArraySizeFactor(factor, this.arraySize);
        }
        array[++currentIndex] = element;
    }

    public void multiplyArraySizeFactor(int factor, int arraySize) {
        char[] newArray = new char[(arraySize) * factor];
        System.arraycopy(array, 0, newArray, 0, arraySize);
        this.array = newArray;
        this.arraySize = newArray.length;
    }


    public void removeLastElement() {
        multiplyArraySizeFactor(1, this.currentIndex);
        --this.currentIndex;
    }

    public void removeElementAtIndex(int index) {
        if (currentIndex == -1 || index < 0 || index > currentIndex) {
            throw new IllegalArgumentException();
        }
        this.arraySize = currentIndex;
        char[] resultArray = new char[arraySize];
        System.arraycopy(array, 0, resultArray, 0, index);
        System.arraycopy(array, index + 1, resultArray, index, arraySize - (index));
        this.array = resultArray;
        currentIndex--;
    }

    public void addElementAtIndex(int index, char element) {
        if (index < 0 || index > arraySize - 1) {
            throw new IllegalArgumentException();
        }
        char[] resultArray;
        this.arraySize = ++currentIndex;
        if (currentIndex == arraySize) {
            this.arraySize = arraySize * factor;
        }
        resultArray = new char[this.arraySize];
        resultArray[index] = element;
        System.arraycopy(array, 0, resultArray, 0, index);
        if (index < array.length) {
            System.arraycopy(array, index, resultArray, index + 1, array.length - (index));
        }
        this.array = resultArray;
    }


    public void clear() {
        this.array = new char[arraySize];
        this.currentIndex = 0;

    }

    public char fastGet(int index) {
        return array[index];
    }

    public char get(int index) {
        if (currentIndex == -1 || index < 0 || index > currentIndex) {
            throw new IllegalArgumentException();
        }
        return array[index];
    }

    public void fastSet(int index, char element) {
        array[index] = element;
    }

    public void set(int index, char element) {
        if (index < 0 || index > arraySize - 1) {
            throw new IllegalArgumentException();
        }
        array[index] = element;
        if (index > currentIndex) {
            this.currentIndex = index;
        }
    }

    public char[] getArrayCutToSize() {
        this.arraySize = currentIndex + 1;
        char[] resultArray = new char[arraySize];
        System.arraycopy(array, 0, resultArray, 0, arraySize);
        return resultArray;
    }

    public char[] getArray() {
        return array;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int getArraySize() {
        return arraySize;
    }

    public int getFactor() {
        return factor;
    }

    public boolean isFull() {
        return currentIndex == arraySize - 1;
    }
}
