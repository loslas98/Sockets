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
        DataInputStream in = new DataInputStream(inputStream);
        String nombreArchivo = in.readUTF();

        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream out = new DataOutputStream(outputStream);

        if(new File("src/main/java/Server/"+nombreArchivo).exists()){
            out.writeUTF("Archivo ya existe");
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) != -1){

            }

        }else{
            FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/Server/"+nombreArchivo);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer,0,len);

            }
            fileOutputStream.close();
            out.writeUTF("Archivo recibido por el servidor");
            System.out.println("Se recibio el archivo: "+nombreArchivo);

        }


        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
