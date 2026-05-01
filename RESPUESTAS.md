## **ÁRBOLES**
### Crea un programa de prueba
#### Verifica que la suma es la misma cuando se suman los elementos de los subárboles izquierdo y derecho. ¿Por qué?
El resultado de la suma es 8256. En todos los casos la suma es la misma. Esto se debe a que lo único que cambia es el orden en el que se pasa por cada nodo, sin embargo, los elementos que sumamos son los mismos. La suma coincide porque podemos garantizar el recorrido de todos los elementos, puesto que sumamos el nodo más actual y la suma total de sus árboles derecho e izquierdo.

#### ¿Cuál es la altura del árbol?
Como todos los nuevos nodos son mayores que el anterior, los hijos solo se insertan en un lado y no hay ramificación alguna, por lo que es orden es ascendente. Así pues, la altura es 128.

#### ¿Cuál es el camino para llegar al valor 110? ¿Cuál es su longitud de camino?
El camino se guido es [0, 1, 2, 3, ..., 110]. La longitud es 110 (obtenido contando, desde la raíz, el número de saltos)

### Crea un segundo programa de prueba
#### Verifica que la suma es la misma cuando se suman los elementos de los subárboles izquierdo y derecho. ¿Por qué?
Como tiene los mismos números del 0 al 128, el resultado se mantiene en 8256. Como ya hemos visto anteriormente, la suma vuelve a ser la misma en los diferentes casos. Dada la recursividad, el resultado no varía en la suma de subárboles.

#### ¿Cuál es la altura del árbol? ¿por qué?
Según el orden que se haya elegido, la altura puede ser desde 7 hasta 15, ya que dicho orden se elige de manera aleatoria. Como los datos que se utilizan no tienen un orden previo, unos serán mayores que la raíz, pero otros, menores, así pues habrá ramificaciones tanto a la izquierda como a la derecha. De esta forma, se conseguirá una distribución de nodos más eficiente, ya que el árbol crece de manera simultánea.

#### ¿Cuál es el camino para llegar al valor 110? ¿Cuál es su longitud de camino?
Se sigue un camino que salta entre varios números no ordenados. Por ejemplo, se podría dar el siguiente camino: [96, 64, 108, 104, 110, 112]. La longitud puede variar de 6 a 12.

### Explique las diferencias (si las ha habido) de los resultados obtenidos entre los dos programas de prueba.
Lo que diferencia los programas es principalmente la eficiencia. En el primer programa, el árbol generado en la primera prueba se asemeja a una lista, pues no tiene ramificaciones hacia ambos lados. Sin embargo, en la segunda prueba si encontramos un árbol algo más equilibrado de ambos lados. En cuanto al segundo programa, en la primera prueba cuesta más llegar hasta el número que se desea encontrar (tantas operaciones como números haya hasta llegar a ese). En cambio, en la segunda, no son necesarias tantas operaciones para llegar a un mismo número.

### ¿Qué sucede con los resultados si ejecuta los programas de prueba varias veces?
Al ser la primera prueba ordenada, los datos mantienen una disposición fija, de forma que el árbol que se forma siempre va a tener la misma estructura y altura. En general, los resultados no varían. Sin embargo, en la segunda no es ordedana, luego aunque el resultado de la suma no cambia (puesto que los elementos son los mismos), la altura del árbol y la longitud del camino hasta cierto número, sí se alteran. Esto se debe a que las posiciones de los nodos varían cada vez que se inicia el programa.

## **GRAFOS**
#### ¿Cuál es el camino mínimo entre dos entidades A y B del grafo?
Se exploran las relaciones nivel por nivel habiendo partido del nodo A, esto es debido a la forma en la que se implementa el grafo. Así pues, cuando se llega al nodo B por primera vez, está asegurado que el camino ha sido recorrido con el número mínimo de saltos. AÑADIR RESPUESTA CONCRETA SEGÚN NUESTROS GRAFOS!!!!

#### Dado un archivo de datos que se carga en el grafo, ¿genera un grafo disjunto? Cree dos archivos que generen cada opción posible y compruebe en el código.
Un grafo es disjunto cuando el número de nodos que se visitan en un recorrido es menor que el número de nodos que se han registrado para generar el grafo. Esto significará que hay dos subgrafos que no están conectados. En este caso, el grafo es PONER SI ES DISJUNTO O NO SEGÚN NUESTROS GRAFOS!!!!

#### Suponiendo un grafo de conocimiento general con la información de los premios Nobel de todas las áreas, cómo harías para responder a la pregunta: ¿Qué físico famoso nació en la misma ciudad que Einstein? Crea el fichero de datos para completar el grafo y poder extraer la respuesta a esta pregunta. Crea el código para verificarlo.
Primero buscaríamos el lugar partiendo de la tripleta <"persona:Albert Einstein", "nace_en", lugarnac>, donde lugarnac = lugar:Ulm, puesto que Einstein nació en la ciudad alemana Ulm. Finalmente, tendríamos que buscar a la persona distinta del científico ya mencionado que cumpla las condiciones (haber nacido en dicha ciudad y haber obtenido el Nobel de Física).

#### Añada una tripleta <"persona:Antonio", "nace_en", "lugar:Villarrubia de los Caballeros"> al grafo. Liste cuáles son los lugares de nacimiento de los premios Nobel. ¿Qué caminos necesita recorrer para que su respuesta fuese correcta?
El problema es que no vamos a encontrar “Villarrubia de los Caballeros” en la lista final. Así pues, para cambiar esto, necesitamos distinguir los sujetos que tengan una arista que empiece por “premio”. Una vez que seleccionamos solo esas aristas, recorremos “nace_en” y escogemos el objeto final, que cumple estos requisitos. Por otro lado, esta persona, Antonio, no tiene ningún premio Nobel, luego el camino se pararía en la primera condición, ya que no la cumple.

#### ¿Qué tipos de nodos tiene el grafo?
Existen los nodos que no pueden tener aristas salientes ni ser sujetos, solo pueden ser objetos. Así pues, se limitan estar formados por datos, por ejemplo, un año. El segundo tipo de nodos. Además de poder ser objetos, también pueden actuar como sujetos. De esta forma, los nodos identifican recursos de manera abstracta, un ejemplo sería el de Einstein, utilizado anteriormente: persona:Albert Einstein, lugar:Ulm.

#### ¿Qué es una ontología? ¿Qué relación tiene con los grafos? ¿Podríamos crear una ontología para nuestro problema? ¿Qué haríamos con ella?
Una ontología es un modelo que define las propiedades, reglas, clases y vocabulario de un área de conocimiento. La ontología determina “las reglas” y cómo unos datos concretos pueden interactuar. El grafo, por su parte, contiene dichos datos.
Podríamos usar la ontología definiendo clases y propiedades; siguiendo los ejemplos anteriores: persona y nace_en, respectivamente. Con la ontología se evita que suceda algo ilógico como insertar una tripleta que especifique el lugar de nacimiento de una persona es un premio. Además, también obviamos tener que crear tripletas específicas, puesto que ya hay datos implícitos en otros. Por ejemplo, si una persona nace en la ciudad de Sevilla, se da por hecho que el país de nacimiento es España, de esta forma, no se necesita crear una tripleta específica para el país.
