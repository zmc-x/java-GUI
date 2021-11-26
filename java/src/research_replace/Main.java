package research_replace;

import javax.swing.*;


import java.awt.Color ;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {
		JFrame frame=new JFrame("search and replace");
		JLabel h1=new JLabel("Դ�ַ�����",JLabel.CENTER);
		JLabel h2=new JLabel("�滻����ַ�����",JLabel.CENTER);
		JLabel lenght=new JLabel("Length:");
		JLabel findc=new JLabel("���ҵ��ַ�Ϊ��",JLabel.CENTER);
		JLabel replace_tag=new JLabel("���滻Ϊ�Ǹ��ַ���",JLabel.CENTER);
		JLabel afterfind=new JLabel("find�ַ����ڵ�λ��:",JLabel.CENTER);
		//�ı�
		static JTextField n_text=new JTextField();
		static JTextField a_text=new JTextField();
		static JTextField findtext=new JTextField();
		static JTextField replace_text=new JTextField();
		static JTextField number=new JTextField();
		static JTextField afterfindText=new JTextField();
		//����������
		JPanel jpnorth=new JPanel();
		JPanel jpsouth=new JPanel();
		JPanel jpcenter=new JPanel();
		//����
		FlowLayout flowlayout=new FlowLayout(FlowLayout.RIGHT,10,10);
		JButton find=new JButton("Find");
		JButton replace=new JButton("Replace");
		JButton clear=new JButton("Clear");
		JButton help=new JButton("Help");
		public void init() {
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(700,500);//���ڴ�С
			frame.setLocation(500,50);//���ڵ�λ��
			find.addActionListener(actionEvent->findPerformed(actionEvent));
			replace.addActionListener(actionEvent->replacePerformed(actionEvent));
			clear.addActionListener(actionEvent->clearPerformed(actionEvent));
			help.addActionListener(actionEvent->helpPerformed(actionEvent));
			//jpnorth
			jpnorth.setLayout(null);
			jpnorth.add(h1);
			jpnorth.add(n_text);
			jpnorth.add(h2);
			jpnorth.add(a_text);
			jpnorth.setPreferredSize(new Dimension(600,80));
			//���ð�����С
			h1.setBounds(15,15,100,30);
			h2.setBounds(15,50,100,30);
			n_text.setBounds(150,15,500,30);
			a_text.setBounds(150,50,500,30);
			frame.add(jpnorth,BorderLayout.NORTH);
			//jpsouth
			jpsouth.setLayout(null);
			jpsouth.add(find);
			jpsouth.add(replace);
			jpsouth.add(clear);
			jpsouth.add(help);
			jpsouth.add(findc);
			jpsouth.add(findtext);
			jpsouth.add(replace_tag);
			jpsouth.add(replace_text);
			//���ð�����С
			replace_text.setBounds(550,20,100,20);
			replace_tag.setBounds(390,20,200,20);
			findtext.setBounds(150,20,200,20);
			findc.setBounds(0,20,200,20);
			help.setBounds(600,60,85,30);
			find.setBounds(50,60,100,30);
			replace.setBounds(200,60,100,30);
			clear.setBounds(400,60,100,30);
			jpsouth.setPreferredSize(new Dimension(200, 200));
			frame.add(jpsouth,BorderLayout.SOUTH);
			jpcenter.setLayout(null);
			jpcenter.add(lenght);
			jpcenter.add(number);
			jpcenter.add(afterfind);
			jpcenter.add(afterfindText);
			number.setBounds(300,100,50,20);
			lenght.setBounds(250,100,100,20);
			afterfind.setBounds(15,150,150,30);
			afterfindText.setBounds(150,150,500,30);
			frame.add(jpcenter,BorderLayout.CENTER);
			frame.setBackground(Color.WHITE);//���ô��屳����ɫ
			frame.setVisible(true);
			frame.setResizable(false);
	}
	//����¼�����
	private static void findPerformed(ActionEvent actionEvent) {
		String str=n_text.getText();
		String finch=findtext.getText();
		int len=str.length();
		number.setText(Integer.toString(len));//����api��intת��Ϊstring
		Find FIND=new Find(str, finch);
		FIND.kmp(str, finch);
		if(FIND.flag==1) afterfindText.setText("û����Ҫ���ҵ��ַ�������");
		else{
			int len_=FIND.me_cnt;
			StringBuffer mid=new StringBuffer();//����һ���м���ַ���
			int cnt=0;//�����ж��Ǽ��롮-�����ǡ� ��
			for(int i=0;i<len_;i++){
				cnt++;
				mid.append(FIND.memory[i]);
				if(cnt%2!=0)mid.append('-');
				else mid.append(' ');
			}
			afterfindText.setText(mid.toString());
		}
		JOptionPane.showMessageDialog(null, "������ɡ�","��ʾ",JOptionPane.INFORMATION_MESSAGE);
	}
	private static void replacePerformed(ActionEvent actionEvent) {
		String str=n_text.getText();
		String re=replace_text.getText();
		if(re.equals("")) JOptionPane.showMessageDialog(null,"�㲻�����ַ����滻ɶ��","Error",JOptionPane.INFORMATION_MESSAGE);
		else{
		String finch=findtext.getText();
		Find FIND=new Find(str, finch);
		FIND.kmp(str, finch);
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append(str);
		int len_=FIND.me_cnt;
		//System.out.println(len_);
		int i=0;
		if(FIND.flag==1){
			a_text.setText(stringBuffer.toString());
			number.setText(String.valueOf(stringBuffer.length()));
			JOptionPane.showMessageDialog(null, "û������ַ����Ҳ�֪��Ҫ�滻ɶ���Һܲ����\n������ֻ�����û��replace���ַ�����","Error",JOptionPane.INFORMATION_MESSAGE);
		}
		else{
		    for(i=0;i<len_;i=i+2) {
			    stringBuffer.replace(FIND.memory[i]-1, FIND.memory[i+1], re);
		    }
		    a_text.setText(stringBuffer.toString());
			number.setText(String.valueOf(stringBuffer.length()));
		    JOptionPane.showMessageDialog(null, "Replaced successfully","��ʾ",JOptionPane.INFORMATION_MESSAGE);
		 }
		}
		//System.out.println(stringBuffer);
		
	}
	private static void clearPerformed(ActionEvent actionEvent) {
		n_text.setText("");
		a_text.setText("");
		findtext.setText("");
		replace_text.setText("");
		number.setText("");
		afterfindText.setText("");
		JOptionPane.showMessageDialog(null,"Cleared successfully","��ʾ",JOptionPane.INFORMATION_MESSAGE);
		//���ı����ƿ�
	}
	private static void helpPerformed(ActionEvent actionEvent) {
		JOptionPane.showMessageDialog(null, "����һ���򵥵��ַ������Լ��滻��GUI,��ز������ܼ�\n����Ϊһ���˶������\n \n \t \t author:mengchao zhang","help",JOptionPane.INFORMATION_MESSAGE);
	}
	public static void main(String []args) {
		new Main().init();
	}
}
