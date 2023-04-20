import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import java.lang.Exception; 
import java.util.Timer;
import java.util.TimerTask; 
class login extends JFrame implements ActionListener  
{  
    JButton b1;  
    JPanel np;  
    JLabel uL, pL;  
    final JTextField  textField1, textField2;  
    login()  
    {             
    	uL = new JLabel();  
        uL.setText("    Username :");      
        textField1 = new JTextField(15);      
        pL = new JLabel();  
        pL.setText("    Password :");        
        textField2 = new JPasswordField(8);     
        b1 = new JButton("   SUBMIT   ");  
        np = new JPanel(new GridLayout(3, 1));  
        np.add(uL);     
        np.add(textField1);  
        np.add(pL);    
        np.add(textField2);   
        np.add(b1);  
       // newPanel.setBackground(Color.blue);
        add(np, BorderLayout.CENTER);  
        b1.addActionListener(this);    
        setTitle("Online Exam Login Form ");         
    }   
    public void actionPerformed(ActionEvent ae)     
    {  
        String userValue = textField1.getText();        
        String passValue = textField2.getText();       
        if(!passValue.equals(""))
            new OnlineTestBegin(userValue); 
        else{
            textField2.setText("Enter Password");
            actionPerformed(ae);
        }
    }     
}  
class OnlineTestBegin extends JFrame implements ActionListener  
{  
    JLabel l;  
    JLabel l1,Note,Note1;  
    JRadioButton jb[]=new JRadioButton[6];  
    JButton b1,b2,log;  
    ButtonGroup bg;  
    int count=0,current=0,x=1,y=1,now=0;  
    int m[]=new int[10];  
    Timer timer = new Timer();  
    OnlineTestBegin(String s)  
    {      
        super(s); 
        l=new JLabel();
        l1 = new JLabel();  
        add(l);
        add(l1);  
        bg=new ButtonGroup();  
        for(int i=0;i<5;i++)  
        {  
            jb[i]=new JRadioButton();     
            add(jb[i]);  
            bg.add(jb[i]);  
        }  
        b1=new JButton("Save and Next");  
        b2=new JButton("Mark for Review");  
        b1.addActionListener(this);  
        b2.addActionListener(this);  
        add(b1);add(b2);  
        set();  
        l.setBounds(30,40,450,20);
        l1.setBounds(20,20,450,20);
        jb[0].setBounds(50,80,100,20);  
        jb[1].setBounds(50,110,100,20);  
        jb[2].setBounds(50,140,100,20);  
        jb[3].setBounds(50,170,100,20);  
        b1.setBounds(50,240,140,30);  
        b2.setBounds(250,240,150,30); 
        Note=new JLabel();
        Note.setText("Exam will close after the time's out,");
        Note1=new JLabel();
        Note1.setText("Please submit before the time goes down.");
        Note.setBounds(50,260,200,30);
        Note1.setBounds(50,280,200,30);
        add(Note);add(Note1);
        set();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setLayout(null);  
        setLocation(250,100);  
        setVisible(true);  
        setSize(600,350);     
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 300;
            public void run() {  
                l1.setText("Time left: " + i);
                i--;   
                if (i < 0) {
                    timer.cancel();
                    l1.setText("Time Out");
                    System.exit(1);
                    
                } 
            }
        }, 0, 1000);        
    }  
    public void actionPerformed(ActionEvent e)  
    {          
        if(e.getSource()==b1)  
        {  
            if(check())  
                count=count+1;  
            current++;  
            set();    
            if(current==9)  
            {  
                b1.setEnabled(false);  
                b2.setText("Result");  
            }  
        }  
        if(e.getActionCommand().equals("Mark for Review"))  
        {  
            JButton bk=new JButton("Review"+x);  
            bk.setBounds(480,20+30*x,100,30);  
            add(bk);  
            bk.addActionListener(this);  
            m[x]=current;  
            x++;  
            current++;  
            set();    
            if(current==9)  
                b2.setText("Result");  
            setVisible(false);  
            setVisible(true);  
        }  
        for(int i=0,y=1;i<x;i++,y++)  
        {  
        if(e.getActionCommand().equals("Review"+y))  
        {  
            if(check())  
                count=count+1;  
            now=current;  
            current=m[y];  
            set();  
            ((JButton)e.getSource()).setEnabled(false);  
            current=now;  
        }  
        }      
        if(e.getActionCommand().equals("Result"))  
        {  
            if(check())  
                count=count+1;  
            current++;  
            JOptionPane.showMessageDialog(this,"Score ="+count);  
            System.exit(0);  
        }  
    }  
    void set()  
    {  
        jb[4].setSelected(true);  
        if(current==0)  
        {  
            l.setText("Que1: First question");  
            jb[0].setText("a");jb[1].setText("b");jb[2].setText("c");jb[3].setText("d");   
        }  
        if(current==1)  
        {  
            l.setText("Que2: Second question");  
            jb[0].setText("a");jb[1].setText("b");jb[2].setText("c");jb[3].setText("d");  
        }  
        if(current==2)  
        {  
            l.setText("Que3: Third question");  
            jb[0].setText("a");jb[1].setText("b");jb[2].setText("c");jb[3].setText("d");  
        }  
        if(current==3)  
        {  
        	l.setText("Que4: Fourth question");  
            jb[0].setText("a");jb[1].setText("b");jb[2].setText("c");jb[3].setText("d");  
        }  
        if(current==4)  
        {  
        	l.setText("Que5: Fifth question");  
            jb[0].setText("a");jb[1].setText("b");jb[2].setText("c");jb[3].setText("d");  
        }  
        if(current==5)  
        {  
        	l.setText("Que6: Sixth question");  
            jb[0].setText("a");jb[1].setText("b");jb[2].setText("c");jb[3].setText("d");  
        }  
        if(current==6)  
        {  
        	l.setText("Que7: Seventh question");  
            jb[0].setText("a");jb[1].setText("b");jb[2].setText("c");jb[3].setText("d");  
        }  
        if(current==7)  
        {  
        	l.setText("Que8: Eighth question");  
            jb[0].setText("a");jb[1].setText("b");jb[2].setText("c");jb[3].setText("d");  
        }  
        if(current==8)  
        {  
        	l.setText("Que9: Ninth question");  
            jb[0].setText("a");jb[1].setText("b");jb[2].setText("c");jb[3].setText("d");  
        }  
        if(current==9)  
        {  
        	l.setText("Que10: Tenth question");  
            jb[0].setText("a");jb[1].setText("b");jb[2].setText("c");jb[3].setText("d");  
        }  
        l.setBounds(30,40,450,20);  
        for(int i=0,j=0;i<=90;i+=30,j++)  
            jb[j].setBounds(50,80+i,200,20);  
    }  
    boolean check()  
    {  
        if(current==0)  
            return(jb[1].isSelected());  
        if(current==1)  
            return(jb[1].isSelected());  
        if(current==2)  
            return(jb[2].isSelected());  
        if(current==3)  
            return(jb[0].isSelected());  
        if(current==4)  
            return(jb[2].isSelected());  
        if(current==5)  
            return(jb[3].isSelected());  
        if(current==6)  
            return(jb[1].isSelected());  
        if(current==7)  
            return(jb[3].isSelected());  
        if(current==8)  
            return(jb[2].isSelected());  
        if(current==9)  
            return(jb[2].isSelected());  
        return false;  
    }    
} 
class OnlineExam  
{  
    public static void main(String args[])  
    {  
        try  
        {  
            login form = new login();  
            form.setSize(400,150);  
            form.setVisible(true);  
        }  
        catch(Exception e)  
        {     
            JOptionPane.showMessageDialog(null, e.getMessage());  
        }  
    }  
} 
