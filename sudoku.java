/*status code define as:
11: row that been checked has conflict
12: column that been checked has conflict
13: block that been checked has conflict
*/
import java.util.Scanner;
public class sudoku
{
    public static void main(String[] args) 
    {
       Scanner input=new Scanner(System.in);
       int[][] in_sudoku=new int[9][9];
       for(int a=0;a<9;a++)
       {
            for(int b=0;b<9;b++)
            {
                in_sudoku[a][b]=input.nextInt();
            }
       }
       int[][] operate_sudoku=copy_array(in_sudoku);
       int current_x=0;
       int current_y=0;
       int ind_result;
       int beginCheckNum=0;
       int time=0;//for debug
       while(current_x<=8&&current_y<=8)
       {
            time++;//for debug
            System.out.println("process time"+time+"current x is "+current_x+"  current y is "+current_y);//for debug
            System.out.println();//for debug
            if(in_sudoku[current_x][current_y]!=0)//排除题目中已经给定的数据
            {
                System.out.println("skip number happen once");
                current_y++;
                if(current_y==9)
                {
                    current_y=0;
                    current_x++;
                }
            }
            else
            {
                System.out.println("situation 2 process once");//for debug
                if(operate_sudoku[current_x][current_y]==0)
                {
                    beginCheckNum=1;
                }
                else
                {
                    beginCheckNum=operate_sudoku[current_x][current_y];
                }
                for(int aa=beginCheckNum;aa<=9;aa++)
                {
                    System.out.println("current x is "+current_x+"  current y is "+current_y+"   current check number is "+aa);
                    ind_result=check_ind(operate_sudoku,current_x,current_y,aa);
                    System.out.println("current xy code is"+ind_result);//for debug
                    if(ind_result!=0)
                    {
                        operate_sudoku[current_x][current_y]=ind_result;
                        current_y++;
                        if(current_y==9)
                        {
                            current_y=0;
                            current_x++;
                        }
                        break;
                    }
                    else if(ind_result==0&&aa==9)
                    {
                        System.out.println("Bad situation process once");//for debug
                        operate_sudoku[current_x][current_y]=0;
                        current_y--;
                        if(current_y==-1)
                        {
                            current_x--;
                            current_y=8;
                        }
                        System.out.println("y-- in bad situation happen once and current y is "+current_y+" current x is "+current_x);//for debug
                        System.out.println("origin matrix x,y number is "+in_sudoku[current_x][current_y]);
                        while(in_sudoku[current_x][current_y]!=0)
                        {
                            System.out.println("y-- in bad situation happen once(in loop)");//for debug
                            current_y--;
                            if(current_y==-1)
                            {
                                current_x--;
                                current_y=8;
                            }

                        }
                        break;
                        
                    }
                }    
            }
       }
       for(int xx=0;xx<9;xx++)
       {
            for(int yy=0;yy<9;yy++)
            {
                System.out.print(operate_sudoku[xx][yy]+" ");
            }
            System.out.println();
       }
       input.close();
    }
    public static int check_ind(int[][] a,int x,int y,int testNumber)
    {
        int row_result=row_check(a,x,y,testNumber);
        int column_result=column_check(a, x, y,testNumber);
        int block_result=block_check(a, x, y,testNumber);
        System.out.println("row result="+row_result+"cloumn result="+column_result+"block result="+block_result);
        if(row_result!=11&&column_result!=12&&block_result!=13)
        {
            return testNumber;
        }
        else
        {
            return 0;
        }
    }
    public static int row_check(int[][] a,int x,int y,int testNumber)
    {
        int row_result=0;
        for(int b=0;b<9;b++) //遍历要check的行是否有冲突
        {
            if(a[x][b]==testNumber)
            {
                return 11;//status code 11
            }
        }
        row_result=testNumber;
        return row_result;
        
    }
    public static int column_check(int[][] a,int x,int y,int testNumber)
    {
        int column_result=0;
        for(int b=0;b<9;b++) 
        {
            if(a[b][y]==testNumber)
            {
                return 12;//status code 12
            }
        }
        column_result=testNumber;
        return column_result;
    }
    public static int block_check(int[][] a,int x,int y,int testNumber)
    {
        //from x and y, identify block number
        int block_result=0;
        String xy=blong_block(x,y);
        int begin_x=Integer.parseInt(xy.substring(0,1));
        int begin_y=Integer.parseInt(xy.substring(1));
        for(int b=begin_x;b<begin_x+3;b++)
        {
            for(int c=begin_y;c<begin_y+3;c++)
            {
                if(a[b][c]==testNumber)
                {
                    return 13;
                }
            }
        }
        block_result=testNumber;
        return block_result;
    }
    public static String blong_block(int x,int y)
    {
        String xy="";
        if(x>=0&&x<=2&&y>=0&&y<=2)
        {
            xy="00";
        }
        else if(x>=0&&x<=2&&y>=3&&y<=5)
        {
            xy="03";
        }
        else if(x>=0&&x<=2&&y>=6&&y<=8)
        {
            xy="06";
        }
        else if(x>=3&&x<=5&&y>=0&&y<=2)
        {
            xy="30";
        }
        else if(x>=3&&x<=5&&y>=3&&y<=5)
        {
            xy="33";
        }
        else if(x>=3&&x<=5&&y>=6&&y<=8)
        {
            xy="36";
        }
        else if(x>=6&&x<=8&&y>=0&&y<=2)
        {
            xy="60";
        }
        else if(x>=6&&x<=8&&y>=3&&y<=5)
        {
            xy="63";
        }
        else if(x>=6&&x<=8&&y>=6&&y<=8)
        {
            xy="66";
        }
        return xy;
    }
    public static int[][] copy_array(int[][] in_array)
    {
        int[][] result_array=new int[in_array.length][in_array[0].length];
        for(int row=0;row<in_array.length;row++)
        {
            for(int column=0;column<in_array[0].length;column++)
            {
                result_array[row][column]=in_array[row][column];
            }
        }
        return result_array;
    }
}