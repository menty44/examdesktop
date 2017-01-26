/** ***************************************************************************

 * The BasicCalc class implements a simple Calculator application.
 * MyCalc uses an operator code to keep track of the operation being
 * performed
 *  - 0 = multiplication
 *  - 1 = division
 *  - 2 = subtraction
 *  - 3 = addition
 *  - 5 = the result of the operator acting on the two numbers
 *        as a result of the equals button being pressed
 *
 *
 * Copyright (c) 2002-2012 Advanced Applications Total Applications Works.
 * (AATAW)  All Rights Reserved.
 *
 * AATAW grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to AATAW.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. AATAW AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL AATAW OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes or for commerical purposes .
 *
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.* ;
import javax.swing.JOptionPane ;
import java.io.*;
import javax.swing.border.Border;
import javax.swing.border.* ;

/** **********************************************************
 * The BasicCalc class is used to create a basic calculator.
 * This calculator allows the user to perform four arithmetic
 * functions:
 * 1- Multiplication
 * 2- Division
 * 3- Subtraction
 * 4- Addition
 *
 * Additionally, this calculator allows the user to chain
 * operations together before pressing the equals button.
 *************************************************************/
public class BasicCalc extends JFrame
                implements ActionListener {

   private JButton bOne, bTwo, bThree, bFour, bFive,
                   bSix, bSeven, bEight, bNine,  bZero, bExit,
                   bMult, bDiv, bSub, bPlus, bEquals, bHelp,
                   bAbout,  bDecPt, bClear ;
   private JTextField enterBox ;
   private JPanel textPanel, exitPanel, buttonPanel, functionPanel ;
   private Container c ;
   private Font         font;
   private String firstNum = new String(""), secondNum  = new String(""), tempNum, tempStr ;
   private boolean myDiag = false, result = false ,
                    tempfirstNumSet = false, firstNumSet = false, secondNumSet = false,
                   pendingOp  = false ;
   private double aNum, dNum1 = 0.0, dNum2 = 0.0 , answer = 0.0,
                   tempD = 0.0 , minusOne = -1.0 ;
   private double dArray[] = new double[ 10 ]  ;
   private int opCode = -1, tempInt = 0, tempInt2 = 0,
                 dArrayPtr = 0, pendingOpCode = -1 ;
   private Border raisedBorder = BorderFactory.createRaisedBevelBorder() ;

   /** *****************************************************
    * The BasicCalc() constructor initializes the application
    * by:
    * 1- Creating the GUI
    * 2- Setting the size of the main application frame.
    * 3- Setting the location of the main application frame.
    * 4- Making the main application frame visible.
    ********************************************************/
   public BasicCalc() {

      super( "Basic Calculator" );

      LogoWindowFrame sqf = new LogoWindowFrame() ;

      setup() ;

      setSize( 350, 300 );  /** sets the size of the frame */
      setLocation( 200, 200 ) ; /** sets the location of the frame on the screen*/
      show();               /** makes the frame visible */
   }

   /** ********************************************************
    * The setup() method is used to allocate the panel and
    * button objects using the new function. The logic is:
    * 1- Get the Content Pane anchor that will be used to anchor
    *    the application's GUI.
    * 2- Setup the text field
    * 3- Setup the button Panel and the buttons used to enter
    *    the numbers.
    * 4- Setup function panel and the buttons used to indicate
    *    the operation to be performed.
    * 5- Setup exit panel and the buttons displayed on its panel.
    * 6- Setup Action Listeners that will used indicate which button
    *    was pressed.
    *
    ******************************************************** */
   public void setup()   {
      c = getContentPane() ;

      /** setup the text field used on the calculator frame */
      setUpTextField() ;


      /** setup button Panel and the number buttons */
      setUpButtons() ;

      /** setup function panel and its buttons*/
      setUpFunctionPanel() ;

      /** setup exit panel and its buttons */
      setUpExitPanel() ;

      /** setup Action Listeners */
      setUpActionListener() ;
   }

   /** **************************************************
    *  The setUpTextField() method creates the test field
    *  that displays the numbers entered and the results
    *  of mathematical operations. The logis is as follows:
    *  1- Create the test field.
    *  2- Enter a zero as the initial value in the field.
    *  3- Set the first number flag to true.
    *  4- Set the background for the text field to "White"
    *  5- Ensure that numbers entered are right justified.
    *  6- Create a text panel to contain the text field.
    *  7- Enter a text field on the text panel.
    ** **************************************************/
   public void setUpTextField() {
            /** setup text field and panel */
      enterBox  = new JTextField( 15 ) ;
      enterBox.setText( "0" );
      firstNumSet = true ;
      enterBox.setEditable( false );
      enterBox.setBackground( Color.white );
      enterBox.setHorizontalAlignment( JTextField.RIGHT );
      enterBox.addKeyListener(
      /** this code reprimes the enterbox and avoids exceptions */
         new KeyAdapter() {
            public void keyPressed ( KeyEvent e )   {
            /*   if ( result ) {
                  MyPrint( "The value of result is " + result +
                   "this is from the enterbox keylistener." );
                  result = false ;
                  enterBox.setText( "0" );
               } */
            }
         }
      );
      textPanel = new JPanel() ;
      textPanel.add(enterBox ) ;

      c.add( textPanel , BorderLayout.NORTH ) ;
   }

   /** **************************************************
    *  The setUpButtons() method creates and initializes
    *  the buttons for this application. The logic is:
    *  1- Create the button panel that will contain the
    *     number buttons.
    *  2- Create the buttons that display the mathematical
    *     operations.
    *  3- Add the button to the button panel.
    *
    ** **************************************************/
   public void setUpButtons() {
      buttonPanel = new JPanel() ;
      c.add( buttonPanel , BorderLayout.CENTER ) ;

      bZero   = new JButton( "0" ) ;
      bZero.setFont( new Font("Sanserif", Font.BOLD, 16 ) );
      bZero.setBorder( raisedBorder ) ;
      bOne    = new JButton( "1" ) ;
      bOne.setBorder( raisedBorder ) ;
      bTwo    = new JButton( "2" ) ;
      bTwo.setBorder( raisedBorder ) ;
      bThree  = new JButton( "3" ) ;
      bThree.setBorder( raisedBorder ) ;
      bFour   = new JButton( "4" ) ;
      bFour.setBorder( raisedBorder ) ;
      bFive   = new JButton( "5" ) ;
      bFive.setBorder( raisedBorder ) ;
      bSix    = new JButton( "6" ) ; 
      bSix.setBorder( raisedBorder ) ;
      bSeven  = new JButton( "7" ) ;
      bSeven.setBorder( raisedBorder ) ;
      bEight  = new JButton( "8" ) ;
      bEight.setBorder( raisedBorder ) ;
      bNine   = new JButton( "9" ) ;
      bNine.setBorder( raisedBorder ) ;
      bClear   = new JButton( "Clear" ) ;
      //bClear.setBackground( Color.blue ) ;
      //bClear.setForeground( Color.white );
      bClear.setBorder( raisedBorder ) ;
      bMult   = new JButton( "  *  " ) ;
      bMult.setFont( new Font("Sanserif", Font.BOLD, 20 ) );
      //bMult.setBackground( Color.blue ) ;
      //bMult.setForeground( Color.white );
      bMult.setBorder( raisedBorder ) ;
      bDiv    = new JButton( "  /  " ) ;
      bDiv.setBorder( raisedBorder ) ;
      bDiv.setFont( new Font("Sanserif", Font.BOLD, 20 ) );
      //bDiv.setBackground( Color.blue ) ;
     // bDiv.setForeground( Color.white );
      bSub    = new JButton( "  -  " ) ;
      bSub.setFont( new Font("Sanserif", Font.BOLD, 24 ) );
      //bSub.setBackground( Color.blue ) ;
      //bSub.setForeground( Color.white );
      bSub.setBorder( raisedBorder ) ;
      bPlus   = new JButton( "  +  " ) ;
      //bPlus.setBackground( Color.blue ) ;
      //bPlus.setForeground( Color.white );  
      bPlus.setBorder( raisedBorder ) ;

      bEquals = new JButton( "  ENTER  " ) ;
      bEquals.setBorder( raisedBorder ) ;
      bEquals.setFont( new Font("Sanserif", Font.BOLD, 16 ) );
      //bEquals.setBackground( Color.red ) ;
      //bEquals.setForeground( Color.white );
      bDecPt = new JButton( "." ) ;
      bDecPt.setFont( new Font("Sanserif", Font.BOLD, 22 ) );
      bDecPt.setBorder( raisedBorder ) ;


      /** add the following buttons to the buttonPanel panel. */
      buttonPanel.add( bSeven ) ;
      buttonPanel.add( bEight ) ;
      buttonPanel.add( bNine ) ;

      buttonPanel.add( bFour ) ;
      buttonPanel.add( bFive ) ;
      buttonPanel.add( bSix ) ;
      buttonPanel.add( bOne ) ;
      buttonPanel.add( bTwo ) ;
      buttonPanel.add( bThree ) ;


      buttonPanel.add( bZero ) ;
      buttonPanel.add( bDecPt ) ;
      buttonPanel.add( bEquals ) ;

      buttonPanel.setLayout( new GridLayout( 5, 3, 5, 5  ) );
   }


   /** **************************************************
    *  The setUpActionListener() method assigns an event
    *  handler to the button and indicate that the
    *  responses will be handled inside the BasicCalc
    *  class,
    ******************************************************/
   public void setUpActionListener()  {
      bDecPt.addActionListener( this );
      bZero.addActionListener( this );
      bOne.addActionListener( this );
      bTwo.addActionListener( this );
      bThree.addActionListener( this );
      bFour.addActionListener( this );
      bFive.addActionListener( this );
      bSix.addActionListener( this );
      bSeven.addActionListener( this );
      bEight.addActionListener( this );
      bNine.addActionListener( this );
      bExit.addActionListener( this );
      bMult.addActionListener( this );
      bDiv.addActionListener( this );
      bSub.addActionListener( this );
      bPlus.addActionListener( this );
      bEquals.addActionListener( this );
      bClear.addActionListener( this );

      bExit.addActionListener( this );
      bHelp.addActionListener( this );
      bAbout.addActionListener( this );
   }

/** *****************************************************
 *  The setUpFunctionPanel() method creates the function
 *  panel and adds its buttons.
 ********************************************************/
   public void setUpFunctionPanel() {

      functionPanel = new JPanel() ;
      c.add( functionPanel , BorderLayout.EAST ) ;
      functionPanel.setLayout( new GridLayout( 6, 1, 5, 3  ) );

      functionPanel.add( bMult ) ;
      functionPanel.add( bDiv ) ;
      functionPanel.add( bSub ) ;
      functionPanel.add( bPlus ) ;
      functionPanel.add( bClear ) ;
   }

   /** *******************************************
    * The setUpExitPanel() method creates and
    * sets up the background color the foreground
    * text for the buttons.
    ******************************************** */
   public void setUpExitPanel() {
      exitPanel = new JPanel() ;
      c.add( exitPanel , BorderLayout.SOUTH ) ;
      bExit   = new JButton( "   Exit   " ) ;
      bExit.setFont( new Font("Sanserif", Font.BOLD, 16 ) );
      bExit.setBorder( raisedBorder ) ;
      //bExit.setBackground( Color.red ) ;
      //bExit.setForeground( Color.white );
      bDecPt.setBorder( raisedBorder ) ;
      bHelp   = new JButton( "  Help  " ) ;
      bHelp.setFont( new Font("Sanserif", Font.BOLD, 16 ) );
      bHelp.setBorder( raisedBorder ) ;
      //bHelp.setBackground( Color.blue ) ;
      //bHelp.setForeground( Color.white );
      bAbout = new JButton( "  About  " ) ;
      bAbout.setFont( new Font("Sanserif", Font.BOLD, 14 ) );
      bAbout.setBorder( raisedBorder ) ;
      //bAbout.setBackground( Color.darkGray ) ;
      //bAbout.setForeground( Color.white );

      /** add the following buttons to the exitPanel panel. */
      exitPanel.add( bExit ) ;
      exitPanel.add( bHelp ) ;
      exitPanel.add( bAbout ) ;

   }

   /**  *********************************************************
    * The actionPerformed() method is the method that is called to
    * respond to a button event.
    * ************************************************************/
   public void actionPerformed( ActionEvent e )    {

      tempNum = ""  + answer ;

      if ( e.getSource() == bPlus || e.getSource() == bSub ||
              e.getSource() == bMult || e.getSource() == bDiv  ) {
         tempNum = ""  + answer ;

         MyPrint( "actionPerformed() 1a: The value of result is " + result +
               " The value of tempNum is " + enterBox.getText() +
                " The value of opCode is " + opCode ) ;
      }

      if ( result ) {
         /** If result =s true, set the text field to blank */
         MyPrint( "actionPerformed() 1b: The value of result is " + result +
                  "The value of tempNum is " + enterBox.getText()
         );
         result = false ;
         enterBox.setText( "" );
      }

      if ( !pendingOp && opCode == 99 ) {
         /** If opCode =s 99, then the =s button was pressed */
         MyPrint( "actionPerformed() 1c: The value of opCode is " + opCode +
                   " The value of firstNum is " + firstNum  +
                   " The value of tempNum is " + tempNum );
         pendingOpCode = -1 ;
         //opCode = -1 ;
         firstNumSet = true ;
         firstNum = tempNum ;
         enterBox.setText( "" );
      }

      if ( e.getSource() == bDecPt ) {
         MyPrint( "The decimal point button was pressed." );
         enterBox.setText( enterBox.getText() + "." ) ;
      }
      else if ( e.getSource() == bZero ) {
         MyPrint( "The zero button was pressed. And pendingOp is " + pendingOp );
         enterBox.setText( enterBox.getText() + "0" ) ;
      }
      else  if ( e.getSource() == bOne ) {
         MyPrint( "The one button was pressed. And pendingOp is " + pendingOp  );
         enterBox.setText( enterBox.getText() + "1" ) ;
      }
      else  if ( e.getSource() == bTwo ) {
         MyPrint( "The two button was pressed. And pendingOp is " + pendingOp );
         enterBox.setText( enterBox.getText() + "2" ) ;
      }
      else  if ( e.getSource() == bThree ) {
         MyPrint( "The Three button was pressed. And pendingOp is " + pendingOp);
         enterBox.setText( enterBox.getText() + "3" ) ;
      }
      else  if ( e.getSource() == bFour ) {
         MyPrint( "The Four button was pressed. And pendingOp is " + pendingOp );
         enterBox.setText( enterBox.getText() + "4" ) ;
      }
      else  if ( e.getSource() == bFive ) {
         MyPrint( "The Five button was pressed. And pendingOp is " + pendingOp );
         enterBox.setText( enterBox.getText() + "5" ) ;
      }
      else  if ( e.getSource() == bSix ) {
         MyPrint( "The Six button was pressed. And pendingOp is " + pendingOp );
         enterBox.setText( enterBox.getText() + "6" ) ;
      }
      else  if ( e.getSource() == bSeven ) {
         MyPrint( "The Seven button was pressed. And pendingOp is " + pendingOp );
         enterBox.setText( enterBox.getText() + "7" ) ;
      }
      else  if ( e.getSource() == bEight ) {
         MyPrint( "The Eight button was pressed. And pendingOp is " + pendingOp );
         enterBox.setText( enterBox.getText() + "8" ) ;
      }
      else  if ( e.getSource() == bNine ) {
         MyPrint( "The Nine button was pressed. And pendingOp is " + pendingOp );
         enterBox.setText( enterBox.getText() + "9" ) ;
      }
      else  if ( e.getSource() == bExit ) {
         MyPrint( "The Exit button was pressed." );
         sysExit() ;
      }
      else  if ( e.getSource() == bMult ) {
         MyPrint( "The Mult button was pressed. And pendingOp is " + pendingOp  );
         multOp() ;
      }
      else  if ( e.getSource() == bDiv ) {
         MyPrint( "The Div button was pressed. And pendingOp is " + pendingOp  );
         divOp() ;
      }
      else  if ( e.getSource() == bSub ) {
         MyPrint( "The Sub button was pressed. And pendingOp is " + pendingOp  );
         subOp() ;
      }
      else  if ( e.getSource() == bPlus ) {
         MyPrint( "The Plus button was pressed. And pendingOp is " + pendingOp  );
         plusOp()    ;
      }
      else  if ( e.getSource() == bClear ) {
         MyPrint( "The bClear button was pressed." );
         enterBox.setText( "" )  ;
         firstNum = "" ;
         firstNumSet = true ;
         secondNum =  ""   ;
         answer = 0.0 ;
         dNum1 = 0.0 ;
         dNum2 = 0.0 ;
         opCode = -1 ;
         pendingOpCode = -1 ;
         pendingOp = false ;
      }
      else  if ( e.getSource() == bEquals ) {
         MyPrint( "bEquals 1: The Equals button was pressed. And pendingOp is " + pendingOp  +
             " The opCode value is " + opCode );
         if ( pendingOpCode >= 0 || pendingOpCode <= 3 ) {
            MyPrint( "bEquals 2: In pendingOpCode =s " + pendingOpCode ) ;
            pendingOp = true ;
            opCodeMethod() ;
         }
      }
      else  if ( e.getSource() == bHelp ) {
         MyPrint( "The Help button was pressed." );
         File hd = new File("BasicCalc_help_doc.html");
         //File net = new File("Netscp.exe");
         String helpStr = hd.getAbsolutePath() ;
         Runtime rt = Runtime.getRuntime();
         /* String[] callAndArgs = { "c:\\Program Files\\Internet Explorer\\IExplore.exe" ,
                          "" +
         E:\MyExamples\SimpleCalc\Calc\help_doc.html }; */
         String[] callAndArgs = { "c:\\Program Files\\Internet Explorer\\IExplore.exe" ,
                          "" + helpStr };

            try {

               Process child = rt.exec( callAndArgs );
               child.waitFor();
               MyPrint ("Process exit code is: " +
                                 child.exitValue());
            }
            catch(IOException e2) {
               MyPrint (
                  "IOException starting process!");
            }
            catch(InterruptedException e3) {
               System.err.println(
                     "Interrupted waiting for process!");
            }
      }
      else  if ( e.getSource() == bAbout ) {
         MyPrint( "The About  button was pressed." );
            Runtime rt = Runtime.getRuntime();
            String[] callAndArgs = { "c:\\Program Files\\Internet Explorer\\IExplore.exe" ,
                           "http://sumtotalz.com/TotalAppsWorks/index.html " };
            try {
               Process child = rt.exec(callAndArgs);
               child.waitFor();
               MyPrint ("Process exit code is: " +
                                 child.exitValue());
            }
            catch(IOException e2) {
                MyPrint( "IOException starting process!");
            }
            catch(InterruptedException e3) {
               MyPrint( "Interrupted waiting for process!");
            }
      }

   }
   /** ***** End of actionPerformed() ****************/

   /** **********************************************************
    * setResults() - enters the result of an operation
    * 1- In the textbox, firstNum and dNum1
    * 2- Sets the indicator that the first number is set
    * 3- Sets the indicator that a result has been reached
    * 4- Indicates that there is no operation pending.
    * ***********************************************************/
   public void setResults() {
      MyPrint( "setResults() 1: answer is " + answer + " dNum1 is " + dNum1 );

      enterBox.setText( "" + answer );
      firstNum = "" + answer ;
      dNum1 =  answer;
      firstNumSet = true ;
      result  = true ;
      pendingOp = false ;
   }
   /** ***** End of setResults() ****************/

/** ************************************************************
 * opCodeMethod() - determines if there is a pending op
 * to perform
 *  - if so, that operation is performed
 *  - if not, the current operation is performed
 *  ************************************************************/
   public void opCodeMethod() {
      int currentOpCode = -1 , tempOpCode = opCode ;
      boolean temppendingOp = pendingOp ;

      MyPrint( "opCodeMethod() 1: with an opCode of " + opCode );

      /** Check if there is a pending operation that must
       *  be performed before the current operation is
       *  performed. */
      if ( pendingOp ) {
         MyPrint( "opCodeMethod() 2: with an opCode of " + opCode +
                                        " and a pendingOpCode =s " + pendingOpCode );
         currentOpCode = pendingOpCode ;
         pendingOp = false ;
      }
      else {
         currentOpCode = opCode ;
         MyPrint( "opCodeMethod() 2b: with an opCode of " + opCode ) ;
      }

      switch ( currentOpCode ) {
         case 0 :
            multOp() ;
         break ;
         case 1 :
            divOp() ;
         break ;
         case 2 :
            MyPrint( "opCodeMethod() 3: opCode =s " + currentOpCode ) ;
            subOp() ;
         break ;
         case 3 :
            MyPrint( "opCodeMethod() 4: opCode =s " + currentOpCode ) ;
            plusOp() ;
         break ;
         case 5 :
           //powerOp() ;
         break ;
         default :
            MyPrint( "opCodeMethod() 7: default case is " + currentOpCode );
            setResults() ;
            firstNumSet = true ;
            secondNum = "0.0" ;
            dNum2 = 0.0 ;
            pendingOp = false ;
            opCode = 99 ;
            MyPrint( "opCodeMethod() 8: Case default: firstNum =s " + firstNum);
         break ;
      }

      MyPrint( "opCodeMethod() 9: pendingOpCode =s " + pendingOpCode +
                   " pendingOp " +  pendingOp );

      pendingOpCode = tempOpCode ;
      pendingOp = temppendingOp ;

      if ( !( opCode == 99 ) )
         opCode = -1 ;

   }
   /** ********** End of opCodeMethod() **************/

/** *****************************************************
 * multOp() method multiplies two numbers. The logic is:
 * 1- If the pending operation Code equals 0 then
 *   a) Get the second number
 *   b) Convert the numbers to double
 *   c) Multiply the numbers
 *   d) Indicate there are no pending operations
 *   e) Call setResults()
 * 2- Check for pending operations other than multiplication
 * 3- It must be multiplication operation, so get the first number
 *
 *  Version 1.1
 * Changed 9/10/2003 - Fixed chained operation errors
  ******************************************************/
   public void multOp() {
      opCode = 0 ;

      MyPrint( "multOp() 1a: The opcode value is " + opCode +
                  " The value of firstNum is " + firstNum +
                  " The value of answer is " + answer ) ;

      if ( pendingOpCode == 0  ) {
         secondNum = enterBox.getText()  ;
         if ( secondNum.equals( "" ) ) {
            secondNum = "0.0" ;
         }
         convertNumsToDouble() ;
         answer = dNum1 * dNum2 ;
         pendingOp = false ;
         setResults() ;
         MyPrint( "multOp() 2: opCode is " + opCode + " The value of answer is " + answer );
         secondNum = "0.0"  ;
         opCode = -1 ;

      }
      else if ( pendingOpCode >= 1 ) {
         MyPrint( "multOp() 2a: In pendingOpCode =s " + pendingOpCode ) ;
         pendingOp = true ;
         opCodeMethod() ;
      }
      else if ( opCode == 0  ) {
         MyPrint( "multOp() 3: In pendingOp code. The opcode value is " + opCode ) ;
         firstNum = enterBox.getText()  ;
         if ( secondNum.equals( "" ) ) {
            secondNum = "0.0" ;
         }
         opCode = 0 ;
         pendingOp = false ;
         pendingOpCode = 0 ;
         MyPrint( "multOp() 5: opCode is " + opCode + " The value of answer is " + answer );
         opCode = -1 ;
         result = true ;
         MyPrint( "multOp() 4: pendingOpCode is " + pendingOpCode + " The value of answer is " + answer );
      }

   }
   /******************************* End of multOp() ***************************/

/** ********************************************************************************
 * divOp() - divides the two entered numbers and checks for a zero divisor
 * 1- If the pending operation Code equals 1 then
 *   a) Get the second number
 *   b) Convert the numbers to double
 *   c) If the first number is zero, then the answer is zero
 *   d) If the second number is zero, then indicate that dividing by zero is not
 *      allowed
 *   e) Otherwise, multiply the numbers
 *   f) Indicate there are no pending operations
 *   g) Call setResults()
 * 2- Check for pending operations other than divide
 * 3- It must be divide operation, so get the first number
 * Version 1.1
 * Changed 9/10/2003 - Fixed chained operation errors
 ***********************************************************************************/
   public void divOp() {
      MyPrint( "divOp() 1a: opCode is " + opCode + " firstNumSet =s " + firstNumSet  +
                " pendingOpCode =s " + pendingOpCode );
      opCode = 1 ;

      MyPrint( "divOp() 1b: The opcode value is " + opCode +
                  " The value of firstNum is " + firstNum +
                  " The value of answer is " + answer ) ;

      if ( pendingOpCode == 1  ) {
         secondNum = enterBox.getText()  ;
         if ( secondNum.equals( "" ) ) {
            secondNum = "0.0" ;
         }
         convertNumsToDouble() ;
         if ( dNum1 == 0.0 ) {
            answer = dNum1  ;
         }
         else if ( dNum2 == 0.0 ) {
            answer = dNum1  ;
            setResults() ;
            JOptionPane.showMessageDialog( this, "The number " +
                    dNum2 + " cannot be used as a denometer.\n" +
                    "You cannot divide by zero.",
                    "Attempted to Divide by Zero",
                    JOptionPane.ERROR_MESSAGE );
         }
         else {
            answer = dNum1 / dNum2 ;
         }
         pendingOp = false ;
         setResults() ;
         MyPrint( "divOp() 2: opCode is " + opCode + " The value of answer is " + answer );
         secondNum = "0.0"  ;
         opCode = -1 ;
      }
      else if ( pendingOpCode == 0 || pendingOpCode == 2 || pendingOpCode == 3 ) {
         MyPrint( "divOp() 2a: In pendingOpCode =s " + pendingOpCode ) ;
         pendingOp = true ;
         opCodeMethod() ;
      }
      else if ( opCode == 1  ) {
         MyPrint( "divOp() 3: In pendingOp code. The opcode value is " + opCode ) ;
         firstNum = enterBox.getText()  ;
         if ( secondNum.equals( "" ) ) {
            secondNum = "0.0" ;
         }
         opCode = 1 ;
         pendingOp = false ;
         pendingOpCode = 1 ;
         opCode = -1 ;
         result = true ;
         MyPrint( "divOp() 4: pendingOpCode is " + pendingOpCode +
                                  " The value of answer is " + answer );
      }

   }
   /******************************* End of divOp() *******************************/

/** ********************************************************************
 *  subOp() - subtracts the second number from the first number  The
 *  logic is:
 * 1- If the pending operation Code equals 2 then
 *   a) Get the second number
 *   b) Convert the numbers to double
 *   c) Subtract the second number from the first number
 *   d) Indicate there are no pending operations
 *   e) Call setResults()
 * 2- Check for pending operations other than subtraction
 * 3- It must be subtraction operation, so get the first number
 *
 *
 * Version 1.1
 * Changed 9/10/2003 - Fixed chained operation errors
 * *********************************************************************/
   public void subOp() {
      opCode = 2 ;

      MyPrint( "subOp() 1a: The opcode value is " + opCode +
                  " The value of firstNum is " + firstNum +
                  " The value of answer is " + answer +
                  " pendingOpCode =s " + pendingOpCode ) ;

      if ( pendingOpCode == 2  ) {
         secondNum = enterBox.getText()  ;
         if ( secondNum.equals( "" ) ) {
            secondNum = "0.0" ;
         }
         convertNumsToDouble() ;
         answer = dNum1 - dNum2 ;
         pendingOp = false ;
         setResults() ;
         MyPrint( "subOp() 2: opCode is " + opCode + " The value of answer is " + answer );
         secondNum = "0.0"  ;
         opCode = -1 ;

      }
      else if ( pendingOpCode == 0 || pendingOpCode == 1 || pendingOpCode == 3 ) {
         MyPrint( "subOp() 2a: In pendingOpCode =s " + pendingOpCode ) ;
         pendingOp = true ;
         opCodeMethod() ;
      }
      else if ( opCode == 2  ) {
         MyPrint( "subOp() 3: In pendingOp code. The opcode value is " + opCode ) ;
         firstNum = enterBox.getText()  ;
         if ( secondNum.equals( "" ) ) {
            secondNum = "0.0" ;
         }
        // convertNumsToDouble() ;
        // answer = dNum1 - dNum2 ;
         pendingOp = true ;
         pendingOpCode = 2 ;
         opCode = 2 ;
         result = true ;
         MyPrint( "subOp() 4: pendingOpCode is " + pendingOpCode +
                        " The value of answer is " + answer );
      }
   }
   /********************************** End of subOp() *********************************/

/** *****************************************************
 * plusOp() - adds the two entered numbers. The
 *  logic is:
 * 1- If the pending operation Code equals 3 then
 *   a) Get the second number
 *   b) Convert the numbers to double
 *   c) Add the second number from the first number
 *   d) Indicate there are no pending operations
 *   e) Call setResults()
 * 2- Check for pending operations other than addition
 * 3- It must be addition operation, so get the first number
 * Version 1.1
 * Changed 9/10/2003 - Fixed chained operation errors
 ***********************************************************/
   public void plusOp() {
      opCode = 3 ;

      MyPrint( "plusOp() 1a: The opcode value is " + opCode +
                  " The value of firstNum is " + firstNum +
                  " The value of answer is " + answer ) ;

      if ( pendingOpCode == 3  ) {
         secondNum = enterBox.getText()  ;
         if ( secondNum.equals( "" ) ) {
            secondNum = "0.0" ;
         }
         convertNumsToDouble() ;
         answer = dNum1 + dNum2 ;
         pendingOp = false ;
         setResults() ;
         MyPrint( "plusOp() 2: opCode is " + opCode + " The value of answer is " + answer );
         secondNum = "0.0"  ;
         opCode = -1 ;

      }
      else if ( pendingOpCode == 0 || pendingOpCode == 1 || pendingOpCode == 2 ) {
         MyPrint( "plusOp() 3: In pendingOpCode =s " + pendingOpCode ) ;
         pendingOp = true ;
         opCodeMethod() ;
      }
      else if ( opCode == 3  ) {
         MyPrint( "plusOp() 4: In pendingOp code. The opcode value is " + opCode ) ;
         firstNum = enterBox.getText()  ;
         if ( secondNum.equals( "" ) ) {
            secondNum = "0.0" ;
         }
         opCode = 3 ;
         pendingOp = false ;
         pendingOpCode = 3 ;
         MyPrint( "plusOp() 5: opCode is " + opCode + " The value of answer is " + answer );
         opCode = -1 ;
         result = true ;
      }

   }
   /********************** End of plusOp() ******************************/

/**  **************************************************************
 * convertNumsToDouble() - converts numbers entered from string
 * to double
 * *****************************************************************/
   public void convertNumsToDouble() {
      if ( !firstNum.equals( "" ) ) {
         dNum1 = Double.parseDouble( firstNum ) ;
      }
      if ( !secondNum.equals( "" ) ) {
         dNum2 = Double.parseDouble( secondNum ) ;
      }
      MyPrint( "convertNumsToDouble(): The value of dNum1 is " + dNum1 );
      MyPrint( "convertNumsToDouble(): The value of dNum2 is " + dNum2 );

   }
   /** ********************* End of convertNumsToDouble() **************/

/**  ***********************************************************
 * sysExit() - causes the application to exit
 *************************************************************/
   public void sysExit()   {
      System.exit( 0 );

   }
   /** ********** End of sysExit() **************/

/**  *******************************************************
 * MyPrint() - toggles debug aid on or off. In debugging mode,
 * this method prints messages  that can be used in debugging.
 * ******************************************************/
   public void MyPrint( String str )   {
      if ( myDiag )
         System.out.println( str );

   }
   /** ********** End of MyPrint() **************/

   /**   *****************************************************
    * The main() method is the entry point called by Java when
    * this application is loaded. The logic is:
    * 1- Create an instane of the BasicCalc class
    * 2- Create a window closing event for this program
    * ******************************************************/

   public static void main( String args[] ) {

      final BasicCalc app = new BasicCalc () ; /** creates an instance of MyCalc  */

      app.addWindowListener(
         new WindowAdapter() {
            public void windowClosing( WindowEvent e )
            {
               app.sysExit()  ;
            }
         }
      );
   }
   /** ********** End of main() **************/


   class LogoWindowFrame extends Frame {
      LogoWindow sw;
      Image logoIm;

      LogoWindowFrame() {
         MediaTracker mt = new MediaTracker(this);
         logoIm = Toolkit.getDefaultToolkit().getImage("LogoScreen.gif");
         mt.addImage(logoIm,0);
         try {
            mt.waitForID(0);
         }
         catch(InterruptedException ie){}

         sw = new LogoWindow( this, logoIm );

         try {
           Thread.sleep(3000);
         }
         catch(InterruptedException ie){}

         sw.dispose();
      }
   }

   class LogoWindow extends Window {
      Image logoIm;
      Thread currentThread = Thread.currentThread() ;

      LogoWindow(Frame parent, Image logoIm) {
         super(parent);
         this.logoIm = logoIm;
         setSize(650,400);

         /* Center the window */
         Dimension screenDim =
             Toolkit.getDefaultToolkit().getScreenSize();
         Rectangle winDim = getBounds();
         setLocation((screenDim.width - winDim.width) / 2,
		(screenDim.height - winDim.height) / 2);
         setVisible(true);
      }

      public void paint(Graphics g) {
         if (logoIm != null) {
            g.drawImage(logoIm,0,0,this);
            try {
               currentThread.sleep(1500);
            }
            catch (InterruptedException e) {}
         }
      }
   }

}
/** ********** End of MyCalc Class **************/





