INSTRUCCIONES DE USO

Para corrrer el progrma se deben clonar/copiar los archivos subidos al repositorio.
Dentro del `pom.xml` se encuentran todas las dependencaias y plugins del proyecto. 
Ya que se utiliza javafx para la interfaz visual, para correr el prgrama se debe correr el `javafx.run` dentro de los plugins o correr el `MainJavaFx.java`.

CONDICIONES DE LA INTERFAZ VISUAL EN CONFIG

- **ColoresJugadores:** el disenio esta hecho para que cada tipo de jugador sea el nombre de una foto en la carpeta `/resources/images/` para que se puedan agregar distintas fotos como personajes. 
Si se agrega un personaje se debe agregar el color en ColoresJugadores y que sea el nombre de la imagen. ej: el ColorJugador SOMBRERO representa `SOMBRERO.png`.

+ **ColoresComprables:** los ColoresComprables representan el color del barrio de los comprables. Para que se puedan agregar colores nuevos y sean representados visualmente en el tablero,
deben ser nombres de colores reales en ingles y en minuscula. ej: el ColorComprable lightblue representa el barrio de celestes y se ve de ese color en la visual

+ **ListaCasilla:** la lista casilla puede modificarse para que haya cantidades de casillas que sean multiplos de 4 o no, y las casillas que van en las esquinas como PASO, SALIDA, CARCEL e IR_A_CARCEL pueden 
estar en los lados tambien. Lo mismo para, las casillas que normalmente estan en los lados, pueden estar en las esquinas y se van a ver.
