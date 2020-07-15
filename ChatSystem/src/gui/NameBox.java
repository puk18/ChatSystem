package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NameBox implements ActionListener {
	public static javax.swing.JTextField jt;
	public static javax.swing.JLabel jl;
	JFrame jf;
	String nameText="";
		NameBox(){
		jf = new JFrame("Name Box");
			startNameBox(jf);
			jf.setLocationRelativeTo(null); 
		}
		
		public void startNameBox(JFrame jf) {
			jt = new JTextField();
			jt.addActionListener(action);
			jl =new JLabel("Enter the Name:");
			jt.setBounds(200,250,150,50);
			jl.setBounds(100,250,100,50);
			jl.setForeground(Color.white);
			Font f = new Font("TimesRoman",Font.BOLD,13);
			jl.setFont(f);
			JButton enter = new JButton("Enter");
			enter.setBounds(375,250,70,50);
			enter.setBackground(Color.white);
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jf.add(jl);
			jf.add(jt);
			jf.add(enter);
			enter.addActionListener(this);
			jf.setSize(600,600);
			jf.getContentPane().setBackground(Color.black);
			jf.setLayout(null);
			jf.setVisible(true);
			
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NameBox name = new NameBox();

	}
	
	Action action = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {nameText = jt.getText();
		if(nameText!=null) {
			new ChatBox(nameText);
//jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);	
}
		jt.setText("");
			//textf = jt.getText();
		//jf.setVisible(false);
		jf.dispose();
	    }
	    	//Sender.sendMessage(port,textf);	    }
	};

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		nameText = jt.getText();
		if(nameText!=null) {
			new ChatBox(nameText);
			//super.dispose();
		jf.dispose();	
		}
		jt.setText("");
		
		
	}

}

