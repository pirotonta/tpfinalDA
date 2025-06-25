public class Alumno {
    private String apellido;
    private String nombre;
    private int legajo;
    private int curso;
    private double promedio;

    public Alumno(String apellido, String nombre, int legajo, int curso, double promedio) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.legajo = legajo;
        this.curso = curso;
        this.promedio = promedio;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        String resultado = "Alumno: " + apellido + ", " + nombre + "\n";
        resultado += "Legajo: " + legajo + "\n";
        resultado += "Curso: " + curso + "\n";
        resultado += "Promedio: " + promedio + "\n";
        return resultado;
    }
}
