import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Program: Aplikacja okienkowa z GUI, kt�ra umo�liwia testowanie
 *          operacji wykonywanych na obiektach klasy Animal.
 *          
 *          Istnieje mo�liwo�� wyboru lokalizacji
 *          plik�w.
 *          
 *          Aplikacje posiada takze g�rny pasek menu
 *          
 *          
 *    Plik: AnimalWindowApp.java
 *
 *   Autor: Tymoteusz Frankiewicz
 *    Data: pazdziernik 2018 r.
 *    
 *   
 */

class AnimalWindowApp extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;

	private static final String GREETING_MESSAGE =
			"Program Animal - wersja okienkowa\n" +
					"Autor: Tymoteusz Frankiewicz\n" +
					"Data:  pa�dziernik 2018 r.\n";
	
	public static void main(String args[]) {
		new AnimalWindowApp();
	}
	
	private Animal animal;
	
	JLabel kindLabel = new JLabel("Rodzaj: ");
	JLabel speciesLabel = new JLabel("Gatunek: ");
	JLabel ageLabel = new JLabel("Wiek: ");
	JLabel legsNumberLabel = new JLabel("Liczba ko�czyn: ");
	
	JTextField kindTextField = new JTextField(10);
	JTextField speciesTextField = new JTextField(10);
	JTextField ageTextField = new JTextField(10);
	JTextField legsNumberTextField = new JTextField(2);
	
	JButton newButton = new JButton("Nowe zwierze");
	JButton editButton = new JButton("Zmie� dane");
	JButton saveButton = new JButton("Zapisz do pliku");
	JButton loadButton = new JButton("Wczytaj z pliku");
	JButton saveBinButton = new JButton("Zapisz do .bin");
	JButton loadBinButton = new JButton("Wczytaj z .bin");
	JButton deleteButton = new JButton("Usu� zwierze");
	JButton infoButton = new JButton("O programie");
	JButton exitButton = new JButton("Zako�cz aplikacj�");
	
	JMenuBar menuBar = new JMenuBar();
	JMenu animalMenu = new JMenu("Zwierz�ta");
	JMenu applicationMenu = new JMenu("Aplikacja");

	JMenuItem newAnimalMenuItem = new JMenuItem("Nowe zwierze");
	JMenuItem editAnimalMenuItem = new JMenuItem("Zmie� dane");
	JMenuItem saveAnimalMenuItem = new JMenuItem("Zapisz do pliku");
	JMenuItem loadAnimalMenuItem = new JMenuItem("Wczytaj z pliku");
	JMenuItem saveBinAnimalMenuItem = new JMenuItem("Zapisz do .bin");
	JMenuItem loadBinAnimalMenuItem = new JMenuItem("Wczytaj z .bin");
	JMenuItem deleteAnimalMenuItem = new JMenuItem("Usu� zwierze");
	
	JMenuItem aboutAppMenuItem = new JMenuItem("O programie");
	JMenuItem exitAppMenuItem = new JMenuItem("Zako�cz aplikacje");
	
	JFileChooser fileChooser = new JFileChooser();
	
	JPanel mainPanel = new JPanel();
	
	public AnimalWindowApp() {
		this.setTitle("Animal App");
		this.setSize(210, 450);
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
		saveBinButton.addActionListener(this);
		loadBinButton.addActionListener(this);
		deleteButton.addActionListener(this);
		infoButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		newAnimalMenuItem.addActionListener(this);
		editAnimalMenuItem.addActionListener(this);
		saveAnimalMenuItem.addActionListener(this);
		saveBinAnimalMenuItem.addActionListener(this);
		loadBinAnimalMenuItem.addActionListener(this);
		deleteAnimalMenuItem.addActionListener(this);
		aboutAppMenuItem.addActionListener(this);
		exitAppMenuItem.addActionListener(this);
		loadAnimalMenuItem.addActionListener(this);

		menuBar.add(animalMenu);
		animalMenu.add(newAnimalMenuItem);
		animalMenu.addSeparator();
		animalMenu.add(editAnimalMenuItem);
		animalMenu.addSeparator();
		animalMenu.add(saveAnimalMenuItem);
		animalMenu.addSeparator();
		animalMenu.add(loadAnimalMenuItem);
		animalMenu.addSeparator();
		animalMenu.add(saveBinAnimalMenuItem);
		animalMenu.addSeparator();
		animalMenu.add(loadBinAnimalMenuItem);
		animalMenu.addSeparator();
		animalMenu.add(deleteAnimalMenuItem);
		
		menuBar.add(applicationMenu);
		applicationMenu.add(aboutAppMenuItem);
		applicationMenu.addSeparator();
		applicationMenu.add(exitAppMenuItem);
		
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
		mainPanel.add(saveBinButton);
		mainPanel.add(loadBinButton);
		mainPanel.add(editButton);
		mainPanel.add(infoButton);
		mainPanel.add(exitButton);
		
		this.setJMenuBar(menuBar);
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
			if (eventSource == newButton || eventSource == newAnimalMenuItem) {
				animal = AnimalWindowDialog.createNewAnimal(this);
			}
			if (eventSource == deleteButton|| eventSource == deleteAnimalMenuItem) {
				animal = null;
			}
			if (eventSource == saveButton || eventSource == saveAnimalMenuItem) {
				String fileName;
				int returnValue;
				returnValue = fileChooser.showSaveDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					fileName = fileChooser.getSelectedFile().getAbsolutePath();
					Animal.printToFile(fileName, animal);
				}	
				
			}
			if (eventSource == loadButton|| eventSource == loadAnimalMenuItem) {
				String fileName;
				int returnValue;
				returnValue = fileChooser.showOpenDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					fileName = fileChooser.getSelectedFile().getAbsolutePath();
					animal = Animal.readFromFile(fileName);
				}	
			}
			
			if (eventSource == saveBinButton || eventSource == saveBinAnimalMenuItem) {
				String fileName;
				int returnValue;
				returnValue = fileChooser.showSaveDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					fileName = fileChooser.getSelectedFile().getAbsolutePath();
					Animal.serializeToBinaryFile(fileName, animal);
				}
			}
			if(eventSource == loadBinButton || eventSource == loadBinAnimalMenuItem) {
				String fileName;
				int returnValue;
				returnValue = fileChooser.showOpenDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					fileName = fileChooser.getSelectedFile().getAbsolutePath();
					animal = Animal.serializeFromBinaryFile(fileName);
				}
			}
			if (eventSource == editButton || eventSource == editAnimalMenuItem) {
				if (animal == null) throw new AnimalException("�adne zwierze nie zosta�o utworzone.");
				AnimalWindowDialog.changeAnimalData(this, animal);
			}
			if (eventSource == infoButton || eventSource == aboutAppMenuItem) {
				JOptionPane.showMessageDialog(this, GREETING_MESSAGE);
			}
			if (eventSource == exitButton || eventSource == exitAppMenuItem) {
				System.exit(0);
			}

		} catch(AnimalException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "B��d", JOptionPane.ERROR_MESSAGE);
		}
		
		showCurrentAnimal();
	}
}