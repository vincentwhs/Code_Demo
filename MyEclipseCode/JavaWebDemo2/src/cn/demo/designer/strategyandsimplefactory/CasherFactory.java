package cn.demo.designer.strategyandsimplefactory;

public class CasherFactory {
	
	public static CasherSuper getCasher(String type){
		
		switch (type) {
		case "正常收费":
			return new CasherNormal();
		case "打五折":
			return new CasherRebate("0.5");
		default:
			return new CasherNormal();
		}
		
	}

}
