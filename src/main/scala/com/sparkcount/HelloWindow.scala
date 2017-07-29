package com.sparkcount

object HelloWindow {

  def main(args: Array[String]): Unit = {
    import javax.swing._, java.awt.event._
    val frame = new JFrame("Hello World Window")

    val button = new JButton("Click Me")
    button.addActionListener(new ActionListener{
      def actionPerformed(e: ActionEvent) = button.setText("You clicked the button!")
    })
    button.setPreferredSize(new java.awt.Dimension(200, 100))
    frame.getContentPane.add(button)
    frame.pack()
    frame.setVisible(true)
  }
}
