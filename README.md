# TestApplicationBinaryLast

Se tiene una tabla donde se almacena el usuario, la contraseña y un número decimal que determina el
nivel de permiso de un usuario y una tabla con las acciones que puede realizar el usuario:
Las acciones son:
1. Crear clientes.
2. Eliminar clientes.
nivel de permiso de un usuario y una tabla con las acciones que puede realizar el usuario:
Las acciones son:
1. Crear clientes.
2. Eliminar clientes.
3. Editar clientes.
create table usuarios (
Id int(11) AUTO_INCREMENT PRIMARY KEY,
Nombre varchar(100),
Contrasena varchar(100),
NivelPermiso int(11)
)
create table permisos (
IdOrden int(11) AUTO_INCREMENT,
Modulo varchar(50) ,
PRIMARY KEY (`IdOrden`)
)
create table clientes(
Id int(11) AUTO_INCREMENT PRIMARY KEY,
Cedula int(11),
Nombre varchasr(100),
teléfono varchar(100)
, email varchar(100) )
Este nivel de permiso se lee y se convierte a binario, dejando así la posibilidad que una acción tenga un
1 o un 0, 1 si aplica y 0 si no, es decir, si el nivel de permiso de un usuario es 3, deben haber 3 registros
en la tabla, el binario de 3 es 011 (con el 0 incluido ya que también se lee) y según el IdOrden de la tabla
permisos el primer binario de 3 es 0, entonces el usuario no tendrá permiso para Crear Clientes, el
segundo binario es 1 entonces el usuario tendrá permiso para eliminar clientes con el registro IdOrden 2
en la tabla permisos, y el tercer registro es 1, entonces también podrá Editar clientes .
Tener en cuenta que el número de dígitos del binario es directamente proporcional al número de
registros en la tabla de permisos, registrando incluso los ceros.
Usted debe realizar una aplicación que le permita ingresar con el usuario y la contraseña para luego
mostrar la lista de clientes con las acciones que el usuario puede realizar sobre ellos.
Si el usuario tiene permisos para la acción 1 entonces se deberá mostrar un botón el cual al presionarlo
genere una alerta en la cual se pueda insertar: el nombre, la cedula, el teléfono y el correo electrónico del
cliente que se está creando. Al darle click en aceptar, deberá insertar el cliente nuevo en la tabla clientes
y refrescar la lista de los clientes
Si el usuario tiene permisos para la acción 2 y/o la acción 3 entonces el usuario al darle click sobre un
cliente de la lista, mostrara una alerta con las acciones permitidas. Si tiene permisos para la acción 2 solo
aparecerá la acción de “Eliminar cliente”, si tiene permisos solo para la acción 3 entonces mostrara la
opción de “Editar cliente”, del mismo modo si el usuario tiene permisos para las dos acciones entonces
se mostraran las dos opciones
ACLARACIONES:
1. Las acciones 2 y 3 solo serán informativas indicando al usuario al presionar sobre el
cliente que permisos tiene si editar o eliminar, se deja la opción al desarrollador de
realizar la programación de estas dos acciones.
2. La aplicación se debe realizar en Android Studio
3. Usted puede ingresar los registros en la tabla. Si considera necesario algún cambio a las
tablas no dude en hacerlo siempre y cuando no afecte el planteamiento del problema
inicial.
