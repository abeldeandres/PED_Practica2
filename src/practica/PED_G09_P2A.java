
package practica;

import EstructuraDeDatos.lineales.ArrayCola;
import EstructuraDeDatos.lineales.LECola;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Abel de Andrés e Iván Barbado
 */
public class PED_G09_P2A {

    /**
     * @param args the command line arguments
     */
    public static Scanner ent = null;

    public static int leerInt() {//Lectura de número por teclado, con la implementación de una excepción en caso de introducir un caracter que no se corresponda con un número.

        String strNumero = "";
        int numero = 0;
        boolean lecturaBien = false;
        do {
            ent = new Scanner(System.in);
            System.out.print("> ");
            strNumero = ent.nextLine();

            try {
                numero = Integer.parseInt(strNumero);
                lecturaBien = true;
            } catch (Exception e) {
            }

        } while (!lecturaBien);//Se repetirá la petición de strNumero mientras lecturaBien tenga el valor booleano false, es decir, no se corresponda con un entero.

        return numero;
    }
    
   //El metodo invertirCola es muy simple. Simplemente vamos a desencolar hasta que la cola se quede vacia.
    //Por lo tanto nuestro caso base es que la cola se quede vacia. Hasta entonces nos encontramos en la fase de desplegado.
    //Una vez que la la cola es vacia, encolaremos los elementos desde la pila del stack hasta la cola inicial, por lo tanto
    //como la pila es FIFO y la cola LIFO, conseguiremos darle la vuelta a los elementos de la cola.
    public static <E extends Comparable<E>> void invertirCola(ArrayCola<E> cola)
    {
        if(!cola.esVacia())
        {
            E dato= cola.desencolar();
            invertirCola(cola);
            cola.encolar(dato);
        }
        
    }
    
    //Ahora contaremos los elementos de forma iterativa, lo que hemos hecho es almacenar el primer valor(el valor del frente) de la cola
    //Una vez que tenemos este primer valor guardado en una variable, procedemos a desencolar el resto de elementos y encolarlos seguidamente ademas de aumentar en una
    //unidad nuestro contador. Cada vez que desencolamos, aumentamos el valor del contador. Iremos desencolando y encolando
    //hasta que el valor que vayamos a desencolar sea igual al primer valor, en ese caso ya pararemos.
    //Por otro lado, podemos afirmar que no existe un elemento "centinela" pues para que exista un elemento "centinela", seria necesario
    //introducir ese valor "centinela" dentro de la cola.
    public static <E extends Comparable<E>> int contarElementosIterativo(ArrayCola<E> cola)
    {
        if(!cola.esVacia()){
            E primero=cola.desencolar(); //Desencolamos el primer elemento. Con esto conseguimos guardar el valor en una variable.
            cola.encolar(primero); //Volvemos a encolar el elemento.
            int contador=1;//Puesto que ya hemos encolado y desencolado un elemento, nuestro contador comienza en 1.
            boolean fin=false;
            while(!fin){
                E desencolado=cola.primero();
                //Si el valor desencolado es igual al primero, terminamos el bucle mediante el boolean "fin"
                if(desencolado.compareTo(primero)==0)
                {
                fin=true;
                }
                //En caso contrario, desencolamos, aumentamos el contador y volvemos a encolar el elemento.
            else{
               cola.desencolar();
               contador++;
               cola.encolar(desencolado);
            }

        }
        return contador; 
        }
        else return 0;
        
    }
    
    //Utilizamos el metodo recursivo. Este es el metodo lanzadera donde llamaremos al propio metodo recursivo.
    public static <E extends Comparable<E>> int contarElementosCola(ArrayCola<E> cola)
    {
        int contar=contarElementosColaRec(cola);//Obtenemos el numeros de elementos
        invertirCola(cola);//Invertimos la cola para que quede igual que en el estado inicial.
        return contar;
    }
    //En este metodo recursivo, el caso base va a ser que la cola se quede vacia. Si se queda vacia
    //entonces habremos desencolado todos los elementos y habremos obtenido las veces que hemos tenido que desencolar
    //y que corresponde con el numero de elementos de la cola.
    public static <E extends Comparable<E>> int contarElementosColaRec(ArrayCola<E> cola)
    {
        int contador=0;
        if(cola.esVacia())
        {
            return contador;
        }
        
        else{
            E dato=cola.desencolar();//Desencolamos
            contador=contarElementosColaRec(cola)+1;//Utilizamos el metodo recursivo sumandole uno a la variable contador
            cola.encolar(dato);//Encolamos de nuevo
            
        }
        return contador;//Devolvemos el valor obtenido de contador.
        
    }
    
    public static <E extends Comparable<E>> void insercionEspecial(ArrayCola<E> cola, E elemento)
    {
        boolean encontrado=false; 
        insercionEspecialRec(cola,elemento,encontrado);
        cola.encolar(elemento);
        invertirCola(cola);
        
    }
    //Nuestro caso base va a ser que la cola se quede vacia.
    //Mientras que la cola no este vacia, vamos a ir desencolando y comparando con el elemento introducido por parametro(por el usuario)
    //Ademas vamos a utilizar un booleano que nos diga si se ha encontrado el elemento, de esta forma conseguiremos "parar" la iteracion.
    //Si el elemento introducido y el desencolado son distintos, entonces llamaremos al metodo recursivo de nuevo para volver a desencolar.
    //Si el elemento introducido y el desencolado son iguales, tambien llamaremos al metodo recursivo. Pero en este caso, como el boolean encontrado
    //es igual a true, entonces los elementos que no hemos desencolado (y que no se encuentran por tanto en el stack) se invertiran.
    //Una vez invertidos, el metodo recursivo avanzara a la fase de plegado. En esta fase de plegado, encolaremos todos los datos del stack siempre
    //y cuando no sean iguales al elemento introducido por parametro. Por lo tanto si el elemento introducido por parametro es igual a un elemento del stack
    //este no se encolara. Pero obviamente no hemos acabado... En la lanzadera nos tocara encolar el elemento introducido y ademas invertir la cola para obtener 
    //el resultado pedido.
    public static <E extends Comparable<E>> void insercionEspecialRec(ArrayCola<E> cola, E elemento, boolean encontrado)
    {
        if(!cola.esVacia()){
            if(!encontrado)
            {
                E dato = cola.desencolar();
                if (dato.compareTo(elemento)==0) {
                    encontrado = true;
                }
                insercionEspecialRec(cola, elemento, encontrado);
                if(dato.compareTo(elemento)!=0)
                {
                   cola.encolar(dato);
                }
            }
            else{
                invertirCola(cola);                
            }
            
        }
    }
   

    public static void menu() {
        System.out.println("\t\tMENÚ");
        System.out.println("1.-Crear una cola de enteros(vacía)");
        System.out.println("2.-Almacenar elementos en la cola");
        System.out.println("3.-Mostrar Cola en Pantalla");
        System.out.println("4.-Contar elementos de una Cola(Iterativo)");
        System.out.println("5.-Contar elementos de una Cola(Recursivo)");
        System.out.println("6.-Insercion Especial");
        System.out.println("0.-Salir");
        System.out.println("Elija Opción:");
    }

    public static void main(String[] args) throws IOException {
        int opcion = 0;
        ArrayCola<Integer> ArrayCola = null;
        boolean salir = false;
        boolean colaCreada = false;
        boolean colaConContenido = false;
        do {
            menu();
            opcion = leerInt();

            switch (opcion) {

                case 1:

                    ArrayCola = new ArrayCola<Integer>();
                    System.out.println("Cola vacía creada.");
                    System.out.println("Pulsa <Intro> para continuar...");
                    System.in.read();
                    colaCreada = true;
                    break;
                    
                    
                case 2:

                    if (!colaCreada) {
                        System.out.println("\nLo sentimos, ha de crear la cola previamente (Opción 1 del menú).\n");
                    } else {
                        boolean terminar = false;
                        do {
                            System.out.println("Introduzca números enteros: ");
                            int numero = leerInt();
                            if (numero == -1) {
                                terminar = true;
                            } else {
                                ArrayCola.encolar(new Integer(numero));
                            }

                        } while (!terminar);

                        if (ArrayCola.esVacia()) {
                            System.out.println("\nPila vacía\n");
                        } else {
                            System.out.println("\nLa cola contiene la siguiente información:\n");
                            System.out.println(ArrayCola.toString());
                            colaConContenido = true;
                        }

                    }

                    break;
                    
                    
                case 3:

                    if (!colaCreada) {
                        System.out.println("\nLo sentimos, la Cola no está creada, ha de crearla previamente (Opción 1 del menú).\n");
                    } else {

                        if (!ArrayCola.esVacia()) {
                            System.out.println("\nLa cola contiene la siguiente información:\n");
                            System.out.println(ArrayCola.toString());
                        } else {
                            System.out.println("\nPila vacía.\n");
                            System.out.println("\nLo sentimos, la Cola está vacía, por favor, rellenela de elementos previamente (Opción 2 del menú).\n");
                        }
                    }

                    break;
                    
                    //En este metodo, usamos otra variable "flag" o bandera, en la que si hemos utilizado la opcion 2 se activara y permitira
                    //usar el metodo del menor al fondo. En caso contrario, mostrara un mensaje.
                    //Por otro lado, si la cola no esta vacia, mostrara los elementos en el estado inicial y en el estado posterior a utilizar
                    //el de contar elementos iterativo.
                case 4:

                    if (!colaConContenido) {
                        System.out.println("\nLo sentimos, la Cola está vacía, por favor, rellenela de elementos previamente (Opción 2 del menú).\n");
                    } else {

                        if (!ArrayCola.esVacia()) {
                            System.out.println("\nEstado inicial de la cola: \n");
                            System.out.println(ArrayCola.toString());
                            int numElementos=contarElementosIterativo(ArrayCola);
                            System.out.println("El numero de elementos que se encuentra en la cola es: \n"+numElementos);
                            System.out.println("\nEstado final de la cola: \n");
                            System.out.println(ArrayCola.toString());
                        } else {
                            System.out.println("\nLo sentimos, la Cola está vacía, por favor, rellenela de elementos previamente (Opción 2 del menú).\n");
                        }
                    }

                    break;
                    
                    //En este metodo, usamos otra variable "flag" o bandera, en la que si hemos utilizado la opcion 2 se activara y permitira
                    //usar el metodo del menor al fondo. En caso contrario, mostrara un mensaje.
                    //Por otro lado, si la cola no esta vacia, mostrara los elementos en el estado inicial y en el estado posterior a utilizar
                    //el de contar elementos recursivo.   
                case 5:

                    if (!colaConContenido) {
                        System.out.println("\nLo sentimos, la Cola está vacía, por favor, rellenela de elementos previamente (Opción 2 del menú).\n");
                    } else {

                        if (!ArrayCola.esVacia()) {
                            System.out.println("\nEstado inicial de la cola: \n");
                            System.out.println(ArrayCola.toString());
                            int numElementos=contarElementosCola(ArrayCola);
                            System.out.println("El numero de elementos que se encuentra en la cola es: \n"+numElementos);
                            System.out.println("\nEstado final de la cola: \n");
                            System.out.println(ArrayCola.toString());
                        } else {
                            System.out.println("\nLo sentimos, la Cola está vacía, por favor, rellenela de elementos previamente (Opción 2 del menú).\n");
                        }
                    }

                    break;
                    
                case 6:

                    if (!colaConContenido) {
                        System.out.println("\nLo sentimos, la Cola está vacía, por favor, rellenela de elementos previamente (Opción 2 del menú).\n");
                    } else {
                        if (!ArrayCola.esVacia()) {
                            System.out.println("\nCola Inicial: \n");
                            System.out.println(ArrayCola.toString());
                            System.out.println("\nIntroduzca un número entero: \t");
                            int entero = leerInt();
                            insercionEspecial(ArrayCola, entero);
                            System.out.println("\nEstado final de la cola: \n");
                            System.out.println(ArrayCola.toString());
                        } else {
                            System.out.println("\nLo sentimos, la Cola está vacía, por favor, rellenela de elementos previamente (Opción 2 del menú).\n");
                        }
                    }

                    break;

                case 0:

                    System.out.println("\n\t\t\t\tGracias por utilizar nuestro TAD Pila\n");
                    System.out.println("\n\t\t\t\t\tIván Barbado & Abel de Andrés\n");
                    salir = true;
                    break;

                default:
                    System.out.println("\nPor favor, introduzca una opcion correcta, del 0 al 5, ambos inclusive y según corresponda.");

                    break;
            }

        } while (!salir);
    }
}
