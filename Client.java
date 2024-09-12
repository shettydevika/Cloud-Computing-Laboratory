import java.io.*;
import java.net.*;
import javax.crypto.SecretKey;

public class Client {
	public static void main(String args[]){
		try {
			// Create Client socket to connect to server
			Socket socket = new ServerSocket("localhost", 8080);
			
			// Get the predefined AES key
			SecretKey key = AESEncryptionUtil.getAESKey();
			
			// Encrypt the message
			String message = "Hello Server.............";
			String encryptedMessage = AESEncryptionUtil.encrypt(message, key);
			System.out.println("Encrypted Message" + encryptedMessage);
			
			//Send encrypted message to the server
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			out.println(encryptedMessage);
			
			// Receive response from server
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("Server Response: " + in.readLine());
			
			// Close Connection
			in.close();
			out.close();
			socket.close();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

