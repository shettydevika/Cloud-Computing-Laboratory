import java.io.*;
import java.net.*;
import javax.crypto.SecretKey;

public class Server {
	public static void main(String args[])	{
		try {
			// Create server socket
			ServerSocket serversocket = new ServerSocket(8080);
 			System.out.println("Server is running....");
			
			while(true) {
				// Accept client connection
				Socket socket = serverSocket.accept();
				System.out.println("Client Connected. ");
				
				// Read the encrypted message from client
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String encryptedMessage = in.readLine();
				System.out.println("Received Encrypted Message: " + encryptedMessage);
				
				// Get predefined AES key
				SecretKey key = AESEncryptionUtil.getAESkey();
				
				// Decrypt the message
				try {
					String decryptedMessage = AESEncryptionUtil.decrypt(encryptedMessage, key);
					System.out.println("Decrypted Message" + decryptedMessage);
					
					// Send acknowlegement to client
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					out.println("Message received and decrypted successfully. ");
				
				} catch(Exception e) {
					System.out.println("Failed to decrypt message. ");
					PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
					out.println("Only encrypted messages are processsed. ");
				} 
				
				// Close Connection 
				socket.close();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}




