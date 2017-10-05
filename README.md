Para ejecutar el proyecto:
-Abrir en Eclipse en un computador donde hayan JBossTools y wildfly 8.2.

-Ejecutar el proyecto en un servidor wildfly.

Ejemplos para los requerimientos:
-RF5:
Para este requerimiento se puede proporcionar el siguiente json:
{	"id_Muelle" : "007", 
	"id_Buque" : "CD02", 
	"fecha_llegada" : "10-JAN-16"
	"fecha_salida" : "15-JAN-16"
}
En el url:
http://localhost:8080/it2/rest/atracos/registrarSalida

-RF6:
{
	"id" : "00006",
	"fecha" : "16-FEB-07",
}
En el url:
http://localhost:8080/it2/rest/registroscargamentos/darInfoAOperador


-RF8:
Para este requerimiento se puede proporcionar el siguiente json:
{
    	"id_Cargamento" : "0003",
		
	"tipo" : "Madera",
		
	"volumen" : 5,
		
	"id_Almacenamiento" : "002"

}
En el url:
http://localhost:8080/it2/rest/registroscargamentos/entregar

-RF9:
Para este requerimiento se podría proporcionar el siguiente objeto json:
{
    "fecha" : "16-MAR-02 14:33:00",
    "id_buque" : "IJ05"
}
En el url:
http://localhost:8080/it2/rest/facturas/generar

-RFC1:
Para este requerimiento puede probarse el siguiente url:
http://localhost:8080/it2/rest/atracos/consulta/identificador/nombre/nombre/Esteban/opcional/nada/opcional2/nada/parametroAgrup/nada/parametroOrd/nada


-RFC2:
Para este requerimiento puede probarse la siguiente url:
http://localhost:8080/it2/rest/exportadores/id/05/orden/peso