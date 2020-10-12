import java.util.*;
import java.io.*;
class Program
{
    static class MinHeap{
        private int CAPACITY = 10;
        private int size = 0;
        int[] arr;
        public MinHeap()
        {
            arr = new int[CAPACITY];
        }
        private int getLeftChildIndex(int parentIndex){ return 2*parentIndex+1; }
        private int getRightChildIndex(int parentIndex){ return 2*parentIndex+2; }
        private int getParentIndex(int childIndex){ return (childIndex-1)/2; }

        private boolean hasLeftChild(int index) { return getLeftChildIndex(index)<size; }
        private boolean hasRightChild(int index) { return getRightChildIndex(index)<size; }
        private boolean hasParent(int index) { return getParentIndex(index)>=0; }

        private int leftChild(int index) { return arr[getLeftChildIndex(index)]; }
        private int rightChild(int index) { return arr[getRightChildIndex(index)]; }
        private int parent(int index) { return arr[getParentIndex(index)]; }

        public void swap(int x,int y)
        {
            int temp = arr[x];
            arr[x] = arr[y];
            arr[y] = temp;
        }
        public void ensureExtraCapacity()
        {
            if(size==CAPACITY)
            {
                arr= Arrays.copyOf(arr, CAPACITY*2);
                CAPACITY*=2;
            }
        }
        public int peek()
        {
            if(size==0)
                throw new IllegalStateException();
            return arr[0];
        }
        public int poll()
        {
            if(size==0)
                throw new IllegalStateException();
            int item = arr[0];
            arr[0] = arr[size-1];
            size--;
            heapifyDown();
            return item;
        }
        public void add(int x)
        {
            ensureExtraCapacity();
            arr[size] = x; 
            size++;
            heapifyUp();
        }
        public void heapifyDown()
        {
            int index = 0;
            while(hasLeftChild(index))
            {
                int smallIdx = getLeftChildIndex(index);
                if(hasRightChild(index) && arr[smallIdx]>rightChild(index))
                {
                    smallIdx = getRightChildIndex(index); 
                }
                if(arr[index]<arr[smallIdx])
                    break;
                else{
                    swap(index,smallIdx);
                }
                index = smallIdx;
            }
        }
        public void heapifyUp()
        {
            int index = size-1;
            while(hasParent(index) && parent(index)>arr[index])
            {
                swap(getParentIndex(index),index);
                index = getParentIndex(index); 
            }
        }
    }
    static class MaxHeap{
        private int CAPACITY = 10;
        private int size = 0;
        int[] arr;
        public MaxHeap()
        {
            arr = new int[CAPACITY];
        }
        private int getLeftChildIndex(int parentIndex){ return 2*parentIndex+1; }
        private int getRightChildIndex(int parentIndex){ return 2*parentIndex+2; }
        private int getParentIndex(int childIndex){ return (childIndex-1)/2; }

        private boolean hasLeftChild(int index) { return getLeftChildIndex(index)<size; }
        private boolean hasRightChild(int index) { return getRightChildIndex(index)<size; }
        private boolean hasParent(int index) { return getParentIndex(index)>=0; }

        private int leftChild(int index) { return arr[getLeftChildIndex(index)]; }
        private int rightChild(int index) { return arr[getRightChildIndex(index)]; }
        private int parent(int index) { return arr[getParentIndex(index)]; }

        public void swap(int x,int y)
        {
            int temp = arr[x];
            arr[x] = arr[y];
            arr[y] = temp;
        }
        public void ensureExtraCapacity()
        {
            if(size==CAPACITY)
            {
                arr= Arrays.copyOf(arr, CAPACITY*2);
                CAPACITY*=2;
            }
        }
        public int peek()
        {
            if(size==0)
                throw new IllegalStateException();
            return arr[0];
        }
        public int poll()
        {
            if(size==0)
                throw new IllegalStateException();
            int item = arr[0];
            arr[0] = arr[size-1];
            size--;
            heapifyDown();
            return item;
        }
        public void add(int x)
        {
            ensureExtraCapacity();
            arr[size] = x; 
            size++;
            heapifyUp();
        }
        public void heapifyDown()
        {
            int index = 0;
            while(hasLeftChild(index))
            {
                int smallIdx = getLeftChildIndex(index);
                if(hasRightChild(index) && arr[smallIdx]<getRightChildIndex(index))
                {
                    smallIdx = getRightChildIndex(index); 
                }
                if(arr[index]>arr[smallIdx])
                    break;
                else{
                    swap(index,smallIdx);
                }
                index = smallIdx;
            }
        }
        public void heapifyUp()
        {
            int index = size-1;
            while(hasParent(index) && parent(index)<arr[index])
            {
                swap(getParentIndex(index),index);
                index = getParentIndex(index); 
            }
        }
    }
    public static void main(String[] args) {
        MinHeap mh = new MinHeap();
        mh.add(4123);
        System.out.println(mh.peek());
        mh.add(33);
        System.out.println(mh.peek());
        mh.add(-12);
        System.out.println(mh.peek());
        mh.poll();
        System.out.println(mh.peek());
        mh.poll();
        System.out.println(mh.peek());
    }
}