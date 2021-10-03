# Software_Engineering-Algorithmics
Este Repositorio tiene un propósito estrictamente profesional de promoción laboral a través de la comunidad GitHub.

1.-DESCRIPCIÓN:

El problema de las 8 reinas es un problema clásico muy usado en el estudio de la algorítmica para ilustrar el esquema de Vuelta Atrás. El enunciado clásico consiste en
colocar 8 reinas sobre un tablero de ajedrez de 8x8 de manera que no se ataquen entre ellas. El problema lo resolveremos en su forma general, consistente en colocar N reinas 
sobre un tablero de ajedrez de NxN.

 - Argumentos y parámetros
La práctica se invoca usando la siguiente sintaxis:

java reinas [-t] [-g] [-h] n [fichero_salida]

o

java –jar reinas.jar [-t] [-g] [-h] n [fichero_salida]

Los argumentos son los siguientes:

• -t: traza cada paso de manera que se describa la aplicación del algoritmo
utilizado mostrando las posiciones rechazadas y las válidas. No hace falta trazar
las posiciones incompletas.

• -g: modo gráfico. Usa un formato visual que representa el tablero (p. ej. con
caracteres ASCII y en tipografía de ancho fijo)

• -h: muestra una ayuda y la sintaxis del comando. 

Por ejemplo:

$ Java reinas -h <ENTER>

SINTAXIS:

reinas [-t][-h] N [fichero_salida]

• -t: Traza

• -g: Modo gráfico

• -h: Muestra esta ayuda

• N: Tamaño del tablero y número de reinas.

• fichero_salida: Nombre del fichero de salida

Si no se proporciona el argumento correspondiente al fichero de salida el programa usará la salida estándar.

2.- PRUEBA FUNCIONAL:

La salida será la lista numerada de soluciones indicada mediante las coordenadas del tablero. La representación sería:

1: a4 b8 c1 d3 e6 f2 g7 h5

2: …

3: …

etc.

Con la opción “-g” el fichero de datos de salida será un tablero con las reinas indicadas con “R” y escrito en tipografía de ancho fijo para que sea legible. Por ejemplo:

---------------------------------

8 | * | R | * | | * | | * | |

-----------------------------------

7 | | * | | * | | * | R | * |

-----------------------------------

6 | * | | * | | R | | * | |

-----------------------------------

5 | | * | | * | | * | | R |

-----------------------------------

4 | R | | * | | * | | * | |

-----------------------------------

3 | | * | | R | | * | | * |

-----------------------------------

2 | * | | * | | * | R | * | |

-----------------------------------

1 | | * | R | * | | * | | * |

-----------------------------------

a b c d e f g h

En general se deja a criterio del alumno la representación gráfica, siempre que sea suficientemente clara y visual. También se deja a criterio del alumno qué se hace para
tamaños mayores de 27 en ambos formatos de salida.
