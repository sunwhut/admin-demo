package algorithm;

public class QuickSort {
    public void quickSort(int[] num, int start, int end){
        if (start >= end){
            return;
        }
        int i = start, j = end;
        int x = num[start];
        while (i < j){
            while (i < j && num[j] >= x){
                j--;
            }
            if (i < j){
                num[i] = num[j];
                i++;
            }
            while (i < j && num[i] < x){
                i++;
            }
            if (i < j){
                num[j] = num[i];
                j--;
            }
        }
        num[i] = x;
        quickSort(num, start, i - 1);
        quickSort(num, i + 1, end);
    }

    public void getLeastKNumbers(int[] num, int k){
        if (num == null || num.length == 0 || k <= 0 || k > num.length){
            return;
        }
        int index = partition(num, 0, num.length - 1);
        while (index != k - 1){
            if (index > k - 1){
                index = partition(num, 0, index - 1);
            } else {
                index = partition(num, index + 1, num.length - 1);
            }
        }
    }

    public int partition(int[] num, int start, int end){
        int i = start, j = end;
        int x = num[start];
        while (i < j){
            while (i < j && num[j] >= x){
                j--;
            }
            if (i < j){
                num[i] = num[j];
                i++;
            }
            while (i < j && num[i] < x){
                i++;
            }
            if (i < j){
                num[j] = num[i];
                j--;
            }
        }
        num[i] = x;
        return i;
    }

    public static void main(String[] args) {
        int[] num = {5, 1, 7, 3, 4, 6, 9};
//        new QuickSort().quickSort(num, 0 ,num.length - 1);
        new QuickSort().getLeastKNumbers(num, 6);
        for (int i = 0; i < num.length; i++) {
            System.out.print(num[i] + ",");
        }
    }

}
