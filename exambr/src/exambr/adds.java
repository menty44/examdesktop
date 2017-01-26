package exambr;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


class addition extends JFrame implements ActionListener
{
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst =null;

JPanel v1,v2,b,i,all,v_1,v_2;
JLabel val1,val2,tot;
JTextField val_1,val_2,itr;
JButton cal,clr;

int sum=0,total_sum=0,x=0,y=0;

public addition(){
    
setTitle("Addition of Numbers 1.0");
setSize(350,250);
		setLocation(200,200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
v_1=new JPanel();
v_2=new JPanel();
b =new JPanel();
i = new JPanel();

	all=new JPanel(new FlowLayout(FlowLayout.CENTER,1000,20));


val1=new JLabel("Enter First Value  ");
val2=new JLabel("Enter Second Value ");
val_1=new JTextField(10);
val_2=new JTextField(10);
tot=new JLabel("The Total Sum is ");
itr=new JTextField(10);
	itr.setEditable(false);

	cal=new JButton("Calculate");
		clr=new JButton("Clear");
	}


	public void init()
	{
		setLayout(new BorderLayout());
v_1.add(val1);
v_1.add(val_1);
v_2.add(val2);
v_2.add(val_2);
		b.add(cal);
		b.add(clr);
i.add(tot);
i.add(itr);

all.add(v_1);
all.add(v_2);
all.add(i);


         add(b,BorderLayout.SOUTH);
		add(all,BorderLayout.CENTER);
		cal.addActionListener(this);
		clr.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			if(cal==e.getSource())
			{
x=Integer.parseInt(val_1.getText());
y=Integer.parseInt(val_2.getText());
total_sum=(x + y);
itr.setText(Integer.toString(total_sum));
			}
			else if(clr==e.getSource())
			{
				val_1.setText("");
				val_2.setText("");
				itr.setText("");
			}
		}
		catch(NumberFormatException n)
		{
			val_1.setText("0");
			val_2.setText("0");
			itr.setText("0");
		}
	}
}



class adds
{
	public static void main(String args[]){
       addition values =new addition();

	    values.init();
	    values.setVisible(true);
        values.setResizable(false);
        values.setLocation(200,200);
}
}


