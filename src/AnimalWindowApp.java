import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Program: Aplikacja okienkowa z GUI, która umo¿liwia testowanie
 *          operacji wykonywanych na obiektach klasy Animal.
 *    Plik: AnimalWindowApp.java
 *
 *   Autor: Tymoteusz Frankiewicz
 *    Data: pazdziernik 2018 r.
 */

class AnimalWindowApp extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;

	private static final String GREETING_MESSAGE =
			"Program Animal - wersja okienkowa\n" +
					"Autor: Tymoteusz Frankiewicz\n" +
					"Data:  paŸdziernik 2018 r.\n";
	
	public static void main(String args[]) {
		new AnimalWindowApp();
	}
	
	private Animal animal;
	
	JLabel kindLabel = new JLabel("Rodzaj: ");
	JLabel speciesLabel = new JLabel("Gatunek: ");
	JLabel ageLabel = new JLabel("Wiek: ");
	JLabel legsNumberLabel = new JLabel("Liczba koñczyn: ");
	
	JTextField kindTextField = new JTextField(10);
	JTextField speciesTextField = new JTextField(10);
	JTextField ageTextField = new JTextField(10);
	JTextField legsNumberTextField = new JTextField(2);
	
	JButton newButton = new JButton("Nowe zwierze");
	JButton editButton = new JButton("Zmieñ dane ");
	JButton saveButton = new JButton("Zapisz do pliku");
	JButton loadButton = new JButton("Wczytaj z pliku");
	JButton deleteButton = new JButton("Usuñ zwierze");
	JButton infoButton = new JButton("O programie");
	JButton exitButton = new JButton("Zakoñcz aplikacjê");
	
	JFileChooser fileChooser = new JFileChooser();
	
	public AnimalWindowApp() {
		this.setTitle("Animal App");
		this.setSize(210, 400);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);

		
		kindTextField.setEditable(false);
		speciesTextField.setEditable(false);
		ageTextField.setEditable(false);
		legsNumberTextField.setEditable(false);
		
		newButton.addActionListener(this);
		editButton.addActionListener(this);
		saveButton.addActionListener(this);
		loadButton.addActionListener(this);
		deleteButton.addActionListener(this);
		infoButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		JPanel mainPanel = new JPanel();
		
		mainPanel.add(kindLabel);
		mainPanel.add(kindTextField);
		
		mainPanel.add(speciesLabel);
		mainPanel.add(speciesTextField);
		
		mainPanel.add(ageLabel);
		mainPanel.add(ageTextField);
		
		mainPanel.add(legsNumberLabel);
		mainPanel.add(legsNumberTextField);
		
		mainPanel.add(newButton);
		mainPanel.add(deleteButton);
		mainPanel.add(saveButton);
		mainPanel.add(loadButton);
		mainPanel.add(editButton);
		mainPanel.add(infoButton);
		mainPanel.add(exitButton);
		
		
		this.add(mainPanel);
		showCurrentAnimal();
		
		this.setVisible(true);
	}

	private void showCurrentAnimal() {
		if(animal == null) {
			kindTextField.setText("");
			speciesTextField.setText("");
			ageTextField.setText("");
			legsNumberTextField.setText("");
		} else {
			kindTextField.setText(animal.getKind().toString());
			speciesTextField.setText(animal.getSpecies());
			ageTextField.setText(Integer.toString(animal.getAge()));
			legsNumberTextField.setText(Integer.toString(animal.getLegsNumber()));
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object eventSource = e.getSource();
		try {
			if (eventSource == newButton) {
				animal = AnimalWindowDialog.createNewAnimal(this);
			}
			if (eventSource == deleteButton) {
				animal = null;
			}
			if (eventSource == saveButton) {
				File fileName;
				int returnValue;
				returnValue = fileChooser.showSaveDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					fileName = fileChooser.getSelectedFile();
					Animal.printToFile(fileName.getAbsolutePath(), animal);
				}	
				
			}
			if (eventSource == loadButton) {
				String fileName;
				int returnValue;
				returnValue = fileChooser.showOpenDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					fileName = fileChooser.getSelectedFile().getName();
							animal = Animal.readFromFile(fileName);
				}	
			}
			if (eventSource == editButton) {
				if (animal == null) throw new AnimalException("¯adne zwierze nie zosta³o utworzone.");
				AnimalWindowDialog.changeAnimalData(this, animal);
			}
			if (eventSource == infoButton) {
				JOptionPane.showMessageDialog(this, GREETING_MESSAGE);
			}
			if (eventSource == exitButton) {
				System.exit(0);
			}
		} catch(AnimalException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "B³¹d", JOptionPane.ERROR_MESSAGE);
		}
		
		showCurrentAnimal();
	}
}