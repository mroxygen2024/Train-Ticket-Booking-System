
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class TrainBooking extends JFrame {

  private RoundedTextField tfName, tfFrom, tfTo, tfDate;
  private RoundedComboBox<String> cbTrainList;
  private RoundedButton btnSearch, btnBook, btnClear, btnSave, btnViewTicket;
  private JTextArea taTicket;
  private ArrayList<String> trains = new ArrayList<>();

  public TrainBooking() {
    setTitle("🚆 Train Ticket Booking");
    setSize(800, 600);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());
    getContentPane().setBackground(Color.WHITE);

    // Header
    JLabel header = new JLabel("Train Booking System", JLabel.CENTER);
    header.setFont(new Font("Segoe UI", Font.BOLD, 24));
    header.setOpaque(true);
    header.setBackground(Color.BLACK);
    header.setForeground(Color.WHITE);
    header.setPreferredSize(new Dimension(400, 60));
    add(header, BorderLayout.NORTH);

    // Form panel (vertical box layout)
    JPanel formPanel = new JPanel();
    formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
    formPanel.setBackground(Color.WHITE);
    formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    // Text fields with placeholders
    tfName = new RoundedTextField("  Your Name");
    tfFrom = new RoundedTextField("  From");
    tfTo = new RoundedTextField("  To");
    tfDate = new RoundedTextField("  Date (dd-mm-yyyy)");

    // Combo box
    cbTrainList = new RoundedComboBox<>();
    cbTrainList.setPreferredSize(new Dimension(360, 45));

    // Buttons
    btnSearch = new RoundedButton("  Search Trains");
    btnBook = new RoundedButton("  Book Ticket");
    btnClear = new RoundedButton("Clear");
    btnSave = new RoundedButton("Save Ticket");
    btnViewTicket = new RoundedButton("View Ticket");

    // Add components with spacing
    formPanel.add(tfName);
    formPanel.add(Box.createVerticalStrut(15));
    formPanel.add(tfFrom);
    formPanel.add(Box.createVerticalStrut(15));
    formPanel.add(tfTo);
    formPanel.add(Box.createVerticalStrut(15));
    formPanel.add(tfDate);
    formPanel.add(Box.createVerticalStrut(15));
    formPanel.add(btnSearch, BorderLayout.CENTER);
    formPanel.add(Box.createVerticalStrut(20));
    formPanel.add(cbTrainList);
    formPanel.add(Box.createVerticalStrut(20));

    // Ticket TextArea
    taTicket = new JTextArea(8, 30);
    taTicket.setEditable(false);
    taTicket.setFont(new Font("Monospaced", Font.PLAIN, 14));
    taTicket.setBorder(BorderFactory.createTitledBorder("Ticket Details"));
    JScrollPane scroll = new JScrollPane(taTicket);
    scroll.setPreferredSize(new Dimension(360, 180));
    formPanel.add(scroll);
    formPanel.add(Box.createVerticalStrut(20));

    // Buttons panel horizontally
    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setBackground(Color.WHITE);
    buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
    buttonsPanel.add(btnBook);
    buttonsPanel.add(btnViewTicket);
    buttonsPanel.add(btnSave);
    buttonsPanel.add(btnClear);

    // Add panels to frame
    add(formPanel, BorderLayout.CENTER);
    add(buttonsPanel, BorderLayout.SOUTH);

    // Sample trains
    trains.add("1001 - Express A");
    trains.add("1002 - Express B");
    trains.add("1003 - Superfast C");
    trains.add("1004 - Local D");

    // Button Actions
    btnSearch.addActionListener(e -> searchTrains());
    btnBook.addActionListener(e -> bookTicket());
    btnClear.addActionListener(e -> clearFields());
    btnSave.addActionListener(e -> saveTicket());
    btnViewTicket.addActionListener(e -> showTicketPage());

    setResizable(false);
  }

  private void searchTrains() {
    cbTrainList.removeAllItems();
    if (tfFrom.isPlaceholderOrEmpty() || tfTo.isPlaceholderOrEmpty()) {
      JOptionPane.showMessageDialog(this, "Please enter both 'From' and 'To' stations.", "Missing Input",
          JOptionPane.WARNING_MESSAGE);
      return;
    }
    for (String train : trains)
      cbTrainList.addItem(train);
    JOptionPane.showMessageDialog(this, "Available trains loaded.", "Trains Found",
        JOptionPane.INFORMATION_MESSAGE);
  }

  private void bookTicket() {
    if (tfName.isPlaceholderOrEmpty() || tfFrom.isPlaceholderOrEmpty() || tfTo.isPlaceholderOrEmpty()
        || tfDate.isPlaceholderOrEmpty() || cbTrainList.getSelectedItem() == null) {
      JOptionPane.showMessageDialog(this, "Please complete all fields and search for a train.", "Incomplete Form",
          JOptionPane.WARNING_MESSAGE);
      return;
    }
    StringBuilder ticket = new StringBuilder();
    ticket.append("========= TRAIN TICKET =========\n");
    ticket.append("👤 Name   : ").append(tfName.getText()).append("\n");
    ticket.append("📍 From   : ").append(tfFrom.getText()).append("\n");
    ticket.append("🏁 To     : ").append(tfTo.getText()).append("\n");
    ticket.append("📅 Date   : ").append(tfDate.getText()).append("\n");
    ticket.append("🚆 Train  : ").append(cbTrainList.getSelectedItem()).append("\n");
    ticket.append("================================\n");
    ticket.append("✅ Booking Confirmed!\n");
    taTicket.setText(ticket.toString());
    JOptionPane.showMessageDialog(this, ticket.toString(), "Ticket Booked", JOptionPane.INFORMATION_MESSAGE);
  }

  private void clearFields() {
    tfName.reset();
    tfFrom.reset();
    tfTo.reset();
    tfDate.reset();
    cbTrainList.removeAllItems();
    taTicket.setText("");
  }

  private void saveTicket() {
    if (taTicket.getText().isEmpty()) {
      JOptionPane.showMessageDialog(this, "No ticket to save!", "Save Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    try (FileWriter writer = new FileWriter("ticket.txt")) {
      writer.write(taTicket.getText());
      JOptionPane.showMessageDialog(this, "Ticket saved to ticket.txt", "Saved", JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, "Error saving ticket.", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  private void showTicketPage() {
    if (taTicket.getText().isEmpty()) {
      JOptionPane.showMessageDialog(this, "No ticket to view!", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    // Show new window with ticket text
    JFrame ticketFrame = new JFrame("Your Ticket");
    JTextArea ticketArea = new JTextArea(taTicket.getText());
    ticketArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
    ticketArea.setEditable(false);
    ticketArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    ticketFrame.add(new JScrollPane(ticketArea));
    ticketFrame.setSize(350, 400);
    ticketFrame.setLocationRelativeTo(this);
    ticketFrame.setVisible(true);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new TrainBooking().setVisible(true));
  }

}

// RoundedTextField with placeholder clear on typing, hover, rounded edges
class RoundedTextField extends JTextField {
  private Color baseColor = new Color(245, 245, 245);
  private Color hoverColor = new Color(220, 220, 220);
  private Color currentColor = baseColor;
  private String placeholder;

  public RoundedTextField(String placeholder) {
    super(placeholder);
    this.placeholder = placeholder;
    setOpaque(false);
    setForeground(Color.GRAY);
    setFont(new Font("Segoe UI", Font.PLAIN, 14));
    setBorder(null);
    setPreferredSize(new Dimension(360, 45));
    setMargin(new Insets(10, 15, 10, 15));

    // Hover color
    addMouseListener(new MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        currentColor = hoverColor;
        repaint();
      }

      public void mouseExited(MouseEvent e) {
        currentColor = baseColor;
        repaint();
      }
    });

    // Focus to clear placeholder
    addFocusListener(new FocusAdapter() {
      public void focusGained(FocusEvent e) {
        if (getText().equals(placeholder)) {
          setText("");
          setForeground(Color.BLACK);
        }
      }

      public void focusLost(FocusEvent e) {
        if (getText().isEmpty()) {
          setText(placeholder);
          setForeground(Color.GRAY);
        }
      }
    });

    // Clear on first key typed
    addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent e) {
        if (getText().equals(placeholder)) {
          setText("");
          setForeground(Color.BLACK);
        }
      }
    });
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setColor(currentColor);
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
    super.paintComponent(g2);
    g2.dispose();
  }

  @Override
  protected void paintBorder(Graphics g) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setColor(Color.LIGHT_GRAY);
    g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
    g2.dispose();
  }

  // Helper to check if field is empty or showing placeholder
  public boolean isPlaceholderOrEmpty() {
    return getText().isEmpty() || getText().equals(placeholder);
  }

  // Reset to placeholder
  public void reset() {
    setText(placeholder);
    setForeground(Color.GRAY);
  }
}

// RoundedComboBox with rounded edges and hover effect
class RoundedComboBox<E> extends JComboBox<E> {
  private Color baseColor = Color.WHITE;
  private Color hoverColor = new Color(230, 230, 230);
  private boolean hovered = false;

  public RoundedComboBox() {
    setOpaque(false);
    setFont(new Font("Segoe UI", Font.PLAIN, 14));
    setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    setPreferredSize(new Dimension(360, 45));

    addMouseListener(new MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        hovered = true;
        repaint();
      }

      public void mouseExited(MouseEvent e) {
        hovered = false;
        repaint();
      }
    });
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g.create();

    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    if (hovered)
      g2.setColor(hoverColor);
    else
      g2.setColor(baseColor);

    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
    super.paintComponent(g2);
    g2.dispose();
  }

  @Override
  protected void paintBorder(Graphics g) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setColor(Color.LIGHT_GRAY);
    g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
    g2.dispose();
  }
}

// RoundedButton with black & white theme and hover effect
class RoundedButton extends JButton {
  private Color baseColor = Color.BLACK;
  private Color hoverColor = new Color(30, 30, 30);
  private Color textColor = Color.WHITE;
  private Color hoverTextColor = Color.WHITE;
  private boolean hovered = false;

  public RoundedButton(String text) {
    super(text);
    setFocusPainted(false);
    setContentAreaFilled(false);
    setOpaque(false);
    setFont(new Font("Segoe UI", Font.BOLD, 16));
    setPreferredSize(new Dimension(150, 45));
    setForeground(textColor);
    setBorder(null);
    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    addMouseListener(new MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        hovered = true;
        repaint();
      }

      public void mouseExited(MouseEvent e) {
        hovered = false;
        repaint();
      }
    });
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g2.setColor(hovered ? hoverColor : baseColor);
    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

    g2.setColor(hovered ? hoverTextColor : textColor);

    FontMetrics fm = g2.getFontMetrics();
    int textWidth = fm.stringWidth(getText());
    int textHeight = fm.getAscent();

    int x = (getWidth() - textWidth) / 2;
    int y = (getHeight() + textHeight) / 2 - 3;

    g2.drawString(getText(), x, y);

    g2.dispose();
  }

  @Override
  protected void paintBorder(Graphics g) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setColor(Color.DARK_GRAY);
    g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
    g2.dispose();
  }
}
