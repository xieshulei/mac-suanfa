package ink.shulei.fuxi;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {2,5,32,13,8,45,11};
        quickSort(arr,0,6);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr,int left,int right){
        int l = left;
        int r = right;
        int mid = arr[(l+r)/2];
        while (l<r){
            while (arr[l] < mid){
                l++;
            }
            while (arr[r] > mid){
                r--;
            }
            if (l>=r){
                break;
            }
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
//            if (arr[l] == arr[mid]){
//                r-=1;
//            }
//            if (arr[r] == arr[mid]){
//                l+=1;
//            }
        }

        if (l == r){
            l+=1;
            r-=1;
        }

        if (left < r){
            quickSort(arr,left,r);
        }
        if (right > l){
            quickSort(arr,l,right);
        }

    }
}
