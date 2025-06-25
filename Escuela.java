public class Escuela {
    private Curso[] cursos;
    private int[] desaprobados;
    private Egresados egresados;

    public Escuela() {
        this.cursos = new Curso[7];
        for (int i = 0; i < cursos.length; i++) {
            cursos[i] = new Curso(i + 1);
        }
        this.egresados = new Egresados();
    }

    public Curso[] getCursos() {
        return cursos;
    }

    public void setCursos(Curso[] cursos) {
        this.cursos = cursos;
    }

    public Egresados getEgresados() {
        return egresados;
    }

    public void setEgresados(Egresados egresados) {
        this.egresados = egresados;
    }

    public int[] getDesaprobados() {
        return desaprobados;
    }

    public void setDesaprobados(int[] desaprobados) {
        this.desaprobados = desaprobados;
    }

    public boolean hayAlumnosCargados() {
        boolean hayAlumnos = false;
        int contador = 0;

        while (contador < cursos.length && !hayAlumnos) {
            if (cursos[contador].contarAlumnos() > 0) {
                hayAlumnos = true;
            }
            contador++;
        }
        
        return hayAlumnos;
    }

    public void cargarAlumnos(String alumnosTexto) {
        String[] lineas = alumnosTexto.split("\n");

        for (String linea : lineas) {
            String[] datos = linea.split(";");
            if (datos.length == 5) {
                try {
                    String apellido = datos[0].trim();
                    String nombre = datos[1].trim();
                    int legajo = Integer.parseInt(datos[2].trim());
                    int numeroCurso = Integer.parseInt(datos[3].trim());
                    double promedio = Double.parseDouble(datos[4].trim());
                    Alumno alumno = new Alumno(apellido, nombre, legajo, numeroCurso, promedio);
                    boolean cargado = cursos[numeroCurso - 1].cargarAlumno(alumno);
                    // se puede evitar llamar a cargarAlumno constantemente by keeping track of # of students as an attribute
                    if (!cargado) {
                        System.err.println("No se pudo cargar el alumno: " + apellido + ", " + nombre);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error al procesar la línea: " + linea + " - " + e.getMessage());
                }
            } else {
                System.err.println("Formato incorrecto en la línea: " + linea);
            }
        }
    }

    public void cargarDesaprobados(String desaprobadosTexto) {
        String[] lineas = desaprobadosTexto.split("\n");
        int[] desaprobados = new int[lineas.length];

        for (int i = 0; i < lineas.length; i++) {
            try {
                desaprobados[i] = Integer.parseInt(lineas[i].trim());
            } catch (NumberFormatException e) {
                System.err.println("Error en la línea " + (i + 1) + ": " + e.getMessage());
            }
        }

        this.setDesaprobados(desaprobados);
    }

    public boolean esDesaprobado(int legajo) {
        boolean esDesaprobado = false;
        int contador = 0;

        while (!esDesaprobado && contador < this.desaprobados.length) {
            if (this.desaprobados[contador] == legajo) {
                esDesaprobado = true;
            }
            contador++;
        }
        return esDesaprobado;
    }

    public void pasarDeGrado() {
        for (int i = cursos.length - 1; i >= 0; i--) {
            Alumno[] alumnos = cursos[i].getCurso();
            int contador = 0;

            while (contador < alumnos.length && alumnos[contador] != null) {
                Alumno alumno = alumnos[contador];
                if (!esDesaprobado(alumno.getLegajo())) {
                    if (i == cursos.length - 1) {
                        egresados.cargarEgresado(alumno);
                    } else {
                        cursos[i + 1].cargarAlumno(alumno);
                    }
                    alumnos[contador] = null;
                }
                contador++;
            }

            cursos[i].agruparCurso();
        }
    }

    public double[] promediosPorCurso() {
        double[] promedios = new double[cursos.length];

        for (int i = 0; i < cursos.length; i++) {
            promedios[i] = cursos[i].calcularPromedio();
        }

        return promedios;
    }

    public int cantidadVacantes(int pos) {
        int cantidad;
        if (pos < cursos.length) {
            int vacantesCurso = 30 - cursos[pos].contarAlumnos();
            System.out.println("Curso " + (pos + 1) + " tiene " + vacantesCurso + " vacantes.");
            cantidad = vacantesCurso + cantidadVacantes(pos + 1);
        } else
            cantidad = 0;
        return cantidad;
    }

    public int[] buscarAlumno(int curso, int legajo) {
        Alumno[] cursoAlumno = cursos[curso - 1].getCurso();
        boolean encontrado = false;
        int contador = 0;
        int[] resultado = { curso - 1, -1 };

        while (!encontrado && contador < cursoAlumno.length) {
            if (cursoAlumno[contador] != null && cursoAlumno[contador].getLegajo() == legajo) {
                encontrado = true;
                resultado[1] = contador;
            }
            contador++;
        }

        return resultado;
    }

    @Override
    public String toString() {
        String resultado = "////// ESTRUCTURA ESCUELA ///////\n";

        for (Curso curso : cursos) {
            resultado += curso.toString() + "\n";
        }

        resultado += "////// EGRESADOS ///////\n";
        Alumno[] egresadosArray = egresados.getEgresados();
        for (Alumno alumno : egresadosArray) {
            if (alumno != null) {
                resultado += alumno.toString() + "\n";
            }
        }
        resultado += "////// FIN ESTRUCTURA ESCUELA ///////\n";
        return resultado;
    }
}
