package ink.shulei.fuxi;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {

        int[] arr = {4,2,7,1,5,3,8,64,3,0};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void shellSort(int[] arr){

        for (int gap = arr.length/2;gap > 0;gap/=2){
            for (int i = gap;i<arr.length;i++){
                for (int j = i-gap;j>=0;j-=gap){
                    if (arr[j+gap] < arr[j]){
                        int temp = arr[j+gap];
                        arr[j+gap] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }

    }
}
