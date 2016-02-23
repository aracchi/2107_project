import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class AdvancedGroupChatApptest extends JFrame {
	MulticastSocket multicastSocket = null;
	InetAddress multicastGroup = null;
	
	private JPanel contentPane;
	private JTextField usernameTextField;
	private JTextField friendtextField;
	private JTextField grouptextField;
	private JTextField displayCurrentGrouptextField;
	private JTextField postMessagetextField;
	
	private String wellKnownIP = "235.1.1.1";
	private int wellKnownPort = 1199;

	MulticastSocket multicastSocketMain = null;
	InetAddress multicastGroupMain = null;

	private ArrayList<String> listOfMyFriends = new ArrayList<String>();
	private ArrayList<String> listOfMyFriendsPort = new ArrayList<String>();

	private ArrayList<String> listOfMyGroups = new ArrayList<String>();
	private ArrayList<String> listOfMyGroupsIP = new ArrayList<String>();

	private String myUserName = "";
	private String myTempUsername = "";
	private int myPort = 0;
	private int generatePort =0;
	Timer timer ;
	
	JButton sendMessageButton, leaveCurrentGroupButton;
	
	JTextArea messageListtextArea;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdvancedGroupChatApptest frame = new AdvancedGroupChatApptest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdvancedGroupChatApptest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(10, 11, 78, 14);
		contentPane.add(lblUserName);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(86, 8, 86, 20);
		contentPane.add(usernameTextField);
		usernameTextField.setColumns(10)
		
		/*JLabel lblFriend = new JLabel("Friend");
		lblFriend.setBounds(10, 36, 46, 14);
		contentPane.add(lblFriend);
		
		JLabel lblGroup = new JLabel("Group");
		lblGroup.setBounds(10, 61, 46, 14);
		contentPane.add(lblGroup);*/
		
		;
		
		/*friendtextField = new JTextField();
		friendtextField.setBounds(86, 33, 86, 20);
		contentPane.add(friendtextField);
		friendtextField.setColumns(10);
		
		grouptextField = new JTextField();
		grouptextField.setBounds(86, 58, 86, 20);
		contentPane.add(grouptextField);
		grouptextField.setColumns(10);*/
		
		JButton registerFriendButton = new JButton("Register");
		registerFriendButton.setBounds(189, 7, 89, 23);
		contentPane.add(registerFriendButton);
		registerFriendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			//Read the string command from the user
				System.out.println("CLICKED BUTTON");
				
				userValidation(usernameTextField.getText().toString());
				//createUsername(username,generatePort);
			}
		});
		
		/*JButton addFriendButton = new JButton("Add");
		addFriendButton.setBounds(189, 32, 89, 23);
		contentPane.add(addFriendButton);
		
		JButton addGroupButton = new JButton("Add");
		addGroupButton.setBounds(189, 57, 89, 23);
		contentPane.add(addGroupButton);
		
		JButton deleteFriendButton = new JButton("Delete");
		deleteFriendButton.setBounds(291, 32, 89, 23);
		contentPane.add(deleteFriendButton);
		
		JButton editGroupButton = new JButton("Edit");
		editGroupButton.setBounds(288, 57, 89, 23);
		contentPane.add(editGroupButton);
		
		JLabel lblFriendList = new JLabel("Friend List");
		lblFriendList.setBounds(10, 110, 78, 14);
		contentPane.add(lblFriendList);
		
		JTextArea friendListTextArea = new JTextArea();
		friendListTextArea.setBounds(10, 130, 65, 151);
		contentPane.add(friendListTextArea);
		
		JLabel lblGroupList = new JLabel("Group List");
		lblGroupList.setBounds(102, 110, 70, 14);
		contentPane.add(lblGroupList);
		
		JTextArea groupListTextArea = new JTextArea();
		groupListTextArea.setBounds(97, 130, 65, 151);
		contentPane.add(groupListTextArea);
		
		displayCurrentGrouptextField = new JTextField();
		displayCurrentGrouptextField.setBounds(189, 107, 86, 20);
		contentPane.add(displayCurrentGrouptextField);
		displayCurrentGrouptextField.setColumns(10);
		
		JButton joinCurrentGroupButton = new JButton("Join");
		joinCurrentGroupButton.setBounds(288, 106, 89, 23);
		contentPane.add(joinCurrentGroupButton);
		
		leaveCurrentGroupButton = new JButton("Leave");
		leaveCurrentGroupButton.setBounds(385, 106, 89, 23);
		contentPane.add(leaveCurrentGroupButton);*/
		
		messageListtextArea = new JTextArea();
		messageListtextArea.setBounds(189, 130, 285, 151);
		contentPane.add(messageListtextArea);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setBounds(10, 292, 46, 14);
		contentPane.add(lblMessage);
		
		postMessagetextField = new JTextField();
		postMessagetextField.setBounds(66, 289, 276, 20);
		contentPane.add(postMessagetextField);
		postMessagetextField.setColumns(10);
		
		sendMessageButton = new JButton("Send");
		sendMessageButton.setBounds(362, 288, 89, 23);
		contentPane.add(sendMessageButton);
		
		
		establishMainConnection();
		
	}
	public void createUsername(String username, int port){
		myUserName = username;
		myPort = port;
		messageListtextArea.append(username + " has joined the chat \n");
	}
	
	public void mainValidateAction(String msg)
	{
		String[] parts = msg.split("/");//splitting by "/"
		switch(parts[0]){
			case "U?": // MSG "U? {{username}} {{port}}"
				checkDuplicateUserOrPort(parts[1],Integer.valueOf(parts[2]));
	
				break;
			case "U!": // MSG "U! {{username}} {{port}}"
				System.out.println("OMG WE FOUND U!");
				//if myusername = username then call the dialog
				String tempUsername = parts[1];
				if (myTempUsername.equals(tempUsername) && myUserName.equals("")){
					System.out.println("Timer is being Canceled");
					timer.cancel();
					
	
					if(parts[4].equals("1")&&parts[3].equals("0"))//port error
					{
						//generate new port and ask everybody again.
						userValidation(myTempUsername);
					}
					else{
						//notify user to enter new username
						JOptionPane.showMessageDialog(null, "Please enter a username!");
					}
					
				}
				break;
		}
	}
	public void userValidation(String username)
	{
		Random r = new Random();
		generatePort = r.nextInt(60000 - 2000) + 2000;
		myTempUsername = username;
		if (myTempUsername.equals("")){
			JOptionPane.showMessageDialog(null, "Please enter a username!");
		}else{
			String checkUsername = "U?/"+myTempUsername+"/"+String.valueOf(generatePort)+"/";
			performSendToMain(checkUsername);
			timer = new Timer();
			timer.schedule(new TimerTask() {
				  @Override
				  public void run() {
					 System.out.println("Timer triggered.");
					 createUsername(myTempUsername, generatePort);
				  }
				}, 5000);
		}

	}
	public void checkDuplicateUserOrPort(String username,int port)
	{
		int usernameFail = 0;
		int portFail = 0;
		if(myUserName.equals(username))
		{
			usernameFail = 1;
			
		}
		if(myPort==port)
		{
			portFail = 1;
		}
		if((usernameFail+portFail)>0)//there is a duplicate
		{
			String msg = "U!/"+username+"/"+port+"/"+Integer.toString(usernameFail)+"/"+Integer.toString(portFail);
			performSendToMain(msg);
		}
	}
	public void performSendToMain(String msg)
	{
		System.out.println("sending a message : "+msg);
		try{
			byte[] buf = msg.getBytes();
			DatagramPacket dgpSend = new DatagramPacket(buf, buf.length, multicastGroupMain, wellKnownPort);
			multicastSocketMain.send(dgpSend);;
			/*multicastSocketMain.setSoTimeout(5000);
		} catch (SocketTimeoutException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			if (usernameTextField==null){
				JOptionPane.showMessageDialog(null, "Please enter a new username!",
			            "Message Dialog", JOptionPane.PLAIN_MESSAGE);
			}
			createUsername(username,generatePort);*/
		}
		catch (IOException ex ){
			ex.printStackTrace();
		}
	}
	public void establishMainConnection()
	{
		System.out.println("Establishing Main Connection");
		try {
			multicastGroupMain = InetAddress.getByName(wellKnownIP);
			multicastSocketMain = new MulticastSocket(wellKnownPort);
			//join
			multicastSocketMain.joinGroup(multicastGroupMain);
			
			new Thread(new Runnable(){
				
				@Override
				public void run(){
					byte buf1[] = new byte[1000];
					DatagramPacket dgpReceived = new DatagramPacket(buf1, buf1.length);
					
					while (true){
						try{
							multicastSocketMain.receive(dgpReceived);
							byte[] receivedData = dgpReceived.getData();
							int length = dgpReceived.getLength();
							
							String msg = new String(receivedData,0,length);
							debugMsg(msg);
							System.out.println("Message Recieved"+msg);
							mainValidateAction(msg);
							
						}catch(IOException ex)
						{
							ex.printStackTrace();
						}
					}	
				}	
			}).start(); 	
		}catch (IOException ex){
			ex.printStackTrace();
		}
	}
	public void debugMsg(String msg)//Purpose is to help you view msg easier by appending it to the chat group/s msg
	{
		System.out.println("msg");
		messageListtextArea.append("Console Msg "+msg+"\n");
	}
}
