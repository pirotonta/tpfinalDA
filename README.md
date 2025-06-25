## Clases utilizadas


Para lograr el propósito del enunciado, se desarrollaron siete clases distintas para manipular
datos abstractos y para contener funciones con un propósito específico.
- TDA Alumno: se encarga de crear objetos Alumno con los datos especificados por el
enunciado - apellido, nombre, legajo, grado y promedio general.
- TDA Curso: contiene un arreglo de objetos Alumno y operaciones específicas a los
cursos.
- TDA Egresados: contiene un arreglo de objetos Alumno y operaciones específicas a
los egresados.
- TDA Escuela: contiene una matriz en la que cada fila representa un Curso. Carga
además al arreglo de desaprobados y una instancia de Egresados.
- La clase Sorting, donde se almacena la lógica de ordenamiento (Burbuja, Burbuja
mejorado y MergeSort) junto al criterio utilizado (promedio, apellido,
apellido+nombre).
- La clase ArchivosController, donde se leen los archivos de texto que contienen la
información sobre los alumnos.
- La clase main, donde se desarrolla la interacción con el usuario a través de un menú
de opciones.

<p align=center><img src="https://github.com/user-attachments/assets/cc9d21ef-c937-4982-8c01-88df070242d9" height="600"></p><br>



## Estructuras utilizadas (en la clase Main)


i. *double[] promedios*: para almacenar el retorno del método promediosPorCurso() de
Escuela.<br>
ii. *Alumno[] cursoOrdenado*: para almacenar el retorno del método de ordenamiento de
Curso, que devuelve un arreglo de objetos Alumno ordenado por apellido y nombre.<br>
iii. *double[] tiempos*: para almacenar los tiempos de ordenamiento del método
compararTiemposOrdenamiento de Egresados.<br>
iv. *Alumno[] egresadosOrdenados*: análogo a cursoOrdenado, almacena un arreglo de
instancias de Alumno ordenados por promedio.<br>
v. *int[] posicion*: almacena la posición donde se encuentra el alumno buscado en el método
buscarAlumno de Escuela, [fila, columna].<br><br>


## Reutilización de código


Se crearon varias clases y métodos para evitar la repetición del código. Entre ellas:<br>
i. *leerOpcion(int max, int min)*: gestiona la lectura del valor ingresado por el usuario y valida
que se encuentre en el rango deseado, continúa preguntando hasta que se ingresa un
entero válido y se devuelve el mismo.<br>
ii. *hayAlumnosCargados()*: método de Escuela creado para verificar que se cargaron
alumnos antes de acceder a las operaciones del menú de opciones.<br>
iii. La clase Sorting fue implementada para poder acceder a los métodos de ordenamiento
en todo el proyecto evitando la repetición del código, permitiendo además definir criterios de
comparación para uso futuro.<br>
iv. *contarAlumnos()*: método de Curso utilizado para varias verificaciones, entre ellas el
mismo método hayAlumnosCargados(), para calcular la cantidad de vacantes y para el
cálculo de promedios.<br>
v. *mostrarAlumnos()* en la clase Main, para recorrer los arreglos de tipo Alumno[].<br><br>


## Manejo de valores nulos


Se implementan métodos para prevenir errores o comportamientos inesperados
relacionados con valores nulos en los arreglos y estructuras de datos:<br>
i. Antes de realizar las operaciones del menú de opciones se verifica que se hayan cargado
alumnos sobre los que operar previamente.<br>
ii. En la carga de los archivos .txt se utilizan estructuras try-catch para informar sobre
excepciones en el formato del texto que no son compatibles con la carga del sistema.<br>
iii. Asimismo, al cargar alumnos se recorre el arreglo de tal forma que sean asignados a la
primera posición nula - garantizando que los objetos queden al principio de los arreglos y los
espacios en blanco al final.<br>
iv. Se creó el método agruparCurso() para garantizar que, al manipular la estructura de
cursos al pasar de grado a los alumnos, se mantengan los espacios vacíos “al fondo” en los
arrays.<br>
v. Se utilizan ciclos while para optimizar el recorrido de arreglos de alumnos - ya que existe
la precondición de tener los objetos agrupados al comienzo de los arreglos globalmente en
el proyecto.<br>
