package com.codingfreaks.NiagaraFallsCurlingClub.constants;

public class WeeklyTimings {

	public enum WeeklyTimingsENUM {
		MONseniorMen,
		MONpartnersLeague,
		TUEdayLadies,
		TUEseniorMen,
		TUErecreationalCoed,
		WEDdayLadies,
		WEDseniorMen,
		WEDbrodie,
		THUdayCoed,
		THUbusinessWomen,
		FRInightDoubles,
		SUNlittleRocks,
		SUNyouthCurling
		}

	
	public static String weeklyTimingName(WeeklyTimingsENUM timing)
	{
	

		    switch(timing) {
		    
		      case MONseniorMen:
		    	  return "Senior Men";
		      case MONpartnersLeague:
		    	  return "Partner's League";
		      case TUEdayLadies:
		    	  return "Day Ladies";
		      case TUEseniorMen:
		    	  return "Senior Men";
		      case TUErecreationalCoed:
			        return "Recreational Co-Ed";
		      case WEDdayLadies:
			       return "Day Ladies";
		      case WEDseniorMen:
			        return "Senior Men";
		      case WEDbrodie:
			        return "Brodie";
		      case THUdayCoed:
			        return "Day Co-Ed";
		      case THUbusinessWomen:
			        return "Business Women";
			  case FRInightDoubles:
				  	return "Friday Night Doubles";
		      case SUNlittleRocks:
			        return "Little Rocks";
		      case SUNyouthCurling:
			       return "Youth Curling";
			  default : 
				  return "null";
		    }
	}
}



