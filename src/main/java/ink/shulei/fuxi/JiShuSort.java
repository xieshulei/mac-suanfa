package ink.shulei.fuxi;

import java.util.Arrays;

public class JiShuSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        jiShuSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void jiShuSort(int[] arr){
        int maxArr = arr[0];
        int a = 1;
        while (a<arr.length){
            if (arr[a] > maxArr){
                maxArr = arr[a];
            }
            a++;
        }
        maxArr = (maxArr+"").toString().length();
        int[] everyTongNum = new int[10];
        int[][] tong = new int[10][arr.length];
        for (int j = 0,n=1;j<maxArr;j++,n*=10){
            for (int i = 0;i<arr.length;i++){
                int tempWeiDigit = arr[i]/n%10;
                tong[tempWeiDigit][everyTongNum[tempWeiDigit]] = arr[i];
                everyTongNum[tempWeiDigit]++;
            }
            int temp = 0;
            for (int c = 0;c< 10;c++){
                if (everyTongNum[c] != 0){
                    for (int z = 0;z<everyTongNum[c];z++){
                        arr[temp] = tong[c][z];
                        temp++;
                    }
                }
                everyTongNum[c] = 0;
            }

        }
        for (int i = 0;i<10;i++){
            for (int j = 0;j<arr.length;j++){
                tong[i][j] = 0;
            }
        }

    }
}
