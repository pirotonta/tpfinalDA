public class Egresados {
    private Alumno[] egresados;

    public Egresados() {
        this.egresados = new Alumno[30];
    }

    public Alumno[] getEgresados() {
        return egresados;
    }

    public void setEgresados(Alumno[] egresados) {
        this.egresados = egresados;
    }

    public boolean cargarEgresado(Alumno alumno) {
        boolean cargado = false;
        int contador = 0;

        while (!cargado && contador < egresados.length) {
            if (egresados[contador] == null) {
                egresados[contador] = alumno;
                cargado = true;
            }
            contador++;
        }

        return cargado;
    }

    public int cantidadEgresados() {
        int contador = 0;
        
        while (contador < egresados.length && egresados[contador] != null) {
            contador++;
        }
        
        return contador;
    }

    public Alumno[] ordenarPorPromedio() {
        int cantidad = cantidadEgresados();
        Alumno[] ordenado = new Alumno[cantidad];
        int index = 0;
        
        while (index < cantidad){
            ordenado[index] = egresados[index];
            index++;
        }

        Sorting.bubbleSort(ordenado, "promedio");
        return ordenado;
    }

    public Alumno[] ordenarPorPromedioAdv() {
        int cantidad = cantidadEgresados();
        Alumno[] ordenado = new Alumno[cantidad];
        int index = 0;
        
        while (index < cantidad){
            ordenado[index] = egresados[index];
            index++;
        }

        Sorting.mergeSort(ordenado, 0, ordenado.length - 1, "promedio");
        return ordenado;
    }

    public double[] compararTiemposOrdenamiento() {
        int cantidad = cantidadEgresados();
        double[] tiempos = {0.0, 0.0};

        if (cantidad > 0) {

            Alumno[] copia1 = new Alumno[cantidad];
            Alumno[] copia2 = new Alumno[cantidad];
            int index = 0;

            while(index < cantidad) {
                copia1[index] = egresados[index];
                copia2[index] = egresados[index];
                index++;
            }

            long inicioBubble = System.nanoTime();
            Sorting.bubbleSort(copia1, "promedio");
            long finBubble = System.nanoTime();

            long inicioMerge = System.nanoTime();
            Sorting.mergeSort(copia2, 0, copia2.length - 1, "promedio");
            long finMerge = System.nanoTime();

            double tiempoBubble = (finBubble - inicioBubble) / 1_000_000.0; // Convertir a milisegundos
            double tiempoMerge = (finMerge - inicioMerge) / 1_000_000.0; // Convertir a milisegundos
            tiempos[0] = tiempoBubble;
            tiempos[1] = tiempoMerge;
        } 
        
        return tiempos;
    }

    @Override
    public String toString() {
        int contador = 0;
        String resultado = "////// EGRESADOS ///////\n";
        if (cantidadEgresados() == 0) {
            resultado += "No hay egresados.\n";
        } else {
            while (contador < egresados.length && egresados[contador] != null) {
                resultado += egresados[contador].toString() + "\n";
                contador++;
            }
        }
        resultado += "////// FIN EGRESADOS ///////\n";
        return resultado;
    }

}
