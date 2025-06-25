public class Curso {
    private Alumno[] curso;
    private int numeroCurso;

    public Curso(int numeroCurso) {
        this.numeroCurso = numeroCurso;
        this.curso = new Alumno[30];
    }

    public Alumno[] getCurso() {
        return curso;
    }

    public void setCurso(Alumno[] curso) {
        this.curso = curso;
    }

    public int getNumeroCurso() {
        return numeroCurso;
    }

    public void setNumeroCurso(int numeroCurso) {
        this.numeroCurso = numeroCurso;
    }

    public boolean cargarAlumno(Alumno alumno) {
        boolean cargado = false;
        int contador = 0;

        while (!cargado && contador < curso.length) {
            if (curso[contador] == null) {
                curso[contador] = alumno;
                cargado = true;
            }
            contador++;
        }

        return cargado;
    }

    public void agruparCurso() {
        Alumno[] nuevoCurso = new Alumno[curso.length];
        int index = 0;

        for (int i = 0; i < curso.length; i++) {
            if (curso[i] != null) {
                nuevoCurso[index++] = curso[i];
            }
        }

        this.setCurso(nuevoCurso);
    }

    public int contarAlumnos() {
        int contador = 0;

        while (contador < curso.length && curso[contador] != null) {
            contador++;
        }

        return contador;
    }

    public double calcularPromedio() {
        int totalAlumnos = contarAlumnos();
        double suma = 0.0;

        if (totalAlumnos > 0) {
            suma = sumarPromedios(0);
        }

        return suma / totalAlumnos;
    }

    private double sumarPromedios(int pos) {
        Alumno[] curso = this.getCurso();
        double suma = 0.0;

        if (pos < curso.length && curso[pos] != null) {
            suma = curso[pos].getPromedio() + sumarPromedios(pos + 1);
        }

        return suma;
    }

    public Alumno[] ordenarPorApellido() {
        int cantidad = contarAlumnos();
        Alumno[] ordenado = new Alumno[cantidad];
        int index = 0;

        while (index < cantidad) {
            ordenado[index] = curso[index];
            index++;
        }

        Sorting.bubbleSortMejorado(ordenado, "apellidoNombre");
        return ordenado;
    }

    @Override
    public String toString() {
        int contador = 0;
        String resultado = "Curso " + this.getNumeroCurso() + ":\n";
        if (contarAlumnos() == 0) {
            return resultado + "No hay alumnos en este curso.\n";
        } else {
            while (contador < curso.length && curso[contador] != null) {
                resultado += curso[contador].toString() + "\n";
                contador++;
            }
        }
        return resultado;
    }
}
