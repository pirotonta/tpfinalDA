
public class testEscuela {
    static java.util.Scanner scanner = new java.util.Scanner(System.in);

    // muestro el menú de opciones
    public static void mostrarMenu() {
        System.out.println("--------- Seleccione una opción: ---------");
        System.out.println("1. Cargar alumnos");
        System.out.println("2. Pasar de grado");
        System.out.println("3. Ver promedios de los cursos");
        System.out.println("4. Ver un curso específico");
        System.out.println("5. Ver un curso específico ordenado por apellido y nombre");
        System.out.println("6. Ver alumnos egresados");
        System.out.println("7. Ver alumnos egresados ordenados por promedio");
        System.out.println("8. Ver cantidad de vacantes disponibles");
        System.out.println("9. Buscar alumno por curso y legajo");
        System.out.println("0. Salir");
        System.out.println("-------------------------------------------");
    }

    // leo una opción validando que esté dentro de un rango
    // inspirado en el método del wordix de la tecnicatura
    public static int leerOpcion(int min, int max) {
        int opcion;

        do {
            try {
                System.out.print("Ingrese un número entre " + min + " y " + max + ": ");
                opcion = Integer.parseInt(scanner.nextLine());

                if (opcion < min || opcion > max) {
                    System.out.println("Opción fuera de rango. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido.");
                opcion = -1; // Forzar la repetición
            }
        } while (opcion < min || opcion > max);

        return opcion;
    }

    // imprimo alumnos
    public static void mostrarAlumnos(Alumno[] alumnos) {
        int contador = 0;

        while (contador < alumnos.length && alumnos[contador] != null) {
            System.out.println(alumnos[contador]);
            contador++;
        }
    }

    public static void main(String[] args) {
        Escuela escuela = new Escuela();
        int opcion = -1;

        do {
            mostrarMenu();
            opcion = leerOpcion(0, 9);
            switch (opcion) {
                case 1:
                    System.out.println("Cargando alumnos...");
                    if (escuela.hayAlumnosCargados()) {
                        System.out.println("Ya hay alumnos cargados. Si desea recargar, reinicie el programa :)");
                    } else {
                        String alumnosTexto = ArchivosController.leerArchivo("ListaAlumnos.txt");
                        String desaprobadosTexto = ArchivosController.leerArchivo("ListaDesaprobados.txt");
                        escuela.cargarAlumnos(alumnosTexto);
                        escuela.cargarDesaprobados(desaprobadosTexto);
                        if (escuela.hayAlumnosCargados()) {
                            System.out.println(
                                    "Se han cargado los datos correspondientes a ListaAlumnos.txt y ListaDesaprobados.txt");

                        } else {
                            System.out.println("No se cargó adecuadamente. Verifique los archivos.");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Pasando de grado...");
                    if (escuela.hayAlumnosCargados()) {
                        escuela.pasarDeGrado();
                    } else {
                        System.out.println("No hay alumnos cargados.");
                    }
                    break;
                case 3:
                    System.out.println("Mostrando promedios de los cursos...");
                    if (!escuela.hayAlumnosCargados()) {
                        System.out.println("No hay alumnos cargados.");
                    } else {
                        double[] promedios = escuela.promediosPorCurso();
                        for (int i = 0; i < promedios.length; i++) {
                            System.out.printf("Curso %d: Promedio = %.2f\n", i + 1, promedios[i]);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Mostrando un curso específico...");
                    System.out.println("Ingrese el número del curso (1-7):");
                    int numeroCurso = leerOpcion(1, 7);
                    if (!escuela.hayAlumnosCargados()) {
                        System.out.println("No hay alumnos cargados.");
                    } else {
                        Curso curso = escuela.getCursos()[numeroCurso - 1];
                        System.out.println(curso);
                    }
                    break;
                case 5:
                    System.out.println("Mostrando un curso específico...");
                    System.out.println("Ingrese el número del curso (1-7):");
                    numeroCurso = leerOpcion(1, 7);
                    if (!escuela.hayAlumnosCargados()) {
                        System.out.println("No hay alumnos cargados.");
                    } else {
                        Curso curso = escuela.getCursos()[numeroCurso - 1];
                        Alumno[] cursoOrdenado = curso.ordenarPorApellido();
                        System.out.println("Lista de alumnos ordenada por apellido y nombre:");
                        mostrarAlumnos(cursoOrdenado);
                    }
                    break;
                case 6:
                    System.out.println("Mostrando alumnos egresados...");
                    if (escuela.getEgresados().cantidadEgresados() == 0) {
                        System.out.println("No hay alumnos egresados.");
                    } else {
                        Egresados egresados = escuela.getEgresados();
                        System.out.println(egresados);
                    }
                    break;
                case 7:
                    System.out.println("Mostrando alumnos egresados...");
                    if (escuela.getEgresados().cantidadEgresados() == 0) {
                        System.out.println("No hay alumnos egresados.");
                    } else {
                        Alumno[] egresadosOrdenados = escuela.getEgresados().ordenarPorPromedioAdv();
                        System.out.println("Lista de alumnos egresados ordenada por promedio:");
                        mostrarAlumnos(egresadosOrdenados);
                        double[] tiempos = escuela.getEgresados().compararTiemposOrdenamiento();
                        System.out.println("////////// Tiempos de ordenamiento /////////");
                        System.out.printf("Bubble Sort: %.2f ms\n", tiempos[0]);
                        System.out.printf("Merge Sort: %.2f ms\n", tiempos[1]);
                    }
                    break;
                case 8:
                    System.out.println("Mostrando cantidad de vacantes disponibles...");
                    if (!escuela.hayAlumnosCargados()) {
                        System.out.println("No hay alumnos cargados.");
                    } else {
                        int vacantes = escuela.cantidadVacantes(0);
                        System.out.println("Cantidad de vacantes disponibles: " + vacantes);
                    }
                    break;
                case 9:
                    System.out.println("Buscando alumno por curso y legajo...");
                    if (!escuela.hayAlumnosCargados()) {
                        System.out.println("No hay alumnos cargados.");
                    } else {
                        System.out.println("Ingrese el número del curso (1-7):");
                        int cursoBuscado = leerOpcion(1, 7);
                        System.out.println("Ingrese el legajo del alumno:");
                        int legajoBuscado = leerOpcion(1, 3000);
                        int[] posicion = escuela.buscarAlumno(cursoBuscado, legajoBuscado);
                        if (posicion[1] != -1) {
                            Alumno alumnoEncontrado = escuela.getCursos()[posicion[0]].getCurso()[posicion[1]];
                            System.out.println("Alumno encontrado: " + alumnoEncontrado);
                            System.out.println("En la posición " + posicion[1] + " del curso " + (posicion[0] + 1));
                        } else {
                            System.out.println("No se encontró un alumno con el legajo " + legajoBuscado
                                    + " en el curso " + cursoBuscado);
                        }
                    }
                    break;
            }
            System.out.println("--------- Fin de la opción seleccionada ---------");

        } while (opcion != 0);
    }
}
