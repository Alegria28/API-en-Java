
/*cspell:disable */
import java.net.*;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/* Importamos los paquetes necesarios para trabajar con gráficos */
import javax.swing.JFrame; // Para poder crear una ventana
import javax.swing.JTextArea;

import java.awt.Image; // Para poder crear una variable de tipo Image
import java.awt.image.BufferedImage; // Para poder usar la clase derivada de Image
import java.awt.Color; // Para poder crear objetos de tipo Color
import java.awt.Font; // Para usar otros fonts
import javax.swing.JButton; // Para manejar botones
import javax.swing.JTextField; // Para manejar textFields
import java.awt.event.*; // Para manejar eventos

// Cuando creamos un objeto de tipo URL, este tiene una advertencia sobre el constructor ya que esta deprecado, para evitar esta advertencia quitamos las advertencias de tipo "deprecation" en esta clase
@SuppressWarnings("deprecation")
public class App {

    // Creamos un botón para obtener nuestra IP
    private static JButton botonIP = new JButton("Obtener IP");
    // Creamos un botón para obtener el clima
    private static JButton botonClima = new JButton("Obtener clima");
    // Creamos un botón para obtener el clima
    private static JButton botonMandarPOST = new JButton("Mandar POST");

    // Creamos el textField para mostrar la IP y sus coordenadas y tamaño
    private static JTextField mostrarIPtTextField = new JTextField();
    // Creamos el textField para obtener la ciudad
    private static JTextField obtenerCiudadTextField = new JTextField();
    // Creamos el textField para mostrar el resultado del clima
    private static JTextField mostrarClimaTextField = new JTextField();
    // Creamos el textField para obtener el POST
    private static JTextArea obtenerPOSTTextArea = new JTextArea();
    // Creamos el textField para mostrar el POST
    private static JTextField mostrarPOSTTextField = new JTextField();

    public static void main(String[] args) throws Exception {

        /*-------------------Ventana-------------------*/

        // Creamos nuestra ventana
        JFrame ventana = new JFrame("API");

        // Una vez que se cierre la ventana ¿Que sucede? En este caso se cierra el
        // programa por completo
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Establecemos un tamaño para nuestra ventana
        ventana.setSize(500, 500);

        // Creamos nuestro icono vacio
        Image icono = new BufferedImage // Como BufferedImage es una subclase de Image, podemos crear un objeto de este
                                        // tipo, el cual nos ayuda a manejar imágenes en Java
        (1, 1, // Tamaño de la imagen de 1px/1px
                BufferedImage.TYPE_INT_ARGB_PRE); // La imagen tendrá 8bits RGBA

        // Ponemos la nueva imagen (que es tan pequeña que tiene el efecto de parecer
        // que no tenemos icono)
        ventana.setIconImage(icono);
        // Centramos nuestra ventana
        ventana.setLocationRelativeTo(null);
        // Desactivamos la gestión automática del diseño
        ventana.setLayout(null);

        /*-------------------Botones-------------------*/

        // Sus coordenadas y tamaño
        botonIP.setBounds(10, 20, 110, 50);
        // Color de nuestro botón
        botonIP.setBackground(new Color(59, 89, 182));
        // Diseño para nuestro botón (quita una rectángulo que aparece alrededor de la
        // palabra)
        botonIP.setFocusPainted(false);
        // Cambiamos la tipografía y el tamaño de esta
        botonIP.setFont(new Font("Tahoma", Font.BOLD, 12));
        // En caso de que se haga click en este boton, realizamos la función de
        // obtenerIp
        botonIP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obtenerIp();

            }
        });

        // Sus coordenadas y tamaño
        botonClima.setBounds(10, 100, 130, 50);
        // Color de nuestro botón
        botonClima.setBackground(new Color(59, 89, 182));
        // Diseño para nuestro botón (quita una rectángulo que aparece alrededor de la
        // palabra)
        botonClima.setFocusPainted(false);
        // Cambiamos la tipografía y el tamaño de esta
        botonClima.setFont(new Font("Tahoma", Font.BOLD, 12));
        // En caso de que se haga click en este boton, realizamos la función de
        // obtenerIp
        botonClima.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamamos a nuestra función mandando lo que tenemos en nuestro textField
                obtenerClima(obtenerCiudadTextField.getText());
            }
        });

        // Sus coordenadas y tamaño
        botonMandarPOST.setBounds(10, 280, 130, 50);
        // Color de nuestro botón
        botonMandarPOST.setBackground(new Color(59, 89, 182));
        // Diseño para nuestro botón (quita una rectángulo que aparece alrededor de la
        // palabra)
        botonMandarPOST.setFocusPainted(false);
        // Cambiamos la tipografía y el tamaño de esta
        botonMandarPOST.setFont(new Font("Tahoma", Font.BOLD, 12));
        // En caso de que se haga click en este boton, realizamos la función de
        // obtenerIp
        botonMandarPOST.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamamos a nuestra función mandando lo que tenemos en nuestro textField
                metodoPost(obtenerPOSTTextArea.getText());
            }
        });

        // Agregamos los botones a nuestra ventana
        ventana.add(botonIP);
        ventana.add(botonClima);
        ventana.add(botonMandarPOST);

        /*-----------------TextFields------------------*/

        // Coordenadas y posición del textField
        mostrarIPtTextField.setBounds(150, 30, 200, 30);
        // Ya que solo vamos a usarlo para mostrar información, cambiamos su propiedad
        mostrarIPtTextField.setEditable(false);
        // Para que no se muestre el cursor en el textField en ningún momento
        mostrarIPtTextField.setFocusable(false);
        // Para que muestre el texto de forma centrada
        mostrarIPtTextField.setHorizontalAlignment(JTextField.CENTER);

        // Coordenadas y posición del textField
        obtenerCiudadTextField.setBounds(150, 90, 200, 30);

        // Coordenadas y posición del textField
        mostrarClimaTextField.setBounds(150, 140, 200, 30);
        // Ya que solo vamos a usarlo para mostrar información, cambiamos su propiedad
        mostrarClimaTextField.setEditable(false);
        // Para que no se muestre el cursor en el textField en ningún momento
        mostrarClimaTextField.setFocusable(false);
        // Para que muestre el texto de forma centrada
        mostrarClimaTextField.setHorizontalAlignment(JTextField.CENTER);

        // Coordenadas y posición del textField
        obtenerPOSTTextArea.setBounds(150, 180, 300,130);
        // Para que el texto quede ordenado bonito
        obtenerPOSTTextArea.setLineWrap(true);
        obtenerPOSTTextArea.setWrapStyleWord(true);

        // Coordenadas y posición del textField
        mostrarPOSTTextField.setBounds(150, 320, 300, 130);
        // Ya que solo vamos a usarlo para mostrar información, cambiamos su propiedad
        mostrarPOSTTextField.setEditable(false);
        // Para que no se muestre el cursor en el textField en ningún momento
        mostrarPOSTTextField.setFocusable(false);
        // Para que muestre el texto de forma centrada
        mostrarPOSTTextField.setHorizontalAlignment(JTextField.CENTER);

        // Agregamos los textField a nuestra ventana
        ventana.add(mostrarIPtTextField);
        ventana.add(obtenerCiudadTextField);
        ventana.add(mostrarClimaTextField);
        ventana.add(obtenerPOSTTextArea);
        ventana.add(mostrarPOSTTextField);

        // Mostramos nuestra ventana
        ventana.setVisible(true);

    }

    public static void obtenerIp() {

        // Dado que es una parte de código que podría llegar a dar error, lo ponemos en
        // un try-catch
        try {

            // Creamos una URL para la primera API (La cual nos regresa nuestra IP)
            URL url = new URL("https://api.ipify.org?format=json");
            // Creamos un objeto de conexión para poder conectarnos a la URL
            // Hacemos un cast para el tipo de dato HttpURLConnection mientras que abrimos
            // la conexión de nuestra URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Solicitamos la conexión a traves de get
            connection.setRequestMethod("GET");

            // Ahora si nos conectamos
            connection.connect();

            // Verificamos de que la conexión haya sido correcta
            int responseCode = connection.getResponseCode();

            // Si el código es distinto de 200, significa que algo estuvo mal
            if (responseCode != 200) {
                throw new RuntimeException("Ocurrió un error: " + responseCode);
            } else {
                // Creamos una variable de tipo StringBuilder para poder almacenar la
                // información recibida de la API
                StringBuilder informacionRecibida = new StringBuilder();
                // Este Scanner va a leer el flujo de datos que recibimos de la API
                Scanner lectura = new Scanner(url.openStream());

                // Vamos a estar leyendo de forma constante el flujo de datos, hasta que este
                // termine
                while (lectura.hasNext()) {
                    // Mientras haya información, lo agregamos a nuestra variable StringBuilder
                    informacionRecibida.append(lectura.nextLine());
                }

                // Tras haber terminado, cerramos el Scanner
                lectura.close();

                // Mostramos la información obtenida en nuestro textField
                mostrarIPtTextField.setText(soloIp(informacionRecibida));

            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static String soloIp(StringBuilder cadena) {
        // Variable para almacenar la IP
        StringBuilder temporal = new StringBuilder();

        // Recorremos toda la cadena para solo obtener la Ip
        for (int i = 0; i < cadena.length(); i++) {
            // Obtenemos el carácter i de nuestra cadena
            char x = cadena.charAt(i);

            // En dado caso de que este sea un carácter valido, lo agregamos a nuestra
            // cadena
            if (Character.isDigit(x) || x == '.') {
                temporal.append(x);
            }
        }

        // Convertimos nuestra variable StringBuilder a String
        String retornar = temporal.toString();

        // Regresamos nuestra variable
        return retornar;
    }

    public static void obtenerClima(String ciudad) {
        // Dado que es una parte de código que podría llegar a dar error, lo ponemos en
        // un try-catch
        try {

            String nuevaCiudad = verificarCiudad(ciudad);

            // Creamos nuestra variable String para el URL
            String variableURL = "https://wttr.in/" + nuevaCiudad + "?format=3";

            // Creamos una URL para la primera API (La cual nos regresa nuestra IP)
            URL url = new URL(variableURL);
            // Creamos un objeto de conexión para poder conectarnos a la URL
            // Hacemos un cast para el tipo de dato HttpURLConnection mientras que abrimos
            // la conexión de nuestra URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Solicitamos la conexión a traves de get
            connection.setRequestMethod("GET");

            // Ahora si nos conectamos
            connection.connect();

            // Verificamos de que la conexión haya sido correcta
            int responseCode = connection.getResponseCode();

            // Si el código es distinto de 200, significa que algo estuvo mal
            if (responseCode != 200) {
                throw new RuntimeException("Ocurrió un error: " + responseCode);
            } else {
                // Creamos una variable de tipo StringBuilder para poder almacenar la
                // información recibida de la API
                StringBuilder informacionRecibida = new StringBuilder();
                // Este Scanner va a leer el flujo de datos que recibimos de la API
                Scanner lectura = new Scanner(url.openStream());

                // Vamos a estar leyendo de forma constante el flujo de datos, hasta que este
                // termine
                while (lectura.hasNext()) {
                    // Mientras haya información, lo agregamos a nuestra variable StringBuilder
                    informacionRecibida.append(lectura.nextLine());
                }

                // Tras haber terminado, cerramos el Scanner
                lectura.close();

                // Mostramos la información obtenida en nuestro textField
                mostrarClimaTextField.setText(informacionRecibida.toString());

                // Tras mostrar el clima de la ciudad, vaciamos el textField
                obtenerCiudadTextField.setText("");

            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public static String verificarCiudad(String ciudad) {

        // En otra variable String, almacenamos la misma cadena pero remplazando los
        // espacios por "%20" (para que el URL sea correcto)
        String replacedCiudad = ciudad.replaceAll("\s+", "%20");

        // Regresamos nuestra variable
        return replacedCiudad;
    }

    public static void metodoPost(String mensaje) {

        obtenerPOSTTextArea.setText("");

        // Ya que es una acción que puede dar error, lo encerramos en un try-catch
        try {
            // Creamos variable URL para la API
            URL url = new URL("https://gogodev.net/ejercicios/testjava.php");

            // Preparamos la información que vamos a enviar
            Map<String, Object> parametros = new LinkedHashMap<>();

            parametros.put("elm", mensaje);

            // Empezamos a codificar los datos a enviar
            StringBuilder informacionPost = new StringBuilder();

            for (Map.Entry<String, Object> param : parametros.entrySet()) {

                if (informacionPost.length() != 0) {
                    informacionPost.append("&");
                }

                informacionPost.append(URLEncoder.encode(param.getKey(), "UTF-8"));

                informacionPost.append("=");
                informacionPost.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }

            byte[] postBytes = informacionPost.toString().getBytes("UTF-8");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", String.valueOf(postBytes.length));
            connection.setDoOutput(true);
            connection.getOutputStream().write(postBytes);

            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

            StringBuilder cadenaRecibidaPost = new StringBuilder();

            for (int c = in.read(); c != -1; c = in.read()) {
                cadenaRecibidaPost.append((char)c);
            }

            // Mostramos lo recibido
            mostrarPOSTTextField.setText(cadenaRecibidaPost.toString());

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}