package practica;

import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

import java.util.Arrays;

public class Funciones {
	
	public static void insertarDocumentos(MongoCollection<Document> alumnos) {
		//añadimos un documento
		alumnos.insertOne(Document.parse("{ nombre:'Dobby', species:'elf', gender:'male', house:'', dateOfBirth:'28-06-????', yearOfBirth:'????', ancestry:'', eyeColour:'green', hairColour:'none', wand:'', patronus:'', hogwartsStudent:false, hogwartsStaff:false, actor:'Diane Gibbins', alive:false, image:'https://th.bing.com/th/id/OIP.77vYWzhhe-5hffJmfWYZSwHaKL?pid=Api&rs=1'}"));
		
		System.out.println("Se ha insertado a Dobby.");
		
		//añadimos varios documentos
		alumnos.insertMany(Arrays.asList(
				Document.parse("{ nombre:'George Weasley', species:'human', gender:'male', house:'Gryffindor', dateOfBirth:'01-04-1978', yearOfBirth:'1978', ancestry:'pure-blood', eyeColour:'brown', hairColour:'red', wand:{wood:'uknown', core:'uknown', lenght:''}, patronus:'magpie', hogwartsStudent:true, hogwartsStaff:false, actor:'Oliver Phelps', alive:true, image:'https://static.wikia.nocookie.net/esharrypotter/images/1/1a/250px-PHELPS2.jpg/revision/latest?cb=20150523213457'}"),
				Document.parse("{ nombre:'Quirinus Quirrel', species:'human', gender:'male', house:'Ravenclaw', dateOfBirth:'26-09-1955', yearOfBirth:'1955', ancestry:'half-blood', eyeColour:'blue', hairColour:'none', wand:{wood:'alder',core:'unicorn hair', lenght:23}, patronus:'non-corporeal', hogwartsStudent:false, hogwartsStaff:true, actor:'Ian Hart', alive:false, image:'https://static.wikia.nocookie.net/esharrypotter/images/5/5d/Quirinus_Quirrell.PNG/revision/latest?cb=20130305195559'}"),
				Document.parse("{ nombre:'Alastor Moody', species:'human', gender:'male', house:'Ravenclaw', dateOfBirth:'????', yearOfBirth:'????', ancestry:'pure-blood', eyeColour:'electric blue', hairColour:'blonde', wand:{wood:'',core:'',lenght:30}, patronus:'bird rapacious', hogwartsStudent:false, hogwartsStaff:true, actor:'Brendan Gleeson', alive:false, image:'https://static.wikia.nocookie.net/esharrypotter/images/e/e2/P7_Alastor_Moody.png/revision/latest?cb=20140728221051'}")));
		
		System.out.println("Se han insertado a 'George Weasley', 'Quirinus Quirrel' y 'Alastor Moody'. \n");
	}
	
	//Busqueda de humanos
	public static void consultaHumanos(MongoCollection<Document> alumnos) {
		FindIterable<Document> findHuman = alumnos.find(eq("species", "human"));
		System.out.println();
		for (Object humano : findHuman) {
			System.out.println(((Document) humano).toJson());
		}
		System.out.println();
	}
	
	//Busqueda nacidos antes de 1979 
	public static void consultaEdad(MongoCollection<Document> alumnos) {
		FindIterable<Document> findYear = alumnos.find(lte("yearOfBirth", 1979));
		System.out.println();
		for (Object i : findYear) {
			System.out.println(((Document) i).toJson());
		}
		System.out.println();
	}
	
	//Busqueda de persona que posea la varita con madera sagrada
	public static void consultaVaritaSagrada(MongoCollection<Document> alumnos) {
		FindIterable<Document> findHolly = alumnos.find(eq("wand.wood", "holly"));
		System.out.println();
		for (Object i : findHolly) {
			System.out.println(((Document) i).toJson());
		}
		System.out.println();
	}
	
	//Busqueda de alumnos vivos
	public static void consultaAlumnosVivos(MongoCollection<Document> alumnos) {
		FindIterable<Document> findStuAlive = alumnos.find(and(eq("alive", true), eq("hogwartsStudent", true)));
		System.out.println();
		for (Object alumno : findStuAlive) {
			System.out.println(((Document) alumno).toJson());
		}
		System.out.println();
	}
	
	//Modificar documentos
	public static void modificarAlastor(MongoCollection<Document> alumnos) {
		alumnos.updateOne(eq("nombre", "Alastor Moody"), combine(set("eyeColour", "brown")));
		
		System.out.println("Debido a que se encontró al verdadero Alastor, se ha modificado su color de ojos al verdadero: Marrones. \n");
	}
	
	//Eliminar documentos que hemos añadido anteriormente
	public static void eliminarNuevos(MongoCollection<Document> alumnos) {
		alumnos.findOneAndDelete(eq("nombre", "Dobby"));
		alumnos.findOneAndDelete(eq("nombre", "George Weasley"));
		alumnos.findOneAndDelete(eq("nombre", "Quirinus Quirrel"));
		alumnos.findOneAndDelete(eq("nombre", "Alastor Moody"));
		
		System.out.println("Se han eliminado a 'Dobby', 'George Weasley', 'Quirinus Quirrel' y 'Alastor Moody'.");
	}

}
