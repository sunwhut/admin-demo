package algorithm;

public class HouseRobber {
    public int[] result;

    public int rob1(int[] arr){
        result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = -1;
        }
        return solve(arr, arr.length - 1);
    }

    public int solve(int[] arr, int index){
        if (index < 0){
            return 0;
        }
        if (result[index] > 0){
            return result[index];
        }
        result[index] = Math.max(solve(arr, index - 1), solve(arr, index - 2) + arr[index]);
        return result[index];
    }

    public int rob2(int[] arr){
        result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = -1;
        }
        int result1 = solve1(arr, arr.length - 2);
        for (int i = 0; i < arr.length; i++) {
            result[i] = -1;
        }
        result[0] = 0;
        int result2 = solve2(arr, arr.length - 1);
        return result1 >= result2 ? result1 : result2;
    }

    public int solve1(int[] arr, int index){
        if (index < 0){
            return 0;
        }
        if (result[index] > 0){
            return result[index];
        }
        result[index] = Math.max(solve1(arr, index - 1), solve1(arr, index - 2) + arr[index]);
        return result[index];
    }

    public int solve2(int[] arr, int index){
        if (index < 0){
            return 0;
        }
        if (result[index] >= 0){
            return result[index];
        }
        result[index] = Math.max(solve2(arr, index -1), solve2(arr, index - 2) + arr[index]);
        return result[index];
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 9, 3, 1};
        HouseRobber houseRobber = new HouseRobber();
//        int result = houseRobber.rob1(arr);
        int result = houseRobber.rob2(arr);
        System.out.println(result);
    }
}
