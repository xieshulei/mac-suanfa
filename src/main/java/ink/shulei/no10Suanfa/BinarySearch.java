package ink.shulei.no10Suanfa;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1,3,6,10,15,22,17};
        int left = 0;
        int right = arr.length - 1;
        int target = 1;
        int a = search(arr,left,right,target);
        System.out.println(a);

    }

    public static int search(int[] arr,int left,int right,int target){
        while (left < right){
            System.out.println("+++++++++++++++++++++++");
            int mid = (left + right)/2;
            if (arr[mid] == target){
                return mid;
            }else if (arr[mid] > target){
                right = mid - 1;
            }else if (arr[mid] < target){
                left = mid + 1;
            }
        }
        return -1;
    }
}
