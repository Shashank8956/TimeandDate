package arduinoconnect;
import com.onbarcode.barcode.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener; 
import java.awt.print.PageFormat;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Enumeration;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.Attribute;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrintQuality;
import javax.print.attribute.standard.PrinterResolution;
import javax.swing.ImageIcon;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Spongebob
 */
public class MainFrame3 extends javax.swing.JFrame implements SerialPortEventListener{


    SerialPort serialPort;
        /** The port we're normally going to use. */
        StringBuilder code = new StringBuilder();
        String path = "D:\\TimeCode.jpg";
	private static final String PORT_NAMES[] = { 
			"COM3", // Windows
	};
        int new1=0, old=0;
	/**
	* A BufferedReader which will be fed by a InputStreamReader 
	* converting the bytes into characters 
	* making the displayed results codepage independent
	*/
	private BufferedReader input;
	/** The output stream to the port */
	private OutputStream output;
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;

	public void initialize() {
                // the next line is for Raspberry Pi and 
                // gets us into the while loop and was suggested here was suggested http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
        
               CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		//First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	/**
	 * This should be called when you stop using the port.
	 * This will prevent port locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		
                if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
                            while (input.ready ()){
                                //System.out.println("Fuss "+new1 +" "+old);
				String inputLine=input.readLine();
                                //System.out.println("InputLine "+inputLine);
                                new1 = Integer.valueOf(inputLine);
                                if(new1-old==1)
                                {
                                    System.out.println("new1-old= "+ (new1-old));
                                    
                                    int hour, min, day, month, year;
                                    java.util.Date date = new java.util.Date();
                                    Calendar cal= Calendar.getInstance();
                                    year = cal.get(Calendar.YEAR);
                                    month = cal.get(Calendar.MONTH);      // 0 to 11
                                    day = cal.get(Calendar.DAY_OF_MONTH);
                                    hour = cal.get(Calendar.HOUR_OF_DAY);
                                    min = cal.get(Calendar.MINUTE);
                                    month = month + 1;
                                    code.append("");
                                    if(day/10<1)
                                    {
                                        code.append("0");
                                        code.append(day);
                                    }
                                    else
                                        code.append(day);
                                        if(month/10<1)
                                    {
                                         code.append("0");
                                         code.append(month);
                                    }
                                    else
                                        code.append(month);
                                    code.append(year);
                                    code.delete(4,6);
                                    if(hour/10<1)
                                    {
                                        code.append("0");
                                        code.append(hour);
                                    }
                                    else
                                        code.append(hour);
                                    if(min/10<1)
                                    {
                                        code.append("0");
                                        code.append(min);
                                    }
                                    else
                                        code.append(min);
            
                                    String code1;
                                    code1= code.toString();
                                    //Lsignal.setText(code1);
                                    
                                    ImageIcon iconLogo = new ImageIcon(path);
                                    Lsignal.setText("");
                                    Lsignal.setIcon(iconLogo);
                                    code.delete(0,11);
                                    System.out.println(code);
                                    Code128 barcode = new Code128();             //Barcode Type
                                    barcode.setData(code1);                       //Barcode String
                                    barcode.setX(2);                             //Barcode data text to encode
                                    //barcode.setBarcodeWidth(-50f);
                                    barcode.setLeftMargin(-15);
                                    barcode.setRightMargin(-5);
                                    barcode.setBarcodeHeight(50f);
                                    barcode.drawBarcode(path);
            
                                    printImg(path);
                                }
                                else if(new1-old==0)
                                    System.out.println("Nope!");
                                else if(new1-old==-1)
                                    System.out.println("Nope!");
                                old=new1;
                            }
			} catch (Exception e) {
				System.err.println(e.toString());
			} finally {
                            Lsignal.setIcon(null);
                            Lsignal.setText("No Input");
                        }
		}
		// Ignore all the other eventTypes, but you should consider the other ones.
	}
        
        private void printImg(String dest) throws Exception
    {
        // Input the file
        FileInputStream textStream = null;
        try {
            textStream = new FileInputStream(dest);
        } catch (Exception exp) {
            return;
        }

        DocFlavor myFormat = DocFlavor.INPUT_STREAM.JPEG;
        PageFormat format = new PageFormat();
        format.setOrientation(PageFormat.PORTRAIT);

        Doc myDoc = new SimpleDoc(textStream, myFormat, null);
        // Build a set of attributes
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        // aset.add(new Copies(2));

        aset.add(new MediaPrintableArea(0, 0, 70f, 17f,
                MediaPrintableArea.MM));
        aset.add(PrintQuality.HIGH);

        aset.add(new PrinterResolution(180, 180, PrinterResolution.DPI));
        aset.add(OrientationRequested.PORTRAIT);

        PrintService[] services = PrintServiceLookup.lookupPrintServices(
                myFormat, null);

        if (services.length > 0) {
            DocPrintJob job = services[0].createPrintJob();           
            
            for (Attribute temp : services[0].getAttributes().toArray())
                System.out.println(temp.getName());

            try {
                job.print(myDoc, aset);
            } catch (PrintException pe) {

            }
        }
        
    }
        
    public MainFrame3() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Lsignal = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Raisoni-App-RGI.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Mahindra-Rise-logo-2560x1440.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipady = 48;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 0, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel4.setText("Time-Date Barcode Gen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 44, 0, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        Lsignal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Lsignal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Lsignal.setText("No Input");

        jLabel1.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Made by: Shashank Singh, Rahul Gour, Shivam Singh and Ashutosh Paliwal");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addComponent(Lsignal, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(265, 265, 265)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(Lsignal, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107)
                .addComponent(jLabel1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainFrame3 m=new MainFrame3();
                m.initialize();
		Thread t=new Thread() {
			public void run() {
				//the following line will keep this app alive for 1000 seconds,
				//waiting for events to occur and responding to them (printing incoming messages to console).
				try {Thread.sleep(1000000000);} catch (InterruptedException ie) {}
			}
		};
		t.start();
		System.out.println("Started");
                m.setLocationRelativeTo(null);
                m.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Lsignal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
