package algorithm;

public class BinarySearch {
    public int binarySearch1(int[] arr, int k){
        int low = 0;
        int high = arr.length - 1;
        while (low <= high){
            int mid = (low + high) / 2;
            if (k == arr[mid]){
                return mid;
            } else if (k < arr[mid]){
                high = mid - 1;
            } else{
                low = mid + 1;
            }
        }
        return -1;
    }

    public int binarySearch2(int[] arr, int low, int high, int k){
        int mid = (low + high) / 2;
        if (low > high){
            return -1;
        }
        if (k == arr[mid]){
            return mid;
        } else if (k < arr[mid]){
            return binarySearch2(arr, low, mid -1, k);
        } else{
            return binarySearch2(arr, mid + 1, high, k);
        }
    }

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int[] arr = {1, 2, 3, 5, 7, 8, 11};
//        System.out.println(binarySearch.binarySearch1(arr, 6));
        System.out.println(binarySearch.binarySearch2(arr, 0, arr.length - 1, 11));
    }
}
