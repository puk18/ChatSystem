package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;

public class ChatBox implements ActionListener {
	public static javax.swing.JTextField jt;
	public static javax.swing.JTextArea ja;
	String textf = ""; 
	static int port = 1313;
	static int filePort=1314;
	static String ReceivedMessage = "";
	static MessageReceiver receiver = new MessageReceiver();
	static FileReceiver fileReceiver=new FileReceiver();
	String name=null;
	public JButton chat;
	public JButton attach;
	
	ChatBox(String nameText){
		
		JFrame jf = new JFrame("Group Messenger");
		startBox(jf,nameText);
		jf.setLocationRelativeTo(null); 
		name=nameText;
	}


	public  void startBox(JFrame jf, String nameText) {
		
		//ja = new JTextArea();
		//ja.setBounds(10,10,535,470);
		//ja.setEditable(false);
		jt = new JTextField();
		jt.setBounds(10,500,300,40);
		jt.addActionListener(action);
		chat = new JButton("Send");
		chat.setBounds(325,500,100,40);
		
		attach = new JButton("Attach");
		attach.setBounds(450,500,100,40);
                JPanel middlePanel = new JPanel ();
    middlePanel.setBorder ( new TitledBorder ( new EtchedBorder (), "Chat Box" ) );

   // jf.getContentPane().setBackground(Color.black);

     ja = new JTextArea ( 28, 50 );
    ja.setEditable ( false ); // set textArea non-editable
    JScrollPane scroll = new JScrollPane ( ja );
    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

    //Add Textarea in to middle panel
    middlePanel.add ( scroll );

    // My code
  //  JFrame frame = new JFrame ();
    jf.add ( middlePanel );
    jf.pack ();
    jf.setLocationRelativeTo ( null );
  //  frame.setVisible ( true );
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//jf.add(ja);
		jf.add(chat);
		//jf.getContentPane().add(sp);
		jf.add(jt);
		jf.add(attach);
		attach.addActionListener(this);
		chat.addActionListener(this);
		//jf.add(sp);
		jf.setSize(600,600);
		jf.setLayout(null);
		jf.setVisible(true);
		receiver.startReceiver(ja);
		fileReceiver.startReceiver(ja);
	
		//ReceivedMessage = Server.receive(ja);
	}
	/*public static void main(String[] args) {
		
		//new ChatBox();
		
		
		server.startServer(ja);
	}*/

	Action action = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
			textf = jt.getText();

	    	MessageSender.sendMessage(port,name+":"+textf);	
	    	  jt.setText("");
    	 }
	};

	///JTextField textField = new JTextField(10);
	//textField.addActionListener( action );
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == chat)
		{	System.out.println("chat");
			textf = jt.getText();
			if(textf!=null) {
				MessageSender.sendMessage(port,name+":"+textf);
			//	ja.append(textf+"\n");
				  jt.setText("");
			}
		}
		if(e.getSource() == attach)
		{	
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

			int returnValue = jfc.showOpenDialog(null);
			// int returnValue = jfc.showSaveDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = jfc.getSelectedFile();
				System.out.println(selectedFile.getAbsolutePath());
				FileSender.sendFile(filePort,selectedFile.getAbsolutePath());
			}
		}
	}

	

}