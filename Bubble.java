package com.example.user.bubbleshooter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import java.util.Random;

public class Bubble
{
    Sprite sprite;
    boolean alive;
    int row ;
    int column;
    int timer;
    final static Bitmap BITMAP = GameScene.bubbleMap;
    int color;
    int velocity;
    double theta;
    double deltaX ;
    double deltaY ;
    boolean checked ;
    static final private float inwordFactor = 0.15f;
    static final int VELOCITY = 30;
    int numS=55;
    static boolean blastFlag=false;

    public Bubble(int x, int y , int color)
    {
        sprite = new Sprite(BITMAP, GameScene.bubbleHeight, GameScene.bubbleWidth);
        reIntialize(x, y, color);
    }

    public Bubble reIntialize (int x,int y ,int color)
    {
        sprite.moveTo(x, y);
        column = (int) ( (sprite.x+ GameScene.bubbleSize/2) /GameScene.bubbleSize) ;
        row = (int) ( (sprite.y+ GameScene.bubbleSize/2) /GameScene.bubbleSize);
        alive = true;
        this.color = color;
        sprite.setCurrentVerFrame(color);
       // sprite.setCurrentScoreVerFrame(5);
        timer = 0;
        checked = false ;
        sprite.setCurrentHorFrame(0);
       // sprite.setCurrentHorScoreFrame(0);
        return this ;
    }

    private void calculateTheta(double cosTHETA,double sinTHETA)
    {
        deltaX=(velocity* cosTHETA);
        deltaY=( velocity* sinTHETA * -1);

    }

    public boolean updatePos()
    {
        double mX=sprite.x+deltaX+(double)GameScene.bubbleSize/2;
        double mY=sprite.y+deltaY+(double)GameScene.bubbleSize/2;
        double aX=SurfaceViewHandler.arraySpriteX+(double)GameScene.bubbleSize/2;
        double aY=SurfaceViewHandler.arraySpriteY+(double)GameScene.bubbleSize/2;

//		if (sprite.x < 0) ??? bta3t a dy :D
//			return true ;
       if(Math.abs(mX-aX)<=(2*GameScene.bubbleSize/Math.sqrt(2))&& Math.abs(mY-aY)<=(2*GameScene.bubbleSize/Math.sqrt(2)))
        {

//            if (colliedWithBubble())
//                return true;

            System.out.println("mmrow "+SurfaceViewHandler.rowNo);
            System.out.println("mmcol "+SurfaceViewHandler.columnNo);
          if (colliedWithBubble()) { // collide()
              System.out.println("mmtrue");
              return true;
          }
        }

        if (sprite.x+(float)GameScene.bubbleSize/2 <0 || (sprite.x + sprite.dispWidth+(float)GameScene.bubbleSize/2) >= SurfaceViewHandler.cWidth)
                deltaX*= -1 ;
        sprite.moveBy(deltaX, deltaY);
        return false ;
    }

    public void fire(double costheta,double sintheta)
    {
        velocity = VELOCITY ;
        calculateTheta(costheta, sintheta);
    }

    private boolean colliedWithBubble ()
    {
        float pointX=SurfaceViewHandler.lineEndPointX;
        float pointY=SurfaceViewHandler.lineEndPointY;
        float arrayX=SurfaceViewHandler.arraySpriteX;
        float arrayY=SurfaceViewHandler.arraySpriteY;
        float size=GameScene.bubbleSize;
         row=SurfaceViewHandler.rowNo;
         column=SurfaceViewHandler.columnNo;

        if(arrayX<=pointX && pointX<=arrayX+size/2 && arrayY+size/2<=pointY && pointY<=arrayY+size)
        {
            row=SurfaceViewHandler.rowNo+1;
            if(row%2==1 && column==0)
                column=0;
            else if(row%2==0 && column ==0)
                column=0;
             else if(row %2==1 && column==GameScene.array[0].length-1)
                column=GameScene.array[0].length-1;
             else if(row%2==0 && column == GameScene.array[0].length-1)
                column=GameScene.array[0].length-2;
            else
                column=SurfaceViewHandler.columnNo-1;

        }
        else if(arrayX+size/2<=pointX && pointX<= arrayX+size && arrayY+size/2<=pointY && pointY<=arrayY+size)
        {
            row=SurfaceViewHandler.rowNo+1;
            if(row%2==1 && column==0)
                column=0;
            else if(row%2 ==0 && column==0)
                column=column+1;
            else if(row %2 ==1 && column == GameScene.array[0].length-1)
                column=column-1;
            else if(row %2 ==0 && column == GameScene.array[0].length-1)
                column=column+1;
            else
                column=column+1;


        }
        else if(arrayX<=pointX && pointX<=arrayX+size/2 && arrayY<=pointY && pointY<=arrayY+size/2)
        {
            row=SurfaceViewHandler.rowNo;
            if(column==GameScene.array[0].length)
                column=column-1;
             else if(row%2==0)
            column=column-1;

        }
        else if(arrayX+size/2<=pointX && pointX<= arrayX+size && arrayY<=pointY && pointY<=arrayY+size/2)
        {
            row=SurfaceViewHandler.rowNo;
            if(column==GameScene.array[0].length)
                column=column-1;
            else if(row%2==0)
                column=column+1;
        }

//        double row1,row2,column1,column2;
//        int rowI1,rowI2,columnI1,columnI2;
//        row1= SurfaceViewHandler.rowNo;
//        row2= SurfaceViewHandler.rowNo+1;
//
//        rowI1 = (int)row1;
//        rowI2 = (int)row2;
//
//        column1=SurfaceViewHandler.columnNo;
//        column2=SurfaceViewHandler.columnNo+1;
//
//
//        columnI1 = (int) column1;
//        columnI2 = (int) column2;
//
//
//
//
//        if (rowI1 >= GameScene.array.length)
//            rowI1 = GameScene.array.length-1;
//
//        if (rowI2 >= GameScene.array.length)
//            rowI2 = GameScene.array.length-1;
//
//        if (columnI1 >= GameScene.array[0].length)
//            columnI1 = GameScene.array[0].length-1;
//
//        if (columnI2 >= GameScene.array[0].length)
//            columnI2 = GameScene.array[0].length-1;
//
//
//        if (columnI1 < 0)
//            columnI1 = 0;
//
//        if (columnI2 < 0)
//            columnI2 = 0;
      boolean   upLeft = false;
        boolean upRight= false;
        boolean downLeft= false;
        boolean downRight= false;
        System.out.println("mmc"+column);
if(row != 0 && column != 0)
    upLeft = GameScene.array[row - 1][column - 1] != null;
        if(row != 0 && column != 9)
            upRight = GameScene.array[row-1][column+1] != null ;
        if(column != 0)
        downLeft =   GameScene.array[row][column-1] != null ;
        if(column != 9)
        downRight =  GameScene.array[row][column+1] != null;

        if(upRight || upLeft || downRight || downLeft)
        {
            if(color == GameScene.superBubbleColor)
            {
                if(upLeft)
                    color =  GameScene.array[row-1][column-1].color;
                else if(upRight)
                    color =  GameScene.array[row-1][column+1].color;
                else if(downRight)
                    color = GameScene.array[row][column-1].color;
                else
                    color = GameScene.array[row][column+1].color;

                sprite.setCurrentVerFrame(color);
            }

//            if((upRight || upLeft ) && !(downLeft || downRight))
//            {
//                row = rowI1+1;
//            }
////            else if( (upRight || upLeft ) && !(downLeft || downRight))
////            {
////                row = rowI2-1;
////            }
//            else if ((upRight&& downRight) || (upLeft && downLeft))
//            {
//                row = (int) ((row1+row2)/2);
//            }
//            else if ((upLeft && downRight) ||(upRight && downLeft))
//            {
//                row = rowI1+1;
//            }
//            else
//            {
//                row = rowI1+1;
//            }
//
//            if( upLeft && downRight )
//            {
//                if(row%2 == 0)
//                {
//                    column = columnI1;
//                }
//                else
//                {
//                    column = columnI1;
//                }
//            }
//            else if (upRight && downLeft)
//            {
//                if(row%2 == 0)
//                {
//                    column = columnI1+1;
//                }
//                else
//                {
//                    column = columnI1+1;
//                }
//            }
//            else if(upLeft || downLeft)
//            {
//                if(row%2 == 0)
//                {
//                    column = columnI1 +1;
//                }
//                else
//                {
//                    column = columnI1;
//                }
//            }
//            else if(upRight || downRight)
//            {
//                if(row%2 == 0)
//                {
//                    column = columnI1+1 ;
//                }
//                else
//                {
//                    column = columnI1;
//                }
//            }
//
//            if(upLeft && downLeft)
//            {
//                column = columnI1+1;
//            }
//            else if(upRight && downRight)
//            {
//                column = columnI1 ;
//            }
//
//            if(columnI1 == columnI2)
//            {
//                column = columnI1;
//            }
//
//            if(row < 0)
//                row = 0;
//
//            if(row >= 	GameScene.array.length)
//                row = GameScene.array.length-1;
//
//            if(column < 0)
//                column = 0;
//
//            if(column > GameScene.array[0].length)
//                column = GameScene.array[0].length-1;
//
//            // if(SurfaceViewHandler.collideSpriteX>sprite.x+deltaX)

            if(GameScene.array[row][column] != null){
                panicRecover();
            }

            GameScene.array[row][column] = this;
            GameScene.array[row][column].sprite.rowNum=row;
            GameScene.array[row][column].sprite.columnNum=column;


            if (row%2 == 1)
            {
                sprite.animateTo((column)*GameScene.bubbleSize +(double) GameScene.bubbleSize/2, (row)*GameScene.bubbleSize  - row*GameScene.bubbleShiftup ,velocity );
            }
            else
            {
                sprite.animateTo((column)*GameScene.bubbleSize, (row)*GameScene.bubbleSize  - row*GameScene.bubbleShiftup , velocity );
            }

            return true;
        }


        return false ;
    }


    private boolean collied ()
    {
        double row1,row2,column1,column2;
        int rowI1,rowI2,columnI1,columnI2;
        row1 =  sprite.y+deltaY;
        row1= SurfaceViewHandler.rowNo;//(row1 + (row1/GameScene.bubbleSize)*GameScene.bubbleShiftup)/GameScene.bubbleSize;
        row2 = (sprite.y +deltaY+GameScene.bubbleSize);
        row2= SurfaceViewHandler.rowNo+1;//(row2 + (row2/GameScene.bubbleSize)*GameScene.bubbleShiftup)/GameScene.bubbleSize;

        rowI1 = (int)row1;
        rowI2 = (int)row2;

        column1=SurfaceViewHandler.columnNo;//((sprite.x+deltaX)/GameScene.bubbleSize);
        column2=SurfaceViewHandler.columnNo+1;//((sprite.x+deltaX+GameScene.bubbleSize)/GameScene.bubbleSize);


        columnI1 = (int) column1;
        columnI2 = (int) column2;


//        boolean touchedBubble=false;
//
//        if((rowI1==SurfaceViewHandler.rowNo)&&(columnI1==SurfaceViewHandler.columnNo||columnI1==SurfaceViewHandler.columnNo-1))
//        {
//            touchedBubble=true;
//        }
//        else if(SurfaceViewHandler.lineEndPointX>sprite.x+deltaX && SurfaceViewHandler.lineEndPointY>sprite.y+deltaY)
//        {
//            touchedBubble=true;
//        }
//
//        if(touchedBubble)
//        {


            if (rowI1 >= GameScene.array.length)
                rowI1 = GameScene.array.length-1;

            if (rowI2 >= GameScene.array.length)
                rowI2 = GameScene.array.length-1;

            if (columnI1 >= GameScene.array[0].length)
                columnI1 = GameScene.array[0].length-1;

            if (columnI2 >= GameScene.array[0].length)
                columnI2 = GameScene.array[0].length-1;


            if (columnI1 < 0)
                columnI1 = 0;

            if (columnI2 < 0)
                columnI2 = 0;

             boolean upLeft = GameScene.array[rowI1][columnI1] != null;
             boolean upRight = GameScene.array[rowI1][columnI2] != null ;
             boolean downLeft =   GameScene.array[rowI2][columnI1] != null ;
             boolean downRight =  GameScene.array[rowI2][columnI2] != null;

             if(upRight || upLeft || downRight || downLeft)
             {
               if(color == GameScene.superBubbleColor)
                {
                  if(upLeft)
                    color =  GameScene.array[rowI1][columnI1].color;
                  else if(upRight)
                    color =  GameScene.array[rowI1][columnI2].color;
                  else if(downRight)
                    color = GameScene.array[rowI2][columnI2].color;
                  else
                    color = GameScene.array[rowI2][columnI1].color;

                  sprite.setCurrentVerFrame(color);
                }

            if((upRight || upLeft ) && !(downLeft || downRight))
            {
                row = rowI1+1;
            }
//            else if( (upRight || upLeft ) && !(downLeft || downRight))
//            {
//                row = rowI2-1;
//            }
            else if ((upRight&& downRight) || (upLeft && downLeft))
            {
                row = (int) ((row1+row2)/2);
            }
            else if ((upLeft && downRight) ||(upRight && downLeft))
            {
                row = rowI1+1;
            }
            else
            {
                row = rowI1+1;
            }

            if( upLeft && downRight )
            {
                if(row%2 == 0)
                {
                    column = columnI1;
                }
                else
                {
                    column = columnI1;
                }
            }
            else if (upRight && downLeft)
            {
                if(row%2 == 0)
                {
                    column = columnI1+1;
                }
                else
                {
                    column = columnI1+1;
                }
            }
            else if(upLeft || downLeft)
            {
                if(row%2 == 0)
                {
                    column = columnI1 +1;
                }
                else
                {
                    column = columnI1;
                }
            }
            else if(upRight || downRight)
            {
                if(row%2 == 0)
                {
                    column = columnI1+1 ;
                }
                else
                {
                    column = columnI1;
                }
            }

            if(upLeft && downLeft)
            {
                column = columnI1+1;
            }
            else if(upRight && downRight)
            {
                column = columnI1 ;
            }

            if(columnI1 == columnI2)
            {
                column = columnI1;
            }

            if(row < 0)
                row = 0;

            if(row >= 	GameScene.array.length)
                row = GameScene.array.length-1;

            if(column < 0)
                column = 0;

            if(column > GameScene.array[0].length)
                column = GameScene.array[0].length-1;

           // if(SurfaceViewHandler.collideSpriteX>sprite.x+deltaX)

            if(GameScene.array[row][column] != null){
                panicRecover();
            }

            GameScene.array[row][column] = this;
            GameScene.array[row][column].sprite.rowNum=row;
            GameScene.array[row][column].sprite.columnNum=column;

            if (row%2 == 1)
            {
                sprite.animateTo((column)*GameScene.bubbleSize +(double) GameScene.bubbleSize/2, (row)*GameScene.bubbleSize  - row*GameScene.bubbleShiftup ,velocity );
            }
            else
            {
                sprite.animateTo((column)*GameScene.bubbleSize, (row)*GameScene.bubbleSize  - row*GameScene.bubbleShiftup , velocity );
            }

            return true;
        }

        else if(rowI2 == 0) {
            row = (int) ((row1 + row2) / (2));
            column = (int) ((column1 + column2) / (2));

            if (row < 0)
                row = 0;

            if (row >= GameScene.array.length)
                row = GameScene.array.length - 1;

            if (column < 0)
                column = 0;

            if (column >= GameScene.array[0].length)
                column = GameScene.array[0].length - 1;

            if (GameScene.array[row][column] != null) {
                panicRecover();
            }
            GameScene.array[row][column] = this;
            GameScene.array[row][column].sprite.rowNum = row;
            GameScene.array[row][column].sprite.columnNum = column;


            if (color == GameScene.superBubbleColor) {
                int bc = new Random().nextInt(6);
                color = bc;
                sprite.setCurrentVerFrame(bc);
            }

            sprite.animateTo((column) * GameScene.bubbleSize, (row) * GameScene.bubbleSize, velocity / 2);
        }
//
//            return true;
//        }
//        else
            return false ;
    }

public void drawProjectile(int j)
{
    double posx = sprite.x;
    double posy = sprite.y;

    if(posy < (SurfaceViewHandler.cHeight - (GameScene.bubbleSize * 5)))
    {
        if(posy>=sprite.storeY-GameScene.bubbleSize/2 && sprite.flag && j%2==0)
        {
            if(sprite.columnNo==0) {
                sprite.changeX = 100 * Math.cos(Math.PI / 180 * sprite.angle1);
                sprite.changeY = 100* Math.sin(Math.PI / 180 * sprite.angle1);
            }
            else {
                sprite.changeX = 100* Math.cos(Math.PI / 180 * sprite.angle2);
                sprite.changeY = 100 * Math.sin(Math.PI / 180 * sprite.angle2);
            }

            sprite.changeY -= 9.82 * sprite.timeChange;
            posx+=sprite.changeX*sprite.timeChange;
            posy-=sprite.changeY*sprite.timeChange;
        }
        else
        {
            sprite.flag=false;
            if(sprite.columnNo==0) {
                sprite.changeX = sprite.velocity * Math.cos(Math.PI / 180 * sprite.angle1);
                sprite.changeY = sprite.velocity * Math.sin(Math.PI / 180 * sprite.angle1);
            }
            else {
                sprite.changeX = sprite.velocity * Math.cos(Math.PI / 180 * sprite.angle2);
                sprite.changeY = sprite.velocity * Math.sin(Math.PI / 180 * sprite.angle2);
            }

            sprite.changeY += 9.82 * sprite.timeChange;
            posx+=sprite.changeX*sprite.timeChange;
            posy+=sprite.changeY*sprite.timeChange;
        }
        sprite.timeChange=sprite.timeChange+.01;
    }
    sprite.moveTo(posx,posy);

    if(posy >= (SurfaceViewHandler.cHeight - (GameScene.bubbleSize * 5)) && !blastFlag)
    {
        blastFlag=true;
        if(sprite.currentHorFrame==7) {
            if(sprite.numCountFrame==100) {

                GameScene.layer.removeSprite(sprite);
                GameScene.fallingExtra.remove(this);
                //  GameScene.falling.remove(this);
                GameScene.pool.add(this);
            }
            else
            {
                if (sprite.numCountFrame==0) {
                    sprite.u = sprite.y;
                }
                sprite.scoreFrameFlag=true;
                String x=String.valueOf(numS);
                sprite.bitmap=drawText(x,50);
                sprite.numCountFrame++;
            }
        }
        else
            {
              sprite.nextHorFrame();
            }

    }

    if(blastFlag)
    {
        if(sprite.currentHorFrame==7) {
            if(sprite.numCountFrame==60) {

                GameScene.layer.removeSprite(sprite);
                GameScene.fallingExtra.remove(this);
                //  GameScene.falling.remove(this);
                GameScene.pool.add(this);
                if(GameScene.fallingExtra.size()==0)
                    blastFlag=false;

            }
            else
            {
                if (sprite.numCountFrame==0) {
                    sprite.u = sprite.y;//-(Math.random()*50);
                }
                sprite.scoreFrameFlag=true;
                String x=String.valueOf(numS);
                sprite.bitmap=drawText(x,50);
                sprite.numCountFrame++;
            }
        }
        else
        {
            sprite.nextHorFrame();
        }

    }


}
    public Bitmap drawText(String text, int textSize)
    {
        // Get text dimensions
        TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setShader(new LinearGradient(0, 0, 0,100, Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));
        textPaint.setColor(Color.RED);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
         int r=(int)(Math.random()*50)+60;
        textPaint.setTextSize(80);

        StaticLayout mTextLayout = new StaticLayout(text, textPaint, textSize*3, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);

        // Create bitmap and canvas to draw to
        Bitmap b = Bitmap.createBitmap(mTextLayout.getWidth(), mTextLayout.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);

        // Draw background
//        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.RED);
//        c.drawPaint(paint);

        // Draw text
        c.save();
        c.translate(0, 0);
        mTextLayout.draw(c);
        c.restore();

       // printBitmap(b);
        return b;
    }


//    public void updateFrame(int num)
//    {
//        num++;
//        timer=2;
//
//        if(sprite.currentHorFrame==7) {
//            if(sprite.numCountFrame==30) {
//                GameScene.layer.removeSprite(sprite);
//               // GameScene.fallingExtra.remove(this);
//                  GameScene.falling.remove(this);
//                GameScene.pool.add(this);
//            }
//            else
//            {
//                if (sprite.numCountFrame==0)
//                {
//                    sprite.u = sprite.y;
//                    sprite.updateFlag=true;
//
//                }
//                sprite.scoreFrameFlag=true;
//                String x=String.valueOf(numS);
//                sprite.bitmap=drawText(x,50);
//                sprite.numCountFrame++;
//            }
//        }
//
//        if (timer%num == 0 &&!sprite.updateFlag)
//        {
//            sprite.nextHorFrame();
//        }
//
//
//    }




    public void updateFrame(int num)
    {
        num++;
        timer=2;
        if (sprite.currentHorFrame == 7)
        {
            GameScene.layer.removeSprite(sprite);
            GameScene.falling.remove(this);
            GameScene.pool.add(this);
        }
        if (timer%num == 0 )
            sprite.nextHorFrame();
    }

    public void checkFalls ()
    {
        alive = false ;
        GameScene.temp.add(this);
        while (!GameScene.temp.isEmpty())
        {
            GameScene.BubblesGroup.add(GameScene.temp.peek());
            GameScene.temp.poll().getNeighbor();
        }
    }

    private void getNeighbor ()
    {
        if (column > 0)
            if (GameScene.array[row][column - 1] != null && GameScene.array[row][column - 1].alive
                    && (GameScene.array[row][column - 1].color == color || GameScene.array[row][column - 1].color == GameScene.superBubbleColor))
            {
                GameScene.array[row][column - 1].alive = false;
                GameScene.temp.add(GameScene.array[row][column - 1]);
            }

        if (column < GameScene.array[0].length - 1)
            if (GameScene.array[row][column + 1] != null && GameScene.array[row][column + 1].alive
                    && (GameScene.array[row][column + 1].color == color || GameScene.array[row][column + 1].color == GameScene.superBubbleColor))
            {
                GameScene.array[row][column + 1].alive = false;
                GameScene.temp.add(GameScene.array[row][column + 1]);
            }

        if (row > 0)
        {
            if (GameScene.array[row - 1][column] != null && GameScene.array[row - 1][column].alive
                    && (GameScene.array[row - 1][column].color == color || GameScene.array[row - 1][column].color == GameScene.superBubbleColor))
            {
                GameScene.array[row - 1][column].alive = false;
                GameScene.temp.add(GameScene.array[row - 1][column]);
            }

            if (column > 0) {
                if (row % 2 == 0 && GameScene.array[row - 1][column - 1] != null && GameScene.array[row - 1][column - 1].alive
                        && (GameScene.array[row - 1][column - 1].color == color || GameScene.array[row - 1][column - 1].color == GameScene.superBubbleColor))
                {
                    GameScene.array[row - 1][column - 1].alive = false;
                    GameScene.temp.add(GameScene.array[row - 1][column - 1]);
                }
            }

            if (column < GameScene.array[0].length - 1)
                if (row % 2 == 1 && GameScene.array[row - 1][column + 1] != null && GameScene.array[row - 1][column + 1].alive
                        && (GameScene.array[row - 1][column + 1].color == color || GameScene.array[row - 1][column + 1].color == GameScene.superBubbleColor))
                {
                    GameScene.array[row - 1][column + 1].alive = false;
                    GameScene.temp.add(GameScene.array[row - 1][column + 1]);
                }
        }

        if (row < GameScene.array.length - 1)
        {
            if (GameScene.array[row + 1][column] != null && GameScene.array[row + 1][column].alive
                    && (GameScene.array[row + 1][column].color == color || GameScene.array[row + 1][column].color == GameScene.superBubbleColor))
            {
                GameScene.array[row + 1][column].alive = false;
                GameScene.temp.add(GameScene.array[row + 1][column]);
            }
            if (column > 0)
                if (row % 2 == 0 && GameScene.array[row + 1][column - 1] != null && GameScene.array[row + 1][column - 1].alive
                        && (GameScene.array[row + 1][column - 1].color == color || GameScene.array[row + 1][column - 1].color == GameScene.superBubbleColor))
                {
                    GameScene.array[row + 1][column - 1].alive = false;
                    GameScene.temp.add(GameScene.array[row + 1][column - 1]);
                }

            if (column < GameScene.array[0].length - 1)
                if (row % 2 == 1 && GameScene.array[row + 1][column + 1] != null && GameScene.array[row + 1][column + 1].alive
                        && (GameScene.array[row + 1][column + 1].color == color || GameScene.array[row + 1][column + 1].color == GameScene.superBubbleColor))
                {
                    GameScene.array[row + 1][column + 1].alive = false;
                    GameScene.temp.add(GameScene.array[row + 1][column + 1]);
                }
        }
    }

    public static void getNotConnected()
    {
        for (int i=0;i<GameScene.array[0].length;i++)
            if (GameScene.array[0][i] != null && GameScene.array[0][i].alive
                    && !GameScene.array[0][i].checked)
            {
//					System.out.println("Visit " + i);
                GameScene.array[0][i].visit();
            }

        for (int i=0;i<GameScene.array.length;i++)
        {
            for (int j=0;j<GameScene.array[0].length;j++)
                if (GameScene.array[i][j] != null && !GameScene.array[i][j].checked) {

                    GameScene.array[i][j].sprite.storeX=GameScene.array[i][j].sprite.x;
                    GameScene.array[i][j].sprite.storeY=GameScene.array[i][j].sprite.y;
                    GameScene.array[i][j].sprite.angle1=(int)(Math.random()*30)+60;
                    GameScene.array[i][j].sprite.angle2=(int)(Math.random()*30)+90;
                    GameScene.array[i][j].sprite.velocity=(int)(Math.random()*10)+13;
                    GameScene.array[i][j].sprite.columnNo=j;

                    GameScene.extra.add(GameScene.array[i][j]);
                }
        }

        /*
         * return checked attribute to false
         * I must find another way to solve this
         */

        for (int i=0;i<GameScene.array.length;i++)
        {
            for (int j=0;j<GameScene.array[0].length;j++)
                if (GameScene.array[i][j] != null)
                    GameScene.array[i][j].checked = false ;
        }
    }

    private void visit ()
    {
//		System.out.println("Visit : row = " + row +" , Column = " + column);
        checked = true ;
        // Up
        if (row > 0)
            if (GameScene.array[row-1][column] != null && GameScene.array[row-1][column].alive
                    && !GameScene.array[row-1][column].checked)
            {
//					System.out.println("Go UP");
                GameScene.array[row-1][column].visit();
            }

        // Down
        if (row < GameScene.array.length-1)
            if (GameScene.array[row+1][column] != null && GameScene.array[row+1][column].alive
                    && !GameScene.array[row+1][column].checked)
            {
//					System.out.println("Go Down");
                GameScene.array[row+1][column].visit();
            }

        // Left
        if (column > 0)
            if (GameScene.array[row][column-1] != null && GameScene.array[row][column-1].alive
                    && !GameScene.array[row][column-1].checked)
            {
//					System.out.println("Go Left");
                GameScene.array[row][column-1].visit();
            }

        // Right
        if (column < GameScene.array[0].length-1)
        {
            if (GameScene.array[row][column+1] != null && GameScene.array[row][column+1].alive
                    && !GameScene.array[row][column+1].checked)
            {
//					System.out.println("Go Right");
                GameScene.array[row][column+1].visit();
            }
        }

        // Upper_Left and Down_Left
        if (row %2 ==0 && column >0)
        {
            //Upper_Left
            if(row >0 && GameScene.array[row-1][column-1] != null
                    && GameScene.array[row-1][column-1].alive && !GameScene.array[row-1][column-1].checked)
            {
//					System.out.println("Go UP_Left");
                GameScene.array[row-1][column-1].visit();
            }

            //Down_Left
            if(row < GameScene.array.length-1 && GameScene.array[row+1][column-1] != null
                    && GameScene.array[row+1][column-1].alive && !GameScene.array[row+1][column-1].checked)
            {
//					System.out.println("Go Down_Left");
                GameScene.array[row+1][column-1].visit();
            }
        }

        // Upper_Right and Down_Right
        if (row %2 ==1 && column < GameScene.array[0].length-1)
        {
            //Upper_Right
            if(row >0 && GameScene.array[row-1][column+1] != null
                    && GameScene.array[row-1][column+1].alive && !GameScene.array[row-1][column+1].checked)
            {
//					System.out.println("Go UP_Right");
                GameScene.array[row-1][column+1].visit();
            }

            //Down_Right
            if(row < GameScene.array.length-1 && GameScene.array[row+1][column+1] != null
                    && GameScene.array[row+1][column+1].alive && !GameScene.array[row+1][column+1].checked)
            {
//					System.out.println("Go Down_Right");
                GameScene.array[row+1][column+1].visit();
            }
        }
    }

    public void panicRecover()
    {
        if(column+1 < GameScene.array[0].length && (GameScene.array[row][column+1] == null)){
            column++;
        }
        else if(row-1 > 0 && (GameScene.array[row-1][column] != null)){
            row--;
        }
        else if(row+1 < GameScene.array.length &&  (GameScene.array[row+1][column] == null) ){
            row++;
        }
        else if(column-1 > 0 &&  (GameScene.array[row][column-1] == null)){
            column--;
        }
        else
            {
            GameScene.layer.removeSprite(GameScene.array[row][column].sprite);
            GameScene.array[row][column]=null;

        }

    }


}
