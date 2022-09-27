package com.lychee.voiceinterface;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JVoiceInterface extends JFrame {
	
	/**	 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField jTFVoicePath;
	private JButton jBBrowseVoice;
	private JScrollPane jSPForTextArea;
	private JTextArea jTALetterFound;
	private JButton jBFindLetters;
	private File file;
	
	public JVoiceInterface(){
		initComponents();
		
		jBBrowseVoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	selectTheVoiceFile(evt);
            }
        });
		
		jBFindLetters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	
            }
        });
	}
	
	
	private void selectTheVoiceFile(java.awt.event.ActionEvent evt) {                                                                  
        if (evt.getSource() == jBBrowseVoice) {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int returnVal = jfc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = jfc.getSelectedFile();

                jTFVoicePath.setText(file.getName());

            } else {
                file = null;
            }
        }
    } 

	private void initComponents() {

        jTFVoicePath = new javax.swing.JTextField();
        jBBrowseVoice = new javax.swing.JButton();
        jSPForTextArea = new javax.swing.JScrollPane();
        jTALetterFound = new javax.swing.JTextArea();
        jBFindLetters = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTFVoicePath.setText("");

        jBBrowseVoice.setText("Browse Voice");

        jTALetterFound.setColumns(20);
        jTALetterFound.setRows(5);
        jSPForTextArea.setViewportView(jTALetterFound);

        jBFindLetters.setText("Find Letters");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSPForTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(jTFVoicePath))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBBrowseVoice, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(jBFindLetters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFVoicePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBBrowseVoice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSPForTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBFindLetters))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }

	public static void main(String[] args) {
		JVoiceInterface jvi = new JVoiceInterface();
		jvi.setSize(700, 600);
		jvi.setVisible(true);
		jvi.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}
