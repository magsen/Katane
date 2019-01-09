/*********************************************************************************
*     File Name           :     TradeManager.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2018-12-26 18:13]
*     Description         :     A class that handles the trade between players
**********************************************************************************/

package Katane;

import java.util.ArrayList;

/* Class TradeManager manage the trades */
public class TradeManager {

	private Katane katane;

	/* Constructor - TradeManager belongs to Katane */
	public TradeManager(Katane katane) {
		this.katane = katane;
	}
	
	/* TRADE METHOD 
	 * 
	 * sP - Sending Player
	 * rP - Receiving Player 
	 * rQuantity - Ressource Quantity
	 * 			   [Grain,Ore,Brick,Wool,Wood]
	 * This order must be respect, you can use negative value in order to send ressource to the Sending Player.
	 * Auto Verify that both player have sufficient Ressources before trade.
	 * 
	 * */
	public void trade(Player sP, Player rP, int rQuantity[]){
			ArrayList<Ressource> SR = sP.getRessource();
			ArrayList<Ressource> RR = rP.getRessource();
			for(Ressource sr : SR) {
				for(Ressource rr : RR) {
					if(sr.getClass()==rr.getClass()) {
						if(sr instanceof Grain) {
							if(sr.quantity - rQuantity[0] < 0 || rr.quantity + rQuantity[0] <0) {return;}
						}
						if(sr instanceof Ore) {
							if(sr.quantity - rQuantity[1] < 0 || rr.quantity + rQuantity[0] <0) {return;}
						}
						if(sr instanceof Brick) {
							if(sr.quantity - rQuantity[2] < 0 || rr.quantity + rQuantity[0] <0) {return;}
						}
						if(sr instanceof Wood) {
							if(sr.quantity - rQuantity[3] < 0 || rr.quantity + rQuantity[0] <0) {return;}
						}
						if(sr instanceof Wood) {
							if(sr.quantity - rQuantity[4] < 0 || rr.quantity + rQuantity[0] <0) {return;}
						}
					}
				}
			}
			for(Ressource sr : SR) {
				for(Ressource rr : RR) {
					if(sr.getClass()==rr.getClass()) {
						if(sr instanceof Grain) {
							sr.quantity -= rQuantity[0];
							rr.quantity += rQuantity[0];
						}
						if(sr instanceof Ore) {
							sr.quantity -= rQuantity[1];
							rr.quantity += rQuantity[1];
						}
						if(sr instanceof Brick) {
							sr.quantity -= rQuantity[2];
							rr.quantity += rQuantity[2];
						}
						if(sr instanceof Wood) {
							sr.quantity -= rQuantity[3];
							rr.quantity += rQuantity[3];
						}
						if(sr instanceof Wood) {
							sr.quantity -= rQuantity[4];
							rr.quantity += rQuantity[4];
						}
					}
				}	
			}
	}
	/* trade with index of player */
	public void trade(int sP, int rP, int rQuantity[]){
		this.trade(this.katane.getPlayerN(sP), this.katane.getPlayerN(rP) , rQuantity);
	}
	public void tradeB(Player Player, int rQuantity[]) {
		ArrayList<Ressource> SR = Player.getRessource();
		for(Ressource sr : SR) {
					if(sr instanceof Grain) {
						if(sr.quantity - rQuantity[0] < 0 ) {return;}
					}
					if(sr instanceof Ore) {
						if(sr.quantity - rQuantity[1] < 0 ) {return;}
					}
					if(sr instanceof Brick) {
						if(sr.quantity - rQuantity[2] < 0 ) {return;}
					}
					if(sr instanceof Wood) {
						if(sr.quantity - rQuantity[3] < 0 ) {return;}
					}
					if(sr instanceof Wood) {
						if(sr.quantity - rQuantity[4] < 0 ) {return;}
					}
				
		}
		for(Ressource sr : SR) {
					if(sr instanceof Grain) {
						sr.quantity -= rQuantity[0];
					}
					if(sr instanceof Ore) {
						sr.quantity -= rQuantity[1];
					}
					if(sr instanceof Brick) {
						sr.quantity -= rQuantity[2];
					}
					if(sr instanceof Wood) {
						sr.quantity -= rQuantity[3];
					}
					if(sr instanceof Wood) {
						sr.quantity -= rQuantity[4];
					}
					
		}
	}
	public void tradeB(int Player, int rQuantity[]){
		this.tradeB(this.katane.getPlayerN(Player) , rQuantity);
	}
}
