package gui;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Manages imported graphics of the client.
 * 
 * @author YU Shijia
 * 
 */
public class ImportImages {

	/*
	 * Yellow player
	 */
	public static Image playerYellow;
	
	public static Image playerGreen;
	
	public static Image playerRed;
	
	public static Image playerBlue;
	/*
	 *I'm on the train edge, which is about the player and opponent frames to be set
	 */
	public static Image myturn;

	public static Image brick;

	public static Image desert;

	public static Image grain;

	public static Image lumber;

	public static Image ore;

	public static Image wool;

	/*
	 * Harbor in northwest orientation
	 */
	public static Image harbor;

	/*
	 * Harbor in northeast orientation
	 */
	public static Image harbor1;

	/*
	 *Harbor in east orientation
	 */
	public static Image harbor2;

	/*
	 * Harbor in southeast orientation
	 */
	public static Image harbor3;

	/*
	 * Harbor in southwest orientation
	 */
	public static Image harbor4;

	/*
	 * Harbor in west orientation
	 */
	public static Image harbor5;

	public static Image brickBtn;

	public static Image grainBtn;

	public static Image lumberBtn;

	public static Image oreBtn;

	public static Image woolBtn;

	/*
	 * Question Mark Button
	 */
	public static Image questionBtn;

	/**
	 * Clay icon (without border)
	 */
	public static Image brickIcon;

	public static Image grainIcon;

	public static Image harborIcon;

	public static Image lumberIcon;

	public static Image oreIcon;

	public static Image woolIcon;

	/*
	 * Number chip with number 2
	 */
	public static Image chit02;

	public static Image chit03;

	public static Image chit04;

	public static Image chit05;

	public static Image chit06;

	public static Image chit08;

	public static Image chit09;

	public static Image chit10;

	public static Image chit11;

	public static Image chit12;

	/*
	 * Scrolls background
	 */
	public static Image chatBg;

	/*
	 * LoginMenu Background graphic
	 */
	public static Image loginBG;

	/*
	 * Starcraft Depot
	 */
	public static Image testvillage;

	public static Image robber;

	public static Image settlementBlue;

	/*
	 * Gr&uuml;ne Sieldung
	 */
	public static Image settlementGreen;

	public static Image settlementRed;

	public static Image settlementYellow;

	/*
	 * Standard Sielding
	 */
	public static Image settlement;

	public static Image cityBlue;

	public static Image cityGreen;

	public static Image cityRed;

	public static Image cityYellow;

	//User avatar graphics
	public static Image kerriganAvatar;

	public static Image zergAvatar;

	public static Image tossAvatar;
	
	/*
	 * Trade Button
	 */
	public static Image tradeBtn;
	/*
	 * Trade button activated
	 */
	public static Image tradeBtnActive;

	public static Image cardBtn;
	
	public static Image cardBtnActive;

	public static Image rollBtn;
	
	public static Image rollBtnActive;

	public static Image nextBtn;
	
	public static Image nextBtnActive;

	/*
	 * Label for Roads
	 */
	public static Image roadLbl;

	/*
	 * Label for knights
	 */
	public static Image armyLbl;

	/*
	 * Label for the player name
	 */
	public static Image nameLbl;

	public static Image resourceLbl;

	public static Image roadBtn;
	
	public static Image roadBtnActive;

	public static Image settlementBtn;
	
	public static Image settlementBtnActive;

	public static Image cityBtn;
	
	public static Image cityBtnActive;

	public static Image dice1;

	public static Image dice2;

	public static Image dice3;

	public static Image dice4;

	public static Image dice5;

	public static Image dice6;

	/*
	 * Double-sided arrow to complete trades
	 */
	public static Image arrowDouble;
	
	public static Image arrowDoubleActive;

	public static Image confirmBtn;
	
	public static Image confirmBtnActive;

	public static Image cancelBtn;
	
	public static Image cancelBtnActive;
	
	public static Image muteBtn;

	/*
	 * Inventions Development Board button
	 */
	public static Image inventionBtn;
	
	public static Image inventionBtnActive;

	public static Image monopolyBtn;

	public static Image monopolyBtnActive;

	public static Image knightBtn;
	
	public static Image knightBtnActive;

	public static Image victoryPointBtn;
	
	public static Image buildStreetBtn;
	
	public static Image buildStreetBtnActive;

	public static Image buyCardBtn;
	
	public static Image buyCardBtnActive;

	/*
	 * Original knight card
	 */
	public static Image knight;

	public static Image develop_invention;

	public static Image develop_monopoly;

	public static Image develop_streets;

	public static Image backside;

	public static Image card_ore;

	public static Image card_grain;

	public static Image card_lumber;

	public static Image card_brick;

	public static Image card_wool;

	public static Image victory;
	
	public static Image buildingcostsmenuBtn;
	
	public static Image buildingcostsmenuBtnActive;

	/*
	 * Red strait down
	 */
	public static Image red_road_down;

	/*
	 *Red road to the top
	 */
	public static Image red_road_up;

	/*
	 *Red street vertical
	 */
	public static Image red_road_vert;

	public static Image blue_road_down;

	public static Image blue_road_up;

	public static Image blue_road_vert;

	public static Image green_road_down;

	public static Image green_road_up;

	public static Image green_road_vert;

	public static Image yellow_road_down;

	public static Image yellow_road_up;

	public static Image yellow_road_vert;

	/*
	 * Status accepted
	 */
	public static Image statusAcc;
	
	/*
	 * Status rejected
	 */
	public static Image statusDen;
	
	public static Image statusWait;

	public static Image english;
	
	public static Image artasAvatar;

	public static Image daemonAvatar;
	
	public static Image humanAvatar;
	
	public static Image nightelfAvatar;
	
	public static Image orcAvatar;
	/*
	 * Undead avatar
	 */
	public static Image undeadAvatar;
	
	public static Image[] avatarArray;
	
	/*
	 * Button Inverted
	 */
	public static Image buttonInvert;
	
	/*
	 * Lets the pictures.
	 */
	public void loadPics() {
		
		robber = new ImageIcon(getClass().getResource("graphics/buildings/raeuber.png")).getImage();
		
		//english = new ImageIcon(getClass().getResource("graphics/other/english.png")).getImage();
		
		playerBlue = new ImageIcon(getClass().getResource("graphics/oppInfo/Spieler_Blau.png")).getImage(); 
		playerRed = new ImageIcon(getClass().getResource("graphics/oppInfo/Spieler_Rot.png")).getImage(); 
		playerYellow = new ImageIcon(getClass().getResource("graphics/oppInfo/Spieler_Gelb.png")).getImage();
		playerGreen = new ImageIcon(getClass().getResource("graphics/oppInfo/Spieler_Gruen.png")).getImage(); 
		buttonInvert = new ImageIcon(getClass().getResource("graphics/buttonPics/buttonInvert.png")).getImage(); 
		
		myturn = new ImageIcon(getClass().getResource(
				"graphics/info/myturn.png")).getImage(); 

		desert = new ImageIcon(getClass().getResource(
				"graphics/tiles/wueste.png")).getImage(); 
		
		brick = new ImageIcon(getClass().getResource(
				"graphics/tiles/lehm.png")).getImage();;

		grain = new ImageIcon(getClass().getResource(
				"graphics/tiles/getreide.png")).getImage();;

		lumber = new ImageIcon(getClass().getResource(
				"graphics/tiles/holz.png")).getImage();;

		ore = new ImageIcon(getClass().getResource(
				"graphics/tiles/erz.png")).getImage();;

		wool = new ImageIcon(getClass().getResource(
				"graphics/tiles/wolle.png")).getImage();;

		harbor = new ImageIcon(getClass().getResource(
				"graphics/tiles/hafenNW.png")).getImage(); 

		harbor1 = new ImageIcon(getClass().getResource(
				"graphics/tiles/hafenNO.png")).getImage(); 

		harbor2 = new ImageIcon(getClass().getResource(
				"graphics/tiles/hafenO.png")).getImage(); 

		harbor3 = new ImageIcon(getClass().getResource(
				"graphics/tiles/hafenSO.png")).getImage(); 

		harbor4 = new ImageIcon(getClass().getResource(
				"graphics/tiles/hafenSW.png")).getImage(); 

		harbor5 = new ImageIcon(getClass().getResource(
				"graphics/tiles/hafenW.png")).getImage(); 

		brickBtn = new ImageIcon(getClass().getResource(
				"graphics/buttonPics/buttonBrick.png")).getImage();

		grainBtn = new ImageIcon(getClass().getResource(
				"graphics/buttonPics/buttonGrain.png")).getImage(); 

		lumberBtn = new ImageIcon(getClass().getResource(
				"graphics/buttonPics/buttonLumber.png")).getImage();

		oreBtn = new ImageIcon(getClass().getResource(
				"graphics/buttonPics/buttonOre.png")).getImage(); 

		woolBtn = new ImageIcon(getClass().getResource(
				"graphics/buttonPics/buttonWool.png")).getImage(); 

		questionBtn = new ImageIcon(getClass().getResource(
				"graphics/buttonPics/buttonFragezeichen.png")).getImage(); 

		chit02 = new ImageIcon(getClass().getResource("graphics/chits/2.png"))
				.getImage();

		chit03 = new ImageIcon(getClass().getResource("graphics/chits/3.png")) 
				.getImage();

		chit04 = new ImageIcon(getClass().getResource("graphics/chits/4.png")) 
				.getImage();

		chit05 = new ImageIcon(getClass().getResource("graphics/chits/5.png")) 
				.getImage();

		chit06 = new ImageIcon(getClass().getResource("graphics/chits/6.png"))
				.getImage();

		chit08 = new ImageIcon(getClass().getResource("graphics/chits/8.png")) 
				.getImage();

		chit09 = new ImageIcon(getClass().getResource("graphics/chits/9.png"))
				.getImage();

		chit10 = new ImageIcon(getClass().getResource("graphics/chits/10.png")) 
				.getImage();

		chit11 = new ImageIcon(getClass().getResource("graphics/chits/11.png"))
				.getImage();

		chit12 = new ImageIcon(getClass().getResource("graphics/chits/12.png")) 
				.getImage();

		chatBg = new ImageIcon(getClass().getResource(
				"graphics/background/chatbg.png")).getImage(); 

		loginBG = new ImageIcon(getClass().getResource(
				"graphics/background/Siedler_von_Catan_Logo.png")).getImage(); 

		settlementBlue = new ImageIcon(getClass().getResource(
				"graphics/buildings/settlement_blue.png")).getImage(); 

		settlementGreen = new ImageIcon(getClass().getResource(
				"graphics/buildings/settlement_green.png")).getImage(); 

		settlementRed = new ImageIcon(getClass().getResource(
				"graphics/buildings/settlement_red.png")).getImage(); 

		settlementYellow = new ImageIcon(getClass().getResource(
				"graphics/buildings/settlement_yellow.png")).getImage(); 

		settlement = new ImageIcon(getClass().getResource(
				"graphics/buildings/settlement.png")).getImage();

		cityBlue = new ImageIcon(getClass().getResource(
				"graphics/buildings/city_blue.png")).getImage();

		cityGreen = new ImageIcon(getClass().getResource(
				"graphics/buildings/city_green.png")).getImage(); 

		cityRed = new ImageIcon(getClass().getResource(
				"graphics/buildings/city_red.png")).getImage(); 

		cityYellow = new ImageIcon(getClass().getResource(
				"graphics/buildings/city_yellow.png")).getImage(); 
/*
		kerriganAvatar = new ImageIcon(getClass().getResource(
				"graphics/avatars/kerrigan.png")).getImage(); 

		zergAvatar = new ImageIcon(getClass().getResource(
				"graphics/avatars/zerg.png")).getImage(); 

		tossAvatar = new ImageIcon(getClass().getResource(
				"graphics/avatars/toss.png")).getImage();
		artasAvatar = new ImageIcon(getClass().getResource("graphics/avatars/artas.png")).getImage();
		
		daemonAvatar = new ImageIcon(getClass().getResource("graphics/avatars/daemon.png")).getImage();
		
		humanAvatar = new ImageIcon(getClass().getResource("graphics/avatars/human.png")).getImage();
		
		nightelfAvatar = new ImageIcon(getClass().getResource("graphics/avatars/nightelf.png")).getImage();
		
		orcAvatar = new ImageIcon(getClass().getResource("graphics/avatars/orc.png")).getImage();
		
		undeadAvatar = new ImageIcon(getClass().getResource("graphics/avatars/undead.png")).getImage();
*/
		tradeBtn = new ImageIcon(getClass().getResource(
				"graphics/info/buttonTrade.png")).getImage(); 
		tradeBtnActive = new ImageIcon(getClass().getResource(
				"graphics/info/buttonTradeActive.png")).getImage(); 

		cardBtn = new ImageIcon(getClass().getResource(
				"graphics/info/buttonPlayCard.png")).getImage(); 
		cardBtnActive = new ImageIcon(getClass().getResource(
				"graphics/info/buttonPlayCardActive.png")).getImage(); 

		rollBtn = new ImageIcon(getClass().getResource(
				"graphics/info/buttonRoll.png")).getImage();
		rollBtnActive = new ImageIcon(getClass().getResource(
				"graphics/info/buttonRollActive.png")).getImage(); 

		nextBtn = new ImageIcon(getClass().getResource(
				"graphics/info/buttonNext.png")).getImage(); 
		nextBtnActive = new ImageIcon(getClass().getResource(
				"graphics/info/buttonNextActive.png")).getImage();

		roadLbl = new ImageIcon(getClass().getResource(
				"graphics/info/longestRoadLbl.png")).getImage(); 

		armyLbl = new ImageIcon(getClass()
				.getResource("graphics/info/army.png")).getImage(); 

		nameLbl = new ImageIcon(getClass().getResource(
				"graphics/info/nameLabel.png")).getImage();

		resourceLbl = new ImageIcon(getClass().getResource(
				"graphics/info/resources.png")).getImage(); 

		roadBtn = new ImageIcon(getClass().getResource(
				"graphics/buttonPics/buttonStreet.png")).getImage(); 
		roadBtnActive = new ImageIcon(getClass().getResource(
				"graphics/buttonPics/buttonStreetActive.png")).getImage(); 

		settlementBtn = new ImageIcon(getClass().getResource(
				"graphics/buttonPics/buttonSettlement.png")).getImage();
		settlementBtnActive = new ImageIcon(getClass().getResource(
				"graphics/buttonPics/buttonSettlementActive.png")).getImage();

		cityBtn = new ImageIcon(getClass().getResource(
				"graphics/buttonPics/buttonCity.png")).getImage(); 
		cityBtnActive = new ImageIcon(getClass().getResource(
				"graphics/buttonPics/buttonCityActive.png")).getImage();

		dice1 = new ImageIcon(getClass().getResource("graphics/dice/dice1.png"))
				.getImage();

		dice2 = new ImageIcon(getClass().getResource("graphics/dice/dice2.png")) 
				.getImage();

		dice3 = new ImageIcon(getClass().getResource("graphics/dice/dice3.png")) 
				.getImage();

		dice4 = new ImageIcon(getClass().getResource("graphics/dice/dice4.png"))
				.getImage();

		dice5 = new ImageIcon(getClass().getResource("graphics/dice/dice5.png")) 
				.getImage();

		dice6 = new ImageIcon(getClass().getResource("graphics/dice/dice6.png")) 
				.getImage();

		arrowDouble = new ImageIcon(getClass().getResource(
				"graphics/other/arrow.png")).getImage(); 
		arrowDoubleActive = new ImageIcon(getClass().getResource(
				"graphics/other/arrowActive.png")).getImage(); 

		confirmBtn = new ImageIcon(getClass().getResource(
				"graphics/buttonPics/buttonPlay.png")).getImage(); 
		confirmBtnActive = new ImageIcon(getClass().getResource(
				"graphics/buttonPics/buttonPlayActive.png")).getImage();

		cancelBtn = new ImageIcon(getClass().getResource(
				"graphics/buttonPics/buttonStop.png")).getImage(); 
		cancelBtnActive = new ImageIcon(getClass().getResource(
				"graphics/buttonPics/buttonStopActive.png")).getImage(); 

		inventionBtn = new ImageIcon(getClass().getResource(
				"graphics/cardIcons/buttonErfindung.png")).getImage(); 
		inventionBtnActive = new ImageIcon(getClass().getResource(
				"graphics/cardIcons/buttonErfindungActive.png")).getImage();

		monopolyBtn = new ImageIcon(getClass().getResource(
				"graphics/cardIcons/buttonMonopol.png")).getImage(); 
		monopolyBtnActive = new ImageIcon(getClass().getResource(
				"graphics/cardIcons/buttonMonopolActive.png")).getImage();

		knightBtn = new ImageIcon(getClass().getResource(
				"graphics/cardIcons/buttonRittermacht.png")).getImage();
		knightBtnActive = new ImageIcon(getClass().getResource(
				"graphics/cardIcons/buttonRittermachtActive.png")).getImage();

		victoryPointBtn = new ImageIcon(getClass().getResource(
				"graphics/cardIcons/buttonSiegpunkt.png")).getImage(); 
		
		buildStreetBtn = new ImageIcon(getClass().getResource("graphics/cardIcons/buttonBuildStreets.png")).getImage(); 
		buildStreetBtnActive = new ImageIcon(getClass().getResource("graphics/cardIcons/buttonBuildStreetsActive.png")).getImage();
		
		buyCardBtn = new ImageIcon(getClass().getResource("graphics/cardIcons/buttonBuyCard.png")).getImage(); 
		buyCardBtnActive = new ImageIcon(getClass().getResource("graphics/cardIcons/buttonBuyCardActive.png")).getImage();

		buildingcostsmenuBtn = new ImageIcon(getClass().getResource(
				"graphics/buttonPics/buttonBaukosten.png")).getImage(); 
		buildingcostsmenuBtnActive = new ImageIcon(getClass().getResource(
				"graphics/buttonPics/buttonBaukostenActive.png")).getImage();

		red_road_down = new ImageIcon(getClass().getResource(
				"graphics/roads/red_down.png")).getImage();

		red_road_up = new ImageIcon(getClass().getResource(
				"graphics/roads/red_up.png")).getImage(); 

		red_road_vert = new ImageIcon(getClass().getResource(
				"graphics/roads/red_vert.png")).getImage(); 

		blue_road_down = new ImageIcon(getClass().getResource(
				"graphics/roads/blue_down.png")).getImage(); 

		blue_road_up = new ImageIcon(getClass().getResource(
				"graphics/roads/blue_up.png")).getImage();

		blue_road_vert = new ImageIcon(getClass().getResource(
				"graphics/roads/blue_vert.png")).getImage();

		green_road_down = new ImageIcon(getClass().getResource(
				"graphics/roads/green_down.png")).getImage(); 

		green_road_up = new ImageIcon(getClass().getResource(
				"graphics/roads/green_up.png")).getImage(); 

		green_road_vert = new ImageIcon(getClass().getResource(
				"graphics/roads/green_vert.png")).getImage(); 

		yellow_road_down = new ImageIcon(getClass().getResource(
				"graphics/roads/yellow_down.png")).getImage(); 

		yellow_road_up = new ImageIcon(getClass().getResource(
				"graphics/roads/yellow_up.png")).getImage(); 

		yellow_road_vert = new ImageIcon(getClass().getResource(
				"graphics/roads/yellow_vert.png")).getImage();
		
		statusAcc = new ImageIcon(getClass().getResource(
				"graphics/buttonPics/accepted.png")).getImage(); 
		
		statusDen = new ImageIcon(getClass().getResource(
				"graphics/buttonPics/denied.png")).getImage(); 
		
		statusWait = new ImageIcon(getClass().getResource(
				"graphics/buttonPics/waiting.png")).getImage(); 
		
		
		
		
		avatarArray = new Image[] {daemonAvatar, kerriganAvatar,tossAvatar,zergAvatar,artasAvatar,daemonAvatar,humanAvatar,nightelfAvatar,orcAvatar,undeadAvatar,daemonAvatar};
	}
}
