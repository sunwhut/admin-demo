package algorithm;

public class GetNumberOfK {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 3, 3, 4, 5};
        GetNumberOfK obj = new GetNumberOfK();
        System.out.println(obj.getNumberOfK(arr, 3));
    }

    public int getFirstK(int[] arr, int k, int low, int high){
        if (low > high){
            return -1;
        }
        int mid = (low + high) / 2;
        if (k == arr[mid]){
            if ((mid > 0 && arr[mid - 1] != k) || mid == 0){
                return mid;
            } else {
                high = mid - 1;
            }
        } else if(k < arr[mid]){
            high = mid - 1;
        } else {
            low = mid + 1;
        }
        return getFirstK(arr, k, low, high);
    }

    public int getLastK(int[] arr, int k, int low, int high){
        if (low > high){
            return -1;
        }
        int mid = (low + high) / 2;
        if (k == arr[mid]){
            if ((mid < arr.length - 1 && arr[mid + 1] != k) || mid == arr.length - 1){
                return mid;
            } else {
                low = mid + 1;
            }
        } else if (k < arr[mid]){
            high = mid - 1;
        } else {
            low = mid + 1;
        }
        return getLastK(arr, k, low, high);
    }

    public int getNumberOfK(int[] arr, int k){
        int result = 0;

        if (arr != null && arr.length > 0){
            int first = getFirstK(arr, k, 0, arr.length - 1);
            int last = getLastK(arr, k, 0, arr.length - 1);
            if (first > -1 && last > -1){
                result = last - first + 1;
            }
        }
        return result;
    }
}
