package homework;

public class Money {
	static int[] list = {50, 20, 10, 5, 1};
	static int[] num = {1, 1, 1, 2, 3};
	/**
	 * If the coins or bills are enough, return true; if not, return false
	 * @param x
	 * @return
	 */
	public static boolean isEnough(int x) {
		// Greedy algorithm
		// Use the largest value first
		if(x >= 0 && x <= 93 ) {
			for (int i = 0; i < 5; i++) {
				int temp = Math.min(x / list[i], num[i]);
				x -= temp * list[i];
				num[i] -= temp;
			}
			if(x == 0) {
				return true;
			}
		} 
		return false;
	}
	public static void main(String []args) {
        System.out.println(isEnough(3));
    }
}
