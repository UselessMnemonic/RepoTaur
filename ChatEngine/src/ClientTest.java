import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientTest implements Chatable<String>{

	String name;
	
	public ClientTest(String IP, int port, String name) throws UnknownHostException, IOException
	{
		this.name = name;
		client = ChatService.getChatClient(this, IP, port);
	}
	
	@Override
	public void onMessage(String message) {
		System.out.println(message);
	}
	
	private ChatClient<String> client;
	
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Type the IP address: ");
		String IP = in.nextLine();
		System.out.println("Type the port: ");
		int port = in.nextInt();
		System.out.println("Type a name: ");
		String name = in.nextLine();
		
		try {
			ClientTest t = new ClientTest(IP, port, name);
			System.out.println("Server Started!");
			while(true)
			{
				System.out.println("Type a Message: ");
				String message = in.nextLine();
				t.send(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
			in.close();
		}
	}

	private void send(String message) {

		client.sendMeesage(name + " sent " + message);
		
	}

}