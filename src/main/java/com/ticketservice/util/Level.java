package com.ticketservice.util;

public enum Level {
	ORCHESTRA(1),
	MAIN(2),
	BALCONY_1(3),
	BALCONY_2(4);
	
	private int levelId;
	
	public int getLevelId() {
		return levelId;
	}
	
	Level(int levelId){
		this.levelId = levelId;
	}
	
	private static final String LEVEL_ID1 = "Orchestra";
	private static final String LEVEL_ID2 = "Main";
	private static final String LEVEL_ID3 = "Balcony 1";
	private static final String LEVEL_ID4 = "Balcony 2";
	
	public static String getLevelDesc(final Level level) {
		
		if(Level.ORCHESTRA == level){
			return LEVEL_ID1;
		}
		else if(Level.MAIN == level){
			return LEVEL_ID2;
		}
		else if(Level.BALCONY_1 == level){
			return LEVEL_ID3;
		}
		else if(Level.BALCONY_2 == level){
			return LEVEL_ID4;
		}
		
		return null;
	}
	
	public static String getLevelDesc(final int level) {
		
		if(Level.ORCHESTRA.levelId == level){
			return LEVEL_ID1;
		}
		else if(Level.MAIN.levelId == level){
			return LEVEL_ID2;
		}
		else if(Level.BALCONY_1.levelId == level){
			return LEVEL_ID3;
		}
		else if(Level.BALCONY_2.levelId == level){
			return LEVEL_ID4;
		}
		
		return null;
	}
}
