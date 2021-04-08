package view_;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;


public class ReportView extends JDialog implements ActionListener{
//	Logger logger = LogManager.getLogger(ReportView.class);
	JTextField jtf_nickName    = null;
	JTextField jtf_time        = null;

	JLabel     jl_nickName     = null;
	JLabel     jl_time         = null;
	JLabel     jl_information  = null;

	JButton    jb_send         = null;
	JButton    jb_cancel       = null;

	
	public ReportView() {
		System.setProperty
		(XmlConfigurationFactory.CONFIGURATION_FILE_PROPERTY, "C:\\KOSMO80\\workspace\\java210208\\log4j.xml");
		
		jtf_nickName    = new JTextField();
		jtf_time        = new JTextField();

		jl_nickName     = new JLabel    ("닉네임");
		jl_time         = new JLabel    ("채팅시간");
		jl_information  = new JLabel    ("정보");

		jb_send         = new JButton   ("보내기");
		jb_cancel       = new JButton   ("취소");

	}
	public void initdisplay() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setBackground(Color.white);
	    
	    add(jtf_nickName);
	    add(jtf_time);
	    
	    add(jl_nickName);
	    add(jl_time);
	    add(jl_information);
	    
	    add(jb_send);
	    add(jb_cancel);
	    
	    setSize(500,400);
		setLocationRelativeTo(null);
		setResizable(false);
	
}

	@Override
	public void actionPerformed(ActionEvent ae) {
	Object obj = ae.getSource();

	if(obj == jb_send){
		int result = JOptionPane.showConfirmDialog(this, jtf_nickName+"/n"+jtf_time+"/n"+"send check");
		if(result == JOptionPane.YES_OPTION);
//			sendmethod.
//	jtx안에 잇는 내용들을 모두 보내는 로직집어넣기.

	}else if(obj == jb_cancel) {
		this.dispose();
}
}
}
