
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;
class GUI
{
    JFrame f;
    GUI(String s)
    {
        f=new JFrame(s);
        f.setLayout(null);
        f.setSize(505, 500);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        //f.getContentPane().setBackground(new Color(67, 58, 242));
        f.setVisible(true);
    }
}
class User
{
    int balance;
    String pin;
    int acc;
    User(int b,String p,int a)
    {
        balance=b;
        pin=p;
        acc=a;
    }
    int checkBalance()
    {
        return balance;
    }
    int withdraw(int b)
    {
        if(b<=balance)
        {
            balance-=b;
            return balance;
        }
        else    
            return -1;
    }
    int deposit(int b)
    {
        balance+=b;
        return balance;
    }
}
class Database 
{
    static ArrayList<User> users;
    GUI dbf;
    Database()
    {
        dbf=new GUI("User Interface");
        users=new ArrayList<User>();
        JButton eub=new JButton("Existing user");
        JButton aub=new JButton("Add user");

        dbf.f.add(eub);
        dbf.f.add(aub);

        eub.setBounds(10,10,300,30);
        aub.setBounds(10,60,300,30);

        //eub.setBackground(new Color(247,27,222));
        
        eub.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    try 
                    {
                        //dbf.f.setVisible(false);
                        existingUser();
                    } catch (Exception ee) {
                        // TODO: handle exception
                        ee.printStackTrace();
                    }
                }
            }
        );

        aub.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    try 
                    {
                        //dbf.f.setVisible(false);
                        addUser();
                    } catch (Exception ee) {
                        // TODO: handle exception
                        ee.printStackTrace();
                    }
                }
            }
        );
    }
    void addUser()
    {
        GUI af=new GUI("Adding User");
        JLabel eaccno=new JLabel("Enter account no:");
        JTextField tfaccno=new JTextField(20);
        JLabel epin=new JLabel("Enter pin");
        JPasswordField pfpin=new JPasswordField(20);
        JLabel ebal=new JLabel("Enter Balance");
        JTextField tfbal=new JTextField(20);
        JButton addb=new JButton("Add User to DB");

        af.f.add(eaccno);
        af.f.add(tfaccno);
        af.f.add(epin);
        af.f.add(pfpin);
        af.f.add(ebal);
        af.f.add(tfbal);
        af.f.add(addb);

        eaccno.setBounds(10,10,230,20);
        tfaccno.setBounds(250,10,230,20);
        epin.setBounds(10,40,230,20);
        pfpin.setBounds(250,40,230,20);
        ebal.setBounds(10,70,230,20);
        tfbal.setBounds(250,70,230,20);
        addb.setBounds(10,120,400,20);

        addb.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    try {
                        int acc=Integer.parseInt(tfaccno.getText());
                        String pin=pfpin.getText().toString();
                        int bal=Integer.parseInt(tfbal.getText());
                        User user=new User(bal,pin,acc);
                        users.add(user);
                        af.f.setVisible(false);
                        operations(user);
                    } catch (Exception ee) {
                        // TODO: handle exception
                        ee.printStackTrace();
                    }
                }
            }
        );
    }
    void existingUser()
    {
        GUI ef=new GUI("Existing user Authentication");
        JLabel eaccno=new JLabel("Enter Account no");
        JTextField tfaccno=new JTextField(20);
        JLabel epin=new JLabel("Enter pin");
        JPasswordField pfpin=new JPasswordField(20);
        JButton auth=new JButton("Authenticate");
        

        ef.f.add(eaccno);
        ef.f.add(tfaccno);
        ef.f.add(epin);
        ef.f.add(pfpin);
        ef.f.add(auth);
        

        eaccno.setBounds(10,10,230,20);
        tfaccno.setBounds(250,10,230,20);
        epin.setBounds(10,40,230,20);
        pfpin.setBounds(250,40,230,20);
        auth.setBounds(300, 70, 150, 20);
        

        auth.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    try 
                    {
                        int acc=Integer.parseInt(tfaccno.getText());
                        String pin=pfpin.getText().toString();
                        boolean ok=false;
                        User user=null;
                        for(User u : users)
                        {
                            //System.out.println(acc+"^^"+pin);
                            //System.out.println(u.acc+"--"+u.pin);
                            if(u.acc==acc && u.pin.equals(pin))
                            {
                                user=u;
                                ok=true;
                            }
                        }
                        if(!ok)
                        {
                            JLabel comment=new JLabel("Account number or pin is not valid");
                            ef.f.add(comment);
                            comment.setBounds(10,120,400,200);
                        }
                        else
                        {
                            //comment.setText("");
                            JOptionPane.showMessageDialog(ef.f,"Account number is valid");
                            System.out.println("Account number or pin is valid :)");
                            Thread.sleep(3000);
                            ef.f.setVisible(false);
                            operations(user);

                        }
                        
                    } catch (Exception ee) {
                        // TODO: handle exception
                        ee.printStackTrace();
                    }
                }
            }
        );  
    }
    void operations(User user)
    {
        //System.out.println(user.acc+"**"+user.pin+"//"+user.balance);
        GUI opf=new GUI("Operations Window");
        JButton dep=new JButton("Deposit");
        JButton with=new JButton("Withdrawal");
        JButton bcheck=new JButton("Balance Check");
        JButton fundt=new JButton("Fund transfer");
        JButton exit=new JButton("Exit");

        opf.f.add(dep);
        opf.f.add(with);
        opf.f.add(bcheck);
        opf.f.add(fundt);
        opf.f.add(exit);
        

        dep.setBounds(50,10,400,50);
        with.setBounds(50,70,400,50);
        bcheck.setBounds(50,130,400,50);
        fundt.setBounds(50,190,400,50);
        exit.setBounds(50,250,400,50);

        dep.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent ee)
                {
                    depositgui(user);
                }
            }
            
        );

        with.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent ee)
                {
                    withdrawgui(user);
                }
            }
            
        );

        bcheck.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent ee)
                {
                    JOptionPane.showMessageDialog(opf.f,"Balance="+Integer.toString(user.checkBalance()));
                }
            }
            
        );
        
        fundt.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent ee)
                {
                    fundTransfergui(user);
                }
            }
            
        );

        exit.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent ee)
                {
                    opf.f.setVisible(false);
                }
            }
        );
    }
    void depositgui(User user)
    {
        GUI df=new GUI("Deposit Money");
        JLabel eamt=new JLabel("Enter Amount");
        JTextField tfamt=new JTextField(10);
        JButton depb=new JButton("Deposit");

        df.f.add(eamt);
        df.f.add(tfamt);
        df.f.add(depb);

        eamt.setBounds(10,10,230,30);
        tfamt.setBounds(250,10,230,30);
        depb.setBounds(300, 50, 150, 30);

        depb.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent ee)
                {
                    try 
                    {
                        String given=tfamt.getText();
                        if(given.equals(""))
                        {
                            JOptionPane.showMessageDialog(df.f,"Enter valid amount");
                        }
                        else
                        {
                            int rem=user.deposit(Integer.parseInt(tfamt.getText()));
                            JOptionPane.showMessageDialog(df.f,"Balance="+Integer.toString(rem));
                            Thread.sleep(3000);
                            df.f.setVisible(false);
                        }

                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
            
        );
    }

    void withdrawgui(User user)
    {
        GUI wf=new GUI("Withdraw Money");
        JLabel eamt=new JLabel("Enter Amount");
        JTextField tfamt=new JTextField(10);
        JButton withb=new JButton("Withdraw");

        wf.f.add(eamt);
        wf.f.add(tfamt);
        wf.f.add(withb);

        eamt.setBounds(10,10,230,30);
        tfamt.setBounds(250,10,230,30);
        withb.setBounds(300, 50, 150, 30);

        withb.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent ee)
                {
                    try 
                    {
                        String given=tfamt.getText();
                        if(given.equals(""))
                        {
                            JOptionPane.showMessageDialog(wf.f,"Enter valid amount");
                        }
                        else
                        {
                            int rem=user.withdraw(Integer.parseInt(tfamt.getText()));
                            if(rem==-1)
                            {
                                JOptionPane.showMessageDialog(wf.f,"Insufficient Balance","OOPS",JOptionPane.WARNING_MESSAGE);
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(wf.f,"Balance="+Integer.toString(rem));
                                Thread.sleep(3000);
                                wf.f.setVisible(false);
                            }
                        }

                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        );
    }

    void fundTransfergui(User user)
    {
        GUI ftf=new GUI("Fund Tranfer");
        JLabel ebacc=new JLabel("Enter Beneficiary account no");
        JTextField tfbacc=new JTextField(10);
        JLabel eamt=new JLabel("Enter Amount to transfer");
        JTextField tfamt=new JTextField(10);
        JButton send=new JButton("Send");

        ftf.f.add(ebacc);
        ftf.f.add(tfbacc);
        ftf.f.add(eamt);
        ftf.f.add(tfamt);
        ftf.f.add(send);

        ebacc.setBounds(10,10,230,20);
        tfbacc.setBounds(250,10,230,20);
        eamt.setBounds(10,40,230,20);
        tfamt.setBounds(250,40,230,20);
        send.setBounds(300, 70, 150, 20);

        send.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent ee)
                {
                    String given=tfamt.getText();
                    String acno=tfbacc.getText();
                    if(given.equals("") || acno.equals("") )
                    {
                        JOptionPane.showMessageDialog(ftf.f,"Enter valid details");
                    }
                    else
                    {
                        User recUser=null;
                        boolean legi=false;
                        for(User u:users)
                        {
                            if(u.acc==Integer.parseInt(acno))
                            {
                                legi=true;
                                recUser=u;
                            }
                        }
                        if(!legi)
                        {
                            JOptionPane.showMessageDialog(ftf.f,"Invalid beneficiary acc.no","Alert",JOptionPane.WARNING_MESSAGE);
                        }
                        else
                        {
                            int rem=user.withdraw(Integer.parseInt(tfamt.getText()));
                            if(rem==-1)
                            {
                                JOptionPane.showMessageDialog(ftf.f,"Insufficient Balance","OOPS",JOptionPane.WARNING_MESSAGE);
                            }
                            else
                            {                                
                                int recBal=recUser.deposit(Integer.parseInt(tfamt.getText()));
                                System.out.println(recBal);
                                JOptionPane.showMessageDialog(ftf.f,"Money Tranfered to acc:"+acno);
                                ftf.f.setVisible(false);
                            }
                        }
                    }
                }
            }
        );
    }

}

public class ATMInterface
{
public static void main(String[] args) {
    Database db=new Database();
}
    
}
