package ink.shulei;

import java.util.Arrays;

public class ThreadBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr  = {4,6,8,5,9};
        heapSOrt(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void heapSOrt(int[] arr){
        int temp;
//        int[] arr  = {4,6,8,5,9};
//        System.out.println("堆排序");
//        adjustHeap(arr,1, arr.length);
//        adjustHeap(arr,0, arr.length);
//        System.out.println("diyici"+Arrays.toString(arr));
        for (int i = arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i, arr.length);
        }

        for (int j = arr.length-1;j>0;j--){
            temp = arr[j];

            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);
        }
    }



    // 讲一个数组（二叉树），调整成一个大顶堆
    public static void adjustHeap(int[] arr,int i,int length){
        int temp = arr[i];
        for (int k = 2*i+1; k < length;k = k*2+1){
            if (k+1<length && arr[k] < arr[k+1]){
                k++;
            }
            if (arr[k] > temp){
                arr[i] = arr[k];
                i = k;
            }else {
                break;
            }
        }
        arr[i] = temp;
    }
}


