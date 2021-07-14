package Server;
import java.io.*;
import java.net.*;


public class ServidorTCP {
    public static void main(String[] args) throws IOException {
        System.out.println("Bienvenido al servidor");
        System.out.println("-------------------------");

        ServerSocket serverSocket = new ServerSocket(9000);
        Socket socket = serverSocket.accept();

        InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        String nombreArchivo = dataInputStream.readUTF();

        OutputStream outputStream = socket.getOutputStream();

        if(new File("src/main/java/Server/"+nombreArchivo).exists()){
            outputStream.write("Archivo ya existe".getBytes());
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1){

            }

        }else{
            FileOutputStream fileOutputStream = new FileOutputStream(new File("src/main/java/Server/"+nombreArchivo));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer,0,len);

            }
            fileOutputStream.close();
            outputStream.write("Archivo recibido por servidor".getBytes());
            System.out.println("Se recibio el archivo: "+nombreArchivo);

        }


        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
