package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor {


    private static int Life = 3;
    private static int score = 0;
    private float timeSeconds = 100;
    private String displayTime;
    private static int currentlevel = 1;
    private int scoredisplay;

    BitmapFont font; //Declared to use text.
    Texture start;
    Texture controls;
    Texture gameover;
    Texture game_complete;
    Texture level_complete;
    Texture backGroundImg;
    Texture backGroundLevelThree;
    ArrayList<Obstacle> obstacles;
    ArrayList<InteractiveObject> interActiveObjects;
    ArrayList<Figure> figures;
    InteractiveObject door;
    InteractiveObject doorTwo;
    InteractiveObject doorThree;
    InteractiveObject endPoint;
    Texture cactus;
    ArrayList <Integer> highscores = new ArrayList<Integer>();
    BitmapFont highscorefont;
    Texture high_score;
    ArrayList<Coin> coins;
    Blocks iceForm11;
    Blocks iceForm12;
    Music start_Screen_Music;
    Music level_Music;
    AnimatedObject water;






    Hero hero;// //an extra reference to Hero, for convenience.

    SpriteBatch spriteBatch;    //The SpriteBatch is used to draw the texture onto the screen.
    static TextureRegion currentFrame; //This variable will hold the current frame and this is the region which is drawn on each render call.
    static TextureRegion currentframtwo;
    float stateTime;   // The stateTime is the number of seconds elapsed from the start of the animation.
    GameState state = GameState.START_SCREEN;

    @Override
    public void create() {
        font = new BitmapFont();
        font.setColor(Color.RED);
        highscorefont = new BitmapFont();
        highscorefont.setColor(Color.WHITE);


        spriteBatch = new SpriteBatch();
        createObstacles();
        createCoinOne();
        createHero();
        createEnemyOne();
        createScenarios();
        Gdx.input.setInputProcessor(this);
        start_Screen_Music = Gdx.audio.newMusic(Gdx.files.internal("Music/StartScreen.mp3"));
        level_Music = Gdx.audio.newMusic(Gdx.files.internal("Music/Playing.mp3"));

    }

    @Override
    public void render() {

        displayTime = String.format("Time: %.0f",timeSeconds );
        playMusic();

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            System.exit(0);
        }


        switch (state) {
            case START_SCREEN:
                showStartScreen();
                break;
            case CONTROLS:
                showControls();
                break;
            case LEVEL_ONE:
                countTime();
                renderLevelOne();
                break;
            case LEVEL_TWO:
                countTime();
                renderLevelTwo();
                break;
            case LEVEL_THREE:
                countTime();
                renderLevelThree();
                break;
            case LEVEL_FOUR:
                countTime();
                renderLevelFour();
                break;
            case GAME_OVER:
                gameOver();
                break;
            case HIGH_SCORE:
                showHighScore();
                break;
            case LEVEL_COMPLETE:
                levelComplete();
                break;
            case GAME_COMPLETE:
                gameComplete();
                break;
        }


    }

    // free up resources when the game exits.
    @Override
    public void dispose() {
        spriteBatch.dispose();
        backGroundImg.dispose();

    }

    public void createObstacles() {

        obstacles = new ArrayList<Obstacle>();
        interActiveObjects = new ArrayList<InteractiveObject>();
        backGroundImg = new Texture("Backgrounds/castle.jpg");
        Obstacle sandfloor = new Obstacle("Plattform/brickPlattform.png", 0, 0, 1300, 40);
        Obstacle brickplattform1 = new Obstacle("Plattform/brickPlattform.png", 0, 350, 321, 25);
        Obstacle brickplattform4 = new Obstacle("Plattform/brickPlattform.png", 300, 130, 400, 25);
        Obstacle brickplattform2 = new Obstacle("Plattform/brickPlattform.png", Gdx.graphics.getWidth() - 421, 130, 321, 25);
        Obstacle brickplattform3 = new Obstacle("Plattform/brickPlattform.png", Gdx.graphics.getWidth() - 400, 400, 400, 25);
        obstacles.add(brickplattform1);
        obstacles.add(sandfloor);
        obstacles.add(brickplattform3);
        obstacles.add(brickplattform2);
        obstacles.add(brickplattform4);


        door = new InteractiveObject("Plattform/Door.png", 1185, 420, 120, 90);
        InteractiveObject ladder = new InteractiveObject("Hero/ladder.png", 950, 150, 50, 255);
        interActiveObjects.add(ladder);
    }

    public void createObstaclesTwo() {
        obstacles = new ArrayList<Obstacle>();
        interActiveObjects = new ArrayList<InteractiveObject>();
        backGroundImg = new Texture("Backgrounds/Desert.jpg");
        Obstacle sandfloor = new Obstacle("Plattform/brickPlattform.png", 0, 0, 1366, 20);
        Obstacle plattform1 = new Obstacle("Plattform/brickPlattform.png", 260, 344, 200, 25);
        Obstacle plattform2 = new Obstacle("Plattform/brickPlattform.png", 790, 360, 200, 25);
        Obstacle plattform3 = new Obstacle("Plattform/brickPlattform.png", 20, 215, 200, 25);
        Obstacle plattform4 = new Obstacle("Plattform/brickPlattform.png", 50, 85, 360, 25);
        Obstacle plattform5 = new Obstacle("Plattform/brickPlattform.png", 500, 250, 200, 25);
        Obstacle plattform6 = new Obstacle("Plattform/brickPlattform.png", 1000, 75, 200, 25);
        Obstacle plattform7 = new Obstacle("Plattform/brickPlattform.png", 850, 179, 150, 25);
        Obstacle plattform8 = new Obstacle("Plattform/brickPlattform.png", 630, 520, 650, 25);





        obstacles.add(plattform4);
        obstacles.add(sandfloor);
        obstacles.add(plattform1);
        obstacles.add(plattform2);
        obstacles.add(plattform3);
        obstacles.add(plattform5);
        obstacles.add(plattform6);
        obstacles.add(plattform7);
        obstacles.add(plattform8);


        InteractiveObject ladder = new InteractiveObject("Hero/ladder.png", 935, 380, 50, 152);
        doorTwo = new InteractiveObject("Plattform/Door.png", 1200,540,80,80);
        cactus = new Texture("Plattform/CactusSprite.png");
        interActiveObjects.add(ladder);


    }

    public void createObstaclesThree() {
        obstacles = new ArrayList<Obstacle>();
        interActiveObjects = new ArrayList<InteractiveObject>();
        backGroundLevelThree = new Texture("Backgrounds/iceMap.jpg");

        Obstacle iceForm = new Obstacle("Plattform/iceForm1.png", 30,500,250,60,true);
        Obstacle iceForm2 = new Obstacle("Plattform/iceForm1.png", 480,500,200,60,true);
        Obstacle iceForm3 = new Obstacle("Plattform/iceForm1.png", 900,500,300,60,true);
        Obstacle iceForm4 = new Obstacle("Plattform/iceForm1.png", 410,300,260,60,true);
        Obstacle iceForm5 = new Obstacle("Plattform/iceForm3.png", 60,300,110,60);
        Obstacle iceForm6 = new Obstacle("Plattform/iceForm4.png", -50,-190,400,300);
        Obstacle iceForm7 = new Obstacle("Plattform/iceForm4.png", 900,-190,400,300);
        Obstacle iceForm8 = new Obstacle("Plattform/iceForm1.png", 900,300,320,60,true);
        Obstacle iceForm9 = new Obstacle("Plattform/ice5.png", 550,70,120,60);
        Obstacle iceForm10 = new Obstacle("Plattform/iceform2.png", 1250,510,50,50);
        Obstacle iceForm13 = new Obstacle("Plattform/iceform2.png", 1200,510,50,50);


        InteractiveObject ladder1 = new InteractiveObject("Hero/ladder.png", 100, 90, 50, 250);
        InteractiveObject ladder2 = new InteractiveObject("Hero/ladder.png", 550, 350, 50, 200);
        InteractiveObject ladder3 = new InteractiveObject("Hero/ladder.png", 1000, 90, 50, 240);



        doorThree = new InteractiveObject("Plattform/Door.png", 1100, 350, 80, 80);
        iceForm11 = new Blocks("Plattform/iceform2.png", 900,430,65,65);
        iceForm12 = new Blocks("Plattform/iceform2.png", 900,356,65,65);


        obstacles.add(iceForm);
        obstacles.add(iceForm2);
        obstacles.add(iceForm3);
        obstacles.add(iceForm4);
        obstacles.add(iceForm5);
        obstacles.add(iceForm6);
        obstacles.add(iceForm7);
        obstacles.add(iceForm8);
        obstacles.add(iceForm9);
        obstacles.add(iceForm10);
        obstacles.add(iceForm13);
        interActiveObjects.add(ladder1);
        interActiveObjects.add(ladder2);
        interActiveObjects.add(ladder3);



    }

    public void createObstaclesFour(){
        obstacles = new ArrayList<Obstacle>();
        interActiveObjects = new ArrayList<InteractiveObject>();
        backGroundImg = new Texture("Backgrounds/Jungle.jpg");


        Obstacle greenForm1 = new Obstacle("Plattform/GreenPlatfromLeft.png", 0, 300, 175, 40);
        Obstacle greenform2 = new Obstacle("Plattform/GreenPlatform.png", 300, 300, 200, 40);
        Obstacle greenForm3 = new Obstacle("Plattform/GreenPlatform.png", 1060, 0, 250, 40);
        Obstacle greenForm4 = new Obstacle("Plattform/GreenPlatform.png", 750, 0, 70, 40);
        Obstacle greenForm5 = new Obstacle("Plattform/GreenPlatform.png", 1060, 280, 230, 40);
        Obstacle greenForm6 = new Obstacle("Plattform/GreenPlatform.png", 920, 400, 170, 40);
        Obstacle greenForm7 = new Obstacle("Plattform/GreenPlatform.png", 210, 500, 150, 40);
        Obstacle greenForm8 = new Obstacle("Plattform/GreenPlatform.png", 500, 540, 700, 40);

        InteractiveObject ladder1 = new InteractiveObject("Hero/ladder.png", 1200, 30, 50, 270);
        InteractiveObject ladder2 = new InteractiveObject("Hero/ladder.png", 920, 435, 50, 120);
        water  = new AnimatedObject("Plattform/Watersheet.gif", 45,580,10000);


        endPoint = new InteractiveObject("Plattform/Star.png", 1160, 580, 50, 50);



        obstacles.add(greenForm1);
        obstacles.add(greenform2);
        obstacles.add(greenForm3);
        obstacles.add(greenForm4);
        obstacles.add(greenForm5);
        obstacles.add(greenForm6);
        obstacles.add(greenForm7);
        obstacles.add(greenForm8);




        interActiveObjects.add(ladder1);
        interActiveObjects.add(ladder2);





    }

    public void createHero() {
        hero = new Hero("Hero/Cng-Hiro.png",0,500,80);
    }

    public void createEnemyOne() {

       figures = new ArrayList<Figure>();
        Bat bat1 = new Bat("Enemy/bat1.png", 70, 170, 60);
        Bat bat2 = new Bat("Enemy/bat1.png", 600, 440, 60);
        figures.add(bat1);
        figures.add(bat2);

    }

    public void createEnemyTwo() {
        figures = new ArrayList<Figure>(figures);

        Bat bat1 = new Bat("Enemy/bat1.png", 70, 170, 60);
        Bat bat2 = new Bat("Enemy/bat1.png", 600, 440, 60);
        Scorpion scorpion1 = new Scorpion("Enemy/Sprite_enemy_scorpion.png", 900,544,60,50);
        figures.add(scorpion1);
        figures.add(bat1);
        figures.add(bat2);

    }

    public void createEnemyThree() {
        figures = new ArrayList<Figure>(figures);

        Bat bat1 = new Bat("Enemy/bat1.png", 70, 170, 60);
        Bat bat2 = new Bat("Enemy/bat1.png", 600, 440, 60);
        Scorpion scorpion1 = new Scorpion("Enemy/Sprite_enemy_scorpion.png", 1140,554,60,50);
        Scorpion scorpion2 = new Scorpion("Enemy/Sprite_enemy_scorpion.png", 900,100,60,50);
        figures.add(scorpion1);
        figures.add(bat1);
        figures.add(bat2);
        figures.add(scorpion2);

    }

    public void createEnemyFour() {
        figures = new ArrayList<Figure>(figures);
        Bat bat1 = new Bat("Enemy/bat1.png", 70, 170, 60);
        Scorpion scorpion1 = new Scorpion("Enemy/Sprite_enemy_scorpion.png", 900, 575, 60, 50);
        figures.add(bat1);
        figures.add(scorpion1);
    }

    public void createCoinOne (){
        coins = new ArrayList<Coin>();

        Coin coin1 = new Coin("Plattform/coiiins.png", 300, 170, 50);
        Coin coin2 = new Coin("Plattform/coiiins.png", 980, 440, 50);
        coins.add(coin1);
        coins.add(coin2);


    }

    public void createCoinTwo (){
        coins = new ArrayList<Coin>();

        Coin coin1 = new Coin("Plattform/coiiins.png", 970, 208, 50);
        Coin coin2 = new Coin("Plattform/coiiins.png", 300, 380, 50);
        Coin coin3 = new Coin("Plattform/coiiins.png", 380, 120, 50);
        Coin coin4 = new Coin("Plattform/coiiins.png", 1000, 30, 50);
        coins.add(coin1);
        coins.add(coin2);
        coins.add(coin3);
        coins.add(coin4);


    }

    public void createCoinThree (){
        coins = new ArrayList<Coin>();

        Coin coin1 = new Coin("Plattform/coiiins.png", 70, 360, 50);
        Coin coin2 = new Coin("Plattform/coiiins.png", 1110, 570, 50);
        Coin coin3 = new Coin("Plattform/coiiins.png", 580, 130, 50);
        Coin coin4 = new Coin("Plattform/coiiins.png", 80, 100, 50);

        coins.add(coin1);
        coins.add(coin2);
        coins.add(coin3);
        coins.add(coin4);


    }

    public void createCoinFour (){
        coins = new ArrayList<Coin>();

        Coin coin1 = new Coin("Plattform/coiiins.png", 766, 50, 50);
        Coin coin2 = new Coin("Plattform/coiiins.png", 230, 550, 50);
        Coin coin3 = new Coin("Plattform/coiiins.png", 1150, 340, 50);
        Coin coin4 = new Coin("Plattform/coiiins.png", 1020, 580, 50);



        coins.add(coin1);
        coins.add(coin2);
        coins.add(coin3);
        coins.add(coin4);

    }

    public void createScenarios(){
        start = new Texture("gamescenarios/pitfall2_startscreen.png");
        controls = new Texture("gamescenarios/pitfall2_controls.png");
        gameover = new Texture("gamescenarios/Game_Over_Screen.png");
        high_score = new Texture("gamescenarios/high_score.png");
        level_complete = new Texture("gamescenarios/level_complete.png");
        game_complete = new Texture("gamescenarios/game_complete.png");
    }

    public void checkObstacleCollision() {


        //hero collides with door get to level2
        if (state == GameState.LEVEL_ONE && hero.collidesWith(door.getCollisionRectangle())) {
            figures.clear();
            obstacles.clear();
            coins.clear();
            createCoinTwo();
            createHero();
            createEnemyTwo();
            createObstaclesTwo();
            score += Math.round( (100 - timeSeconds) * 100);
            state = GameState.LEVEL_COMPLETE;
            return;
        }
      if(state == GameState.LEVEL_TWO && hero.collidesWith(doorTwo.getCollisionRectangle())) {
            figures.clear();
            obstacles.clear();
            coins.clear();
            createCoinThree();
            createHero();
            createEnemyThree();
            createObstaclesThree();
            score += Math.round((100 - timeSeconds) * 100);
            state = GameState.LEVEL_COMPLETE;
            hero.setX(10);
            hero.setY(600);
            return;
      }




        if (state == GameState.LEVEL_THREE && hero.collidesWith(doorThree.getCollisionRectangle())) {
            figures.clear();
            obstacles.clear();
            coins.clear();
            createCoinFour();
            createHero();
            createEnemyFour();
            createObstaclesFour();
            score += Math.round((100 - timeSeconds) * 100);
            state = GameState.LEVEL_COMPLETE;
            hero.setX(10);
            hero.setY(600);
            return;
        }
        if (state == GameState.LEVEL_FOUR && hero.collidesWith(endPoint.getCollisionRectangle())) {
            figures.clear();
            obstacles.clear();
            coins.clear();
            score += Math.round((100 - timeSeconds) * 100);
            highscores.add(score);
            Collections.sort(highscores);
            Collections.reverse(highscores);
            state = GameState.GAME_COMPLETE;

            return;
        }



        for (InteractiveObject object : interActiveObjects) {
            if (!(hero.getState() == Hero.HeroState.SLIDING)) {
                if (hero.collidesWith(object.getCollisionRectangle())) {
                    //hero cant "fly past" ladder
                    if (hero.getState() == Hero.HeroState.FLYING) {
                        hero.stopComplete();
                        hero.heroStateClimbing();
                        return;
                    }
                    hero.heroStateClimbing();

                    //hero not able to go below ladder
                    if (hero.getY() < object.sprite.getY()) {
                        hero.setY(object.sprite.getY());
                    }
                    return;
                }
            }
        }


        //loops through all obstacles to see if hero collides with them
        for (Obstacle obstacle : obstacles) {


            //hero is not able to jump through platform (except if he is climbing a ladder)
            if (hero.collidesWith(obstacle.getCollisionRectangle())) {

                if (hero.getY() < obstacle.sprite.getY() && !(hero.getState() == Hero.HeroState.CLIMBING)) {
                    hero.setSpeedY(-1);
                    return;
                }
                //if hero collides with obstacle (from above) put him ontop of obstacle and set Yspeed to 0

                if (hero.getSpeedY() < 1 && (hero.collidesWith(obstacle.getCollisionRectangle())) && !(obstacle.hasIce)) {
                    hero.setSpeedY(0);
                    hero.heroStateWalking();
                    hero.setY(obstacle.sprite.getY() + obstacle.sprite.getHeight() - 10);
                    return;
                }

                if (obstacle.hasIce) {

                    if (hero.getSpeedY() < 1 && hero.collidesWith(obstacle.getCollisionRectangle())) {
                        hero.setSpeedY(0);
                        hero.heroStateSliding();
                        hero.setY(obstacle.sprite.getY() + obstacle.sprite.getHeight() - 10);
                        return;
                    }
                }
            }
        }

    //max speedY = -5 for each render speedY becomes faster (increase fallspeed)
                if (hero.getSpeedY() > -5) {
                    hero.heroStateFlying();
                    hero.setSpeedY(hero.getSpeedY() - 0.1f);
                }
    }

    public void checkEnemyCollision() {
        if (hero.getY() < 2) {
            Life = Life -1;
            if (Life <= 0) {
                highscores.add(score);
                Collections.sort(highscores);
                Collections.reverse(highscores);
                figures.clear();
                obstacles.clear();
                state = GameState.GAME_OVER;
            }
            if (state == GameState.LEVEL_ONE) {
                figures.clear();
                obstacles.clear();
                createObstacles();
                createEnemyOne();
            }
            if (state == GameState.LEVEL_TWO) {
                figures.clear();
                obstacles.clear();
                createObstaclesTwo();
                createEnemyTwo();
            }
            if (state == GameState.LEVEL_THREE) {
                figures.clear();
                obstacles.clear();
                createObstaclesThree();
                createEnemyThree();
            }
            if (state == GameState.LEVEL_FOUR){
                figures.clear();
                obstacles.clear();

                createObstaclesFour();
                createEnemyFour();
            }
            createHero();
            return;
        }

        Iterator<Coin> iter = coins.iterator();
        while (iter.hasNext()) {
            Coin coin = iter.next();
            if (hero.collidesWith(coin.getCollisionRectangle())) {
                iter.remove();
                score += 500;
            }
        }


        for (int i = 0; i < figures.size(); i++) {
                if (hero.collidesWith(figures.get(i).getCollisionRectangle())) {
                        Life = Life - 1;
                        if (Life <= 0) {
                            createHero();
                            highscores.add(score);
                            state = GameState.GAME_OVER;
                        }
                        if (state == GameState.LEVEL_ONE) {
                            figures.clear();
                            obstacles.clear();
                            createObstacles();
                            createEnemyOne();
                        }
                        if (state == GameState.LEVEL_TWO) {
                            figures.clear();
                            obstacles.clear();
                            createObstaclesTwo();
                            createEnemyTwo();
                        }
                        if (state == GameState.LEVEL_THREE) {
                            figures.clear();
                            obstacles.clear();
                            createObstaclesThree();
                            createEnemyThree();
                        }
                        if (state == GameState.LEVEL_FOUR) {
                            figures.clear();
                            obstacles.clear();
                            createObstaclesFour();
                            createEnemyFour();
                        }
                        createHero();

            }
        }
    }

    public void updatePositions() {
        for (Figure figure : figures) {
            figure.updatePosition();
        }
    }

    public void showStartScreen() {

        spriteBatch.begin();
        spriteBatch.draw(start, 411, 144);
        spriteBatch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
            state = GameState.LEVEL_ONE;
            hero.setX(10);
            hero.setY(600);
            score = 0;
            Life = 3;
            timeSeconds = 100;
            createObstacles();
            createEnemyOne();
            createCoinOne();
            start_Screen_Music.stop();
        } else if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
            state = GameState.CONTROLS;
        }else if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)){
            state = GameState.HIGH_SCORE;
        } else if (Gdx.input.isKeyPressed(Input.Keys.NUM_4)) {
            System.exit(0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.NUM_5)) {
            state = GameState.GAME_OVER;
            start_Screen_Music.stop();
        } else if (Gdx.input.isKeyPressed(Input.Keys.NUM_7)) {
            obstacles.clear();
            figures.clear();
            coins.clear();
            interActiveObjects.clear();
            createObstaclesTwo();
            createEnemyTwo();
            createCoinTwo();
            createHero();
            state = GameState.LEVEL_TWO;  //Knapp 7 för att kunna testköra Level2
            start_Screen_Music.stop();
            currentlevel =2;

        }
        else if (Gdx.input.isKeyPressed(Input.Keys.NUM_8)) {
            createObstaclesThree();
            createEnemyThree();
            createHero();
            createCoinThree();
            state = GameState.LEVEL_THREE;  //Knapp 8 för att kunna testköra Level3
            start_Screen_Music.stop();
            currentlevel=3;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.NUM_9)) {
            createObstaclesFour();
            createEnemyFour();
            createHero();
            createCoinFour();
            state = GameState.LEVEL_FOUR;
            start_Screen_Music.stop();
            currentlevel = 4;
        } else if (Gdx.input.isKeyPressed(Input.Keys.F1)){
            gameOver();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.F2)){
            figures.clear();
            createObstaclesThree();
            createCoinThree();
            state = GameState.LEVEL_THREE;
        }
    }

    public void gameOver() {

        //scoredisplay = score;
        spriteBatch.begin();
        spriteBatch.draw(gameover, 480, 210);
        //highscorefont.draw(spriteBatch, "Your score:  " + scoredisplay, 645, 360);
        score = 0;
        Life = 3;
        currentlevel = 1;
        timeSeconds = 100;

        spriteBatch.end();
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            state = GameState.START_SCREEN;
        }

    }

    public void levelComplete(){

        spriteBatch.begin();
        spriteBatch.draw(level_complete, 411, 144);
        spriteBatch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            if (currentlevel == 1){
                currentlevel++;
                state = GameState.LEVEL_TWO;
            } else if (currentlevel == 2){
                currentlevel++;
                state = GameState.LEVEL_THREE;
            } else if (currentlevel == 3){
                currentlevel++;
                state = GameState.LEVEL_FOUR;
            }
        }
    }

    public void gameComplete(){
        spriteBatch.begin();
        spriteBatch.draw(game_complete, 411, 144);
        spriteBatch.end();


        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            state = GameState.START_SCREEN;
        }
    }

    public void showHighScore(){

        //highscore != 0 &&
        float ypos = 405;
        float xpos = 598;
        int rank = 1;
        spriteBatch.begin();
        spriteBatch.draw(high_score, 411, 144);
        if (highscores.size() != 0) {
            for (Integer highscore : highscores){
                if ( rank <= 10) {
                    if (rank == 10){
                        xpos -= 7;
                    }
                    highscorefont.draw(spriteBatch, rank + "                           " + highscore, xpos, ypos);
                    rank++;
                    ypos -= 20;
                }
            }
        }
        spriteBatch.end();
        if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
            state = GameState.START_SCREEN;
        }
    }

    public void showControls() {
        spriteBatch.begin();
        spriteBatch.draw(controls, 411, 144);
        spriteBatch.end();
        if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
            state = GameState.START_SCREEN;
        }
    }

    public void renderLevelOne() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT); //Clears the screen each frame.
        stateTime += Gdx.graphics.getDeltaTime(); // Adds the time elapsed since the last render to the stateTime.

        currentFrame = Coin.walkAnimation.getKeyFrame(stateTime, true); //Obtains the current frame


        checkObstacleCollision();
        checkEnemyCollision();
        updatePositions();

        hero.updatePosition();
        for (Figure figure : figures) {
            figure.updatePosition();
        }

        spriteBatch.begin();
        spriteBatch.draw(backGroundImg, 0, 20);
        font.draw(spriteBatch, "Number of lives:" + Life, 20, 700);
        font.draw(spriteBatch, "Score:" + score, 20, 680);
        font.draw(spriteBatch, displayTime, 20,720);




        //draws all platforms on the map
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(spriteBatch);
        }
        for (InteractiveObject object : interActiveObjects){
            object.draw(spriteBatch);
        }
        door.draw(spriteBatch);

        for (Coin coin : coins){
            coin.drawCoin(spriteBatch);
        }


        //Update all game figures' positions based on their speeds
        hero.draw(spriteBatch);
        for (Figure figure : figures) {
            figure.draw(spriteBatch);
        }

        spriteBatch.end();
        //makes the hero stop when he is not walking
        if (hero.getState() == Hero.HeroState.WALKING && !(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) && !(Gdx.input.isKeyPressed(Input.Keys.LEFT))) {
            hero.setSpeedX(0);
        }
        //makes the hero lose momentum during jump and falling
        if (hero.getState() == Hero.HeroState.FLYING && hero.getSpeedX() < 0) {
            hero.setSpeedX(hero.getSpeedX() + 0.03f);
        }
        if (hero.getState() == Hero.HeroState.FLYING && hero.getSpeedX() > 0) {
            hero.setSpeedX(hero.getSpeedX() - 0.03f);
        }
        //makes the hero stop when he is on ladders
        if (hero.getState() == Hero.HeroState.CLIMBING && !(Gdx.input.isKeyPressed(Input.Keys.UP)) && !(Gdx.input.isKeyPressed(Input.Keys.DOWN))) {
            hero.stopHeight();
        }


    }

    public void renderLevelTwo() {




        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT); //Clears the screen each frame.
        stateTime += Gdx.graphics.getDeltaTime(); // Adds the time elapsed since the last render to the stateTime.

        currentFrame = Coin.walkAnimation.getKeyFrame(stateTime, true); //Obtains the current frame



        checkEnemyCollision();
        updatePositions();
        checkObstacleCollision();

        hero.updatePosition();

        for (Figure figure : figures) {
            figure.updatePosition();
        }



        spriteBatch.begin();
        spriteBatch.draw(backGroundImg, 0, 20);
        font.draw(spriteBatch, "Number of lives:"+ Life, 20, 700);
        font.draw(spriteBatch, "Score:" + score, 20, 680);
        spriteBatch.draw(cactus,550,20,200,140);
        font.draw(spriteBatch, displayTime, 20,720);

        for (Coin coin : coins){
            coin.drawCoin(spriteBatch);
        }

        for (Obstacle obstacle : obstacles) {
            obstacle.draw(spriteBatch);
        }
        for (InteractiveObject object : interActiveObjects){
            object.draw(spriteBatch);
        }
        doorTwo.draw(spriteBatch);

        hero.draw(spriteBatch);
        //Update all game figures' positions based on their speeds

        for (Figure figure : figures) {
            figure.draw(spriteBatch);
        }

        spriteBatch.end();
        //makes the hero stop when he is not walking
        if (hero.getState() == Hero.HeroState.WALKING && !(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) && !(Gdx.input.isKeyPressed(Input.Keys.LEFT))) {
            hero.setSpeedX(0);
        }
        //makes the hero lose momentum during jump and falling
        if (hero.getState() == Hero.HeroState.FLYING && hero.getSpeedX() < 0) {
            hero.setSpeedX(hero.getSpeedX() + 0.03f);
        }
        if (hero.getState() == Hero.HeroState.FLYING && hero.getSpeedX() > 0) {
            hero.setSpeedX(hero.getSpeedX() - 0.03f);
        }
        //makes the hero stop when he is on ladders
        if (hero.getState() == Hero.HeroState.CLIMBING && !(Gdx.input.isKeyPressed(Input.Keys.UP)) && !(Gdx.input.isKeyPressed(Input.Keys.DOWN))) {
            hero.stopHeight();
        }




    }

    public void renderLevelThree() {



        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);


        stateTime += Gdx.graphics.getDeltaTime(); // Adds the time elapsed since the last render to the stateTime.
        currentFrame = Coin.walkAnimation.getKeyFrame(stateTime, true);

        checkEnemyCollision();
        checkObstacleCollision();
        updatePositions();
        hero.updatePosition();

        for (Figure figure : figures) {
            figure.updatePosition();
        }

        spriteBatch.begin();
        spriteBatch.draw(backGroundLevelThree,-400,0);

        font.draw(spriteBatch, "Number of lives:" + Life, 0, 700);
        font.draw(spriteBatch, "Score:" + score, 20, 680);
        font.draw(spriteBatch, displayTime, 20,720);

        for (Coin coin : coins){
            coin.drawCoin(spriteBatch);
        }

        for(Obstacle obstacle: obstacles) {
            obstacle.draw(spriteBatch);
        }
        for (InteractiveObject object : interActiveObjects){
            object.draw(spriteBatch);
        }
        doorThree.draw(spriteBatch);
        iceForm12.draw(spriteBatch);
        iceForm11.draw(spriteBatch);

        hero.draw(spriteBatch);
        for (Figure figure : figures) {
            figure.draw(spriteBatch);
        }
        spriteBatch.end();

        if (hero.getState() == Hero.HeroState.WALKING && !(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) && !(Gdx.input.isKeyPressed(Input.Keys.LEFT))) {
            hero.setSpeedX(0);
        }
        //makes the hero lose momentum during jump and falling
        if (hero.getState() == Hero.HeroState.FLYING && hero.getSpeedX() < 0) {
            hero.setSpeedX(hero.getSpeedX() + 0.03f);
        }
        if (hero.getState() == Hero.HeroState.FLYING && hero.getSpeedX() > 0) {
            hero.setSpeedX(hero.getSpeedX() - 0.03f);
        }
        if (hero.getState() == Hero.HeroState.SLIDING && hero.getSpeedX() < 0) {
            hero.setSpeedX(hero.getSpeedX() - 0.03f);
        }
        if (hero.getState() == Hero.HeroState.SLIDING && hero.getSpeedX() > 0) {
            hero.setSpeedX(hero.getSpeedX() + 0.03f);
        }
        //makes the hero stop when he is on ladders
        if (hero.getState() == Hero.HeroState.CLIMBING && !(Gdx.input.isKeyPressed(Input.Keys.UP)) && !(Gdx.input.isKeyPressed(Input.Keys.DOWN))) {
            hero.stopHeight();
        }


        if(state == GameState.LEVEL_THREE && hero.collidesWith(iceForm11.getCollisionRectangle()) || hero.collidesWith(iceForm12.getCollisionRectangle()))  {
            hero.setSpeedX(0);
            hero.setX(iceForm11.sprite.getX()+(iceForm11.sprite.getWidth()-10));
            return;
        }

    }

    public void renderLevelFour() {

        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);


        stateTime += Gdx.graphics.getDeltaTime(); // Adds the time elapsed since the last render to the stateTime.
        currentFrame = Coin.walkAnimation.getKeyFrame(stateTime, true);
        currentframtwo = AnimatedObject.walkAnimation.getKeyFrame(stateTime, true);



        checkEnemyCollision();
        updatePositions();
        checkObstacleCollision();

        hero.updatePosition();
        for (Figure figure : figures) {
            figure.updatePosition();
        }



        spriteBatch.begin();
        spriteBatch.draw(backGroundImg, 0, 20);
        spriteBatch.draw(currentframtwo,300,-60);
        spriteBatch.draw(currentframtwo,0,-60);
        font.draw(spriteBatch, "Number of lives:"+ Life, 20, 700);
        font.draw(spriteBatch, "Score:" + score, 20, 680);
        font.draw(spriteBatch, displayTime, 20,720);


        for (Coin coin : coins){
            coin.drawCoin(spriteBatch);

        }


        for (Obstacle obstacle : obstacles) {
            obstacle.draw(spriteBatch);
        }
        for (InteractiveObject object : interActiveObjects){
            object.draw(spriteBatch);
        }

        endPoint.draw(spriteBatch);
        hero.draw(spriteBatch);
        //Update all game figures' positions based on their speeds


        for (Figure figure : figures) {
            figure.draw(spriteBatch);
        }

        spriteBatch.end();
        //makes the hero stop when he is not walking
        if (hero.getState() == Hero.HeroState.WALKING && !(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) && !(Gdx.input.isKeyPressed(Input.Keys.LEFT))) {
            hero.setSpeedX(0);
        }
        //makes the hero lose momentum during jump and falling
        if (hero.getState() == Hero.HeroState.FLYING && hero.getSpeedX() < 0) {
            hero.setSpeedX(hero.getSpeedX() + 0.03f);
        }
        if (hero.getState() == Hero.HeroState.FLYING && hero.getSpeedX() > 0) {
            hero.setSpeedX(hero.getSpeedX() - 0.03f);
        }
        //makes the hero stop when he is on ladders
        if (hero.getState() == Hero.HeroState.CLIMBING && !(Gdx.input.isKeyPressed(Input.Keys.UP)) && !(Gdx.input.isKeyPressed(Input.Keys.DOWN))) {
            hero.stopHeight();
        }





    }

    @Override
    public boolean keyDown(int keycode) {

        if (keycode == Input.Keys.SPACE && state != GameState.START_SCREEN && state != GameState.LEVEL_COMPLETE && state != GameState.GAME_OVER) {
            hero.jump();
        }
        if (keycode == Input.Keys.RIGHT) {
            hero.goRight();
        }
        if (keycode == Input.Keys.LEFT) {
            hero.goLeft();
        }
        if (keycode == Input.Keys.UP && hero.getState() == Hero.HeroState.CLIMBING) {
            hero.goUp();
        }
        if (keycode == Input.Keys.DOWN && hero.getState() == Hero.HeroState.CLIMBING) {
            hero.goDown();
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        if (keycode == Input.Keys.RIGHT && hero.getSpeedY() == 0 &&(hero.getState()==Hero.HeroState.WALKING||hero.getState()== Hero.HeroState.CLIMBING)) {
            hero.stopComplete();
        }
        if (keycode == Input.Keys.LEFT &&  hero.getSpeedY() == 0 && (hero.getState()==Hero.HeroState.WALKING||hero.getState()== Hero.HeroState.CLIMBING)) {
            hero.stopComplete();
        }
        if (keycode == Input.Keys.UP && hero.getState() == Hero.HeroState.CLIMBING ) {
            hero.stopComplete();
        }
        if (keycode == Input.Keys.DOWN && hero.getState() == Hero.HeroState.CLIMBING ) {
            hero.stopComplete();
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private enum GameState {

        START_SCREEN,
        CONTROLS,
        GAME_OVER,
        HIGH_SCORE,
        LEVEL_COMPLETE,
        GAME_COMPLETE,
        LEVEL_ONE,
        LEVEL_TWO,
        LEVEL_THREE,
        LEVEL_FOUR,
        LEVEL_FIVE,
        LEVEL_SIX,
        LEVEL_SEVEN,
        LEVEL_EIGHT,
        LEVEL_NINE,
        LEVEL_TEN;

    }

    public void playMusic(){

        if (state == GameState.START_SCREEN){
            start_Screen_Music.play();
            level_Music.stop();
        }
        else if (state == GameState.LEVEL_ONE ||state == GameState.LEVEL_TWO ||state == GameState.LEVEL_THREE ||state == GameState.LEVEL_FOUR ){
            level_Music.play();
            start_Screen_Music.stop();
        }
    }

    public void countTime(){

        if (timeSeconds >= 0) {
            timeSeconds -= Gdx.graphics.getRawDeltaTime();
        }
}

}

