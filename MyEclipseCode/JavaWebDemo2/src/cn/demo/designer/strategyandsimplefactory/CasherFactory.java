package cn.demo.designer.strategyandsimplefactory;

public class CasherFactory {
	
	public static CasherSuper getCasher(String type){
		
		switch (type) {
		case "�����շ�":
			return new CasherNormal();
		case "������":
			return new CasherRebate("0.5");
		default:
			return new CasherNormal();
		}
		
	}

}
