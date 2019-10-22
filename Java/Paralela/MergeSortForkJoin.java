import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MergeSortForkJoin {

    public void sort(int[] a) {
        int[] helper = new int[a.length];
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new MergeSortTask(a, helper, 0, a.length - 1));
    }

    private class MergeSortTask extends RecursiveAction {
        private static final long serialVersionUID = -749935388568367268L;
        private final int[] a;
        private final int[] helper;
        private final int lo;
        private final int hi;

        public MergeSortTask(int[] a, int[] helper, int lo, int hi) {
            this.a = a;
            this.helper = helper;
            this.lo = lo;
            this.hi = hi;
        }

        @Override
        protected void compute() {
            if (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                MergeSortTask left = new MergeSortTask(a, helper, lo, mid);
                MergeSortTask right = new MergeSortTask(a, helper, mid + 1, hi);
                invokeAll(left, right);
                merge(this.a, this.helper, this.lo, mid, this.hi);
            } else {
                return;
            }
        }

        private void merge(int[] a, int[] tmpArray, int leftPos, int rightPos, int rightEnd) {

            int leftEnd = rightPos - 1;
            int tmpPos = leftPos;
            int numElements = rightEnd - leftPos + 1;
            while (leftPos <= leftEnd && rightPos <= rightEnd) { // Main loop
                if (a[leftPos] < (a[rightPos])) {
                    tmpArray[tmpPos++] = a[leftPos++];
                } else {
                    tmpArray[tmpPos++] = a[rightPos++];
                }
            }
            while (leftPos <= leftEnd) { // Copy rest of first half
                tmpArray[tmpPos++] = a[leftPos++];
            }
            while (rightPos <= rightEnd) { // Copy rest of right half
                tmpArray[tmpPos++] = a[rightPos++];
            }
            for (int i = 0; i < numElements; i++, rightEnd--) { // Copy TmpArray back
                a[rightEnd] = tmpArray[rightEnd];
            }
        }
    }
}
