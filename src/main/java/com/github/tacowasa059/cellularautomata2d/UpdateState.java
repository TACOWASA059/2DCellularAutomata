package com.github.tacowasa059.cellularautomata2d;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.material.Wool;

import java.awt.*;
import java.util.*;

public class UpdateState {
    private static Material[] wool={
            Material.BLACK_WOOL,
            Material.BROWN_WOOL,
            Material.RED_WOOL,
            Material.ORANGE_WOOL,
            Material.YELLOW_WOOL,
            Material.LIME_WOOL,
            Material.GREEN_WOOL,
            Material.CYAN_WOOL,
            Material.LIGHT_BLUE_WOOL,
            Material.BLUE_WOOL,
            Material.PURPLE_WOOL,
            Material.MAGENTA_WOOL,
            Material.PINK_WOOL,
            Material.WHITE_WOOL,
            Material.LIGHT_GRAY_WOOL,
            Material.GRAY_WOOL
    };
    private Integer[][] cells;
    private Location location;

    private int neighbor=0;
    private Integer[] rule;

    private int type=0;
    public UpdateState(int rows, int cols,Location location) {
        cells = new Integer[rows][cols];
        for(int i=0;i<cells.length;i++){
            for(int j=0;j<cells[i].length;j++){
                cells[i][j]=0;
            }
        }
        this.location=location;
        copy_from_board();
        this.location.subtract(0.0,1.0,0.0);
    }
    public void setRule(int neighbor,int rule){
        this.neighbor=neighbor;
        int rulenumber=rule;
        int r=0;
        if(neighbor==0){//outer totalistic+5neighbor
            r=10;
        }
        else if(neighbor==1){
            //outer totalistic+ 9neighbor
            r=18;
        }
        else if(neighbor==2){
            //totalistic+ 5neighbor
            r=6;
        }
        else if(neighbor==3){
            //totalistic+9neighbor
            r=10;
        }
        else{
            System.out.println("neighborが間違っています.");
        }
        this.rule=new Integer[r];
        for(int i=0;i<r;i++){
            this.rule[i]=rulenumber%2;
            rulenumber=rulenumber/2;
        }
    }
    //ランダム化
    public void randomize(){
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Random random =new Random();
                int rand= random.nextInt(2);
                cells[i][j]=rand;
            }
        }
        copy_to_board();
    }
    //worldのブロックデータから設定する
    public void copy_from_board(){
        for(int i=0;i<cells.length;i++){
            for(int j=0;j<cells[i].length;j++){
                Location loc=location.clone();
                loc=loc.add((double)i,0,(double)j);
                if(loc.getBlock().getType()== Material.BLACK_WOOL){
                    cells[i][j]=1;
                }
                else{
                    cells[i][j]=0;
                    Block block=loc.getBlock();
                    block.setType(Material.AIR);
                }
            }
        }
    }
    //worldのブロックデータにコピーする
    public void copy_to_board(){
        type=(type+1)%16;
        for(int i=0;i<cells.length;i++){
            for(int j=0;j<cells[i].length;j++){
                Location loc=location.clone();
                loc=loc.add((double)i,0,(double)j);
                if(cells[i][j]==0){
                    loc.getBlock().setType(Material.AIR);
                }
                else{
                    loc.getBlock().setType(wool[type]);
                }
            }
        }
    }
    //ステップ更新
    public void update() {
        Integer[][] nextGeneration = new Integer[cells.length][cells[0].length];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Integer current= cells[i][j];
                int top = cells[(i - 1 + cells.length) % cells.length][j];
                int top_right = cells[(i - 1 + cells.length) % cells.length][(j + 1) % cells[i].length];
                int top_left = cells[(i - 1 + cells.length) % cells.length][(j - 1 + cells[i].length) % cells[i].length];
                int right = cells[i][(j + 1) % cells[i].length];
                int bottom = cells[(i + 1) % cells.length][j];
                int bottom_right = cells[(i + 1) % cells.length][(j + 1) % cells[i].length];
                int bottom_left = cells[(i + 1) % cells.length][(j - 1 + cells[i].length) % cells[i].length];
                int left = cells[i][(j - 1 + cells[i].length) % cells[i].length];

                int sum=0;
                int nextState=0;
                if(neighbor==0){
                    sum=top+right+left+bottom;
                    nextState=rule[sum+5*current];
                }
                else if(neighbor==1){
                    sum=top+right+left+bottom+top_left+top_right+bottom_left+bottom_right;
                    nextState=rule[sum+9*current];
                }
                else if(neighbor==2){
                    sum= current+top+right+left+bottom;
                    nextState=rule[sum];
                }
                else if(neighbor==3){
                    sum=current+top+right+left+bottom+top_left+top_right+bottom_left+bottom_right;
                    nextState=rule[sum];
                }

                nextGeneration[i][j] = nextState;
            }
        }
        cells = nextGeneration;
        copy_to_board();
        location.subtract(0.0,1.0,0.0);
    }
    public void resetLoc(){
        location.setY(255.0);
        copy_from_board();
        location.setY(254.0);
    }
}
