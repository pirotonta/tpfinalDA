public class Sorting {

    private static boolean criterioIntercambio(Alumno a1, Alumno a2, String criterio) {
        boolean intercambio = false;
        switch (criterio) {
            case "promedio":
                intercambio = a1.getPromedio() < a2.getPromedio();
                break;
            case "apellido":
                intercambio = a1.getApellido().compareToIgnoreCase(a2.getApellido()) > 0;
                break;
            case "apellidoNombre":
                int cmp = a1.getApellido().compareToIgnoreCase(a2.getApellido());
                if (cmp == 0) {
                    intercambio = a1.getNombre().compareToIgnoreCase(a2.getNombre()) > 0;
                } else {
                    intercambio = cmp > 0;
                }
                break;
        }

        return intercambio;
    }

    public static void bubbleSort(Alumno[] arr, String criterio) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (criterioIntercambio(arr[j], arr[j + 1], criterio)) {
                    Alumno temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void bubbleSortMejorado(Alumno[] arr, String criterio) {
        boolean huboIntercambio = true;
        int n = arr.length;

        while (huboIntercambio) {
            huboIntercambio = false;
            for (int i = 0; i < n - 1; i++) {
                if (criterioIntercambio(arr[i], arr[i + 1], criterio)) {
                    Alumno temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    huboIntercambio = true;
                }
            }
            n--;
        }
    }

    public static void mergeSort(Alumno[] arr, int left, int right, String criterio) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, criterio);
            mergeSort(arr, mid + 1, right, criterio);
            merge(arr, left, mid, right, criterio);
        }
    }

    private static void merge(Alumno[] arr, int left, int mid, int right, String criterio) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Alumno[] L = new Alumno[n1];
        Alumno[] R = new Alumno[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (!criterioIntercambio(L[i], R[j], criterio)) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1)
            arr[k++] = L[i++];
        while (j < n2)
            arr[k++] = R[j++];
    }

}
