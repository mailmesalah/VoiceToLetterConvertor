package com.lychee.rnd;

//TextSpeaker.java

import java.awt.*;
import java.awt.event.*;

import javax.speech.*;
import javax.speech.synthesis.*;
import javax.swing.*;

public class TextSpeaker
{
static Synthesizer synth;

public static JFrame createGUI ()
{
   JFrame frame = new JFrame ("Text Speaker");

   WindowListener wl;
   wl = new WindowAdapter ()
        {
            public void windowClosing
                                (WindowEvent e)
            {
               try
               {
                   // Deallocate synthesizer
                   // resources.

                   synth.deallocate ();
               }
               catch (EngineException e2)
               {
               }

               System.exit (0);
            }
        };
   frame.addWindowListener (wl);

   JPanel p = new JPanel ();

   p.add (new JLabel ("Specify text to " +
                      "speak:"));

   final JTextField text = new JTextField (20);
   p.add (text);

   frame.getContentPane ().add (p,
                           BorderLayout.NORTH);

   p = new JPanel ();
   p.setLayout (new FlowLayout
                           (FlowLayout.RIGHT));

   JButton btnSpeak = new JButton ("Speak");

   ActionListener al;
   al = new ActionListener ()
        {
            public void actionPerformed
                                (ActionEvent e)
            {
               // Speak the context of the text
               // field, ignoring JSML tags. 
               // Pass null as the second
               // argument because I am not
               // interested in attaching a
               // listener that receives events
               // as text is spoken.

               synth.speakPlainText
                       (text.getText (), null);

               try
               {
                   // Block this thread until
                   // the synthesizer's queue
                   // is empty (all text has
                   // been spoken). Normally,
                   // blocking the
                   // event-dispatching thread
                   // is not a good idea.
                   // However, the amount of
                   // text to be spoken should
                   // not take more than a few
                   // seconds to speak, and the
                   // user probably would not
                   // need to do anything with
                   // the GUI until the text
                   // had been spoken.

                   synth.waitEngineState
                     (Synthesizer.QUEUE_EMPTY);
               }
               catch (InterruptedException e2)
               {
               }
            }
        };
   btnSpeak.addActionListener (al);

   p.add (btnSpeak);

   JButton btnClear = new JButton ("Clear");

   al = new ActionListener ()
        {
            public void actionPerformed
                                (ActionEvent e)
            {
               text.setText ("");
               text.requestFocusInWindow ();
            }
        };
   btnClear.addActionListener (al);

   p.add (btnClear);

   frame.getContentPane ().add (p,
                           BorderLayout.SOUTH);

   frame.getRootPane ().setDefaultButton
                                    (btnSpeak);

   frame.pack ();

   return frame;
}

public static void main (String [] args)
{
   try
   {
       // Create a synthesizer for the default
       // locale.

       synth = Central.createSynthesizer
                                        (null);

       // Allocate synthesizer resources.

       synth.allocate ();

       // Place synthesizer in the RESUMED
       // state so that it can produce speech
       // as it receives text.

       synth.resume ();
   }
   catch (Exception e)
   {                 
       JOptionPane.showMessageDialog (null,
                              e.getMessage ());
       System.exit (0);
   }

   createGUI ().setVisible (true);
}
}