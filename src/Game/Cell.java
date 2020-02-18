package Game;
import Game.*;
import Piece.*;
import java.awt.*;
import javax.swing.*;

public class Cell extends JPanel implements Cloneable{
    private boolean ispossibledestination = false;
    private Piece piece = null;
    public int x,y;
    boolean isSelected = false;
    boolean isCheck = false;
    private JLabel content;

    //constructor

    public Cell(int x, int y , Piece p) {
        this.x = x;
        this.y = y;

        setLayout(new BorderLayout());
        if ((x+y) % 2 == 0) setBackground(Color.GRAY);
        else setBackground(Color.white);
        if(p!=null) setPiece(p);
    }

    public Cell(Cell cell) throws CloneNotSupportedException
    {
        this.x=cell.x;
        this.y=cell.y;

        setLayout(new BorderLayout());

        if((x+y)%2==0) setBackground(Color.GRAY);
        else setBackground(Color.white);

        if(cell.getPiece()!=null) setPiece(cell.getPiece().getcopy());
        else piece=null;
    }
    

    public void setPiece(Piece p) {
        piece = p;
        ImageIcon img = new javax.swing.ImageIcon(this.getClass().getResource("/ChessGame/images/"+p.getPath()));
        content = new JLabel(img);
        this.add(content);
    }

    public void removePiece() {
        piece = null;
        this.remove(content);
    }

    public Piece getPiece() {
        return this.piece;
    }
    

    public void select() {
        this.setBorder(BorderFactory.createLineBorder(Color.red,6));
        this.isSelected = true;
    }

    public void deselect() {
        this.setBorder(null);
        this.isSelected = false;
    }

    public void setpossibledestination() {
        if (getPiece() != null && Handler.turn != getPiece().getColor()){
            this.setBorder(BorderFactory.createLineBorder(Color.yellow,7));
        }else {
            this.setBorder(BorderFactory.createLineBorder(Color.green,6));
            this.ispossibledestination = true;
        }
    }
    
    public void setAttacker () {
        this.setBackground(new Color(13, 71, 161));
    }
    
    public void removeAttacker () {
        this.setBorder(null);
        if((x+y)%2 == 0) setBackground(Color.GRAY);
        else setBackground(Color.white);
        this.isCheck = false;
    }

    public void removepossibledestination() {
        this.setBorder(null);
        this.ispossibledestination = false;
    }

    public boolean ispossibledestination () {
        return this.ispossibledestination;
    }

    public void setCheck() {
        this.setBackground(Color.RED);
        this.isCheck = true;
    }

    public void removecheck() {
        this.setBorder(null);
        if((x+y)%2 == 0) setBackground(Color.GRAY);
        else setBackground(Color.white);
        this.isCheck = false;
    }

    public boolean isCheck() {
        return isCheck;
    }
}
