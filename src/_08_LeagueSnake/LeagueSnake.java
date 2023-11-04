package _08_LeagueSnake;

import java.util.ArrayList;

import processing.core.PApplet;

public class LeagueSnake extends PApplet {
    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    
    /*
     * Game variables
     * 
     * Put all the game variables here.
     */
    Segment snakeHead;
    int foodX;
    int foodY;
    int snakeDirection = UP;
    int foodEaten = 0;
    ArrayList <Segment> tail = new ArrayList<>();
    
    /*
     * Setup methods
     * 
     * These methods are called at the start of the game.
     */
    @Override
    public void settings() {
        setSize(600, 600);
    }

    @Override
    public void setup() {
        snakeHead = new Segment(120,120);
        frameRate(15);
        dropFood();
    }

    void dropFood() {
        // Set the food in a new random location
    	foodX = ((int)random(20)*30);
    	foodY = ((int)random(20)*30);
    	for(int i=0; i<tail.size(); i++) {
    		int x = tail.get(i).x;
    		int y = tail.get(i).y;
    		if(x == foodX && y == foodY) {
    			dropFood();
    		}
    	}
    }


    /*
     * Draw Methods
     * 
     * These methods are used to draw the snake and its food
     */

    @Override
    public void draw() {
        background(0,0,0);
        drawFood();
        move();
        drawSnake();
        eat();
    }

    void drawFood() {
        // Draw the food
    	fill(255,0,0);
    	rect(foodX, foodY, 30, 30);
    }

    void drawSnake() {
        // Draw the head of the snake followed by its tail
    	fill(0,0,255);
    	rect(snakeHead.x,snakeHead.y,30,30);
    	manageTail();
    }

    void drawTail() {
        // Draw each segment of the tail
        for(int i=0; i<tail.size(); i++) {
        	fill(0,255,0);
        	rect(tail.get(i).x,tail.get(i).y,30,30);
        }
    }

    /*
     * Tail Management methods
     * 
     * These methods make sure the tail is the correct length.
     */

    void manageTail() {
        // After drawing the tail, add a new segment at the "start" of the tail and
        // remove the one at the "end"
        // This produces the illusion of the snake tail moving.
    	checkTailCollision();
    	drawTail();
    	Segment newTailSegment = new Segment(snakeHead.x, snakeHead.y);
    	tail.add(newTailSegment);
    	tail.remove(0);
    	
    }

    void checkTailCollision() {
    	// If the snake crosses its own tail, shrink the tail back to one segment
    	for(int i=0; i<tail.size(); i++) {
    		int x = tail.get(i).x;
    		int y = tail.get(i).y;
    		if(snakeHead.x == x && snakeHead.y == y) {
    			foodEaten=0;
    			tail = new ArrayList<>();
    			//Segment newTailSegment = new Segment(snakeHead.x, snakeHead.y);
    	    	//tail.add(newTailSegment);
    		}
    	}
    }

    /*
     * Control methods
     * 
     * These methods are used to change what is happening to the snake
     */

    @Override
    public void keyPressed() {
        // Set the direction of the snake according to the arrow keys pressed
    	if(keyCode == 38 && snakeDirection != DOWN) {
    		snakeDirection = UP;
    	}else if(keyCode == 40 && snakeDirection != UP) {
    		snakeDirection = DOWN;
    	}else if (keyCode == 37 && snakeDirection != RIGHT) {
    		snakeDirection = LEFT;
    	}else if (keyCode == 39 && snakeDirection != LEFT) {
    		snakeDirection = RIGHT;
    	}
    }

    void move() {
        // Change the location of the Snake head based on the direction it is moving.
    	
        
    	if (snakeDirection == UP) {
            snakeHead.y-=30;
        } else if (snakeDirection == DOWN) {
            snakeHead.y+=30; 
        } else if (snakeDirection == LEFT) {
            snakeHead.x-=30;        
        } else if (snakeDirection == RIGHT) {
            snakeHead.x+=30;         
        }
    	checkBoundaries();
    }

    void checkBoundaries() {
        // If the snake leaves the frame, make it reappear on the other side
        if(snakeHead.y == 600) {
        	snakeHead.y=0;
        } else if(snakeHead.x == 600) {
        	snakeHead.x=0;
        } else if(snakeHead.y == -30) {
        	snakeHead.y=570;
        } else if(snakeHead.x == -30) {
        	snakeHead.x=570;
        }
    }

    void eat() {
        // When the snake eats the food, its tail should grow and more
        // food appear
    	if(snakeHead.x == foodX && snakeHead.y == foodY) {
    		foodEaten+=1;
    		dropFood();
    		Segment newTailSegment = new Segment(snakeHead.x, snakeHead.y);
        	tail.add(newTailSegment);
    	}        
    }

    static public void main(String[] passedArgs) {
        PApplet.main(LeagueSnake.class.getName());
    }
}
