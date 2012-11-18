import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GantryManager extends JPanel implements ActionListener {
	
	public static final int UPDATE_RATE = 50;
	
	private GantryClient client;
	private GantryManager gantryView;
	private FactoryPainter painter;

	
	public GantryManager(GantryClient client){
		this.client = client;
		setLayout(new BorderLayout());
		gantryView = new JPanel();
		mainpanel.add(fpsp,"fpsp");
		mainpanel.add(fpvp,"fpvbp");
		fpbp.btnSwitchSchedule.addActionListener(this);
		fpbp.btnSwitchView.addActionListener(this);
		add(mainpanel,BorderLayout.CENTER);
		add(fpbp,BorderLayout.SOUTH);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == fpbp.btnSwitchSchedule){
			cardlayout.first(mainpanel);
			
		}
		if(e.getSource() == fpbp.btnSwitchView){
			validate();
			repaint();
			cardlayout.last(mainpanel);
			
		}
	}

	public FactoryProductionViewPanel getViewPanel() {
		return fpvp;
	}
}