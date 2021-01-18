package practica;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import java.util.Scanner;

public class Principal {

	static int i;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MongoClient conexion = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase db = conexion.getDatabase("adp5");
		MongoCollection<Document> alumnos = db.getCollection("harry-potter");
		
		Scanner teclado = new Scanner(System.in);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Seleccione la operación que desea realizar: \n");
		System.out.println("\n 1. Insertar nuevos documentos.");
		System.out.println("\n 2. Mostrar a todos los humanos.");
		System.out.println("\n 3. Mostrar a los que han nacido antes de 1979.");
		System.out.println("\n 4. Mostrar a los poseedores de varitas de madera sagrada.");
		System.out.println("\n 5. Mostrar a todos los alumnos vivos de Hogwarts.");
		System.out.println("\n 6. Cambiar el color de ojo del falso al verdadero Alastor Moody.");
		System.out.println("\n 7. Eliminar a los personajes añadidos anteriormente.");
		System.out.println("\n 8. Salir.");
		
		i = teclado.nextInt();
		
		while(i!=8) {
			
			if(i==1) {
				Funciones.insertarDocumentos(alumnos);
			}
			else if(i==2) {
				Funciones.consultaHumanos(alumnos);
			}
			else if(i==3) {
				Funciones.consultaEdad(alumnos);
			}
			else if(i==4){
				Funciones.consultaVaritaSagrada(alumnos);
			}
			else if(i==5) {
				Funciones.consultaAlumnosVivos(alumnos);
			}
			else if(i==6) {
				Funciones.modificarAlastor(alumnos);
			}
			else if(i==7) {
				Funciones.eliminarNuevos(alumnos);
			}
			else {
				System.out.println("\n Seleccione una opercaión válida. \n");
			}
			
			
			System.out.println("Seleccione la operación que desea realizar: \n");
			System.out.println("\n 1. Insertar nuevos documentos.");
			System.out.println("\n 2. Mostrar a todos los humanos.");
			System.out.println("\n 3. Mostrar a los que han nacido antes de 1979.");
			System.out.println("\n 4. Mostrar a los poseedores de varitas de madera sagrada.");
			System.out.println("\n 5. Mostrar a todos los alumnos vivos de Hogwarts.");
			System.out.println("\n 6. Cambiar el color de ojo del falso al verdadero Alastor Moody.");
			System.out.println("\n 7. Eliminar a los personajes añadidos anteriormente.");
			System.out.println("\n 8. Salir.");
			
			i = teclado.nextInt();
			
		}
		System.out.println("Cerrando conexión con la base de datos...");
		
		teclado.close();
		conexion.close();

	}

}
