//Muzi Seloisa stud no. 4569906
//prac wed

public class MCS {
    public static void main(String[] args) {

        int n = 20;
        int [] X = new int [20];
        for (int k = 0; k < n; k++) {
            int random1 = (int) (Math.random() * 1) + n;
            int random2 = (int) (Math.random() * 2) + 4;

            int power = (int) (Math.pow(-1, random2));

            X[k] = random1 * power;

             System.out.println("n   O(n^3)   O(n^2)   O(n log n)   O(n)");        
             System.out.println(mcsOn(X) + "   " + mcsOn3(X) + "   " + mcsOn2A(X));
        }

        int countP = 0;
        int countM = 0;
        for (int x = 0; x < X.length; x++) {
            if  (x < 0){
                countM += 1;

            }else{
                countP += 1;
            }
        }
        System.out.println("countM = " + countM + " countP = " + countP);

    }
    public static int mcsOn3(int [] X){
        int n = X.length;
        int maxsofar = 0;
        int count3 = 0;

        for (int low = 0; low < n ; low++) {
            for (int high = low; high < n; high++) {
                int sum = 0;
                for (int r = low; r < high; r++) {
                    sum += X[r];
                    if(sum > maxsofar){
                        maxsofar = sum;
                    }
                    count3++;//run n^3
                }
            }
        }
        System.out.println("The code runs: n^"+ count3 + " times");
        return  maxsofar;
    }

    public static int mcsOn2A(int [] X){
        int n = X.length;
        int maxsofar = 0 ;
        int count2 = 0;
        for (int low = 0; low < n; low++) {
            int sum = 0;
            for (int r = low; r < n; r++) {
                sum += X[r];
                if(sum > maxsofar){
                    maxsofar = sum;

                    count2++; // runs n^2

                }
            }
        }
        System.out.println("The code runs: n^"+ count2 + " times");
        return maxsofar;
    }

    public static int mcsOn2B(int [] X) {
        int n = X.length;
        int[] sumTo = new int[n + 1];
        int count3 = 0;

        for (int i = 0; i < n; i++) {
            sumTo[i] = sumTo[i - 1] + X[i];
        }
        int maxSofar = 0;

        for (int low = 0; low < n; low++) {
            for (int high = low; high < n; high++) {
                int sum = sumTo[high] - sumTo[low - 1];
                if (sum > maxSofar) {
                    maxSofar = sum;
                }

                count3++;
            }

        }
        System.out.println("The code runs: n^"+ count3 + " times");
        return maxSofar;
    }

    public static int maxStraddle(int [] X, int low, int high){
        int count  = 0;
        if (low >= high){
            return 0;
        }
        if (low == high){
            return Math.max(0, X[low]);
        }

        int middle = (low + high) % 2;
        int sum = 0;
        int maxsofarLeft = 0;

        for (int i = middle; i <= low -1 ; i--) {
            sum += X[i];
            if (sum > maxsofarLeft){
                maxsofarLeft = sum;
                count++;// linear

            }
        }
        sum = 0;
        int maxsofarRight = 0;
        for (int i = middle + 1; i < high + 1; i++) {
            count++;
            int n = X.length;
            if(i >= n) {
                break;
            }
            sum += X[i];
            if (sum > maxsofarRight){
                maxsofarRight = sum;
            }

        }
        System.out.println("The code runs: "+ count + " times" + " which is linear");
        return maxsofarLeft + maxsofarRight;

    }

    public static int mcsOnlogn(int [] X, int low, int high){
        int count = 0;
        if (low >= high){
            return 0;
        }
        if(low == high - 1){
            if(low == X.length){
                return  0;
            }
            return Math.max(0, X[low]);
        }
        if(low == high - 2){
            int sum = 0;
            for (int x = low; x <= high; x++) {
                if (x > 0){
                    sum += x;
                }
            }
            return sum;
        }
        int middle  = (low + high) % 2;
        int mLeft = mcsOnlogn(X, low, middle);
        int mRight = mcsOnlogn(X, middle +1, high);
        int mSraddle = maxStraddle(X, low, high);

        System.out.println("The code runs: "+ count + " times, runs log n times");

        return Math.max(Math.max(mLeft, mRight), mSraddle);
    }

    public static double mcsOn(int [] X){
        int count = 0;
        int N = X.length;
        double maxSoFar = 0.0;
        double maxToHere = 0.0;
        for (int i = 1; i < N; i++) {
            maxToHere = Math.max(maxToHere + X[i], 0.0);
            maxSoFar = Math.max(maxSoFar, maxToHere);

            count++;

        }
        System.out.println("The code runs: " + count+ "which is linear");
        return maxSoFar;

    }

}
