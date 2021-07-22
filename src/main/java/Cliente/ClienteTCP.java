package Cliente;
import java.io.*;
import java.net.*;
import java.util.Scanner;



public class ClienteTCP {
    public static void main(String[] args) throws Exception {
        System.out.println("Bienvenido al cliente");
        System.out.println("------------------------");
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"),9000);

        System.out.println("Ingrese el nombre del archivo a enviar: ");
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream out = new DataOutputStream(outputStream);
        Scanner teclado = new Scanner(System.in);
        String nombreArchivo = teclado.nextLine();
        try {
            out.writeUTF(nombreArchivo);
            FileInputStream fileInputStream = new FileInputStream("src/main/java/Cliente/"+nombreArchivo);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fileInputStream.read(buffer)) != -1){
                out.write(buffer, 0, len);
            }

            socket.shutdownOutput();

            InputStream inputStream = socket.getInputStream();
            DataInputStream in = new DataInputStream(inputStream);
            System.out.println(in.readUTF());

        }catch (FileNotFoundException e){
            System.out.println("El archivo no existe");

        }catch (SocketException e){
            e.printStackTrace();
        }



        outputStream.close();
        socket.close();




    }
}
