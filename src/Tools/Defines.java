package Tools;

public class Defines {
    public static final int UP_ARROW = 72;
    public static final int DOWN_ARROW = 80;
    public static final int RIGHT_ARROW = 77;
    public static final int LEFT_ARROW = 75;
    public static final int ARROW_CLICK = 224;
    public static final int ESCAPE = 27;

    public static final char SUPERPOWER = 'f';
    public static final char SAVE = 's';
    public static final char LOAD = 'l';
    public static final char REFRESH = 'r';

    public static final int BOARD_POS_X = 0;
    public static final int BOARD_POS_Y = 0;
    public static final int X_SCALING = 2;
    public static final int X_FRAME = 2;
    public static final int Y_FRAME = 1;

    public static final int LOG_X = (BOARD_POS_X + 10 * X_SCALING + X_FRAME + 10);
    public static final int LOG_Y = (BOARD_POS_Y);
    public static final int LOG_LENGTH = 20;
    public static final int LOG_LINE_LENGTH = 50;

    public static final char HUMAN_SYMBOL = 'H';
    public static final char WOLF_SYMBOL = 'W';
    public static final char SHEEP_SYMBOL = 'S';
    public static final char FOX_SYMBOL = 'F';
    public static final char TURTLE_SYMBOL = 'T';
    public static final char ANTELOPE_SYMBOL = 'A';

    public static final char GRASS_SYMBOL = 'G';
    public static final char DANDELION_SYMBOL = 'D';
    public static final char GUARANA_SYMBOL = 'U';
    public static final char WOLFBERRIES_SYMBOL = 'B';
    public static final char PINEBORSCHT_SYMBOL = 'P';

    public static final int ATTACKER_WINS = 1;
    public static final int DEFENDER_WINS = 0;
    public static final int ATTACKER_RETREATS = 2;
    public static final int DEFENDER_RUNS_AWAY = 3;
    public static final int BOTH_DIED = 4;
    public static final int ATTACKER_EATS = 5;

    public static final int NO_EMPTY_ADJACENT = -1;

    public static final boolean SLOW_MODE = false;
    public static final boolean DEBUG_MODE = false;

    public static final String RESET_COLOR = "\033[0m";
    public static final String SET_LETTER_RED = "\033[31m";
    public static final String SET_LETTER_GREEN = "\033[32m";
    public static final String SET_LETTER_YELLOW = "\033[33m";
    public static final String SET_LETTER_BLUE = "\033[34m";
    public static final String SET_LETTER_MAGENTA = "\033[35m";
    public static final String SET_LETTER_CYAN = "\033[36m";
    public static final String SET_LETTER_WHITE = "\033[37m";
    public static final String SET_LETTER_BLACK = "\033[30m";

    public static final int SET_BG_BLACK = 0;
    public static final int SET_BG_BLUE = 16;
    public static final int SET_BG_GREEN = 32;
    public static final int SET_BG_CYAN = 48;
    public static final int SET_BG_RED = 64;
    public static final int SET_BG_MAGENTA = 80;
    public static final int SET_BG_YELLOW = 96;
    public static final int SET_BG_GRAY = 112;
    public static final int SET_BG_DARKGRAY = 128;
    public static final int SET_BG_LIGHTBLUE = 144;
    public static final int SET_BG_LIGHTGREEN = 160;
    public static final int SET_BG_LIGHTCYAN = 176;
    public static final int SET_BG_LIGHTRED = 192;
    public static final int SET_BG_LIGHTMAGENTA = 208;
    public static final int SET_BG_LIGHTYELLOW = 224;
    public static final int SET_BG_WHITE = 240;

    public static final int HUMAN_COLOR = SET_BG_GREEN;
    public static final int TURTLE_COLOR = SET_BG_CYAN;
    public static final int FOX_COLOR = SET_BG_RED;
    public static final int GUARANA_COLOR = SET_BG_MAGENTA;
    public static final int DANDELION_COLOR = SET_BG_YELLOW;
    public static final int WOLF_COLOR = SET_BG_GRAY;
    public static final int WOLFBERRIES_COLOR = SET_BG_DARKGRAY;
    public static final int MAP_BORDER_COLOR = SET_BG_LIGHTBLUE;
    public static final int GRASS_COLOR = SET_BG_LIGHTGREEN;
    public static final int PINEBORSCHT_COLOR = SET_BG_LIGHTRED;
    public static final int ANTELOPE_COLOR = SET_BG_LIGHTMAGENTA;
    public static final int MAP_SURFACE_COLOR = SET_BG_LIGHTYELLOW;
    public static final int SHEEP_COLOR = SET_BG_WHITE;

    public static final int PLANT_STRENGTH = 0;
    public static final int PLANT_SPREAD_CHANCE = 20;
    public static final int GUARANA_BOOST = 3;

    public static final int HUMAN_STRENGTH = 5;
    public static final int HUMAN_INITIATIVE = 4;
    public static final int MAX_COOLDOWN = 10;
    public static final int END_OF_POWER = 5;
    public static final int POWER_WORSE_STATE = 7;

    public static final int WOLF_STRENGTH = 9;
    public static final int WOLF_INITIATIVE = 5;

    public static final int SHEEP_STRENGTH = 4;
    public static final int SHEEP_INITIATIVE = 4;

    public static final int FOX_STRENGTH = 3;
    public static final int FOX_INITIATIVE = 7;

    public static final int TURTLE_STRENGTH = 2;
    public static final int TURTLE_INITIATIVE = 1;
    public static final int MIN_ATTACK_TO_KILL = 4;
    public static final int TURTLE_MOVE_CHANCE = 4;

    public static final int ANTELOPE_STRENGTH = 4;
    public static final int ANTELOPE_INITIATIVE = 4;
    public static final int ANTELOPE_JUMP = 2;

    public static final int WOLFBERRIES_STRENGTH = 99;

    public static final int PINEBORSCHT_STRENGTH = 10;

    public static final int DANDELION_TURNS = 3;

    public static final int WOLF_COUNT = 4;
    public static final int SHEEP_COUNT = 4;
    public static final int FOX_COUNT = 4;
    public static final int TURTLE_COUNT = 4;
    public static final int ANTELOPE_COUNT = 4;
    public static final int GRASS_COUNT = 4;
    public static final int DANDELION_COUNT = 4;
    public static final int GUARANA_COUNT = 4;
    public static final int WOLFBERRIES_COUNT = 4;
    public static final int PINEBORSCHT_COUNT = 4;

    public static final int ORGANISMS_COUNT = WOLF_COUNT + SHEEP_COUNT + FOX_COUNT + TURTLE_COUNT +
            ANTELOPE_COUNT + GRASS_COUNT + DANDELION_COUNT + GUARANA_COUNT +
            WOLFBERRIES_COUNT + PINEBORSCHT_COUNT + 1;

    public static final int UNIQUE_ORGANISMS = 11;
    public static final int MIN_MAP_SIZE = 8;

    public static final int LEGEND_X = LOG_X;
    public static final int LEGEND_Y = 400;

    public static final int CONTROLS_X = LEGEND_X + 20;
    public static final int CONTROLS_Y = LEGEND_Y;

    public static final int NOT_IN_PLAY = -1;

    public static final int NEXTTURNWIDTH = 90;
    public static final int NEXTTURNHEIGHT = 30;


    public static final int LOG_WIDTH = 500;
    public static final int LOG_HEIGHT = 350;
    public static final int LEGEND_WIDTH = 500;
    public static final int LEGEND_HEIGHT = 500;
}


