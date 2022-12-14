# TENIS LAB BARCELONA
![imagen](./img/portada.png)

## Problema Propuesto
La vida te hace conocer a personas extraordinarias. Xavi Colomina es uno de los mejores encordadores y técnico para personalizar raquetas que hay. Él trabaja con figuras ATP y WTA. Además de magnifico profesional, es una persona maravillosa. Y me siento orgulloso de poder llamarlo amigo. “Colo”, me hizo mis raquetas hace un tiempo (uno de mis tesoros personales), y aún tenemos otras pendientes (no se puede escapar de ello). Un día me dijo: “Me gustaría tener una app para gestionar los pedidos que tenemos en el torneo de Conde de Godó”.
Cuando hay un pacto entre buena gente hay que cumplirlo. Así que vamos a esbozar con el nivel que tenemos en este curso una pequeña aplicación para gestionar los encordados. ¿Nos ayudas a Colo y a mi y te sacas una buena nota?
En nuestra aplicación se conectan distintos usuarios, con su nombre, apellido, email y password (siempre codificado en la base de datos, usando sha512). Además, sabemos que existe el perfil de administrador (encargado o jefe), encordador (trabajador) y tenista (cliente).

Trabajamos con varias máquinas, que son de encordar o de personalización. Para cada máquina, nos interesa saber su marca, modelo, fecha de adquisición y número de serie. Si la máquina es de encordar, debemos saber si es manual o automática, tensión máxima y tensión mínima de trabajo. Si es de personalizar, debemos saber si puede o no medir maniobrabilidad (swingweight), balance (equilibrio) y rigidez (resilencia).

Los pedidos pueden estar formados por varias tareas o partes de trabajo a realizar que recibimos de un tenista y son asignados a un encordador y tiene un estado: recibido, en proceso o terminado y la máquina asociada si se necesita. Tenemos un tope de entrega marcado por una fecha. Los pedidos tiene una fecha de entrada, una fecha de salida programada y de salida final y un precio asociado que es la suma de todas las acciones. La fecha de salida final será inicialmente la programada, luego se actualizará a la real. 

Para cada tarea/acción a realizar necesitamos saber la raqueta o raquetas (una acción por raqueta) con la que trabajar si se necesita. Si es encordado necesitamos tensión de cuerdas horizontales y cordaje, tensión de cuerdas verticales y cordaje y si queremos dos o cuatro nudos. El precio será 15€ más el precio del producto o productos a usar. Si es personalización necesitamos saber peso en gramos, balance y rigidez. Su precio será de 60€. Por ejemplo, Rafa Nadal encuerda su Raqueta Babolat Pure Aero Rafa a 25Kg tanto horizontales como verticales en 4 nudos usando Babolat RPM Blast como cordaje. Si es adquisicón sumamos el precio del producto adquirido (comprado). Podemos tener en cuenta que podemos tener distintas acciones para un mismo tenista en un pedido, por ejemplo 3 encordados, un equilibrado y cuatro complementos que pueden ser adquiridos.

Debemos tener en cuenta que un encordador no puede tener más de dos pedidos activos por turno. Del turno nos interesa saber comienzo y fin del mismo. Un encordador no puede usar otra máquina si ya tiene asignada una en un turno determinado.

Además, como vendemos distintos productos del tipo: raquetas, cordajes, y complementos como overgrips, grips, antivibradores, fundas, etc. Necesitamos saber el tipo, marca, modelo, precio y stock del mismo. Ten en cuenta que solo podrá realizar operaciones CRUD el encargado del sistema, pero la asignación de pedidos la puede hacer también los encordadores.

Por otro lado, nos interesa mantener el histórico de los elementos del sistema y
- CRUD completo de los elementos que consideres necesarios.
- Sistema de errores y excepciones personalizados.
- Información completa en JSON de un pedido.
- Listado de pedidos pendientes en JSON.
- Listado de pedidos completados en JSON.
- Listado de productos y servicios que ofrecemos en JSON.
- Listado de asignaciones para los encordadores por fecha en JSON.


<br>
1. Completar la información que te falta hasta tener los requisitos de información completos.
<br>
2. Realizar el Diagrama de Clases asociado, mostrando la semántica, navegabilidad y cardinalidad de las relaciones, justificando la respuesta con el máximo detalle. Crear las Clases del modelo asociadas así como las tablas para el almacenamiento en una base de datos relacional.
<br>
3. Implementación y test de repositorios y controladores de las operaciones CRUD y otras operaciones relevantes aplicando las restricciones indicadas usando Exposed.
<br>
4. Implementación y test de repositorios y controladores de las operaciones CRUD y otras operaciones relevantes aplicando las restricciones indicadas usando JPA.
<br>
Nuestro programa debe llamarse con un JAR de la siguiente manera: java -jar tennislab.jar.


## Descripción




## Funcionamiento



## Analisis de datos




## Autores
- Jorge Sánchez Berrocoso
- Daniel Rodríguez Fernández
- Alfredo Maldonado Pertuz


## Contacto 
* daniel.rodriguezfernandez@alumno.iesluisvives.org
* jorge.sanchezberrocoso@alumno.iesluisvives.org

