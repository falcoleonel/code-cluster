package ordenamientos;

public class MergeSort {
    
    public static void ordenar(int[] list) {
        mergeSortAux(list, 0, list.length / 2, (list.length + 1) / 2);
    }
    
    public static void mergeSortAux(int[] list, int offset, int left, int right) {

        if(left == 0) return;
        mergeSortAux(list, offset, left / 2, (left + 1) / 2);
        mergeSortAux(list, offset + left, right / 2, (right + 1) / 2);
        merge(list, offset, left, right);
    }

    public static void merge(int[] list, int offset, int left, int right) {
        
        int[] leftPart = new int[left];
        int[] rightPart = new int[right];
        for(int i = 0, j = offset; i < left; i++, j++) leftPart[i] = list[j];
        for(int i = 0, j = offset + left; i < right; i++, j++) rightPart[i] = list[j];

        int l = 0, r = 0, i = offset;
        while(l < left && r < right) {
            if(leftPart[l] > rightPart[r]) list[i++] = rightPart[r++];
            else list[i++] = leftPart[l++];
        }
        while(l < left) list[i++] = leftPart[l++];
        while(r < right) list[i++] = rightPart[r++];
    }
}
